package training.core.models;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.log4j.Logger;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.ExporterOption;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Via;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.RequestAttribute;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.osgi.service.component.annotations.Reference;

import training.core.services.CustomService;

import com.day.cq.wcm.api.Page;

/**
 * The Class MyCustomModel.
 */
@Model(adaptables = { SlingHttpServletRequest.class, Resource.class }, resourceType = "training/components/custom/test", defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
@Exporter(name = "jackson", extensions = "json", options = { @ExporterOption(name = "SerializationFeature.WRITE_DATES_AS_TIMESTAMPS", value = "true") })
public class MyCustomModel {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger.getLogger(MyCustomModel.class);
	
	/** The resource. */
	@SlingObject
	Resource resource;

	/** The color. */
	@RequestAttribute(name = "color")
	private String color;

	/** The heading. */
	@Inject
	@Via("resource")
	@Default(values = "Default Heading")
	private String heading;

	/** The multifield. */
	@Inject
	@Via("resource")
	private List<Product> multifield; // it should be the name of multifield
										// e.g- ./multifield

	/** The created by. */
	@Inject
	@Via("resource")
	@Named("jcr:createdBy")
	private String createdBy;

	/** The page. */
	@ScriptVariable(name = "currentPage")
	Page page;
	
	@OSGiService
	CustomService custService;
	
	/*@Inject
	private ResourceResolverFactory resourceResolverFactory;
*/
	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		
		heading = custService.userName()+page.getPath();
		heading = "Heading Goes here::: " + heading;
		LOGGER.info("Inside init():::");
		/*try {
			ResourceResolver resolver = custService.getResourceResolver(resourceResolverFactory);
		} catch (LoginException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/

	}

	/**
	 * Gets the title.
	 *
	 * @return the title
	 */
	public String getTitle() {
		/*
		 * ValueMap valueMap = resource.getValueMap(); return
		 * valueMap.get("jcr:").toString()
		 */;
		return page.getTitle();
	}

	/**
	 * Gets the heading.
	 *
	 * @return the heading
	 */
	public String getHeading() {
		return heading;
	}

	/**
	 * Gets the page path.
	 *
	 * @return the page path
	 */
	public String getPagePath() {
		return page.getPath();
	}

	/**
	 * Gets the color.
	 *
	 * @return the color
	 */
	public String getColor() {
		return color.concat(" is the passed parameter");
	}

	/**
	 * Gets the created by.
	 *
	 * @return the created by
	 */
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * Gets the multifield.
	 *
	 * @return the multifield
	 */
	public List<Product> getMultifield() {
		return multifield;
	}

	/**
	 * The Interface Multifield.
	 */
	@Model(adaptables = { SlingHttpServletRequest.class, Resource.class }, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
	public interface Product {

		/**
		 * Gets the product.
		 *
		 * @return the product
		 */
		@Inject
		public String getProduct();
	}
}
