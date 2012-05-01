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
 * Servlet that controls the main computer building functionality
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
		String url = "/index.jsp";
		// Check for a processor type in the URL parameters
		String processorTypeString = request.getParameter("ProcessorType");
		int processorType = -1;
		if(null != processorTypeString)
		{
			processorType = Integer.parseInt(processorTypeString);
		}
		
		// Get session if one exists, create new session if needed
		HttpSession session = request.getSession(true);
		// Valid processor type - start build
		if((processorType > 0) && (processorType < Build.buildStates.length))
		{
			Build newBuild = new Build();
			newBuild.setState(0);
			newBuild.setProcessorType(processorType);
			session.setAttribute("build", newBuild);
		}
		// Retrieve build bean
		Build build = (Build) session.getAttribute("build");
		if(null != build)
		{
			doPost(request, response);
			return;
		}
		// load view
		RequestDispatcher dispatcher =
			     getServletContext().getRequestDispatcher(url);
			dispatcher.forward(request, response);	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Default redirect from this action
		String url = "/index.jsp";
		// This is the worker bean that gets bean info from the DB
		ComponentDataBean componentData = new ComponentDataBean();
		// Get session if one exists, do not create new session
		HttpSession session = request.getSession(false);
		// Don't attempt to process data on a null session
		if(null != session)
		{
			// Retrieve build bean
			Build build = (Build) session.getAttribute("build");
			// Don't work on the build if it doesn't exist
			if(null != build)
			{
				// Get item selection
				String newComponentId = request.getParameter(Build.buildStates[build.getState()]);
				// Don't attempt to find the selected component and move the build ahead if nothing was selected
				if(null != newComponentId)
				{
					// Add selection to the build bean
					List<Component> components = (List<Component>) componentData.getAllComponentsOfType(Build.buildStates[build.getState()], build.getProcessorType());
					Iterator<Component> itr = components.iterator();
					while (itr.hasNext()) {
						Component cmp = itr.next();
						if (Integer.parseInt(newComponentId) == cmp.getId()) {
							Component newCmp = new Component(cmp.getCategory(), cmp.getName(), cmp.getBrand(), cmp.getPrice(), cmp.getId());
							build.addComponent(newCmp);
							break;
						}
					}
					// Change state if the build has more states to go through
					if (build.getState()+1 < Build.buildStates.length)
					{
						build.incrementState();
					}
				}
			
				// store build and new parts in session
				List<Component> nextComponents = (List<Component>) componentData.getAllComponentsOfType(Build.buildStates[build.getState()], build.getProcessorType());
				session.setAttribute("build", build);
				session.setAttribute("newComponents", nextComponents);
				// forward request and response objects to JSP page
				url = "/build.jsp";
			}
		}
		// load view
		RequestDispatcher dispatcher =
			     getServletContext().getRequestDispatcher(url);
			dispatcher.forward(request, response);	
	}

}
