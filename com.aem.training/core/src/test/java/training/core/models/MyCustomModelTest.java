package training.core.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * @author VINEET
 *
 */
@ExtendWith({ AemContextExtension.class, MockitoExtension.class })
public class MyCustomModelTest {
	
	private final AemContext aemContext = new AemContext();
	
	private MyCustomModel myCustomModel;

	@BeforeEach
	void setUp() {
		aemContext.load().json("/training/core/models/test.json", "/component");
		aemContext.load().json("/training/core/models/page.json", "/page");

	}

	@Test
	void getFirstName() {
		aemContext.currentResource("/component/test");
		myCustomModel = aemContext.request().adaptTo(MyCustomModel.class);
		final String expected = "Vineet";
		String actual = myCustomModel.getFname();
		assertEquals(expected, actual);
	}

	@Test
	void getHeading() {
		aemContext.currentResource("/component/test");
		myCustomModel = aemContext.request().adaptTo(MyCustomModel.class);
		final String expected = "Heading Goes here::: This is Heading Authored by vineet pandey";
		String actual = myCustomModel.getHeading();
		assertEquals(expected, actual);
	}

	@Test
	void getMultifield() {
		aemContext.currentResource("/component/test");
		myCustomModel = aemContext.request().adaptTo(MyCustomModel.class);
		assertEquals(myCustomModel.getMultifield().get(0).getProduct(), "Product1");
	}

	@Test
	void getCreatedBy() {
		aemContext.currentResource("/component/test");
		myCustomModel = aemContext.request().adaptTo(MyCustomModel.class);
		assertEquals(myCustomModel.getCreatedBy(), "admin");
	}

	@Test
	void getColor() {
		aemContext.request().setAttribute("color", "red");
		myCustomModel = aemContext.request().adaptTo(MyCustomModel.class);
		assertEquals(myCustomModel.getColor(), "red is the passed parameter");
	}

	@Test
	void getTitle() {
		aemContext.currentPage("/page/currentPage");
		myCustomModel = aemContext.request().adaptTo(MyCustomModel.class);
		assertEquals(myCustomModel.getTitle(), "Home");

	}

	@Test
	void getPagePath() {
		aemContext.currentPage("/page/currentPage");
		myCustomModel = aemContext.request().adaptTo(MyCustomModel.class);
		assertEquals(myCustomModel.getPagePath(), "/page/currentPage");

	}
}
