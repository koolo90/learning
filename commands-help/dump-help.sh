#!/usr/bin/env bash

#pwd
#git status .
# git stash
# git checkout main
# git pull

shell_command_name="$1"
echo "INFO: Cleanup: [$shell_command_name]... "
rm -rf "$shell_command_name"
git checkout -b "feature/bash-help-$shell_command_name"

mkdir -p $shell_command_name
touch $shell_command_name/help.txt
bash -c "$shell_command_name --help" > $shell_command_name/help.txt

git status "./$shell_command_name"
git add "./$shell_command_name/"
git status "./$shell_command_name"

git commit -m "doc(help): Added $shell_command_name help file"
#git push
git checkout main
