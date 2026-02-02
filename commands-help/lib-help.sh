#!/usr/bin/bash

# Source required utilities
source "$(dirname "${BASH_SOURCE[0]}")/_framework/utils/log.sh"
source "$(dirname "${BASH_SOURCE[0]}")/_framework/utils/gathered.sh"

# Trim whitespace and quotes from a string
function trim {
  local str="$1"
  # Remove leading/trailing whitespace and quotes
  str="${str#"${str%%[![:space:]]*}"}"  # Remove leading whitespace
  str="${str%"${str##*[![:space:]]}"}"  # Remove trailing whitespace
  str="${str#\"}"  # Remove leading quote
  str="${str%\"}"  # Remove trailing quote
  echo "$str"
}

# Parse a single command into command and subcommand parts
# Example: "git branch" -> command="git", subcommand="branch"
function parseCommand {
  local cmd="$1"
  log debug "Parsing command: [$cmd]"
  
  # Split command by spaces
  read -ra parts <<< "$cmd"
  
  export shell_command_name="${parts[0]}"
  export shell_subcommand=""
  export help_flag="-h"
  
  # If there's more than one part, second part is subcommand
  if [[ ${#parts[@]} -gt 1 ]]; then
    export shell_subcommand="${parts[1]}"
  fi
  
  log debug "Command: [$shell_command_name], Subcommand: [$shell_subcommand]"
}

# Parse comma-separated list of commands
function parseCommaSeparatedCommands {
  local input="$1"
  log debug "Parsing comma-separated commands: [$input]"
  
  # Split by comma and trim spaces
  IFS=',' read -ra commands <<< "$input"
  
  # Export array of commands
  export parsed_commands=()
  for cmd in "${commands[@]}"; do
    # Trim leading/trailing whitespace and quotes
    cmd=$(trim "$cmd")
    if [[ -n "$cmd" ]]; then
      parsed_commands+=("$cmd")
      log debug "Added command: [$cmd]"
    fi
  done
  
  log info "Parsed ${#parsed_commands[@]} commands"
}

# Parse commands from a file (one command per line)
function parseCommandsFromFile {
  local file_path="$1"
  log debug "Parsing commands from file: [$file_path]"
  
  if [[ ! -f "$file_path" ]]; then
    log error "File does not exist: [$file_path]"
    return 1
  fi
  
  export parsed_commands=()
  while IFS= read -r line; do
    # Skip empty lines and comments
    [[ -z "$line" || "$line" =~ ^[[:space:]]*# ]] && continue
    
    # Trim whitespace
    line=$(trim "$line")
    
    if [[ -n "$line" ]]; then
      parsed_commands+=("$line")
      log debug "Added command from file: [$line]"
    fi
  done < "$file_path"
  
  log info "Parsed ${#parsed_commands[@]} commands from file"
}

# Determine input type and parse accordingly
function parseInput {
  local input="$1"
  log debug "Determining input type for: [$input]"
  
  # Check if input is a file
  if [[ -f "$input" ]]; then
    log info "Input is a file"
    parseCommandsFromFile "$input"
    return 0
  fi
  
  # Check if input contains comma (comma-separated list)
  if [[ "$input" == *","* ]]; then
    log info "Input is comma-separated list"
    parseCommaSeparatedCommands "$input"
    return 0
  fi
  
  # Otherwise, treat as single command
  log info "Input is single command"
  export parsed_commands=("$input")
}
