#!/bin/bash

# Source the log function
source "$(dirname "$0")/../_framework/utils/log.sh"

echo "Running log() function tests..."
echo "--------------------------------"

# Helper function to run a test and print a header
run_test() {
    echo -e "\n>>> Scenario: $1"
    shift
    log "$@"
}

# 1. Test all levels by full name (lowercase)
run_test "Full name - trace" trace "Message for trace"
run_test "Full name - debug" debug "Message for debug"
run_test "Full name - info" info "Message for info"
run_test "Full name - warn" warn "Message for warn"
run_test "Full name - error" error "Message for error"
run_test "Full name - fatal" fatal "Message for fatal"

# 2. Test all levels by initials (lowercase)
run_test "Initial - t" t "Message for t (trace)"
run_test "Initial - d" d "Message for d (debug)"
run_test "Initial - i" i "Message for i (info)"
run_test "Initial - w" w "Message for w (warn)"
run_test "Initial - e" e "Message for e (error)"
run_test "Initial - f" f "Message for f (fatal)"

# 3. Test case insensitivity
run_test "Mixed case name - TrAcE" TrAcE "Message for TrAcE"
run_test "Uppercase initial - I" I "Message for I (info)"

# 4. Test multiple arguments (message with spaces and extra params)
run_test "Multiple arguments" info "Message" "with" "spaces" "and" "extra" "args"

# 5. Test unsupported log level (traversal to FATAL)
run_test "Unsupported level - unknown" unknown "This should be FATAL and show MISUSE/HINT"
run_test "Unsupported level - x" x "This should also be FATAL and show MISUSE/HINT"

# 6. Test empty level (technically unsupported)
run_test "Empty level" "" "Message with empty level"

echo -e "\n--------------------------------"
echo "Tests completed."
