import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
public class Admin {
	
	public void ManageCustomers() {
		
		Scanner scan = new Scanner(System.in);
		System.out.println("Displaying blocked accounts...");
		boolean allUnblocked = true;
		
		for (int i = 0 ; i < DataConversion.customer.size() ; i++) {
			if (DataConversion.customer.get(i).isBlocked() == true) {
				allUnblocked = false;
			System.out.println("Customer login: " + DataConversion.customer.get(i). getCustomerLogin());
			System.out.println("Customer Name: " + DataConversion.customer.get(i).getCustomerName());
			System.out.println("Customer Address: " + DataConversion.customer.get(i).getCustomerAddress());
			System.out.println("Customer Phone Number: " + DataConversion.customer.get(i).getPhoneNumber());
			
			System.out.println("Press 1 to unblock an account"); 
			System.out.println("Press 2 to skip this account");
			System.out.println("Press 3 to exit without changing any account");
			
			int choice = scan.nextInt();
			
			switch(choice) {
			case 1:
				System.out.println("Enter the login ID of the account you would like to unblock");
				int login = scan.nextInt();
				
				for (int j = 0 ; j < DataConversion.customer.size() ; j++) {
					if(DataConversion.customer.get(j).isBlocked() == true && DataConversion.customer.get(j).getCustomerLogin() == login) {
						DataConversion.customer.get(j).setBlocked(false);
						System.out.println("Account has been unblocked successfully");
					}
				}
				break;
			case 2:
				continue;
			case 3:
				return;
			default:
				System.out.println("Invalid input");
			}
			}
		}

		if(allUnblocked == true) {
		System.out.println("No accounts are blocked at the moment");
		}
		}
	
	public void ManageRestaurants() {
		
		System.out.println("Displaying all unapproved restaurant accounts...");
		Scanner scan = new Scanner(System.in);
		
		for (int i = 0 ; i < DataConversion.restaurant.size() ; i++) {
			if (DataConversion.restaurant.get(i).isApproval() == false) {
			System.out.println("Restaurant [Possible] login: " + DataConversion.restaurant.get(i).getLoginID());
			System.out.println("Restaurant Name: " + DataConversion.restaurant.get(i).getRestaurantName());
			System.out.println("Restaurant Address: " + DataConversion.restaurant.get(i).getRestaurantAddress());
			System.out.println("Restaurant Phone Number: " + DataConversion.restaurant.get(i).getRestaurantContact());
			
			System.out.println("Press 1 to approve this account"); 
			System.out.println("Press 2 to continue without updating this account");
			System.out.println("Press 3 to exit without updating");
			
			int choice = scan.nextInt();
			
			switch(choice) {
			case 1:
						DataConversion.restaurant.get(i).setApproval(true);
				try {
					FileWriter file = new FileWriter("RestaurantNames.txt",true);
					file.write("\n"+DataConversion.restaurant.get(i).getRestaurantName());
					file.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
						System.out.println("Account has been approved successfully");
				break;
			case 2:
				continue;
			case 3:
				return;
			default:
				System.out.println("Invalid input");
			}
			
			}
		}
		}
	
	public void ManageRiders() {

		Scanner scan = new Scanner(System.in);
		System.out.println("Displaying all unapproved rider accounts...");
		
		for (int i = 0 ; i < DataConversion.rider.size() ; i++) {
			if (DataConversion.rider.get(i).isApproval() == false) {
			System.out.println("Rider login: " + DataConversion.rider.get(i).getRiderLogin());
			System.out.println("Rider Name: " + DataConversion.rider.get(i).getRiderName());
			System.out.println("Rider Phone Number: " + DataConversion.rider.get(i).getRiderPhoneNumber());
			
			System.out.println("Press 1 to approve this account"); 
			System.out.println("Press 2 to continue without updating this account");
			System.out.println("Press 3 to exit without updating");
			
			int choice = scan.nextInt();
			
			switch(choice) {
			case 1:
				System.out.println("Enter the login ID of the rider account you would like to approve");
				int login = scan.nextInt();
				
				for (int j = 0 ; j < DataConversion.rider.size() ; j++) {
					if(DataConversion.rider.get(j).isApproval() == false && DataConversion.rider.get(j).getRiderLogin() == login) {
						DataConversion.rider.get(j).setApproval(true);
						System.out.println("Account has been approved successfully");
					}
				}
				break;
			case 2:
				continue;
			case 3:
				return;
			default:
				System.out.println("Invalid input");
			}
			
		}
		}
	}
	
	public void ManageVendorProducts() {
		
		Scanner menuManagement = new Scanner(System.in);
		Scanner menuDec = new Scanner(System.in);
		
		Restaurant restaurant = new Restaurant();
		
		System.out.println("Enter the name of the restaurant you would like to manage");
		String adminDecision = menuManagement.nextLine();
		boolean restaurantExists = false;
		
		for(int i = 0; i<DataConversion.restaurant.size(); i++) {
		if(adminDecision.equalsIgnoreCase(DataConversion.restaurant.get(i).getRestaurantName())) {
			restaurantExists = true;
			 System.out.println("Would you like to [1] add an item to their menu, [2] delete an item, [3] update an item");
			 int adminResDec = menuDec.nextInt();
			 
			 switch(adminResDec) {
			 case 1:
				 restaurant.addLine(DataConversion.restaurant.get(i).getRestaurantName());
				 break;
			 case 2:
				 try {
					restaurant.deleteLine(DataConversion.restaurant.get(i).getRestaurantName());
				} catch (IOException e) {
					System.out.println("An error has occured concerning deleting an item about:: "+e);
				}
				 break;
			 case 3:
				 try {
					restaurant.editLine(DataConversion.restaurant.get(i).getRestaurantName());
				} catch (IOException e) {
					System.out.println("An error has occured concerning updating an item about:: "+e);
				}
				 break;
				 default:
					 System.out.println("Invalid input.");
			 }
			 
		}
		}
		
	}
	
	public void ManageFoodOrderDetails() {
		
		Customer customer = new Customer();
		System.out.println("Displaying all orders...");
		for(int i = 0 ; i<Customer.order.size(); i++) {
			System.out.println(i+1);
			System.out.println("Customer Login:: " + Customer.order.get(i).getLogin());
		    System.out.println("The item ordered:: " + Customer.order.get(i).getItemName());
			System.out.println("The price of item:: " + Customer.order.get(i).getItemPrice());
			System.out.println("The quantity of the item ordered:: " + Customer.order.get(i).getQuantity());
			
			System.out.println(" ");
		}
		
		System.out.println("Would you like to delete any order? If so, enter the position of the order, if not, press 0");
		
		Scanner scan = new Scanner(System.in);
		int decision = scan.nextInt();
		decision--;
		
		if(decision == -1) {
			System.out.println("Returning to general menu...");
			return;
		}
		else {
			System.out.println("Removing order...");
			Customer.order.remove(decision);
			System.out.println("Order removed successfully");
		}
	}
	
	public void ManagePaymentDetails() {
		
		System.out.println("Displaying total restaurant earnings...");
		for(int i = 0 ; i<DataConversion.restaurant.size(); i++) {
			if(DataConversion.restaurant.get(i).isApproval() == true) {
			System.out.println();
			DataConversion.restaurant.get(i).CalculateBills(DataConversion.restaurant.get(i).getRestaurantName());
			System.out.println();
			}
		}
		
	}
}