<details>
<summary>Google Gemini Prompt</summary>
As a programing teacher create me an excercise of CRUEL (Create, READ, UPDATE, ERASE, LIST) 
of variables in bash. Consider different options of variables creation, include local variables 
for functions and allow expport to md file, include examples but not the solutions. Consider 
bash 5.2.37
</details>

As a programming teacher, I’ve designed this exercise to push your understanding of Bash
5.2.37 beyond simple scripts. In Bash, managing variables follows a "CRUEL" lifecycle:
Create, Read, Update, Erase, and List.

This exercise focuses on scoping (global vs. local), types (indexed vs. associative arrays), and persistence (exporting to the environment).

<hr>

# The Bash "CRUEL" Exercise
## Objective
Create a script named variable_manager.sh that manages a virtual "Session Store." You will perform CRUEL operations on three types of variables: Global Strings, Local Function Variables, and Associative Arrays. Finally, you will export the "Read" and "List" results to a Markdown file.
## Technical Requirements
- Version: Must be compatible with Bash 5.2.x.
- Local Scope: Use the local or declare keywords to ensure function variables do not "leak" into the global scope.
- Associative Arrays: Use declare -A to map keys to values.
- Redirection: Use > or >> to generate the .md report.

<hr>

# Task 1: The Global "Create" and "Read"
1. __Create__ a global variable named APP_STATUS and initialize it to "Active".
2. __Export__ this variable so it is visible to any child processes or subshells.
3. __Read__ the value and print it to the console with a prefix: [GLOBAL]: Status is....
# Task 2: The Functional "Update" (Local Scope)
1. __Create__ a function `update_session()`.
2. __Inside__ the function, Create a `local` variable named `SESSION_ID` with a random number.
3. __Update__ the global `APP_STATUS` from within this function to "Maintenance".
4. __Demonstrate Scoping:__ After calling the function, try to `echo $SESSION_ID` in the main script. (Expected: It should be empty/erased).
# Task 3: Associative Array "CRUEL"
1. __Create:__ Initialize an associative array user_data containing at least: [name]="Student", [role]="Admin". 
2. __Update:__ Add a new key [last_login] with the current timestamp. 
3. __Erase:__ Remove the [role] key from the array. 
4. __List:__ Use a loop to print every key and value in the array.
# Task 4: Markdown Export
1. __Create__ a function generate_report(). 
2. __Inside__ this function, List all current environment variables (use env or export -p). 
3. __Export__ the results to a file named report.md. The file must be formatted in Markdown, using headers (#, ##) and code blocks (bash ... ).
<hr>

# Examples (For Inspiration)
## Defining a Local Variable in Bash 5.2:

```bash
# Example of local scope within a function
my_function() {
local secret_code="1234"
echo "Inside: $secret_code"
# Calling the function works, but secret_code won't exist here!
}
```
_Use code with caution._

## Working with Associative Arrays:
```bash
# Explicit declaration is required for associative arrays
declare -A colors
colors[primary]="blue"
colors[secondary]="green"

# To erase a specific key:
unset colors[secondary]
```

_Use code with caution._

## Writing to Markdown:
```bash
# Appending a header and a list to a file
echo "## Current Variables" > output.md
echo "* Status: $APP_STATUS" >> output.md
```
_Use code with caution._
<hr>

# Final Challenge
Modify your script so that it takes a command-line argument. If the user runs 
`./variable_manager.sh --clean`, the script should Erase (unset) all variables created during the 
session before exiting. 

__Helpful Resource:__ GNU Bash Reference Manual - Arrays | Bash Local Variables Guide