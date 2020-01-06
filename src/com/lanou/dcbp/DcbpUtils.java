package com.lanou.dcbp;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.omg.CosNaming.IstringHelper;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DcbpUtils {
    
	private static DataSource ds = new ComboPooledDataSource("mysql");
	
	public static Connection getConnection1() throws Exception {
		return ds.getConnection();
	}
	
	//通用的增删改
	
	public static int update(String sql , Object...parameters) throws Exception {
		try(Connection con = ds.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);){
			for(int i =0;i<parameters.length;i++) {
				ps.setObject(i+1, parameters[i]);
			}
			return ps.executeUpdate();
		}
		
	}
	
	//通用的增删改(适用于事务)
	
	public static int update(Connection con, String sql , Object...parameters) throws Exception {
		try(PreparedStatement ps = con.prepareStatement(sql);){
			for(int i =0;i<parameters.length;i++) {
				ps.setObject(i+1, parameters[i]);
			}
			
			return ps.executeUpdate();
		}
		
	}
	
	//通用的查询
	public static <T> List<T> query(String sql,Class<T>class1, Object...parameters) throws Exception {
		List<T> list = new ArrayList<>();
		Map<String, String> map = getField(class1);
		 Connection con = ds.getConnection();
		 PreparedStatement ps = con.prepareStatement(sql);
		 for(int i=0;i<parameters.length;i++) {
			 ps.setObject(i+1, parameters[i]);
		 }
		   ResultSet rs  =  ps.executeQuery();
		    ResultSetMetaData  rsmd  =  rs.getMetaData();
		        int columncount  =  rsmd.getColumnCount();
		      while(rs.next()) {
		    	  T t = class1.newInstance();
		    	 for(int i=1;i<columncount;i++) {
		    		 String columnName = rsmd.getColumnLabel(i);
		    		 String fieldName = map.get(columnName);
		    		 if( fieldName!=null) {
		    			 Field field = class1.getDeclaredField(fieldName);
		    			 field.setAccessible(true);
		    			 field.set(t, rs.getObject(i));
		    		 }
		    	 } 
		    	  list.add(t);
		      }  
		 return list;
	}
	
	
	//通用的查询(适用与事务)
		public static <T> List<T> query( Connection con ,String sql,Class<T>class1, Object...parameters) throws Exception {
			List<T> list = new ArrayList<>();
			Map<String, String> map = getField(class1);
			
			 PreparedStatement ps = con.prepareStatement(sql);
			 for(int i=0;i<parameters.length;i++) {
				 ps.setObject(i+1, parameters[i]);
			 }
			   ResultSet rs  =  ps.executeQuery();
			    ResultSetMetaData  rsmd  =  rs.getMetaData();
			        int columncount  =  rsmd.getColumnCount();
			      while(rs.next()) {
			    	  T t = class1.newInstance();
			    	 for(int i=1;i<columncount;i++) {
			    		 String columnName = rsmd.getColumnLabel(i);
			    		 String fieldName = map.get(columnName);
			    		 if( fieldName!=null) {
			    			 Field field = class1.getDeclaredField(fieldName);
			    			 field.setAccessible(true);
			    			 field.set(t, rs.getObject(i));
			    		 }
			    	 } 
			    	  list.add(t);
			      }  
			 return list;
		}
		
	
	
	
	private static Map<String, String> getField(Class class1){
		Map<String, String> map = new HashMap<String, String>();
		Field[] field = class1.getDeclaredFields();
		
		for(Field f:field) {
			tablefield tf = f.getDeclaredAnnotation(tablefield.class);
			String name = f.getName();
			if(tf==null) {
				map.put(name, name);
			}else {
				map.put(tf.value(), name);
			}
		}
		return map;
		
	}
	
	
	//查询单个信息
	
	public static <T> T queryone(String sql, Class<T> class1, Object...parameters) throws Exception {
		List<T> list =query(sql, class1, parameters);
		return list.size()==0? null:list.get(0);
		
	}
	//查询单个信息,(适用于事务)
	public static <T> T queryone( Connection con ,String sql, Class<T> class1, Object...parameters) throws Exception {
		List<T> list =query(sql, class1, parameters);
		return list.size()==0? null:list.get(0);
		
	}
	
}
