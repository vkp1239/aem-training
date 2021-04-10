package training.core.config;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

/**
 * The Interface CustomOsgiConfig.
 */
@ObjectClassDefinition (name = "Custom Osgi Config", description = "My Custom Osgi Configuration")
public @interface CustomOsgiConfig {
	
	
	/**
	 * Gets the user name.
	 *
	 * @return the user name
	 */
	@AttributeDefinition(name = "userName", description = "Username")
	String getUserName();
	
	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	@AttributeDefinition(name = "password", description = "Password")
	String getPassword();
	
}
