package training.core.services;

import org.json.JSONObject;

/**
 * The Class SearchService.
 *
 * @author VINEET
 */
public interface SearchService {
	
	/**
	 * Search.
	 *
	 * @param searchText the search text
	 * @param startResult the start result
	 * @param resultPerPage the result per page
	 * @return the JSON object
	 */
	public JSONObject search(String searchText,int startResult,int resultPerPage);
}
