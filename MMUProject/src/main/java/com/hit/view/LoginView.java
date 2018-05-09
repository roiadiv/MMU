package com.hit.view;

import java.awt.*;
import java.util.Observable;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class LoginView extends Observable implements View, ActionListener
{
	public JFrame mainWinOfLogin;
	private GridBagConstraints constraints;
	private JLabel jLabelUserName;
	private JTextField textForUserName;
	private JLabel jLabelPassWord;
	private JPasswordField  textForPassWord;
	private JLabel jLabelConfigFile;
	private JTextField textForConfigFile;
	private JButton jButtonLogin;
	private String[] userDetails;

	@Override
	public void start() { 
		userDetails = new String[3];
		mainWinOfLogin = new JFrame("Login");
		mainWinOfLogin.setLayout(new BorderLayout());
		mainWinOfLogin.setSize((int)(Toolkit.getDefaultToolkit().getScreenSize().width * 0.25), (int)(Toolkit.getDefaultToolkit().getScreenSize().height * 0.2));
		mainWinOfLogin.setLocationRelativeTo(null);
		
		constraints = new GridBagConstraints();
		
		JPanel panel = new JPanel(new GridBagLayout());	
		
		jLabelUserName = new JLabel("UserName: ");
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		panel.add(jLabelUserName,constraints);
			
		textForUserName = new JTextField(10);
		constraints.gridx = 1;
		constraints.gridy = 0;
		constraints.gridwidth = 2;
		panel.add(textForUserName,constraints);

		jLabelPassWord = new JLabel("Password: ");
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		panel.add(jLabelPassWord,constraints);
		
		textForPassWord = new JPasswordField(10);
		constraints.gridx = 1;
		constraints.gridy = 1;
		constraints.gridwidth = 2;
	    panel.add(textForPassWord, constraints);
	    panel.setBorder(new LineBorder(Color.GRAY));
		
		jLabelConfigFile = new JLabel("Filename: ");
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.gridwidth = 1;
		panel.add(jLabelConfigFile,constraints);
			
		textForConfigFile = new JTextField(10);
		constraints.gridx = 1;
		constraints.gridy = 2;
		constraints.gridwidth = 2;
		panel.add(textForConfigFile,constraints);
		
		jButtonLogin = new JButton("Login");
		constraints.gridx = 1;
		constraints.gridy = 3;
		constraints.gridwidth = 2;
		jButtonLogin.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				
					userDetails[0] = textForUserName.getText();
					userDetails[1] = textForPassWord.getText();
					userDetails[2] = textForConfigFile.getText();
					mainWinOfLogin.dispose();					
					setChanged();
					notifyObservers(userDetails);
			}
		});	
		
		panel.add(jButtonLogin,constraints);		
		mainWinOfLogin.add(panel);
		mainWinOfLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWinOfLogin.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == jButtonLogin){
			userDetails[0] = textForUserName.getText();
			userDetails[1] = textForPassWord.getText();
			userDetails[2] = textForConfigFile.getText();
			setChanged();
			notifyObservers(userDetails);
			mainWinOfLogin.dispose();
		}
	}
}

