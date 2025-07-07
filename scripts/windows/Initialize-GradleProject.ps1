# Enable advanced parameter binding and common parameters like -Verbose, -ErrorAction, etc.
[CmdletBinding()]
param (
    # Mandatory path for the Gradle project root.
    [Parameter(Mandatory)]
    [ValidateNotNullOrEmpty()]  # Prevents null or empty string values.
    [string] $ProjectRoot
)

# Define the function that encapsulates the Gradle project initialization logic.
function Initialize-GradleProject {
    [CmdletBinding()]
    param ()

    try {
        # Create the project directory if it doesn't already exist.
        # The -Force flag prevents errors if it already exists.
        $null = New-Item -Path $ProjectRoot -ItemType Directory -Force -ErrorAction Stop

        # Change to the project directory
        # (this change will persist after the script ends).
        Set-Location -Path $ProjectRoot

        # Run 'gradle init' interactively in the current directory.
        Write-Verbose "Running 'gradle init' in '$ProjectRoot'"
        & gradle init
    }
    catch {
        # Display a clear error message if anything goes wrong.
        Write-Error "Failed to initialize Gradle project: $_"
    }
}

# Invoke the function to initialize the project.
Initialize-GradleProject
