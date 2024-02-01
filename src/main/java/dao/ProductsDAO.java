package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Product;

public class ProductsDAO {
	//データベース接続に使用する情報
	private final String JDBC_URL = "jdbc:h2:tcp://localhost/~/HandmadeInventoryContorol";
	private final String DB_USER ="sa";
	private final String DB_PASS="";
	
	public List<Product> findAll(){
		
		List<Product> productList = new ArrayList<>();
		//JDBCドライバを読み込む
		try {
			Class.forName("org.h2.Driver");
		}catch(ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした。");
		}
		
		//データベース接続
		try(Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)){
			//SELECT文を準
			String sql = "SELECT ID,NAME,PRICE,IMAGE_URL FROM PRODUCTS ORDER BY ID ASC;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			//SELECT文を実行
			ResultSet rs = pStmt.executeQuery();
			
			//SELECT文の結果をArrayListに格納
			while(rs.next()){
				int id = rs.getInt("ID");
				String name = rs.getString("NAME");
				int price = rs.getInt("PRICE");
				String imageUrl = rs.getString("IMAGE_URL");
				
				Product product = new Product(id,name,price,imageUrl);
				productList.add(product);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		return productList;
	}
	
	public Product find(int id){
		
		Product product = null;
		//JDBCドライバを読み込む
		try {
			Class.forName("org.h2.Driver");
		}catch(ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした。");
		}
		
		//データベース接続
		try(Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)){
			//SELECT文を準
			String sql = "SELECT ID,NAME,PRICE,IMAGE_URL FROM PRODUCTS WHERE ID = ?;";
			
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, id);
			//SELECT文を実行
			ResultSet rs = pStmt.executeQuery();
			
			//SELECT文の結果をArrayListに格納
			while(rs.next()){
				int wkid = rs.getInt("ID");
				String name = rs.getString("NAME");
				int price = rs.getInt("PRICE");
				String imageUrl = rs.getString("IMAGE_URL");
				
				product = new Product(wkid,name,price,imageUrl);
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		return product;
	}
	
	public boolean create(Product product) {
		
		//JDBCドライバを読み込む
		try {
			Class.forName("org.h2.Driver");
		}catch(ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした。");
		}
		
		//データベース接続
		try(Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)){
			//INSERT文を準備(idは自動採番の為指定しなくてOK)
			String sql = "INSERT INTO PRODUCTS(NAME,PRICE,IMAGE_URL) VALUES (?,?,?);";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			//INSERT文の?に使用する値をセットしてSQL文を完成させる
			pStmt.setString(1, product.getProductName());
			pStmt.setInt(2, product.getPrice());
			pStmt.setString(3, product.getImageUrl());
			
			//INSERT文を実行
			int result = pStmt.executeUpdate();
			if(result != 1) {
				return false;
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
}
