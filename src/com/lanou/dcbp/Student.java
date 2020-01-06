package com.lanou.dcbp;

import java.security.KeyStore.PrivateKeyEntry;

public class Student {
	@tablefield ("id")
    private int sid;
	@tablefield ("num")
    private int snum;
	@tablefield ("name")
    private String sname;
	@tablefield ("sex")
    private String ssex;
	@tablefield ("age")
    private int sage;
	@tablefield ("professional")
    private String major;
	@tablefield ("number")
    private String phone;
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public int getSnum() {
		return snum;
	}
	public void setSnum(int snum) {
		this.snum = snum;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getSsex() {
		return ssex;
	}
	public void setSsex(String ssex) {
		this.ssex = ssex;
	}
	public int getSage() {
		return sage;
	}
	public void setSage(int sage) {
		this.sage = sage;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Student(int sid, int snum, String sname, String ssex, int sage, String major, String phone) {
		super();
		this.sid = sid;
		this.snum = snum;
		this.sname = sname;
		this.ssex = ssex;
		this.sage = sage;
		this.major = major;
		this.phone = phone;
	}
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Student [sid=" + sid + ", snum=" + snum + ", sname=" + sname + ", ssex=" + ssex + ", sage=" + sage
				+ ", major=" + major + ", phone=" + phone + "]";
	}
	
	
	
}
