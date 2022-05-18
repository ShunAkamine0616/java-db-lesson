package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ProductDao;
import entity.Product;
import service.ProductService;
import util.Utility;
/**
 * Servlet implementation class SearchResult
 */
@WebServlet("/SearchResult")
public class SearchResult extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection connection;
	private ProductDao productDao;
    /**
     * Default constructor. 
     */
    public SearchResult() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		request.setCharacterEncoding("UTF-8");
        String product_id = request.getParameter("product_id");
        
        Integer product_id_int;
        try {
        	product_id_int = Integer.parseInt(product_id);
        } catch (NumberFormatException e) {
        	product_id_int = null;
        }
        
        if (Utility.isNullOrEmpty(product_id)) {
            request.setAttribute("result", "product_idを入力してください");

            request.getRequestDispatcher("top.jsp").forward(request, response);
            return;
        }
        request.setAttribute("result", "商品が登録されました");
        // セッションを取得
        
        HttpSession session = request.getSession();

        // セッションより商品リストを取得
        // (警告が出るが、無視して良い)
//        List<Product> productList = (List<Product>) session.getAttribute("productList");
//        connection = DbUtil.getConnection();
//		connection.setAutoCommit(false);
//        ProductDao pDao = new ProductDao(connection);
        ProductService pService = new ProductService();
        List<Product> productList = pService.find();
       
        // 取得した商品リストが無い場合、ArrayListオブジェクトを新規作成
//        if (productList == null) {
//            productList = new ArrayList<>();
//        }
        Product product = null;
        for(Product p: productList) {
        	if(product_id_int == p.getProduct_id()) {
        		product = p;
        	}
        }
        
        if (product == null) {
            request.setAttribute("result", "対象のデータはありません");
            request.getRequestDispatcher("top.jsp").forward(request, response);
            return;
         }
        // productオブジェクトを作成し、入力値をセット
//        Product product = new Product(null, productName, price);

        // productListに上記で作成したオブジェクトを追加
//        productList.add(product);

        // セッションに商品リスト(productList)を登録
//        session.setAttribute("productList", productList);
        session.setAttribute("product", product);
        // 結果画面へ遷移
        request.getRequestDispatcher("searchResult.jsp").forward(request, response);

	}

}
