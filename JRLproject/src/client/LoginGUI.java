package client;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LoginGUI extends JFrame {
	JTextField textField1;
	JPasswordField textField2;
	private ChatGUI chat;
	
	LoginGUI(ChatGUI chat) {
		this.chat = chat;
		
		setTitle("Login");
		
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new GridLayout(2,1));
		
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel(new BorderLayout());
		panel2.setPreferredSize(new Dimension(200, 30));
		
		JLabel label1 = new JLabel("Enter your name");
		textField1 = new JTextField(30);
		JButton button = new JButton("login");
		
		label1.setPreferredSize(new Dimension(100,15));
		button.addActionListener(new ButtonClickListener());
		
		panel1.add(label1);
		panel1.add(textField1);
		panel2.add(button, BorderLayout.CENTER);
		
		Container c = getContentPane();
		c.add(panel1);
		c.add(panel2);
		
		pack();
		setVisible(true);
	}
	class ButtonClickListener implements ActionListener {
		public void actionPerformed (ActionEvent e) {
			if(textField1.getText().equals(""))
			{
				JOptionPane.showMessageDialog(LoginGUI.this, "이름을 입력하세요", "에러", JOptionPane.ERROR_MESSAGE);
			}
			else {
				LoginGUI.this.setVisible(false);
				chat.setName(textField1.getText());
				chat.setVisible(true);
			}
		}
	}
}
