# Java演習環境用VS code設定
- /home/teachers/t2015025/public_html/progjava/ を作成する
  - `~/`から`~/public_html/progjava`までを711にしておく
- getjava.shとgetlocal.shを/home/teachers/t2015025/public_html/progjava/に配置し，各コマンドを実行する
  - getjava.shのclass設定が正しいかを確認しておく（毎年更新の必要あり）
- 上記 progjava フォルダに対して権限設定が適切であることを確認しておくこと
  - `progjava`内の`javaXX`,`local`以下のディレクトリを755
    - javaXXにはjava19等の年度情報が入る
  - `progjava`内の各ファイルは644


## 運用上のルール
- 各授業回について`lec01`等のフォルダがgetjavaコマンドで自動生成される
- `bin`フォルダにvscodeでauto buildした際のclassファイルが保存される．
- packageは授業で使わない
  - 設定ファイルでのmainメソッド指定が困難なため
- 別プログラムで同一クラス名は扱わない

## 構成
- bin\output.txt
  - vscodeでコンパイル時のclassファイルを出力する場所．.classpathで指定されている．output.txtはvscode利用時には自動削除される
- lec01
  - 課題のソースファイルを置く箇所．.classpathファイルで指定する．
- .classpath
  - Intellisense等のためのclasspathを設定する．src,output,con(コンパイラ)を指定する．授業の回ごとにjava01,java02,...とフォルダを作成していく場合，.classpathファイルの`<classpathentry kind="src" path="java01"/>`を追加するフォルダにあわせて追加する必要がある．

```
<?xml version="1.0" encoding="UTF-8"?>
<classpath>
	<classpathentry kind="src" path="java01"/>
	<classpathentry kind="con" path="org.eclipse.jdt.launching.JRE_CONTAINER/org.eclipse.jdt.internal.debug.ui.launcher.StandardVMType/JavaSE-1.8"/>
	<classpathentry kind="output" path="bin"/>
</classpath>
```

- .vscode
  - settings.json
    - vscodeの起動設定．UTF-8関連の設定とjava.homeの設定，terminalとしてbash.exeを利用する設定などを行っている．
    - とりあえずはgitも利用しないので，効かないように設定ｌ
    - 参考 https://qiita.com/yumetodo/items/42132a1e8435504448aa
      - `terminal.integrated.env.windows`は設定しなくともうまく動いているっぽい
  - tasks.json
    - log for compileというロギングを行うタスクを呼び出している．
      - logタスクは~/kadai/java18フォルダの`.java_bash_history.%Y%m`ファイルに追記される．
- launch.json
    - デバッガの起動設定．F5で呼び出して実行が行われる．log for compileタスクが事前に呼び出される設定になっている．
    - 出力先を現在はexternalTerminal(実行時に別途ウィンドウが開く)ようにしている．
      - 参考：http://www.atmarkit.co.jp/ait/articles/1709/01/news034_3.html
- .editorconfig
  - javaの自動整形用設定ファイル
