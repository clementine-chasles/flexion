package flexion.integrationteam;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.flexionmobile.codingchallenge.integration.IntegrationTestRunner;
import com.flexionmobile.codingchallenge.integration.Purchase;

import flexion.integrationteam.domain.MyIntegration;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
@Component
public class AppTest extends TestCase {

	@Autowired
	private MyIntegration myIntegration;

	private static String myFirstItemId = "myFirstItem";

	/**
	 * Create the test case
	 * 
	 * @param testName
	 *            name of the test case
	 */
	public AppTest() {
	}

	public AppTest(String testName) {
		super(testName);
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"applicationContext-test.xml");
		AppTest p = context.getBean(AppTest.class);
		p.runFlexionTests();
	}

	private void runFlexionTests() {
		IntegrationTestRunner testRunner = new IntegrationTestRunner();
		testRunner.runTests(myIntegration);
		runMyTests();
	}

	private void runMyTests() {
		
		List<Purchase> l = myIntegration.getPurchases();
		assertNotNull(l);
		int sizeBefore = l.size();
		
		Purchase firstPurchase = myIntegration.buy(myFirstItemId);
		assertFalse(firstPurchase.getConsumed());
		
		myIntegration.consume(firstPurchase);
		
		l = myIntegration.getPurchases();
		assertNotNull(l);
		assertEquals(sizeBefore+1, l.size());
		
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(AppTest.class);
	}

	/**
	 * Rigourous Test :-)
	 */
	public void testApp() {

	}
}
