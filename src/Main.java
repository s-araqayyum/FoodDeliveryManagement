//Sara Qayyum | 20I-0556 | BSSE-R
//Hasham ul Haq | 20I-0752 | BSSE-R
public class Main {

	public static void main(String[] args) {
		
		DataConversion convertingFilesToArrays = new DataConversion();
		convertingFilesToArrays.CustomerToArray();
		convertingFilesToArrays.RestaurantToArray();
		convertingFilesToArrays.RiderToArray();
		convertingFilesToArrays.OrderToArray();
		
		convertingFilesToArrays.ReplaceLogin();
		
		convertingFilesToArrays.ReenterPositions();
		
		convertingFilesToArrays.FeedbackToArray();
		
		for(int i = 0; i<Customer.order.size(); i++)
		convertingFilesToArrays.ReenterOrderNum(Customer.order.get(i).getLogin());

				try {
					FoodOnWheelsGUI frame = new FoodOnWheelsGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				};
	}
}