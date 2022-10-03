import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Restaurant extends Account{

	private String restaurantName;
	private String restaurantAddress;
	private String restaurantContact;
	private int loginID;
	private String password;
	private boolean approval;
	private double totalBills = 0;

	public Restaurant(String restaurantName, String restaurantAddress, String restaurantContact, int loginID, String password, boolean approval) {
		this.restaurantName = restaurantName;
		this.restaurantAddress = restaurantAddress;
		this.restaurantContact = restaurantContact;
		this.loginID = loginID;
		this.password = password;
		this.approval = approval;
	}

		public Restaurant() {
	}

		public void addLine(String restaurantName) {
			
			File directory = new File("RestaurantMenu");
			String path = directory.getAbsolutePath();
			
			
			FileWriter fw = null;
	        BufferedWriter bw = null;
	        PrintWriter pw = null;

	        try {
	            fw = new FileWriter(path + File.separatorChar + restaurantName + "Menu.txt", true);
	            bw = new BufferedWriter(fw);
	            pw = new PrintWriter(bw);
	            
	            Scanner menuDetails = new Scanner(System.in);
	            System.out.println("Enter the name of the new item");
	            String itemName = menuDetails.nextLine();
	            System.out.println("Enter the price of the preceding item");
	            String itemPrice = menuDetails.nextLine();
	            
	            System.out.println("Item added successfully");
	            
	            pw.println(itemName+". PKR."+itemPrice);
	            pw.flush();
	          
				bw.close();
				fw.close();
				pw.close();
				
	        } catch (IOException io) {
	            	System.out.println(io);
	            }
	        }
		
		public void deleteLine(String restaurantName) throws IOException {
			
			File directory = new File("RestaurantMenu");
			String path = directory.getAbsolutePath();
			
			  System.out.println("Enter the name of the item you would like removed");
		      
		      Scanner deleteItem = new	 Scanner(System.in);
		      String menuToBeDeleted = deleteItem.nextLine();
		      
		      File inputFile = new File(path +File.separatorChar + restaurantName + "Menu.txt");
              File tempFile = new File(path + File.separatorChar + restaurantName + "tempfile.txt");
		      
		      BufferedReader br = new BufferedReader(new FileReader(inputFile));  
		      
		      String line;  

              BufferedReader reader = new BufferedReader(new FileReader(inputFile));
              BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

			
		      try {
			      while((line=br.readLine())!=null)  
			      {
			    	  if (line.toLowerCase().contains(menuToBeDeleted.toLowerCase())) {
			                 
			                 System.out.println("Deleting item:: "+ line);
			                 
			                 String currentLine;

			                     while((currentLine = reader.readLine()) != null) {
			                    	
			                    	 
			                    	 if(currentLine.equals(line)) {
			                        	 continue;
			                    	 }
			                    	 else
			                         writer.write(currentLine + "\n");
			                     }
			                     
			                     break;
			                 }
			      	}	
			      br.close();
		      }catch(IOException e) {
		    	  System.out.println(e);
		      }
		      writer.close(); 
              reader.close(); 
              
              try {
		            FileReader fr = new FileReader(tempFile);
		            FileWriter fw = new FileWriter(inputFile);
		            String str = "";
		  
		            int i;
		            while ((i = fr.read()) != -1) {
		                str += (char)i;
		            }
		            fw.write(str);
		            fr.close();
		            fw.close();
		  
		        }
		  
		        catch (IOException e) {
		            System.out.println( "There are some IOException");
		        }
		      
		}
		
		public void editLine(String restaurantName) throws IOException {
			
			System.out.println("To edit an item, you will have to delete them item, and re-add it. Would you like to continue [1 for yes, 2 for No?]");
			
			Scanner editItem = new Scanner(System.in);
			int dec = editItem.nextInt();
			
			if(dec == 1) {

				deleteLine(restaurantName);
				addLine(restaurantName);
				
			}
			else if (dec == 2) {
				System.out.println("Returning you to main menu...");
				return;
			}
			else {
				System.out.println("Invalid option");
			}
			
		}
		
		public void createAccount() {
			
			Scanner restaurantDetails = new Scanner(System.in);
			System.out.print("Enter the name of your establishment ");
	        String restaurantName = restaurantDetails.nextLine();
	        
	        System.out.print("Enter your establishment's resdiential address ");
	        String restaurantAddress = restaurantDetails.nextLine();
	        
	        System.out.print("Enter your contact details [Phone Number/Landline] ");
	        String phoneNumber = restaurantDetails.nextLine();
	        
	        System.out.print("Registration almost complete! Enter a password by which you can access this account ");
	        String password = restaurantDetails.nextLine();
	        
	        System.out.println("You will be notified if the admin accepts your registration. We will get back to your shortly.");
	        
	        FoodOnWheels fow = new FoodOnWheels();
	        
	        DataConversion dC = new DataConversion();
	        
	        DataConversion.restaurant.add(dC.fileNumberR, new Restaurant(restaurantName, restaurantAddress, phoneNumber, fow.login, password, false));
	        
	        dC.fileNumberR++;
	        fow.login++;
	        
		}
		
		public void checkCurrentOrders(String name) {
			
			boolean currentExisitng = false;
			for(int i = 0; i< Customer.order.size(); i++) {
				if(Customer.order.get(i).getRestaurantName().equals(name) && Customer.order.get(i).isDelivered()==false) {
					
					currentExisitng = true;
					System.out.println("Customer Login:: " + Customer.order.get(i).getLogin());
				    System.out.println("The item ordered:: " + Customer.order.get(i).getItemName());
					System.out.println("The price of item:: " + Customer.order.get(i).getItemPrice());
					System.out.println("The quantity of the item ordered:: " + Customer.order.get(i).getQuantity());
					
					System.out.println(" ");
					
				}
			}
			if(currentExisitng == false) {
				System.out.println("You have no current orders, at the moment.");
			}
			
		}	
		
		public void UpdateFoodDelivery(String name) {
			
			checkCurrentOrders(name);
			
			Scanner delivery = new Scanner(System.in);
			
			for (int i = 0 ; i <Customer.order.size(); i++ ) {
				if(Customer.order.get(i).getLogin() == loginID && Customer.order.get(i).isDelivered() == false) {
					System.out.println("Would you like to set this order as delivered? Press 1 to mark as delivered, 2 to mark as undelivered and 3 to continue");
					int restaurantDec = delivery.nextInt();
					
					switch(restaurantDec) {
					case 1:
						if(Customer.order.get(i).isDelivered() == false) {
							Customer.order.get(i).setDelivered(true);
							System.out.println("Order marked as delivered");
						}
						else
							System.out.println("Order already marked delivered successfully");
						break;
					case 2:
						if(Customer.order.get(i).isDelivered() == true) {
							Customer.order.get(i).setDelivered(false);
							System.out.println("Order marked as undelivered successfully");
						}
						else
							System.out.println("Order already marked delivered");
						break;
					case 3:
						System.out.println("Returning to main menu...");
						System.out.println(" ");
						return;
						default:
							System.out.println("Invalid input.");
					}
				}
			}
			
			
		}
		
		public void CalculateBills(String restaurantName) {
			
			for(int i = 0; i < Customer.order.size(); i++) {
				if(Customer.order.get(i).getRestaurantName().equalsIgnoreCase(restaurantName)) {
					totalBills+=Customer.order.get(i).getItemPrice();
				}
			}
			
			System.out.println("Total earnings for "+restaurantName+" is PKR."+totalBills);
			
		}
		
		public void DisplayFeedBack(String name) {
			
			boolean flag = false;
			
			for(int i = 0; i < Customer.feedback.size(); i ++) {
				if(Customer.feedback.get(i).getRestaurantName().equalsIgnoreCase(name)) {
					flag = true;
					System.out.println(Customer.feedback.get(i).getDescription());
				}
			}
			
			if(flag == false) {
				System.out.println("No feedback has been given yet");
			}
		}
	
	public String getRestaurantName() {
		return restaurantName;
	}

	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}

	public int getLoginID() {
		return loginID;
	}

	public void setLoginID(int loginID) {
		this.loginID = loginID;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRestaurantAddress() {
		return restaurantAddress;
	}

	public void setRestaurantAddress(String restaurantAddress) {
		this.restaurantAddress = restaurantAddress;
	}

	public String getRestaurantContact() {
		return restaurantContact;
	}

	public void setRestaurantContact(String restaurantContact) {
		this.restaurantContact = restaurantContact;
	}
	public boolean isApproval() {
		return approval;
	}

	public void setApproval(boolean approval) {
		this.approval = approval;
	}

	public double getTotalBills() {
		return totalBills;
	}

	public void setTotalBills(double totalBills) {
		this.totalBills = totalBills;
	}

	}

