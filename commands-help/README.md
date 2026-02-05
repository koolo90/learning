# dump-help.sh - Command Help Dumper

## Overview

The `dump-help.sh` script is designed to automatically extract and store help documentation for shell commands and their sub-commands. It supports commands with sub-functions (like `git branch`, `docker ps`, etc.) and can process multiple commands at once.

## Features

- **Single Command Support**: Dump help for a single command
- **Sub-command Support**: Automatically handles commands with sub-commands (e.g., `git branch`, `docker ps`)
- **Multiple Input Methods**:
  - Single quoted command
  - Comma-separated list of commands
  - File with commands (one per line)
- **Automatic Directory Structure**: Creates organized directory structure for commands and sub-commands
- **Git Integration**: Automatically creates feature branches and commits changes
- **Help Flag Detection**: Tries both `-h` and `--help` flags automatically

## Usage

### 1. Single Command

```bash
./dump-help.sh "ls"
```

This will:
- Create a branch: `feature/ls-help-dump`
- Create directory: `commands-help/ls/`
- Generate file: `commands-help/ls/help.txt`
- Commit changes with message: `feat(help): dumped help for ls`

### 2. Command with Sub-command

```bash
./dump-help.sh "git branch"
```

This will:
- Create a branch: `feature/git-branch-help-dump`
- Create directory: `commands-help/git/branch/`
- Generate file: `commands-help/git/branch/help.txt`
- Commit changes with message: `feat(help): dumped help for git branch`

### 3. Comma-separated Commands

```bash
./dump-help.sh "git branch, docker ps, ls -la"
```

This will process each command separately, creating appropriate branches, directories, and commits for each.

### 4. File with Commands

Create a file `commands.txt`:
```
# Comments start with #
git branch
git status
docker ps
ls -la
```

Then run:
```bash
./dump-help.sh commands.txt
```

This will process each command in the file (ignoring comments and empty lines).

## Directory Structure

The script creates the following directory structure:

```
commands-help/
├── ls/
│   └── help.txt
├── git/
│   ├── branch/
│   │   └── help.txt
│   ├── status/
│   │   └── help.txt
│   └── log/
│       └── help.txt
└── docker/
    ├── ps/
    │   └── help.txt
    └── version/
        └── help.txt
```

## How It Works

1. **Input Parsing**: The script determines if the input is a single command, comma-separated list, or file
2. **Command Splitting**: Each command is split into main command and sub-command parts
   - `ls` → command: `ls`, sub-command: (none)
   - `git branch` → command: `git`, sub-command: `branch`
3. **Branch Creation**: Creates a feature branch named `feature/<command>[-subcommand]-help-dump`
4. **Directory Creation**: Creates directory structure based on command/sub-command
5. **Help Extraction**: Runs the command with `-h` or `--help` flag to capture help text
6. **Git Commit**: Stages and commits the changes
7. **Summary**: Provides instructions for pushing the branch

## Testing

The script includes comprehensive tests located in `_tests/dump-help-tests.sh`. Run tests with:

```bash
cd commands-help
bash _tests/dump-help-tests.sh
```

Tests cover:
- Command parsing
- Sub-command splitting
- Single command input
- Comma-separated input
- File input
- Help flag handling
- Edge cases

## Continuous Integration

Tests are automatically run via GitHub Actions on:
- Push to `main` or `feature/**` branches
- Pull requests to `main`

See `.github/workflows/dump-help-tests.yml` for CI configuration.

## Requirements

- Bash 4.0 or later
- Git
- Access to the commands you want to document

## Environment Variables

- `LOG_LEVEL`: Control logging verbosity (TRACE, DEBUG, INFO, WARN, ERROR, FATAL)
  ```bash
  LOG_LEVEL=INFO ./dump-help.sh "git branch"
  ```

## Examples

### Example 1: Document Git Commands
```bash
# Create a file with git commands
cat > git-commands.txt << EOF
git branch
git status
git log
git diff
EOF

# Process all commands
./dump-help.sh git-commands.txt
```

### Example 2: Document Docker Commands
```bash
./dump-help.sh "docker ps, docker images, docker network ls"
```

### Example 3: Single Command
```bash
LOG_LEVEL=INFO ./dump-help.sh "kubectl get pods"
```

## Troubleshooting

### Command not found
If you get "command not found" errors, ensure:
- The command is installed and available in your PATH
- You have permission to execute the command

### No changes to commit
This means the help file already exists and hasn't changed. This is normal when re-running the script for the same command.

### Branch already exists
The script will checkout the existing branch and update the help file if needed.

## Contributing

When adding new features:
1. Add tests to `_tests/dump-help-tests.sh`
2. Ensure all tests pass
3. Update this documentation
4. Verify CI passes

## License

See repository LICENSE file.
