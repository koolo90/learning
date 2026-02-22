grep -r "alias" /etc/profile.d/ ~/.bashrc ~/.bash_profile ~/.gitconfig 2>/dev/null

echo '[[ -f ~/.bashrc ]] && . ~/.bashrc' > ~/.bash_profile


if [[ -f ~/.bashrc ]] ; then
  echo "Bash rc preset"
else
  echo "Bash RC is not present, creating..."
  touch ~/.bashrc
  echo "Checking existence with 'ls'"
  ls ~/.bashrc
  echo "Printing initial contents to '~/.bashrc' file..."
  echo '# This file contains the 'factory' defaults for Git Bash' > ~/.bashrc
  echo 'cat /etc/profile.d/aliases.sh' >> ~/.bashrc
  echo "Printing the contents..."
  cat ~/.bashrc
  echo "Sourcing the file (reloading current session):"
  source ~/.bashrc
fi
