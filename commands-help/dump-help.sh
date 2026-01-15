#!/usr/bin/bash

pwd
pushd .

git_direcory_root_unix_path=$( git rev-parse --show-toplevel | sed "s/C:/\/c/" )
echo "DEBUG: git_direcory_root=$git_direcory_root_unix_path"

shell_command_name="$1"
branch_name="feature/$shell_command_name-help-dump"
help_dir_dump_path="$git_direcory_root_unix_path/commands-help/$shell_command_name"
help_file_dump_path="$git_direcory_root_unix_path/commands-help/$shell_command_name/help.txt"
echo "INFO: Checking if command [$shell_command_name] exists..."
echo "find /usr/bin -name \"$shell_command_name.exe\""

if [[ $(find /usr/bin -name "$shell_command_name.exe") ]]; then
    echo "DEBUG: Executable for [$shell_command_name] found."
else
    echo "ERROR: Executable for [$shell_command_name] not found!"
    exit 1
fi

echo "DEBUG: Creating branch for [$shell_command_name], named: [$branch_name]"
git status
echo "INFO: Creating directory for: [$shell_command_name], path: [$help_dir_dump_path]"
echo "INFO: Creating help file dump for: [$shell_command_name], path: [$help_file_dump_path]"
echo "INFO: Dumping help page for: [$shell_command_name]"
echo "bash -c "$shell_command_name --help" > /dev/null"

#pwd
#git status .
# git stash
# git checkout main
# git pull

#shell_command_name="$1"
#echo "INFO: Cleanup: [$shell_command_name]... "
#rm -rf "$shell_command_name"
#git checkout -b "feature/bash-help-$shell_command_name"

#mkdir -p $shell_command_name
#touch $shell_command_name/help.txt
#bash -c "$shell_command_name --help" > $shell_command_name/help.txt

#git status "./$shell_command_name"
#git add "./$shell_command_name/"
#git status "./$shell_command_name"

#git commit -m "doc(help): Added $shell_command_name help file"
#git push
#git checkout main

git branch -d "$branch_name"
popd