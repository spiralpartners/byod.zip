# PortableGitの設定情報
- このフォルダにはPortableGitを対象とした追加の設定ファイル，コマンドを設置する．

## 利用方法
- [Release](https://github.com/spiralpartners/byod.zip/releases) にあるbyod_v1.X.zipをC:\に展開すると，本フォルダの内容を適用済みのPortableGitフォルダが展開される．
- `/etc/bash.bashrc` ログイン時の処理を最後に追記した．指定したホームフォルダを作成し，そこに移動する．
- `/usr/local/bin`

## 課題
### vscodeでシェル(bash.exe)を開いたときに`/usr/local/bin`にPATHが通っていない
- 原因不明．bash.bashrcで明示的にPATHに追加すればいけるかもしれないが，2重でPATHを通すことになりそうで避けたい．
- echo $PATHをすると，`/usr/local/bin/`と`/bin`へのPATHがbash.exeを実行した場合は通っていない(git-bash.exeの場合は通っている）
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

## PortableGit設定方法詳細
### Step1. PortableGitのダウンロード及び解凍
- 下記URLより，PortableGit-2.XX.Y.Z-64-bit.7z.exe をダウンロードし，byodフォルダに解凍する
- https://github.com/git-for-windows/git/releases/latest

### Step2. PortableGitフォルダへの設定ファイルの追加
- 本リポジトリPortableGitフォルダの中身をStep1で解凍したPortableGitフォルダに上書きする

### StepX. 追加バイナリのインストール方法
- 本リポジトリ中に既に用意されているnkf.exeやrsync.exeはmsys2を利用して取得したもの．毎回やる必要はないが，セットアップ方法を以下に記しておく．
- msys2の64bit版をインストール
  - https://sourceforge.net/projects/msys2/files/Base/
- msys2 64bitを起動してrsyncをインストール（nkfも多分同じ）
  - `pacman -S rsync`
- `${msys2}/usr/bin/rsync.exe` を `${PortableGit}/usr/local/bin/`にコピーする
