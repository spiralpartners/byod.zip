# BYOD下におけるプログラミング演習環境の構築
## リポジトリの内容
- このリポジトリの内容はBYOD下でのプログラミング演習環境の一部である
- 学生が解く課題のサンプルを保存するフォルダとPortableGitの変更内容をリポジトリで管理する
- 想定するプログラミング演習の言語はJava
- 実行環境はJDK＋PortableGit+Visual Studio Code．
  - [Release](https://github.com/spiralpartners/byod.zip/releases) でDLできる．
  - PortableGitについての設定方法や課題は下記参照のこと
    - https://github.com/spiralpartners/byod.zip/tree/master/PortableGit

## 演習環境の準備
### vscode, jdk, portablegit
- [Release](https://github.com/spiralpartners/byod.zip/releases) にあるbyod_v1.X.zipをC:\に展開する
  - ファイル解凍後のフォルダ構成は以下のとおり(X,Y,Zにはバージョン番号が入る)
```
C:\byod\VSCodePortable_1.XX.Y\App
C:\byod\VSCodePortable_1.XX.Y\Data
C:\byod\VSCodePortable_1.XX.Y\Other
C:\byod\VSCodePortable_1.XX.Y\Help.html
C:\byod\VSCodePortable_1.XX.Y\VSCodePortable.exe
:
C:\byod\java1.8_XXX\App
C:\byod\java1.8_XXX\bin
C:\byod\java1.8_XXX\Data
C:\byod\java1.8_XXX\db
C:\byod\java1.8_XXX\include
C:\byod\java1.8_XXX\jre
:
C:\byod\PortableGit2.XX.YY.Z-64\bin
C:\byod\PortableGit2.XX.YY.Z-64\cmd
:
C:\byod\PortableGit2.XX.YY.Z-64\git-bash.exe
\
```

### java_kadai
- `PortableGit2.XX.YY.Z-64\git-bash.exe`を実行すると$HOMEフォルダとして`C:\Users\ユーザ名\byod_home`が生成される．この下に`kadai`フォルダを作成し，本リポジトリのjava_kadaiフォルダを以下のようになるように展開する．
```
C:\Users\ユーザ名\byod_home\kadai\java_kadai\src
C:\Users\ユーザ名\byod_home\kadai\java_kadai\bin
```
- java_kadai/.vscodeフォルダ内のsettings.json, tasks.json, launch.jsonファイルがすべての設定ファイル

## 開発(学生の立場から)の流れ
### 準備(公開鍵sshの設定)
- `PortableGit2.XX.YY.Z-64\git-bash.exe`を実行し，「initssh」コマンドを実行する
  - $HOMEフォルダとして`C:\Users\ユーザ名\byod_home`が生成される．
  - sshの公開鍵が登録され，ID/Pass認証ではなく公開鍵認証方式でo-vncにアクセスできるようになる
  - .sshフォルダと公開鍵・秘密鍵はbyod_homeフォルダ内部に作成される
  - $HOME/.ssh フォルダが既に作成されている場合はinitsshコマンドを実行する必要はない（他の授業と共有．なお，$HOMEの場所は変更の可能性あり）
  - 下記画像のように`成功しました`と表示されればOK.

<img src="https://github.com/spiralpartners/byod.zip/blob/images/images/initssh.png?raw=true" width=500>

### Javaファイルの編集・コンパイル・実行
#### vscodeの起動及びファイル編集
- `C:\byod\VSCodePortable_1.XX.1\VSCodePortable.exe`を起動する
- ファイル->フォルダを開く->「java_kadai」フォルダを指定する
- 「src\java01\ex01\Hello.java」を開いて適当に編集する

#### コンパイル・実行方法(方法1)
- Hello.javaを開いた状態で，表示->統合ターミナル（PortableGitのbash.exeが起動する）
- $HOMEにいる状態でターミナルが開くので，`cd kadai/java_kadai/src/java01/`と実行する
- `javac -encoding utf8 Hello.java`と実行する
- 正常にコンパイルができ，classファイルができたら，`java Hello`と実行すると結果が出力される

#### コンパイル・実行方法(方法2)
- Hello.javaを開いた状態で，デバッグ->デバッグを開始，を選択する
- コンパイルがターミナルで行われ，実行結果がデバッグコンソールに表示される
  - breakpointを指定したデバッグ等も可能

### 出席・課題提出方法
- `PortableGit2.XX.YY.Z-64\git-bash.exe`を実行し，「shusseki」コマンドを実行する
- 下記のように~/kadai がsyncされればOK．
<img src="https://github.com/spiralpartners/byod.zip/blob/images/images/shusseki.png?raw=true" width=500>

## 今後の課題
### 全ディレクトリ構成をどうするか
- jdk,vscode,portablegit等のバイナリを置くディレクトリとjavakadaiを置くディレクトリの場所をどうするか.
- 他の授業との兼ね合いや年度進行（再履修含む）にどう対応するかも検討する必要あり．
### デフォルト文字エンコードをどうするか
- vs codeのデフォはutf-8だが，その場合winで`javac -encoding utf-8`を毎回つける必要がある
  - 参考 http://kyouichisato.blogspot.jp/2015/06/visual-studio-code-jis.html
  - shiftjisの場合は何もつけなくて良いが，課題チェックシステム等のアプリで表示する際にutf-8で統一しておいたほうが多分実装が楽．

### シェルをどうするか
- PortableGit(Bash)を導入するとbash.exeが利用できる（250MB程度必要）
  - javacへのPATHをbash.bashrcを用いてPortableな形で通せる
- Powershell, cmd.exeも可能
  - Windows10だとデフォがpowershell, それ以前だとcmdになる
  - javacへのPATHをPortableな形で通せなくなる（Windowsのシステム環境変数かユーザ環境変数で指定する必要あり）->他の環境と競合を起こす．

### ファイル編集時にextends元のクラスが間違っており，エラーが検出されることがある
- extends元のクラスと同じ名前のクラスを別の課題で作成していた場合に，そちらを参照してしまうことがある
- 原因は下にもあるディレクトリ構造とpackageの問題．JDTによる自動出力先の設定がbinになっているため，packageの指定がないと，同一クラス名は上書きされてしまう．
  - 本リポジトリにあるようにjava02とjava01\ex02で両方共PacMan classを定義したところ，java02のNoizyPacMan extends PacMan が正常に継承できなかった（ただし，コンパイル・実行（方法1）で行う場合は正常にコンパイル・実行できる）．

### 課題提出方法をどうするか
- 学生が課題を解いている過程をリアルタイムにチェックできるような課題提出方法が望ましい．
- 方法1: 学生にネットワークドライブ上のフォルダに特定のドライブレターを割り当てさせる．その中に学生ごとのフォルダを作成し，そこに本リポジトリの内容を配置，学生ごとのvscodeから開かせる
  - メリット：フォルダを教員が任意のタイミングで配布できる．
  - デメリット：ファイル削除の際にゴミ箱に保存できないので一々システムからのアラートがでる．VPNで接続したときにだいぶ重いことがある（ネットワークによってはファイル書き込みに数秒かかる）．教員側のLinuxからアクセスするのが困難．
- 方法2: rsyncコマンドと本リポジトリの内容を配布し，サーバ側の学生のホームディレクトリ下にrsyncさせる（コマンドは既にある）
  - メリット：教員側から見ると，現状の演習環境と同一に見える（学生のホームディレクトリ下の特定ディレクトリにアクセスすると学生のコードが見える）．
  - デメリット：rsyncコマンドを学生が実施するのを忘れると提出がまったくできないことになる．学生から見たときに何が提出されているかを確認することが難しい(定期的にrsyncを実行する関係上，最新がUPLOADされているかが分からない)．rsyncが何らかの不具合で学生環境において正常に実行できないと詰む詰む（方法1もこの点では同様）．

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
- さらに，`shell:"C://byod/.../bash.exe"(ワークスペースの設定として定義されている）をターミナルで起動することを許可しますか？`というメッセージが表示される
- 下記のデバッガと同様に初回起動時はbash.exeでの起動が認識されていない（許可が必要）
- 上記の許可しますか？のメッセージを対象に`Allow`を選択し，Visual Studio Codeを再起動すればOK．

### ~~初回起動時にデバッガが認識されない~~(一応解決)
- 初回に起動し，ソースが含まれたフォルダを選択してから方法2でコンパイル・デバッグを行うと`デバッグアダプター'{0}'の実行可能ファイルを判別できません`と表示される
- すべての設定をソースが含まれたフォルダ内に置いているため，Java拡張機能が起動時にjava.homeの場所を認識できていないと推測される．
- デバッガあるいはvscode本体を再起動すればOK

### ~~初回起動時に方法2でビルドを行うと失敗する~~（解決）
- Ctr+Shit+bなどでtask.jsonに定義されたビルドタスクを実施すると`binフォルダがない`と言われて失敗する
- 下記コマンドで`-d`オプションで指定している出力先フォルダが存在しないため
  - `javac.exe -encoding utf-8 -source 1.8 -target 1.8 -cp bin -d c:\byod_tmp\javatest_local\bin -sourcepath c:\byod_tmp\javatest_local\src\java01\ex01 c:\byod_tmp\javatest_local\src\java01\ex01\Hello.java`
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



## VS codeセットアップ詳細
### Step1. VSCodePortable Updaterのセットアップ
- [UpdateManagerつきVSCodePortable](https://github.com/LightDestory/vscode-portable/archive/master.zip)をダウンロードし，VSCodePortableフォルダ以下をc:\byodに展開する．

### Step2. VSCodePortableの最新へのUpdate
- VSCodePortable.exe を実行するとUpdateManagerが起動するので，最新へのUpdateを実施する．

### Step3. 配布用にUpdateの停止
- App\AppInfo\Launcher\VSCodePortable.ini を下記を参考に，UpdateManager.exeを利用しないように変更する
  - https://github.com/garethflowers/vscode-portable/pull/33/commits/9948ec6ba287f789b430e67b2c4397aed2e80375
```
[Launch]
ProgramExecutable=VSCode\code.exe
ProgramExecutable64=VSCode64\code.exe
CommandLineArguments='--user-data-dir="%PAL:DataDir%\code" --extensionHomePath="%PAL:DataDir%\code\extensions"'
DirectoryMoveOK=yes
DisableSplashScreen=true
SplashTime=0
SupportsUNC=yes
MinOS=7

[Environment]
VSCODE_HOME=%PAL:DataDir%\code

[DirectoriesCleanupIfEmpty]
1=%APPDATA%\Code
2=%USERPROFILE%\.vscode\extensions
3=%USERPROFILE%\.vscode
```
- App\UpdateManager.exe を削除する
  - 削除しないとアンチウィルスにひっかかることがある

### Step4. 以下の2つをc:\byod以下に追加インストール
- jdk portable (x64)
  - https://portableapps.com/apps/utilities/jdkportable
- PortableGit(x64)（解凍するだけ）
  - [PortableGit-2.xx.x.xx-64-bit.7z.exe](https://github.com/git-for-windows/git/releases)

### Step5. 拡張機能の追加
- Java Extention Pack
  - Java Language SupportとDebugger for Javaのセット
- Project Manager
  - 複数のフォルダを管理するための拡張機能

### Step6. 不要なフォルダを削除
-「C:\byod\VSCodePortable_1.XX.1\Data\code\」以下のextensions以外のフォルダをすべて削除
  - ただし，「C:\byod\VSCodePortable_1.XX.1\Data\code\extensions\redhat.java-0.14.0\server\config_win」以下にキャッシュができる場合があるので注意
- 「C:\byod\VSCodePortable_1.XX.1\Data\cache\, settings\, Temp\」を削除

### Step7. 演習フォルダ(本リポジトリ)のセットアップ
- .vscode以下のlaunch.json, tasks.json, settings.json
- フォルダルートにある.classpath, .project
- 以上のファイルの設定は本リポジトリ参照のこと

### Step8. シェルのセットアップ
- jdkのbinへのpath設定が必要．WindowsのPATH環境変数の設定はいじりたくないので，シェル起動時に追加するようにする
- bash.exeの場合は$PorableGit\etcのbash.bashrcに`export PATH=/c/byod/java1.8_152/bin/:$PATH`を追加し，bash.exeをvs codeの統合ターミナルとして呼び出すようにする(settings.json)．
