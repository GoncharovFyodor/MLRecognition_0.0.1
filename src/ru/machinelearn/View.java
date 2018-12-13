package ru.machinelearn;

import javax.swing.*;
import java.awt.*;

public class View {
    public static void view(){
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run () {
                // TODO Auto-generated method stub
                TextFrame frame = new TextFrame();
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}
