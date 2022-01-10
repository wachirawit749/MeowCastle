import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import javax.swing.*;
import javax.swing.border.Border;

public class GameBoard extends JFrame 
{
    private JPanel contentpane;   
    private JPanel tapHP;
    private JPanel Choice;
    private JPanel dataBox; 
    
    private JLabel dataBoxpane;    
    private JLabel previewTower;
    private JLabel drawpane;    
    private JLabel tower1, tower2, tower3, tower4, tower5;
    private JLabel startRound;    
    
    private MyImageIcon []backgroundImg =new MyImageIcon[]{
                        new MyImageIcon("resources/background.jpg").resize(800,600),   
                        new MyImageIcon("resources/background1.jpg").resize(800,600),
                        new MyImageIcon("resources/background2.jpg").resize(800,600),
                        new MyImageIcon("resources/background3.jpg").resize(800,600),
                        new MyImageIcon("resources/background4.jpg").resize(800,600),
                        new MyImageIcon("resources/background5.jpg").resize(800,600)
                        };
   

    private MyImageIcon enemyImg[] = new MyImageIcon[]{    
                        new MyImageIcon("resources/DOG01.png").resize(80, 80),
                        new MyImageIcon("resources/DOG02.png").resize(80, 80),
                        new MyImageIcon("resources/DOG03.png").resize(80, 80),
                        new MyImageIcon("resources/DOG04.png").resize(80, 80),
                        new MyImageIcon("resources/DOG05.png").resize(80, 80)
                        };    
    
    private MyImageIcon towerImg[] = new MyImageIcon[]{
                        new MyImageIcon("resources/CAT01.png").resize(80, 80),
                        new MyImageIcon("resources/CAT02.png").resize(80, 80),
                        new MyImageIcon("resources/CAT03.png").resize(80, 80),
                        new MyImageIcon("resources/CAT04.png").resize(80, 80),
                        new MyImageIcon("resources/CAT05.png").resize(80, 80)
                        };
    
    private ArrayList<JLabel> path;
    
    private ArrayList<TowerThread> allTower;    
    private ArrayList<Tower> typeTower;
    
    private ArrayList<EnemyThread> allEnemy;
    private ArrayList<Enemy> typeEnemy;
    
    private ArrayList<MyImageIcon> dataEnemy;
    private ArrayList<MyImageIcon> dataTower;
    private ArrayList<MyImageIcon> Bullet1;
    private ArrayList<MyImageIcon> Bullet2;
    private ArrayList<MyImageIcon> Bullet3;
    private ArrayList<MyImageIcon> Bullet4;
    private ArrayList<MyImageIcon> Bullet5;
    
    private int noEnemy;
    private int plusSpeed;
    
    private boolean quit;   
    
    private Border border = BorderFactory.createLineBorder(new Color(0,0,0,0), 1); 
    
    static boolean startPhase;    
    static private int hpCastle=0;
    static private int money;
    static int round;
    static int count;
    static int moneyPerUnit;
    static final int pathX[] = {180, 180, 545, 545, 810};
    static final int pathY[] = {130, 373, 373, 256, 256};    
    static public JTextField Money;
    static public JTextField HP;    
    
    public JFrame getF(){   
        return this;
    }
     
