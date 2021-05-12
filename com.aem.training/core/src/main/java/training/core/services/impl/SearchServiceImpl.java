package training.core.services.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jcr.Session;

import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.json.JSONArray;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.search.result.SearchResult;
import com.day.cq.wcm.api.Page;

import training.core.services.SearchService;
import training.core.util.ResolverUtil;

/**
 * The Class SearchServiceImpl.
 *
 * @author VINEET
 */
@Component(service=SearchService.class)
public class SearchServiceImpl implements SearchService{
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER= LoggerFactory.getLogger(SearchServiceImpl.class);

    /** The query builder. */
    @Reference
    QueryBuilder queryBuilder;
    
    /** The resource resolver factory. */
    @Reference
    ResourceResolverFactory resourceResolverFactory;
    
    /**
     * Creates the query.
     *
     * @param searchText the search text
     * @param startResult the start result
     * @param resultPerPage the result per page
     * @return the map
     */
    public Map<String,String> createQuery(String searchText,int startResult,int resultPerPage){
        Map<String,String> queryMap=new HashMap<>();
        queryMap.put("path","/content/we-retail");
        queryMap.put("type","cq:Page");
        queryMap.put("fulltext",searchText);
        queryMap.put("p.offset",Long.toString(startResult));
        queryMap.put("p.limit",Long.toString(resultPerPage));
        return queryMap;
    }

	/* (non-Javadoc)
	 * @see training.core.services.SearchService#search(java.lang.String, int, int)
	 */
	@Override
	public JSONObject search(String searchText, int startResult, int resultPerPage) {
        LOGGER.info("Search Result :::-");
        JSONObject searchResult=new JSONObject();
        try {
        	ResourceResolver resourceResolver = ResolverUtil.getResourceResolver(resourceResolverFactory);
        	Session session = resourceResolver.adaptTo(Session.class);
        	Query query = queryBuilder.createQuery(PredicateGroup.create(createQuery(searchText, startResult, resultPerPage)), session);
        	SearchResult result = query.getResult();
        	int perPageResults = result.getHits().size();
            long totalResults = result.getTotalMatches();
            long startingResult = result.getStartIndex();
            double totalPages = Math.ceil((double) totalResults / (double) resultPerPage);
            searchResult.put("perpageresult",perPageResults);
            searchResult.put("totalresults",totalResults);
            searchResult.put("startingresult",startingResult);
            searchResult.put("pages",totalPages);
            List<Hit> hits =result.getHits();
            JSONArray resultArray=new JSONArray();
            for(Hit hit: hits){
                Page page=hit.getResource().adaptTo(Page.class);
                JSONObject resultObject=new JSONObject();
                resultObject.put("title",page.getTitle());
                resultObject.put("path",page.getPath());
                resultArray.put(resultObject);
                LOGGER.info("\n Page {} ",page.getPath());
            }
            searchResult.put("results",resultArray);
            resourceResolver.close();
        }
        catch(Exception e){
        	LOGGER.info("Error Occured::::");
        }
		return searchResult;
	}

}
