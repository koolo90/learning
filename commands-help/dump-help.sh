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
    exit -1
fi

echo "DEBUG: Creating branch for [$shell_command_name], named: [$branch_name]"
if [[ $(git status --porcelain) ]]; then
  echo "INFO: git repository poluted with user changes, aborting!"
  git status
  exit -2
else
  echo "INFO: git repository status is clear, creating branch"
  echo "DEBUG: Temporarily disabled for development purposes..."
  git fetch
  git branch "$branch_name" origin/main
  git checkout "$branch_name"
fi

echo "INFO: Creating directory for: [$shell_command_name], path: [$help_dir_dump_path]"
mkdir -p "$help_dir_dump_path" && ls "$help_dir_dump_path"
echo "INFO: Creating help file dump for: [$shell_command_name], path: [$help_file_dump_path]"
touch $help_file_dump_path && ls "$help_file_dump_path"
echo "INFO: Dumping help page for: [$shell_command_name]"
bash -c "$shell_command_name --help" >> "$help_file_dump_path"

git add "$help_file_dump_path"
git commit -m "feat(help): Dumped help for $shell_command_name"
git checkout -

echo "You can now publish changes by executing:"
echo -e "\t git push origin $branch_name"
echo "File is stored in dir: [$help_dir_dump_path]"
echo "Content of grabbed help file for [$shell_command_name] is: "
echo "=== [$help_file_dump_path] ==="
echo "=== EOF: [$help_file_dump_path] ==="
popd