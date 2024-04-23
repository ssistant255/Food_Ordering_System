package miniproject;
import java.util.Scanner;
import java.sql.*;
class Customer1
{
	int customer_id;
	String customer_Name,customer_Email,customers_Address,customers_password,cpassword ,query;
	long customer_phonenumber;
	void Registration() throws ClassNotFoundException, SQLException
	{
		Scanner sc=new Scanner(System.in);
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/foodorders_darshan", "root", "darshan3009");
		query = "insert into customers(customer_Name, customer_Email,customer_phonenumber,customers_Address, customers_password)values (?,?,?,?,?)";
		System.out.println("Customers Registration:");
        System.out.print("Enter your customers_Name: ");
        customer_Name = sc.next();
        System.out.println("Enter your customer_Email: ");
        customer_Email= sc.next();
        System.out.println("Enter your customer_phonenumber: ");
        customer_phonenumber = sc.nextLong();
        System.out.println("Enter your customers_Address: ");
        customers_Address= sc.next();
        System.out.print("Enter customers_password: ");
        customers_password = sc.next();
        System.out.print("Confirm password: ");
        cpassword = sc.next();
        if(customers_password.equals(cpassword))
        {
        	PreparedStatement p = conn.prepareStatement(query);
			p.setString(1 ,customer_Name);
        	p.setString(2, customer_Email);
        	p.setLong(3, customer_phonenumber);
        	p.setString(4,customers_Address );
        	p.setString(5, customers_password);       	
        	int i = p.executeUpdate();
        	if(i>0)
        	{
        		System.out.println("Registration Customers Successful..");
        	}
        	else
        	{
        		System.out.println("Registration Customers Failed..");
        	}
      
        }
        else
        {
       	 System.out.println("Customers Password Does'nt Match");
        }
	}
	void login()throws ClassNotFoundException, SQLException
	{
		Scanner sc=new Scanner(System.in);
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/foodorders_darshan", "root", "darshan3009");
		System.out.println("Customers Login:");
  		System.out.print("Enter your customer_Email: ");
  		customer_Email = sc.next();
  		System.out.print("Enter your Password: ");
  		customers_password = sc.next();
  		query = "select customers_password from customers where customer_Email=?";
  		PreparedStatement loginStmt = conn.prepareStatement(query);
  		loginStmt.setString(1, customer_Email);
  		ResultSet rs = loginStmt.executeQuery();

  		if (rs.next())
  		{
  			String cpassword = rs.getString("customers_password");
  			if (customers_password.equals(cpassword)) 
  			{
  				System.out.println("Customers Login Successful..");
  			} 
  			else 
  			{
  				System.out.println("customers Incorrect Password..");
  			}
  			} 
  			else
	  		{
	  			System.out.println("Customers User not found..");
	  		}
	}
	void UpdateCustomers() throws ClassNotFoundException, SQLException
	{
		Scanner sc=new Scanner(System.in);
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/foodorders_darshan", "root", "darshan3009");
		Statement st = conn.createStatement();
		System.out.println("Enter the customer_id");
		customer_id=sc.nextInt();
		System.out.print("Enter your customer_Name: ");
        customer_Name = sc.next();
        System.out.println("Enter your customer_Email: ");
        customer_Email= sc.next();
        System.out.println("Enter your customer_phonenumber: ");
        customer_phonenumber = sc.nextLong();
        System.out.println("Enter your customers_Address: ");
        customers_Address= sc.next();
        System.out.print("Enter customers_password: ");
        customers_password = sc.next();
        query="update customers set customer_Name=?,customer_Email=?,customer_phonenumber=?,customers_Address=?,customers_password =? where customer_id=?"; 
        PreparedStatement p = conn.prepareStatement(query);
		p.setString(1,  customer_Name);
		p.setString(2, customer_Email);
		p.setLong(3,  customer_phonenumber);
		p.setString(4, customers_Address);
		p.setString(5,customers_password);
		p.setInt(6,customer_id);
		int i1 = p.executeUpdate();
		if(i1>0)
		{
			System.out.println("Customers Update Successfully");
		}
		else
		{
			System.out.println("Customers is Not Update Change");
		}
	}
	void DeleteCustomers() throws ClassNotFoundException, SQLException 
	{
		Scanner sc=new Scanner(System.in);
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/foodorders_darshan", "root", "darshan3009");
		Statement st = conn.createStatement();
		System.out.println("Enter the customer_id");
		int customer_id=sc.nextInt();
		query="delete from customers where customer_id=?";
		PreparedStatement pr1=conn.prepareStatement(query);
		pr1.setInt(1, customer_id);
		int i=pr1.executeUpdate();
		System.out.println(i+"rows is delete");
	}
	
