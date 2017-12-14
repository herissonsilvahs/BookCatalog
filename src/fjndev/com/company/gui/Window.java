package fjndev.com.company.gui;

import napkin.NapkinLookAndFeel;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame
{
    String title = null;
    int width = 800;
    int height = 600;
    int position_x = 0;
    int position_y = 0;
    boolean resizable = false;
    boolean visible = true;
    int close = EXIT_ON_CLOSE;

    public void setWindow()
    {
        setTitle(title);
        setSize(width, height);
        setLocation(position_x, position_y);
        setResizable(resizable);
    }

    public void drawWindow()
    {
        getContentPane().setBackground(new Color(200, 200,200));
        setVisible(visible);
        setDefaultCloseOperation(close);
    }

}