    public GameBoard(int hpc,int m,int mpu,int ps ,int bg,int x,int y) 
    {
        Setmainframe(x,y);
        
        count = 0;
        quit = false;        
        noEnemy = 10;
        setMoney(m);
        round = 1;
        hpCastle = hpc;        
        moneyPerUnit = mpu;
        plusSpeed = ps;
        startPhase = false;
        
        Setstartvalue();
        Setcomponents(bg);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e)
            {
                hpCastle = 0;
                startPhase = false;
                quit = true;          
            }
        });
        while (!quit) 
        {
            System.out.println(" ");//important!!!!!!!
            if (startPhase) {
                try {
                    
                    for (int i = 0; i < allTower.size(); i++) {
                        if (allTower.get(i).getState() == Thread.State.NEW) {
                            allTower.get(i).start();
                        } else {
                            allTower.get(i).resume();
                        }
                    }
                } 
                catch (Exception e) {
                    System.out.println(e);
                    e.printStackTrace();
                }
                
                spawnEnemy();
                if(hpCastle>0){
                    increaseDifficulty(); 
                }
            } 
            
            if(hpCastle<=0)
            {                
                quit = true;
                break;                
            }                
        }    
        
        startPhase=false;
        contentpane.removeAll();
        dispose();
        new MainApplication(getF().getX(),getF().getY(),1,1);   
    }

    public void Setmainframe(int x,int y) {
        setTitle("Play");
        setBounds(x, y, 1050, 600);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    
    public void Setstartvalue() 
    {         
        typeEnemy = new ArrayList();
        typeEnemy.add(new Enemy(15-plusSpeed, 1, 100, enemyImg[0]));
        typeEnemy.add(new Enemy(15-plusSpeed, 3, 80, enemyImg[1]));
        typeEnemy.add(new Enemy(10-plusSpeed, 4, 125, enemyImg[3]));
        typeEnemy.add(new Enemy(30-plusSpeed, 10, 155, enemyImg[2]));
        typeEnemy.add(new Enemy(25-plusSpeed, 8, 200, enemyImg[4]));
        
        Bullet1 = new ArrayList();
        Bullet1.add(new MyImageIcon("BULLETCAT01/BULLETCAT01_1.png").resize(20, 20));
        Bullet1.add(new MyImageIcon("BULLETCAT01/BULLETCAT01_2.png").resize(20, 20));
        Bullet1.add(new MyImageIcon("BULLETCAT01/BULLETCAT01_3.png").resize(20, 20));
        Bullet1.add(new MyImageIcon("BULLETCAT01/BULLETCAT01_4.png").resize(20, 20));
        Bullet1.add(new MyImageIcon("BULLETCAT01/BULLETCAT01_5.png").resize(20, 20));
        Bullet1.add(new MyImageIcon("BULLETCAT01/BULLETCAT01_6.png").resize(20, 20));
        
        Bullet4 = new ArrayList();
        Bullet4.add(new MyImageIcon("BULLETCAT02/BULLETCAT02_1.png").resize(20, 20));
        Bullet4.add(new MyImageIcon("BULLETCAT02/BULLETCAT02_2.png").resize(20, 20));
        Bullet4.add(new MyImageIcon("BULLETCAT02/BULLETCAT02_3.png").resize(20, 20));
        Bullet4.add(new MyImageIcon("BULLETCAT02/BULLETCAT02_4.png").resize(20, 20));
        Bullet4.add(new MyImageIcon("BULLETCAT02/BULLETCAT02_5.png").resize(20, 20));
        Bullet4.add(new MyImageIcon("BULLETCAT02/BULLETCAT02_6.png").resize(20, 20));
        
        Bullet3 = new ArrayList();
        Bullet3.add(new MyImageIcon("BULLETCAT03/BULLETCAT03_1.png").resize(20, 20));
        Bullet3.add(new MyImageIcon("BULLETCAT03/BULLETCAT03_2.png").resize(20, 20));
        Bullet3.add(new MyImageIcon("BULLETCAT03/BULLETCAT03_3.png").resize(20, 20));
        Bullet3.add(new MyImageIcon("BULLETCAT03/BULLETCAT03_4.png").resize(20, 20));
        Bullet3.add(new MyImageIcon("BULLETCAT03/BULLETCAT03_5.png").resize(20, 20));
        Bullet3.add(new MyImageIcon("BULLETCAT03/BULLETCAT03_6.png").resize(20, 20));
        
        Bullet2 = new ArrayList();
        Bullet2.add(new MyImageIcon("BULLETCAT04/BULLETCAT04_1.png").resize(20, 20));
        Bullet2.add(new MyImageIcon("BULLETCAT04/BULLETCAT04_2.png").resize(20, 20));
        Bullet2.add(new MyImageIcon("BULLETCAT04/BULLETCAT04_3.png").resize(20, 20));
        
        Bullet5 = new ArrayList();
        Bullet5.add(new MyImageIcon("resources/Bullet05.png"));
        
        typeTower = new ArrayList();
        typeTower.add(new Tower(1000, 35, 170,  towerImg[0], Bullet1, "sound/knife.WAV"));
        typeTower.add(new Tower(1500, 40, 185,  towerImg[1],  Bullet2,"sound/gshot.WAV"));
        typeTower.add(new Tower(4000, 70, 230,  towerImg[2],  Bullet3, "sound/bomb.WAV"));
        typeTower.add(new Tower(3000, 40, 160,  towerImg[3],  Bullet4, "sound/beep.WAV"));
        typeTower.add(new Tower(3000, 60, 100,  towerImg[4], Bullet5, "sound/Magic.WAV"));
        
        dataEnemy = new ArrayList();
        dataEnemy.add(new MyImageIcon("STATUS/STATUSDOG01.jpg").resize(250, 600));
        dataEnemy.add(new MyImageIcon("STATUS/STATUSDOG02.jpg").resize(250, 600));
        dataEnemy.add(new MyImageIcon("STATUS/STATUSDOG04.jpg").resize(250, 600));
        dataEnemy.add(new MyImageIcon("STATUS/STATUSDOG03.jpg").resize(250, 600));
        dataEnemy.add(new MyImageIcon("STATUS/STATUSDOG05.jpg").resize(250, 600));
        
        dataTower = new ArrayList();
        dataTower.add(new MyImageIcon("STATUS/STATUSCAT01.jpg").resize(250, 600));
        dataTower.add(new MyImageIcon("STATUS/STATUSCAT02.jpg").resize(250, 600));
        dataTower.add(new MyImageIcon("STATUS/STATUSCAT03.jpg").resize(250, 600));
        dataTower.add(new MyImageIcon("STATUS/STATUSCAT04.jpg").resize(250, 600));
        dataTower.add(new MyImageIcon("STATUS/STATUSCAT05.jpg").resize(250, 600));
        
        
        
        JLabel path1 = new JLabel();
        path1.setBounds(0, 120, 180, 130);
        path1.setBackground(new Color(0,0,0,0));
        path1.setBorder(border);
        
        JLabel path2 = new JLabel();
        path2.setBounds(180, 120, 105, 373);
        path2.setBackground(new Color(0,0,0,0));
        path2.setBorder(border);
        
        JLabel path3 = new JLabel();
        path3.setBounds(285, 368, 355, 125);
        path3.setBackground(new Color(0,0,0,0));
        path3.setBorder(border);
        
        JLabel path4 = new JLabel();
        path4.setBounds(535, 241, 263, 128);
        path4.setBackground(new Color(0,0,0,0));
        path4.setBorder(border);   
        
        path = new ArrayList();
        path.add(path1);
        path.add(path2);
        path.add(path3);
        path.add(path4);              
        
        drawpane = new JLabel();
        
        drawpane.add(path1);
        drawpane.add(path2);
        drawpane.add(path3);
        drawpane.add(path4);   
        
        allEnemy = new ArrayList();
        allTower = new ArrayList();
    }

    public void Setcomponents(int bg) 
    {
        contentpane = (JPanel) getContentPane();
        contentpane.setLayout(new BorderLayout());
        
        drawpane.setIcon(backgroundImg[bg-1]);
        
        drawpane.setLayout(null);
        drawpane.setBounds(0 , 0 , 800, 600);
        
        dataBox = new JPanel(){
            @Override
            public Dimension getPreferredSize(){
                return new Dimension(250,600);
            }
        };  
        dataBox.setLayout(new BorderLayout());
       // dataBox.setBorder(border);
        
        dataBoxpane = new JLabel();
        dataBoxpane.setBounds(800,0,250,600);
       // dataBoxpane.setBorder(border);
        dataBox.add(dataBoxpane, BorderLayout.CENTER );
        

        startRound = new JLabel(new MyImageIcon("resources/STARTBUTTON.png").resize(100,100));
        drawpane.add(startRound);
        //startRound.setFont(new Font("Serif", Font.BOLD, 30));
        startRound.setBounds(690, 0, 100, 100);
        startRound.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                GameBoard.startPhase = true;
            }
        });
        startRound.setFocusable(false);
        Choice = new JPanel();  
        Choice.setLayout(new GridLayout(1,5,10,60));
        Choice.setBackground(new Color(252,210,57,155));
        tower1 = new JLabel(new MyImageIcon("resources/IconButtonCat01.png").resize(70, 50));
        tower1.setFocusable(false);
        tower1.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                
                Point p = MouseInfo.getPointerInfo().getLocation();
                SwingUtilities.convertPointFromScreen(p, drawpane);
                
                previewTower.setIcon(towerImg[0]);
                p.x=Math.max(0, p.x);
                p.y=Math.max(0, p.y);
                if(p.x>800-80){
                    p.x=800-80;
                }
                if(p.y>600-80){
                    p.y=600-80;
                }
                previewTower.setLocation(p.x, p.y);
                previewTower.setVisible(true);

                drawpane.repaint();

                for (JLabel a : path) {
                    if (previewTower.getBounds().intersects(a.getBounds())) {
                        previewTower.setVisible(false);
                        break;
                    }
                }
                for (TowerThread b : allTower) {
                    if (previewTower.getBounds().intersects(b.getLabel().getBounds())) {
                        previewTower.setVisible(false);
                        break;
                    }
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                    
                    dataBoxpane.setIcon(dataTower.get(0));
                    dataBox.validate();
            }
        });
        tower1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                
                if (previewTower.isVisible()) {
                    try {
                        for (Tower b : typeTower) {
                            if (previewTower.getIcon() == b.getIcon()&&getMoney()>=75) {
                                TowerThread a = new TowerThread(previewTower.getLocation().x, previewTower.getLocation().y, b, drawpane, allTower,  allEnemy,1);
                                GameBoard.plusMoney(-75);
                                allTower.add(a);
                                break;
                            }
                         
                        }
                        
                    } catch (Exception ex) {
                        System.out.println(ex);
                    }
                }
                previewTower.setVisible(false);
                
                previewTower.validate();
            }
        });
        
        tower2 = new JLabel(new MyImageIcon("resources/IconButtonCat02.png").resize(70, 50));
        tower2.setFocusable(false);
        tower2.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                Point p = MouseInfo.getPointerInfo().getLocation();
                SwingUtilities.convertPointFromScreen(p, drawpane);

                
                previewTower.setIcon(towerImg[1]);
                p.x=Math.max(0, p.x);
                p.y=Math.max(0, p.y);
                if(p.x>800-80){
                    p.x=800-80;
                }
                if(p.y>600-80){
                    p.y=600-80;
                }
                previewTower.setLocation(p.x, p.y);
                previewTower.setVisible(true);

                drawpane.repaint();

                for (JLabel a : path) {
                    if (previewTower.getBounds().intersects(a.getBounds())) {
                        previewTower.setVisible(false);
                        break;
                    }
                }
                for (TowerThread b : allTower) {
                    if (previewTower.getBounds().intersects(b.getLabel().getBounds())) {
                        previewTower.setVisible(false);
                        break;
                    }
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                                    dataBoxpane.setIcon(dataTower.get(1));
                    dataBox.validate();
            }
        });
        tower2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (previewTower.isVisible()) {
                    
                    try {
                        for (Tower b : typeTower) {
                            if (previewTower.getIcon() == b.getIcon()&&getMoney()>=100) {
                                TowerThread a = new TowerThread(previewTower.getLocation().x, previewTower.getLocation().y, b, drawpane, allTower, allEnemy,1);
                                GameBoard.plusMoney(-100);
                                allTower.add(a);
                                break;
                            }
                           
                        } 
                        
                    } 
                    catch (Exception ex) {
                        System.out.println(ex);
                    }
                }
                previewTower.setVisible(false);
                previewTower.validate();
            }
        });
        
        tower3 = new JLabel(new MyImageIcon("resources/IconButtonCat03.png").resize(70, 50));
        tower3.setFocusable(false);
        tower3.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                Point p = MouseInfo.getPointerInfo().getLocation();
                SwingUtilities.convertPointFromScreen(p, drawpane);

                
                previewTower.setIcon(towerImg[2]);
                p.x=Math.max(0, p.x);
                p.y=Math.max(0, p.y);
                if(p.x>800-80){
                    p.x=800-80;
                }
                if(p.y>600-80){
                    p.y=600-80;
                }
                previewTower.setLocation(p.x, p.y);
                previewTower.setVisible(true);

                drawpane.repaint();

                for (JLabel a : path) {
                    if (previewTower.getBounds().intersects(a.getBounds())) {
                        previewTower.setVisible(false);
                        break;
                    }
                }
                for (TowerThread b : allTower) {
                    if (previewTower.getBounds().intersects(b.getLabel().getBounds())) {
                        previewTower.setVisible(false);
                        break;
                    }
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                                    dataBoxpane.setIcon(dataTower.get(2));
                    dataBox.validate();
            }
        });
        tower3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                
                if (previewTower.isVisible()) {
                    
                    try {
                        for (Tower b : typeTower) {
                            if (previewTower.getIcon() == b.getIcon()&&getMoney()>=150) {
                                TowerThread a = new TowerThread(previewTower.getLocation().x, previewTower.getLocation().y, b, drawpane, allTower, allEnemy,1);
                                GameBoard.plusMoney(-150);
                                allTower.add(a);
                                break;
                            }
                        }
                      
                    } catch (Exception ex) {
                        System.out.println(ex);
                    }
                }
                previewTower.setVisible(false);
                previewTower.validate();
            }
        });
        
        tower4 = new JLabel(new MyImageIcon("resources/IconButtonCat04.png").resize(70, 50));
        tower4.setFocusable(false);
        tower4.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                Point p = MouseInfo.getPointerInfo().getLocation();
                SwingUtilities.convertPointFromScreen(p, drawpane);

               
                previewTower.setIcon(towerImg[3]);
                p.x=Math.max(0, p.x);
                p.y=Math.max(0, p.y);
                if(p.x>800-80){
                    p.x=800-80;
                }
                if(p.y>600-80){
                    p.y=600-80;
                }
                previewTower.setLocation(p.x, p.y);
                previewTower.setVisible(true);

                drawpane.repaint();

                for (JLabel a : path) {
                    if (previewTower.getBounds().intersects(a.getBounds())) {
                        previewTower.setVisible(false);
                        break;
                    }
                }
                for (TowerThread b : allTower) {
                    if (previewTower.getBounds().intersects(b.getLabel().getBounds())) {
                        previewTower.setVisible(false);
                        break;
                    }
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                    dataBoxpane.setIcon(dataTower.get(3));
                    dataBox.validate();
            }
        });
        tower4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                
                if (previewTower.isVisible()) {
                   
                    try {
                        for (Tower b : typeTower) {
                            if (previewTower.getIcon() == b.getIcon()&&getMoney()>=150) {
                                TowerThread a = new TowerThread(previewTower.getLocation().x, previewTower.getLocation().y, b, drawpane, allTower, allEnemy,2);
                                GameBoard.plusMoney(-150);
                                allTower.add(a);
                                break;
                            }
                        }
                      
                    } catch (Exception ex) {
                        System.out.println(ex);
                    }
                }
                previewTower.setVisible(false);
                previewTower.validate();
            }
        });
        
        tower5 = new JLabel(new MyImageIcon("resources/IconButtonCat05.png").resize(70, 50));
        tower5.setFocusable(false);
        tower5.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                Point p = MouseInfo.getPointerInfo().getLocation();
                SwingUtilities.convertPointFromScreen(p, drawpane);

                
                previewTower.setIcon(towerImg[4]);
                p.x=Math.max(0, p.x);
                p.y=Math.max(0, p.y);
                if(p.x>800-80){
                    p.x=800-80;
                }
                if(p.y>600-80){
                    p.y=600-80;
                }
                previewTower.setLocation(p.x, p.y);
                previewTower.setVisible(true);

                drawpane.repaint();

                for (JLabel a : path) {
                    if (previewTower.getBounds().intersects(a.getBounds())) {
                        previewTower.setVisible(false);
                        break;
                    }
                }
                for (TowerThread b : allTower) {
                    if (previewTower.getBounds().intersects(b.getLabel().getBounds())) {
                        previewTower.setVisible(false);
                        break;
                    }
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                    dataBoxpane.setIcon(dataTower.get(4));
                    dataBox.validate();
            }
        });
        tower5.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                
                if (previewTower.isVisible()) {
                    
                    try {
                        for (Tower b : typeTower) {
                            if (previewTower.getIcon() == b.getIcon()&&getMoney()>=175) {
                                TowerThread a = new TowerThread(previewTower.getLocation().x, previewTower.getLocation().y, b, drawpane, allTower, allEnemy,3);
                                GameBoard.plusMoney(-175);
                                allTower.add(a);
                                break;
                                
                            }
                        }
                      
                    } catch (Exception ex) {
                        System.out.println(ex);
                    }
                }
                previewTower.setVisible(false);
                previewTower.validate();
            }
        });
        
        previewTower = new JLabel();
        previewTower.setBounds(0, 0, 80, 80);
        previewTower.setVisible(false);
        drawpane.add(previewTower); 
        
        Choice.add(tower1);     
        Choice.add(tower2);
        Choice.add(tower3);
        Choice.add(tower4);
        Choice.add(tower5);
      
        HP = new JTextField(getHpCastle() + "");
        HP.setEditable(false);
        HP.setBounds(5, 5, 100, 100);
        HP.setPreferredSize(new Dimension(100,20));
        HP.setBorder(border);
        Money = new JTextField(getMoney() + "");
        Money.setEditable(false);
        Money.setBounds(5, 5, 100, 100);
        Money.setPreferredSize(new Dimension(100,20));
        Money.setBorder(border);
        tapHP = new JPanel(){
            public Dimension getPreferredSize(){
                return new Dimension(250,80);
            }
        };  
        tapHP.setLayout(new GridLayout(2,2));
        tapHP.add(new JLabel("   HP       : "));
        tapHP.add(HP);
        tapHP.add(new JLabel("   Money : "));
        tapHP.add(Money);
        tapHP.setLocation(750, 55);
        tapHP.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "HP&Money"));
        Choice.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Tower"));
        JPanel control = new JPanel(){
            public Dimension getPreferredSize(){
                return new Dimension(1050,80);
            }
        };        
        control.setLayout(new BorderLayout());        
        
        control.add(Choice);
        control.add(tapHP, BorderLayout.EAST);
        
        
        
        contentpane.add(control, BorderLayout.SOUTH);
        contentpane.add(dataBox, BorderLayout.EAST);
        contentpane.add(drawpane, BorderLayout.CENTER);
        dataBox.setFocusable(true);
        dataBox.requestFocusInWindow(); 
        dataBox.addKeyListener(new KeyAdapter(){
            public void keyPressed(KeyEvent e){
                if ( e.getKeyCode() == KeyEvent.VK_1 )
                    {
                        
                        dataBoxpane.setIcon(dataEnemy.get(0));
                        dataBox.validate();
                    }
                else if ( e.getKeyCode() == KeyEvent.VK_2)
                    {
                        dataBoxpane.setIcon(dataEnemy.get(1));
                        dataBox.validate();
                    }
                else if ( e.getKeyCode() == KeyEvent.VK_3 )
                    {
                        
                        dataBoxpane.setIcon(dataEnemy.get(2));
                        dataBox.validate();
                    }
                else if ( e.getKeyCode() == KeyEvent.VK_4 )
                    {
                        dataBoxpane.setIcon(dataEnemy.get(3));
                        dataBox.validate();
                    }
                else if ( e.getKeyCode() == KeyEvent.VK_5 )
                    {
                        dataBoxpane.setIcon(dataEnemy.get(4));
                        dataBox.validate();
                    }
                else if ( e.getKeyCode() == KeyEvent.VK_6 )
                    {
                        dataBoxpane.setIcon(dataTower.get(0));
                        dataBox.validate();
                    }
                else if ( e.getKeyCode() == KeyEvent.VK_7 )
                    {
                        dataBoxpane.setIcon(dataTower.get(1));
                        dataBox.validate();
                    }
                else if ( e.getKeyCode() == KeyEvent.VK_8 )
                    {
                        dataBoxpane.setIcon(dataTower.get(2));
                        dataBox.validate();
                    }
                else if ( e.getKeyCode() == KeyEvent.VK_9 )
                    {
                        dataBoxpane.setIcon(dataTower.get(3));
                        dataBox.validate();
                    }
                else if ( e.getKeyCode() == KeyEvent.VK_0 )
                    {
                        dataBoxpane.setIcon(dataTower.get(4));
                        dataBox.validate();
                    }
            }
        });

        pack();
    }

    public void spawnEnemy() 
    {
        int kiki = noEnemy;
        kiki -= Math.min(round/5, 3);
        kiki -= Math.min(round/6, 4);        
        
        for (int i = 0; i < 3; i++) 
        {
            int rand = (int)(Math.random()*10)%Math.min(round, 3);   
            
                EnemyThread en = new EnemyThread(-27, 130, typeEnemy.get(rand), drawpane, allEnemy);
                 allEnemy.add(en);
                  en.start();            
     
            try {
                if(!startPhase)
                    break;
                TimeUnit.MILLISECONDS.sleep(2500);
            } catch (InterruptedException ex) {
                System.out.println(ex);
            }
        }
        
        for (int i = 0; i < noEnemy-kiki; i++) 
        {
            int rand = (int)(((Math.random()*10)%2)+3);  
             EnemyThread en = new EnemyThread(-27, 130, typeEnemy.get(rand), drawpane, allEnemy);
             allEnemy.add(en);
             en.start();
             try {
                 if(!startPhase)
                    break;
                 TimeUnit.SECONDS.sleep(3);
             } 
             catch (InterruptedException ex) {
                    System.out.println(ex);
             }
        }
        for (int i = 0; i < kiki-3; i++) 
        {
            int rand = (int)(Math.random()*10)%Math.min(round, 3);   
            
                EnemyThread en = new EnemyThread(-27, 130, typeEnemy.get(rand), drawpane, allEnemy);
                 allEnemy.add(en);
                  en.start();            
     
            try {
                if(!startPhase)
                    break;
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException ex) {
                System.out.println(ex);
            }
        }
       
        
        while (!allEnemy.isEmpty()&&startPhase)
        {
            if(getHpCastle()<=0)
                break;
            try 
            {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException ex) {
                System.out.println(ex);
            }           
        }

        startPhase = false;
        round++;
    }

    public void increaseDifficulty() 
    {
        JLabel Round = new JLabel("Round "+(round-1)+" Clear!");
        Round.setBounds(285, 368, 355, 125);
        Round.setFont(new Font("Serif", Font.BOLD+Font.ITALIC, 45));
        drawpane.add(Round);
        for(int i=10;i<256;i+=4){
            Round.setForeground(new Color(255,255,255,i));
            try{
                TimeUnit.MILLISECONDS.sleep(17);
                drawpane.repaint();
            }
            catch(Exception ex)
            {
                System.out.println(ex);
            }
            
        }
        
        if (round % 5 == 0) {
            noEnemy++;//increase noEnemy 
            for (Enemy e : typeEnemy) {
                e.getStrenge(round);
            }
        }
        
        plusMoney( getHpCastle() / 2);//give bonus coins    
        
        try{
            TimeUnit.MILLISECONDS.sleep(1500);            
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
        
        drawpane.remove(Round);
        drawpane.repaint();
    }    
    
    synchronized static public void damageCastle(int d)
    {
        setHpCastle( getHpCastle() - d);
        HP.setText(getHpCastle() + "");
        
        if(getHpCastle()<=0)
        {
            setHpCastle(0);
            HP.setText(getHpCastle() + "");            
            JFrame f = new JFrame();
            if(count==0)
            {
                count=1;
                startPhase = false;  
                JOptionPane.showMessageDialog(f,"       EndGame  "+"Round : "+round,"GameOver",JOptionPane.INFORMATION_MESSAGE);                               
            } 
        }
    }
    
    synchronized static public void plusMoney(int d)
    {
        setMoney( getMoney() +d);
        Money.setText(getMoney() + "");       
    }
    
    static public int Positive(double x, double y) 
    {
        if (x > y) {
            return 1;
        } else if (x < y) {
            return -1;
        }
        return 0;
    }
    
    public static int getMoney(){
        return money;
    }   
    public static void setMoney(int d){
        money = d;
    } 
    public static int getHpCastle(){
        return hpCastle;
    }   
    public static void setHpCastle(int d){
        hpCastle = d;
    }
}

