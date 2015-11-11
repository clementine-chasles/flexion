package flexion.integrationteam.domain;

import com.flexionmobile.codingchallenge.integration.Purchase;

public class MyPurchase implements Purchase {
	
	private boolean isConsumed;
	private String id;
	private String itemId;
	
	public MyPurchase() {}
	
	public MyPurchase(boolean isConsumed, String id, String itemId) {
		this.isConsumed = isConsumed;
		this.id = id;
		this.itemId = itemId;
	}

	public boolean getConsumed() {
		return isConsumed;
	}

	public String getId() {
		return id;
	}

	public String getItemId() {
		return itemId;
	}

	public void setConsumed(boolean isConsumed) {
		this.isConsumed = isConsumed;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	
	@Override
	public String toString() {
		return "\nid : " + id + ", itemId : " + itemId + ", isConsumed ? " + isConsumed + "\n";
	}

}
