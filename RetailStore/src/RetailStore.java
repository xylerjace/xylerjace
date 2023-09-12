import java.util.*;
                             
class User{
   int userId;
   String userName;
   String email;
   
   
   public void login(){
    Scanner scan = new Scanner(System.in);
    
    System.out.print("Enter User ID: ");
    userId = scan.nextInt();
    scan.nextLine();
    
    System.out.print("Enter userName: ");
    userName = scan.nextLine();
    
    System.out.print("Enter email: ");
    email = scan.nextLine();
    
   }
   public boolean logout(){
	   
	   return false;
   
   }
   
}

class Customer extends User{
   int customerID;
   String Address;
   String ch;
   int money, change;
   Scanner scan = new Scanner(System.in);
   public void placeOrder(Order order,List<Order>or) { 
	   for(Order orde: or) {
		   System.out.println(orde);
		   
		   System.out.println("\nYour total amount is : "+ order.calculateTotalAmount(or));
		   System.out.println("---------------------------------------------------------------------");
	   }
	   if(!or.isEmpty()) {
		   System.out.print("\nDo you want to purchase this items? (y or n) -->");
		   ch = scan.nextLine().toLowerCase();
		   
		   if(ch.equals("y")) {
			   System.out.print("\nEnter payment: ");
			   money = scan.nextInt();
			   change = money - order.calculateTotalAmount(or);
			   System.out.println("\nYour Change is "+ change);
			   System.out.println("---------------------------------------------------------------------");
			   System.out.println("Your transaction is successful!!");
			   System.out.println("You will be contacted when the delivery is on its way!!");
			   System.out.println("---------------------------------------------------------------------");
		   }else {
			   or.clear();
		   }
	   }
	   else {
		   System.out.println("---------------------------------------------------------------------");
		   System.out.println("No orders have been made");
		   System.out.println("---------------------------------------------------------------------");
	   }
   }
   public void viewPurchaseHistory(List<Order> orders,Order or,Customer customer ) {
	   System.out.println("---------------------------------------------------------------------");
       System.out.println("Purchase History:\n");
       if (orders.isEmpty()) {
           System.out.println("No purchase history available.");
           System.out.println("---------------------------------------------------------------------");
           
       } else {
           for (Order order : orders) {
        	   System.out.println("Customer Id:     " + String.format("%-30s", customer.customerID));
        	   System.out.println("Customer Address:" + String.format("%-30s", customer.Address));
        	   System.out.println("Order ID:        " + String.format("%-30s", order.orderID));
        	   System.out.println("Order Date:      " + String.format("%-30s", order.orderdate));
        	   System.out.println("Product Details:" + String.format("%-30s", order.product));
        	   System.out.println("Total Amount:    " + String.format("%-30s", order.calculateTotalAmount(orders)));
        	   System.out.println("------------------------------");

           }
       }
   }
   public void getCustomerInfo() {
	   Scanner cus = new Scanner(System.in);
	   System.out.print("Customer ID: ");
	   customerID = cus.nextInt();
	   cus.nextLine();
	   System.out.print("Address: ");
	   Address = cus.nextLine();
   }
   	
   
}

class Admin extends User{
      
      int adminID;
      String department;
      Scanner cust = new Scanner(System.in);
      
      public void addProduct(List<Product> prod){
    	  
    	  Product product = new Product();
    	  System.out.print("\nProduct id: ");
    	  product.productID = cust.nextInt();
    	  cust.nextLine();
    	  System.out.print("Product name: ");
    	  product.name = cust.nextLine();
    	  System.out.print("Price: ");
    	  int price = cust.nextInt();
    	  product.updatePrice(price);
    	  System.out.print("Stock Quantity: ");
    	  int stock = cust.nextInt();
    	  product.updateStock(stock);
    	  prod.add(product);
    	  
      }
      
      public void removeProduct(List<Product> product){
    	  
    	  System.out.print("What product do you want to remove?(use product Id) -->");
    	  System.out.println("\n---------------------------------------------------------------------");
    	  int removeprod = cust.nextInt();
    	  int i = 0;
    	  for(;i<product.size();i++) {
    		  if(removeprod == product.get(i).productID) {
    			  product.remove(i);
    		  }
    		  
    	  }
    	  
      }
      
      public void manageInventory(List<Product> product){
    	  
    	  for (Product prod : product) {
              System.out.println(prod);
          }
      }
      public void getAdminInfo() {
   	   System.out.print("Admin ID: ");
   	   adminID = cust.nextInt();
   	   cust.nextLine();
   	   System.out.print("Department: ");
   	   department = cust.nextLine();
      }
      
}

class Product{
      
      int productID;
      String name;
      int price, stockQuantity;
      
      public void updatePrice(int price){
    	  this.price = price;
    	  
      }
      
      public void updateStock(int stockQuantity){
    	  this.stockQuantity = stockQuantity;
      }
      @Override
	      public String toString() {
    	  return String.format("%-30s: %s\n%-30s: %s\n%-30s: %d", // Use %d for integers
    			    "Product Id", productID,
    		        " Product name", name,
    		        " Price", price,
    		        " Stock Quantity", stockQuantity) +
    		"\n---------------------------------------------------------------------"; 
      }


}

