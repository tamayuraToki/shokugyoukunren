package model;

import dao.AccountsDAO;

public class LoginLogic {
	public boolean execute(User user) {
		
		AccountsDAO dao = new AccountsDAO();
		Account account =dao.findByLogin(user);
		return account != null;
	}
}
