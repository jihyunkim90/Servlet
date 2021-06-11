package com.jdbc.web.entity.content;

import java.util.List;

public class EtcList {
	private int count;
	private List<notice>list;
	public int getCount() {
		return count;
	}
	
	
	public EtcList(int count, List<notice> list) {
	
		this.count = count;
		this.list = list;
	}


	public void setCount(int count) {
		this.count = count;
	}
	public List<notice> getList() {
		return list;
	}
	public void setList(List<notice> list) {
		this.list = list;
	}
	 

	
}
