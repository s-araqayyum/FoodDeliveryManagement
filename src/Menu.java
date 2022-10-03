import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
public class Menu {
	
	public void CustomerMenu(String name, int login)  {
		
		Customer customer = new Customer();
		
		System.out.println("Welcome, " + name); 
		System.out.println("Which of the following actions would you like to perform?"); 
		System.out.println("1. Order Food");
		System.out.println("2. Check Delivery Status");
		System.out.println("3. Cancel Order");
		System.out.println("4. Give Feedback");
		System.out.println("5. Logout");
		
		 Scanner customerScan = new Scanner(System.in);
		 int customerDecision = customerScan.nextInt();
		    
		    switch(customerDecision) {
		    case 1:
		    	customer.FoodDetails(name, login);
		    	break;
		    case 2:
		    	
		    	for(int i = 0; i < Customer.order.size(); i++) {
		    		if(Customer.order.get(i).getLogin() == login) {
		    		System.out.println();
					System.out.println("Customer login id" + Customer.order.get(i).getLogin());
				    System.out.println("The item you ordered:" + Customer.order.get(i).getItemName());
					System.out.println("The price of the item:" + Customer.order.get(i).getItemPrice());
					System.out.println("The quantity of the item ordered:" + Customer.order.get(i).getQuantity());
					System.out.println("Delivered: " + Customer.order.get(i).isDelivered());
					System.out.println();
		    		}
		    	}
		    	break;
		    case 3:
		    	boolean flag = false;
		    	System.out.println("Displaying all current orders...");
		    	
		    	for(int i = 0; i < Customer.order.size(); i++) {
		    		
		    		if(Customer.order.get(i).isDelivered() == false && Customer.order.get(i).getLogin() == login) {
		    			flag = true;
		    			System.out.println(i + ":: ");
		    			    System.out.println("The item you ordered:" + Customer.order.get(i).getItemName());
							System.out.println("The price of the item:" + Customer.order.get(i).getItemPrice());
							System.out.println("The quantity of the item ordered:" + Customer.order.get(i).getQuantity());
							
							System.out.println(" ");
							System.out.println("Would you like to cancel this specific order? Press 1 for yes and 2 for no");
				    		int cancelDec = customerScan.nextInt();
				    		if (cancelDec == 1) {
				    			Customer.order.remove(i);

				    			String folderName = "CustomerOrderHistory"; 
				    			File directory = new File(folderName);
				    		    if (!directory.exists()){
				    		        directory.mkdir();
				    		    }
				    		    String path = directory.getAbsolutePath();
				    		    
				    		    try {
				    		        FileWriter writingHistory = new FileWriter(path+File.separator+Customer.order.get(i).getLogin()+"History.txt",false);
				    		        for(int k = 0; k < Customer.order.size()-1; k++) {
				    		        writingHistory.write(Customer.order.get(k).getLogin()+"\n"+Customer.order.get(k).getRestaurantName()+"\n"+Customer.order.get(k).getItemName()+"\n"+Customer.order.get(k).getItemPrice()+"\n"+Customer.order.get(k).getQuantity()+"\n"+Customer.order.get(k).isDelivered()+"\n"+Customer.order.get(k).isFeedback()+"\n");
				    		        }
				    		        writingHistory.close();
				    		      } catch (IOException e) {
				    		        System.out.println("An error occurred.");
				    		        e.printStackTrace();
				    		      }
				    			System.out.println("Order deleted successfully.");
				    		}
				    		else if(cancelDec == 2) {
				    			continue;
				    		}
				    		else
				    			System.out.println("Invalid Decision.");
		    		}
		    		}

	    		if(flag == false) {
	    			System.out.println("You have no current orders");
		    	}
		    	break;
		    case 4:
		    	for(int i = 0; i < Customer.order.size(); i ++)
		    	customer.CustomerFeedback(i);
		    	break;
		    case 5:
		    	DataConversion dC = new DataConversion();
		    	System.out.println("Thank you for using Food On Wheels");
		    	dC.ChangeLogin();
		    	dC.RewritePositions();
		    	try {
			    	for(int i = 0; i <DataConversion.rider.size();i++)
						dC.RiderToFile(i);
			    		
			    		for(int i = 0; i <DataConversion.restaurant.size();i++)
							dC.RestaurantToFile(i);
			    		
			    		for(int i = 0; i <DataConversion.customer.size();i++)
							dC.CustomerToFile(i);
				} catch (IOException e) {
					System.out.println("An error has concerned regarding transfering of data to files regarding:: "+e);
				}
		    	
		    	System.exit(0);
		    	break;
		    }
	}

