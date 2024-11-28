package View;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JScrollBar;
import javax.swing.plaf.basic.BasicScrollBarUI;

@SuppressWarnings("serial")
public class JScrollPane extends javax.swing.JScrollPane {
	
	public JScrollPane(JComponent component) {
		super(component);
		setBackground(null);
		getViewport().setBackground(null);
		setBorder(null);
		
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBackground(null);
		scrollBar.setBorder(null);
		scrollBar.setUI(new BasicScrollBarUI() {
             
			// changing scroll bar color
			@Override
			protected void configureScrollBarColors() {
				this.thumbColor = GUIConstants.textAreaHint;
		    	
		    }
			
			//hiding arrows of the scroll bar
			@Override
			protected JButton createDecreaseButton(int orientation) {
				return createZeroButton();
			}
			
			protected JButton createIncreaseButton(int orientation) {
				return createZeroButton();
			}
			
			private JButton createZeroButton() {
				JButton btn = new JButton();
				btn.setPreferredSize(new Dimension(0, 0));
				btn.setMaximumSize(new Dimension(0, 0));
				btn.setMinimumSize(new Dimension(0, 0));
				return btn;
			}
			
			
		});
		
		setVerticalScrollBar(scrollBar);
		//hiding horizontal scroll bar
		setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);
		getVerticalScrollBar().setPreferredSize(new Dimension(5, 0));
		getHorizontalScrollBar().setPreferredSize(new Dimension(0, 5));
	}
	

}