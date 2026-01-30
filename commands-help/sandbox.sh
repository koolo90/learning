#!/bin/bash

source $(dirname "$0")/_framework/utils/gathered.sh
source $(dirname "$0")/_framework/test/bash_unit.sh
source $(dirname "$0")/_framework/output/colors.sh
source $(dirname "$0")/_framework/utils/arguments.sh
source $(dirname "$0")/_fframework/utils/log.sh

log_statement=$(log test "Message")

# assert type "actual value" comparison "expected value"
# assert text "$(log test "Message")" equals "Wololo"
# The assertion below might need adjustment because of the timestamp
# assert text "$(log test "Message")" equals "[TEST] Message"

log t "Test trace 1"
log T "Test trace 1"
log trace "Test trace 1"
log Trace "Test trace 1"
log TRACE "Test trace 1"