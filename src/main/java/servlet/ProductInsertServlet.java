
package servlet;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import model.Product;
import model.ProductService;

/**
 * Servlet implementation class ProductInsertServlet
 */
@MultipartConfig
@WebServlet("/ProductInsertServlet")
public class ProductInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductInsertServlet() {
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
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/productInsert.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//セッションスコープからユーザ情報を取得
		HttpSession session = request.getSession();
		String userId = (String)session.getAttribute("userId");
		
		String productName=request.getParameter("productName");
		int price=Integer.parseInt(request.getParameter("price"));
		String buyer=request.getParameter("buyer");
		String stock=request.getParameter("stock");
		
		//name属性がpictのファイルをPartオブジェクトとして取得
		Part part=request.getPart("pict");
		//ファイル名を取得
		String filename=part.getSubmittedFileName();
		//アップロードするフォルダ
		String path=getServletContext().getRealPath("/upload");
		
		//実際にファイルが保存されるパス確認
		System.out.println(path);
		//画像をサーバーに書き込み
		part.write(path+File.separator+filename);
		
		//データをDBにInsertする
		Product product = new Product(productName,price,"upload/"+filename,buyer,stock);
		ProductService ps = new ProductService();
		ps.insertProduct(product,userId);
		
		//メイン画面に遷移する
		String targetServletURL = "/ProductServlet";
        RequestDispatcher rd= getServletContext().getRequestDispatcher(targetServletURL);
		
        rd.forward(request, response);
	}

}
