package model;


import java.util.List;

import dao.ProductsDAO;

public class ProductService {
	List<Product> productList;

	
    // 在庫リストを取得するメソッド
    public List<Product> getProducts(String userId) {
    	ProductsDAO dao = new ProductsDAO();
    	productList = dao.findAll(userId);
        return productList;
    }

    // 在庫IDに基づいて在庫情報を取得するメソッド
    public Product getProductById(int productId) {

    	ProductsDAO dao = new ProductsDAO();
    	Product product = dao.find(productId);
        return product;
    }
    
    //在庫のデータを追加するメソッド
    public boolean insertProduct(Product product,String userId) {
    	ProductsDAO dao = new ProductsDAO();
    	return dao.create(product,userId);
    
    }
    
    //在庫情報更新用メソッド
    public boolean updateProduct(Product product) {
    	ProductsDAO dao = new ProductsDAO();
    	return dao.update(product);
    }
    
    //在庫情報更新用メソッド
    public boolean deleteProduct(int productId) {
    	ProductsDAO dao = new ProductsDAO();
    	return dao.delete(productId);
    }
    
}
