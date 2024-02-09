package servlet;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
 * Servlet implementation class ProductDeleteServlet
 */
@WebServlet("/ProductDeleteServlet")
public class ProductDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductDeleteServlet() {
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
		
		//リクエストスコープに保存
		request.setAttribute("productId", productId);
		
		//特定した在庫を表示する
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/productDelete.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//在庫IDを取得する
		int productId = Integer.parseInt(request.getParameter("productId"));
		
		//選択がNOであれば前の画面に遷移する
		String isYesNo= (String)request.getParameter("isYesNo");
		if(isYesNo.equals("NO")) {
			//削除しない場合
			//在庫詳細画面に遷移する
			String targetServletURL = "/ProductDetailsServlet";
			RequestDispatcher rd= getServletContext().getRequestDispatcher(targetServletURL);
			
			rd.forward(request, response);
			
			return;
		}
		
		//選択がYESの場合
		ProductServiceLogic ps = new ProductServiceLogic();
		
		//画像のURLを取得する
		Product product = ps.getProductById(productId);
		String imageUrl =getServletContext().getRealPath("") + product.getImageUrl();
		
		Path path = Paths.get(imageUrl);
		
		try{
			Files.delete(path);
		}catch(IOException e) {
			System.out.println(e);
		}
		
		//DBからデータを削除する
		ps.deleteProduct(productId);
		
		
		//メイン画面に遷移する
		String targetServletURL = "/ProductServlet";
        RequestDispatcher rd= getServletContext().getRequestDispatcher(targetServletURL);
        rd.forward(request, response);
	}

}
