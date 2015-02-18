package com.welge.framework.vo.dwz;

import java.util.Collection;

public class JsonTable{
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
