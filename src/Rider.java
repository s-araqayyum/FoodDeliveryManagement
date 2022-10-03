import java.util.Scanner;
public class Rider extends Account{

	
	private String riderName;
	private int riderLogin;
    private String riderPassword;
    private String riderPhoneNumber;
    private boolean approval;
    
    
    public Rider(String riderName, String riderPhoneNumber, int riderLogin, String riderPassword, boolean approval) {
		
		this.riderName = riderName;
		this.riderLogin = riderLogin;
		this.riderPassword = riderPassword;
		this.riderPhoneNumber = riderPhoneNumber;
		this.approval = approval;
	}

	public Rider() {
	}

	public void createAccount() {
		
		Scanner riderDetails = new Scanner(System.in);
		System.out.print("Enter your name ");
        String riderName = riderDetails.nextLine();
        
        System.out.print("Enter your contact details [Phone Number/Landline] ");
        String phoneNumber = riderDetails.nextLine();
        
        System.out.print("Registration almost complete! Enter a password by which you can later access this account ");
        String password = riderDetails.nextLine();
        
        System.out.println("You will be notified if the admin accepts your registration. We will get back to your shortly.");
        
        FoodOnWheels fow = new FoodOnWheels();
        
        DataConversion dC = new DataConversion();
        
        DataConversion.rider.add(dC.fileNumberD, new Rider(riderName, phoneNumber, fow.login, password, false));
        
        dC.fileNumberD++;
        fow.login++;
	}
	
	public void ViewOrder() {
		
		   boolean flag = false;
     	for (int i = 0; i<Customer.order.size(); i++) {
     		if(Customer.order.get(i).isDelivered() == false) {
     			flag = true;
     			System.out.println("Customer Login:: " + Customer.order.get(i).getLogin());
				    System.out.println("The item ordered:: " + Customer.order.get(i).getItemName());
					System.out.println("The price of item:: " + Customer.order.get(i).getItemPrice());
					System.out.println("The quantity of the item ordered:: " + Customer.order.get(i).getQuantity());
					
					System.out.println(" ");
     		}
     	}
     	
     	if(flag == false) {
     		System.out.println("No more orders for today");
     		return;
     	}
     	
	}

	public void Accept_Or_RejectOrders() throws InterruptedException {
		
		Scanner scan = new Scanner(System.in);
		Scanner scan2 = new Scanner(System.in);
		
     boolean flagCheck = false;
     
     	ViewOrder();
     	System.out.println("Enter login id of order you would like to deliver");
     	
     	int IdChoice = scan.nextInt();
     	
     	for (int i = 0 ; i < DataConversion.customer.size() ; i++) {
     		
     		if(IdChoice == DataConversion.customer.get(i).getCustomerLogin()) {
     			
     			flagCheck = true;
     			
     			if(Customer.order.get(i).isDelivered() == false) {
     				System.out.println("Customer Login:: " + DataConversion.customer.get(i).getCustomerLogin());
     				System.out.println("Customer address:: " + DataConversion.customer.get(i).getCustomerAddress());
     				
     				for(int k = 0 ; k <Customer.order.size(); k ++) {
     					if(Customer.order.get(k).isDelivered() == false && Customer.order.get(k).getLogin() == IdChoice) {
     						System.out.println("Item Ordered:: "+Customer.order.get(k).getItemName());
     						System.out.println("Item Quantity:: "+Customer.order.get(k).getItemPrice());
     						System.out.println("Item Price Required:: "+Customer.order.get(k).getItemPrice());
     					}
     				}
     				
					
					System.out.println(" ");
     			}
     			
     			
     			System.out.println("Press 1 to accept to deliver this order or 2 to reject this order");
     			
     			int choice = scan2.nextInt();
             	
         		switch (choice) {
         		
         		case 1:
         			System.out.println("You have chosen to deliver this order");
         			System.out.println("Delivering...");
         			
         			System.out.println("Order has been delivered.");
         			FoodDeliveryAndCashCollectionUpdate(i);
         			
         			break;
         		case 2:
         			System.out.println("This order will be left for another rider to accept");
         			break;
         		default: System.out.println("You did not choose a valid option");
         		break;
         			
         		
         		}
     	}
     	
     	if(flagCheck == false) {
     		System.out.println("This order does not exist.");
     	}
     	}

	}
	public void FoodDeliveryAndCashCollectionUpdate(int i) {
		
		System.out.println("Please input 1 if payment has been collected and 2 if payment has not been collected");
		Scanner scan3 = new Scanner(System.in);
		int choice2 = scan3.nextInt();
 	
 	switch(choice2) {
 	
 	case 1:
 		System.out.println("Payment has been collected... Thank you!");
 		break;
 		
 	case 2:
 		System.out.println("Customer did not pay, account will be processed for blocking");
 		DataConversion.customer.get(i).setBlocked(true);
 		break;
 		
     default: System.out.println("You did not choose a valid option"); 
     break;
     
 	}
	}
   
	public String getRiderPhoneNumber() {
		return riderPhoneNumber;
	}
	public void setRiderPhoneNumber(String riderPhoneNumber) {
		this.riderPhoneNumber = riderPhoneNumber;
	}
	public String getRiderName() {
		return riderName;
	}
	public void setRiderName(String riderName) {
		this.riderName = riderName;
	}
	public int getRiderLogin() {
		return riderLogin;
	}
	public void setRiderLogin(int riderLogin) {
		this.riderLogin = riderLogin;
	}
	public String getRiderPassword() {
		return riderPassword;
	}
	public void setRiderPassword(String riderPassword) {
		this.riderPassword = riderPassword;
	}

	public boolean isApproval() {
		return approval;
	}

	public void setApproval(boolean approval) {
		this.approval = approval;
	}


	
}