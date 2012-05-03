

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import DataAccess.CustomerDataBean;
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
	 * Handles logouts
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/index.jsp";
		HttpSession session = request.getSession(false);
		String logout = request.getParameter("logout");
		if(null != session)
		{
			// Check to see if this is a logout attempt
			if(null != logout)
			{
				session.removeAttribute("customer");
			}
		}
		// Return control to view
		RequestDispatcher dispatcher =
			     getServletContext().getRequestDispatcher(url);
			dispatcher.forward(request, response);
	}

	/**
	 * Handles Logins
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = "/index.jsp";
		HttpSession session = request.getSession(false);
		CustomerDataBean customerData = new CustomerDataBean();
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		// Check to see if this is a registration request
		String register = request.getParameter("register");
		
		// Login Attempt
		if(null == register)
		{
			List<Customer> validCustomers = (List<Customer>) customerData.validate(email, password);
			if (validCustomers.isEmpty()) {
				// redirect back to login
				url = "/login.jsp";
				request.setAttribute("regMessage", "Invalid Login");
			} else {
				// Store customer in session. Lazy...just grabbing first customer in list. Assuming it will always be just
				// one customer email/password match.
				session.setAttribute("customer", validCustomers.get(0));
			}
		}
		// Registration Attempt
		else
		{
			// get remaining registration parameters
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			String address1 = request.getParameter("address1");
			String address2 = request.getParameter("address2");
			String city = request.getParameter("city");
			String state = request.getParameter("state");
			String zip = request.getParameter("zip");
			
			//Add user to database
			if(customerData.addNewUser(email, password, firstName, lastName, address1, address2, city, state, zip) >= 0)
			{
				//registration successful
				request.setAttribute("regMessage", "Registration was succesful");
			}
			else
			{
				//registration failed
				request.setAttribute("regMessage", "Registration failed");
			}
			url = "/login.jsp";
		}
		// Return control to view
		RequestDispatcher dispatcher =
			     getServletContext().getRequestDispatcher(url);
			dispatcher.forward(request, response);	
	}
}
