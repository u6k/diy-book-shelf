# テスト、ビルド、リリース、実行手順

## ソースコードを最新化

ソースコードを最新化する。同時に、ゴミファイルを削除する。

```
cd /mnt/data/opt/diy-book-shelf/git/
git clean -x -d -f
git reset --hard HEAD
git fetch
git pull origin master
cd diy-book-shelf-server/
```

## ステージング・テスト

テストを実行する。`-Dspring.datasource.url`、`-Dspring.datasource.username`、`-Dspring.datasource.password`には、ステージング環境の値を設定する。

```
mvn test package -Dspring.profiles.active=staging -Dspring.datasource.url=xxx -Dspring.datasource.username=xxx -Dspring.datasource.password=xxx
```

### ビルド

ビルドを実行する。

```
mvn package -Dmaven.test.skip=true
cp target/diy-book-shelf-server-*.*.*.jar ../../bin/
```

### リリース

```
sudo ln -sf ../../bin/diy-book-shelf-server.*.*.*.jar /etc/init.d/diy-book-shelf-server
```

### 実行手順

```
sudo service diy-book-shelf-server restart
```
