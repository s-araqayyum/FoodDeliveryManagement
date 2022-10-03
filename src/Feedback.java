
public class Feedback {
	
	private String description;
	private String restaurantName;
	
	public Feedback(String restaurantName, String description) {
		this.description = description;
		this.restaurantName = restaurantName;
	}
	
	public String getRestaurantName() {
		return restaurantName;
	}
	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

}
