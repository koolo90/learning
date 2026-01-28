# Contribution guidelines
# Introduction
TODO: Add introduction

## Table of Contents
- [GitHub Actions](#github-actions)

## GitHub Actions
### Automatic Branch Deletion
This repository includes a GitHub Action that automatically deletes branches 
after they have been merged to the `main` branch. 
This helps keep the repository clean by removing obsolete branches.

#### How it works
- **Trigger**: The action runs when a pull request is closed
- **Condition**: The branch is only deleted if the PR was merged (not just closed)
- **Protection**: Common protected branches (`main`, `master`, `develop`, `development`) are never deleted
- **Location**: `.github/workflows/delete-merged-branch.yml`

#### Benefits
- Keeps the repository clean and organized
- Reduces clutter from old feature branches
- Automatically manages branch lifecycle
- No manual intervention is required after merging PRs

#### Source Code
- [`.github/workflows/delete-merged-branch.yml`](.github/workflows/delete-merged-branch.yml)