class Tower 
{
    private int range;
    private int fireSpeed;
    private int damage;
    private MyImageIcon TIcon;
    private ArrayList<MyImageIcon> bulletImg;
    private String fireSound;
    public int getRange (){
        return range;
    }
    public int getFireSpeed (){
        return fireSpeed;
    }
    public int getDamage (){
        return damage;
    }
    public String getSound()
    {
        return fireSound;
    }
    public MyImageIcon getIcon()
    {
        return TIcon;
    }
    public ArrayList<MyImageIcon> getBulletIcon(){
        return bulletImg;
    }
    public Tower(int f, int d, int r, MyImageIcon t, ArrayList<MyImageIcon> b, String fs) {
        fireSpeed = f;
        damage = d;
        range = r;
        bulletImg = b;
        TIcon = t;
        fireSound = fs;
    }
    
}

class TowerThread extends Thread 
{
    private int range;
    private int fireSpeed;
    private int damage;
    private int noBullet;
    
    private ArrayList<MyImageIcon> bulletImg;
    
    private JLabel TLabel;
    private String fireSound;

    private int targetX;
    private int targetY;
    private int X, Y;

    private boolean quit;
    
    private JLabel drawpane;
    
    private ArrayList<EnemyThread> allEnemy;
    private ArrayList<TowerThread> allTower;
    
