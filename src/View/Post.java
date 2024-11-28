package View;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import Controller.ReadPostComments;
import Model.Database;
import Model.User;

@SuppressWarnings("serial")
public class Post extends JPanel{
	
	
	
	public Post (User u, Model.Post post, Database database, JFrame f) {
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBackground(GUIConstants.white);
		setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 25));
		
		JPanel header = new JPanel(new BorderLayout());
		header.setBackground(null);
		
		JLabel author = new JLabel(post.getUser().getName(), 20, GUIConstants.post, 
				Font.BOLD);
		header.add(author, BorderLayout.WEST);
		
		JLabel date = new JLabel(post.getDateToString(), 15, GUIConstants.post, Font.PLAIN);
		header.add(date, BorderLayout.EAST);
		
		add(header);
		add(Box.createVerticalStrut(7));
		
		JPanel center = new JPanel(new FlowLayout(FlowLayout.LEADING));
		center.setBackground(null);
		JTextArea content = new JTextArea(post.getContent(), 18, GUIConstants.post, Font.PLAIN);
		center.add(content);
		add(center);
		add(Box.createVerticalStrut(7));
		
		JPanel bottom = new JPanel(new BorderLayout());
		bottom.setBackground(null);
		
		int commentsCount = new ReadPostComments(post, database).getCommentsCount();
		JLabel comments = new JLabel("", 15, GUIConstants.textFieldHint,
				Font.BOLD);
		comments.setCursor(new Cursor(Cursor.HAND_CURSOR));
		if (commentsCount<2) {
			comments.setText(commentsCount+" Comment");
		} else {
			comments.setText(commentsCount+" Comment");
		}
		comments.addMouseListener(new MouseListener() {
			
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
				new Comments(u, post, database); 
				f.dispose();
				}
		});
		bottom.add(comments, BorderLayout.WEST);
		add(bottom);
		
		int height = (int) (115+content.getPreferredSize().getHeight());
		
		Dimension dimension = new Dimension(500, height);
		setPreferredSize(dimension);
		setMaximumSize(dimension);
		setMinimumSize(dimension);
		
	}

}
