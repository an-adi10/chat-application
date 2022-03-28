package chatApp;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Chat {

	private JFrame frame;
	private JTextField msg_text;
	static ServerSocket ss;
	static Socket s;
	static DataInputStream dis;
	static DataOutputStream dout;
	static JTextArea message;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Chat window = new Chat();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		String msg=" ";
		try {
			ss = new ServerSocket(1201);
			s = ss.accept();
			dis = new DataInputStream(s.getInputStream());
			dout = new DataOutputStream(s.getOutputStream());
			
			while(!msg.equals("exit")) {
				msg = dis.readUTF();
				message.setText(message.getText()+"\n CLIENT :" + msg);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Create the application.
	 */
	public Chat() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		message = new JTextArea();
		message.setFont(new Font("Monospaced", Font.BOLD, 13));
		message.setBounds(10, 34, 416, 173);
		frame.getContentPane().add(message);
		
		msg_text = new JTextField();
		msg_text.setBounds(10, 217, 268, 36);
		frame.getContentPane().add(msg_text);
		msg_text.setColumns(10);
		
		JButton msg_btn = new JButton("SEND");
		msg_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String msg1 = " ";
				msg1 = msg_text.getText();
				try {
					dout.writeUTF(msg1);
					msg_text.setText(" ");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		msg_btn.setBounds(308, 216, 118, 36);
		frame.getContentPane().add(msg_btn);
		
		JLabel lblNewLabel = new JLabel("ADITYA");
		lblNewLabel.setFont(new Font("Sitka Display", Font.BOLD, 20));
		lblNewLabel.setBounds(10, 10, 139, 26);
		frame.getContentPane().add(lblNewLabel);
	}
}
