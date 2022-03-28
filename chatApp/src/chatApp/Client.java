package chatApp;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Client extends JFrame {

	private JPanel contentPane;
	private JTextField msg_text1;
	
	static Socket s;
	static DataInputStream dis;
	static DataOutputStream dout;
	static JTextArea message1;

	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Client frame = new Client();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
		try {
			String msg=" ";
			s = new Socket("127.0.0.1",1201);
			dis = new DataInputStream(s.getInputStream());
			dout = new DataOutputStream(s.getOutputStream());
			
			while(!msg.equals("exit")) {
				msg = dis.readUTF();
				message1.setText(message1.getText()+"\n SERVER :" + msg);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 */
	public Client() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		message1 = new JTextArea();
		message1.setBounds(10, 40, 416, 166);
		contentPane.add(message1);
		
		msg_text1 = new JTextField();
		msg_text1.setBounds(10, 216, 261, 37);
		contentPane.add(msg_text1);
		msg_text1.setColumns(10);
		
		JButton msg_btn1 = new JButton("SEND");
		msg_btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String msg1 = " ";
				msg1 = msg_text1.getText();
				try {
					dout.writeUTF(msg1);
					msg_text1.setText(" ");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		msg_btn1.setBounds(293, 216, 133, 37);
		contentPane.add(msg_btn1);
		
		JLabel lblNewLabel = new JLabel("RAHUL");
		lblNewLabel.setFont(new Font("Monospaced", Font.BOLD, 20));
		lblNewLabel.setBounds(10, 10, 171, 25);
		contentPane.add(lblNewLabel);
	}
}
