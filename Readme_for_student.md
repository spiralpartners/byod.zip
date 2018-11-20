# 自ノートPCへのJava演習環境のインストールとセットアップ

## **注意！！**
- アンチウィルスソフトがインストール時及び実行時に悪影響を及ぼすことがあります．Java演習環境のインストールがうまくいかない場合にはアンチウィルスソフトの動作状況を確認してください．
- 特に**Avast**(アバスト)は重要なファイルを無断で削除することが確認されていますので，自分のノートPCにインストールされている場合はアンインストールしておいてください．
  - アンインストール後はWindowsのデフォルトのアンチウィルスソフト等を起動するようにしておいてください．

## 演習環境の準備
- 下記インストール及びセットアップは必ず授業開始前にすべて実行しておくこと

### vscode, jdk, portablegitのインストール
- [java_archive(http://bit.ly/oitjava)](http://bit.ly/oitjava) にあるoit-javaYYYYMMDD.exeの最新版をC:\oitにダウンロードし，実行（展開）する
   - 大学のGoogleアカウントでログインしないとダウンロードできない
   - C:\oitフォルダがない場合は作成すること
   - 自己解凍ファイル展開後のフォルダ構成は以下のとおり(一部省略している．また，X,Y,Zにはバージョン番号が入る)
```
C:\oit\VSCodePortable_1.XX.Y\App
C:\oit\VSCodePortable_1.XX.Y\Data
C:\oit\VSCodePortable_1.XX.Y\Other
C:\oit\VSCodePortable_1.XX.Y\Help.html
C:\oit\VSCodePortable_1.XX.Y\VSCodePortable.exe
:
C:\oit\openjdk1.8.0.XXX\App
C:\oit\openjdk1.8.0.XXX\bin
C:\oit\openjdk1.8.0.XXX\Data
C:\oit\openjdk1.8.0.XXX\db
C:\oit\openjdk1.8.0.XXX\include
C:\oit\openjdk1.8.0.XXX\jre
:
C:\oit\PortableGit-2.XX.Z-64\bin
C:\oit\PortableGit-2.XX.Z-64\cmd
:
C:\oit\PortableGit-2.XX.Z-64\git-bash.exe
C:\oit\java-bash-2.XX.Z-64.exe
```

### 演習環境のセットアップ
1. `C:\oit\java-bash-2.XX.Z-64.exe`を実行する．
   - `C:\Users\ユーザ名\oithomes\java\kadai\javaYY\`フォルダが生成される（まだ作成されていない場合．なお，javaYYのYYにはその年の末尾2桁が入る）．
   - $HOMEフォルダとして`C:\Users\ユーザ名\oithomes\java`がセットされている．
1. bashが起動するので，下記のように`initssh`と入力し，Enter
   - まずe1で始まるユーザ名を入力する
   - 画面に書かれているように，「Enter file in..」「Enter passphrase」「Enter same passphrase again」と言われたらEnterキーを押す
   - 最後に演習室へのログインパスワードを入力し，Enter
   - 成功しましたと表示されればOK．表示されなかった場合は再度`initssh`を実行すること．
   <img src="https://github.com/igakilab/byod.zip/blob/images/images/initssh.jpg?raw=true" width=500>
1. `initssh`実行後に，`getlocal`コマンドを入力し，Enter
   - 演習で利用する様々なコマンドが最新のものに更新される（課題提出等）
   <img src="https://github.com/igakilab/byod.zip/blob/images/images/getlocal.jpg?raw=true" width=400>
1. `initssh`及び`getlocal`実行後に，`getjava`コマンドを入力し，Enter
   - `$HOME\java\kadai\javaYY`フォルダ内にlec01~lec14等の課題作成フォルダ及びvscodeのための設定ファイルがダウンロードされる．
   <img src="https://github.com/igakilab/byod.zip/blob/images/images/getjava.jpg?raw=true" width=400>
1. `initssh`，`getlocal`及び`getjava`コマンド実行後に，`shusseki`コマンドを入力し，Enter
   - 画面に下記のように「rsyncは成功しました」もしくは「更新ファイルがありませんでした」と表示されればOK.
   - これらが表示されるのを確認後，Ctrlキーを押したままCボタンを押し，shussekiコマンドを終了させる
   <img src="https://github.com/igakilab/byod.zip/blob/images/images/shusseki.jpg?raw=true" width=400>
1. 上記終了後，アンケートに回答すること．なお，実施はしたが不具合が発生した等で自力で解決できない場合は，上記終了前にアンケートに回答し，教員からの連絡を待つこと．

## 開発の流れ
- 下記の準備，ファイル編集，コンパイル及び実行は毎演習あるいは課題実施ごとに毎回実施する
### 準備
- `C:\oit\java-bash-2.XX.Z-64.exe`を実行し，「shusseki」コマンドを実行する
- 下記のように「rsyncは成功しました」もしくは「更新ファイルがありませんでした」と表示されればOK．
  - 「rsyncは失敗しました．」と表示されたときはサーバに接続できていない（＝課題が提出されない）ので，必ず原因を確認すること．
- 以降はshussekiコマンドを実行したまま課題を実施する．shussekiコマンドが実行されている限り，課題ファイルが更新されるたびに一定時間ごとに自動的にサーバにファイルが提出される．
   - shussekiコマンド終了時には`Ctrl + C`を押すこと．
<img src="https://github.com/igakilab/byod.zip/blob/images/images/shusseki.jpg?raw=true" width=400>

### vscodeの起動及びファイル編集
- `C:\oit\VSCodePortable_1.XX.Y\VSCodePortable.exe`を起動する
  - 英語モードで起動するが，再起動すると設定ファイルが読み込まれて日本語モードになる
- ファイル->フォルダを開く->「`$HOME\kadai\javaYY`」フォルダを指定する
  - $HOMEは`C:\Users\ユーザ名\oithomes\java`．
  - javaYYのYYには19や20等20YY年の下2桁の数字が入る．
- 下図のように，lec01~lec14フォルダが表示されるので，javaファイルを作成したい回のフォルダを選択し，ファイルを新規作成・編集する．
  - 更新されたファイルはshussekiコマンドを実行し続けている限り自動的に提出される．
<img src="https://github.com/igakilab/byod.zip/blob/images/images/vscode_newfile.jpg?raw=true" width=500>

### コンパイル・実行方法
- javaファイルをvscodeで編集後，`java-bash-2.XX.Y-64.exe`を実行する(shussekiを動かしているものとは別に開くこと)
- $HOMEに移動した状態でbashが開くので，編集中のjavaファイルが存在するディレクトリに移動し，`javac`コマンドでjavaファイルをコンパイルする．
- 正常にコンパイルができ，classファイルができたら，`java`コマンドを実行すると結果が出力される．
- 下図はlec01\Work11.javaをコンパイル・実行した例である．
<img src="https://github.com/igakilab/byod.zip/blob/images/images/javac.jpg?raw=true" width=400>

## 各コマンドの詳細
### initssh
- initsshを実行すると，sshの公開鍵が登録され，ID/Pass認証ではなく公開鍵認証方式でo-vnc.center.oit.ac.jpにアクセスできるようになる
- .sshフォルダと公開鍵・秘密鍵は`$HOME/.ssh`フォルダ内部に作成される
  - $HOMEフォルダは`C:\Users\ユーザ名\oithomes\java`
- $HOME/.ssh フォルダが既に作成されている場合はinitsshコマンドを実行する必要はない
  - Java演習環境をインストール後最初に一回実施するだけでOK.

