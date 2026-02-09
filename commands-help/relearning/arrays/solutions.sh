#!/usr/bin/env bash
export LOG_LEVEL=1

echo "Exercise 1.1: Create an array"
inline_array=("first" 1 2 3 "nth" 5 6 7 8 "last")
echo ""

echo "Exercise 2.1: Print an array"
echo "Entire [inline_array]: [${inline_array[@]}]"
echo ""

echo "Exercise 2.2: Print length an array"
array_length=${#inline_array[@]}
echo "Length of [inline_array] as variable [array_length]: [${#inline_array[@]}]"
echo "Length of [inline_array]: [${array_length}]"
echo ""

echo "Exercise 2.3: Get the 1'st element of an array"
echo "1'st element of [inline_array]: [${inline_array[0]}]"
echo ""

echo "Exercise 2.4: Get the n-th element of an array"
echo "7th element of [inline_array]: [${#inline_array[7]}]"
echo ""

echo "Exercise 2.5: Calculate index of last element in the array"
last_elem_idx=$((array_length - 1))
echo "Last element index: [${last_elem_idx}]"
echo ""

echo "Exercise 2.6: Get the last element of an array"
last_element=${inline_array[$last_elem_idx]}
echo "Last element of [inline_array]: [${last_element}]"
echo ""

echo "Exercise 2.7: Get the out-of-bounds element of an array"
post_last_elem_idx=$((array_length))
last_element=${inline_array[$post_last_elem_idx]}
echo "Last element of [inline_array]: [${last_element}]"
echo ""


echo "=== --- Done so far --- ==="
exit 0
# ===---===---===
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