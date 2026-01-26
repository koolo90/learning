#!/usr/bin/bash

echo "DEBUG: Script: $0, args: [$@]"

source $(dirname "$0")/lib-help.sh

pushd .

shell_command_name="$1"
validateCommandName $shell_command_name

binary_placemet=$(findBinary "$shell_command_name")

createBranch
createDumpDir
createHelpFileDump
commitChanges

printCompletionSummary

popd
exit 0;