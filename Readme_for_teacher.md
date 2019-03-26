# 教員向けプログラミング演習環境利用マニュアル
## Java開発環境
- Java開発環境はJDK＋PortableGit+Visual Studio Code．
  - [oit-java20YYMMDD.exe](https://drive.google.com/drive/folders/1IqUOxfcV4Ort2wKhY1OpOVpVRusfTG-r?usp=sharing)
    - 大学アカウントでGoogleにログインした場合のみDL可能
    - jdk,portablegit,vscodeportableが自己解凍形式で圧縮されているので，c:\oit にダウンロードし，実行すると良い．
    - 実行後以下のようなディレクトリ構成になっていることを確認する

```
C:\oit\VSCodePortable_1.XX.Y\App
C:\oit\VSCodePortable_1.XX.Y\Data
C:\oit\VSCodePortable_1.XX.Y\Other
C:\oit\VSCodePortable_1.XX.Y\Help.html
C:\oit\VSCodePortable_1.XX.Y\VSCodePortable.exe
:
C:\oit\openjdk1.8.0_XXX\App
C:\oit\openjdk1.8.0_XXX\bin
C:\oit\openjdk1.8.0_XXX\Data
C:\oit\openjdk1.8.0_XXX\db
C:\oit\openjdk1.8.0_XXX\include
C:\oit\openjdk1.8.0_XXX\jre
:
C:\oit\PortableGit-2.XX.YY.Z-64\bin
C:\oit\PortableGit-2.XX.YY.Z-64\cmd
:
C:\oit\PortableGit-2.XX.YY.Z-64\git-bash.exe
C:\oit\java-bash-2.XX.YY.Z-64.exe
C:\java-bash.bat
```



## 開発（教員の立場から）の流れ
### セットアップ
- `C:\oit\java-bash-2.XX.Y-64.exe`を実行する
- `C:\Users\名前\oithomes\java\kadai\java18` フォルダが自動的にできる
  - HOMEフォルダが`oithomes\java`になるので，`java-bash-2.XX.Y-64.exe`実行時には自動的に`HOME`に移動する．
- gitのコミッター情報を登録する
  - 細かい設定もちょっと追加（日本語の扱いや改行コード関連の設定）

```
$ git config --global user.email "自分のメールアドレスを入れてください"
$ git config --global user.name "自分の名前をローマ字で入れてください"
$ git config --global core.quotepath false
$ git config --global core.autocrlf false
```
- sshでgitに接続する場合は`ssh-keygen -t rsa`と実行し，公開鍵をgitbucketのstaffアカウントに追加する
  - http://150.89.223.124/teacher/_ssh
  - 鍵をgitbucketのstaffアカウントに追加する際は，必ず誰の鍵か分かるように登録すること
  - `.ssh`フォルダはoithomes\java以下に作成される
  - よくわからない場合は飛ばしてもOK（その場合はsshじゃなくてhttpでgitにアクセスする）

### 開発作業（リポジトリのclone）
- リポジトリのURIを確認する
-- 下記のようなリポジトリページを開き，HTTPで接続する場合は「HTTP」を，SSHで接続する場合は「SSH」を選択し，表示されるURLをコピーする．

<img src="https://github.com/igakilab/byod.zip/blob/images/images/gitbucket1.jpg?raw=true" width=500>

- Clone（sshの場合）
```
$ git clone ssh://git@150.89.223.124:2222/teacher/Java.git
```
- Clone（httpの場合）
  - httpの場合は，↓のコマンド実行後にID，Passを入力するダイアログが表示されるので，teacher/いつもの，を入力する．
```
$ git clone http://150.89.223.124/git/teacher/Java.git
```

### 開発作業（実装・コンパイル）
#### vscodeの起動及びファイル編集
- `C:\oit\VSCodePortable_1.XX.Y\VSCodePortable.exe`を起動する
- ファイル->フォルダを開く->Cloneしたリポジトリのフォルダ(Javaフォルダ)を指定する
- フォルダを開いた後，lec01~lec14の任意のフォルダ内にjavaファイルを作成し，適当に編集する

#### コンパイル・実行方法(方法1)
- lec01にあるHello.javaを例に説明する．
- lec01/Hello.javaをvscodeで開いた状態で，`java-bash-2.XX.Y-64.exe`を実行する
- `C:\oit`にいる状態でbashのターミナルが開くので，`cd`と実行する．
- $HOMEに移動するので，`cd Java/lec01`等のHello.javaが存在するディレクトリに移動し，`javac Hello.java`と実行する．
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
$ git add ./lec01/Hoge.java
$ git commit -m "Hogeクラスを作成した"
$ git push origin master
```
- branchモデルでの開発等(Pull Requestも)は可能なら活用する

