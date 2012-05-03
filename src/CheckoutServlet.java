import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Models.Customer;
import Models.CreditCard;

/**
 * Handles processing of the checkout form
 */
@WebServlet("/CheckoutServlet")
public class CheckoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckoutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// No get handling
	}

	/**
	 * Processes the input from the checkout form
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/confirmation.jsp";
		// get parameters from the request
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String address1 = request.getParameter("address1");
		String address2 = request.getParameter("address2");
		String zipcode = request.getParameter("zipcode");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		String ccNum = request.getParameter("ccNum");
		String ccExp = request.getParameter("ccExp");
		String ccCvc = request.getParameter("ccCvc");
		String ccType = request.getParameter("ccType");
		// Address 2 is not required
		if(null == address2)
		{
			address2 = "";
		}

		// create customer bean
		Customer customer = new Customer (firstName, lastName, email, "", address1, address2, zipcode, city, state);
		// create credit card bean
		CreditCard credit = new CreditCard(ccNum,ccExp,ccType,ccCvc);
		
		// Set these objects as request objects - not session objets
		request.setAttribute("checkout_customer", customer);
		request.setAttribute("credit_card", credit);
		
		//Send to the checkout
		RequestDispatcher dispatcher =
			     getServletContext().getRequestDispatcher(url);
			dispatcher.forward(request, response);	
		
	}

}
