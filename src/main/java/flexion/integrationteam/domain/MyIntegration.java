package flexion.integrationteam.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.flexionmobile.codingchallenge.integration.Integration;
import com.flexionmobile.codingchallenge.integration.Purchase;

import flexion.integrationteam.handler.FlexionHandler;

@Component
public class MyIntegration implements Integration {

	@Autowired
	private FlexionHandler flexionHandler;

	public MyIntegration() {
	}

	/**
	 * Performs a purchase
	 * 
	 * @param item
	 *            id of the item to be bought
	 * @return the purchase
	 */
	public Purchase buy(String item) {
		return flexionHandler.buy(item);
	}

	/**
	 * Sets the status of a purchase to <i>consumed</i>
	 * 
	 * @param purchase
	 *            the purchase that needs to be consumed
	 */
	public void consume(Purchase purchase) {
		flexionHandler.consume(purchase);
	}

	/**
	 * returns a history of previous purchases and their consumption statuses
	 * 
	 * @return a list of purchases
	 */
	public List<Purchase> getPurchases() {
		return flexionHandler.getPurchases();
	}

}
