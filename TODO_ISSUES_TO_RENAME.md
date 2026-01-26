# Bulk Rename TODO Issues

This document lists all GitHub issues in the koolo90/learning repository that start with `*TODO*` and should have that prefix removed.

## Summary

**Total issues to rename: 17**

All issues are currently open and have the `enhancement` label. The task is to remove the `*TODO*: ` prefix from each issue title.

---

## Issues to Rename

### Issue #83
- **Current Title:** `*TODO*: `container-workdir`: Change working directory.`
- **New Title:** `` `container-workdir`: Change working directory.``
- **URL:** https://github.com/koolo90/learning/issues/83

### Issue #82
- **Current Title:** `*TODO*: `container-volume`: Create volume mounts.`
- **New Title:** `` `container-volume`: Create volume mounts.``
- **URL:** https://github.com/koolo90/learning/issues/82

### Issue #81
- **Current Title:** `*TODO*: `container-user`: Set user and group ID.`
- **New Title:** `` `container-user`: Set user and group ID.``
- **URL:** https://github.com/koolo90/learning/issues/81

### Issue #80
- **Current Title:** `*TODO*: `container-stopsignal`: Specify the system call signal for exiting a container.`
- **New Title:** `` `container-stopsignal`: Specify the system call signal for exiting a container.``
- **URL:** https://github.com/koolo90/learning/issues/80

### Issue #79
- **Current Title:** `*TODO*: `container-shell`: Set the default shell of an image.`
- **New Title:** `` `container-shell`: Set the default shell of an image.``
- **URL:** https://github.com/koolo90/learning/issues/79

### Issue #78
- **Current Title:** `*TODO*: `container-run`: Execute build commands.`
- **New Title:** `` `container-run`: Execute build commands.``
- **URL:** https://github.com/koolo90/learning/issues/78

### Issue #77
- **Current Title:** `*TODO*: `container-onbuild`: Specify instructions for when the image is used in a build.`
- **New Title:** `` `container-onbuild`: Specify instructions for when the image is used in a build.``
- **URL:** https://github.com/koolo90/learning/issues/77

### Issue #76
- **Current Title:** `*TODO*: `container-maintainer`: Specify the author of an image.`
- **New Title:** `` `container-maintainer`: Specify the author of an image.``
- **URL:** https://github.com/koolo90/learning/issues/76

### Issue #75
- **Current Title:** `*TODO*: `container-label`: Add metadata to an image.`
- **New Title:** `` `container-label`: Add metadata to an image.``
- **URL:** https://github.com/koolo90/learning/issues/75

### Issue #74
- **Current Title:** `*TODO*: `container-healthchek`: Check a container's health on startup.`
- **New Title:** `` `container-healthchek`: Check a container's health on startup.``
- **URL:** https://github.com/koolo90/learning/issues/74

### Issue #73
- **Current Title:** `*TODO*: `container-from`: Create a new build stage from a base image.`
- **New Title:** `` `container-from`: Create a new build stage from a base image.``
- **URL:** https://github.com/koolo90/learning/issues/73

### Issue #72
- **Current Title:** `*TODO*: `container-expose`: Describe which ports your application is listening on.`
- **New Title:** `` `container-expose`: Describe which ports your application is listening on.``
- **URL:** https://github.com/koolo90/learning/issues/72

### Issue #71
- **Current Title:** `*TODO*: `container-env`: Set environment variables.`
- **New Title:** `` `container-env`: Set environment variables.``
- **URL:** https://github.com/koolo90/learning/issues/71

### Issue #70
- **Current Title:** `*TODO*: `container-entrypoint`: Specify default executable.`
- **New Title:** `` `container-entrypoint`: Specify default executable.``
- **URL:** https://github.com/koolo90/learning/issues/70

### Issue #69
- **Current Title:** `*TODO*: `container-copy`: Copy files and directories.`
- **New Title:** `` `container-copy`: Copy files and directories.``
- **URL:** https://github.com/koolo90/learning/issues/69

### Issue #68
- **Current Title:** `*TODO*: `container-cmd`: Specify default commands.`
- **New Title:** `` `container-cmd`: Specify default commands.``
- **URL:** https://github.com/koolo90/learning/issues/68

### Issue #67
- **Current Title:** `*TODO*: `container-arg`: Use build-time variables.`
- **New Title:** `` `container-arg`: Use build-time variables.``
- **URL:** https://github.com/koolo90/learning/issues/67

---

## How to Rename These Issues

### Option 1: Using GitHub Web Interface
1. Click on each issue URL above
2. Click "Edit" next to the issue title
3. Remove the `*TODO*: ` prefix
4. Save the changes

### Option 2: Using GitHub CLI (gh)
If you have the GitHub CLI installed and authenticated, you can run:

```bash
gh issue edit 83 --title "\`container-workdir\`: Change working directory." --repo koolo90/learning
gh issue edit 82 --title "\`container-volume\`: Create volume mounts." --repo koolo90/learning
gh issue edit 81 --title "\`container-user\`: Set user and group ID." --repo koolo90/learning
gh issue edit 80 --title "\`container-stopsignal\`: Specify the system call signal for exiting a container." --repo koolo90/learning
gh issue edit 79 --title "\`container-shell\`: Set the default shell of an image." --repo koolo90/learning
gh issue edit 78 --title "\`container-run\`: Execute build commands." --repo koolo90/learning
gh issue edit 77 --title "\`container-onbuild\`: Specify instructions for when the image is used in a build." --repo koolo90/learning
gh issue edit 76 --title "\`container-maintainer\`: Specify the author of an image." --repo koolo90/learning
gh issue edit 75 --title "\`container-label\`: Add metadata to an image." --repo koolo90/learning
gh issue edit 74 --title "\`container-healthchek\`: Check a container's health on startup." --repo koolo90/learning
gh issue edit 73 --title "\`container-from\`: Create a new build stage from a base image." --repo koolo90/learning
gh issue edit 72 --title "\`container-expose\`: Describe which ports your application is listening on." --repo koolo90/learning
gh issue edit 71 --title "\`container-env\`: Set environment variables." --repo koolo90/learning
gh issue edit 70 --title "\`container-entrypoint\`: Specify default executable." --repo koolo90/learning
gh issue edit 69 --title "\`container-copy\`: Copy files and directories." --repo koolo90/learning
gh issue edit 68 --title "\`container-cmd\`: Specify default commands." --repo koolo90/learning
gh issue edit 67 --title "\`container-arg\`: Use build-time variables." --repo koolo90/learning
```

### Option 3: Using the Python Script
The Python script `/tmp/rename_todo_issues.py` contains the logic to rename all issues at once. Run it with proper GitHub authentication.

---

## Notes

- All 17 issues are part of the Docker container instruction documentation
- Issue #66 has `*WIPR*` prefix (Work In Progress) and was excluded from this renaming
- All issues have the `enhancement` label and are currently open
- The renaming is purely cosmetic and does not affect issue content or labels

---

**Generated on:** 2026-01-26
