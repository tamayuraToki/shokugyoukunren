package model;

import java.io.Serializable;

public class Product implements Serializable {
    private int productId;
    private String productName;
    private int price;
    private String imageUrl;
    private String buyer;
    private String stock;
    
    public Product() {}

    //メイン画面表示用
    public Product(int productId, String productName, int price, String imageUrl) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.imageUrl = imageUrl;
    }
    
    //詳細データ表示用
    public Product(int productId, String productName, int price, String imageUrl
    		,String buyer,String stock) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.imageUrl = imageUrl;
        this.buyer = buyer;
        this.stock = stock;
    }
    
    //データ新規登録用
    public Product(String productName, int price, String imageUrl, String buyer, String stock) {
        this.productName = productName;
        this.price = price;
        this.imageUrl = imageUrl;
        this.buyer = buyer;
        this.stock = stock;
    }

    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public int getPrice() {
        return price;
    }

    public String getImageUrl() {
        return imageUrl;
    }
    
    public String getBuyer() {
    	return buyer;
    }
    public String getStock(){
    	return stock;
    }
    
}