	void DisplayCustomersById() throws ClassNotFoundException, SQLException
	{

		Scanner sc=new Scanner(System.in);
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/foodorders_darshan", "root", "darshan3009");
		Statement st = conn.createStatement();
		query = "select * from customers where customer_id= ?";
		System.out.println("Enter customer_id :");
		int customer_id= sc.nextInt();
		PreparedStatement ps =  conn.prepareStatement(query);
		ps.setInt(1, customer_id);
		ResultSet  rs = ps.executeQuery();
		while(rs.next())
		{
			
			System.out.println(rs.getString(1) + " " +rs.getString(2)+" ");
		}
	}
	
	void DisplayCustomersAll() throws ClassNotFoundException, SQLException
	{
		Scanner sc=new Scanner(System.in);
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/foodorders_darshan", "root", "darshan3009");
		Statement st = conn.createStatement();
		query="select *from customers";
		ResultSet rs = st.executeQuery(query);
		while(rs.next()) 
		{
			System.out.println(rs.getInt(1) + " " +rs.getString(2)+" "+rs.getString(3)+" "+rs.getLong(4)+" "+rs.getString(5)+" "+rs.getString(6));
	    }
	}
}
class Food
{
	int i;
	String query;
	
	void AddFood() throws ClassNotFoundException, SQLException 
	{
		Scanner sc=new Scanner(System.in);
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/foodorders_darshan", "root", "darshan3009");
		Statement st = conn.createStatement();
		query = "insert into food1(food_Name,food_Type,food_Category,food_Price)values(?,?,?,?)";
		System.out.println("Welcome Food Orders");
		System.out.println("Enter the food_Name");
		String food_Name=sc.next();
		System.out.println("Enter the food_Type");
		String food_Type=sc.next();
		System.out.println("Enter the food_Category");
		String food_Category=sc.next();
		System.out.println("Enter the food_Price");
		Double food_Price=sc.nextDouble();
		PreparedStatement p = conn.prepareStatement(query);
		p.setString(1, food_Name);
		p.setString(2, food_Type);
		p.setString(3, food_Category);
		p.setDouble(4, food_Price);
		int i = p.executeUpdate();
    	if(i>0)
    	{
    		System.out.println("Food added Successfully");
    	}
    	else
    	{
    		System.out.println("Food added is Failed..");
    	}
		
	}	
	
	void UpdateFood() throws ClassNotFoundException, SQLException
	{
		Scanner sc=new Scanner(System.in);
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/foodorders_darshan", "root", "darshan3009");
		Statement st = conn.createStatement();
		System.out.println("Enter the food_id");
		int food_id=sc.nextInt();
		System.out.println("Enter the food_Name");
		String food_Name=sc.next();
		System.out.println("Enter the food_Type");
		String food_Type=sc.next();
		System.out.println("Enter the food_Category");
		String food_Category=sc.next();
		System.out.println("Enter the food_Price");
		Double food_Price=sc.nextDouble();
		query="update food1 set food_Name=?,food_Type=?,food_category=?,food_price=? where food_id=?"; 
		PreparedStatement pr1=conn.prepareStatement(query);
		pr1.setString(1, food_Name);
		pr1.setString(2, food_Type);
		pr1.setString(3, food_Category);
		pr1.setDouble(4, food_Price);
		pr1.setInt(5, food_id);
		int i1 = pr1.executeUpdate();
			if(i1>0)
			{
				System.out.println("Food Update Successfully");
			}
			else
			{
				System.out.println("Food  is Not Update Change");
			}
	}
	void DeleteFood() throws ClassNotFoundException, SQLException 
	{
		Scanner sc=new Scanner(System.in);
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/foodorders_darshan", "root", "darshan3009");
		Statement st = conn.createStatement();
		System.out.println("Enter the food_id");
		int food_id=sc.nextInt();
		query="delete from food1 where food_id=?";
		PreparedStatement pr1=conn.prepareStatement(query);
		pr1.setInt(1, food_id);
		int i=pr1.executeUpdate();
		System.out.println(i+"rows is delete");
	}
	void DisplayFoodById() throws ClassNotFoundException, SQLException
	{
		Scanner sc=new Scanner(System.in);
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/foodorders_darshan", "root", "darshan3009");
		Statement st = conn.createStatement();
		query = "select * from food1 where food_id = ?";
		System.out.println("Enter food_id :");
		int food_id= sc.nextInt();
		PreparedStatement ps =  conn.prepareStatement(query);
		ps.setInt(1, food_id);
		ResultSet  rs = ps.executeQuery();
		while(rs.next())
		{
			
			System.out.println(rs.getString(1) + " " +rs.getString(2)+" ");
		}
	}
	
