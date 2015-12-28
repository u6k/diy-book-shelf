デプロイ手順
============

* `mvn package:package`でjarファイルを作成する。
* `scp`で対象ノードにコピーする。
* `/var1/opt/diy-book-shelf/`に移動する。
* `screen`で別セッションを起動する。
* `java -jar diy-book-shelf-server-xxx.jar`で起動する。
