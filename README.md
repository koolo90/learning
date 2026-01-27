# Introduction

Re-learning actually - focus on technologies i do know already but require recap

# Contribution guidelines  
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

# Contribution guidelines  
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
