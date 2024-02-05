package model;


import java.util.List;

import dao.ProductsDAO;

public class ProductService {
	List<Product> productList;

	
    // 在庫リストを取得するメソッド
    public List<Product> getProducts(User user) {
    	ProductsDAO dao = new ProductsDAO();
    	productList = dao.findAll(user.getName());
        return productList;
    }

    // 在庫IDに基づいて在庫情報を取得するメソッド
    public Product getProductById(int productId) {

    	ProductsDAO dao = new ProductsDAO();
    	Product product = dao.find(productId);
        return product;
    }
    
    //在庫のデータを追加するメソッド
    public boolean insertProduct(Product product,User user) {
    	ProductsDAO dao = new ProductsDAO();
    	return dao.create(product,user.getName());
    
    }
}
