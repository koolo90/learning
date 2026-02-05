#!/usr/bin/bash

# Example usage of dump-help.sh script
# This script demonstrates the three different ways to provide commands

echo "=========================================="
echo "dump-help.sh Usage Examples"
echo "=========================================="
echo ""

echo "1. Single command:"
echo "   ./dump-help.sh 'ls'"
echo ""

echo "2. Single command with subcommand:"
echo "   ./dump-help.sh 'git branch'"
echo ""

echo "3. Comma-separated commands:"
echo "   ./dump-help.sh 'ls, git status, docker ps'"
echo ""

echo "4. File with commands (one per line):"
echo "   ./dump-help.sh /path/to/commands.txt"
echo ""

echo "Example file format (commands.txt):"
echo "   # Lines starting with # are comments"
echo "   git branch"
echo "   git status"
echo "   docker ps"
echo "   ls -la"
echo ""

echo "=========================================="
echo "Running actual examples..."
echo "=========================================="

# Example 1: Single simple command
echo ""
echo "Example 1: Testing with 'echo' command..."
LOG_LEVEL=INFO ./dump-help.sh "echo"

# To test more examples, uncomment the following:
# echo ""
# echo "Example 2: Testing with 'git branch'..."
# LOG_LEVEL=INFO ./dump-help.sh "git branch"

# echo ""
# echo "Example 3: Testing with comma-separated commands..."
# LOG_LEVEL=INFO ./dump-help.sh "ls, echo"
