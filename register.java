package finals;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
public class register extends JFrame{
    Font font = new Font ("Bebas Neue",Font.PLAIN,15);
	Font insert = new Font ("Consolas",Font.PLAIN,20);
	Font title = new Font ("Bebas Neue",Font.PLAIN,20);
	Font header = new Font ("Bebas Neue",Font.PLAIN,50);
	JTextField tfUser, tfPass;
	JLabel lblMed;
    public register(){
        ImageIcon logo = new ImageIcon("medicalPic.png");
		this.setIconImage(logo.getImage());

	JLabel lblHeader = new JLabel("Gentri Medical \n",SwingConstants.CENTER);
	lblHeader.setFont(header);

	JLabel lblRegMes = new JLabel("Create an Account");
	lblRegMes.setFont(title);

	JLabel lblUser = new JLabel("Enter a Username:");
	lblUser.setFont(font);

	tfUser = new JTextField();
	tfUser.setFont(insert);

	JLabel lblPass = new JLabel("Enter a Password:");
	lblPass.setFont(font);

	tfPass = new JTextField();
	tfPass.setFont(insert);

	JPanel panel1 = new JPanel();
	panel1.setLayout(new GridLayout(9,1,6,6));
	panel1.setOpaque(false);
	panel1.add(lblHeader);
	panel1.add(lblRegMes);
	panel1.add(lblUser);
	panel1.add(tfUser);
	panel1.add(lblPass);
	panel1.add(tfPass);

	lblMed = new JLabel();
	lblMed.setFont(font);

	JButton btnReg = new JButton("Register");
	btnReg.setFont(font);
	btnReg.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent a) {
			String userName = tfUser.getText();
			String passWord = tfPass.getText();

            if(!userName.equals("") && !passWord.equals("")){
				try{
					File reg = new File("account.txt");
					FileWriter writes = new FileWriter(reg,true);
					writes.write(userName+" "+passWord+"\n");
					writes.close();
					dispose();
					new Lobby();
				}catch(Exception exe){
					exe.printStackTrace();
				}
				dispose();
				new Lobby();
                
			}
		}

	});

	JPanel logReg = new JPanel();
	logReg.setLayout(new GridLayout(1,2,7,7));
	logReg.setOpaque(false);
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
	setSize(500,750);
	setMinimumSize(new Dimension(300,550));
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setVisible(true);
    }
}
