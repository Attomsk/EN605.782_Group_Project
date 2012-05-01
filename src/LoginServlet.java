

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import DataAccess.ComponentDataBean;
import DataAccess.CustomerDataBean;
import Models.Build;
import Models.Component;
import Models.Customer;

/**
 * Servlet implementation class CustomerServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url;
		HttpSession session = request.getSession(false);
		
		CustomerDataBean customerData = new CustomerDataBean();
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		List<Customer> validCustomers = (List<Customer>) customerData.validate(email, password);
		
		if (validCustomers.isEmpty()) {
			// redirect back to login
			url = "/login.jsp";
		} else {
			url = "/main.jsp";
			
			// Store customer in session. Lazy...just grabbing first customer in list. Assuming it will always be just
			// one customer email/password match.
			session.setAttribute("customer", validCustomers.get(0));
		}
		
		RequestDispatcher dispatcher =
			     getServletContext().getRequestDispatcher(url);
			dispatcher.forward(request, response);	
	}
}
