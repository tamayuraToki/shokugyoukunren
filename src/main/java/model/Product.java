package model;

import java.io.Serializable;

public class Product implements Serializable {
    private int productId;
    private String productName;
    private int price;
    private String imageUrl;
    
    public Product() {}

    public Product(int productId, String productName, int price, String imageUrl) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.imageUrl = imageUrl;
    }
    
    //データ新規登録用
    public Product(String productName, int price, String imageUrl) {
        this.productName = productName;
        this.price = price;
        this.imageUrl = imageUrl;
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
    
}
