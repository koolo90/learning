# ====] LOG LEVELS: [====
source "$(dirname "${BASH_SOURCE[0]}")/../output/colors.sh"
declare -A LOG_LEVELS
LOG_LEVELS[TRACE]="t trace 0 IPurple"
LOG_LEVELS[DEBUG]="d debug 1 IBlue"
LOG_LEVELS[INFO]="i info 2"
LOG_LEVELS[WARN]="w warn 3 IYellow"
LOG_LEVELS[ERROR]="e error 4 IRed"
LOG_LEVELS[FATAL]="f fatal 5 On_IRed"
# =======================

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
  
  if [[ -z "${level_data[*]}" ]]; then
    echo -e "${IRed}[MISUSE] Log level '$1' is not supported. ${Color_Off}"
    echo -ne "${IYellow}[HINT] Possible options: "
    local options=("${!LOG_LEVELS[@]}")
    echo "${options[*],,}${Color_Off}"
    level_data=(${LOG_LEVELS[FATAL]})
  fi

  local display_name=${level_data[1]}
  local color_var=${level_data[3]}
  local color_code=${!color_var}
  local timestamp=$(date +"%Y-%m-%d %H:%M:%S")
  echo -e "${timestamp} ${color_code}[${display_name^^}]${Color_Off} ${@:2}"
}
