package View;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import Controller.CreateUser;
import Model.Database;
import Model.User;

public class Welcome {
	
	public Welcome(Database database) {
		JFrame frame = new JFrame();
		
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBackground(null);
		panel.setBorder(BorderFactory.createEmptyBorder(53, 84, 76, 84));
		
		
		JPanel center = new JPanel (new GridLayout(6, 1, 10, 10));
		center.setBackground(null);
		center.setBorder(BorderFactory.createEmptyBorder(22, 231, 17, 231));
	    JTextField firstName = new JTextField("First Name");
	    center.add(firstName);
	    JTextField lastName = new JTextField("Last Name");
	    center.add(lastName);
	    JTextField email = new JTextField("Email");
	    center.add(email);
	    JTextField password = new JTextField("Password");
	    center.add(password);
	    JTextField confirmPassword = new JTextField("Confirm Password");
	    center.add(confirmPassword);
	    
	    
	    JButton createAcc = new JButton("Create Account", 45, 20);
	    
	    createAcc.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {}
			
			@Override
			public void mousePressed(MouseEvent e) {}
			
			@Override
			public void mouseExited(MouseEvent e) {}
			
			@Override
			public void mouseEntered(MouseEvent e) {}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if (firstName.isEmpty()) {
					new Alert("First Name is required", frame);
					return;
				}
				if (lastName.isEmpty()) {
					new Alert("Last Name is required", frame);
					return;
				}
				if (email.isEmpty()) {
					new Alert("Email Name is required", frame);
					return;
				}
				if (password.isEmpty()) {
					new Alert("Password is required", frame);
					return;
				}
				if (password.getText().length()<6) {
					new Alert("Password must contain more than 6 characters", frame);
					return;
				}
				if (confirmPassword.isEmpty()) {
					new Alert("Kindly fill in the confirm password tab", frame);
					return;
				}
				if (!password.getText().equals(confirmPassword.getText())) {
					new Alert("Password does not match", frame);
					return;
				}
				User u = new User();
				u.setFirstName(firstName.getText());
				u.setLastName(lastName.getText());
				u.setEmail(email.getText());
				u.setPassword(password.getText());
				CreateUser create = new CreateUser(u, database);
				if(!create.isEmailUsed()) {
					create.create();
					u = create.getUser();
					new Home(u, database);
				} else {
					new Alert("This email is already in use", frame);
				}
				
			}
			
		});
	    
	    center.add(createAcc);
	    
	    panel.add(center, BorderLayout.CENTER);
	    
	    JLabel login = new JLabel("Login", 20,
	    		GUIConstants.hotpink, Font.BOLD);
	    login.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {}
			
			@Override
			public void mousePressed(MouseEvent e) {}
			
			@Override
			public void mouseExited(MouseEvent e) {}
			
			@Override
			public void mouseEntered(MouseEvent e) {}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				new Login(database);
				frame.dispose();
			}
			
		});
	    login.setCursor(new Cursor(Cursor.HAND_CURSOR));
	    login.setHorizontalAlignment(JLabel.CENTER);
	    panel.add(login, BorderLayout.SOUTH);
	    
	    frame.getContentPane().add(panel);
	    
	    frame.setVisible(true);
	    frame.requestFocus();
	}

}
