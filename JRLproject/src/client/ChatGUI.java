package client;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ChatGUI extends JFrame{
	public static void main(String args[]) {
		ChatGUI chat = new ChatGUI();
	}
	
	ChatGUI() {
		// 메인 프레임 생성
        JFrame frame = new JFrame("Chat Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 600);

        // 중앙 채팅창
        JTextArea chatArea = new JTextArea();
        chatArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(chatArea);

        // 상태 표시 창
        JTextField statusField = new JTextField("Status", 6);
        statusField.setEditable(false);
        statusField.setHorizontalAlignment(JTextField.CENTER);
        
        // 입력 창
        JTextField inputField = new JTextField();

        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.add(statusField, BorderLayout.WEST);
        inputPanel.add(inputField, BorderLayout.CENTER);

        // 프로그래밍 언어 설정 버튼
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 4));

        JButton button1 = new JButton("C");
        JButton button2 = new JButton("Java");
        JButton button3 = new JButton("Python");
        JButton button4 = new JButton("Java Script");

        buttonPanel.add(button1);
        buttonPanel.add(button2);
        buttonPanel.add(button3);
        buttonPanel.add(button4);
        
        // 버튼 리스너 추가
        ButtonClickListener btlistener = new ButtonClickListener(statusField);
        button1.addActionListener(btlistener);
        button2.addActionListener(btlistener);
        button3.addActionListener(btlistener);
        button4.addActionListener(btlistener);

        // 레이아웃 설정
        frame.setLayout(new BorderLayout());
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(inputPanel, BorderLayout.SOUTH);
        frame.add(buttonPanel, BorderLayout.PAGE_END);

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(scrollPane, BorderLayout.CENTER);
        centerPanel.add(inputPanel, BorderLayout.SOUTH);

        frame.add(centerPanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
	}
	class ButtonClickListener implements ActionListener {
		private JTextField statusField;
		
		ButtonClickListener (JTextField statusField) {
			this.statusField = statusField;
			this.statusField.setText("All");
		}
		public void actionPerformed (ActionEvent e) {
            JButton source = (JButton) e.getSource();
            
            if(source.getText().equals(statusField.getText())) {
    			this.statusField.setText("All");
            } else {
            	statusField.setText(source.getText());
            }
		}
	}
}