    public JLabel getLabel(){        
        return TLabel;
    }
    
    public TowerThread(int x, int y, Tower t, JLabel d, ArrayList<TowerThread> allT, ArrayList<EnemyThread> e,int n) 
    {      
        quit = false;
        
        this.setName("TT");
                
        X = x;
        Y = y;
        
        bulletImg = t.getBulletIcon();
        
        drawpane = d;
        
        range = t.getRange();
        fireSpeed = t.getFireSpeed();
        damage = t.getDamage();
        TLabel = new JLabel(t.getIcon());
        fireSound = t.getSound();
        noBullet = n;
        
        TLabel.setBounds(X, Y, 60, 60);
        drawpane.add(TLabel);

        drawpane.validate();
        TLabel.repaint();

        X += 10; //set center
        Y += 10; //set center
        
        targetX = X + range;
        targetY = Y;
        
        TLabel.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                Point p = MouseInfo.getPointerInfo().getLocation();
                SwingUtilities.convertPointFromScreen(p, drawpane);

                double calrange = Math.atan2((p.y - Y), (p.x - X));
                
                targetX = (int) ((range) * Math.cos(calrange)) + X;
                targetY = (int) (range * Math.sin(calrange)) + Y;
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                
            }
        });
                
        allEnemy = e;
        
        allTower = allT;
        
        allTower.add(this);
        
        if(GameBoard.startPhase)
        {
            start();
        }            
    }

    @Override
    public void run() 
    {
        while (!quit) 
        {   
            MySoundEffect sound = new MySoundEffect(fireSound);
            try {
                Thread.sleep(fireSpeed+800);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
            sound.playOnce(); 
            
            if(noBullet==3){
                Bullet b1 = new Bullet(bulletImg, X, Y, 8, damage, targetX+10, targetY+10, drawpane, allEnemy);  
                Bullet b2 = new Bullet(bulletImg, X, Y, 7, damage, targetX, targetY, drawpane, allEnemy);  
                Bullet b3 = new Bullet(bulletImg, X, Y, 6, damage, targetX-10, targetY-10, drawpane, allEnemy);  
            }
            else if(noBullet==2){
                Bullet b1 = new Bullet(bulletImg, X, Y, 7, damage, targetX+10, targetY+10, drawpane, allEnemy);  
                Bullet b2 = new Bullet(bulletImg, X, Y, 6, damage, targetX-10, targetY-10, drawpane, allEnemy);  
            }
            else {
                Bullet b = new Bullet(bulletImg, X, Y, 5, damage, targetX, targetY, drawpane, allEnemy);  
            }    
            
            if (!GameBoard.startPhase) 
            {                
                if(GameBoard.getHpCastle()<=0)
                {                    
                    sound.stop();                    
                    quit = true;
                    break;                
                }   
                sound.stop();
                suspend();
            }           
        }        
        allTower.remove(this);
    }
}

