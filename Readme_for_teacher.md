# 教員向けプログラミング演習環境利用マニュアル
## Java開発環境
- Java開発環境はJDK＋PortableGit+Visual Studio Code．
  - [oit-java20180509.exe](https://drive.google.com/open?id=1o3O-GHunZnAACjgAp53SAscogYbNw1w4)
    - 大学アカウントでGoogleにログインした場合のみDL可能
    - jdk,portablegit,vscodeportable,java-bash-2.17.0-64.exeが自己解凍形式で圧縮されているので，c:\oit にダウンロードし，実行すると良い．
    - 実行後以下のようなディレクトリ構成になっていることを確認する

```
C:\oit\VSCodePortable_1.XX.Y\App
C:\oit\VSCodePortable_1.XX.Y\Data
C:\oit\VSCodePortable_1.XX.Y\Other
C:\oit\VSCodePortable_1.XX.Y\Help.html
C:\oit\VSCodePortable_1.XX.Y\VSCodePortable.exe
:
C:\oit\java8_XXX\App
C:\oit\java8_XXX\bin
C:\oit\java8_XXX\Data
C:\oit\java8_XXX\db
C:\oit\java8_XXX\include
C:\oit\java8_XXX\jre
:
C:\oit\PortableGit-2.XX.YY.Z-64\bin
C:\oit\PortableGit-2.XX.YY.Z-64\cmd
:
C:\oit\PortableGit-2.XX.YY.Z-64\git-bash.exe
C:\oit\java-bash-2.XX.YY.Z-64.exe
```


## 教材管理用各種サーバ
### Knowledge
- http://150.89.223.125
- teacher/いつもの でログイン可能
- Markdownで教材作成が可能
- 各授業回について説明資料，解説資料（翌週公開）を作成する
- 公開/非公開機能を利用可能
- SAに学生より事前に資料を公開する必要があるかどうか（解説資料を事前に提示する等）

### Gitbucket
- http://150.89.223.124/
- teacher/いつもの，sa/いつもの でログイン可能
- 作成したリポジトリごとに公開鍵を登録すればOK（httpでも可）

## 開発（教員の立場から）の流れ
### セットアップ
- `C:\oit\java-bash-2.XX.YY.Z-64.exe`を実行する
- `C:\Users\名前\oithome\kadai` フォルダが自動的にできる
  - HOMEフォルダが`oithome`になるので，`cd`と実行すると`HOME`に移動する．
- gitのコミッター情報を登録する
  - 細かい設定もちょっと追加（日本語の扱いや改行コード関連の設定）

```
$ git config --global user.email "igaki@gitbucket.com"
$ git config --global user.name "Hiroshi Igaki"
$ git config --global core.quotepath false
$ git config --global core.autocrlf false
```
- sshでgitに接続する場合は`ssh-keygen -t rsa`と実行し，公開鍵をgitbucketの各リポジトリに追加する（リポジトリページ->Settings->DeployKeys->Add a deploy key)
  - `.ssh`フォルダはoithome以下に作成される
  - よくわからない場合は飛ばしてもOK（その場合はsshじゃなくてhttpでgitにアクセスする）

### 開発作業（リポジトリのclone）
- リポジトリのURIを確認する
-- 下記のようなリポジトリページを開き，HTTPで接続する場合は「HTTP」を，SSHで接続する場合は「SSH」を選択し，表示されるURLをコピーする．

<img src="https://github.com/igakilab/byod.zip/blob/images/images/gitbucket1.jpg?raw=true" width=500>

- Clone（sshの場合）
```
$ git clone ssh://git@150.89.233.124/teacher/????.git
```
- Clone（httpの場合）
  - httpの場合は，↓のコマンド実行後にID，Passを入力するダイアログが表示されるので，teacher/いつもの，を入力する
```
$ git clone http://150.89.223.124/git/teacher/????.git
```

### 開発作業（実装・コンパイル）
#### vscodeの起動及びファイル編集
- `C:\oit\VSCodePortable_1.XX.Y\VSCodePortable.exe`を起動する
- ファイル->フォルダを開く->Cloneしたリポジトリのフォルダを指定する
- フォルダを開いた後，javaファイルを作成し，適当に編集する