	void DisplayAllFood() throws ClassNotFoundException, SQLException
	{
		Scanner sc=new Scanner(System.in);
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/foodorders_darshan", "root", "darshan3009");
		Statement st = conn.createStatement();
		query="select *from food1";
		ResultSet rs = st.executeQuery(query);
		while(rs.next()) 
		{
			System.out.println(rs.getInt(1) + " " +rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4)+" "+rs.getDouble(5));
	    }
	}
}
public class Food_Ordering_System 
{
	public static void main(String args[])throws ClassNotFoundException, SQLException
	{
		int choice;
		Food f=new Food();
		do
		{
			Scanner sc=new Scanner(System.in);
			System.out.println("WELCOME_FOOD_ORDERING_SYTEM");
			System.out.println("1.ADD FOOD");
			System.out.println("2.UPDATE FOOD");
			System.out.println("3.DELETE FOOD");
			System.out.println("4.DISPLAY FOOD BY ID");
			System.out.println("5.DISPLAY ALL FOOD");
			System.out.println("6.EXIT");
			System.out.println("Enter the Choice[1-6]");
			choice=sc.nextInt();
		    switch(choice)
			{
		    case 1:
		    	f.AddFood();
		    	break;
		    case 2:
		    	f.UpdateFood();
		    	break;
		    case 3:
		    	f.DeleteFood();
		    	break;
		    case 4:
		    	f.DisplayFoodById();
		    	break;
		    case 5:
		    	f.DisplayAllFood();
		    	break;
		    case 6:
		    	System.out.println("Exit");
		    	break;
		    	default:
		        System.out.println("Invalid choice. Please enter a valid option (1-6).");   
			}
		}while(choice<6);
		int choice1 = 0;
		Customer1 c=new Customer1();
		do
		{
			Scanner sc=new Scanner(System.in);
			System.out.println("WELCOME_FOOD_ORDERING_SYTEM");
			System.out.println("1.RESIGRATION CUSTOMERS");
			System.out.println("2.LOGIN");
			System.out.println("3.UPDATE CUSTOMERS");
		    System.out.println("4.DELETE CUSTOMERS");
		    System.out.println("5.DISPLAY CUSTOMER BY ID");
		    System.out.println("6.DISPLAY ALL  CUSTOMERS");
		    System.out.println("7.EXIT");
			System.out.println("Enter the Choice[1-7]");
			choice=sc.nextInt();
		    switch(choice)
			{
			 case 1:
			    	c.Registration();
			    	break;
			 case 2:
				 c.login();
				 break;
			 case 3:
				 c.UpdateCustomers();
				 break;
			 case 4:
				 c.DeleteCustomers();
				 break;
			 case 5:
				 c.DisplayCustomersById();
				 break;
			 case 6:
				 c.DisplayCustomersAll();
				 break;
			 case 7:
				 System.out.println("Exit");
			    	break;
			    	default:
			        System.out.println("Invalid choice. Please enter a valid option[1-7]");
		    
	    }
		}while(choice1<7);
	}
}

