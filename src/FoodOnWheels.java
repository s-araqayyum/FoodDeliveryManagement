import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class FoodOnWheels {

	final int adminLogin = 0; //Static admin login
	final String adminPassword = "SaraHasham"; //Static admin password [Unchangeable]
	static int login = 131;
	private static int counter = 0;

		public void Login(int login, String Password) throws IOException {
            int i;
            JFrame f=new JFrame(); 

                    if (adminLogin == (login) && adminPassword.equals(Password)) {
                    	 
                    	    JOptionPane.showMessageDialog(f,"Proceed to console to continue!");  
        				while(true) {
                        	Menu admin = new Menu();
                        	admin.AdminMenu();
                        	}}
                    
                
                for (i = 0; i < DataConversion.customer.size(); i++) {
                    if (DataConversion.customer.get(i).getCustomerLogin() == (login) && DataConversion.customer.get(i).getCustomerPassword().equals(Password)) {
                    	if(DataConversion.customer.get(i).isBlocked() == false) {
                    		JOptionPane.showMessageDialog(f,"Proceed to console to continue!");  
                    		while(true) {
                    	Menu customer = new Menu();
                    	customer.CustomerMenu(DataConversion.customer.get(i).getCustomerName(), DataConversion.customer.get(i).getCustomerLogin());
                    		}
                    	}
                    	else
                    		System.out.println("Your account has been blocked for failure of payment. If you think this is a mistake, contact foodonwheels@gamil.com");
                    }
                }
                for (i = 0; i < DataConversion.restaurant.size(); i++) {
                	if (DataConversion.restaurant.get(i).getLoginID() == (login) && DataConversion.restaurant.get(i).getPassword().equals(Password)) {
                		JOptionPane.showMessageDialog(f,"Proceed to console to continue!");  
                		while(true) {
                		Menu restaurant = new Menu();
                    	restaurant.RestaurantMenu(DataConversion.restaurant.get(i).getRestaurantName(), DataConversion.restaurant.get(i).getLoginID());
                	}
                    }
                }
                for (i = 0; i < DataConversion.rider.size(); i++) {
                    if (DataConversion.rider.get(i).getRiderLogin() == (login) && DataConversion.rider.get(i).getRiderPassword().equals(Password)) {
                    	JOptionPane.showMessageDialog(f,"Proceed to console to continue!");  
                    	while(true) {
                    	Menu rider = new Menu();
                    	rider.RiderMenu(DataConversion.rider.get(i).getRiderName());
                    	}
                }
                }
                
                UnsuccessfulAttempt us = new UnsuccessfulAttempt();
				us.setVisible(true);
                
        }

           

			public void RegisterAccount(){

				
                System.out.println("Registration for an Account on Food on Wheels");
                System.out.println("Enter 1 to make a Customer account");
                System.out.println("Enter 2 to make a Restaurant account");
                System.out.println("Enter 3 to make a Rider account");

                Scanner Registerchoice = new Scanner(System.in);
                int choice = Registerchoice.nextInt();


                switch(choice){
                    case 1:
                    	counter++;
                    	Customer registerCustomer = new Customer();
                    	registerCustomer.createAccount();
                    	 EndingProgram();
                        break;

                    case 2:
                    	counter++;
                    	Restaurant regRes = new Restaurant();
                    	regRes.createAccount();
                    	 EndingProgram();
                        break;
                    case 3:
                    	counter++;
                    	Rider regRider = new Rider();
                    	regRider.createAccount();
                    	EndingProgram();
                        break;
                        
                    default:
                        System.out.println("Invalid Input.");

                }
                
			}
			
			public void EndingProgram() {
				
				DataConversion dC = new DataConversion();
				
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
            	
			}

	        public int getLogin() {
			return login;
		}
		public void setLogin(int login) {
			this.login = login;
		}



		public int getCounter() {
			return counter;
		}



		public void setCounter(int counter) {
			this.counter = counter;
		}
        }