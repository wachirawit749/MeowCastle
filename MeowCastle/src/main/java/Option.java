
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class Option extends JFrame 
{
    public JFrame getF() 
    {
        return this;
    }
    
    JComboBox combo;
    JPanel contentpane = (JPanel) getContentPane();
    JLabel screen = new JLabel();
    JToggleButton[] field;
    private static int selectMode;
    private static int selectBackground; 
   
    public void setBackgrounds(int x) 
    {
        selectBackground = x;
    }
    
    private MyImageIcon background[] = new MyImageIcon[]
    {
        new MyImageIcon("resources/background.jpg").resize(600, 450),
        new MyImageIcon("resources/background1.jpg").resize(600, 450),
        new MyImageIcon("resources/background2.jpg").resize(600, 450),
        new MyImageIcon("resources/background3.jpg").resize(600, 450),
        new MyImageIcon("resources/background4.jpg").resize(600, 450),
        new MyImageIcon("resources/background5.jpg").resize(600, 450)
    };

    public Option(int x, int y) 
    {
        selectMode = 1;
        selectBackground = 1;
        setTitle("Option");
        setBounds(x, y, 800, 600);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        JPanel control = new JPanel();
        JPanel pane = new JPanel();
        JPanel pane1 = new JPanel();
        JLabel text = new JLabel("Select Mode:");
        text.setBounds(570, 35, 100, 15);
        control.add(text);
        control.setLayout(null);
        control.setOpaque(false);
        MyImageIcon backgroundImg = new MyImageIcon("resources/bgoption.jpg").resize(800,600);
        JLabel label = new JLabel(backgroundImg);
        label.setLayout(new BorderLayout());
        label.setBounds(0, 0, 800, 600);
        JButton button1  = new JButton(new MyImageIcon("resources/BACKYELLOW1.png").resize(175, 175));
        
        button1.setBounds(-20,-50, 250, 180);
        button1.setBackground(new Color(0,0,0,0));
        button1.setOpaque(false);
        button1.setBorder(BorderFactory.createLineBorder(new Color(0,0,0,0), 1));
        button1.setFocusable(false);
        button1.setContentAreaFilled(false);
        button1.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e )
            {
                
              new MainApplication(getF().getX(),getF().getY(),selectBackground,selectMode);
              
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

        String[] Mode = {"Beginner", "Amatuet", "Semipro", "Pro", "Legendary"};
        combo = new JComboBox(Mode);
        combo.requestFocusInWindow();
        combo.setSelectedIndex(0);
        
        combo.addItemListener(new ItemListener() 
        {
            public void itemStateChanged(ItemEvent e) 
            {
                if (combo.getSelectedIndex() == 0) {
                    selectMode = 1;
                } else if (combo.getSelectedIndex() == 1) {
                    selectMode = 2;
                } else if (combo.getSelectedIndex() == 2) {
                    selectMode = 3;
                } else if (combo.getSelectedIndex() == 3) {
                    selectMode = 4;
                } else {
                    selectMode = 5;
                }                
            }
        });

        field = new JToggleButton[5];
        field[0] = new JRadioButton("Summer");
        field[0].setName("background1");
        field[0].setOpaque(false);
        field[1] = new JRadioButton("Winter");
        field[1].setName("background2");
        field[1].setOpaque(false);
        field[2] = new JRadioButton("Spring");
        field[2].setName("background3");
        field[2].setOpaque(false);
        field[3] = new JRadioButton("Autumn");
        field[3].setName("background4");
        field[3].setOpaque(false);
        field[4] = new JRadioButton("Rain");
        field[4].setName("background5");
        field[4].setOpaque(false);
        
        field[0].setSelected(true);
        
        JLabel bg = new JLabel(background[0]);
        bg.setBounds(160, 90, 600, 450);
        control.add(bg);
        control.validate();

        field[0].addItemListener(new ItemListener() 
        {
            public void itemStateChanged(ItemEvent e) 
            {
                if (e.getStateChange() == ItemEvent.SELECTED) 
                {
                    field[1].setSelected(false);
                    field[2].setSelected(false);
                    field[3].setSelected(false);
                    field[4].setSelected(false);
                    bg.setIcon(background[0]);
                    selectBackground = 1;
                    control.validate();
                }
            }
        });
        
        field[1].addItemListener(new ItemListener() 
        {
            public void itemStateChanged(ItemEvent e) 
            {
                if (e.getStateChange() == ItemEvent.SELECTED) 
                {                    
                    field[0].setSelected(false);
                    field[2].setSelected(false);
                    field[3].setSelected(false);
                    field[4].setSelected(false);
                    bg.setIcon(background[1]);
                    selectBackground = 2;
                    control.validate();
                }
            }
        });
        
        field[2].addItemListener(new ItemListener() 
        {
            public void itemStateChanged(ItemEvent e) 
            {
                if (e.getStateChange() == ItemEvent.SELECTED) 
                {
                    //olafLeft = true;
                    field[0].setSelected(false);
                    field[1].setSelected(false);
                    field[3].setSelected(false);
                    field[4].setSelected(false);
                    bg.setIcon(background[2]);
                    selectBackground = 3;
                    control.validate();
                }
            }
        });
        
        field[3].addItemListener(new ItemListener() 
        {
            public void itemStateChanged(ItemEvent e) 
            {
                if (e.getStateChange() == ItemEvent.SELECTED) 
                {                    
                    field[0].setSelected(false);
                    field[1].setSelected(false);
                    field[2].setSelected(false);
                    field[4].setSelected(false);
                    bg.setIcon(background[3]);
                    selectBackground = 4;
                    control.validate();
                }
            }
        });
        
        field[4].addItemListener(new ItemListener() 
        {
            public void itemStateChanged(ItemEvent e) 
            {
                if (e.getStateChange() == ItemEvent.SELECTED)
                {                    
                    field[0].setSelected(false);
                    field[1].setSelected(false);
                    field[2].setSelected(false);
                    field[3].setSelected(false);
                    bg.setIcon(background[4]);
                    selectBackground = 5;
                    control.validate();

                }
            }
        });

        pane.setBounds(0, 0, 100, 400);
        pane1.setBounds(100, 100, 1000, 100);
        pane.setLayout(new GridLayout(5, 1, 10, 10));
        control.add(button1, BorderLayout.NORTH);

        pane1.add(combo);
        pane1.setLocation(200, 25);

        pane.add(field[0]);
        pane.add(field[1]);
        pane.add(field[2]);
        pane.add(field[3]);
        pane.add(field[4]);
        pane.setLocation(20, 110);
        pane.setOpaque(false);
        pane1.setOpaque(false);
        control.add(pane);
        control.add(pane1);        
        
        label.add(control);
        add(label);
        
        validate();
    }
}
