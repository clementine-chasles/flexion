package flexion.integrationteam;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.flexionmobile.codingchallenge.integration.IntegrationTestRunner;

import flexion.integrationteam.domain.MyIntegration;

@Component
public class App {
	
	@Autowired
	private MyIntegration myIntegration;
	
	final static Logger LOGGER = Logger.getLogger(App.class);

	public static void main(String[] args) {
		LOGGER.debug("Loading application context");
		ApplicationContext context = 
                new ClassPathXmlApplicationContext("applicationContext.xml");
		App p = context.getBean(App.class);
		p.run();
	}

	private void run() {
		LOGGER.debug("Running flexion tests");
		IntegrationTestRunner testRunner = new IntegrationTestRunner();
		testRunner.runTests(myIntegration);
	}
	
}
