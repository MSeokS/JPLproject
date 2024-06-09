package client;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.*;

public class ChatGUI extends JFrame{	
	private String[] msg = new String[100];
	private int strCnt = 0;
	private JTextArea chatArea;
	private JTextField statusField;
	private String name = "";
    private PrintWriter writer;
	
	ChatGUI() {
		// 메인 프레임 생성
        setTitle("Chat Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        // 중앙 채팅창
        chatArea = new JTextArea();
        chatArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(chatArea);

        // 상태 표시 창
        statusField = new JTextField("Status", 6);
        statusField.setEditable(false);
        statusField.setHorizontalAlignment(JTextField.CENTER);
        
        // 입력 창
        JTextField inputField = new JTextField();
        
        InputEnterListener inputlistener = new InputEnterListener(inputField);
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
        ButtonClickListener buttonlistener = new ButtonClickListener();
        button1.addActionListener(buttonlistener);
        button2.addActionListener(buttonlistener);
        button3.addActionListener(buttonlistener);
        button4.addActionListener(buttonlistener);
        button5.addActionListener(buttonlistener);

        // 레이아웃 설정
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(scrollPane, BorderLayout.CENTER);
        centerPanel.add(inputPanel, BorderLayout.SOUTH);

        add(centerPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
	}
	
	// 프로그래밍 언어 버튼 클릭 시 이벤트 핸들러
	class ButtonClickListener implements ActionListener {
		
		ButtonClickListener () {
			statusField.setText("All");
		}
		public void actionPerformed (ActionEvent e) {
            JButton source = (JButton) e.getSource();
            
            if(source.getText().equals(statusField.getText())) {
    			statusField.setText("All");
            } else {
            	statusField.setText(source.getText());
            }
            setMessage();
		}
	}
	
	// 채팅 창에서 엔터 입력 시 이벤트 핸들러
	class InputEnterListener implements ActionListener {
		private JTextField inputField;
		
		InputEnterListener (JTextField inputField) {
			this.inputField = inputField;
		}
		public void actionPerformed (ActionEvent e) {
            getMessage("(" + statusField.getText() + ") " + name + " : " + inputField.getText());
            setMessage();
            
            SendMessage send = new SendMessage(writer);
            send.run("(" + statusField.getText() + ") " + name + " : " + inputField.getText());
            
            inputField.setText("");
		}
	}
	
	
	public void setMessage() {
		String status = statusField.getText();
		chatArea.setText("");
		for (int i = 0; i < strCnt; i++) {
			if(status.equals(msg[i].substring(1, msg[i].indexOf(")"))) || status.equals("All"))
				chatArea.append(msg[i] + "\n");
		}
		repaint();
    }
	
	public void getMessage(String message) {
		msg[strCnt] = message;
		strCnt++;
		setMessage();
    }
	
	public void setWriter (PrintWriter writer) {
		this.writer = writer;
	}
	
	public void setName (String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
}
