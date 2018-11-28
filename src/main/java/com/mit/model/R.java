package com.mit.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
public class R {
	private int s;
	private Object d;
	//q
	public R() {
		super();
	}
	public R(int s) {
		super();
		this.s = s;
	}
	public R(Object d) {
		super();
		this.d = d;
	}
	public R(int s, Object d) {
		super();
		this.s = s;
		this.d = d;
	}
	public int getS() {
		return s;
	}
	public void setS(int s) {
		this.s = s;
	}
	public Object getD() {
		return d;
	}
	public void setD(Object d) {
		this.d = d;
	}
}
