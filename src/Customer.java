import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Customer extends Account{
	
	private String customerName;
	private String customerAddress;
	private String phoneNumber;
	private int customerLogin;
	private String customerPassword;
	private int ordernumber = 0;
	private boolean blocked;
	private int feedbackPosition = 0;
	static ArrayList<CurrentOrder> order = new ArrayList<CurrentOrder>();
	static ArrayList<Feedback> feedback = new ArrayList<Feedback>();
    
	public Customer(String customerName, String customerAddress, String phoneNumber, int customerLogin, String customerPassword, boolean blocked) {
		
		this.customerName = customerName;
		this.customerAddress = customerAddress;
		this.phoneNumber = phoneNumber;
		this.customerLogin = customerLogin;
		this.customerPassword = customerPassword;
		this.setBlocked(blocked);
		
	}
	
	public Customer() {
	}

	public void FoodDetails(String name, int login) {
		
		System.out.println("Welcome, " + name); 
		System.out.println("Which Restaurant would you like to order from today? Enter the position.");
		
		for(int i = 0; i<DataConversion.restaurant.size(); i++) {
			if(DataConversion.restaurant.get(i).isApproval() == true) {
				System.out.println((i+1)+". "+DataConversion.restaurant.get(i).getRestaurantName());
			}
		}
		
		Scanner userDec = new Scanner(System.in);
		int restaurantDec = userDec.nextInt();
		
		showMenu(restaurantDec-1, login, name);
		
		
	}
	
	public void showMenu(int restaurantDec, int login, String customerName) {
			
		String folderName = "RestaurantMenu"; 
		File directory = new File(folderName);
	    if (!directory.exists()){
	        directory.mkdir();
	    }
	    String path = directory.getAbsolutePath();
		
		try{
	        String line = Files.readAllLines(Paths.get("RestaurantNames.txt")).get(restaurantDec);
	        
	        System.out.println("Welcome to "+line+", what would you like to order today? Enter the position");
	        
			  int i = 1;
		      File restaurantMenu = new File(path+File.separator+line+"Menu.txt");
		      Scanner scanRestaurant = new Scanner(restaurantMenu);
		      while (scanRestaurant.hasNextLine()) {
		      String menuItems = scanRestaurant.nextLine();
		      System.out.println(i+". "+menuItems);
		      i++;
		      
		      }
		      Scanner chooseItem = new Scanner(System.in);
		      int itemChoice = chooseItem.nextInt();
		      
		      String item = Files.readAllLines(Paths.get(path+File.separator+line+"Menu.txt")).get(itemChoice-1);
		      OrderItems(restaurantDec, item,line, login, customerName);
	      } 
	      catch(IOException e){
	        System.out.println(e);
	      }
		
	}

	public void OrderItems(int restaurantDec, String item, String line,int login, String customerName) {
		
		boolean flag = true;
		System.out.println(item);
		while(flag) {
		try {
			
			StringTokenizer tokens = new StringTokenizer(item, ".");  //Tokenizing the item from the menu
			
			while (tokens.hasMoreTokens())   
			{  
				String menuItem = tokens.nextToken(); //Storing the tokens in the specified variable
				String currency = tokens.nextToken(); //Unused currency token
				int price = Integer.parseInt(tokens.nextToken()); //Parsing the third for its value in int
				
				 System.out.println("Would you like to proceed with this order or update it? Press 1 for proceeding, and 2 for updating");
			      Scanner update = new Scanner(System.in);
			      int decision = update.nextInt();
			      
			      if(decision == 1) {
			    	  System.out.println("What quantity of "+menuItem+" would you like?");
			    	  Scanner quantity = new Scanner(System.in);
				      int itemQuantity = quantity.nextInt();
				     
				      price*=itemQuantity;
				      
				      order.add(ordernumber,new CurrentOrder(login, line, menuItem, price, itemQuantity, false, false));
				      ordernumber++;
				      
				      customerOrdertoFile(login, line, menuItem, price, itemQuantity, false, false);
				      
			    	  System.out.println("What else would you like to order today? Press 0 to confirm completion, and 1 to keep ordering");
			    	  Scanner completion = new Scanner(System.in);
				      int completions = completion.nextInt();
				      
				      if(completions == 0) {
				    	  
				    	  flag = false;
				    	  
				    	  
				      }
				      else if(completions == 1) {
				    	  
				    	  showMenu(restaurantDec, login, customerName);
				    	  
				      }
				      else {
				    	  System.out.println("Invalid input");
				      }
			    	  
			      }
			      else if(decision == 2) {
			    	  System.out.println("Updating order...Discarding previous items...");
			    	  FoodDetails(customerName, login);
			      }
			      else {
			    	  System.out.println("That is an invalid option");
			      }
			      
			}
	      }catch(Exception e){
			System.out.println(e);
		}
	  }
	}
	
	
	public void createAccount() {
		
		Scanner customerDetails = new Scanner(System.in);
		
		System.out.print("Enter your name ");
        String customerName = customerDetails.nextLine();
        
        System.out.print("Enter your address ");
        String customerAddress = customerDetails.nextLine();
        
        System.out.print("Enter your phone number ");
        String phoneNumber = customerDetails.nextLine();
        
        System.out.print("Registration almost complete! Enter a password by which you can access this account ");
        String password = customerDetails.nextLine();
        

    	
    	FoodOnWheels fow = new FoodOnWheels();
        DataConversion dC = new DataConversion();
        
        DataConversion.customer.add(dC.fileNumberC, new Customer(customerName, customerAddress, phoneNumber, fow.login, password, false));
        
        dC.fileNumberC++;
        fow.login++;
        fow.setLogin(fow.login);
        
        
        System.out.println("Registration complete! Refresh this page to login");
        
	}

	public void customerOrdertoFile(int login, String line, String menuItem, int price, int itemQuantity, boolean delivered, boolean feedback) {//Login IDS {loginID -Data file} //OrderHistory LoginID+HIstory
		
		String folderName = "CustomerOrderHistory"; 
		File directory = new File(folderName);
	    if (!directory.exists()){
	        directory.mkdir();
	    }
	    String path = directory.getAbsolutePath();
	    
	    try {
	        FileWriter writingHistory = new FileWriter(path+File.separator+login+"History.txt",true);
	        writingHistory.write("\n"+login+"\n"+line+"\n"+menuItem+"\n"+price+"\n"+itemQuantity+"\n"+delivered+"\n"+feedback);
	        writingHistory.close();
	      } catch (IOException e) {
	        System.out.println("An error occurred.");
	        e.printStackTrace();
	      }
		
	}
	
	public void Printorder() {
		
		for (int i = 0 ; i < order.size() ; i++ ) {
			System.out.println();
			System.out.println("Customer login id" + getCustomerLogin());
		    System.out.println("The item you ordered:" + order.get(i).getItemName());
			System.out.println("The price of the item:" + order.get(i).getItemPrice());
			System.out.println("The quantity of the item ordered:" + order.get(i).getQuantity());
			System.out.println("Delivered:" + order.get(i).isDelivered());
			System.out.println();
		}
		}
	
	public void CustomerFeedback(int i) {

		DataConversion dC = new DataConversion();
		
		boolean flag = true;
		if (order.get(i).feedback == false && order.get(i).isDelivered() == true && flag!=false) {
			System.out.println();
		    System.out.println("The item you ordered:" + Customer.order.get(i).getItemName());
			System.out.println("The price of the item:" + Customer.order.get(i).getItemPrice());
			System.out.println("The quantity of the item ordered:" + Customer.order.get(i).getQuantity());
			System.out.println();
			
			System.out.println("Would you like to give feedback for this order? Press 1 for Yes and 2 for No");
			
			Scanner scan = new Scanner(System.in);
			int choice = scan.nextInt();
			switch(choice) {
			case 1:
				Scanner CFeedback = new Scanner(System.in);
				System.out.println("Enter your feedback:: ");
				String Feedback = CFeedback.nextLine(); 
				feedback.add(dC.fileNumberF, new Feedback(Customer.order.get(i).getRestaurantName(), Feedback));
				dC.fileNumberF++;
				
				try {
					FileWriter file = new FileWriter("Feedback.txt", true);
					for(int i1 = 0; i1<feedback.size(); i1++) {
						file.write(Customer.order.get(i).getRestaurantName()+"\n"+Feedback+"\n");
						Customer.order.get(i).setFeedback(true);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				
				break;
			case 2:
				System.out.println("Returning you to general menu...");
				flag = false;
				return;
			}
		}
	}

	public int getCustomerLogin() {
		return customerLogin;
	}
	public void setCustomerLogin(int customerLogin) {
		this.customerLogin = customerLogin;
	}
	public String getCustomerPassword() {
		return customerPassword;
	}
	public void setCustomerPassword(String customerPassword) {
		this.customerPassword = customerPassword;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerAddress() {
		return customerAddress;
	}
	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public boolean isBlocked() {
		return blocked;
	}
	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}
	public int getOrdernumber() {
		return ordernumber;
	}

	public void setOrdernumber(int ordernumber) {
		this.ordernumber = ordernumber;
	}

	public int getFeedbackPosition() {
		return feedbackPosition;
	}

	public void setFeedbackPosition(int feedbackPosition) {
		this.feedbackPosition = feedbackPosition;
	}

	public static ArrayList<CurrentOrder> getOrder() {
		return order;
	}

	public static void setOrder(ArrayList<CurrentOrder> order) {
		Customer.order = order;
	}
}
