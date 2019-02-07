# BYOD下におけるプログラミング演習環境の構築
## リポジトリの内容
- このリポジトリの内容はBYOD下でのプログラミング演習環境の一部である
- 学生が解く課題を保存するフォルダ(vscode設定ファイル付き)，PortableGitの変更内容，PortableGitのバイナリを実行するためのオプション付きexeを作成するフォルダの3つをこのリポジトリで管理する
  - 各フォルダの詳細はそれぞれのフォルダのReadme.md参照のこと
- 想定するプログラミング演習の言語はJava
- 実行環境はopenJDK＋PortableGit+Visual Studio Code．

## 演習環境の準備
### vscode, jdk, portablegitのインストール
- [java_archive](https://drive.google.com/drive/folders/1IqUOxfcV4Ort2wKhY1OpOVpVRusfTG-r?usp=sharing) にあるoit-javaYYYYMMDD.exeの最新版をC:\oitにダウンロードし，実行（展開）する
  - 自己解凍ファイル展開後のフォルダ構成は以下のとおり(X,Y,Zにはバージョン番号が入る)
```
C:\oit\VSCode-win32-x64-1.XX.Y\bin
C:\oit\VSCode-win32-x64-1.XX.Y\data
C:\oit\VSCode-win32-x64-1.XX.Y\locales
C:\oit\VSCode-win32-x64-1.XX.Y\resources
C:\oit\VSCode-win32-x64-1.XX.Y\tools
:
C:\oit\openjdk1.8.0.XXX\bin
C:\oit\openjdk1.8.0.XXX\include
C:\oit\openjdk1.8.0.XXX\jre
C:\oit\openjdk1.8.0.XXX\lib
:
C:\oit\PortableGit-2.XX.Y-64\bin
C:\oit\PortableGit-2.XX.Y-64\cmd
:
C:\oit\PortableGit-2.XX.Y-64\git-bash.exe
C:\oit\java-bash-2.XX.Y-64.exe
```

### 開発環境セットアップ
- `C:\oit\java-bash-2.XX.Y-64.exe`を実行する．
  - `C:\Users\ユーザ名\oithomes\java\kadai\javaYY\`フォルダが生成される（なお，javaYYのYYにはその年の末尾2桁が入る）．
- 「initssh」コマンドを実行する
  - $HOMEフォルダとして`C:\Users\ユーザ名\oithomes\java`がセットされる．
  - sshの公開鍵が登録され，ID/Pass認証ではなく公開鍵認証方式でo-vnc.center.oit.ac.jpにアクセスできるようになる
  - .sshフォルダと公開鍵・秘密鍵は`oithomes\java\.ssh`フォルダ内部に作成される
  - $HOME/.ssh フォルダが既に作成されている場合はinitsshコマンドを実行する必要はない
    - Java演習環境をインストール後最初に一回実施するだけでOK.
  - 下記画像のように`成功しました`と表示されればOK.

<img src="https://github.com/spiralpartners/byod.zip/blob/images/images/initssh.png?raw=true" width=500>

- getlocalコマンドを実行する
  - 様々な便利コマンドが最新のものに更新される
- getjavaコマンドを実行する
  - `$HOME\java\kadai\javaYY`フォルダ内にlec01~lec14等の課題作成フォルダ及びvscodeのための設定ファイルがダウンロードされる．
    - `javaYY/.vscode`フォルダ内のsettings.json, tasks.json, launch.jsonファイルがvs code関連のすべての設定ファイル
    - $HOMEは`C:\Users\ユーザ名\oithomes\java`

## 開発(学生の立場から)の流れ
### Javaファイルの編集・コンパイル・実行
#### vscodeの起動及びファイル編集
- `C:\oit\java-bash-2.XX.Y-64.exe`を起動する
  - ターミナルが起動するので，`code`と入力するとVisual Studio Codeが起動する．
- ファイル->フォルダを開く->「`$HOME\kadai\javaYY`」フォルダを指定する
- 例えば`lec01\Work11.java`を開いて適当に編集する

#### コンパイル・実行方法
- Hello.javaをvscodeで開いた状態で，vscodeを開いたターミナルを表示させる
- `C:\oit`にいる状態でbashのターミナルが開くので，`cd`と実行する．
- $HOMEにいる状態でターミナルが開いている（はず）ので，`cd kadai/javaYY/java01`等のHello.javaが存在するディレクトリに移動し，`javac Hello.java`と実行する．
- 正常にコンパイルができ，classファイルができたら，`java Hello`と実行すると結果が出力される．

### 出席・課題提出方法
- `C:\oit\java-bash-2.XX.YY.Z-64.exe`を実行し，「shusseki」コマンドを実行する
- 下記のように~/kadai がo-vnc.center.oit.ac.jp上の同じフォルダとsyncされればOK．
- 終了時には`Ctr + C`
<img src="https://github.com/spiralpartners/byod.zip/blob/images/images/shusseki.png?raw=true" width=500>

# Java演習開発環境用VS codeセットアップ詳細
- 以下は0からvs code 環境のセットアップを行う際に参考にする情報．
## Step1. VSCode Portable Modeのセットアップ
- [Download VSCode](https://code.visualstudio.com/Download)からVSCode Windows .zip (64bit)をダウンロードし，c:\oitに保存・実行（展開）する．
- dataフォルダをVSCodeフォルダ内に作成する
  - 参考：https://code.visualstudio.com/docs/editor/portable

## Step2. 以下の2つをC:\oit以下に追加インストール
- ディレクトリ名を指定のものに変更する
- redhat openjdk (x64)
  - https://developers.redhat.com/products/openjdk/download/
  - C:\oitにopenjdk1.8.0.191のようなバージョンに対応するフォルダを作成し，DLしたopenjdkのzipファイル内の中身を展開する．
    - C:\oit\openjdk1.8.0.191\bin といったディレクトリ構成になっていることを確認する．
- PortableGit(x64)（解凍するだけ）
  - フォルダ名は「PortableGit-2.20.0-64」のようにつける
  - [PortableGit-2.xx.x.xx-64-bit.7z.exe](https://github.com/git-for-windows/git/releases)
  - bash.exe経由で起動するように本リポジトリのjava-bashフォルダからexeファイルを作成しておく
    - go環境を作成し，java-bash/build_java-bash.sh を実行する．その後ファイル名をjava-bash-2.xx.x-64.exe にし，c:\oit直下に配置する．

## Step3. vscodeの拡張機能の追加
- Japanese Language Pack for Visual Studio Code
  - Install後にCtrl+Shift+P を押してコマンド パレットを表示させ、"config" と入力し、利用できるコマンドのリストをフィルター処理してから Configure Display Language を選択すると，locale.jsonが生成される．
  - 最新のバージョンだとInstall後に再起動すると自動で設定するっぽいが，念のためにlocale.jsonファイルをvscode経由で作成しておくこと．
- Language support for Java ™ for Visual Studio Code, Debugger for Java
  - Java Extension Packだと不要なMaven pluginまでインストールされるので，個別に2つのプラグインをインストールする
- EvilInspector
  - 全角スペースを強調表示する
- EditorConfig for vscode
  - .editorconfigをプロジェクトのホームフォルダにおいておき，VSCodeの設定で自動整形設定をしておけば，.editorconfig のとおりに自動整形してくれる

## Step4. VS Codeユーザー設定の変更
- ファイル->基本設定->設定をクリックする
- ユーザー設定を選択し，updateで設定を検索する．
- 拡張機能やアプリケーションの更新関連の自動アップデート等をすべてOFFにしておく
  - data\user-data\User\settings.json ファイルが生成されるので，下記のようになっているか確認しておくこと

```
{
    "update.enableWindowsBackgroundUpdates": false,
    "update.channel": "none",
    "update.showReleaseNotes": false,
    "extensions.autoCheckUpdates": false,
    "extensions.autoUpdate": false
}
```

## Step5. VS Codeワークスペース設定
- 自動整形設定やjava.homeの設定(settings.jsonに追記）
  - https://qiita.com/maron8676/items/017cd830ab0c5fb8bcac
- 本リポジトリjava_srcフォルダ参照．

### Step6. 不要なフォルダを削除
- 「C:\oit\VSCode-win32-x64-1.XX.Y\data\user-data」の中身を以下を除いて削除する．
  - ただし，「C:\oit\VSCode-win32-x64-1.XX.Y\data\user-data\User\locale.json」だけは残しておくこと（日本語設定）
  - ただし，「C:\oit\VSCode-win32-x64-1.XX.Y\Data\extensions\redhat.java-0.14.0\server\config_win」以下にキャッシュができる場合があるので注意(config.ini以外はキャッシュ）


### Step7. 演習フォルダ(本リポジトリ)のセットアップ
- .vscode以下のlaunch.json, tasks.json, settings.json
- フォルダルートにある.classpathと.editorconfig, .project
- 以上のファイルの設定は本リポジトリ`java_src`参照のこと

### Step8. シェルのセットアップ
- C:\oit\PortableGit-2.XX.YY.Z-64 以下に本リポジトリのPortableGitフォルダ以下をコピーする

### Step8. /usr/local/bin/と学生用java演習フォルダをサーバに設置
- /home/teachers/t2015025/public_html/progjava/ を作成する
  - `~/`から`~/public_html/progjava`までを711にしておく
- getjava.shとgetlocal.shを/home/teachers/t2015025/public_html/progjava/に配置し，各コマンドを実行する
- 上記 progjava フォルダに対して権限設定が適切であることを確認しておくこと
  - `progjava`内の`java18`,`local`以下のディレクトリを755
  - `progjava`内の各ファイルを644

# 今後の課題
### Ctrl+@でvscode内のターミナルでbashを開くと，コンパイルエラー時に文字化けする
- vscode内ターミナルで`javac Hello.java`を実行し，コンパイルエラーが起きると文字化けする．調べた限りではshift-jisをutf-8で表示しようとして文字化けしてるっぽい．
- 正常にコンパイルが通ったときは問題なく日本語も出力されるが，コンパイルエラー（恐らく実行時エラーも？）時にのみ文字化けする．
- 今の所Ctrl+@を実行させないようにするしか解決方法がない
- encodingを指定しても駄目．

### ~~全ディレクトリ構成をどうするか~~(一応交渉成立)
- jdk,vscode,portablegit等のバイナリを置くディレクトリとjavakadaiを置くディレクトリの場所をどうするか.
- 他の授業との兼ね合いや年度進行（再履修含む）にどう対応するかも検討する必要あり．

### ~~デフォルト文字エンコードをどうするか~~(UTF8で確定)
- vs codeのデフォはutf-8だが，その場合winで`javac -encoding utf-8`を毎回つける必要がある
  - 参考 http://kyouichisato.blogspot.jp/2015/06/visual-studio-code-jis.html
  - shiftjisの場合は何もつけなくて良いが，課題チェックシステム等のアプリで表示する際にutf-8で統一しておいたほうが多分実装が楽．

### ~~課題提出方法をどうするか~~(rsyncの定期実行で確定)
- 学生が課題を解いている過程をリアルタイムにチェックできるような課題提出方法が望ましい．
- 方法1: 学生にネットワークドライブ上のフォルダに特定のドライブレターを割り当てさせる．その中に学生ごとのフォルダを作成し，そこに本リポジトリの内容を配置，学生ごとのvscodeから開かせる
  - メリット：フォルダを教員が任意のタイミングで配布できる．
  - デメリット：ファイル削除の際にゴミ箱に保存できないので一々システムからのアラートがでる．VPNで接続したときにだいぶ重いことがある（ネットワークによってはファイル書き込みに数秒かかる）．教員側のLinuxからアクセスするのが困難．
- 方法2: rsyncコマンドと本リポジトリの内容を配布し，サーバ側の学生のホームディレクトリ下にrsyncさせる（コマンドは既にある）
  - メリット：教員側から見ると，現状の演習環境と同一に見える（学生のホームディレクトリ下の特定ディレクトリにアクセスすると学生のコードが見える）．
  - デメリット：rsyncコマンドを学生が実施するのを忘れると提出がまったくできないことになる．学生から見たときに何が提出されているかを確認することが難しい(定期的にrsyncを実行する関係上，最新がUPLOADされているかが分からない)．rsyncが何らかの不具合で学生環境において正常に実行できないと詰む詰む（方法1もこの点では同様）．

### ~~シェルをどうするか~~(bash.exe)
- PortableGit(Bash)を導入するとbash.exeが利用できる（250MB程度必要）
  - javacへのPATHをbash.bashrcを用いてPortableな形で通せる
- Powershell, cmd.exeも可能
  - Windows10だとデフォがpowershell, それ以前だとcmdになる
  - javacへのPATHをPortableな形で通せなくなる（Windowsのシステム環境変数かユーザ環境変数で指定する必要あり）->他の環境と競合を起こす．

### ~~ファイル編集時にextends元のクラスが間違っており，エラーが検出されることがある~~(運用で解決)
- extends元のクラスと同じ名前のクラスを別の課題で作成していた場合に，そちらを参照してしまうことがある
- 原因は下にもあるディレクトリ構造とpackageの問題．JDTによる自動出力先の設定がbinになっているため，packageの指定がないと，同一クラス名は上書きされてしまう．
  - 本リポジトリにあるようにjava02とjava01\ex02で両方共PacMan classを定義したところ，java02のNoizyPacMan extends PacMan が正常に継承できなかった（ただし，コンパイル・実行（方法1）で行う場合は正常にコンパイル・実行できる）．
  - 同じクラス名で異なる実装のものを作らないようにする

### ~~java課題のディレクトリ構造をどうするか~~（解決)
- ↓の`src/java02`方式で実施する予定．つまり授業回ごとにフォルダを作成する．また，パッケージはやらず，授業回が変わっても同じクラス名は使わない．
- 現在は.classpathを以下のように指定している．
  - srcとoutputを指定しないと，ソースコードの自動チェックが走らない(Intellisenseとかも働かない）
  - つまり，*.javaファイルを置くディレクトリごとにsrcの指定が必要
```xml
<?xml version="1.0" encoding="UTF-8"?>
<classpath>
	<classpathentry kind="src" path="src/java01/ex01"/>
	<classpathentry kind="src" path="src/java01/ex02"/>
	<classpathentry kind="src" path="src/java02"/>
	<classpathentry kind="con" path="org.eclipse.jdt.launching.JRE_CONTAINER/org.eclipse.jdt.internal.debug.ui.launcher.StandardVMType/JavaSE-1.8"/>
	<classpathentry kind="output" path="bin"/>
</classpath>
```
- packageの概念を教えてsrcフォルダのみをsrcにし，java01.ex01.Hello を実行させることも可能だが，その場合は「.vscode\launch.json」で別の問題が発生する．
- 現状のlaunch.jsonファイル(抜粋)は以下のとおり
  - mainClassを現在開いているファイルのファイル名から拡張子を取り除いたもの(```${fileBasenameNoExtension}```)を自動的に取得して設定するようになっている
  - packageを利用する場合，mainClassの設定を「java01.ex01.Hello」のように明示的に書く必要がある．
    - 課題が進むごとに書き換えないといけなくなるので現実的ではない
```javascript
    "configurations": [
        {
            "type": "java",
            "name": "Debug (Launch)",
            "request": "launch",
            "mainClass": "${fileBasenameNoExtension}",
            "args": "",
            "preLaunchTask": "Compile Java4",
            "encoding": "UTF-8",
            "classPaths": [
                "${workspaceRoot}\\bin"
            ]
        }
    ]
```
- 現実的な案としてはjava2017というようなフォルダ（.vscodeがある）を1つ作り，その下に全14回の回ごとのフォルダを1つずつ作り，課題ごとにはフォルダを作らないようにするものが考えられる．classpathentryは「src/java01」「src/java02」といった単位で設定しておく．また，packageを教えない場合は全課題を通じて，同一クラス名で仕様が異なるクラスを作成しないようにする必要がある．
  - packageを教える場合は「java2017」フォルダは用意せず，「java01」フォルダ，「java02」フォルダ，のように回ごとに.vscodeを含むフォルダを配布し，毎回設定を変えることで対応し，かつ，課題ごとにmainClassを書き換える必要がある．

### ~~初回起動時に統合ターミナルがbashにならない~~(一応解決)
- 初回に起動した際に，`表示->統合ターミナル`を選択すると，settings.jsonにbash.exeで統合ターミナルを開くよう設定しているにも関わらず，powershellが起動する(Win10の場合)．
- さらに，`shell:"C://oit/.../bash.exe"(ワークスペースの設定として定義されている）をターミナルで起動することを許可しますか？`というメッセージが表示される
- 下記のデバッガと同様に初回起動時はbash.exeでの起動が認識されていない（許可が必要）
- 上記の許可しますか？のメッセージを対象に`Allow`を選択し，Visual Studio Codeを再起動すればOK．

### ~~初回起動時にデバッガが認識されない~~(一応解決)
- 初回に起動し，ソースが含まれたフォルダを選択してから方法2でコンパイル・デバッグを行うと`デバッグアダプター'{0}'の実行可能ファイルを判別できません`と表示される
- すべての設定をソースが含まれたフォルダ内に置いているため，Java拡張機能が起動時にjava.homeの場所を認識できていないと推測される．
- デバッガあるいはvscode本体を再起動すればOK

### ~~初回起動時に方法2でビルドを行うと失敗する~~（解決）
- Ctr+Shit+bなどでtask.jsonに定義されたビルドタスクを実施すると`binフォルダがない`と言われて失敗する
- 下記コマンドで`-d`オプションで指定している出力先フォルダが存在しないため
  - `javac.exe -encoding utf-8 -source 1.8 -target 1.8 -cp bin -d C:\oit_tmp\javatest_local\bin -sourcepath C:\oit_tmp\javatest_local\src\java01\ex01 C:\oit_tmp\javatest_local\src\java01\ex01\Hello.java`
- jdtも初回起動時には実施されない（java.homeが認識されないため)ので，自動生成もされない
- ソースフォルダ配布時にbinフォルダを付与して配布すればOK

### ~~日本語出力が文字化けする（コンパイル・実行（方法2)時）~~(解決)
- 恐らくJDTによる自動コンパイルがShift-jisで行われているため
- 手動でビルドしたときはutf8でビルドしているため，文字化けは起こらない．
- 実行時に手動でビルドした直後であれば文字化けは起きないが，修正した後にJDTが自動ビルドした状態で，ビルドせずに実行すると修正したクラスについてだけ（要するにJDTでビルドされたクラスファイルの方は）文字化けが起きる．
- settings.jsonに`"java.jdt.ls.vmargs": "-Dfile.encoding=UTF-8"`を追加したらいけたっぽい

### ~~デバッガ利用時の不具合~~(解決)
- 方法2でコンパイル・実行をした場合，デバッガ実行時にコンパイルエラー・ランタイムエラーが発生すると，該当のファイルへのリンクがデバッガ出力に表示されるが，そのリンクが間違っており，ファイルが開けない
- __VScodeのバージョンをUP(1.16.1->1.18.1)したら治ったっぽい__
