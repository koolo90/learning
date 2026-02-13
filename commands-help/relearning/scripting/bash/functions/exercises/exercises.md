# Exercises about function

1. Create a function that takes no arguments and use it, check if execution was successful
<details>
<summary>Solution</summary>

```bash
function solution {
  echo
}
solution || echo $?
```
</details>

2. Create a function that takes one argument and use it
<details>
<summary>Solution</summary>

```bash
function solution {
  echo $1
}
solution "The argument" || echo $?
solution The argument || echo $?
```
</details>

3. Create a function that takes any amount of arguments and use it
4. Create a function that returns a successful value and use it 
5. Create a function that returns an unsuccessful value and use it
6. Create a function that returns the number of argument that were passed value and use it
7. 
8. Create a function that prints scripts name
8. Create a function that prints its name
9. Create a function that prints its arguments
10. Create a function that prints the number of arguments passed
