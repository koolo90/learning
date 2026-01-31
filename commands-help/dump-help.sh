#!/usr/bin/bash
source $(dirname "$0")/lib-help.sh
debug_level="trace"

log debug "Script: $0, args: [$@]"
log debug "All arguments: $@"
log debug "Count of arguments: $#"
log debug "Count of arguments: ${#a[@]}"

# Copy all script arguments to a local array named 'args'.
# The syntax 'args=("$@")' expands each positional parameter to a 
# separate word, preserving spaces within individual arguments.
args=("$@")

for i in "${!args[@]}"; do
  human_readable_index=$((i+1))
  log debug "$human_readable_index: ${args[$i]}"
done

pushd .

shell_command_name="${args[0]}"
shel_command_args="${args[1]}"
validateCommandName "$shell_command_name"

binary_placement=$(findBinary "${args[@]}")

createBranch
createDumpDir
createHelpFileDump
commitChanges

printCompletionSummary

# popd
exit 0;