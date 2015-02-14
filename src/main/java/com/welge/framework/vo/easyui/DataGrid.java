package com.welge.framework.vo.easyui;

import java.util.Collection;

public class DataGrid {
	private Collection<?> rows;
	private Long totals;
	public Long getTotals() {
		return totals;
	}
	public void setTotals(Long totals) {
		this.totals = totals;
	}
	public Collection<?> getRows() {
		return rows;
	}
	public void setRows(Collection<?> rows) {
		this.rows = rows;
	}
}
