package com.frame;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.regex.Pattern;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.text.AbstractDocument;

import org.apache.commons.lang.RandomStringUtils;

import com.model.User;
import com.util.CAPTCHALabel;
import com.util.DBHelper;
import com.util.DocumentSizeFilter;
import com.util.DocumentSizeListener;
import com.util.SwingUtil;

public class Register extends JFrame{
	private static final long seriaVersionUID=2491294229716316338L;
	private JPanel contentPane;
	private JTextField usernameTextField;
	private JPasswordField passwordField1;
	private JPasswordField passwordField2;
	private JTextField emailTextField;
	
	
	
	private JLabel tipLabel=new JLabel();
	public static void main(String args[]) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		}catch(Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable(){
			public void run() {
				try {
					Register frame=new Register();
					frame.setVisible(true);
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	public Register() {
		setTitle("\u7528\u6237\u6CE8\u518C");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane=new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane,BoxLayout.PAGE_AXIS));
		
		JPanel usernamePanel=new JPanel();
		contentPane.add(usernamePanel);
		
		JLabel usernameLabel=new JLabel("\u7528 \u6237 \u540D\uFF1A");
		usernameLabel.setFont(new Font("΢���ź�",Font.PLAIN,15));
		usernamePanel.add(usernameLabel);
		
		usernameTextField=new JTextField();
		usernameTextField.setToolTipText("\u8BF7\u8F93\u51655~15\u4E2A\u7531\u5B57\u6BCD\u6570\u5B57\u4E0B\u5212\u7EC4\u6210\u7684\u5B57\u7B26\u4E32");
		AbstractDocument doc=(AbstractDocument)usernameTextField.getDocument();
		//System.out.println("GetText:::"+usernameTextField.getText());
		doc.setDocumentFilter(new DocumentSizeFilter(15));//�����ı����ڿ��������ַ�����Ϊ15
		doc.addDocumentListener(new DocumentSizeListener(tipLabel,15));
		usernameTextField.setFont(new Font("΢���ź�",Font.PLAIN,15));
		usernamePanel.add(usernameTextField);
		usernameTextField.setColumns(10);
		JPanel passwordPanel1=new JPanel();
		contentPane.add(passwordPanel1);
		JLabel passwordLabel1=new JLabel("\u8F93\u5165\u5BC6\u7801\uFF1A");
		passwordPanel1.setFont(new Font("΢���ź�",Font.PLAIN,15));
		passwordPanel1.add(passwordLabel1);
		passwordField1=new JPasswordField();
		doc=(AbstractDocument)passwordField1.getDocument();
		doc.setDocumentFilter(new DocumentSizeFilter(20));
		doc.addDocumentListener(new DocumentSizeListener(tipLabel,20));
		passwordField1.setFont(new Font("΢���ź�",Font.PLAIN,15));
		passwordField1.setColumns(10);
		passwordPanel1.add(passwordField1);
		JPanel passwordPanel2=new JPanel();
		contentPane.add(passwordPanel2);
		JLabel passwordLabel2=new JLabel("\u786E\u8BA4\u5BC6\u7801\uFF1A");
		passwordLabel2.setFont(new Font("΢���ź�",Font.PLAIN,15));
		passwordPanel2.add(passwordLabel2);
		passwordField2=new JPasswordField();
		doc=(AbstractDocument)passwordField2.getDocument();
		doc.setDocumentFilter(new DocumentSizeFilter(20));//�����������ڿ��������ַ�����Ϊ20
		doc.addDocumentListener(new DocumentSizeListener(tipLabel,20));
		passwordField2.setFont(new Font("΢���ź�",Font.PLAIN,15));
		passwordField2.setColumns(10);
		passwordPanel2.add(passwordField2);
		JPanel emailPanel=new JPanel();
		contentPane.add(emailPanel);
		JLabel emailLabel=new JLabel("\u7535\u5B50\u90AE\u7BB1\uFF1A");
		emailLabel.setFont(new Font("΢���ź�",Font.PLAIN,15));
		emailPanel.add(emailLabel);
		emailTextField=new JTextField();
		doc=(AbstractDocument)emailTextField.getDocument();
		doc.setDocumentFilter(new DocumentSizeFilter(45));
		doc.addDocumentListener(new DocumentSizeListener(tipLabel,45));
		emailTextField.setFont(new Font("΢���ź�",Font.PLAIN,15));
		emailPanel.add(emailTextField);
		emailTextField.setColumns(10);
		JPanel buttonPanel=new JPanel();
		contentPane.add(buttonPanel);
		JButton submitButton=new JButton("�ύ");
		submitButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				do_submitButton_actionPerformed(e);
			}
		});
		buttonPanel.setLayout(new BoxLayout(buttonPanel,BoxLayout.LINE_AXIS));
		tipLabel.setFont(new Font("΢���ź�",Font.PLAIN,15));
		buttonPanel.add(tipLabel);
		Component glue=Box.createGlue();
		buttonPanel.add(glue);
		submitButton.setFont(new Font("΢���ź�",Font.PLAIN,15));
		buttonPanel.add(submitButton);
		JButton backButton=new JButton("����");
		backButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				do_backButton_actionPerformed(e);
			}
		});
		backButton.setFont(new Font("΢���ź�",Font.PLAIN,15));
		buttonPanel.add(backButton);
		pack();
		setLocation(SwingUtil.centreContainer(getSize()));
	
	}

	protected void do_submitButton_actionPerformed(ActionEvent e)
	{
		String username=usernameTextField.getText().trim();//����û�������û���
		char[] password1=passwordField1.getPassword();
		char[] password2=passwordField2.getPassword();
		String email=emailTextField.getText().trim();
		//���зǿ�У��
		if(username.isEmpty())
		{
			JOptionPane.showMessageDialog(this, "�û�������Ϊ�գ�", "������Ϣ", JOptionPane.WARNING_MESSAGE);
			return;
		}
		if(new String(password1).isEmpty())
		{
			JOptionPane.showMessageDialog(this, "���벻��Ϊ�գ�", "������Ϣ", JOptionPane.WARNING_MESSAGE);
			return;
		}
		if(new String(password2).isEmpty())
		{
			JOptionPane.showMessageDialog(this, "ȷ�����벻��Ϊ�գ�", "������Ϣ", JOptionPane.WARNING_MESSAGE);
			return;
		}
		if(email.isEmpty())
		{
			JOptionPane.showMessageDialog(this, "�������䲻��Ϊ�գ�", "������Ϣ", JOptionPane.WARNING_MESSAGE);
			return;
		}
		//У���û�����Ƿ�Ϸ�
		if(!Pattern.matches("\\w{5,15}", username))
		{
			JOptionPane.showMessageDialog(this, "������Ϸ����û�����", "������Ϣ", JOptionPane.WARNING_MESSAGE);
			return;
		}
		//У����������������Ƿ���ͬ
		if(!Arrays.equals(password1,password2))
		{
			JOptionPane.showMessageDialog(this, "������������벻ͬ��", "������Ϣ", JOptionPane.WARNING_MESSAGE);
			return;
		}
		//У����������Ƿ�Ϸ�
		if(!Pattern.matches("\\w+@\\w+\\.\\w+", email))
		{
			JOptionPane.showMessageDialog(this, "������Ϸ��ĵ������䣡", "������Ϣ", JOptionPane.WARNING_MESSAGE);
			return;
		}
		//У���û����Ƿ����
		if(DBHelper.exists(username))
		{
			JOptionPane.showMessageDialog(this, "�û����Ѿ����ڣ�", "������Ϣ", JOptionPane.WARNING_MESSAGE);
			return;
		}
		User user=new User();
		user.setUsername(username);
		user.setPassword(new String(password1));
		user.setEmail(email);
		Arrays.fill(password1, '0');//��ձ���������ַ�����
		Arrays.fill(password2, '0');
		if(DBHelper.sava(user))
		{
			JOptionPane.showMessageDialog(this, "�û�ע��ɹ���", "��ʾ��Ϣ", JOptionPane.WARNING_MESSAGE);
			return;
		}
		else
		{
			JOptionPane.showMessageDialog(this, "�û�ע��ʧ�ܣ�", "������Ϣ", JOptionPane.WARNING_MESSAGE);
			return;
		}
	}
	protected void do_backButton_actionPerformed(ActionEvent e) {
		EventQueue.invokeLater(new Runnable() {
			public void run()
			{
				try {
					login frame=new login();
					frame.setVisible(true);
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		});
		dispose();
		
		
		
	}
}
