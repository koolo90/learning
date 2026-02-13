#!/usr/bin/env bash
export LOG_LEVEL=1

echo "Excercise 1: Define an array"
inline_array=("inline" 1 2 3 4 5 6 7 8 9)
echo "Entire [inline_array]: [${inline_array[@]}]"
echo -en "\tElements: ["
for elem in "${inline_array[@]}"
do
  echo -n "$elem, "
done
echo -e "] \n"

echo "Excercise 2: Define an array, and expand it with reassignment"
expanded_array_by_appending=("base" 1 2 3 4)
expanded_array_by_appending+=("append" 6 7 8 9)
echo "Entire [expanded_array_by_appending]: ${expanded_array_by_appending[@]}"
echo -en "\tElements: ["
for elem in "${inline_array[@]}"
do
  echo -n "$elem, "
done
echo -e "] \n"

echo "Exercise 3: Define an array, and expand it with rewriting"
array_expanded_by_rewriting=("base" 1 2 3 4)
array_expanded_by_rewriting=(${array_expanded_by_rewriting[@]} "rewritten" 6 7 8 9)
arr_var_name=(${!array_expanded_by_rewriting@})
echo "Entire [${arr_var_name}]: ${array_expanded_by_rewriting[@]}"

echo -en "\tElements: ["
for elem in "${array_expanded_by_rewriting[@]}"
do
  echo -n "$elem, "
done
echo -e "] \n"


# Excercise 2: Multi-dimensional array using eval
# https://stackoverflow.com/questions/11233825/multi-dimensional-arrays-in-bash
# https://stackoverflow.com/questions/44828958/how-do-format-and-traverse-an-array-that-contains-arrays-and-each-array-contain/44831174#44831174
# Exercise
echo "Exercise 3: Define an 2-dimensional array, and expand it with rewriting"
arr_1=("Arr", "Of", "Strings")
arr_2=(1, 2, 3)

data1=("Santiago" "Barcelona" "AWG" "6792992" "Male")
data2=("Santi" "Texas" "BGG" "6792992" "Male")
data3=("Tiago" "Rio" "GHA" "6792992" "Female")

datacol=("data1" "data2" "data3")

for arrayName in "${datacol[@]}"; do
  declare -n array="$arrayName"
  echo "The second element of the array '$arrayName' is: ${array[1]}"
  for elem in "${array[@]}"; do
    echo "Elements are: ${elem}"
  done
done