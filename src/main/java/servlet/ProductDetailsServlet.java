package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Product;
import model.ProductServiceLogic;

/**
 * Servlet implementation class ProductDetailsServlet
 */
@WebServlet("/ProductDetailsServlet")
public class ProductDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductDetailsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//ログインしているかの確認
		//セッションスコープからユーザ情報を取得
		HttpSession session = request.getSession();
		String userId = (String)session.getAttribute("userId");
		if(userId == null) {
			//未ログインの場合トップページへ遷移
			response.sendRedirect("index.jsp");
			return;
		}
			
		//在庫IDを取得する
		int productId = Integer.parseInt(request.getParameter("productId"));
		
		//在庫IDから在庫を特定する
		ProductServiceLogic ps = new ProductServiceLogic();
		Product product = ps.getProductById(productId); // 商品情報を取得するメソッド
		
		//リクエストスコープに保存
		request.setAttribute("product", product);
		
		//特定した在庫を表示する
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/productDetails.jsp");
		dispatcher.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
