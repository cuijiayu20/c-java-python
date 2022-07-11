package com.view;
import javax.swing.JTable;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.dao.BookDao;
import com.dao.BookTypeDao;
import com.model.Book;
import com.model.BookType;
import com.util.DbUtil;
import com.util.StringUtil;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BookManageInterFrm extends JInternalFrame {
	private JTable bookTable;
	private JTextField s_bookNameTxt;
	private JTextField s_authorTxt;
    private DbUtil dbUtil=new DbUtil();
    private BookTypeDao bookTypeDao= new BookTypeDao();
    private BookDao bookDao=new BookDao();
    private JComboBox s_bookTypeJcb;
    private JTextField idTxt;
    private JTextField bookNameTxt;
    private final ButtonGroup buttonGroup = new ButtonGroup();
    private JTextField priceTxt;
    private JTextField authorTxt;
	private JRadioButton manJrb;
	private JRadioButton femaleJrb;
	private JTextArea bookDescTxt;
	private JComboBox bookTypeJcb;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookManageInterFrm frame = new BookManageInterFrm();
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
	public BookManageInterFrm() {
		setIconifiable(true);
		setClosable(true);
		setTitle("图书管理");
		setBounds(100, 100, 631, 799);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "\u641C\u7D22\u6761\u4EF6", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "\u8868\u5355\u64CD\u4F5C", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(34)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, groupLayout.createParallelGroup(Alignment.LEADING, false)
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 541, GroupLayout.PREFERRED_SIZE)
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 542, Short.MAX_VALUE)))
					.addContainerGap(43, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(36)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 219, GroupLayout.PREFERRED_SIZE)
					.addGap(30)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 348, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(26, Short.MAX_VALUE))
		);
		
		JLabel lblNewLabel_3 = new JLabel("编号：");
		lblNewLabel_3.setFont(new Font("宋体", Font.PLAIN, 20));
		
		idTxt = new JTextField();
		idTxt.setEditable(false);
		idTxt.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("图书名称：");
		lblNewLabel_4.setFont(new Font("宋体", Font.PLAIN, 20));
		
		bookNameTxt = new JTextField();
		bookNameTxt.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("作者性别：");
		lblNewLabel_5.setFont(new Font("宋体", Font.PLAIN, 20));
		
		manJrb = new JRadioButton("男");
		manJrb.setSelected(true);
		buttonGroup.add(manJrb);
		
		 femaleJrb = new JRadioButton("女");
		buttonGroup.add(femaleJrb);
		
		JLabel lblNewLabel_6 = new JLabel("价格：");
		lblNewLabel_6.setFont(new Font("宋体", Font.PLAIN, 20));
		
		priceTxt = new JTextField();
		priceTxt.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("图书作者：");
		lblNewLabel_7.setFont(new Font("宋体", Font.PLAIN, 20));
		
		authorTxt = new JTextField();
		authorTxt.setColumns(10);
		
		JLabel lblNewLabel_8 = new JLabel("图书类别：");
		lblNewLabel_8.setFont(new Font("宋体", Font.PLAIN, 20));
		
		 bookTypeJcb = new JComboBox();
		
		JLabel lblNewLabel_9 = new JLabel("图书描述：");
		lblNewLabel_9.setFont(new Font("宋体", Font.PLAIN, 20));
		
		bookDescTxt = new JTextArea();
		
		JButton btnNewButton_1 = new JButton("修改");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				bookUpdateActionPerformed(evt);
			}
		});
		
		JButton btnNewButton_2 = new JButton("删除");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				bookDeleteActionPerformed(evt);
			}
		});
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addComponent(lblNewLabel_7)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(authorTxt, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(lblNewLabel_8, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addGroup(gl_panel_1.createSequentialGroup()
									.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
										.addGroup(gl_panel_1.createSequentialGroup()
											.addComponent(lblNewLabel_5)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(manJrb, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
										.addGroup(gl_panel_1.createSequentialGroup()
											.addComponent(lblNewLabel_3)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(idTxt, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)))
									.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel_1.createSequentialGroup()
											.addGap(50)
											.addComponent(lblNewLabel_4, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_panel_1.createSequentialGroup()
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(femaleJrb, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(lblNewLabel_6)))))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
								.addComponent(bookTypeJcb, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(priceTxt)
								.addComponent(bookNameTxt, GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(lblNewLabel_9, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(bookDescTxt, GroupLayout.PREFERRED_SIZE, 355, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
							.addGap(44)
							.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 235, Short.MAX_VALUE)
							.addComponent(btnNewButton_2, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(44, Short.MAX_VALUE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(29)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
							.addComponent(idTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblNewLabel_4)
							.addComponent(bookNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblNewLabel_3))
					.addGap(30)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNewLabel_5)
							.addComponent(manJrb)
							.addComponent(femaleJrb))
						.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNewLabel_6)
							.addComponent(priceTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(22)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_7)
						.addComponent(authorTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_8)
						.addComponent(bookTypeJcb, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_9)
						.addComponent(bookDescTxt, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton_2)
						.addComponent(btnNewButton_1))
					.addContainerGap(21, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		
		JLabel lblNewLabel = new JLabel("图书名称：");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 20));
		
		s_bookNameTxt = new JTextField();
		s_bookNameTxt.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("图书作者：");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 20));
		
		s_authorTxt = new JTextField();
		s_authorTxt.setColumns(10);
		
		 s_bookTypeJcb = new JComboBox();
		
		JLabel lblNewLabel_2 = new JLabel("图书类别：");
		lblNewLabel_2.setFont(new Font("宋体", Font.PLAIN, 20));
		
		JButton btnNewButton = new JButton("查询");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bookSearchActionPerformed(e);
			}
		});
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 20));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(6)
							.addComponent(s_bookNameTxt, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
							.addGap(32)
							.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(s_authorTxt, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(s_bookTypeJcb, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
							.addGap(69)
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(47, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(s_authorTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(s_bookNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblNewLabel))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNewLabel_2)
							.addComponent(s_bookTypeJcb, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnNewButton))
					.addContainerGap(14, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
		bookTable = new JTable();
		bookTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent met) {
				bookTableMousePressed(met);
			}
		});
		bookTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u7F16\u53F7", "\u56FE\u4E66\u540D\u79F0", "\u56FE\u4E66\u4F5C\u8005", "\u4F5C\u8005\u6027\u522B", "\u56FE\u4E66\u4EF7\u683C", "\u56FE\u4E66\u8868\u8FF0", "\u56FE\u4E66\u7C7B\u522B"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, true, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		bookTable.getColumnModel().getColumn(5).setPreferredWidth(114);
		scrollPane.setViewportView(bookTable);
		getContentPane().setLayout(groupLayout);
        this.fillBookType("search");
		this.fillBookType("modify");
        this.fillTable(new Book());
	}
	/**
	 * 图书删除
	 * @param evt
	 * @return 
	 */
	private void bookDeleteActionPerformed(ActionEvent evt){
		String id=idTxt.getText();
		if(StringUtil.isEmpty(id)) {
			JOptionPane.showMessageDialog(null, "请选择要删除的记录");
			return;
		}
		int n=JOptionPane.showConfirmDialog(null, "是否删除");
		if(n==0) {
			Connection con= null;
			try {
				con = dbUtil.getCon();
				int deleteNum=bookDao.delete(con,id);
				if(deleteNum==1) {
					JOptionPane.showMessageDialog(null, "删除成功");
					this.resetValue();
					this.fillTable(new Book());
				}
				else{
					JOptionPane.showMessageDialog(null, "删除失败");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "删除失败");
			
		} finally {
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		}
	}
	/**
	 * 图书修改事件
	 *
	 * @param evt
	 */
	
	private void bookUpdateActionPerformed(ActionEvent evt) {
    String id=this.idTxt.getText();
	if(StringUtil.isEmpty(id)){
		JOptionPane.showMessageDialog(null, "请选择要修改的记录");
		return;
	}
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
		}else if(femaleJrb.isSelected()){
			sex="女";
		}
		BookType bookType=(BookType) bookTypeJcb.getSelectedItem();
		int bookTypeId=bookType.getId();
		Book book= new Book(Integer.parseInt(id),  bookName,  author, sex,Float.parseFloat(price), bookTypeId, bookDesc);
		Connection con=null;
		try {
			con=dbUtil.getCon();
			int addNum=bookDao.update(con,book);
			if (addNum==1){
				JOptionPane.showMessageDialog(null, "修改成功");
				resetValue();
			}
			else{
				JOptionPane.showMessageDialog(null, "修改失败");
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "修改失败");
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
	 * 重置表单
	 */
	private void resetValue() {
		this.idTxt.setText("");
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
	 * 表格点击事件
	 * @param met
	 */
	private void bookTableMousePressed(MouseEvent met) {
    int row=this.bookTable.getSelectedRow();
    this.idTxt.setText((String)bookTable.getValueAt(row, 0));
    this.bookNameTxt.setText((String)bookTable.getValueAt(row, 1));
	this.authorTxt.setText((String)bookTable.getValueAt(row, 2));
	String sex=(String) bookTable.getValueAt(row, 3);
	if("男".equals(sex)){
		this.manJrb.setSelected(true);

	}else if("女".equals(sex)){
		this.femaleJrb.setSelected(true);
	}

	this.priceTxt.setText((Float)bookTable.getValueAt(row,4)+"");
	this.bookDescTxt.setText((String) bookTable.getValueAt(row,5));
    String bookTypeName = (String) this.bookTable.getValueAt(row,6);
	int n=this.bookTypeJcb.getItemCount();
	for(int i=0;i<n;i++){
		BookType item=(BookType)this.bookTypeJcb.getItemAt(i);
		if(item.getBookTypeName().equals(bookTypeName)){
			this.bookTypeJcb.setSelectedIndex(i);
		}
	}
}
    /**
     * 图书查询事件处理
     * @param e
     */
    private void bookSearchActionPerformed(ActionEvent e){
        String bookName=this.s_bookNameTxt.getText();
        String author=this.s_authorTxt.getText();
        BookType bookType=(BookType) this.s_bookTypeJcb.getSelectedItem();
        int bookTypeId=bookType.getId();
        Book book=new Book(bookName,  author, bookTypeId);
        this.fillTable(book);
        
    }
    /**
     * 初始化下拉框
     * @param type
     */
    private void fillBookType(String type){
    Connection con= null;
    dbUtil=new DbUtil();
    BookType bookType=null;
    try {
        con=dbUtil.getCon();
        ResultSet rs=bookTypeDao.list(con,new BookType());
        if("search".equals(type)){
            bookType=new BookType();
            bookType.setBookTypeName("请选择");
            bookType.setId(-1);
            this.s_bookTypeJcb.addItem(bookType);
        }
        while(rs.next()){
            bookType=new BookType();
            bookType.setBookTypeName(rs.getString("bookTypeName"));
            bookType.setId(rs.getInt("id"));
            if("search".equals(type)){
                this.s_bookTypeJcb.addItem(bookType);
            }else if("modify".equals(type)){
            this.bookTypeJcb.addItem(bookType);
            }
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
    /**
     * 表格初始化
     */
    private void fillTable(Book book){
        DefaultTableModel dtm=(DefaultTableModel)bookTable.getModel(); //初始化默认表格
        
		dtm.setRowCount(0); //设置表格行为0
		Connection con= null;//设置连接
		try {
			con=dbUtil.getCon();//建立连接
			ResultSet rs=bookDao.list(con,book); //查询发挥结果集
			while(rs.next()) {//遍历结果集
				Vector v=new Vector();//创建一个向量
				v.add(rs.getString("id"));//向量添加数据
				v.add(rs.getString("bookName"));//向量添加数据
				v.add(rs.getString("author"));//向量添加数据
                v.add(rs.getString("sex"));
                v.add(rs.getFloat("price"));
                v.add(rs.getString("bookDesc"));
                v.add(rs.getString("bookTypeName"));
                

				dtm.addRow(v);//添加数据
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			 try{
				 dbUtil.closeCon(con);
			 }catch (Exception e) {
				 e.printStackTrace();
			 }
		}
    }
}

