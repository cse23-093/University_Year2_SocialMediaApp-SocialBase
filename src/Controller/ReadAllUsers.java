package Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.Database;
import Model.User;
import View.Alert;


//retrieve a list of all users from the database, excluding the current user
public class ReadAllUsers {
	
	//list of users retrieved from the database
	private ArrayList<User> users;
	
	public ReadAllUsers(Database database, User user) {
		//retrieve the all users with SQL query
		String select = "SELECT * FROM `users`;";
		
		//initialize users to store retrieved users
		users = new ArrayList<>();
		try {
			ResultSet rs = database.getStatement().executeQuery(select);
			while (rs.next()) {
				User u = new User();
				u.setID(rs.getInt("ID"));
				u.setFirstName(rs.getString("FirstName"));
				u.setLastName(rs.getString("LastName")); 
				u.setEmail(rs.getString("Email"));
				if (u.getID()!=user.getID()) users.add(u);
			}
		} catch (SQLException e) {
			new Alert(e.getMessage(), null);
		}
	}
	
	//returns the list of users generated by the constructor
      public ArrayList<User> getList() {
    	  return users;
      }
}
