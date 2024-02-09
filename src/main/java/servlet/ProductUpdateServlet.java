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
import model.ProductServiceLogic;

/**
 * Servlet implementation class ProductUpdateServlet
 */
@MultipartConfig
@WebServlet("/ProductUpdateServlet")
public class ProductUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductUpdateServlet() {
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
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/productUpdate.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//フォームから送られてきた情報を取得する
		int productId=Integer.parseInt(request.getParameter("id"));
		String productName=request.getParameter("productName");
		int price=Integer.parseInt(request.getParameter("price"));
		String buyer=request.getParameter("buyer");
		String stock=request.getParameter("stock");
		
		String biforPict = request.getParameter("biforPict");
		
		Product product = null;
		//name属性がpictのファイルをPartオブジェクトとして取得
		Part part=request.getPart("pict");
		//ファイル名を取得
		String filename=part.getSubmittedFileName();
		if(filename != "") {
			//画像が選択されていた場合
			//アップロードするフォルダ
			String path=getServletContext().getRealPath("/upload");
			
			//画像をサーバーに書き込み
			part.write(path+File.separator+filename);
			product = new Product(productId,productName,price,"upload/"+filename,buyer,stock);
		}else {
			//画像が選択されていなかった場合
			product = new Product(productId,productName,price,biforPict,buyer,stock);
		}
		
		ProductServiceLogic ps = new ProductServiceLogic();
		ps.updateProduct(product);
		
		//メイン画面に遷移する
        String targetServletURL = "/ProductServlet";
        RequestDispatcher rd= getServletContext().getRequestDispatcher(targetServletURL);
		
        rd.forward(request, response);
	}

}
