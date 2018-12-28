package cn.minsin.excel;

import java.util.Map;

import cn.minsin.core.rule.ModelRule;

public class ExcelRowModel extends ModelRule{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7096662531755822709L;

	private int rowIndex;
	
	private Map<Integer, Object> cells;

	public int getRowIndex() {
		return rowIndex;
	}

	public void setRowIndex(int rowIndex) {
		this.rowIndex = rowIndex;
	}

	public Map<Integer, Object> getCells() {
		return cells;
	}

	public void setCells(Map<Integer, Object> cells) {
		this.cells = cells;
	}
	
	

}
