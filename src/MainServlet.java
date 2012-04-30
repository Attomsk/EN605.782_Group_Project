

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import DataAccess.ComponentDataBean;
import Models.Build;
import Models.Component;

/**
 * Servlet implementation class MainServlet
 */
@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ComponentDataBean componentData = new ComponentDataBean();
		List<Component> memory = (List<Component>)componentData.getAllComponentsOfType("MEMORY");
		HttpSession session = request.getSession();
		session.setAttribute("memory", memory);
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/test.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ComponentDataBean componentData = new ComponentDataBean();
		
		// Retrieve build bean
		HttpSession session = request.getSession(false);
		Build build = (Build) session.getAttribute("build");
		// Check for null (first time)
		if (build == null) {
			build = new Build();
		}
		
		// Get item selection
		String newComponentId = request.getParameter(build.buildStates[build.getState()]);
		
		// Add selection to the build bean
		List<Component> components = (List<Component>) componentData.getAllComponentsOfType(build.buildStates[build.getState()]);
		Iterator<Component> itr = components.iterator();
		while (itr.hasNext()) {
			Component cmp = itr.next();
			if (Integer.getInteger(newComponentId) == cmp.getId()) {
				build.addComponent(cmp);
				break;
			}
		}
		
		// increment state
		build.incrementState();
		
		// store build and new parts in session
		List<Component> nextComponents = (List<Component>) componentData.getAllComponentsOfType(build.buildStates[build.getState()]);
		session.setAttribute("build", build);	
		session.setAttribute("newComponents", nextComponents);
	
		// forward request and response objects to JSP page
		String url = "/main.jsp";
		RequestDispatcher dispatcher =
		     getServletContext().getRequestDispatcher(url);
		dispatcher.forward(request, response);	
	}

}
