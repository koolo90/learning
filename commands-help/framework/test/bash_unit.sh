#!/bin/bash

source ../output/color.sh

function assert() {
  echo $@
  local args=("$@")

  echo "=== Splitting arguments: ==="
  for i in "${!args[@]}"; do
    human_readable_index=$((i+1))
    echo "$human_readable_index: ${args[$i]}"
  done
  echo "=== ===================== ==="

  local type=${args[0]}
  local value=${args[1]}
  local comparator=${args[2]}
  local expected=${args[3]}

  echo "Checking if actual=[$value] is by comparing=[$comparator] to expected=[$expected] considering it is of type=[$type] is fulfilled..."

  local result=1
  case $comparator in
      equals)
        if [[ "$value" == "$expected" ]] ; then
          echo "${BIGreen} OK ${Color_Off}"
        else
          echo "${BIRed} NOK ${Color_Off}"
        fi
        ;;
      greater)
        echo "You have selected greater-then comparison" ;;
      less)
        echo "You have selected lower-then comparison" ;;
      *) echo "Valid Choices are A,B,C" exit 1 ;;
  esac
}