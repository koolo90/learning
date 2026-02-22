#!/usr/bin/env bash

# Solution for exercise #1
function exercise1 {
  echo
  #echo "script: $0, f(): $FUNCNAME($@)"
}
exercise1 || echo $?

# Solution for exercise #2
function exercise2 {
  echo $1
}
exercise2 "The argument" || echo $?
exercise2 The argument || echo $?

# Solution for exercise #3
function exercise3 {
  echo "script: $0, f(): $FUNCNAME($#: [$@])"
}
exercise3 The argument

#Solution for exercise #4
function exercise4 {
  echo "script: $0, f(): $FUNCNAME($#: [$@])"
  return 0
}
exercise4 && echo $?

#Solution for exercise #5
function exercise5 {
  echo "script: $0, f(): $FUNCNAME($@)"
  return 50
}
exercise5 || echo $?

#Solution for exercise #4
function exercise6 {
  echo "script: $0, f(): $FUNCNAME($@)"
  return $#
}
exercise6 a b c d || echo $?