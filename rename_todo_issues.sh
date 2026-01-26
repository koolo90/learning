#!/bin/bash
# Bulk rename GitHub issues by removing *TODO* prefix
# Usage: ./rename_todo_issues.sh
# Requirements: GitHub CLI (gh) must be installed and authenticated

set -e

REPO="koolo90/learning"

echo "=========================================="
echo "Bulk Rename TODO Issues"
echo "Repository: $REPO"
echo "=========================================="
echo ""

# Check if gh is installed
if ! command -v gh &> /dev/null; then
    echo "❌ Error: GitHub CLI (gh) is not installed."
    echo "Please install it from: https://cli.github.com/"
    exit 1
fi

# Check if authenticated
if ! gh auth status &> /dev/null; then
    echo "❌ Error: Not authenticated with GitHub CLI."
    echo "Please run: gh auth login"
    exit 1
fi

echo "✓ GitHub CLI is installed and authenticated"
echo ""

# Array of issues to rename
declare -a issues=(
    "83|`container-workdir`: Change working directory."
    "82|`container-volume`: Create volume mounts."
    "81|`container-user`: Set user and group ID."
    "80|`container-stopsignal`: Specify the system call signal for exiting a container."
    "79|`container-shell`: Set the default shell of an image."
    "78|`container-run`: Execute build commands."
    "77|`container-onbuild`: Specify instructions for when the image is used in a build."
    "76|`container-maintainer`: Specify the author of an image."
    "75|`container-label`: Add metadata to an image."
    "74|`container-healthchek`: Check a container's health on startup."
    "73|`container-from`: Create a new build stage from a base image."
    "72|`container-expose`: Describe which ports your application is listening on."
    "71|`container-env`: Set environment variables."
    "70|`container-entrypoint`: Specify default executable."
    "69|`container-copy`: Copy files and directories."
    "68|`container-cmd`: Specify default commands."
    "67|`container-arg`: Use build-time variables."
)

total=${#issues[@]}
success=0
failed=0

echo "Processing $total issues..."
echo ""

for item in "${issues[@]}"; do
    IFS='|' read -r issue_num new_title <<< "$item"
    
    echo "[$((success + failed + 1))/$total] Processing issue #$issue_num..."
    echo "  New title: $new_title"
    
    if gh issue edit "$issue_num" --title "$new_title" --repo "$REPO" &> /dev/null; then
        echo "  ✓ Success"
        ((success++))
    else
        echo "  ✗ Failed"
        ((failed++))
    fi
    echo ""
    
    # Add a small delay to avoid rate limiting
    sleep 1
done

echo "=========================================="
echo "Summary"
echo "=========================================="
echo "Total issues:     $total"
echo "Successfully renamed: $success"
echo "Failed:          $failed"
echo ""

if [ $failed -eq 0 ]; then
    echo "✓ All issues renamed successfully!"
    exit 0
else
    echo "⚠ Some issues failed to rename. Please check manually."
    exit 1
fi
