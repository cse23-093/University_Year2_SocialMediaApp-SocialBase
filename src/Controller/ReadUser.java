package Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.Database;
import Model.User;
import View.Alert;

public class ReadUser {
	
	//indicates whether the user is successfully logged in
	private boolean loggedIn = false;
	
	//contains the authenticated user's information, their password, email and friends they follow
	private User user;

	public ReadUser(String email, String password, Database database) {
		String select = "SELECT * FROM `users` WHERE `Email` = '"+email+"' AND `Password` = '"+password+"';";
		//verify the user's credentials and populates the user object with the retrieved data if the login is successful
	try {
		ResultSet rs = database.getStatement().executeQuery(select);
		loggedIn = rs.next();
		if (loggedIn) {
			user = new User();
			user.setID(rs.getInt("ID"));
			user.setFirstName(rs.getString("FirstName"));
			user.setLastName(rs.getString("LastName"));
			user.setEmail(rs.getString("Email"));
			user.setPassword(rs.getString("Password"));
			
			String findFriends = "SELECT * FROM `friends` WHERE `User` = "
			+user.getID()+" ;";
			ResultSet rs2 = database.getStatement().executeQuery(findFriends);
			ArrayList<Integer> friendsIDs = new ArrayList<>();
			while (rs2.next()) {
				friendsIDs.add(rs2.getInt("Friend"));
			}
			 user.setFriendsIDs(friendsIDs);
			 
		}
		
		
	} catch (SQLException e) {
		new Alert(e.getMessage(), null);
	}
	
	}
	
	//success in log in
	public boolean loggedIn() {
		return loggedIn;
		
	}
	
	//method returns the authenticated user object
	public User getUser() {
		return user;	
	}
}
