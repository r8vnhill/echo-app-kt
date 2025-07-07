#!/usr/bin/env bash

set -euo pipefail

# Display usage information
print_usage() {
    echo "Usage: $0 <project-root>"
    echo
    echo "Initializes a Gradle project in the specified directory."
}

# Trap common errors and provide meaningful output
trap 'echo "❌ An unexpected error occurred."; exit 1' ERR

# Ensure exactly one argument is passed
if [[ $# -ne 1 ]]; then
    echo "❌ Error: Missing required argument: project-root"
    print_usage
    exit 1
fi

# Assign and sanitize argument
PROJECT_ROOT="$1"

# Disallow empty strings or strings with only whitespace
if [[ -z "${PROJECT_ROOT// }" ]]; then
    echo "❌ Error: Project root cannot be empty or whitespace."
    exit 1
fi

# Create the directory if it doesn't exist
mkdir -p "$PROJECT_ROOT"

# Change to the project directory
cd "$PROJECT_ROOT"

# Run gradle init interactively
echo "🚀 Running 'gradle init' in '$PROJECT_ROOT'..."
gradle init
