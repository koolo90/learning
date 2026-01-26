# Bulk TODO Issue Rename - Quick Reference

## Summary
This PR identifies and documents **17 GitHub issues** that need their `*TODO*` prefix removed from their titles.

## Files Added
1. **TODO_ISSUES_TO_RENAME.md** - Detailed documentation with:
   - Complete list of all 17 issues
   - Current and new titles
   - Direct URLs to each issue
   - Multiple renaming options

2. **rename_todo_issues.sh** - Executable shell script that:
   - Automatically renames all 17 issues
   - Provides progress feedback
   - Requires GitHub CLI (gh) authentication

## Quick Start

### Option 1: Use the Automated Script
```bash
# Make sure you have GitHub CLI installed and authenticated
gh auth login

# Run the script
./rename_todo_issues.sh
```

### Option 2: Manual Web Interface
See `TODO_ISSUES_TO_RENAME.md` for clickable links to each issue.

### Option 3: Individual gh Commands
The markdown file contains copy-paste ready commands for each issue.

## Issues Affected
All issues are:
- **Status:** Open
- **Label:** enhancement
- **Pattern:** Title starts with `*TODO*: `
- **Range:** Issues #67 through #83

## Change Preview
- **Before:** `*TODO*: `container-workdir`: Change working directory.`
- **After:** `` `container-workdir`: Change working directory.``

Simply removes the `*TODO*: ` prefix while preserving the rest of the title.

## Why These Changes?
These issues represent Docker container instruction documentation tasks. Removing the TODO prefix indicates they are tracked issues ready for work, not temporary placeholders.

## Testing
Run the script in dry-run mode first (if needed) or manually verify the first few issues before proceeding with the bulk update.
