package com.mycompany.finalproject;


import com.mycompany.finalproject.MyImageIcon;
import java.net.URL;
import javax.swing.*;
import java.awt.Color;
import java.awt.Image;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author JJ
 */
public class test {
    JFrame frame1;
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        test f = new test();
        f.load();
    }
    public void load(){
        
        Icon icon = new MyImageIcon("olaf.png").resize(200, 200);
        JLabel label = new JLabel(icon);
        label.setBackground(new Color(0,0,0,0));
        
        frame1 = new JFrame();
        frame1.setSize(250,250);
        frame1.setUndecorated(true);
        frame1.add(label);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.pack();
        frame1.setLocationRelativeTo(null);
        frame1.setBackground(new Color(0,0,0,0));
        frame1.setVisible(true);
        
        
    }
}
class MyImageIcon extends ImageIcon
{
    public MyImageIcon(String fname)  { super(fname); }
    public MyImageIcon(Image image)   { super(image); }

    public MyImageIcon resize(int width, int height)
    {
	Image oldimg = this.getImage();
	Image newimg = oldimg.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
        return new MyImageIcon(newimg);
    }
};