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
 * Servlet implementation class deleteResult
 */
@WebServlet("/deleteResult")
public class deleteResult extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public deleteResult() {
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
		Integer product_id_int;

		try {
			product_id_int = Integer.parseInt(product_id);
		} catch (NumberFormatException e) {
			product_id_int = null;
		}


//		HttpSession session = request.getSession();

		ProductService pService = new ProductService();
//		List<Product> productList = null;
		if (Utility.isNullOrEmpty(product_id)) {
			request.setAttribute("idErrMsg", "product_idは必須です");
			request.getRequestDispatcher("delete.jsp").forward(request, response);
			return;
		}else {
			if(pService.delete(product_id_int) == 0) {
				request.setAttribute("result", "対象のデータはありません");
				request.getRequestDispatcher("delete.jsp").forward(request, response);
				return;
			}
			request.getRequestDispatcher("deleteResult.jsp").forward(request, response);
			return;
		}
	}
}
