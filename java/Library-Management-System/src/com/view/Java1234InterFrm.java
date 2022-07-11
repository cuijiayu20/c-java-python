package com.view;


import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;

public class Java1234InterFrm extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Java1234InterFrm frame = new Java1234InterFrm();
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
	public Java1234InterFrm() {
		getContentPane().setBackground(Color.CYAN);
		setIconifiable(true);
		setClosable(true);
		setTitle("关于我们");
		setBounds(100, 100, 450, 300);
		
		JLabel lblNewLabel = new JLabel("学生组：崔家瑜 刘世琦");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 20));
		
		JLabel lblNewLabel_1 = new JLabel("组号：03");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 20));
		
		JLabel lblNewLabel_2 = new JLabel("作品：图书管理系统");
		lblNewLabel_2.setFont(new Font("宋体", Font.PLAIN, 20));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(150)
							.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(97)
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 222, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(110)
							.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 196, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(119, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(43)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
					.addGap(31)
					.addComponent(lblNewLabel_2)
					.addContainerGap(62, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);

	}
}
