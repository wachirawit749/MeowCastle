
import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.WindowConstants;


public class Howtoplay extends JFrame{

    public JFrame getF(){   
    return this;
    }
    private MyImageIcon []htp =  {  new MyImageIcon("resources/howtoplay1.png").resize(800,600),
                                    new MyImageIcon("resources/howtoplay2.png").resize(800,600),
                                    new MyImageIcon("resources/howtoplay3.png").resize(800,600)};
    private JPanel htpPane ;
    private int count=0;
    private  JLabel label = new JLabel();
    private  JLabel spaceBar = new JLabel(">> Press Spacebar to next pages <<");
    
public Howtoplay(int x,int y,int bg,int m){
        htpPane = (JPanel)getContentPane();
        setTitle("How to play");
        setBounds(x, y, 800, 600);
        
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation( WindowConstants.DO_NOTHING_ON_CLOSE );
        htpPane.setFocusable(true);
        label.setBounds(0, 0, 800, 600);
        label.setIcon(htp[0]);
        spaceBar.setBounds(215, 20, 400, 50);
        
        spaceBar.setFont(new Font("Serif", Font.BOLD, 24));
        spaceBar.setForeground(new Color(255,255,255,185));
        htpPane.addKeyListener(new KeyAdapter(){
            public void keyPressed(KeyEvent e){
                if ( e.getKeyCode() == KeyEvent.VK_SPACE )
                    {
                        count++;
                        label.setIcon(htp[count%htp.length]);
                        
                        label.repaint();
                        
                    }
            }
        });
        JButton button1  = new JButton(new MyImageIcon("resources/BACKBLUE1.png").resize(175, 175));
        
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
                button1.setIcon(new MyImageIcon("resources/BACKBLUE2.png").resize(175, 175));
                htpPane.validate();
                
               
            }
                public void mouseReleased( MouseEvent e )
            {
                button1.setIcon(new MyImageIcon("resources/BACKBLUE1.png").resize(175, 175));
                htpPane.validate();
                
                
            }
            
        });
        
        htpPane.add(button1);
        htpPane.add(label);
        label.add(spaceBar);
        
}
}
