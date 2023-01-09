if [ ! -d "PROJEKT-PIS" ]; then
  git clone git@github.com:Daniel141592/PROJEKT-PIS.git
  cd PROJEKT-PIS/magazyn-frontend
else
  cd PROJEKT-PIS/magazyn-frontend
  git pull
fi

npm --version
if [ ! $? -eq 0 ]; then
  sudo apt install -y npm
fi

nvm --version
if [ ! $? -eq 0 ]; then
  curl -o- https://raw.githubusercontent.com/nvm-sh/nvm/v0.39.2/install.sh | bash
  export NVM_DIR="$HOME/.nvm"
  [ -s "$NVM_DIR/nvm.sh" ] && \. "$NVM_DIR/nvm.sh"
  [ -s "$NVM_DIR/bash_completion" ] && \. "$NVM_DIR/bash_completion"
fi
nvm install 19.3.0

npm i
sudo killall node
sudo killall python3

npm run build
cd dist
sudo python3 -m http.server 80 &

