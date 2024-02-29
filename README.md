# 職業訓練のポートフォリオ
## 概要
ハンドメイドの在庫管理を行うアプリケーションです。
機能は下記の通り
- ログイン追加
- データの悦単
- データの削除、更新、追加

## 使用環境
下記の通り
- Windows10
- eclipse2022
- java 17
- tomcat 9
- H2DB



## 動作について
H2DBの以下のSQL文を実行しないと動きません。

CREATE TABLE ACCOUNTS (
    USER_ID VARCHAR(255) NOT NULL,
    PASS VARCHAR(255) NOT NULL,
    MAIL VARCHAR(255) NOT NULL,
    NAME VARCHAR(255) NOT NULL,
    PRIMARY KEY (USER_ID)
);

INSERT INTO ACCOUNTS (USER_ID, PASS, MAIL, NAME) 
VALUES ('testuser', 'password', 'user123@example.com', 'John Doe');

CREATE TABLE PRODUCTS (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    NAME VARCHAR(255) NOT NULL,
    PRICE INT NOT NULL,
    IMAGE_URL VARCHAR(255) NOT NULL,
    USER_NAME VARCHAR(255) NOT NULL,
    BUYER VARCHAR(255) NOT NULL,
    STOCK VARCHAR(255) NOT NULL
);
