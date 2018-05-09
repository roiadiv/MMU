package com.hit.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.AbstractListModel;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.hit.memoryunits.Page;

public class MMUView extends java.util.Observable implements View
{

	private GUI gui;
	private int pageColNumber = 1;
	private final int pageRowNumber = 5;
	private int numberOfProcess = 0;
	private int[] selectedProcessNumbers;

	public MMUView() {
		gui = new GUI();
	}

	@Override
	public void start() {
		configPageTable();
		configProcessList();
		configPlayButtons();
		gui.getFrame().setVisible(true);
	}

	@SuppressWarnings("unchecked")
	private void configProcessList() {
		String[] valuesa = new String[numberOfProcess];
		for (int i = 0; i < valuesa.length; i++) {
			valuesa[i] = "Process " + (i+1);
		}
		gui.getList().setModel(new AbstractListModel() {
			String[] values = valuesa;
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});		
	}

	public GUI getGui() {
		return gui;
	}

	public void setPageColNumber(int number) {
		this.pageColNumber = number;
	}

	public void setNumberOfProcess(int numberOfProcess) {
		this.numberOfProcess = numberOfProcess;
	}

	public int[] getSelectedPageNumbers()
	{
		return this.selectedProcessNumbers;
	}

	private void configPageTable() {
		Object[][] content= new Object[pageRowNumber][pageColNumber];
		String[] pageId = new String[pageColNumber];
		for (int i = 0; i < pageRowNumber; i++) {
			for (int j = 0; j < pageColNumber; j++) {
				content[i][j] = 0;
			}
		}

		for (int i = 0; i < pageId.length; i++) {
			pageId[i] = "page " + (i + 1);
		}
		gui.getTable().setModel(new DefaultTableModel(content,pageId));
	}

	private void configPlayButtons()
	{
		gui.getBtnPlayAll().addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				selectedProcessNumbers = new int[numberOfProcess];
				for (int i = 0; i < selectedProcessNumbers.length; ++i)
				{
					selectedProcessNumbers[i] = i;
				}

				notifyController();
			}
		});

		gui.getBtnPlay().addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				selectedProcessNumbers = new int[gui.getList().getSelectedValuesList().size()];
				int i = 0; 
				for (Object currentSelectedValue : gui.getList().getSelectedValuesList())
				{
					selectedProcessNumbers[i] = getProcessNumberFromString((String) currentSelectedValue);
					i++;
				}

				notifyController();
			}
		});		

	}

	private int getProcessNumberFromString(String processDetail)
	{
		String[] SplittedBySpace = processDetail.split(" ");
		String[] SplittedByClosure = SplittedBySpace[1].split("]");
		return Integer.parseInt(SplittedByClosure[0]);
	}

	private void notifyController()
	{
		setChanged();
		notifyObservers(this.selectedProcessNumbers);
	}

	public void UpdateCounters(List<String> logFileAllLines)
	{
		Integer pageFaultCounter = 0;
		Integer pageReplacementCounter = 0;
		for (String currentLogFileLine : logFileAllLines)
		{
			if (currentLogFileLine.contains("PF"))
			{
				pageFaultCounter++;
			}
			else if (currentLogFileLine.contains("PR"))
			{
				pageReplacementCounter++;
			}
		}
		
		this.gui.getPageFaultTextField().setText(pageFaultCounter.toString());
		this.gui.getPageReplacementTextField().setText(pageReplacementCounter.toString());
	}
	
	public void UpdateTable(Map<Long, Page<byte[]>> ramPagesInTheEnd) 
	{
		Map<Long, Page<byte[]>> sortedMapByKey = new TreeMap<>(ramPagesInTheEnd);
		Object[][] content= new Object[pageRowNumber][pageColNumber];
		String[] pageId = new String[pageColNumber];
		int col = 0;
		
		for (java.lang.Long key : sortedMapByKey.keySet())
		{
			updateCurrentColTable(content, col, sortedMapByKey.get(key));			
			pageId[col] = "Page " + key;
			col++;
		}
		
		gui.getTable().setModel(new DefaultTableModel(content,pageId));
	}
	
	private void updateCurrentColTable(Object[][] tableContent, int currentCol, Page<byte[]> currentPage)
	{
		for (int i = 0; i < pageRowNumber; ++i)
		{
			tableContent[i][currentCol] = currentPage.getContent()[i];
		}
	}
}
