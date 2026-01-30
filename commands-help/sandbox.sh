#!/bin/bash

source $(dirname "$0")/lib-d.sh
source $(dirname "$0")/framework/test/bash_unit.sh
source $(dirname "$0")/framework/output/colors.sh

log_statement=$(log test "Message")

#assert type "actual value" comparison "expected value"
assert text "$log_statement" equals "Wololo"
assert text "$log_statement" equals "[TEST] Message"