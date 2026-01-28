#!/bin/bash

source_branch=$1
target_branch=$2

#TODO: if repo clean
gti checkout $target_branch
git pull

git checkout $source_branch
git pull

git checkout - # goes to $target_branch
git merge
#TODO: if status ok and no merge conflicts

git push
git checkout - # goes to $source_branch
git branch -D $target_branch