# BYOD下におけるプログラミング演習環境の構築
## リポジトリの内容
- このリポジトリの内容はBYOD下でのプログラミング演習環境の一部である
- 学生が解く課題を保存するフォルダのみをリポジトリで管理する
- 想定するプログラミング演習の言語はJava
- 実行環境はJDK＋PortableGit+Visual Studio Code．
  - [Release](https://github.com/spiralpartners/byod.zip/releases) でDLできる．

## 演習環境の準備
- [Release](https://github.com/spiralpartners/byod.zip/releases) にあるbyod_v1.1.zipをC:\に展開する
  - ファイル解凍後のフォルダ構成は以下のとおり
```
C:\byod\VSCodePortable_1.16.1\App
C:\byod\VSCodePortable_1.16.1\Data
C:\byod\VSCodePortable_1.16.1\Other
C:\byod\VSCodePortable_1.16.1\Help.html
C:\byod\VSCodePortable_1.16.1\VSCodePortable.exe
:
C:\byod\java1.8_152\App
C:\byod\java1.8_152\bin
C:\byod\java1.8_152\Data
C:\byod\java1.8_152\db
C:\byod\java1.8_152\include
C:\byod\java1.8_152\jre
:
C:\byod\PortableGit\bin
C:\byod\PortableGit\cmd
:
C:\byod\PortableGit\git-bash.exe
\
```

- 任意のフォルダを作成し，本リポジトリの内容を展開する（どこでも良い）
  - c:\byod以下にjavatestという名前のフォルダとして解凍すると以下のようなフォルダ構成になる
```
c:\byod\javatest\src
c:\byod\javatest\bin
```
- .vscodeフォルダ内のsettings.json, tasks.json, launch.jsonファイルがすべての設定ファイル
- [OIT限定]将来的にはネットワークドライブ上に（恐らく）置くことになるので，現時点では\\o-file01.ad.oit.ac.jp\post\IS科専門以下の教員フォルダに置いてテストできると良いかも．
  - その場合，「\\o-file01.ad.oit.ac.jp\post\IS科専門\教員名」を例えばS:\などに割り当てることが望ましい

## 開発(学生の立場から)の流れ
### ファイル編集
- C:\byod\VSCodePortable_1.16.1\VSCodePortable.exeを起動する
- ファイル->フォルダを開く->「javatest_local」フォルダを指定する
- 「src\java01\ex01\Hello.java」を開いて適当に編集する

### コンパイル・実行(方法1)
- Hello.javaを開いた状態で，表示->統合ターミナル（PortableGitのbash.exeが起動する）
- フォルダルートにいる状態でターミナルが開くので，`cd src/java01/ex01/`と実行する
- `javac -encoding utf8 Hello.java Test2.java`と実行する
- 正常にコンパイルができ，classファイルができたら，`java Hello`と実行すると結果が出力される

### コンパイル・実行(方法2)
- Hello.javaを開いた状態で，デバッグ->デバッグを開始，を選択する
- コンパイルがターミナルで行われ，実行結果がデバッグコンソールに表示される
  - breakpointを指定したデバッグ等も可能

## 今後の課題
### デフォルト文字エンコードをどうするか
- vs codeのデフォはutf-8だが，その場合winで`javac -encoding utf-8`を毎回つける必要がある
  - 参考 http://kyouichisato.blogspot.jp/2015/06/visual-studio-code-jis.html

### シェルをどうするか
- PortableGit(Bash)を導入するとbash.exeが利用できる（250MB程度必要）
- Powershell, cmd.exeも可能
  - Windows10だとデフォがpowershell, それ以前だとcmdになる
- Bashの場合，ホームディレクトリをどう指定するかが問題(.bashrcその他の設定ファイルで別途指定する)．powershell,cmdの場合はプロジェクトのルートが自動で開くようになってるっぽい

### ファイル編集時にextends元のクラスが間違っており，エラーが検出されることがある
- extends元のクラスと同じ名前のクラスを別の課題で作成していた場合に，そちらを参照してしまうことがある
- 原因は下にもあるディレクトリ構造とpackageの問題．JDTによる自動出力先の設定がbinになっているため，packageの指定がないと，同一クラス名は上書きされてしまう．
  - 本リポジトリにあるようにjava02とjava01\ex02で両方共PacMan classを定義したところ，java02のNoizyPacMan extends PacMan が正常に継承できなかった（ただし，コンパイル・実行（方法1）で行う場合は正常にコンパイル・実行できる）．

### ディレクトリ構造をどうするか
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
-「C:\byod\VSCodePortable_1.18.1\Data\code\」以下のextensions以外のフォルダをすべて削除
  - ただし，「C:\byod\VSCodePortable_1.16.1\Data\code\extensions\redhat.java-0.14.0\server\config_win」以下にキャッシュができる場合があるので注意

### Step7. 演習フォルダ(本リポジトリ)のセットアップ
- .vscode以下のlaunch.json, tasks.json, settings.json
- フォルダルートにある.classpath, .project
- 以上のファイルの設定は本リポジトリ参照のこと

### Step8. シェルのセットアップ
- jdkのbinへのpath設定が必要．WindowsのPATH環境変数の設定はいじりたくないので，シェル起動時に追加するようにする
- bash.exeの場合は$PorableGit\etcのbash.bashrcに`export PATH=/c/byod/java1.8_152/bin/:$PATH`を追加し，bash.exeをvs codeの統合ターミナルとして呼び出すようにする(settings.json)．
