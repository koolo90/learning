#!/bin/bash

# Test suite for dump-help.sh script
# This script tests the functionality of parsing commands with sub-functions

# Source the library functions for testing
SCRIPT_DIR="$(dirname "$0")"
source "$SCRIPT_DIR/../lib-help.sh"

# Color codes for output
GREEN='\033[0;32m'
RED='\033[0;31m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

# Test counters
TESTS_RUN=0
TESTS_PASSED=0
TESTS_FAILED=0

# Helper function to run a test
run_test() {
    local test_name="$1"
    local expected="$2"
    local actual="$3"
    
    TESTS_RUN=$((TESTS_RUN + 1))
    
    echo -e "\n${YELLOW}Test $TESTS_RUN: $test_name${NC}"
    
    if [[ "$actual" == "$expected" ]]; then
        echo -e "${GREEN}✓ PASSED${NC}"
        echo "  Expected: $expected"
        echo "  Got:      $actual"
        TESTS_PASSED=$((TESTS_PASSED + 1))
        return 0
    else
        echo -e "${RED}✗ FAILED${NC}"
        echo "  Expected: $expected"
        echo "  Got:      $actual"
        TESTS_FAILED=$((TESTS_FAILED + 1))
        return 1
    fi
}

# Helper function to run array test
run_array_test() {
    local test_name="$1"
    local expected_count="$2"
    local actual_count="${#parsed_commands[@]}"
    
    TESTS_RUN=$((TESTS_RUN + 1))
    
    echo -e "\n${YELLOW}Test $TESTS_RUN: $test_name${NC}"
    
    if [[ "$actual_count" == "$expected_count" ]]; then
        echo -e "${GREEN}✓ PASSED${NC}"
        echo "  Expected count: $expected_count"
        echo "  Got count:      $actual_count"
        echo "  Commands: ${parsed_commands[@]}"
        TESTS_PASSED=$((TESTS_PASSED + 1))
        return 0
    else
        echo -e "${RED}✗ FAILED${NC}"
        echo "  Expected count: $expected_count"
        echo "  Got count:      $actual_count"
        echo "  Commands: ${parsed_commands[@]}"
        TESTS_FAILED=$((TESTS_FAILED + 1))
        return 1
    fi
}

echo "=========================================="
echo "  dump-help.sh Test Suite"
echo "=========================================="

# Test 1: Parse simple command without subcommand
echo -e "\n--- Testing parseCommand function ---"
parseCommand "ls"
run_test "Parse simple command 'ls'" "ls" "$shell_command_name"
run_test "Subcommand should be empty for 'ls'" "" "$shell_subcommand"

# Test 2: Parse command with subcommand
parseCommand "git branch"
run_test "Parse command with subcommand - command part" "git" "$shell_command_name"
run_test "Parse command with subcommand - subcommand part" "branch" "$shell_subcommand"

# Test 3: Parse command with subcommand (docker)
parseCommand "docker ps"
run_test "Parse docker command - command part" "docker" "$shell_command_name"
run_test "Parse docker command - subcommand part" "ps" "$shell_subcommand"

# Test 4: Parse single command input
echo -e "\n--- Testing parseInput for single command ---"
parseInput "git status"
run_array_test "Single command should result in 1 command" "1"
run_test "Single command should be 'git status'" "git status" "${parsed_commands[0]}"

# Test 5: Parse comma-separated commands
echo -e "\n--- Testing parseInput for comma-separated commands ---"
parseInput "git branch, docker ps, ls -la"
run_array_test "Comma-separated should result in 3 commands" "3"
run_test "First command should be 'git branch'" "git branch" "${parsed_commands[0]}"
run_test "Second command should be 'docker ps'" "docker ps" "${parsed_commands[1]}"
run_test "Third command should be 'ls -la'" "ls -la" "${parsed_commands[2]}"

# Test 6: Parse comma-separated with quotes
parseInput '"git branch", "docker ps", "kubectl get pods"'
run_array_test "Quoted comma-separated should result in 3 commands" "3"
run_test "First quoted command" "git branch" "${parsed_commands[0]}"
run_test "Second quoted command" "docker ps" "${parsed_commands[1]}"
run_test "Third quoted command" "kubectl get pods" "${parsed_commands[2]}"

# Test 7: Parse commands from file
echo -e "\n--- Testing parseInput for file input ---"
# Create a temporary test file
TEST_FILE="/tmp/test-commands-$$.txt"
cat > "$TEST_FILE" << 'EOF'
# This is a comment
git branch

docker ps
ls -la
# Another comment
EOF

parseInput "$TEST_FILE"
run_array_test "File with 3 commands (ignoring comments)" "3"
run_test "First command from file" "git branch" "${parsed_commands[0]}"
run_test "Second command from file" "docker ps" "${parsed_commands[1]}"
run_test "Third command from file" "ls -la" "${parsed_commands[2]}"

# Clean up test file
rm -f "$TEST_FILE"

# Test 8: Verify help flag is set correctly
echo -e "\n--- Testing help flag ---"
parseCommand "git branch"
run_test "Help flag should default to '-h'" "-h" "$help_flag"

# Test 9: Test parseCommaSeparatedCommands with various spacing
echo -e "\n--- Testing parseCommaSeparatedCommands with various formats ---"
parseCommaSeparatedCommands "git branch,docker ps,  kubectl get"
run_array_test "Commands with varied spacing" "3"
run_test "First command (no space after comma)" "git branch" "${parsed_commands[0]}"
run_test "Second command (no space after comma)" "docker ps" "${parsed_commands[1]}"
run_test "Third command (spaces after comma)" "kubectl get" "${parsed_commands[2]}"

# Test 10: Empty file handling
echo -e "\n--- Testing empty file handling ---"
EMPTY_FILE="/tmp/empty-commands-$$.txt"
touch "$EMPTY_FILE"
parseCommandsFromFile "$EMPTY_FILE"
run_array_test "Empty file should result in 0 commands" "0"
rm -f "$EMPTY_FILE"

# Print summary
echo ""
echo "=========================================="
echo "  Test Summary"
echo "=========================================="
echo "Total tests run:    $TESTS_RUN"
echo -e "${GREEN}Tests passed:       $TESTS_PASSED${NC}"
if [[ $TESTS_FAILED -gt 0 ]]; then
    echo -e "${RED}Tests failed:       $TESTS_FAILED${NC}"
else
    echo -e "Tests failed:       $TESTS_FAILED"
fi
echo "=========================================="

# Exit with appropriate code
if [[ $TESTS_FAILED -gt 0 ]]; then
    exit 1
else
    exit 0
fi
