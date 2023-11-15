
package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class MyFrame implements ActionListener{
    
    JButton button,button2;
    JFrame frame;
    JPanel upPanel,sidePanel,centerPanel,otherPanel,cardPanel,panel;
    CardLayout card = new CardLayout();
    JLabel label;
 
    MyFrame(){          
    
    init();
    }
    
    /**
     *
     */
    public void init(){
     frame = new JFrame("GUI");
        
        
       //  frame.setUndecorated(true);

            // Set the frame size to the screen size
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(screenSize);
       // frame.setSize(800,600);
        frame.setDefaultCloseOperation(3);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

       
       upPanel = new JPanel();
       upPanel.setBackground(Color.red);
       
       upPanel.setPreferredSize(new Dimension(100,100));
       frame.add(upPanel,BorderLayout.NORTH);
       
       cardPanel = new JPanel(card);
       
       
       centerPanel = new JPanel();
       centerPanel.setBackground(Color.blue);
       
       //centerPanel.setPreferredSize(new Dimension(100,100));
       centerPanel.setLayout(new GridLayout(3,3,50,50));
    //   centerPanel.setVisible(true);
       
       sidePanel = new JPanel();
       sidePanel.setBackground(Color.yellow);
       
       sidePanel.setPreferredSize(new Dimension(150,100));
       sidePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
       frame.add(sidePanel,BorderLayout.WEST);
       
       button = new JButton("Change Color");
       button.setBounds(0,0,sidePanel.getWidth(),50);
       button.setBackground(Color.yellow);
       button.setBorderPainted(false);
       button.setFocusable(false);
       button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                                card.show(cardPanel,"2");
			}
		});

       sidePanel.add(button, new FlowLayout(FlowLayout.CENTER));
       
       
       otherPanel = new JPanel();
       otherPanel.setBackground(Color.black);
       otherPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
       
       panel = new JPanel();
       panel.setBackground(Color.green);
     //  otherPanel.setVisible(false);
    //  frame.add(otherPanel,BorderLayout.CENTER);
       
       
        button2 = new JButton("Home");
        button2.setBounds(0,50,sidePanel.getWidth(),50);
        button2.setBackground(Color.yellow);
        button2.setFocusable(false);
        button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(cardPanel,"3");
			}
		});

        button2.setBorderPainted(false);
       sidePanel.add(button2);
       
       centerPanel.add(new JButton("1"));
       centerPanel.add(new JButton("2"));
       centerPanel.add(new JButton("3"));
       centerPanel.add(new JButton("4"));
       centerPanel.add(new JButton("5"));
       centerPanel.add(new JButton("6"));
       centerPanel.add(new JButton("7"));
       centerPanel.add(new JButton("8"));
       centerPanel.add(new JButton("9"));
       
     //  cardPanel.add(panel,"1"); 
     
       label = new JLabel("Hello Ni**a");
       label.setForeground(Color.white);
       Font newFont = new Font(label.getFont().getName(), Font.PLAIN, 50);
       label.setFont(newFont);
       otherPanel.add(label);
       
       cardPanel.add(otherPanel,"2");
       cardPanel.add(centerPanel,"3");
       
       frame.add(cardPanel,BorderLayout.CENTER);
       frame.setVisible(true); 
    
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
     
}
