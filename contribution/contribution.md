# Contribution guidelines
# Introduction
This document will describe the contribution guidelines for this project.
It will cover how new code or documentation should be submitted.

## Contribution to tracking tool 
### Formalized way of conribution
If you observve that there is a function ality that you would like to see in the project, 
please createan issue in the project here: https://github.com/users/koolo90/projects/3. 

### For greedy beasts
If, however, as a greedy developer you've already started working in forked repository 
or separate branch (the project visbility might change over time), please at least 
commit your changes with a scope set f.e. 
`feat(no tracking): brief description`,
Later there will be implemented hooks that will prevent publishing commits that does not 
follow that rule. 

## Table of Contents
- [Branch Naming](#branch-naming)
- [Commit Messages](#commit-messages)
- [GitHub Actions](#github-actions)

## Semantic Branch Names
See how a minor change to your branch name style can make you a better programmer.

Format: `<type>/#<issueNumber>-<alias>`

### Example
```
feature/#1-init
^------^   ^---^
|          |
|          +---> issue's keyword
|
+-------> Type: or feat, chore, docs, fix, refactor, style, or test.
```

More Examples:
- `feat` or `feature`: (new feature for the user, not a new feature for build script)
- `fix`: (bug fix for the user, not a fix to a build script)
- `docs`: (changes to the documentation)
- `style`: (formatting, missing semi colons, etc; no production code change)
- `refactor`: (refactoring production code, eg. renaming a variable)
- `test`: (adding missing tests, refactoring tests; no production code change)
- `chore`: (updating grunt tasks etc; no production code change)

References:
- https://gist.github.com/joshbuchea/6f47e86d2510bce28f8e7f42ae84c716

## Semantic Commit Messages

See how a minor change to your commit message style can make you a better programmer.

Format: `<type>(<scope>): <subject>`

`<scope>` is optional

### Example

```
feat: add hat wobble
^--^  ^------------^
|     |
|     +-> Summary in present tense.
|
+-------> Type: chore, docs, feat, fix, refactor, style, or test.
```

More Examples:

- `feat`: (new feature for the user, not a new feature for build script)
- `fix`: (bug fix for the user, not a fix to a build script)
- `docs`: (changes to the documentation)
- `style`: (formatting, missing semi colons, etc; no production code change)
- `refactor`: (refactoring production code, eg. renaming a variable)
- `test`: (adding missing tests, refactoring tests; no production code change)
- `chore`: (updating grunt tasks etc; no production code change)

References:

- https://www.conventionalcommits.org/
- https://seesparkbox.com/foundry/semantic_commit_messages
- http://karma-runner.github.io/1.0/dev/git-commit-msg.html

# GitHub Actions

## Automatic Branch Deletion

This repository includes a GitHub Action that automatically deletes branches after they have been merged to the `main` branch. This helps keep the repository clean by removing obsolete branches.

### How it works

- **Trigger**: The action runs when a pull request is closed
- **Condition**: The branch is only deleted if the PR was merged (not just closed)
- **Protection**: Common protected branches (`main`, `master`, `develop`, `development`) are never deleted
- **Location**: `.github/workflows/delete-merged-branch.yml`

### Benefits

- Keeps the repository clean and organized
- Reduces clutter from old feature branches
- Automatically manages branch lifecycle
- No manual intervention required after merging PRs