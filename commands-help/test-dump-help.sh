#!/bin/bash

set +x

echo "INFO: Deleting the directory `echo`"
rm -rf echo
git stash

echo "INFO: calling script dump-help.sh for echo command `echo`"

./dump-help.sh echo
ls echo

git log -2 feature/echo-help-dump --oneline --name-only
git branch -D feature/echo-help-dump
git stash pop