import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import javax.swing.*;
import javax.swing.border.Border;

public class MainApplication extends JFrame {

    private JPanel c;
    
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private MySoundEffect   themeSound;
    static MainApplication m1;

    public JFrame getF() {
        return this;
    }

    public MainApplication(int x, int y,int bg,int m) 
    {
        setTitle("MainApplication");
        themeSound = new MySoundEffect("sound/theme.wav"); themeSound.playLoop();
        setBounds(x, y, 800, 600);
        setResizable(false);
        setVisible(true);
        MyImageIcon backgroundImg = new MyImageIcon("resources/bg.jpg").resize(800,580);        
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        
        c = (JPanel) getContentPane();
        c.setLayout(new BorderLayout());
        JLabel label = new JLabel(backgroundImg);

        UIManager.put("Label.font", new Font("Serif", Font.BOLD, 12));
        UIManager.put("Button.font", new Font("Serif", Font.BOLD + Font.ITALIC, 12));
        UIManager.put("TitledBorder.font", new Font("Serif", Font.BOLD + Font.ITALIC, 16));
        UIManager.put("ComboBox.font", new Font("Serif", Font.BOLD, 16));
        UIManager.put("RadioButton.font", new Font("Serif", Font.BOLD + Font.ITALIC, 12));



        //Label label1 = new JLabel(backgroundImg);
        button1 = new JButton("Start game");
        button1.setBounds(331, 206, 100, 35);
        
        button1.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                themeSound.stop();
                Thread t = new Thread("PG") 
                {
                    public void run() 
                    {                        
                        if (m == 1) {
                            new GameBoard(30, 200, 10, 0, bg, getF().getX(), getF().getY());
                        } else if (m == 2) {
                            new GameBoard(30, 150, 10, 0, bg, getF().getX(), getF().getY());
                        } else if (m == 3) {
                            new GameBoard(20, 150, 10, 0, bg, getF().getX(), getF().getY());
                        } else if (m == 4) {
                            new GameBoard(20, 150, 10, 1, bg, getF().getX(), getF().getY());
                        } else if (m == 5) {
                            new GameBoard(1, 150, 7, 3, bg, getF().getX(), getF().getY());
                        }
                    }

                };
                t.start();   

                dispose();

            }
        });
        button2 = new JButton("Option");
        button2.setBounds(331, 256, 100, 35);
        button2.addActionListener(e ->{
            themeSound.stop();
            new Option(getF().getX(), getF().getY());
            dispose();
        }); 
        button3 = new JButton("Credit");
        button3.setBounds(331, 356, 100, 35);
        button3.addActionListener(e ->{
            themeSound.stop();
            new Credit(getF().getX(), getF().getY(),bg,m);
            dispose();
        });
        
        button4 = new JButton("Exit");
        button4.setBounds(331, 406, 100, 35);
        button4.addActionListener(e ->{
            System.exit(0);
        });
        
        button5 = new JButton("How to play");
        button5.setBounds(331, 306, 100, 35);
        button5.addActionListener(e ->{
            themeSound.stop();
             new Howtoplay(getF().getX(), getF().getY(),bg,m);
            dispose();
        });
        
        label.add(button1);
        label.add(button2);
        label.add(button3);
        label.add(button4);
        label.add(button5);
        //add(label1);
        add(label);

        validate();

       
    }

    public static void main(String args[]) {
        //  MainApplication.setCur(100,100);
        new MainApplication(100, 100,1,1);

    }
}

class MySoundEffect
{
    private java.applet.AudioClip audio;

    public MySoundEffect(String filename)
    {
	try
	{
            java.io.File file = new java.io.File(filename);
            audio = java.applet.Applet.newAudioClip(file.toURL());
	}
	catch (Exception e) { e.printStackTrace(); }
    }
    public void playOnce()   { audio.play(); }
    public void playLoop()   { audio.loop(); }
    public void stop()       { audio.stop(); }
}

class MyImageIcon extends ImageIcon 
{
    public MyImageIcon(String fname) {
        super(fname);
    }

    public MyImageIcon(Image image) {
        super(image);
    }

    public MyImageIcon resize(int width, int height) {
        Image oldimg = this.getImage();
        Image newimg = oldimg.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
        return new MyImageIcon(newimg);
    }
}