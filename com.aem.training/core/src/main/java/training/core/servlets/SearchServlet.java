package training.core.servlets;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.json.JSONObject;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import training.core.services.SearchService;
import training.core.services.impl.SearchServiceImpl;

/**
 * The Class SearchServlet.
 * @author VINEET
 * Sample URL http://localhost:4502/content/training/language-master/en/home.search.html?searchText=women&pageNumber=1&resultPerPage=20
 */
@Component(service = Servlet.class, property = { Constants.SERVICE_DESCRIPTION + "=Full Text Search Servlet",
	"sling.servlet.methods=" + HttpConstants.METHOD_GET,
	"sling.servlet.resourceTypes=" + "training/components/structure/page", "sling.servlet.selectors=" + "search"
})
public class SearchServlet extends SlingSafeMethodsServlet{

	/** The Constant serialVersionUID.*/
	private static final long serialVersionUID = 1L;
	
	@Reference
	SearchService searchService;
	
	private static final Logger LOGGER= LoggerFactory.getLogger(SearchServiceImpl.class);
	
	@Override
    protected void doGet(final SlingHttpServletRequest req, final SlingHttpServletResponse resp) throws ServletException, IOException {
        JSONObject searchResult=null;
        try {
            String searchtext = req.getRequestParameter("searchText").getString();
            int pageNumber = Integer.parseInt(req.getRequestParameter("pageNumber").getString())-1;
            int resultPerPage = Integer.parseInt(req.getRequestParameter("resultPerPage").getString());
            int startResult=pageNumber*resultPerPage;
            searchResult=searchService.search(searchtext,startResult,resultPerPage);
            resp.setContentType("application/json");
            resp.getWriter().write(searchResult.toString());
        } catch (Exception e) {
            LOGGER.info("ERROR {} ", e.getMessage());
            resp.setContentType("text/html");
            resp.getWriter().write("<h3>Something went Wrong</h3>");
        }

    }

	
}
