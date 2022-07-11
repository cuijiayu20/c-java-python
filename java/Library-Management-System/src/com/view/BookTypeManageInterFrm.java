package com.view;



import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.dao.BookTypeDao;
import com.model.BookType;
import com.util.DbUtil;
import com.util.StringUtil;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JTextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BookTypeManageInterFrm extends JInternalFrame {
	
	private JTable bookTypeTable;
	private JTextArea bookTypeDescTxt;
	private DbUtil dbUtil=new DbUtil();
    private BookTypeDao bookTypeDao= new BookTypeDao();
    private JTextField s_bookTypeNameTxt;
    private JTextField idTxt;
    private JTextField bookTypeNameTxt;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookTypeManageInterFrm frame = new BookTypeManageInterFrm();
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
	public BookTypeManageInterFrm() {
		setIconifiable(true);
		setClosable(true);
		setTitle("图书类别管理");
		setBounds(100, 100, 450, 538);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblNewLabel = new JLabel("图书类别名：：");
		
		s_bookTypeNameTxt = new JTextField();
		s_bookTypeNameTxt.setColumns(10);
		
		JButton btnNewButton = new JButton("查询");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bookTypeSearchActionPerformed(e);
			}
            

		
		});
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "\u64CD\u4F5C", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(24)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, Alignment.TRAILING, 0, 0, Short.MAX_VALUE)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(s_bookTypeNameTxt, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE))
							.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE)))
					.addContainerGap(47, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(20)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(s_bookTypeNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton))
					.addGap(26)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 278, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(24, Short.MAX_VALUE))
		);
		
		JLabel lblNewLabel_1 = new JLabel("编号：");
		
		idTxt = new JTextField();
		idTxt.setEditable(false);
		idTxt.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("图书类别名称：");
		
		bookTypeNameTxt = new JTextField();
		bookTypeNameTxt.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("描述：");
		
		 bookTypeDescTxt = new JTextArea();
		
		JButton btnNewButton_1 = new JButton("修改");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bookTypeUpdataActionEvent(e);
			}

			
		});
		
		JButton btnNewButton_2 = new JButton("删除");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bookTypeDeleteActionEvent(e);
			}

			
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(20)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_2)
								.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
									.addComponent(bookTypeNameTxt)
									.addComponent(idTxt, GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE))
								.addGroup(gl_panel.createSequentialGroup()
									.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
										.addComponent(btnNewButton_2, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
										.addComponent(bookTypeDescTxt, GroupLayout.PREFERRED_SIZE, 195, GroupLayout.PREFERRED_SIZE))
									.addGap(64)
									.addComponent(lblNewLabel_3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
							.addGap(261))))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(9)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(idTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2)
						.addComponent(bookTypeNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(30)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_3)
						.addComponent(bookTypeDescTxt, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton_1)
						.addComponent(btnNewButton_2))
					.addGap(34))
		);
		panel.setLayout(gl_panel);
		
		bookTypeTable = new JTable();
		//鼠标点击事件
		bookTypeTable.addMouseListener(new MouseAdapter() {//鼠标点击事件
			@Override
			public void mousePressed(MouseEvent e) {
				bookTypeTableMousePressed(e);
			}
		});
		bookTypeTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u7F16\u53F7", "\u56FE\u4E66\u7C7B\u522B\u540D\u79F0", "\u56FE\u4E66\u7C7B\u522B\u63CF\u8FF0"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		bookTypeTable.getColumnModel().getColumn(0).setPreferredWidth(40);
		bookTypeTable.getColumnModel().getColumn(1).setPreferredWidth(87);
		bookTypeTable.getColumnModel().getColumn(2).setPreferredWidth(99);
		scrollPane.setViewportView(bookTypeTable);
		getContentPane().setLayout(groupLayout);
		this.fillTable(new BookType());

	}
	/**
	 * 图书类别删除
	 * @param e
	 */
	private void bookTypeDeleteActionEvent(ActionEvent evt) {
		// TODO Auto-generated method stub
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
				int deleteNum=bookTypeDao.delete(con,id);
				if(deleteNum==1) {
					JOptionPane.showMessageDialog(null, "删除成功");
					this.resetValue();
					this.fillTable(new BookType());
				}
				else{
					JOptionPane.showMessageDialog(null, "删除失败");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "该类别下有图书，删除失败");
			
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
	 * 图书类别修改
	 * @param evt
	 */
	private void bookTypeUpdataActionEvent(ActionEvent evt) {
		// TODO Auto-generated method stub
		String id=idTxt.getText();
		String bookTypeName=bookTypeNameTxt.getText();
		String bookTypeDesc=bookTypeDescTxt.getText();
		if(StringUtil.isEmpty(id)) {
			JOptionPane.showMessageDialog(null, "请选择要修改的记录");
			return;
		}
		if(StringUtil.isEmpty(bookTypeName)) {
			JOptionPane.showMessageDialog(null, "请输入图书类型名称");
			return;
		}
		BookType bookType=new BookType(Integer.parseInt(id), bookTypeName, bookTypeDesc);
		Connection con=null;
		try {
			con=dbUtil.getCon();
			int modifyNum=bookTypeDao.update(con, bookType);
			if(modifyNum==1) {
				JOptionPane.showMessageDialog(null, "修改成功");
				this.resetValue();
				this.fillTable(new BookType());
			}else {
				JOptionPane.showMessageDialog(null, "修改失败");
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
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
	 * 点击事件
	 * @param evt
	 */
	private void bookTypeTableMousePressed (MouseEvent evt) {
		int row=bookTypeTable.getSelectedRow();
		
		idTxt.setText((String)bookTypeTable.getValueAt(row, 0));
		bookTypeNameTxt.setText((String)bookTypeTable.getValueAt(row, 1));
		bookTypeDescTxt.setText((String)bookTypeTable.getValueAt(row, 2));
		
		
		
	}
	//图书类别查询事件
    private void bookTypeSearchActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		String s_bookTypeName=this.s_bookTypeNameTxt.getText();
		BookType bookType=new BookType();
		bookType.setBookTypeName(s_bookTypeName);
		this.fillTable(bookType);
		
	}
	
	/**
	 * 初始化表格
	 *
	 * @param booktype
	 */
	private void fillTable(BookType booktype) {
		DefaultTableModel dtm=(DefaultTableModel)bookTypeTable.getModel(); //初始化默认表格
        
		dtm.setRowCount(0); //设置表格行为0
		Connection con= null;//设置连接
		try {
			con=dbUtil.getCon();//建立连接
			ResultSet rs=bookTypeDao.list(con,booktype); //查询发挥结果集
			while(rs.next()) {//遍历结果集
				Vector v=new Vector();//创建一个向量
				v.add(rs.getString("id"));//向量添加数据
				v.add(rs.getString("bookTypeName"));//向量添加数据
				v.add(rs.getString("bookTypeDesc"));//向量添加数据
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
	/**
	 * 重置表单
	 */

	private void resetValue(){
		idTxt.setText("");
		bookTypeNameTxt.setText("");
		bookTypeDescTxt.setText("");
	}
}
