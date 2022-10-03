
public class CurrentOrder {

	private String itemName;
	private double itemPrice;
	private int quantity;
	private String restaurantName;
	private boolean delivered;
	private int login;
	boolean feedback;
	
	public CurrentOrder(int login, String restaurantName, String itemName, double itemPrice, int quantity, boolean delivered, boolean feedback) {
		
		this.setLogin(login);
		this.itemName = itemName;
		this.itemPrice = itemPrice;
		this.quantity = quantity;
		this.restaurantName = restaurantName;
		this.delivered = delivered;
		this.feedback = feedback;
		
	}
	
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public double getItemPrice() {
		return itemPrice;
	}
	public void setItemPrice(double itemPrice) {
		this.itemPrice = itemPrice;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getRestaurantName() {
		return restaurantName;
	}

	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}

	public boolean isDelivered() {
		return delivered;
	}

	public void setDelivered(boolean delivered) {
		this.delivered = delivered;
	}

	public int getLogin() {
		return login;
	}

	public void setLogin(int login) {
		this.login = login;
	}
	public boolean isFeedback() {
		return feedback;
	}

	public void setFeedback(boolean feedback) {
		this.feedback = feedback;
	}

}
