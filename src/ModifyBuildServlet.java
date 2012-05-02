import java.io.IOException;
import java.util.ArrayList;
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
 * Allows the user to modify a component selection in the current build
 * @author Ben Morlok
 *
 */
@WebServlet("/ModifyBuildServlet")
public class ModifyBuildServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyBuildServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * Takes a request containing a build category and returns the user to the modify build view
	 * If there is a modified item selection it returns the user to the shopping cart after changing
	 * the build
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// This is the worker bean that gets bean info from the DB
		ComponentDataBean componentData = new ComponentDataBean();
		// URL for redirect
		String url = "/index.jsp";
		// Check for a category in the URL parameters
		String category = request.getParameter("category");
		// Check for a modified item selection
		String modifiedItem = request.getParameter("newItem");
		// Get session if one exists, do not create new session
		HttpSession session = request.getSession(false);
		if(null != category)
		{
			if(null != session)
			{
				// Retrieve build bean
				Build build = (Build) session.getAttribute("build");
				// Don't work on the build if it doesn't exist
				if(null != build)
				{
					// get all items of the category type for modification
					List<Component> components = (List<Component>) componentData.getAllComponentsOfType(category, build.getProcessorType());
					if(null != components)
					{
						if(null != modifiedItem)
						{
							// get the newly selected component from the components list
							Component newCmp = null;
							Iterator<Component> itr = components.iterator();
							while (itr.hasNext()) {
								Component cmp = itr.next();
								if (Integer.parseInt(modifiedItem) == cmp.getId()) {
									newCmp = new Component(cmp.getCategory(), cmp.getName(), cmp.getBrand(), cmp.getPrice(), cmp.getId());
									break;
								}
							}
							// check if the new component was found, if so remove old component and replace it with the new one
							if(null != newCmp)
							{
								List<Component> newComponents = build.getComponents();
								for(int i=0; i < newComponents.size(); i++)
								{
									if(newCmp.getCategory().equals(build.getComponents().get(i).getCategory()))
									{
										newComponents.set(i,newCmp);
									}
								}
								session.setAttribute("build", build);
								// send user back to the shopping cart
								url = "/cart.jsp";
							}
						}
						else
						{
							session.setAttribute("modifyComponents", components);
							url = "/modify.jsp";
						}
					}
				}
			}
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
		// TODO Auto-generated method stub
	}

}
