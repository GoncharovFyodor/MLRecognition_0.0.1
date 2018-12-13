package ru.machinelearn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class TextFrame extends JFrame {
	public TextFrame() {
		setTitle("MLRecognition - распознавание языка текста");
		setSize(300,250);
        tpanel = new JPanel(new BorderLayout());
        tpanel.setBackground(DEFAULT_COLOR);
        setContentPane(tpanel);
        tpanel.add(new JLabel("Введите текст:"), BorderLayout.NORTH);
        someTextArea = new JTextArea(9,25);
        tpanel.add(someTextArea, BorderLayout.CENTER);
        buttonPanel = new JPanel();
        detectButton=new JButton("Распознать");
        buttonPanel.add(detectButton);
        tpanel.add(buttonPanel,BorderLayout.SOUTH);
        setLocationRelativeTo(null);
        detectButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Model.someText+=someTextArea.getText()+"\r\n";
				Controller.controller();
				Model.someText="";
			}
		});
	}
	private JPanel tpanel, buttonPanel;
	private Color DEFAULT_COLOR = Color.LIGHT_GRAY;
	private JTextArea someTextArea;
	private JButton detectButton;
}
