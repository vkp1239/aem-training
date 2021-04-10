package training.core.models;

import java.util.List;

import javax.inject.Inject;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Via;

// TODO: Auto-generated Javadoc
/**
 * The Interface SampleInterfaceModel.
 */
@Model(adaptables = {SlingHttpServletRequest.class, Resource.class },defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public interface SampleInterfaceModel {

	/**
	 * Gets the heading.
	 *
	 * @return the heading
	 */
	@Inject 
	@Via("resource")
	@Default(values = "Default Heading")
	public String getHeading();
	
	/**
	 * Gets the multifield.
	 *
	 * @return the multifield
	 */
	@Inject
	@Via("resource")
	public List<Product> getMultifield();	// it should be based on the name of multifield e.g- ./multifield
	
	
	/**
	 * The Interface Product.
	 */
	@Model(adaptables = {SlingHttpServletRequest.class, Resource.class },defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
	public interface Product{
		
		/**
		 * Gets the product.
		 *
		 * @return the product
		 */
		@Inject
		//@Via("resource")
		public String getProduct();
	}
	
}
