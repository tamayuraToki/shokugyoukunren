package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Account;
import model.User;

public class AccountsDAO {
	//データベース接続に使用する情報
	private final String JDBC_URL = "jdbc:h2:tcp://localhost/~/HandmadeInventoryContorol";
	private final String DB_USER ="sa";
	private final String DB_PASS="";
	
	public Account findByLogin(User user) {
		Account account = null;
		//JDBCドライバを読み込む
		try {
			Class.forName("org.h2.Driver");
		}catch(ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした。");
		}
		
		//データベース接続
		try(Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)){
			//SELECT文を準備
			String sql = "SELECT USER_ID,PASS,MAIL,NAME FROM ACCOUNTS WHERE USER_ID = ? AND PASS = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1,user.getName());
			pStmt.setString(2,user.getPass());
			
			//Select文を実行し、結果表を取得
			ResultSet rs = pStmt.executeQuery();
			
			if(rs.next()) {
				//ユーザーが存在したらデータ取得
				//そのユーザーを表すAccountインスタンスを取得
				String userId = rs.getString("USER_ID");
				String pass = rs.getString("PASS");
				String mail = rs.getString("MAIL");
				String name = rs.getString("NAME");
				account = new Account(userId,pass,mail,name);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		return account;
	}
}
