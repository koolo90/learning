#!/usr/bin/bash

echo "DEBUG: Script: $0, args: [$@]"

pushd .

# ====] LOG LEVELS: [====
L_TRACE="trace"
L_DEBUG="debug"
L_INFO="info"
L_WARN="warn"
L_ERROR="error"
L_FATAL="fatal"
# =======================

function log() {
  local level=$(echo $1 | tr '[:lower:]' '[:upper:]')
  echo -e "[$level] ${@:2}"
}

function findBinary {
  shell_command_name="$1"
  log info "Function: $FUNCNAME args=[$@]"

  log info "Checking if command [$shell_command_name] exists..."

  local binary_placemet=$(which $shell_command_name)
  log info "Binary found in: [$binary_placemet]"
  echo $binary_placemet
}

function createBranch {
  branch_name="feature/$shell_command_name-help-dump"
  if [[ $(git rev-parse --verify $branch_name) ]] ; then
    git checkout $branch_name
  else
    git branch $branch_name main
  fi
}

function createDumpDir {
    git_direcory_root_unix_path=$( git rev-parse --show-toplevel | sed "s/C:/\/c/" )
    log debug "git_direcory_root=$git_direcory_root_unix_path"
    help_dir_dump_path="$git_direcory_root_unix_path/commands-help/$shell_command_name"

    if [[ ! -d "$help_dir_dump_path" ]]; then
        log info "Directory [$help_dir_dump_path] does not exist, creating it..."
        mkdir -p "$help_dir_dump_path"
    else
        log info "Directory [$help_dir_dump_path] already exists."
    fi
}

function createHelpFileDump {
  help_file_dump_path="$help_dir_dump_path/help.txt"
  log info "Dumping help for [$shell_command_name] into [$help_file_dump_path]"

  if $shell_command_name --help > "$help_file_dump_path" 2>&1; then
    log info "Successfully dumped help for [$shell_command_name]"
  else
    log error "Failed to dump help for [$shell_command_name]"
    return 1
  fi
}

function validateCommandName {
  if [[ -z "$1" ]]; then
    log error "No argument passed. Usage: $0 <command_name>"
    exit 1
  fi
}

function commitChanges {
  log info "Staging and committing changes for [$shell_command_name]..."
  git add "$help_dir_dump_path"
  local commit_msg="feat(help): dumped help for $shell_command_name"

  if git commit -m "$commit_msg"; then
    log info "Successfully committed changes with message: [$commit_msg]"
  else
    log error "Failed to commit changes."
    return 1
  fi
}

shell_command_name="$1"
validateCommandName $shell_command_name

binary_placemet=$(findBinary "$shell_command_name")

createBranch
createDumpDir
createHelpFileDump
commitChanges

log info "You can now publish changes by executing:"
log info "\t git push origin $branch_name"
log info "File is stored in dir: [$help_dir_dump_path]"

popd
exit 0;