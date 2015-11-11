package flexion.integrationteam.mapper;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.stereotype.Component;

import com.flexionmobile.codingchallenge.integration.Purchase;

import flexion.integrationteam.domain.MyPurchase;

@Component
public class PurchaseMapper {

	public Purchase toPurchase(JSONObject response) {
		MyPurchase purchase = null;
		if (response != null) {
			purchase = new MyPurchase();
			if (response.get("id") != null) {
				purchase.setId(response.get("id").toString());
			}
			if (response.get("itemId") != null) {
				purchase.setItemId(response.get("itemId").toString());
			}
			if (response.get("consumed") != null) {
				purchase.setConsumed(Boolean.parseBoolean(response
						.get("consumed").toString()));
			}
		}
		return purchase;
	}

	public List<Purchase> toPurchases(JSONObject response) {
		List<Purchase> purchases = null;
		if(response != null) {
			purchases = new ArrayList<Purchase>();
			JSONArray jsonPurchases = (JSONArray)response.get("purchases");
			for(Object o : jsonPurchases) {
				purchases.add(toPurchase((JSONObject)o));
			}
		}
		return purchases;
	}

}
