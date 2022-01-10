
import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;

    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author JJ
 */
public class Credit extends JFrame{

    public JFrame getF(){   
        return this;
    }
   private MyImageIcon credit =  new MyImageIcon("resources/CREDIT.png").resize(800,600);
   private JPanel creditpane ;
    public Credit(int x ,int y,int bg,int m){
         setTitle("Credit");
        setBounds(x, y, 800, 600);
        setResizable(false);
        setVisible(true);
        creditpane = (JPanel) getContentPane();
        setDefaultCloseOperation( WindowConstants.DO_NOTHING_ON_CLOSE);
        JButton button1  = new JButton(new MyImageIcon("resources/BACKYELLOW1.png").resize(175, 175));
        
        button1.setBounds(-20,-50, 250, 200);
        button1.setBackground(new Color(0,0,0,0));
        button1.setOpaque(false);
        button1.setBorder(BorderFactory.createLineBorder(new Color(0,0,0,0), 1));
        button1.setFocusable(false);
        button1.setContentAreaFilled(false);
        button1.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e )
            {
          
              new MainApplication(getF().getX(),getF().getY(),bg,m);
              
              dispose();  
            }
        });
        button1.addMouseListener( new MouseAdapter() {
                public void mousePressed( MouseEvent e )
            {
                button1.setIcon(new MyImageIcon("resources/BACKYELLOW2.png").resize(175, 175));
                button1.validate();
                
               
            }
                public void mouseReleased( MouseEvent e )
            {
                button1.setIcon(new MyImageIcon("resources/BACKYELLOW1.png").resize(175, 175));
                button1.validate();
                
                
            }
            
        });
        JLabel label = new JLabel(credit);
        creditpane.add(button1);
        
        creditpane.add(label);
        validate();
    }
   /* public static void main(String args[]) {
       // new Credit();
    }*/
}
