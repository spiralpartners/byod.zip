
msys2の64bit版をインストール
https://sourceforge.net/projects/msys2/files/Base/

msys2 64bitを起動してrsyncをインストール
pacman -S rsync

/usr/bin/rsync.exe を /usr/local/bin/にコピー

* configの作成
if [ ! -d ~/.ssh ]; then
    mkdir -p ~/.ssh
fi
echo "StrictHostKeyChecking no" > ~/.ssh/config

* keyfileの作成(.sshフォルダがないとミスる）
ssh-keygen -t rsa -f ~/.ssh/id_rsa

cat ~/.ssh/id_rsa.pub  | ssh t2015025@o-vnc.center.oit.ac.jp 'mkdir -p ~/.ssh;chmod 700 ~/.ssh;cat >> .ssh/authorized_keys;chmod 600 .ssh/authorized_keys'

#rsyncでssh呼び出すときの-iでは~/.ssh だと%USERPROFILE%/.ssh を見に行くっぽい．
rsync -a -e "ssh -i ~/byod_home/.ssh/id_rsa " t2015025@o-vnc.center.oit.ac.jp:kadai .

#以下のコマンドでもいけるが
rsync -a -e "ssh -i ~/byod_home/.ssh/id_rsa " t2015025@o-vnc.center.oit.ac.jp:kadai /c/Users/Hiroshi/byod_home

#以下だと両方共remoteだよとかいうエラーが出る．~/がC:\になってるとremoteだとご判定するっぽい(":"を誤解してるっぽい）
rsync -a -e "ssh -i ~/byod_home/.ssh/id_rsa " t2015025@o-vnc.center.oit.ac.jp:kadai ~/

#HOMEを以下のように設定すると，C:\..にならないのでいける
export HOME=$(cd "$USERPROFILE\byod_home" && pwd)