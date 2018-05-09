package com.hit.view;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.table.DefaultTableModel;
import java.awt.Dimension;
import java.awt.Point;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Component;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.ScrollPaneConstants;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class GUI
{
	private JFrame frame;
	private JList list;
	private JTable table;
	private JButton btnPlayAll;	
	private JButton btnPlay;
	private JScrollPane scrollPane;
	private JTextField pageFaultTextField;
	private JTextField pageReplacementTextField;
	private int pageColNumber = 1;

	public GUI() {
		initialize();
	}
	
	public JFrame getFrame() {
		return frame;
	}
	
	public JTable getTable() {
		return table;
	}
	
	public JList getList() {
		return list;
	}

	public JButton getBtnPlayAll() {
		return btnPlayAll;
	}

	public JButton getBtnPlay() {
		return btnPlay;
	}
	
	public JTextField getPageFaultTextField() {
		return pageFaultTextField;
	}

	public JTextField getPageReplacementTextField() {
		return pageReplacementTextField;
	}

	@SuppressWarnings("unchecked")
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setLocation(new Point(19, 3));
		frame.getContentPane().setSize(new Dimension(22, 0));
		frame.getContentPane().setPreferredSize(new Dimension(10, 0));
		frame.getContentPane().setBackground(Color.LIGHT_GRAY);
		frame.setBounds(100, 100, 779, 385);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		JPanel panel = new JPanel();
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 747, Short.MAX_VALUE))
					.addGap(28))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 182, Short.MAX_VALUE)
					.addGap(6))
		);
		
		list = new JList();
		list.setModel(new AbstractListModel() {
			String[] values = new String[] {"Process 1", "Process 2", "Process 3", "Process 4", "Process 5", "Process 6", "Process 7", "Process 8", "Process 9", "Process 10"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		
		JLabel lblProcesses = new JLabel("Processes");
		
		this.btnPlayAll = new JButton("PLAY ALL");
		
		this.btnPlay = new JButton("PLAY");
		
		JLabel lblPageFaultAmount = new JLabel("Page fault Amount");
		
		JLabel lblPageReplacementAmount = new JLabel("Page Replacement Amount");
		
		this.pageFaultTextField = new JTextField();
		this.pageFaultTextField.setColumns(10);		
		this.pageFaultTextField.setEnabled(false);
		this.pageReplacementTextField = new JTextField();
		this.pageReplacementTextField.setColumns(10);
		this.pageReplacementTextField.setEnabled(false);

		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(list, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
							.addGap(26)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(btnPlay, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnPlayAll, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE))
							.addGap(84)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblPageFaultAmount)
								.addComponent(lblPageReplacementAmount, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE))
							.addGap(28)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
								.addComponent(pageReplacementTextField, 0, 0, Short.MAX_VALUE)
								.addComponent(pageFaultTextField, GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)))
						.addComponent(lblProcesses))
					.addContainerGap(200, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblProcesses)
							.addPreferredGap(ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
							.addComponent(list))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(38)
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnPlayAll, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
									.addComponent(lblPageFaultAmount)
									.addComponent(pageFaultTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
							.addGap(18)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(pageReplacementTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
									.addComponent(btnPlay, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblPageReplacementAmount)))))
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setFillsViewportHeight(true);
		table.setDragEnabled(true);
		table.setDoubleBuffered(true);
		table.setSurrendersFocusOnKeystroke(true);
		table.setPreferredScrollableViewportSize(new Dimension(462, 400));
		table.setAutoCreateRowSorter(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		table.setColumnSelectionAllowed(true);
		table.setCellSelectionEnabled(true);
		
		
		
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0"},
				{"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0"},
				{"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0"},
				{"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0"},
				{"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0"},
			},
			new String[] {
				"Page 1", "Page 2", "Page 3", "Page 4", "Page 5", "Page 6", "Page 7", "Page 8", "Page 9", "Page 10", "Page 11", "Page 12", "Page 13", "Page 14", "Page 15", "Page 16"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(43);
		table.getColumnModel().getColumn(1).setPreferredWidth(43);
		table.getColumnModel().getColumn(2).setPreferredWidth(43);
		table.getColumnModel().getColumn(3).setPreferredWidth(43);
		table.getColumnModel().getColumn(4).setPreferredWidth(43);
		table.getColumnModel().getColumn(5).setPreferredWidth(43);
		table.getColumnModel().getColumn(6).setPreferredWidth(43);
		table.getColumnModel().getColumn(7).setPreferredWidth(43);
		table.getColumnModel().getColumn(8).setPreferredWidth(43);
		table.getColumnModel().getColumn(9).setPreferredWidth(52);
		table.getColumnModel().getColumn(10).setPreferredWidth(52);
		table.getColumnModel().getColumn(11).setPreferredWidth(52);
		table.getColumnModel().getColumn(12).setPreferredWidth(52);
		table.getColumnModel().getColumn(13).setPreferredWidth(52);
		table.getColumnModel().getColumn(14).setPreferredWidth(52);
		table.getColumnModel().getColumn(15).setPreferredWidth(52);
		frame.getContentPane().setLayout(groupLayout);
	}
	
	public void setPageColNumber(int number) {
		this.pageColNumber = number;
	}
}