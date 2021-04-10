package training.core.services.impl;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.metatype.annotations.Designate;

import training.core.config.CustomOsgiConfig;
import training.core.services.CustomService;

/**
 * The Class CustomServiceImpl.
 */
@Component(service = CustomService.class, immediate = true)
@Designate(ocd = CustomOsgiConfig.class)
public class CustomServiceImpl implements CustomService {

	/** The user name. */
	public String userName;

	/** The password. */
	public String password;

	/** The custom osgi config. */
	private CustomOsgiConfig customOsgiConfig;

	/**
	 * Activate.
	 *
	 * @param config
	 *            the config
	 */
	@Activate
	@Modified
	protected void activate(final CustomOsgiConfig config) {
		this.customOsgiConfig = config;
		userName = customOsgiConfig.getUserName();
		password = customOsgiConfig.getPassword();
	}

	/**
	 * @return the username.
	 */
	@Override
	public String userName() {

		return this.userName;
	}

	/**
	 * @return the password.
	 */
	@Override
	public String password() {

		return this.password;
	}

}
