package finals;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;
import javax.swing.*;

public class Lobby extends JFrame{
	Font font = new Font ("Bebas Neue",Font.PLAIN,15);
	Font insert = new Font ("Consolas",Font.PLAIN,20);
	Font title = new Font ("Bebas Neue",Font.PLAIN,20);
	Font header = new Font ("Bebas Neue",Font.PLAIN,50);
public Lobby(){

	ImageIcon logo = new ImageIcon("medicalPic.png");
	this.setIconImage(logo.getImage());

	JLabel lblBanner = new JLabel(logo, SwingConstants.CENTER);
	lblBanner.setIcon(new ImageIcon("medicalPic.png"));


	JLabel lblHeader = new JLabel("Gentri Medical",SwingConstants.CENTER);
	lblHeader.setFont(header);

	JLabel lblRegMes = new JLabel("Click Register if you don't have an Account",SwingConstants.CENTER);
	lblRegMes.setFont(insert);

	JLabel lblLogMes = new JLabel("Login", SwingConstants.CENTER);
	lblLogMes.setFont(header);

	JLabel lblUser = new JLabel("Enter a Username:");
	lblUser.setFont(font);

	JTextField tfUser = new JTextField();
	tfUser.setFont(insert);

	JLabel lblPass = new JLabel("Enter a Password:");
	lblPass.setFont(font);

	JTextField tfPass = new JTextField();
	tfPass.setFont(insert);

	JPanel panel1 = new JPanel();
	panel1.setLayout(new GridLayout(8,1,2,2));
	panel1.setOpaque(false);
	panel1.add(lblBanner);
	panel1.add(lblHeader);
	panel1.add(lblRegMes);
	panel1.add(lblLogMes);
	panel1.add(lblUser);
	panel1.add(tfUser);
	panel1.add(lblPass);
	panel1.add(tfPass);

	JLabel lblMed = new JLabel();
	lblMed.setFont(font);

	JButton btnReg = new JButton("Register");
	btnReg.setFont(font);
	btnReg.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent a) {
			if(a.getSource()==btnReg){
				dispose();
				new register();
			}
		}
	});
	JButton btnLog = new JButton("Confirm");
	btnLog.setFont(font);
	btnLog.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent b) {
			String userName = tfUser.getText();
			String passWord = tfPass.getText();
			String check = "";
			File acc = new File("account.txt");
			if(userName.isEmpty()||passWord.isEmpty()){
				lblMed.setText("Please Insert a Username and a Password");
			}
			try(Scanner look = new Scanner(acc)){
				while(look.hasNextLine()){
					check = look.nextLine();
					String[] data = check.split(" ");
					if(userName.equals(data[0])&&passWord.equals(data[1])){
						dispose();
						new homePage();
					}
				}
			}catch(Exception exe){
				exe.printStackTrace();
			}
		}

	});
	JPanel logReg = new JPanel();
	logReg.setLayout(new GridLayout(1,2,7,7));
	logReg.setOpaque(false);
	logReg.add(btnLog);
	logReg.add(btnReg);

	JPanel mPanel = new JPanel();
	mPanel.setLayout(new BorderLayout());
	mPanel.setBackground(new Color(0,201,154));
	mPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
	mPanel.add(panel1, BorderLayout.NORTH);
	mPanel.add(lblMed, BorderLayout.CENTER);
	mPanel.add(logReg, BorderLayout.SOUTH);

	add(mPanel);

	setTitle("Gentri Medical");
	setSize(550,950);
	setMinimumSize(new Dimension(550,960));
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setVisible(true);

	}
	public static void main(String[]args) {
		new Lobby();
	}
}
