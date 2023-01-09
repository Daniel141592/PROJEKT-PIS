if [ ! -d "PROJEKT-PIS" ]; then
    git clone git@github.com:Daniel141592/PROJEKT-PIS.git
    cd PROJEKT-PIS/magazyn-frontend
else
    cd PROJEKT-PIS/magazyn-frontend
    git pull
fi

sudo apt install -y npm

curl -o- https://raw.githubusercontent.com/nvm-sh/nvm/v0.39.2/install.sh | bash
export NVM_DIR="$HOME/.nvm"
[ -s "$NVM_DIR/nvm.sh" ] && \. "$NVM_DIR/nvm.sh"  # This loads nvm

[ -s "$NVM_DIR/bash_completion" ] && \. "$NVM_DIR/bash_completion"  # This loads nvm bash_completion
nvm install 19.3.0

npm i
npm run build
cd dist

