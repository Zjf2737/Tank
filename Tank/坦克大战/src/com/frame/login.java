package com.frame;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

import org.apache.commons.lang.RandomStringUtils;

import com.tanke.Yard;
import com.util.CAPTCHALabel;
import com.util.DBHelper;

public class login extends JFrame{
	private static final long serialVersionUID=-4655235896173916415L;
	private JPanel contentPane;
	private JTextField usernameTextField;
	private JPasswordField passwordField;
	private JTextField validateTextField;
	private String randomText;
	static String username;
	public static void main(String args[]) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		}catch(Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable(){
			public void run() {
				try {
					login frame=new login();
					frame.setVisible(true);
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public login() {
		setTitle("游戏登录");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane=new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane,BoxLayout.PAGE_AXIS));
		
		JPanel usernamePanel=new JPanel();
		contentPane.add(usernamePanel);
		
		JLabel usernameLabel=new JLabel("\u7528\u6237\u540D\uFF1A");
		usernameLabel.setFont(new Font("微软雅黑",Font.PLAIN,15));
		usernamePanel.add(usernameLabel);
		
		usernameTextField=new JTextField();
		usernameTextField.setFont(new Font("微软雅黑",Font.PLAIN,15));
		usernamePanel.add(usernameTextField);
		usernameTextField.setColumns(10);
		
		JPanel passwordPanel=new JPanel();
		contentPane.add(passwordPanel);
		JLabel passwordLabel=new JLabel("\u5BC6 \u7801\uFF1A");
		passwordLabel.setFont(new Font("微软雅黑",Font.PLAIN,15));
		passwordPanel.add(passwordLabel);
		passwordField=new JPasswordField();
		passwordField.setColumns(10);
		passwordField.setFont(new Font("微软雅黑",Font.PLAIN,15));
		passwordPanel.add(passwordField);
		JPanel validatePanel=new JPanel();
		contentPane.add(validatePanel);
		JLabel validateLabel=new JLabel("\u9A8C\u8BC1\u7801\uFF1A");
		validateLabel.setFont(new Font("微软雅黑",Font.PLAIN,15));
		validatePanel.add(validateLabel);
		validateTextField=new JTextField();
		validateTextField.setFont(new Font("微软雅黑",Font.PLAIN,15));
		validatePanel.add(validateTextField);
		validateTextField.setColumns(5);
		randomText=RandomStringUtils.randomAlphanumeric(4);
		CAPTCHALabel label=new CAPTCHALabel(randomText);//随机验证码
		label.setFont(new Font("微软雅黑",Font.PLAIN,15));
		validatePanel.add(label);
		
		JPanel buttonPanel=new JPanel();
		contentPane.add(buttonPanel);
		
		JButton registerButton=new JButton("注册");
		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_registerButton_actionPerformed(e);
			}
		});
		registerButton.setFont(new Font("微软雅黑",Font.PLAIN,15));
		buttonPanel.add(registerButton);
		
		JButton submitButton=new JButton("登录");
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_submitButton_actionPerformed(e);
			}
		});
		submitButton.setFont(new Font("微软雅黑",Font.PLAIN,15));
		buttonPanel.add(submitButton);
		
		JButton cancelButton=new JButton("退出");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_cancelButton_actionPerformed(e);
			}
		});
		cancelButton.setFont(new Font("微软雅黑",Font.PLAIN,15));
		buttonPanel.add(cancelButton);
		
		pack();//自动调整窗体大小
		setLocation(com.util.SwingUtil.centreContainer(getSize()));//让窗体居中显示
	}
	
		protected void do_submitButton_actionPerformed(ActionEvent e)
		{
			username=usernameTextField.getText().trim();//获得用户输入的用户名
			char[] password=passwordField.getPassword();
			String validate=validateTextField.getText().trim();
			//进行非空校验
			if(username.isEmpty())
			{
				JOptionPane.showConfirmDialog(this, "用户名不能为空！", "警告信息", JOptionPane.WARNING_MESSAGE);
				return;
			}
			if(new String(password).isEmpty())
			{
				JOptionPane.showConfirmDialog(this, "密码不能为空！", "警告信息", JOptionPane.WARNING_MESSAGE);
				return;
			}
			if(validate.isEmpty())
			{
				JOptionPane.showConfirmDialog(this, "验证码不能为空！", "警告信息", JOptionPane.WARNING_MESSAGE);
				return;
			}
			//进行合法校验
			if(!DBHelper.exists(username))
			{
				JOptionPane.showMessageDialog(this, "用户名不存在！", "警告信息", JOptionPane.WARNING_MESSAGE);
				return;
			}
			if(!DBHelper.check(username, password))
			{
				JOptionPane.showMessageDialog(this, "密码错误！", "警告信息", JOptionPane.WARNING_MESSAGE);
				return;
			}
			if(!validate.equals(randomText))
			{
				JOptionPane.showMessageDialog(this, "验证码错误！", "警告信息", JOptionPane.WARNING_MESSAGE);
				return;
			}
			//校验完毕，显示主窗体
			EventQueue.invokeLater(new Runnable() {
				public void run()
				{
					try {
						//MainFrame frame=new MainFrame();
						//frame.setVisible(true);
						new runTank().init();
					}catch(Exception e) {
						e.printStackTrace();
					}
				}
			});
			dispose();
		}
		protected void do_registerButton_actionPerformed(ActionEvent e) {
			
			EventQueue.invokeLater(new Runnable() {
				public void run()
				{
					try {
						Register frame1=new Register();
						frame1.setVisible(true);
					}catch(Exception e) {
						e.printStackTrace();
					}
				}
			});
			dispose();
		}
		
		
		
		
		
		protected void do_cancelButton_actionPerformed(ActionEvent e) {
			System.exit(0);
		}
		

}
