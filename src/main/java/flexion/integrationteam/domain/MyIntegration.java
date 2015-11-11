package flexion.integrationteam.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.flexionmobile.codingchallenge.integration.Integration;
import com.flexionmobile.codingchallenge.integration.Purchase;

import flexion.integrationteam.handler.FlexionHandler;

@Component
public class MyIntegration implements Integration {
	
	@Autowired
	private FlexionHandler flexionHandler;
	
	public MyIntegration() { }

	public Purchase buy(String item) {
		return flexionHandler.buy(item);
	}

	public void consume(Purchase purchase) {
		flexionHandler.consume(purchase);
	}

	public List<Purchase> getPurchases() {
		return flexionHandler.getPurchases();
	}

}
