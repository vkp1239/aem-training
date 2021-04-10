package training.core.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;

public final class ResolverUtil {

	public static final String TRAINING_SERVICE = "trainingservice";

	public static ResourceResolver getResourceResolver(ResourceResolverFactory resourceResolverFactory) {
		ResourceResolver resolver = null;
		try {
			final Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put(ResourceResolverFactory.SUBSERVICE, TRAINING_SERVICE);
			resolver = resourceResolverFactory.getServiceResourceResolver(paramMap);
		} catch (LoginException e) {
			e.printStackTrace();
		}
		return resolver;

	}

}
