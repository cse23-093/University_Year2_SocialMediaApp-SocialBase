package Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.Database;
import Model.User;
import View.Alert;
//create user, check if email used and retrieve user information
public class CreateUser {

	 private User u;
	 private Database database;
	 
	public CreateUser (User u, Database database) {
		this.u = u;
		this.database = database;
	}
	
	public void create() {
		String insert = "INSERT INTO `users`(`FirstName`, `LastName`,"
				+ " `Password`,`Email`) VALUES ('"+u.getFirstName()+"','"+u.getLastName()
				+"','"+u.getPassword()+"','"+u.getEmail()+"');";
		try {
			database.getStatement().execute(insert);
		} catch (SQLException e) {
			new Alert(e.getMessage(), null);
		}
	 } 
	
	//checks if the email is already used by another user
	public boolean isEmailUsed() {
		String select = "SELECT * FROM `users` WHERE `Email` = '"+u.getEmail()+"';";
        boolean used = false;
        try {
        	ResultSet rs = database.getStatement().executeQuery(select);
        	//It checks if any record email in the result set is already used
        	used = rs.next();
        }
        catch (SQLException e) {
        	new Alert(e.getMessage(), null);
        }
	    return used;
	
	}
	
	//retrieves user information from the database
	public User getUser() {
		u.setComments(new ArrayList<>());
		u.setFriends(new ArrayList<>());
		u.setPosts(new ArrayList<>());
		String select = "SELECT `ID` FROM `users` WHERE `Email` = '"+u.getEmail()+"' AND `Password` = '"+u.getPassword()+"';";
        try {
        	ResultSet rs = database.getStatement().executeQuery(select);
        	rs.next();
        	//It sets the user's ID based on the result set
        	u.setID(rs.getInt("ID"));
        }
        catch (SQLException e) {
        	new Alert(e.getMessage(), null);
        }
		
		return u;
	}
}

