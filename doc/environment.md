# 環境定義

## ディレクトリ、ファイル定義

* `/mnt/data/opt/diy-book-shelf/bin/diy-book-shelf-server-*.*.*.jar`
    * アプリケーション実行ファイルの実体。
* `/mnt/data/opt/diy-book-shelf/bin/diy-book-shelf-server.conf`
    * アプリケーション外部設定ファイル。サービス起動時に読み込まれる。環境変数を定義する。
* `/mnt/data/opt/diy-book-shelf/git/`
    * gitリポジトリ。ここからソースコードを取得してビルドを行う。
* `/mnt/data/var/log/diy-book-shelf/*.log`
    * ログ・ファイル。

## データベース定義

* ミドルウェア
    * PostgreSQL
    * OSパッケージ管理システムでインストールする。
* データベース名(JDBC接続文字列)
    * 本番: `jdbc:postgresql://localhost:5432/diy_book_shelf_db?charSet=UTF8`
    * ステージング: `jdbc:postgresql://localhost:5432/diy_book_shelf_stg_db?charSet=UTF8`
* ユーザー名、パスワード
    * LastPassを参照

## 環境変数(実行時)

* `DBS_DATASOURCE_URL`: JDBC接続文字列。
* `DBS_DATASOURCE_USERNAME`: データベース・ユーザー。
* `DBS_DATASOURCE_PASSWORD`: データベース・パスワード。
* `LOG_FOLDER`: ログ出力先フォルダー。

## OSユーザー

* diy-book-shelf用のOSユーザーを作成する。

> *TODO:* とりあえずrootで運用する。

## 初回構築手順

### 各種ディレクトリを作成

```
sudo mkdir /mnt/data/opt/diy-book-shelf/
sudo mkdir /mnt/data/opt/diy-book-shelf/bin/
sudo mkdir /mnt/data/var/log/diy-book-shelf/
```

### git clone

```
cd /mnt/data/opt/diy-book-shelf/
git clone git@github.com:u6k/diy-book-shelf.git git
```

### PostgreSQL構築

PostgreSQLはパッケージャーでインストールする。rootでログインして、以下のコマンドを実行する。`diy_book_shelf_user`、`diy_book_shelf_pass`は、本番用の値に読み替える。

```
create user diy_book_shelf_user with password 'diy_book_shelf_pass';
create database diy_book_shelf_db owner diy_book_shelf_user encoding 'UTF8';
```

同様に、ステージング用にも構築する。

### サービス登録

```
sudo ln -sf /mnt/data/opt/diy-book-shelf/bin/diy-book-shelf-server-*.*.*.jar /etc/init.d/diy-book-shelf-server
sudo chkconfig --add diy-book-shelf-server
```

設定ファイルをコピーして、内容を変更する。

```
cp /mnt/data/opt/diy-book-shelf/git/diy-book-shelf-server/src/release/conf/diy-book-shelf-server.conf /mnt/data/opt/diy-book-shelf/bin/
vim /mnt/data/opt/diy-book-shelf/bin/diy-book-shelf-server.conf
```