#### コンパイル・実行方法(方法1)
- Hello.javaをvscodeで開いた状態で，`java-bash-2.XX.Y-64.exe`を実行する(initsshやshussekiを動かしているものとは別に開くこと)
- `C:\oit`にいる状態でbashのターミナルが開くので，`cd`と実行する．
- $HOMEに移動するので，`cd kadai/java_src/java01`等のHello.javaが存在するディレクトリに移動し，`javac Hello.java`と実行する．
- 正常にコンパイルができ，classファイルができたら，`java Hello`と実行すると結果が出力される．

#### コンパイル・実行方法(方法2)
- Hello.javaを開いた状態で，`F5`を叩く．
- コンパイルがターミナルで行われ，実行結果が外部ターミナル（自動的に開く）に表示される
- なお，これも初回起動時（初回にリポジトリフォルダを開いた際）はデバッガが見つけられず，コンパイルに失敗することがあるので，その場合はvscodeごと再起動する．
- なお，実行は不要でコンパイルのみがしたい場合は，`Ctr+Shift+B`を押せば良い．
- 実行には環境によっては数秒かかる

### 開発作業（コミット・プッシュ）
- 編集したファイルをaddして，commitしてpushする
```
$ git add ./java01/Hoge.java
$ git commit -m "Hogeクラスを作成した"
$ git push origin master
```
- branchモデルでの開発等(Pull Requestも)は可能なら活用する

## 開発(学生の立場から)の流れ
### 準備(公開鍵sshの設定)
- `C:\oit\java-bash-2.XX.YY.Z-64.exe`を実行し，「initssh」コマンドを実行する
  - $HOMEフォルダとして`C:\Users\ユーザ名\oithome`がセットされる．
  - sshの公開鍵が登録され，ID/Pass認証ではなく公開鍵認証方式でo-vnc.center.oit.ac.jpにアクセスできるようになる
  - .sshフォルダと公開鍵・秘密鍵は`oithome\.ssh`フォルダ内部に作成される
  - $HOME/.ssh フォルダが既に作成されている場合はinitsshコマンドを実行する必要はない（他の授業と共有することを前提とする）
  - 下記画像のように`成功しました`と表示されればOK.

<img src="https://github.com/igakilab/byod.zip/blob/images/images/initssh.png?raw=true" width=500>

### Javaファイルの編集・コンパイル・実行
#### vscodeの起動及びファイル編集
- `C:\oit\VSCodePortable_1.XX.Y\VSCodePortable.exe`を起動する
- ファイル->フォルダを開く->「`$HOME\kadai\java_src`」(授業ではjava2018等のフォルダ名になる可能性あり)フォルダを指定する
- 例えば`java01\Hello.java`を開いて適当に編集する

#### コンパイル・実行方法(方法1)
- Hello.javaをvscodeで開いた状態で，`java-bash-2.XX.Y-64.exe`を実行する(initsshやshussekiを動かしているものとは別に開くこと)
- `C:\oit`にいる状態でbashのターミナルが開くので，`cd`と実行する．
- $HOMEに移動するので，`cd kadai/java_src/java01`等のHello.javaが存在するディレクトリに移動し，`javac Hello.java`と実行する．
- 正常にコンパイルができ，classファイルができたら，`java Hello`と実行すると結果が出力される．

#### コンパイル・実行方法(方法2)
- Hello.javaを開いた状態で，`F5`を叩く．
- コンパイルがターミナルで行われ，実行結果が外部ターミナル（自動的に開く）に表示される
- なお，これも初回起動時（初回にjava_srcフォルダを開いた際）はデバッガが見つけられず，コンパイルに失敗することがあるので，その場合はvscodeごと再起動する．
- なお，実行は不要でコンパイルのみがしたい場合は，`Ctr+Shift+B`を押せば良い．
- 実行には環境によっては数秒かかる

### 出席・課題提出方法
- `C:\oit\java-bash-2.XX.YY.Z-64.exe`を実行し，「shusseki」コマンドを実行する
- 下記のように~/kadai がo-vnc.center.oit.ac.jp上の同じフォルダとsyncされればOK．
- 終了時には`Ctr + C`
<img src="https://github.com/igakilab/byod.zip/blob/images/images/shusseki.png?raw=true" width=500>
