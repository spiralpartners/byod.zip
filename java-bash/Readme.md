# bash.exeを呼び出すgoプログラム
## ビルド環境設定
- msys2 portableをダウンロードして展開
  - https://sourceforge.net/projects/mwayne/files/MSYS2Portable/
- golang環境を導入
  - 参考：http://takaya030.hatenablog.com/entry/2018/01/18/230105
```
$ pacman -S mingw-w64-x86_64-go
$ pacman -S git
```
    - GOPATHの設定が必要と書いてあるが，なくても動いた
- icon作成環境の導入
  - .icoファイルを用意してrsrcをbuild
    - 参考：http://blog.y-yuki.net/entry/2017/04/22/000000
```
$ go get -v github.com/akavel/rsrc
$ cd %GOPATH%/src/github.com/akavel/rsrc
$ go build
```

## ビルド
- ↑でビルドしたrsrc.exeを*.goファイルと同じディレクトリに置き，msys2の環境で `build_java-bash.sh`を実行する
  - `sh build_java-bash.sh`
- このとき，`java-bash.go` のL15をPortableGitのバージョンに合わせて修正すること