class Bullet extends Thread 
{
    private double curX, curY;
    private int speed; 
    private int damage;
    
    private JLabel BLabel;
    private JLabel drawpane;
    private ArrayList<MyImageIcon>  bulletANM;
    private int targetX, targetY;
    private boolean hit;
    
    private ArrayList<EnemyThread> allEnemy;

    public Bullet(ArrayList<MyImageIcon> p, int x, int y, int s, int d, int X, int Y, JLabel draw, ArrayList<EnemyThread> e) 
    {
        this.setName("Bullet");
        bulletANM =p;
        BLabel =new JLabel(p.get(0));
        curX = x;
        curY = y;
        speed = s;
        damage = d;
        targetX = X;
        targetY = Y;
        hit = false;
        
        allEnemy = e;
        
        drawpane = draw;
        
        start();
    }

    @Override
    public void run() 
    {
        BLabel.setBounds((int)curX, (int)curY, 30, 30);
        drawpane.add(BLabel);

        BLabel.repaint();
        
        int directX=0,directY=0;
        
        double dummy=1;
         Thread temp = new Thread("ANMbullet"){
         public void run(){
            while(!hit){
                 for(int j=0;j<bulletANM.size();j++){
                    BLabel.setIcon(bulletANM.get(j));
                    try{
                        TimeUnit.MILLISECONDS.sleep(34);
                        BLabel.repaint();
                    }
                    catch(Exception ex)
                    {
                        System.out.println(ex);
                    }
                }    
            }
         }
         
         };
         temp.start();
        while (!hit) 
        {   
            int difX = targetX - (int) curX;
            int difY = targetY - (int) curY;

            if (difX == 0 && difY == 0) {
                break;
            }

            if (difX == 0 && difY != 0) {
                directY = Math.abs(difY) / (difY);
                curY += directY;
            } else if (difY == 0 && difX != 0) {
                directX = Math.abs(difX) / (difX);
                curX += directX;
            } else {
                directX = Math.abs(difX) / (difX);
                directY = Math.abs(difY) / (difY);

                dummy = (double) difX / difY;
                dummy = Math.abs(dummy);

                if (dummy < 1) {
                   

                    curX += dummy * directX;
                    curY += directY;
                } else if (dummy > 1) {
                    dummy = Math.abs((double) difY / difX);

                   

                    curX += directX;
                    curY += dummy * directY;
                } else {
                    curX += directX;
                    curY += directY;
                }
            }
                
            BLabel.setLocation((int)curX, (int)curY);
            drawpane.repaint();
            drawpane.validate();
            
            try
            {
                this.sleep(speed);
            }
            catch(Exception ex)
            {
                System.out.println(ex);
            }
            
            
            try 
            {
                for (EnemyThread e : allEnemy) 
                {                
                    if (BLabel.getBounds().intersects(e.getLabel().getBounds())&&e.getHp()>0) 
                    {
                        e.plusHP(damage * -1);                    
                        hit = true;
                        break;
                    }
                }                
            } catch (Exception e){

            }
        }

        drawpane.remove(BLabel);
        drawpane.repaint();
    }
}

