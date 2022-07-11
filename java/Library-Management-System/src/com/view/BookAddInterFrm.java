package com.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import com.dao.BookDao;
import com.dao.BookTypeDao;
import com.model.Book;
import com.model.BookType;
import com.util.DbUtil;
import com.util.StringUtil;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JDesktopPane;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BookAddInterFrm extends JInternalFrame {
	private JTextField bookNameTxt;
	private JTextField authorTxt;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField priceTxt;
    private DbUtil dbUtil=new DbUtil();
    private BookTypeDao bookTypeDao= new BookTypeDao();
    private BookDao bookDao= new BookDao();
    private JComboBox bookTypeJcb;
	private JTextArea bookDescTxt;
	private JRadioButton manJrb ;
    private JRadioButton femalejrb;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookAddInterFrm frame = new BookAddInterFrm();
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
	public BookAddInterFrm() {
		setIconifiable(true);
		setClosable(true);
		setTitle("图书添加");
		setBounds(100, 100, 627, 602);
		
		JLabel lblNewLabel = new JLabel("图书名称:");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 20));
		
		bookNameTxt = new JTextField();
		bookNameTxt.setFont(new Font("宋体", Font.PLAIN, 20));
		bookNameTxt.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("作者:");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 20));
		
		authorTxt = new JTextField();
		authorTxt.setFont(new Font("宋体", Font.PLAIN, 20));
		authorTxt.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("作者性别：");
		lblNewLabel_2.setFont(new Font("宋体", Font.PLAIN, 20));
		
		manJrb = new JRadioButton("男");
		manJrb.setSelected(true);
		buttonGroup.add(manJrb);
		manJrb.setFont(new Font("宋体", Font.PLAIN, 20));
		
		femalejrb = new JRadioButton("女");
		buttonGroup.add(femalejrb);
		femalejrb.setFont(new Font("宋体", Font.PLAIN, 20));
		
		JLabel lblNewLabel_3 = new JLabel("图书价格：");
		lblNewLabel_3.setFont(new Font("宋体", Font.PLAIN, 20));
		
		priceTxt = new JTextField();
		priceTxt.setFont(new Font("宋体", Font.PLAIN, 20));
		priceTxt.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("图书描述：");
		lblNewLabel_4.setFont(new Font("宋体", Font.PLAIN, 20));
		
		bookDescTxt = new JTextArea();
		
		JLabel lblNewLabel_5 = new JLabel("图书类别：");
		lblNewLabel_5.setFont(new Font("宋体", Font.PLAIN, 20));
		
		 bookTypeJcb = new JComboBox();
		
		JButton btnNewButton = new JButton("添加");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bookAddActionPerformed(e);
			}

			
		});
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 20));
		
		JButton btnNewButton_1 = new JButton("重置");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetValueActionPerformed(e);
			}
		});
		btnNewButton_1.setFont(new Font("宋体", Font.PLAIN, 20));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(43)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(81)
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
							.addGap(177)
							.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
							.addGap(120))
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addGroup(groupLayout.createSequentialGroup()
										.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
										.addGap(18)
										.addComponent(bookNameTxt, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
										.addGap(31)
										.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE))
									.addGroup(groupLayout.createSequentialGroup()
										.addComponent(lblNewLabel_2)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(manJrb)
										.addGap(18)
										.addComponent(femalejrb, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
										.addGap(30)
										.addComponent(lblNewLabel_3)))
								.addGap(2)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addComponent(priceTxt, GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
									.addComponent(authorTxt, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE))
								.addContainerGap(84, Short.MAX_VALUE))
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(lblNewLabel_5)
								.addGap(18)
								.addComponent(bookTypeJcb, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
								.addContainerGap())
							.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
								.addComponent(lblNewLabel_4, GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(bookDescTxt, GroupLayout.PREFERRED_SIZE, 386, GroupLayout.PREFERRED_SIZE)
								.addGap(73)))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(58)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(bookNameTxt, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1)
						.addComponent(lblNewLabel)
						.addComponent(authorTxt, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
					.addGap(31)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(manJrb, 0, 0, Short.MAX_VALUE)
						.addComponent(femalejrb, 0, 0, Short.MAX_VALUE)
						.addComponent(lblNewLabel_3)
						.addComponent(priceTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(45)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_5)
						.addComponent(bookTypeJcb, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(39)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_4)
						.addComponent(bookDescTxt, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE))
					.addGap(40)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton_1)
						.addComponent(btnNewButton))
					.addGap(58))
		);
		getContentPane().setLayout(groupLayout);
        fillBookType();
	}
	/**
	 * 重置事件
	 * @param e
	 */
	private void resetValueActionPerformed(ActionEvent e){
            this.resetValue();
	}
	/**
	 * 重置表单
	 */
	private void resetValue() {
		this.bookNameTxt.setText("");
		this.authorTxt.setText("");
		this.priceTxt.setText("");
		this.bookDescTxt.setText("");
		this.manJrb.setSelected(true);
		if(this.bookTypeJcb.getItemCount()>0){
			this.bookTypeJcb.setSelectedIndex(0);
		}

	}
	/**
	 * 添加图书
	 *
	 * @param e
	 */
	private void bookAddActionPerformed(ActionEvent evt) {
        String bookName=this.bookNameTxt.getText();
        String author=this.authorTxt.getText();
        String price=this.priceTxt.getText();
        String bookDesc=this.bookDescTxt.getText();
        if(StringUtil.isEmpty(bookName)){
			JOptionPane.showMessageDialog(null,"图书名称不能为空");
			return;
		}
		if(StringUtil.isEmpty(author)){
			JOptionPane.showMessageDialog(null,"作者不能为空");
			return;
		}
		if(StringUtil.isEmpty(price)){
			JOptionPane.showMessageDialog(null,"价格不能为空");
			return;
		}
		String sex="";
		if(manJrb.isSelected()){
			sex="男";
		}else if(femalejrb.isSelected()){
			sex="女";
		}
		//获取下拉菜单

		BookType bookType=(BookType) bookTypeJcb.getSelectedItem();
		int bookTypeId=bookType.getId();
		Book book=new Book(bookName,  author, sex, Float.parseFloat(price), bookTypeId, bookDesc);
        Connection con=null;
		try {
			con=dbUtil.getCon();
			int addNum=bookDao.add(con,book);
			if (addNum==1){
				JOptionPane.showMessageDialog(null, "添加成功");
				resetValue();
			}
			else{
				JOptionPane.showMessageDialog(null, "添加失败");
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "添加失败");
		}finally{
			try {
				dbUtil.closeCon(con);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
    /**
     * 初始化图书类别下拉框、
     * 
     */
    private void fillBookType(){
    Connection con= null;
    BookType bookType=null;
    try {
        con=dbUtil.getCon();
        ResultSet rs= bookTypeDao.list(con,new BookType());
        while (rs.next()){
            bookType=new BookType();
            bookType.setId(rs.getInt("id"));
            bookType.setBookTypeName(rs.getString("bookTypeName"));
            this.bookTypeJcb.addItem(bookType);////////////////////////////////////////
        }
    } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }finally{
        try {
            dbUtil.closeCon(con);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
            }
    
    }
}







