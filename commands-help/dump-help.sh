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

log info "You can now publish changes by executing:"
log info "\t git push origin $branch_name"
log info "File is stored in dir: [$help_dir_dump_path]"

popd
exit 0;