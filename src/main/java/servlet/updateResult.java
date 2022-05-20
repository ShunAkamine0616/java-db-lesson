package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.ProductService;
import util.Utility;

/**
 * Servlet implementation class updateResult
 */
@WebServlet("/updateResult")
public class updateResult extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateResult() {
        super();
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
		//doGet(request, response);
		request.setCharacterEncoding("UTF-8");
		String product_id = request.getParameter("product_id");
		String product_name = request.getParameter("product_name");
		String price = request.getParameter("price");
		Integer product_id_int;
		Integer price_int;

		try {
			product_id_int = Integer.parseInt(product_id);
			price_int = Integer.parseInt(price);
		} catch (NumberFormatException e) {
			product_id_int = null;
			price_int = null;
		}


//		HttpSession session = request.getSession();

		ProductService pService = new ProductService();
//		List<Product> productList = null;
		if (Utility.isNullOrEmpty(product_id)) {
			request.setAttribute("idErrMsg", "product_idは必須です");
		}
		
		if (Utility.isNullOrEmpty(product_name)) {
			request.setAttribute("nameErrMsg", "product_nameは必須です");
		}
		
		if (Utility.isNullOrEmpty(price)) {
			request.setAttribute("priceErrMsg", "priceは必須です");
		}
		
		if(Utility.isNullOrEmpty(product_id) ||
				Utility.isNullOrEmpty(product_name) ||
				Utility.isNullOrEmpty(price)) {
			request.getRequestDispatcher("update.jsp").forward(request, response);
			return;	
		}

		request.setAttribute("beforeUpdate", pService.findById(product_id_int));
		if(pService.update(product_id_int, product_name, price_int) == 0) {
			request.setAttribute("result", "対象のデータはありません");
			request.getRequestDispatcher("update.jsp").forward(request, response);
			return;
		}
		request.setAttribute("afterUpdate", pService.findById(product_id_int));
		request.getRequestDispatcher("updateResult.jsp").forward(request, response);
		return;
	}

}
