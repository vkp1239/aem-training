package training.core.servlets;

import java.io.IOException;
import java.rmi.ServerException;

import javax.servlet.Servlet;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import training.core.services.CustomService;
import training.core.util.ResolverUtil;

/**
 * The Class MyCustomServlet.
 * Servlet sample based on resourceTypes
 */
@Component(service = Servlet.class, property = { Constants.SERVICE_DESCRIPTION + "=Simple custom Servlet",
		"sling.servlet.methods=" + HttpConstants.METHOD_GET,
		"sling.servlet.resourceTypes=" + "training/components/structure/page", "sling.servlet.selectors=" + "select"
// "sling.servlet.extensions=" + "txt"

})
public class MyCustomServlet extends SlingSafeMethodsServlet {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The custom service. */
	@Reference
	CustomService customService;

	/** The resource resolver factory. */
	@Reference
	ResourceResolverFactory resourceResolverFactory;

	/* doGet.
	 */
	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServerException,
			IOException {

		String message = "   Hello System User :-- ";
		message = message + ResolverUtil.getResourceResolver(resourceResolverFactory).getUserID();

		response.getWriter().write("There goes   servlet response -->" + customService.userName() + message);
		// response.getWriter().write("There goes   servlet response" );
	}

}
