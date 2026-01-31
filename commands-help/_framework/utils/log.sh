# ====] LOG LEVELS: [====
source "$(dirname "${BASH_SOURCE[0]}")/../output/colors.sh"
declare -A LOG_LEVELS
LOG_LEVELS[TRACE]="t trace 0 IPurple"
LOG_LEVELS[DEBUG]="d debug 1 IBlue"
LOG_LEVELS[INFO]="i info 2 Color_Off"
LOG_LEVELS[WARN]="w warn 3 IYellow"
LOG_LEVELS[ERROR]="e error 4 IRed"
LOG_LEVELS[FATAL]="f fatal 5 On_IRed"
# =======================
# Default LOG_LEVEL to FATAL if not set
if [[ -z "$LOG_LEVEL" ]]; then
  LOG_LEVEL="FATAL"
fi

function log() {
  local input_level=$(echo $1 | tr '[:lower:]' '[:upper:]')
  local level_data=(${LOG_LEVELS[$input_level]})

  # If not found by full name, try searching by initial
  if [[ -z "${level_data[*]}" ]]; then
    for key in "${!LOG_LEVELS[@]}"; do
      local data=(${LOG_LEVELS[$key]})
      if [[ "${data[0],,}" == "${1,,}" ]]; then
        level_data=(${LOG_LEVELS[$key]})
        break
      fi
    done
  fi

  local timestamp=$(date +"%Y-%m-%d %H:%M:%S")
  if [[ -z "${level_data[*]}" ]]; then
    echo -e "${IRed}[MISUSE] Log level '$1' is not supported. ${Color_Off}"
    echo -ne "${IYellow}[HINT] Possible options: "
    local options=("${!LOG_LEVELS[@]}")
    echo -e "${options[*],,}${Color_Off}"
    level_data=(${LOG_LEVELS[FATAL]})
  fi

  # Determine current threshold numeric value
  local threshold_numeric=5
  if [[ "$LOG_LEVEL" =~ ^[0-5]$ ]]; then
    threshold_numeric=$LOG_LEVEL
  else
    local log_level_upper=$(echo $LOG_LEVEL | tr '[:lower:]' '[:upper:]')
    local threshold_data=(${LOG_LEVELS[$log_level_upper]})
    if [[ -n "${threshold_data[*]}" ]]; then
      threshold_numeric=${threshold_data[2]}
    else
      # Try searching by initial for LOG_LEVEL too
      for key in "${!LOG_LEVELS[@]}"; do
        local data=(${LOG_LEVELS[$key]})
        if [[ "${data[0],,}" == "${LOG_LEVEL,,}" ]]; then
          threshold_numeric=${data[2]}
          break
        fi
      done
    fi
  fi

  local current_level_numeric=${level_data[2]}
  if [[ $current_level_numeric -lt $threshold_numeric ]]; then
    return 0
  fi

  local display_name=${level_data[1]}
  local color_var=${level_data[3]}
  local color_code=${!color_var}
  echo -e "[${timestamp}]${color_code}[${display_name^^}] ${@:2} ${Color_Off}"
}
