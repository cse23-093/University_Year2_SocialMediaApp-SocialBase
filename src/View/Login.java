package View;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import Controller.ReadUser;
import Model.Database;
import Model.User;

public class Login {
	public Login(Database database) {
		JFrame frame = new JFrame();
		
		JPanel panel = new JPanel (new BorderLayout());
		panel.setBackground(null);
		panel.setBorder(BorderFactory.createEmptyBorder(115, 0, 182, 0));
		
		JLabel title = new JLabel("Login", 40, GUIConstants.hotpink,Font.BOLD);
		title.setHorizontalAlignment(JLabel.CENTER);
		panel.add(title, BorderLayout.NORTH);
		
		JPanel center = new JPanel(new GridLayout(3, 1, 10, 10));
		center.setBackground(null);
		center.setBorder(BorderFactory.createEmptyBorder(34, 315, 17, 315));
		JTextField email = new JTextField("Email");
		center.add(email);
		JTextField password = new JTextField("Password");
		center.add(password);
		JButton login =  new JButton("Login", 45, 20);
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
				if (email.isEmpty()) {
					new Alert("Email is required", frame);
					return;
				}
				if (password.isEmpty()) {
					new Alert("Password is required", frame);
					return;
				}
			ReadUser read = new ReadUser(email.getText(), password.getText(), database); 
			if (read.loggedIn()) {
				User user = read.getUser();
				new Home(user, database);
			} else {
				new Alert("Incorrect Login credentials", frame);
			}
			}
		});
		center.add(login);
		
		panel.add(center, BorderLayout.CENTER);
		
		JLabel createAcc = new JLabel("Back to Home", 20, 
				GUIConstants.black, Font.BOLD);
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
				new Welcome(database);
				frame.dispose();
			}
		});
		createAcc.setCursor(new Cursor(Cursor.HAND_CURSOR));	
		createAcc.setHorizontalAlignment(JLabel.CENTER);
		panel.add(createAcc, BorderLayout.SOUTH);
		
		frame.getContentPane().add(panel);
		frame.setVisible(true);
		frame.requestFocus();
		
		
		
		
	}

}
