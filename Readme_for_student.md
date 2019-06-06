# **注意！！**
- アンチウィルスソフトがインストール時及び実行時に悪影響を及ぼすことがあります．Java演習環境のインストールがうまくいかない場合にはアンチウィルスソフトの動作状況を確認してください．
- 特に**Avast**(アバスト)は重要なファイルを無断で削除することが確認されていますので，自分のノートPCにインストールされている場合はアンインストールしておいてください．
  - アンインストール後はWindowsのデフォルトのアンチウィルスソフト等を起動するようにしておいてください．
- 自宅など，学外で実施する場合には必ずVPN接続を行ってからセットアップ等を実施しましょう．
  - [学外から学内ネットワークを利用する方法について（学内専用）](http://www.oit.ac.jp/japanese/center/portal/share/pdf/Anyconnect-VPN.pdf)

# 演習環境の準備
- 下記インストール，セットアップ，テスト課題及びアンケートの回答は必ず授業開始前にすべて実行しておきましょう．
- 予習課題の一環として，下記すべてが授業開始前に完了しているかは**成績に影響します**．
- なお，何らかの不具合でインストール等が正常に完了できなかった場合，アンケートで不具合を報告してください．教員からメールで連絡します．

## vscode, jdk, portablegitのインストール
- [java_archive(http://bit.ly/oitjava)](http://bit.ly/oitjava) にあるoit-javaYYYYMMDD.exeの最新版をC:\oitにダウンロードし，実行（展開）しましょう．
   - 大学の組織アカウントでログインしないとダウンロードできません．
      - 組織アカウントとはWebmailなどにログインする際に利用するe1X18YYY@oit.ac.jp (メールアドレスではありません）のようなアカウントのことです．
      - わからない人は[基礎情報処理I 補足資料p.14](http://www.oit.ac.jp/japanese/center/inside/portal/share/pdf/info_manual.pdf)を参照してください．
   - C:\oitフォルダがない場合は自分で作成しましょう
   - 自己解凍ファイル展開後のフォルダ構成は以下のようになります(一部省略．また，X,Y,Zにはバージョン番号が入ります)．
```
C:\oit\vscode-portable-win64-1.XX.Y-Z\app
C:\oit\vscode-portable-win64-1.XX.Y-Z\data
C:\oit\vscode-portable-win64-1.XX.Y-Z\vscode-portable.exe
:
C:\oit\openjdk1.8.0.XXX\bin
C:\oit\openjdk1.8.0.XXX\include
C:\oit\openjdk1.8.0.XXX\jre
C:\oit\openjdk1.8.0.XXX\lib
:
C:\oit\PortableGit-2.XX.Y-64\bin
C:\oit\PortableGit-2.XX.Y-64\cmd
C:\oit\PortableGit-2.XX.Y-64\git-bash.exe
:
C:\oit\java-bash-2.XX.Y-64.exe
```

## 演習環境のセットアップ
1. `C:\oit\java-bash-2.XX.Z-64.exe`を実行します．
   - 初回起動時には`C:\Users\{ユーザ名}\oithomes\java\kadai\javaYY\`フォルダが生成されます（javaYYのYYにはその年の末尾2桁が入ります．2019であればjava19に，2020であればjava20になります）．
   - $HOMEフォルダとして`C:\Users\{ユーザ名}\oithomes\java`がセットされます．
1. bashターミナルが起動するので，下記のように`initssh`と入力し，Enter
   - まずe1で始まる自分のユーザ名を入力します．
   - 画面に書かれているように，「Enter file in..」「Enter passphrase」「Enter same passphrase again」と言われたらEnterキーを押しましょう．
   - 最後に演習室へのログインパスワードを入力し，Enter
   - 成功しましたと表示されればOK．表示されなかった場合は再度`initssh`を実行しましょう．
   <img src="https://drive.google.com/uc?export=view&id=1vzB_5m0c5hclx5Sy5LC0Oruq94PER4pd" width=600>
1. `initssh`実行後に，`getlocal`コマンドを入力し，Enter
   - 演習で利用する様々なコマンドが最新のものに更新されます（課題提出コマンド等）．なお，このときにもしログインIDとパスワードの入力を求められた場合，`initssh`が正常に完了できていません．再度`initssh`コマンドを実行してください(以降のgetjavaやshussekiコマンドでも同じです)．
   <img src="https://drive.google.com/uc?export=view&id=1B0qywWtvT51INzbmS-x6LjbiCV9iCrsZ" width=500>
1. `initssh`及び`getlocal`実行後に，`getjava`コマンドを入力し，Enter
   - `$HOME\kadai\javaYY`フォルダ内にlec01~lec14等の課題作成フォルダ及びvscodeのための設定ファイルがダウンロードされます．
   <img src="https://drive.google.com/uc?export=view&id=10XH_-9ruPWw6VDhq9WQ9HP0151Ay05OM" width=500>
1. `initssh`，`getlocal`及び`getjava`コマンド実行後に，`shusseki`コマンドを入力し，Enter
   - 画面に下記のように「rsyncは成功しました」もしくは「更新ファイルがありませんでした」と表示されればOK.
      - 表示されなかった場合はネットワークを確認しましょう．学外から接続する場合はこのページ冒頭にあるようにVPN接続を行う必要があります．
   - これらが表示されるのを確認後，Ctrlキーを押したままCボタンを押し，一度shussekiコマンドを終了します．
   <img src="https://drive.google.com/uc?export=view&id=1pSm5hoGoydyb-PPr_9Z8CIxJVr7ZPn5x" width=500>

# テスト課題の実施「課題1-1 Hello World」
## 準備
- `C:\oit\java-bash-2.XX.Z-64.exe`を実行します．
- ターミナルが起動するので，`shusseki`コマンドを実行します．
  - 下記のように「rsyncは成功しました」もしくは「更新ファイルがありませんでした」と表示されればOK．
  - 以降は10秒毎に自動的に課題が提出されるので，授業中や課題実施中は起動したままにしておきましょう．
  - 「rsyncは失敗しました．」と表示されたときはサーバに接続できていない（＝課題が提出されない）ので，必ず原因を確認すること．
   <img src="https://drive.google.com/uc?export=view&id=1pSm5hoGoydyb-PPr_9Z8CIxJVr7ZPn5x" width=500>

## vscodeの起動及びファイル編集
- `C:\oit\vscode-portable-win64-1.XX.Y-Z\vscode-portable.exe`を起動しましょう．
  - 英語モードで起動した場合は，再起動すると設定ファイルが読み込まれて日本語モードになります．
- ファイル->フォルダーを開く->「`$HOME\kadai\javaYY`」フォルダを指定して開きましょう．
  - $HOMEは`C:\Users\{各自のユーザ名}\oithomes\java`．
  - javaYYのYYには19や20等20YY年の下2桁の数字が入ります．
- 下図のように，lec01~lec14フォルダが表示されるので，lec01フォルダ（第1回課題用フォルダ）を選択し，Work11.javaという名前のファイルを新規作成しましょう．
<img src="https://drive.google.com/uc?export=view&id=1seTCRo3e6lI5Y7rblXypmwLZJHmFOq6e" width=600>

- 次に，以下のプログラムを``Work11.java``に入力し，保存しましょう．
```java
  public class Work11{
    public static void main(String[] args) {
      System.out.println("Hello World!");
    }
  }
```

- `$HOME\kadai\javaYY\lec01\Work11.java` ファイルが作成されていることを確認しましょう．

## javaプログラムのコンパイル・実行
- `C:\oit\java-bash-2.XX.Z-64.exe`を実行しましょう．
- lec01フォルダ(`cd ~/kadai/javaYY/lec01/`) に移動してから，下記を実行しましょう．
  - javaYYのYYには年度の末尾2桁が入ります．例えば，2019年度はjava19．
  - javacコマンドがjavaファイルをコンパイルし，javaコマンドがclassファイルを実行します．
```sh
$ javac Work11.java
$ java Work11
Hello World!
```
- 必ずコンパイルが正常に終了して，Work11.classが作成されているか，実行結果が上記と等しいか確認しましょう．
- 下図はWork11.javaをコンパイル・実行した例になっています．
<img src="https://drive.google.com/uc?export=view&id=1X0ErqTZD7Y9GyyrSRreP8aUM-6ylWeeM" width=500>

- なお，更新されたファイルはshussekiコマンドを実行し続けている限りサーバに自動的に提出されます．shussekiコマンドを実行しているターミナルで，ファイルのアップロードが行われているかを確認しましょう．
- Work11.javaのコンパイル・実行及びアップロードが正常に完了したら，shussekiコマンドをCtrl+Cで終了しましょう．

# アンケートへの回答
- Work11.javaのテスト課題までが完了したら，下記アンケートに回答しましょう．なお，実施はしたが不具合が発生した等で自力で解決できない場合は，上記終了前にアンケートに回答し，教員からの連絡を待ってください．
  - http://bit.ly/oitjavaintro


# 各コマンドの詳細
- 以降は各コマンドの詳細です．必ずしも読まなくても構いません．

## initssh
- initsshを実行すると，sshの公開鍵が登録され，ID/Pass認証ではなく公開鍵認証方式でo-vnc.center.oit.ac.jpにアクセスできるようになります．
- .sshフォルダと公開鍵・秘密鍵は`$HOME/.ssh`フォルダ内部に作成されます．
  - $HOMEフォルダは`C:\Users\{ユーザ名}\oithomes\java`
- $HOME/.ssh フォルダが既に作成されている場合はinitsshコマンドを実行する必要はありません．
  - Java演習環境をインストール後最初に一回実施するだけでOK.

## shusseki
- shussekiを実行すると，指定した秒数間隔で「C:\Users\{ユーザ名}\oithomes\java\kadai\javaYY\」フォルダの中身が更新されているかをチェックし，更新されていた場合に，更新されていたファイルやフォルダを各学生のLinux環境の「~/kadai/javaYY/」にアップロードされます．
- ３種類のメッセージが表示されます．
   - 「rsyncは失敗しました」：赤字で表示されます．アップロードにネットワークか何らかの要因で失敗していることを示しています．このままだと課題の提出ができないので，必ず原因を確認し，分からなければ教員に聞きましょう．
   - 「rsyncは成功しました」：緑字で表示されます．このメッセージの前にアップロードするファイルが表示されており，それらのファイルが正しくサーバに提出されたことを示しています．
   - 「更新ファイルがありませんでした」：緑字で表示されます．アップロード処理自体は行われたが，アップロード対象のファイルがなかったことを示しています．アップロード処理自体は正常に実行可能であることを示しているため，このメッセージについては気にする必要はありません．

