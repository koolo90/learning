#!/usr/bin/env bash

<<<<<<< Updated upstream
function exercise1 {
  echo "I am a function form exercise1"
}

exercise1
=======
# Solution for exercise #1
function exercise1 {
  echo "I am a function form exercise #1"
}
exercise1

# Solution for exercise #2
function exercise2 {
  echo "I am function from exercise #2, argument I was given was: [${1}]"
}
exercise2 "The argument"

# Solution for exercise #3
function exercise3 {\
  echo "I am function from exercise #3, argument I was given was: [${1}, ${2}]"
}
exercise3 "The" "argument"

#Solution for excercise #4
function exercise4 {\
  echo "Arguments count: $#"
  echo "All arguments: $@"
  echo "Second argument was: [${2}]"
}
exercise4 "The" "argument"
>>>>>>> Stashed changes
