package servlet;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
		//
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/productInsert.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String productName=request.getParameter("productName");
		int price=Integer.parseInt(request.getParameter("price"));
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
		Product product = new Product(productName,price,"upload/"+filename);
		ProductService ps = new ProductService();
		ps.insertProduct(product);
		
		// 商品情報を取得するメソッド
        List<Product> productList = ps.getProducts(); 
        request.setAttribute("productList", productList);
		
		RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/jsp/productList.jsp");
		rd.forward(request, response);
	}

}
