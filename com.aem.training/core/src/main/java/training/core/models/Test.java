package training.core.models;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.Model;

@Model(adaptables = Resource.class
		)
public class Test {

	protected static final String RESOURCE_TYPE = "training/components/content/test";
	@Inject
	private String testxext;

	public String getTestTest() {
		return testxext;
	}

	public void setTestTest(String testTest) {
		this.testxext = testTest;
	}
}
