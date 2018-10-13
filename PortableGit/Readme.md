# PortableGitの設定情報
- このフォルダにはPortableGitを対象とした追加の設定ファイル，コマンドを設置する．

## 利用方法
- [Release](https://github.com/spiralpartners/byod.zip/releases) exeファイルをC:\oit以下で展開すると，本フォルダの内容を適用済みのPortableGitフォルダが展開される．
- `C:\oit\java2018.vbs`を実行すると，PortableGitフォルダ内のjava-bash.batが呼ばれ，`mintty`経由で`C:\oit\PortableGit-2.17.0-64\usr\bin\bash.exe`が呼び出される．
  - 正確な実行内容は`java-bash.bat`参照．

## 構成
- `profile.d/aliases.sh` 
  - aliasを設定している．javaコマンド及びjavacコマンドのutf-8呼び出し．
    - 参考：http://blog.msz3nhen.net/?p=39
- `profile.d/bash_profile.sh` 
  - bashにログイン時の処理↓を記述している．
    - 参考：https://qiita.com/u1and0/items/b4c3217868cf8bafb085
    - ログイン時に`$HOME/javaexc/`ディレクトリがない場合にmkdir
      - 参考：http://blog.katty.in/4967
    - PATHにjavaのbinを追加
    - HOMEを`$USERPROFILE\oithomes\java18`に設定し，同時にパスの表示をLinuxの形式にあわせている
    - コマンド実行履歴を`$HOME/java18/.java_bash_history.YYYYmm`に即時保存する設定追加
      - 参考：http://takuya-1st.hatenablog.jp/entry/2017/01/01/034040
- minttyrc
  - bashをmintty経由で起動したときにフォントやUTF8設定，右クリックアクション等の設定を行っている．
- nsswitch.conf
  - 暗黙的な？ホームディレクトリをここで指定する-> `db_home: /%H/oithome`
  - これを指定していないと，sshkeygen等のopenssh系コマンド（多分他にもある）がホームディレクトリとして`$USERPROFILE`を見に行ってしまう．なお，$HOME環境変数にはこの設定は影響しない．
    - 参考：http://yanor.net/wiki/?Windows-%E3%82%A2%E3%83%97%E3%83%AA%E3%82%B1%E3%83%BC%E3%82%B7%E3%83%A7%E3%83%B3%2FMinGW-MSYS%2FMSYS2%E3%81%AEOpenSSH%E3%81%A7%E3%81%AE%E3%83%9B%E3%83%BC%E3%83%A0%E3%83%87%E3%82%A3%E3%83%AC%E3%82%AF%E3%83%88%E3%83%AA%E3%81%AE%E6%89%B1%E3%81%84

  - `/usr/local/bin` nkf.exe, rsync.exe, putkadai, shusseki等必要なexeや課題提出用スクリプト

## PortableGit設定方法詳細
### Step1. PortableGitのダウンロード及び解凍
- 下記URLより，PortableGit-2.XX.Y.Z-64-bit.7z.exe をダウンロードし，oitフォルダに解凍する
  - ディレクトリ名を`PortableGit2.XX.YY.Z-64`のようにバージョン番号を付与したものにしておくこと
  - https://github.com/git-for-windows/git/releases/latest

### Step2. PortableGitフォルダへの設定ファイルの追加
- 本リポジトリPortableGitフォルダの中身をStep1で解凍したPortableGitフォルダに上書きする
  - c1のmizutaniスクリプト群の中から必要なものをコピーする
    - getkadai, putkadaiをjava2018用に変更（rsync元，先ディレクトリをどうするかは要検討）
    - courseの中身をjava2018用に変更
    - getlocalのアクセス先を変更
    - oitnameに.java_bash_historyファイルの削除処理を追加

```
this_month=`date +'%Y%m'`
last_month=`date -d "${this_month}01 1 month ago" +'%Y%m'`
find $HOME/javaexc/ -name ".java_bash_history*" -not -name ".java_bash_history.${this_month}" -not -name ".java_bash_history.${last_month}" -exec rm {} \;
```

- java2018.vbsはc:\oitの中に，java-bash.batはPortableGitフォルダの中にコピーすること

### StepX. 追加バイナリのインストール方法
- 本リポジトリ中に既に用意されているnkf.exeやrsync.exeはmsys2を利用して取得したもの．毎回やる必要はないが，セットアップ方法を以下に記しておく．
- msys2の64bit版をインストール
  - https://sourceforge.net/projects/msys2/files/Base/
- msys2 64bitを起動してrsyncをインストール（nkfはソースからmakeする必要あり）
  - `pacman -S rsync`
- `${msys2}/usr/bin/rsync.exe` を `${PortableGit}/usr/local/bin/`にコピーする


## 課題
### ファイルとディレクトリを間違えて作成した場合に，正しくリモートに課題が提出されない可能性がある
- `ex01.c`とかいうディレクトリを間違って作成し，それが一度pushされると，以降はそれが変更されない（--deleteをつけない場合）
### shussekiコマンドが失敗していても学生が気づかない場合が考えられる
- 1回目の同期が終了した後に確認コマンドを入力させる？

### 複数の授業で公開鍵方式でsshする場合に，リモート側に1つのホストからの複数の鍵しか残らないようになっている
- `/usr/local/bin/initssh`参照
- 授業で使う公開鍵を使い分けるような状況になると，毎回initsshやり直す必要がある
- とりあえずは公開鍵を授業間で共有する方向で検討する（運用でカバー）

### ~~vscodeでシェル(bash.exe)を開いたときに`/usr/local/bin`にPATHが通っていない~~(解決済み)
- /usr/bin/bash.exe から起動するように設定し，環境変数MSYSTEMにMINGW64を指定すればPATHが通るようになることが確認できた
- 参考：https://qiita.com/yumetodo/items/42132a1e8435504448aa
- 原因不明．bash.bashrcで明示的にPATHに追加すればいけるかもしれないが，2重でPATHを通すことになりそうで避けたい．
- echo $PATHをすると，`/usr/local/bin/`と`/bin`へのPATHがbash.exeを実行した場合は通っていない(git-bash.exeを実行した場合は通っている）
### ~~$USERPROFILE/byod をホームにして，.sshフォルダもそこに置きたいが，認識されない~~(解決済み)
- bash.bashrcでHOMEを新たにセットし直しても，sshの公開鍵がデフォルトで読み込まれる場所は$USERPROFILEのまま
- fstabの設定をしたりしても，なぜか変更できず
- （解決策1:）nsswitch.conf でdb_homeを$HOMEと同じ場所に以下を参考に設定しておくと，sshも正しい場所を見てくれる
  - http://yanor.net/wiki/?Windows-%E3%82%A2%E3%83%97%E3%83%AA%E3%82%B1%E3%83%BC%E3%82%B7%E3%83%A7%E3%83%B3%2FMinGW-MSYS%2FMSYS2%E3%81%AEOpenSSH%E3%81%A7%E3%81%AE%E3%83%9B%E3%83%BC%E3%83%A0%E3%83%87%E3%82%A3%E3%83%AC%E3%82%AF%E3%83%88%E3%83%AA%E3%81%AE%E6%89%B1%E3%81%84
-（解決策2:）ssh-keygenのときに-fオプションで作成場所を指定し，rsyncやsshのときには-iオプションで指定したファイルを読み込むように変更した．

### ~~`~/`を指定したときに展開される場所がときによって違う~~(解決済み)
- 通常はHOMEとしてセットしたところが展開されるが，ssh -i ~/.ssh/id_rsa としたときに，$USERPROFILEが展開されることがある
- （解決策1:）nsswitch.conf でdb_homeを$HOMEと同じ場所に以下を参考に設定しておくと，sshも正しい場所を見てくれる
  - http://yanor.net/wiki/?Windows-%E3%82%A2%E3%83%97%E3%83%AA%E3%82%B1%E3%83%BC%E3%82%B7%E3%83%A7%E3%83%B3%2FMinGW-MSYS%2FMSYS2%E3%81%AEOpenSSH%E3%81%A7%E3%81%AE%E3%83%9B%E3%83%BC%E3%83%A0%E3%83%87%E3%82%A3%E3%83%AC%E3%82%AF%E3%83%88%E3%83%AA%E3%81%AE%E6%89%B1%E3%81%84
- （解決策2:）$HOME/.ssh/id_rsa と指定することで展開される場所が共通化できた

### ~~フォルダにスペースが混じっているとバグる(コマンドが正常に実行されなくなる)~~(解決済み)
- "$HOME"等で囲む必要あり．
- 下記のsshコマンド部分はどうしようもないので，その下にあるように変更した
  - `rsync -av -e "ssh -i $HOME/.ssh/id_rsa " ${id}@o-vnc.center.oit.ac.jp:kadai "$HOME"`
  - `rsync -av -e "ssh -i ./.ssh/id_rsa " ${id}@o-vnc.center.oit.ac.jp:kadai "$HOME"`
    - rsyncの前にcdを実行しておくこと．

### ~~ホームディレクトリが`C:\Users\hoge`形式の場合，rsyncコマンドが失敗する(The source and destination cannot both be remote)~~(解決済み)
- `C:\`の`:`のせいでremoteだと誤認するっぽい．
- HOMEを`export HOME=$(cd "$USERPROFILE\byod_home" && pwd)`として設定することで，`/c/Users/..`形式でホームディレクトリを処理できるようになる

