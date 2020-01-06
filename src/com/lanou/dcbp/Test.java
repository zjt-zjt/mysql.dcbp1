package com.lanou.dcbp;

import java.util.List;

public class Test {
  public static void main(String[] args) throws Exception {
	    String sql = "update  stu set name = ? where num=?  ";
     DcbpUtils.update(sql,  "",37);
//	  
//	     String  sql = "select*from stu";
//	     List<Student > list = DcbpUtils.query(sql, Student.class);
//	     for(Student student:list) {
//	    	 System.out.println(student);
//	     }
	  
//	  String  sql = "select*from stu where num=37";
//	   Student student  = DcbpUtils.queryone(sql, Student.class);
//			   System.out.println(student);
}
}
