package flexion.integrationteam.handler;

import java.util.List;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.flexionmobile.codingchallenge.integration.Purchase;

import flexion.integrationteam.exception.ErrorResponseException;
import flexion.integrationteam.mapper.PurchaseMapper;

@Component
public class FlexionHandler {

	final static Logger LOGGER = Logger.getLogger(FlexionHandler.class);

	@Autowired
	private PurchaseMapper purchaseMapper;

	@Autowired
	private HttpHandler httpHandler;

	@Value("${developer.name}")
	private String developer;

	public FlexionHandler() {
	}

	public Purchase buy(String item) {
		JSONObject response = null;
		Purchase purchase = null;
		try {
			httpHandler.addArgs("developer", developer);
			httpHandler.addArgs("buy", item);
			response = httpHandler.sendPost();
		} catch (ErrorResponseException httpError) {
			LOGGER.error(
					"An exception occurred when trying to connect to the flexion API",
					httpError);
		} catch (Exception e) {
			LOGGER.error(
					"An exception occurred when trying to connect to the flexion API",
					e);
		} finally {
			purchase = purchaseMapper.toPurchase(response);
		}
		return purchase;
	}

	public void consume(Purchase purchase) {
		if (purchase != null) {
			try {
				httpHandler.addArgs("developer", developer);
				httpHandler.addArgs("consume", purchase.getId());
				httpHandler.sendPost();
			} catch (ErrorResponseException httpError) {
				LOGGER.error(
						"An exception occurred when trying to connect to the flexion API",
						httpError);
			} catch (Exception e) {
				LOGGER.error(
						"An exception occurred when trying to connect to the flexion API",
						e);
			}
		}
	}

	public List<Purchase> getPurchases() {
		List<Purchase> purchases = null;
		JSONObject response = null;
		try {
			httpHandler.addArgs("developer", developer);
			httpHandler.addArgs("all", null);
			response = httpHandler.sendGet();
		} catch (ErrorResponseException httpError) {
			LOGGER.error(
					"An exception occurred when trying to connect to the flexion API",
					httpError);
		} catch (Exception e) {
			LOGGER.error(
					"An exception occurred when trying to connect to the flexion API",
					e);
		} finally {
			purchases = purchaseMapper.toPurchases(response);
		}
		return purchases;
	}

}
