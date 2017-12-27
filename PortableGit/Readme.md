PortableGitのダウンロード及び解凍

msys2の64bit版をインストール
https://sourceforge.net/projects/msys2/files/Base/

msys2 64bitを起動してrsyncをインストール
pacman -S rsync

/usr/bin/rsync.exe を /usr/local/bin/にコピー

## 課題
### ファイルとディレクトリを間違えて作成した場合に，正しくリモートに課題が提出されない可能性がある
- `ex01.c`とかいうディレクトリを間違って作成し，それが一度pushされると，以降はそれが変更されない（--deleteをつけない場合）
### shussekiコマンドが失敗していても学生が気づかない場合が考えられる
- 1回目の同期が終了した後に確認コマンドを入力させる？

### 複数の授業で公開鍵方式でsshする場合に，リモート側に1つのホストからの複数の鍵が残らないようになっている
- `/usr/local/bin/initssh`参照
- 授業で使う公開鍵を使い分けるような状況になると，毎回initsshやり直す必要がある
- とりあえずは公開鍵を授業間で共有する（運用でカバー）

### ~~$USERPROFILE/byod をホームにして，.sshフォルダもそこに置きたいが，認識されない~~(解決済み)
- bash.bashrcでHOMEを新たにセットし直しても，sshの公開鍵がデフォルトで読み込まれる場所は$USERPROFILEのまま
- fstabの設定をしたりしても，なぜか変更できず
- ssh-keygenのときに-fオプションで作成場所を指定し，rsyncやsshのときには-iオプションで指定したファイルを読み込むように変更した．

### ~~`~/`を指定したときに展開される場所がときによって違う~~(解決済み)
- 通常はHOMEとしてセットしたところが展開されるが，ssh -i ~/.ssh/id_rsa としたときに，$USERPROFILEが展開されることがある
- $HOME/.ssh/id_rsa と指定することで展開される場所が共通化できた

### ~~フォルダにスペースが混じっているとバグる(コマンドが正常に実行されなくなる)~~(解決済み)
- "$HOME"等で囲む必要あり．
- 下記のsshコマンド部分はどうしようもないので，その下にあるように変更した
  - `rsync -av -e "ssh -i $HOME/.ssh/id_rsa " ${id}@o-vnc.center.oit.ac.jp:kadai "$HOME"`
  - `rsync -av -e "ssh -i ./.ssh/id_rsa " ${id}@o-vnc.center.oit.ac.jp:kadai "$HOME"`
    - rsyncの前にcdを実行しておくこと．

### ~~ホームディレクトリが`C:\Users\hoge`形式の場合，rsyncコマンドが失敗する(The source and destination cannot both be remote)~~(解決済み)
- `C:\`の`:`のせいでremoteだと誤認するっぽい．
- HOMEを`export HOME=$(cd "$USERPROFILE\byod_home" && pwd)`として設定することで，`/c/Users/..`形式でホームディレクトリを処理できるようになる