	public void RestaurantMenu(String name, int login) {
		
		Restaurant restaurant = new Restaurant();
		
		System.out.println("Welcome, " + name); 
		
		System.out.println("Which of the following actions would you like to perform?"); 
		System.out.println("1. Add Food Details");
		System.out.println("2. Update Food Details");
		System.out.println("3. Delete Food Details");
		System.out.println("4. Check Food Order");
	    System.out.println("5. Calculate Bills");
	    System.out.println("6. Display Feedback");
	    System.out.println("7. Logout");
	    
	    Scanner restaurantScan = new Scanner(System.in);
	    int restaurantDecision = restaurantScan.nextInt();
	    
	    switch(restaurantDecision) {
	    
	    case 1:
	    	restaurant.addLine(name);
	    	break;
	    case 2:
	    	try {
				restaurant.editLine(name);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	break;
	    case 3:
	    	try {
				restaurant.deleteLine(name);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	break;
	    case 4:
	    	restaurant.checkCurrentOrders(name);
	    	break;
	    case 5:
	    	restaurant.CalculateBills(name);
	    	break;
	    case 6:
	    	restaurant.DisplayFeedBack(name);
	    	break;
	    case 7:
	    	DataConversion dC = new DataConversion();
	    	System.out.println("Thank you for using Food On Wheels");
	    	dC.ChangeLogin();
	    	dC.RewritePositions();
	    	for(int i = 0; i <DataConversion.rider.size();i++) {
				try {
					dC.RiderToFile(i);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    	}
	    		
	    		for(int i = 0; i <DataConversion.restaurant.size();i++) {
					try {
						dC.RestaurantToFile(i);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	    		}
	    		
	    		for(int i = 0; i <DataConversion.customer.size();i++) {
					try {
						dC.CustomerToFile(i);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	    		}
	    	System.exit(0);
	    	break;
	    default:
	    		System.out.println("Invalid Input! Try again.");
	    }
	    
	}
	
	public void RiderMenu(String name) {
		
		Rider rider = new Rider();
		
		System.out.println("Welcome, "+ name);
		
		System.out.println("Which of the following actions would you like to perform?"); 
		System.out.println("1. View Order");
		System.out.println("2. Accept or reject delivery order [Update Delivery Statuses & Cash Collection]");
		System.out.println("3. Logout");
	    
	    Scanner riderScan = new Scanner(System.in);
	    int riderDecision = riderScan.nextInt();
	    
	    switch(riderDecision) {
	    case 1:
	    	rider.ViewOrder();
	    	break;
	    case 2:
	    	try {
				rider.Accept_Or_RejectOrders();
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
	    	break;
	    case 3:
	    	DataConversion dC = new DataConversion();
	    	System.out.println("Thank you for using Food On Wheels");
	    	dC.ChangeLogin();
	    	dC.RewritePositions();
	    	try {
	    		for(int i = 0; i <DataConversion.rider.size();i++)
					dC.RiderToFile(i);
		    		
		    		for(int i = 0; i <DataConversion.restaurant.size();i++)
						dC.RestaurantToFile(i);
		    		
		    		for(int i = 0; i <DataConversion.customer.size();i++)
						dC.CustomerToFile(i);
			} catch (IOException e) {
				System.out.println("An error has concerned regarding transfering of data to files regarding:: "+e);
			}
	    	System.exit(0);
	    	break;
	    	default:
	    		System.out.println("You've chosen an invalid option");
	    }

		
	}
	
	public void AdminMenu() {
		Admin admin = new Admin();
	    Scanner adminScan = new Scanner(System.in);
		
		System.out.println("Welcome, FAST's faculty"); 
		System.out.println("Which of the following actions would you like to perform?"); 
		System.out.println("1. Manage Vendors");
		System.out.println("2. Manage Vendors Products/Food"); 
		System.out.println("3. Manage Customers");
		System.out.println("4. Manage Food Order details");
		System.out.println("5. Manage Payment details");
		System.out.println("6. Manage Riders");
		System.out.println("7. Logout");
	    
	    int adminDecision = adminScan.nextInt();
	    
	    switch(adminDecision) {
	    case 1:
	    	admin.ManageRestaurants();
	    	break;
	    case 2:
	    	admin.ManageVendorProducts();
	    	break;
	    case 3:
	    	admin.ManageCustomers();
	    	break;
	    case 4:
	    	admin.ManageFoodOrderDetails();
	    	break;
	    case 5:
	    	admin.ManagePaymentDetails();
	    	break;
	    case 6:
	    	admin.ManageRiders();
	    	break;
	    case 7:
	    	DataConversion dC = new DataConversion();
	    	System.out.println("Thank you for using Food On Wheels");
	    	dC.ChangeLogin();
	    	dC.RewritePositions();
	    	try {
	    		for(int i = 0; i <DataConversion.rider.size();i++)
					dC.RiderToFile(i);
		    		
		    		for(int i = 0; i <DataConversion.restaurant.size();i++)
						dC.RestaurantToFile(i);
		    		
		    		for(int i = 0; i <DataConversion.customer.size();i++)
						dC.CustomerToFile(i);
			} catch (IOException e) {
				System.out.println("An error has concerned regarding transfering of data to files regarding:: "+e);
			}
	    	System.exit(0);
	    	break;
	    	default:
	    		System.out.println("You've chosen an invalid option");
	    }
	    }
	}

