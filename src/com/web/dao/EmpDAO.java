package com.web.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.web.vo.EmpDTO;
import com.web.vo.SpotDTO;



public class EmpDAO {
private Connection getConnection() throws SQLException {
		
		Connection connection = null;
		
		try {
			//1.�뱶�씪�씠踰� 濡쒕뵫
			Class.forName( "oracle.jdbc.driver.OracleDriver" );
		
			//2.而ㅻ꽖�뀡 留뚮뱾湲�(ORACLE DB)
			String dbURL  = "jdbc:oracle:thin:@localhost:1521:xe";
			connection = DriverManager.getConnection( dbURL, "final", "final" );
			
		} catch( ClassNotFoundException ex ){
			System.out.println( "�뱶�씪�씠踰� 濡쒕뵫 �떎�뙣-" + ex );
		} 
		
		return connection;
	}

	public void insert(EmpDTO dto){
		
		
		try{
			//1.connection 媛��졇�삤湲�
			Connection connection = getConnection();
			//2.PreparedStatement以�鍮�
			String sql="insert into emp values(EMP_SEQ.nextval,?,?,?,?,SYSDATE,?)";		
			PreparedStatement pstmt=connection.prepareStatement(sql);
			//3.諛붿씤�뵫
			pstmt.setString(1, dto.getPosition());
			pstmt.setInt(2, dto.getPassWord());
			pstmt.setString(3, dto.getEmpName());
			pstmt.setString(4, dto.getDept());
			pstmt.setString(5, dto.getCount());
			//4.荑쇰━ �떛�빞
			pstmt.executeUpdate();
			//5.�옄�썝�젙由�
			pstmt.close();
			connection.close();
	}catch(SQLException ex){
		System.out.println( "SQL �삤瑜�-" + ex );
			
		}
	}
	//////////////////////////////////�삤瑜�///////////////////////////
	public EmpDTO EmpSerch(String empName){
		
		EmpDTO dto = new EmpDTO();
		ResultSet rs = null;
		
		try{
			Connection connection = getConnection();
			String sql = "select * from emp where EMPNAME=?";
			PreparedStatement pstmt=connection.prepareStatement(sql);
			pstmt.setString(1,empName);
			rs=pstmt.executeQuery();
			
			if(rs.next()){
				dto.setEmpNo(rs.getInt("empNo"));
				dto.setPosition(rs.getString("position"));
				dto.setPassWord(rs.getInt("passWord"));
				dto.setDept(rs.getString("dept"));
				dto.setEmpjoin(rs.getString("empjoin"));
				dto.setCount(rs.getString("count"));
				
			}
			rs.close();
			pstmt.close();
			connection.close();
			
		}catch(Exception ex){
			System.out.println( "SQL �삤瑜�-" + ex );
		}
		return null;
	}
	public EmpDTO BestEmp(String count){
		
		EmpDTO dto = new EmpDTO();
		ResultSet rs = null;
		
		try{
			Connection connection = getConnection();
			String sql = "select *from emp where count=?";
			PreparedStatement pstmt=connection.prepareStatement(sql);
			pstmt.setString(1,count);
			rs=pstmt.executeQuery();
			
			if(rs.next()){
				dto.setEmpNo(rs.getInt("empNo"));
				dto.setPosition(rs.getString("position"));
				dto.setPassWord(rs.getInt("passWord"));
				dto.setEmpName(rs.getString("empName"));
				dto.setDept(rs.getString("dept"));
				dto.setEmpjoin(rs.getString("empjoin"));
				
				
			}
			rs.close();
			pstmt.close();
			connection.close();
		}catch(Exception ex){
			System.out.println( "SQL �삤瑜�-" + ex );
		}
		
		return dto;
	}
	public List<EmpDTO> selectAll(){
		 ArrayList<EmpDTO> list = new ArrayList<EmpDTO>();

		try{
			//1.connection 媛��졇�삤湲�
			Connection connection = getConnection();
			//2.Statement �깮�꽦
			
			Statement stmt = connection.createStatement(); 
			//3.荑쇰━ 臾� �떎�뻾
			String sql="select * from emp  ";
			ResultSet rs = stmt.executeQuery( sql );
			//4,.row 媛��졇�삤湲�
		while(rs.next()){
			 int empNo=rs.getInt(1);
			 String position=rs.getString(2);
			 int passWord =rs.getInt(3);;
			 String empName=rs.getString(4);;
			 String dept=rs.getString(5);;
			 String empjoin=rs.getString(6);;
			 String count=rs.getString(7);;
			 
			 EmpDTO dto = new EmpDTO();
			 dto.setEmpNo(empNo);
			 dto.setPosition(position);
			 dto.setPassWord(passWord);
			 dto.setEmpName(empName);
			 dto.setDept(dept);
			 dto.setEmpjoin(empjoin);
			 dto.setCount(count);
			 
			 list.add(dto);
			 
		}
		
			rs.close();
			stmt.close();
			connection.close();
		}catch(Exception ex){
				System.out.println( "SQL �삤瑜�-" + ex );
			}
		
		return list;
		}
	public void delete(EmpDTO dto){
		try{
			//1.connection 媛��졇�삤湲�
			Connection connection = getConnection();
			String sql = "delete from emp where empNo = ?";
			//2.statement �깮�꽦
			PreparedStatement pstmt = connection.prepareStatement(sql);
			//3.諛붿씤�뵫
			pstmt.setInt(1,dto.getEmpNo());
			//4.荑쇰━�떎�뻾
			pstmt.executeUpdate();
		
			//4.�옄�썝�젙由�
			pstmt.close();
			connection.close();
		}catch(Exception ex){
			System.out.println( "SQL �삤瑜�-" + ex );
		}
	}
	public void update(int empNo,String position,int passWord ,String empName,String dept){
		try{
			Connection conn =getConnection();
			String sql ="UPDATE emp set position=?,passWord=?,empName=?,dept=?,where empNo=?";
		
			PreparedStatement pstmt =conn.prepareStatement(sql);
			
			//3.諛붿씤�뵫
			pstmt.setString(1, position);
			pstmt.setInt(2, passWord);
			pstmt.setString(3, empName);
			pstmt.setString(4,dept);
			pstmt.setInt(5, empNo);
			
			pstmt.executeUpdate();
		
		
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}


