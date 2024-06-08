package client;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ChatGUI extends JFrame{	
	public static String[] msg = new String[100];
	public static int strCnt = 0;
	public static JTextArea chatArea;
	
	ChatGUI() {
		// 메인 프레임 생성
        JFrame frame = new JFrame("Chat Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        // 중앙 채팅창
        chatArea = new JTextArea();
        chatArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(chatArea);

        // 상태 표시 창
        JTextField statusField = new JTextField("Status", 6);
        statusField.setEditable(false);
        statusField.setHorizontalAlignment(JTextField.CENTER);
        
        // 입력 창
        JTextField inputField = new JTextField();
        
        InputEnterListener inputlistener = new InputEnterListener(inputField, statusField);
        inputField.addActionListener(inputlistener);

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
        JButton button5 = new JButton("Flutter");

        buttonPanel.add(button1);
        buttonPanel.add(button2);
        buttonPanel.add(button3);
        buttonPanel.add(button4);
        buttonPanel.add(button5);
        
        // 버튼 리스너 추가
        ButtonClickListener buttonlistener = new ButtonClickListener(statusField);
        button1.addActionListener(buttonlistener);
        button2.addActionListener(buttonlistener);
        button3.addActionListener(buttonlistener);
        button4.addActionListener(buttonlistener);
        button5.addActionListener(buttonlistener);

        // 레이아웃 설정
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(scrollPane, BorderLayout.CENTER);
        centerPanel.add(inputPanel, BorderLayout.SOUTH);

        frame.add(centerPanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
	}
	
	// 프로그래밍 언어 버튼 클릭 시 이벤트 핸들러
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
            setMessage(statusField.getText());
		}
	}
	
	// 채팅 창에서 엔터 입력 시 이벤트 핸들러
	class InputEnterListener implements ActionListener {
		private JTextField inputField;
		private JTextField statusField;		
		
		InputEnterListener (JTextField inputField, JTextField statusField) {
			this.inputField = inputField;
			this.statusField = statusField;
		}
		public void actionPerformed (ActionEvent e) {
            getMessage(statusField.getText() + " : " + inputField.getText());
            setMessage(statusField.getText());
            
            SendMessage send = new SendMessage();
            send.run(statusField.getText() + " : " + inputField.getText());
            
            inputField.setText("");
		}
	}
	
	public void setMessage(String status) {
		chatArea.setText("");
		for (int i = 0; i < strCnt; i++) {
			if(status.equals(msg[i].split(" : ")[0]) || status.equals("All"))
				chatArea.append(msg[i] + "\n");
		}
    }
	
	public void getMessage(String message) {
		msg[strCnt] = message;
		strCnt++;
    }
}
