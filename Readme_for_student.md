# BYOD下におけるプログラミング演習環境の構築
## 演習環境の準備
### vscode, jdk, portablegitのインストール
- [java_archive](https://drive.google.com/drive/folders/1IqUOxfcV4Ort2wKhY1OpOVpVRusfTG-r?usp=sharing) にあるoit-javaYYYYMMDD.exeの最新版をC:\oitにダウンロードし，実行（展開）する
  - C:\oitフォルダがない場合は作成すること
  - 自己解凍ファイル展開後のフォルダ構成は以下のとおり(一部省略している．また，X,Y,Zにはバージョン番号が入る)
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
C:\oit\PortableGit-2.XX.Z-64\bin
C:\oit\PortableGit-2.XX.Z-64\cmd
:
C:\oit\PortableGit-2.XX.YY.Z-64\git-bash.exe
C:\oit\java-bash-2.XX.Z-64.exe
```

### 開発環境セットアップ
- 授業開始までに必ず下記全てを実行しておくこと．
1. `C:\oit\java-bash-2.XX.Z-64.exe`を実行する．
   - `C:\Users\ユーザ名\oithomes\java\kadai\javaYY\`フォルダが生成される（まだ作成されていない場合．なお，javaYYのYYにはその年の末尾2桁が入る）．
   - $HOMEフォルダとして`C:\Users\ユーザ名\oithomes\java`がセットされている．
1. bashが起動するので，下記のように`initssh`と入力し，Enter
   - まずe1で始まるユーザ名を入力する
   - 画面に書かれているように，「Enter passphrase」「Enter same passphrase again」と言われたらEnterキーを押す
   - 最後に演習室へのログインパスワードを入力し，Enter
   - 成功しましたと表示されればOK．表示されなかった場合は再度`initssh`を実行すること．
   <img src="https://github.com/igakilab/byod.zip/blob/images/images/initssh.png?raw=true" width=500>
1. `initssh`実行後に，`getlocal`コマンドを入力し，Enter
   - 演習で利用する様々なコマンドが最新のものに更新される（課題提出等）
1. `initssh`及び`getlocal`実行後に，`getjava`コマンドを入力し，Enter
   - `$HOME\java\kadai\javaYY`フォルダ内にlec01~lec14等の課題作成フォルダ及びvscodeのための設定ファイルがダウンロードされる．
1. `initssh`，`getlocal`及び`getjava`コマンド実行後に，`shusseki`コマンドを入力し，Enter
   - 画面に下記のように「rsyncは成功しました」もしくは「更新ファイルがありませんでした」と表示されればOK.
   - これらが表示されるのを確認後，Ctrlキーを押したままCボタンを押し，shussekiコマンドを終了させる

## 各コマンドの詳細
### initssh
- initsshを実行すると，sshの公開鍵が登録され，ID/Pass認証ではなく公開鍵認証方式でo-vnc.center.oit.ac.jpにアクセスできるようになる
- .sshフォルダと公開鍵・秘密鍵は`$HOME/.ssh`フォルダ内部に作成される
  - $HOMEフォルダは`C:\Users\ユーザ名\oithomes\java`
- $HOME/.ssh フォルダが既に作成されている場合はinitsshコマンドを実行する必要はない
  - Java演習環境をインストール後最初に一回実施するだけでOK.

## 開発(学生の立場から)の流れ
### 準備
- `C:\oit\java-bash-2.XX.YY.Z-64.exe`を実行し，「shusseki」コマンドを実行する
- 下記のように「rsyncは成功しました」もしくは「更新ファイルがありませんでした」と表示されればOK．
  - 「rsyncは失敗しました．」と表示されたときはサーバに接続できていない（＝課題が提出されない）．必ず原因を確認すること．
- 以降はshussekiコマンドを実行したまま課題を実施する．課題ファイルが更新されるたびに一定時間ごとに自動的にサーバにファイルが提出される．
- 終了時には`Ctrl + C`を押すこと．
<img src="https://github.com/igakilab/byod.zip/blob/images/images/shusseki.png?raw=true" width=500>

### vscodeの起動及びファイル編集
- `C:\oit\VSCodePortable_1.XX.Y\VSCodePortable.exe`を起動する
- ファイル->フォルダを開く->「`$HOME\kadai\javaYY`」フォルダを指定する
  - $HOMEは`C:\Users\ユーザ名\oithomes\java`．
- 下図のように，lec01~lec14フォルダが表示されるので，各回のフォルダにjavaファイルを作成し，編集する．
  - 更新されたファイルはshussekiコマンドを実行し続けている限り自動的に提出される．

### コンパイル・実行方法
- javaファイルをvscodeで編集後，`java-bash-2.XX.Y-64.exe`を実行する(shussekiを動かしているものとは別に開くこと)
- $HOMEに移動した状態でbashが開くので，編集中のjavaファイルが存在するディレクトリに移動し，`javac javaファイル`と実行する．
- 正常にコンパイルができ，classファイルができたら，`java javaファイル`と実行すると結果が出力される．
- 下図はlec01\Work11.javaをコンパイル・実行した例である．

### コンパイル・実行方法(方法2)
- Hello.javaを開いた状態で，`F5`を叩く．
- コンパイルがターミナルで行われ，実行結果が外部ターミナル（自動的に開く）に表示される
- なお，これも初回起動時（初回にjava_srcフォルダを開いた際）はデバッガが見つけられず，コンパイルに失敗することがあるので，その場合はvscodeごと再起動する．
- なお，実行は不要でコンパイルのみがしたい場合は，`Ctr+Shift+B`を押せば良い．
- 実行には環境によっては数秒かかる

