package com.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.dao.BookTypeDao;
import com.model.BookType;
import com.util.DbUtil;
import com.util.StringUtil;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;

public class BookTypeAddInterFrm extends JInternalFrame {
	private JTextField textField;
	private JTextArea textArea;
    private DbUtil dbUtil=new DbUtil();
    private BookTypeDao bookTypeDao= new BookTypeDao();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookTypeAddInterFrm frame = new BookTypeAddInterFrm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public BookTypeAddInterFrm() {
		getContentPane().setFont(new Font("宋体", Font.PLAIN, 20));
		setClosable(true);
		setIconifiable(true);
		setTitle("图书类别添加");
		setBounds(100, 100, 450, 300);
		
		JLabel lblNewLabel = new JLabel("图书类别名称：");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 20));
		
		JLabel lblNewLabel_1 = new JLabel("图书类别描述：");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 20));
		
		textField = new JTextField();
		textField.setColumns(10);
		
		textArea = new JTextArea();
		
		JButton btnNewButton = new JButton("添加");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bookTypeAddActionPerformed(e);
			}
		});
		
		JButton btnNewButton_1 = new JButton("清空");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetValueActionPerformed(e);
			}
		});
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(36)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel_1)
							.addGap(18)
							.addComponent(textArea))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addGap(18)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(87, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(61)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
					.addGap(76)
					.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(107, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(53)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblNewLabel_1)))
					.addPreferredGap(ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnNewButton)
						.addComponent(btnNewButton_1))
					.addGap(22))
		);
		getContentPane().setLayout(groupLayout);

	}
	/**
	 * 图书类添加
	 * @param e
	 */
	private void bookTypeAddActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		String bookTypeName=this.textField.getText();
		String bookTypeDesc=this.textArea.getText();
		if(StringUtil.isEmpty(bookTypeName)) {
			JOptionPane.showMessageDialog(null, "图书类别名称不能为空");
			return;
		}
		BookType bookType=new BookType(bookTypeName,bookTypeDesc);
        Connection cno=null;
        try { cno= dbUtil.getCon();
        int n=bookTypeDao.add(cno,bookType);
        if(n==1){
            JOptionPane.showMessageDialog(null, "添加成功");
            resetValue();
            }else{
                JOptionPane.showMessageDialog(null, "添加失败");
                }
        }catch(Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "添加失败");
        }finally {
            try {
                dbUtil.closeCon(cno);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }


	}

	//清空
	private void resetValueActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		this.resetValue();//表示该类的对象
	}

	//重置
	private void resetValue() {
		this.textField.setText("");
		this.textArea.setText("");
	}
}
