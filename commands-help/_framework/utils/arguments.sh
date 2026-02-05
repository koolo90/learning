function print_arguments {
  local args=("$@")

  echo "=== Splitting arguments: ==="
  for i in "${!args[@]}"; do
    human_readable_index=$((i+1))
    echo "$human_readable_index: ${args[$i]}"
  done
  echo "=== ===================== ==="
}