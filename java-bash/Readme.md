# bash.exeを呼び出すgoプログラム
## ビルド環境設定
- msys2 portableをダウンロードして展開
  - https://sourceforge.net/projects/mwayne/files/MSYS2Portable/
- golang環境を導入
  - 参考：http://takaya030.hatenablog.com/entry/2018/01/18/230105
    - GOPATHの設定が必要と書いてあるが，なくても動いた
- icon作成環境の導入
  - .icoファイルを用意してrsrcをbuild
    - 参考：http://blog.y-yuki.net/entry/2017/04/22/000000

## ビルド
- ↑でビルドしたrsrc.exeを*.goファイルと同じディレクトリに置き，`build_java-bash.sh`を実行する