class Enemy 
{
    private int speed;
    private int damage;
    private int hp;
    private MyImageIcon EIcon;
    public int getSpeed(){
        return speed;
        
    }
     public int getDamage(){
        return damage;
        
    }
    public int getHp(){
        return hp;
        
    }
    public MyImageIcon getIcon(){
        return EIcon;
        
    }
    public Enemy(int s, int d, int h, MyImageIcon e) {
        speed = s;
        damage = d;
        hp = h;
        EIcon = e;
    }

    public void getStrenge(int i) {
        damage += i / 10;
        hp += 1;
    }
}

class EnemyThread extends Thread 
{
    private int speed;
    private int damage;
    private int hp;
    private int MaxHP;
    private JLabel ELabel;
    private JLabel drawpane;
    
    private JPanel enemyBar;
    public JProgressBar hpBar;
    private int x, y;
    public int getHp(){
        return hp;
        
    }
    ArrayList<EnemyThread> allEnemy;

    public EnemyThread(int x, int y, Enemy E, JLabel d, ArrayList<EnemyThread> e) 
    {
        speed = E.getSpeed();
        damage = E.getDamage();
        hp = E.getHp();
        ELabel = new JLabel(E.getIcon());
        MaxHP = E.getHp();
        enemyBar = new JPanel();
        enemyBar.setBounds(0, 0,50, 14);
        enemyBar.setOpaque(false);
        
        hpBar = new JProgressBar(0, 50);
        //hpBar.setBounds(0, 0, hp, 14);
        hpBar.setPreferredSize( new Dimension ( 50, 14));
        //hpBar.setMaximum(hp);
        hpBar.setBackground(Color.red);
        //scaleDamage = hp/50;
        hpBar.setForeground(Color.green);
        hpBar.setValue(50);
        hpBar.setVisible(true);
        enemyBar.add(hpBar);
        
        allEnemy = e;
        
        drawpane = d;
        
        this.x = x;
        this.y = y;
    }

