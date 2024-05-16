package beeNb.dao;
import java.sql.*;
import java.util.*;

public class ComplainDAO {
	// 설명 : complainlist 보여주기
	// 호출 : customerComplainList.jsp
	// return : ArrayList<HashMap<String,Object>>
	public static ArrayList<HashMap<String,Object>> selectComplainList(String customerId) throws Exception {
		ArrayList<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();
		Connection conn = DBHelper.getConnection();
		String sql = "SELECT  b.booking_no bookingNo, c.complain_no complainNo, c.complain_type complainType, "
				+ "c.complain_content complainContent, c.complain_state complainState, c.complain_answer complainAnswer, "
				+ "c.create_date createDate, c.update_date updateDate "
				+ "FROM complain c INNER JOIN booking b "
				+ "ON c.booking_no = b.booking_no "
				+ "WHERE b.customer_id = ? "
				+ "ORDER BY b.create_date";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1,customerId);
		// 디버깅코드
		System.out.println("stmt:"+stmt);
		
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()){
			HashMap<String,Object> m = new HashMap<String,Object>();
			m.put("bookingNo", rs.getInt("bookingNo"));
			m.put("complainNo", rs.getInt("complainNo"));
			m.put("complainType", rs.getString("complainType"));
			m.put("complainContent", rs.getString("complainContent"));
			m.put("complainState", rs.getString("complainState"));
			m.put("complainAnswer", rs.getString("complainAnswer"));
			m.put("createDate", rs.getString("createDate"));
			m.put("updateDate", rs.getString("updateDate"));
			list.add(m);
		}
		
		conn.close();
		return list;
	}
	// 설명 : complain 상세보기 
	// 호출 : customerComplainOne.jsp
	// return : <HashMap<String,Object>>
	public static HashMap<String, Object> selectComplainOne(String complainNo) throws Exception {
		HashMap<String, Object> map = null;
		Connection conn = DBHelper.getConnection();
		String sql = "SELECT  b.booking_no bookingNo, c.complain_no complainNo, c.complain_type complainType, "
				+ "c.complain_content complainContent, c.complain_state complainState, c.complain_answer complainAnswer, "
				+ "c.create_date createDate, c.update_date updateDate FROM complain c INNER JOIN booking b "
				+ "ON c.booking_no = b.booking_no "
				+ "WHERE c.complain_no = ? ";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1,complainNo );
		// 디버깅코드
		System.out.println("stmt:"+stmt);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			map = new HashMap<String, Object>();
			map.put("complainNo", rs.getInt("complainNo"));
			map.put("bookingNo", rs.getInt("bookingNo"));
			map.put("complainType", rs.getString("complainType"));
			map.put("complainState", rs.getString("complainState"));
			map.put("complainAnswer", rs.getString("complainAnswer"));
			map.put("createDate", rs.getString("createDate"));
			map.put("updateDate", rs.getString("updateDate"));
			
		}
		
		conn.close();
		return map;
	}
	
}
