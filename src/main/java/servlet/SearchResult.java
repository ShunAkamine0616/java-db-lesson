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
		// doGet(request, response)
		request.setCharacterEncoding("UTF-8");
		String btn = request.getParameter("btn");
		String product_name = request.getParameter("product_name");
		String price = request.getParameter("price");
		Integer price_int;

		try {
			price_int = Integer.parseInt(price);
		} catch (NumberFormatException e) {
			price_int = null;
		}
		
		
		HttpSession session = request.getSession();

		ProductService pService = new ProductService();
		List<Product> productList = null;
		if(btn.equals("insert")) {
			if (Utility.isNullOrEmpty(product_name) && Utility.isNullOrEmpty(price)) {
				request.setAttribute("nameErrMsg", "product_nameは必須です");
				request.setAttribute("priceErrMsg", "priceは必須です");
				productList = pService.find();
				request.getRequestDispatcher("top.jsp").forward(request, response);
				return;
			} else if(!Utility.isNullOrEmpty(product_name) && Utility.isNullOrEmpty(price)){
				request.setAttribute("priceErrMsg", "priceは必須です");
				productList = pService.findByName(product_name);
				request.getRequestDispatcher("top.jsp").forward(request, response);
				return;
			} else if(Utility.isNullOrEmpty(product_name) && !Utility.isNullOrEmpty(price)){
				request.setAttribute("nameErrMsg", "product_nameは必須です");
				productList = pService.findByPrice(price_int);
				request.getRequestDispatcher("top.jsp").forward(request, response);
				return;
			} else {
				Product p = new Product(product_name, price_int);
				pService.register(p);
				request.getRequestDispatcher("insertResult.jsp").forward(request, response);
				return;
			} 
		} else {
			if (Utility.isNullOrEmpty(product_name) && Utility.isNullOrEmpty(price)) {
				productList = pService.find();
			} else if(!Utility.isNullOrEmpty(product_name) && Utility.isNullOrEmpty(price)){
				productList = pService.findByName(product_name);
			} else if(Utility.isNullOrEmpty(product_name) && !Utility.isNullOrEmpty(price)){
				productList = pService.findByPrice(price_int);
			} else {
				productList = pService.findByNameAndPrice(product_name, price_int);
			} 
			request.setAttribute("productList", productList);
			//    
			//        Product product = null;
			//        for(Product p: productList) {
			//        	if(price_int == p.getProduct_id()) {
			//        		product = p;
			//        	}
			//        }
	
			if (productList.isEmpty()) {
				request.setAttribute("result", "対象のデータはありません");
				request.getRequestDispatcher("top.jsp").forward(request, response);
				return;
			}
			request.getRequestDispatcher("searchResult.jsp").forward(request, response);
			return;
		}
	}

}
