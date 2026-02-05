#!/usr/bin/bash
source $(dirname "$0")/lib-help.sh
debug_level="trace"

log debug "Script: $0, args: [$@]"
log debug "All arguments: $@"
log debug "Count of arguments: $#"

# Validate that at least one argument is provided
if [[ $# -eq 0 ]]; then
  log error "No arguments provided. Usage:"
  log error "  $0 'command'                    - Single command (e.g., 'git branch')"
  log error "  $0 'cmd1', 'cmd2', 'cmd3'       - Comma-separated commands"
  log error "  $0 /path/to/file                - File with commands (one per line)"
  exit 1
fi

# Join all arguments into a single string (in case user passed multiple args)
input_string="$*"

# Parse input to determine type and extract commands
parseInput "$input_string"

# Process each command
for cmd in "${parsed_commands[@]}"; do
  log info "=========================================="
  log info "Processing command: [$cmd]"
  log info "=========================================="
  
  # Parse the command into components
  parseCommand "$cmd"
  
  # Validate command exists
  validateCommandName "$shell_command_name"
  
  # Find binary
  binary_placement=$(findBinary "$shell_command_name")
  
  # Create branch, dump dir, help file, and commit
  createBranch
  createDumpDir
  createHelpFileDump
  #commitChanges
  
  printCompletionSummary
  
  log info "Completed processing: [$cmd]"
  log info ""
done

log info "All commands processed successfully!"
exit 0