package training.core.servlets;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.Servlet;

import org.apache.commons.collections4.iterators.TransformIterator;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceMetadata;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;

import com.adobe.cq.commerce.common.ValueMapDecorator;
import com.adobe.granite.ui.components.ds.DataSource;
import com.adobe.granite.ui.components.ds.SimpleDataSource;
import com.adobe.granite.ui.components.ds.ValueMapResource;
import com.day.cq.commons.jcr.JcrConstants;

@Component(service = Servlet.class, immediate = true, property = {
		Constants.SERVICE_DESCRIPTION + "=Simple custom Servlet", "sling.servlet.resourceTypes=" + "/apps/dropdown" })
public class DropDownServlet extends SlingSafeMethodsServlet {
	
	/** The Constant serialVersionUID.*/
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) {
		// below 4 lines are for demo purpose .It can be modified based on the requirements.
		
		KeyValue c1 = new KeyValue("India", "ind");
		KeyValue c2 = new KeyValue("United States", "usa");
		KeyValue l1 = new KeyValue("Hindi", "hi");
		KeyValue l2 = new KeyValue("English(USA)", "en-US");
		List<KeyValue> dropDownList = new ArrayList<>();
		ResourceResolver resourceResolver = request.getResourceResolver();
		Resource resource = request.getResource();
		Resource dataSource = resource.getChild("datasource");
		String selector = dataSource.getValueMap().get("selector", String.class);
		if (selector.equalsIgnoreCase("language")) {
			dropDownList.add(l1);
			dropDownList.add(l2);
		} else {
			dropDownList.add(c1);
			dropDownList.add(c2);
		}
		@SuppressWarnings({ "unchecked", "rawtypes" })
		DataSource ds = new SimpleDataSource(new TransformIterator(dropDownList.iterator(), input -> {
			KeyValue keyValue = (KeyValue) input;
			ValueMap vm = new ValueMapDecorator(new HashMap<>());
			vm.put("value", keyValue.value);
			vm.put("text", keyValue.key);
			return new ValueMapResource(resourceResolver, new ResourceMetadata(), JcrConstants.NT_UNSTRUCTURED, vm);
		}));
		request.setAttribute(DataSource.class.getName(), ds);
	}

	private class KeyValue {

		/**
		 * key property.
		 */
		private String key;
		/**
		 * value property.
		 */
		private String value;

		/**
		 * constructor instance intance.
		 *
		 * @param newKey
		 *            -
		 * @param newValue
		 *            -
		 */
		private KeyValue(final String newKey, final String newValue) {
			this.key = newKey;
			this.value = newValue;
		}
	}

}
