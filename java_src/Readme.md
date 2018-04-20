# Java演習環境用VS code設定
- 演習時にはjava_srcフォルダをjava2018フォルダ等に改名し，学生に配布する．学生はvscodeで当該フォルダを開くことでソースコードの編集やコンパイルができる．

## 構成
- .vscode
  - settings.json
    - vscodeの起動設定
  - launch.json
    - デバッガの起動設定
  - tasks.json
    - ビルドコマンドの設定
- bin\output.txt
  - vscodeでコンパイル時のclassファイルを出力する場所．.classpathで指定されている．output.txtはvscode利用時には自動削除される
- java01
  - 課題のソースファイルを置く箇所．.classpathファイルで指定する．授業の回ごとにjava01,java02,...とフォルダを作成していく予定
- .classpath
  - Intellisense等のためのclasspathを設定する．src,output,con(コンパイラ)を指定する．最終的にはsrcとしてjava01~java14を指定する必要あり．
- .project
  - project name等を指定する．現在はjavaなのでjava2018等にしたほうが良いかもしれない．

### Step7. 演習フォルダ(本リポジトリ)のセットアップ
- .vscode以下のlaunch.json, tasks.json, settings.json
- フォルダルートにある.classpath, .project
- 以上のファイルの設定は本リポジトリ`java_src`参照のこと
- https://qiita.com/yumetodo/items/42132a1e8435504448aa