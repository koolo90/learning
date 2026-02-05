source "$(dirname "${BASH_SOURCE[0]}")/log.sh"

function createBranch {
  # Build branch name including subcommand if present
  if [[ -n "$shell_subcommand" ]]; then
    branch_name="feature/$shell_command_name-$shell_subcommand-help-dump"
  else
    branch_name="feature/$shell_command_name-help-dump"
  fi
  
  if [[ $(git rev-parse --verify $branch_name 2>/dev/null) ]] ; then
    log info "Branch [$branch_name] is already in place, checking out.."
    git checkout $branch_name
  else
    log info "Creating branch [$branch_name]..."
    # Try to branch from main-local, fall back to HEAD if it doesn't exist
    if git rev-parse --verify main >/dev/null 2>&1; then
      git branch $branch_name main
      log debug "Branched from main"
    else
      git branch $branch_name HEAD
      log debug "Branched from HEAD (main-local not found)"
    fi
    git checkout $branch_name
  fi
}

function createDumpDir {
    git_direcory_root_unix_path=$( git rev-parse --show-toplevel | sed "s/C:/\/c/" )
    log debug "git_direcory_root=$git_direcory_root_unix_path"
    
    # Create directory path with subcommand if present
    if [[ -n "$shell_subcommand" ]]; then
        help_dir_dump_path="$git_direcory_root_unix_path/commands-help/$shell_command_name/$shell_subcommand"
    else
        help_dir_dump_path="$git_direcory_root_unix_path/commands-help/$shell_command_name"
    fi

    if [[ ! -d "$help_dir_dump_path" ]]; then
        log info "Directory [$help_dir_dump_path] does not exist, creating it..."
        mkdir -p "$help_dir_dump_path"
    else
        log info "Directory [$help_dir_dump_path] already exists."
    fi
}

function createHelpFileDump {
  help_file_dump_path="$help_dir_dump_path/help.txt"
  
  # Build the full command with subcommand if present
  local full_command="$shell_command_name"
  if [[ -n "$shell_subcommand" ]]; then
    full_command="$shell_command_name $shell_subcommand"
  fi
  
  log info "Dumping help for [$full_command] into [$help_file_dump_path]"

  # Try help flag (default -h, or use custom)
  if $full_command $help_flag > "$help_file_dump_path" 2>&1; then
    log info "Successfully dumped help for [$full_command] using flag [$help_flag]"
  elif $full_command --help > "$help_file_dump_path" 2>&1; then
    log info "Successfully dumped help for [$full_command] using flag [--help]"
  else
    log error "Failed to dump help for [$full_command]"
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
  local full_command="$shell_command_name"
  if [[ -n "$shell_subcommand" ]]; then
    full_command="$shell_command_name $shell_subcommand"
  fi
  
  log info "Staging and committing changes for [$full_command]..."
  git add "$help_dir_dump_path"
  local commit_msg="feat(help): dumped help for $full_command"

  if git commit -m "$commit_msg"; then
    log info "Successfully committed changes with message: [$commit_msg]"
  else
    log error "Failed to commit changes."
    return 1
  fi
}

function printCompletionSummary {
  log info "You can now publish changes by executing:"
  log info "\t git push origin $branch_name"
  log info "File is stored in dir: [$help_dir_dump_path]"
}

function findBinary {
  log info "Function: $FUNCNAME args=[$@]"
  shell_command_name="$1"
  log info "Checking if command [$shell_command_name] exists..."

  local binary_placemet=$(which $shell_command_name)
  log info "Binary found in: [$binary_placemet]"
  echo $binary_placemet
  return 0;
}