class Order{
	
	   Random random = new Random();
	   Date orderdate = new Date();
	   int orderID = random.nextInt(100000)+1000;
	   
	   Scanner scan = new Scanner(System.in);
	   Product product;
	   String ch;
	   int money,change;
	   public void addProductToOrder(List<Order>orders, List<Product>products){
		   
		   int choice;	
		   int exit;
		   boolean ch = true;
		   
		   
		   while(ch) {
			   for (Product prod : products) {
				   System.out.println("---------------------------------------------------------------------");
				   System.out.println(prod);
			   }
			   if(products.isEmpty()) {
				   System.out.println("\nNo product is added");
				   System.out.println("---------------------------------------------------------------------");
				   
				   break;
			   }
		   System.out.print("\nWhat product do you want to order(USE PRODUCT ID ONLY!!!) -->");
		   choice = scan.nextInt();
		   
		   Product selectedProduct = null;
		    for (Product product : products) {
		        if (choice == product.productID) {
		            selectedProduct = product;
		            break;
		        }
	    }

	    if (selectedProduct != null) {
	        // Create an order and associate it with the selected product
	    	Order order = new Order();
	        order.product = selectedProduct;

	        orders.add(order);
	        order.product.stockQuantity--;
	        System.out.println("---------------------------------------------------------------------");
	        System.out.println("\nProduct added to the order successfully!");
	        System.out.println("---------------------------------------------------------------------");
	        System.out.println("\nDo you want to add another order?(1 = Yes, 0=No)");
	        exit = scan.nextInt();
		    scan.nextLine();
		    if(order.product.stockQuantity == 0) {
		    	products.remove(selectedProduct);
		    }
		    if(exit == 0) {
		    	ch = false;
		    }
	    } else {
	    	System.out.println("---------------------------------------------------------------------");
	        System.out.println("\t\tProduct not found with the specified ID.");
	        System.out.println("---------------------------------------------------------------------");
	    }
	   }
	 confirmOrder(orders);
		   
	}
		   
	   public void confirmOrder(List<Order>orders){
		   
		   for(Order order: orders) {
			   System.out.println(order);
			   System.out.println("Your total amount is : "+ calculateTotalAmount(orders));
		   }
		   
		   
		   
	   }
	   public int calculateTotalAmount(List<Order>orders){
	       int total = 0;
		   for(Order order: orders) {
	    	   total += order.product.price;
	       }
	       return total;
	        
	  }

	    @Override
	    public String toString() {
	        return "Order Date:\t"+ orderdate +
	               "\n\nProduct Details:\n " + product;
	        	   
	    }
}


public class RetailStore{
public static void main(String[] args){
      Scanner sc = new Scanner(System.in);
      Customer customer = new Customer();
      Admin admin = new Admin();
      Order order = new Order();
      User user = new User();
  	List <Product> prod = new ArrayList<>();
  	List<Order> or = new ArrayList<>();
  	boolean loggedIn = true;
  	
      user.login();
      
      do {
    	  int choice;
    	  System.out.println("\nAre u a customer or admin: ");
          System.out.println("[1]Customer\n[2]Admin\n[3]Logout");
          choice = sc.nextInt();
      switch(choice) {
      	
      case 1:
    	  customer.getCustomerInfo();
    	  int custChoice;
    	  while(loggedIn) {
    		  System.out.println("\nCustomer options:\n[1]Add order\n[2]Purchase\n[3]View purchase history\n[4]Logout");
    		  custChoice = sc.nextInt();
    		  switch(custChoice) {
    		  case 1:
        			  order.addProductToOrder(or,prod);break;
        			  
    		  case 2:
    			  customer.placeOrder(order,or);
        		  break;
    		  case 3:customer.viewPurchaseHistory(or,order,customer);break;
    			  
    			  
    		  case 4:loggedIn = customer.logout();
    		  }
    	  }
      	
      	break;
      	
      case 2:
    	  admin.getAdminInfo();
    	  
    	 while(loggedIn) {
    		  int adminChoice;
	          admin.manageInventory(prod);
	    	  System.out.println("\n[1]Add Product\n[2]Remove product\n[3]Logout");
	    	  System.out.print("What do you want to do? --> ");
	    	  adminChoice = sc.nextInt();
	    	  
	    	  switch(adminChoice) {
	    	  
	    	    case 1:
	    	        admin.addProduct(prod);
	    	        System.out.println("Product added successfully!");
	    	        System.out.println("---------------------------------------------------------------------");         
	    	        break;
	    	    case 2:
	    	        admin.manageInventory(prod);
	    	        admin.removeProduct(prod);
	    	        break;
	    	    case 3: loggedIn = customer.logout();
	    	  }
    	 	}
    	 break;
      default:
    	  System.out.println("---------------------------------------------------------------------");
    	  System.out.println("THANK YOU FOR USING OUR SYSTEM!!!\nTAKE CARE!!");
    	  System.exit(0);break;
      	}
      
      loggedIn = true;
      }while(loggedIn);

     
     }
   
}
