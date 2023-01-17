package finals;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class homePage extends JFrame{
    Font font = new Font ("Bebas Neue",Font.PLAIN,20);
	Font insert = new Font ("Consolas",Font.PLAIN,20);
	Font forTable = new Font ("Consolas",Font.PLAIN,15);
	Font title = new Font ("Bebas Neue",Font.PLAIN,20);
	Font header = new Font ("Bebas Neue",Font.PLAIN,50);

    public homePage(){
        ImageIcon logo = new ImageIcon("medicalPic.png");
		this.setIconImage(logo.getImage());

		JLabel lblHeader = new JLabel("Gentri Medical",SwingConstants.CENTER);
		lblHeader.setFont(header);

		JLabel lblPatient = new JLabel("Patient Information",SwingConstants.CENTER);
		lblPatient.setFont(header);

		JLabel lblBlood = new JLabel("Blood Type:");
		lblBlood.setFont(font);

		JLabel lblGender = new JLabel("Gender:");
		lblGender.setFont(font);

		JLabel lblName = new JLabel("Name:");
		lblName.setFont(font);

		JLabel lblAge = new JLabel("Age:");
		lblAge.setFont(font);

		JLabel lblNumber = new JLabel("Number:");
		lblNumber.setFont(font);

		JLabel lblPurpose = new JLabel("Purpose:");
		lblPurpose.setFont(font);

		JTextField tfName = new JTextField();
		tfName.setFont(insert);

		JTextField tfAge = new JTextField();
		tfAge.setFont(insert);

		JTextField tfNumber = new JTextField();
		tfNumber.setFont(insert);

		JTextField tfPurpose = new JTextField();
		tfPurpose.setFont(insert);

		String[] blood ={"A+","B+","AB+","O+","A-","B-","AB-","O-"};
		JComboBox cbBlood = new JComboBox<>(blood);
		cbBlood.setFont(forTable);

		String[] gender ={"Female","Male"};
		JComboBox cbGender = new JComboBox<>(gender);
		cbGender.setFont(forTable);

		JLabel lblData = new JLabel();
		lblData.setFont(forTable);

		int num = 0;
        try{
            Scanner data = new Scanner(new File("information.txt"));
            while(data.hasNextLine()){
                num++;
                data.nextLine();
            }
            data.close();
        }catch(Exception exe){
            System.out.println("An Error has Occured");;
        }
        String info[][] = new String[num][6];
        String lines[] = {"NAME", "AGE","NUMBER", "PURPOSE", "BLOOD TYPE", "GENDER"};
        int i = 0;
        try{
            Scanner data = new Scanner(new File("information.txt"));
            while(data.hasNextLine()){
                String s = data.nextLine();
                String[] info2 = s.split(" ");
                info[i][0] = info2[0];
                info[i][1] = info2[1];
                info[i][2] = info2[2];
                info[i][3] = info2[3];
                info[i][4] = info2[4];
                info[i][5] = info2[5];
                i++;
            }
            data.close();
        }catch(Exception exe){
            exe.printStackTrace();
        }
		DefaultTableModel table = new DefaultTableModel(info, lines);
		JTable daTable = new JTable(table);

		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.setFont(font);
		btnConfirm.setBounds(600,700,100,50);
		btnConfirm.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent c) {
				String name = tfName.getText();
                String age = tfAge.getText();
                String number = tfNumber.getText();
                String purpose = tfPurpose.getText();
                String blood = cbBlood.getSelectedItem().toString();
                String gender = cbGender.getSelectedItem().toString();

				
					if(!name.equals("")&&!age.equals("")&&!number.equals("")&&!purpose.equals("")&&!blood.equals("")&&!gender.equals("")){
						JOptionPane.showMessageDialog(null, "Information Added");
						try{
							File patientData = new File("information.txt");
							FileWriter datawriter = new FileWriter(patientData,true);
							datawriter.write(name+" "+age+" "+number+" "+purpose+" "+blood+" "+gender +"\n");
							datawriter.close();
					}catch(Exception exe){
						exe.printStackTrace();
					}
					dispose();
					new homePage();
				}

			}
		});
		JButton btnLeave = new JButton("Logout");
		btnLeave.setFont(font);
		btnLeave.setBounds(700,700,100,50);
		btnLeave.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent l) {
				dispose();
				new Lobby();
			}
			
		});
		JButton btnRev = new JButton("Remove");
		btnRev.setFont(font);
		btnRev.setBounds(500,700,100,50);
		btnRev.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent r) {
				int line = daTable.getSelectedRow();
				 if(line == -1){
					JOptionPane.showMessageDialog(null,"Select a Line!");
				 }else {
					int remove = JOptionPane.showConfirmDialog(null, "Line Removed", "Remove", JOptionPane.YES_NO_OPTION);
						if(remove == JOptionPane.YES_OPTION){
							table.removeRow(line);
				try{
					FileWriter FW = new FileWriter("information.txt");
					for(int d = 0;d <table.getRowCount(); d++){
						FW.write(table.getValueAt(d, 0).toString() + " " + table.getValueAt(d, 1).toString() + " " + 
						table.getValueAt(d, 2).toString() + " " + table.getValueAt(d, 3).toString() + " " + 
						table.getValueAt(d, 4).toString() + " " +table.getValueAt(d, 5).toString()+ "\n");
					}
					FW.close();
					JOptionPane.showMessageDialog(null, "Information Deleted");
				}catch (IOException exe){
					exe.printStackTrace();
				}
						}

				 }
			}
		});
		JPanel panel1 = new JPanel();
		panel1.setLayout(new GridLayout(10,6,6,6));
		panel1.setOpaque(false);
		panel1.add(lblHeader);
		panel1.add(lblPatient);
		panel1.add(lblData);
		panel1.add(daTable);
		
		JPanel panel2 = new JPanel();
		panel2.setLayout(new GridLayout(2,7,5,7));
		panel2.setOpaque(false);
		panel2.add(lblName);
		panel2.add(tfName);
		panel2.add(lblAge);
		panel2.add(tfAge);
		panel2.add(lblNumber);
		panel2.add(tfNumber);
		panel2.add(lblPurpose);
		panel2.add(tfPurpose);
		panel2.add(lblBlood);
		panel2.add(cbBlood);
		panel2.add(lblGender);
		panel2.add(cbGender);

		JPanel panel3 = new JPanel();
		panel3.setOpaque(false);
		panel3.add(btnConfirm);
		panel3.add(btnLeave);
		panel3.add(btnRev);

    JPanel mPanel = new JPanel();
	mPanel.setLayout(new BorderLayout());
	mPanel.setBackground(new Color(0,201,154));
	mPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
	mPanel.add(panel1, BorderLayout.NORTH);
	mPanel.add(panel2, BorderLayout.CENTER);
	mPanel.add(panel3,BorderLayout.SOUTH);
	
    add(mPanel);
    setTitle("Gentri Medical");
	setSize(1200,810);
	setMinimumSize(new Dimension(1200,810));
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setVisible(true);
    }
}
