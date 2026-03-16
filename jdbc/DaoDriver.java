package jdbc;

import java.util.List;
import java.util.Scanner;

import com.pro1.dao.UserDAOImpl;
import com.pro1.dto.User;

//without main we cannot do anything with the other classes so we need this class(DAODriver) 
public class DaoDriver {

	public static void main(String[] args) {
		
		UserDAOImpl userDAOImpl = new UserDAOImpl();
		//call method which I want
		//only one method we have implemented
		List<User> users = userDAOImpl.getUsers();
		
		for(User u : users) 
		{
			System.out.println(u);//it string format it should be print user object so hashcode,so to String
		}
		
		//getUser()
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Enter the Employee ID to be updated:");
		int userid = scan.nextInt();
		
		User u = userDAOImpl.getUser(userid);
		System.out.println(u);
		
		System.out.println();
		
		//insertUser
		
		
		//update
		System.out.println("Enter the userId");
		int userid1 = scan.nextInt();
		System.out.println("Enter the Email");
		String Email = scan.next();
		
		User user = userDAOImpl.getUser(userid1); 
		user.setEmail(Email);
		userDAOImpl.UpdateUser(user);
	}

}