    public JLabel getLabel() {
        return ELabel;
    }

    synchronized public void plusHP(int d) {
        hp += d;
        hpBar.setValue(50*hp/MaxHP);
        hpBar.validate();
    }
    
    @Override
    public void run()
    {          
        int i = 0;
        
        ELabel.setBounds(x, y, 100, 120);
        drawpane.add(ELabel);        
        drawpane.add(enemyBar);        
        drawpane.validate();
        
        
        while (hp > 0&&GameBoard.startPhase) 
        {            
            if (x == GameBoard.pathX[i] && y == GameBoard.pathY[i]) 
            {
                if (i == 4) {
                    GameBoard.damageCastle(damage);
                    break;
                }
                i++;
            } 
            else 
            {
                x += 1 * GameBoard.Positive(GameBoard.pathX[i], x);
                y += 1 * GameBoard.Positive(GameBoard.pathY[i], y);
            }

            ELabel.setLocation(x, y);
            ELabel.repaint();
            enemyBar.setLocation(x + 25, y - 10);
            enemyBar.repaint();
            
            try {
                Thread.sleep(speed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if(hp<=0){
             GameBoard.plusMoney(GameBoard.moneyPerUnit);
             MyImageIcon enemyDeath[] = new MyImageIcon[]{    
                        new MyImageIcon("dogdeath/death01.png").resize(80, 80),
                        new MyImageIcon("dogdeath/death02.png").resize(80, 80),
                        new MyImageIcon("dogdeath/death03.png").resize(80, 80),
                        new MyImageIcon("dogdeath/death04.png").resize(80, 80),
                        new MyImageIcon("dogdeath/death05.png").resize(80, 80),
                        new MyImageIcon("dogdeath/death06.png").resize(80, 80)
                        };
             
            
            for(int j=0;j<enemyDeath.length*2;j++){
                ELabel.setIcon(enemyDeath[j%enemyDeath.length]);
                try{
                    TimeUnit.MILLISECONDS.sleep(34);
                    drawpane.repaint();
                }
                catch(Exception ex)
                {
                    System.out.println(ex);
                }
            }    
        }
        
       
        drawpane.remove(ELabel);
        drawpane.repaint();
        drawpane.remove(enemyBar);
        drawpane.repaint();
        
        allEnemy.remove(this);
    }
}



