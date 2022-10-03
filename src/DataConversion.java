import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class DataConversion {

    static ArrayList<Customer> customer = new ArrayList<Customer>();
    static ArrayList<Restaurant> restaurant = new ArrayList<Restaurant>();
    static ArrayList<Rider> rider = new ArrayList<Rider>();
    int fileNumberC = customer.size(); //allows increments in array-list per file added 
	int fileNumberR = restaurant.size() ;
    int fileNumberD = rider.size() ;
    int fileNumberO = 0;
    int fileNumberF = 0;
    
	public void CustomerToArray() {
		
		File dir = new File("CustomerData");
		File[] files = dir.listFiles(); // Fetching all the files
		
		for (File file : files) {
          if(file.isFile()) {
        	  
              BufferedReader inputStream = null;
              String name, address, phoneNumber, password,login, blocked;
              
              try {
                  inputStream = new BufferedReader(new FileReader(file));
                  
                  Scanner scanFile = new Scanner(file);
                  while(scanFile.hasNextLine()) {
                 
                  	//converting data from array to attributes
                  	name=scanFile.nextLine();
                  	address=scanFile.nextLine();
                  	phoneNumber=scanFile.nextLine();
                  	login = scanFile.nextLine();
                  	password=scanFile.nextLine();
                  	blocked = scanFile.nextLine();
                  	
                  	int customerLogin = Integer.parseInt(login);
                  	boolean isBlocked = Boolean.parseBoolean(blocked);
                  	
                  	customer.add(fileNumberC, new Customer(name, address, phoneNumber, customerLogin, password, isBlocked));
                  	fileNumberC++;
                  	
                  }
                  }catch(Exception e) {  	
                	  System.out.println(e);
              }
              }
          }
		
		
	}
	
	public void RestaurantToArray() {
		
		File dir = new File("RestaurantData");
		File[] files = dir.listFiles(); // Fetching all the files
		
		for (File file : files) {
          if(file.isFile()) {
        	  
              BufferedReader inputStream = null;
              String name, address, phoneNumber, password,login, approvals;
              try {
                  inputStream = new BufferedReader(new FileReader(file));
                  
                  Scanner scanFile = new Scanner(file);
                  while(scanFile.hasNextLine()) {
                 
                  	//converting data from array to attributes
                  	name=scanFile.nextLine();
                  	address=scanFile.nextLine();
                  	phoneNumber=scanFile.nextLine();
                  	login = scanFile.nextLine();
                  	password=scanFile.nextLine();
                  	approvals = scanFile.nextLine();
                  	
                  	int restaurantLogin = Integer.parseInt(login);
                  	boolean approval = Boolean.parseBoolean(approvals);
                  	
                  	restaurant.add(fileNumberR, new Restaurant(name, address, phoneNumber, restaurantLogin, password, approval));
                  	fileNumberR++;
                  	
                  }
                  
                  inputStream.close();
                  }catch(Exception e) {  	
                	  System.out.println(e);
                  }
              }
          }
		
		
		
	}
	
	
	public void RiderToArray() {
		
		File dir = new File("RiderData");
		File[] files = dir.listFiles(); // Fetching all the files
		
		for (File file : files) {
          if(file.isFile()) {
        	  
              BufferedReader inputStream = null;
              String name, phoneNumber, password,login, approvals;
              
              try {
                  inputStream = new BufferedReader(new FileReader(file));
                  
                  Scanner scanFile = new Scanner(file);
                  while(scanFile.hasNextLine()) {
                 
                  	//converting data from array to attributes
                  	name=scanFile.nextLine();
                  	phoneNumber=scanFile.nextLine();
                  	login = scanFile.nextLine();
                  	password=scanFile.nextLine();
                  	approvals=scanFile.nextLine();
                  	
                  	int riderLogin = Integer.parseInt(login);
                	boolean approval = Boolean.parseBoolean(approvals);
                  	
                  	rider.add(fileNumberD, new Rider(name, phoneNumber, riderLogin, password, approval));
                  	fileNumberD++;
                  	
                  }
                  
                  inputStream.close();
                  }catch(Exception e) {  	
                	  System.out.println(e);
                  }
               }
			}
		}
	
	public void CustomerToFile(int i) throws IOException {
		
		
		File folder = new File("CustomerData");
		String path = folder.getAbsolutePath();
		
		
		File oldInputFile = new File(path + File.separatorChar + customer.get(i).getCustomerLogin()+".txt");
		oldInputFile.delete();
		
		File newInputFile = new File(path + File.separatorChar + customer.get(i).getCustomerLogin()+".txt"); 

		try {
		    FileWriter f2 = new FileWriter(newInputFile, false);
		    f2.write(customer.get(i).getCustomerName()+"\n"+customer.get(i).getCustomerAddress()+"\n"+customer.get(i).getPhoneNumber()+"\n"
    			 	+customer.get(i).getCustomerLogin()+"\n"+customer.get(i).getCustomerPassword()+"\n"+customer.get(i).isBlocked());
		    f2.close();
		} catch (IOException e) {
		    e.printStackTrace();
		}         
	}
	
	public void RiderToFile(int i) throws IOException {
		
		File folder = new File("RiderData");
		String path = folder.getAbsolutePath();
		
		
		File oldInputFile = new File(path + File.separatorChar + rider.get(i).getRiderLogin()+".txt");
		oldInputFile.delete();
		
		File newInputFile = new File(path + File.separatorChar + rider.get(i).getRiderLogin()+".txt"); 

		try {
		    FileWriter f2 = new FileWriter(newInputFile, false);
		    f2.write(rider.get(i).getRiderName()+"\n"+rider.get(i).getRiderPhoneNumber()+"\n"+rider.get(i).getRiderLogin()+"\n"
    			 	+rider.get(i).getRiderPassword()+"\n"+rider.get(i).isApproval());
		    f2.close();
		} catch (IOException e) {
		    e.printStackTrace();
		}         
		}
	
	public void RestaurantToFile(int i) throws IOException {
		
		File folder = new File("RestaurantData");
		String path = folder.getAbsolutePath();
		

		File oldInputFile = new File(path + File.separatorChar + restaurant.get(i).getLoginID()+".txt"); 
		oldInputFile.delete();
		
		File newInputFile = new File(path + File.separatorChar + restaurant.get(i).getLoginID()+".txt"); 

		try {
		    FileWriter f2 = new FileWriter(newInputFile, false);
		    f2.write(restaurant.get(i).getRestaurantName()+"\n"+restaurant.get(i).getRestaurantAddress()+"\n"+restaurant.get(i).getRestaurantContact()+"\n"
    			 	+restaurant.get(i).getLoginID()+"\n"+restaurant.get(i).getPassword()+"\n"+restaurant.get(i).isApproval());
		    f2.close();
		} catch (IOException e) {
		    e.printStackTrace();
		}           
	}
	
	public void OrderToArray() {

		File dir = new File("CustomerOrderHistory");
		File[] files = dir.listFiles(); // Fetching all the files
		
		for (File file : files) {
          if(file.isFile()) {
        	  
              BufferedReader inputStream = null;
              String login, restaurantName, menuItem, itemPrice, itemQuantity, delivered, feedback;
              
              try {
                  inputStream = new BufferedReader(new FileReader(file));
                  
                  Scanner scanFile = new Scanner(file);
                  while(scanFile.hasNextLine()) {
                 
                  	//converting data from array to attributes
                  	login = scanFile.nextLine();
                  	restaurantName = scanFile.nextLine();
                  	menuItem = scanFile.nextLine();
                  	itemPrice = scanFile.nextLine();
                  	itemQuantity = scanFile.nextLine();
                  	delivered = scanFile.nextLine();
                  	feedback = scanFile.nextLine();
                  	
                  	int orderLogin = Integer.parseInt(login);
                  	double price = Double.parseDouble(itemPrice);
                  	int quantity = Integer.parseInt(itemQuantity);
                	boolean isDelivered = Boolean.parseBoolean(delivered);
                	boolean givenFeedback = Boolean.parseBoolean(feedback);
                  	
                  	Customer.order.add(fileNumberO, new CurrentOrder(orderLogin, restaurantName, menuItem, price, quantity, isDelivered, givenFeedback));
                  	fileNumberO++;
                  	
                  }
                  
                  inputStream.close();
                  }catch(Exception e) {  	
                	  System.out.println(e);
                  }
               }
			}
		}
	
	public void ChangeLogin()  {
		
		FoodOnWheels fow = new FoodOnWheels();
		
		File oldInputFile = new File("loginNumber.txt");
		oldInputFile.delete();
		
		File newInputFile = new File("loginNumber.txt"); 

		try {
		    FileWriter f2 = new FileWriter(newInputFile, false);
		    int login = fow.login + fow.getCounter() + 1;
		    f2.write(String.valueOf(login));
		    f2.close();
		} catch (IOException e) {
		    e.printStackTrace();
		}    
		
	}
	
	public void ReplaceLogin() {
		
		FoodOnWheels fow = new FoodOnWheels();
		
		try {
		      File myObj = new File("loginNumber.txt");
		      Scanner myReader = new Scanner(myObj);
		      
		      while (myReader.hasNextLine()) {
		        String data = myReader.nextLine();
		        fow.setLogin(Integer.parseInt(data));
		      }
		      
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
	}
	
	public void ReenterPositions() {
		
		try {
		      File myObj = new File("Positions.txt");
		      Scanner myReader = new Scanner(myObj);
		      while (myReader.hasNextLine()) {
		        String C = myReader.nextLine();
		        String R = myReader.nextLine();
		        String D = myReader.nextLine();
		        String O = myReader.nextLine();
		        
		        fileNumberC = Integer.parseInt(C);
		        fileNumberR = Integer.parseInt(R);
		        fileNumberD = Integer.parseInt(D);
		        fileNumberO = Integer.parseInt(O);
		        
		      }
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		
	}
	
	public void RewritePositions() {
			
			
			File oldInputFile = new File("Positions.txt");
			oldInputFile.delete();
			
			File newInputFile = new File("Positions.txt"); 

			try {
			    FileWriter f2 = new FileWriter(newInputFile, false);
			    f2.write(fileNumberC+"\n"+fileNumberR +"\n"+fileNumberD+"\n"+fileNumberO);
			    f2.close();
			} catch (IOException e) {
			    e.printStackTrace();
			}         
	}
	
	public void ReenterOrderNum(int login) {
		
		Customer customers = new Customer();
		
		File folder = new File("CustomerOrderHistory");
		String path = folder.getAbsolutePath();
		
		Path paths = Paths.get(path + File.separatorChar + login+"History.txt");
        long lines = 0;
        try {
            lines = Files.lines(paths).count();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        int numberOfOrders = (int) (lines/7);
        
        customers.setOrdernumber(numberOfOrders + 1);
    	
	}
	
	public void FeedbackToArray() {
		
		try {
		      File myObj = new File("Feedback.txt");
		      Scanner myReader = new Scanner(myObj);
		      while (myReader.hasNextLine()) {
		        String restaurantName = myReader.nextLine();
		        String description = myReader.nextLine();
		        
		        Customer.feedback.add(fileNumberF, new Feedback(restaurantName, description));
		        fileNumberF++;
		      }
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
	}
	
	public static ArrayList<Customer> getCustomer() {
		return customer;
	}

	public static void setCustomer(ArrayList<Customer> customer) {
		DataConversion.customer = customer;
	}

	public static ArrayList<Restaurant> getRestaurant() {
		return restaurant;
	}

	public static void setRestaurant(ArrayList<Restaurant> restaurant) {
		DataConversion.restaurant = restaurant;
	}

	public static ArrayList<Rider> getRider() {
		return rider;
	}

	public static void setRider(ArrayList<Rider> rider) {
		DataConversion.rider = rider;
	}

	public int getFileNumberC() {
		return fileNumberC;
	}

	public void setFileNumberC(int fileNumberC) {
		this.fileNumberC = fileNumberC;
	}

	public int getFileNumberR() {
		return fileNumberR;
	}

	public void setFileNumberR(int fileNumberR) {
		this.fileNumberR = fileNumberR;
	}

	public int getFileNumberD() {
		return fileNumberD;
	}

	public void setFileNumberD(int fileNumberD) {
		this.fileNumberD = fileNumberD;
	}
}
