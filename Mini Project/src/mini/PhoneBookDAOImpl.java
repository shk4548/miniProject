package mini;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PhoneBookDAOImpl implements PhoneBookDAO {

	private Connection getConnection() throws SQLException{
		Connection conn = null;
		try {
				
				Class.forName("oracle.jdbc.driver.OracleDriver");
				String dburl = "jdbc:oracle:thin:@localhost:1521:xe";
				conn = DriverManager.getConnection(dburl,
						"C##KIMSH",
						"1234");
			} catch (ClassNotFoundException e) {
				System.err.println("드라이버 로드 실패");
			}
			return conn;
	}

	@Override
	public List<PhoneBookVO> getList() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<PhoneBookVO> list = new ArrayList<>();
		
		try {
			conn = getConnection();
			stmt = conn.createStatement();
		
			// 쿼리
			String sql ="select id,name,hp,tel FROM PHONE_BOOK";
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				Long id = rs.getLong(1);
				String name =rs.getString(2);
				String hp = rs.getString(3);
				String tel = rs.getString(4);
				
				PhoneBookVO vo = new PhoneBookVO(id,name,hp,tel);
				
				
				list.add(vo);			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (Exception e) {
				
			}
		}
		return list;
	}

	@Override
	public List<PhoneBookVO> search(String keyword) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<PhoneBookVO> list =new ArrayList<>();
		
		String sql ="SELECT id, name, hp, tel FROM PHONE_BOOK Where name LIKE?";
	try {
		conn = getConnection();
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1,"%" + keyword + "%");
		
		rs = pstmt.executeQuery();
		
		while(rs.next()) {
			Long id = rs.getLong("id");
			String name =rs.getString("name");
			String hp  =rs.getString("hp");
			String tel = rs.getString("tel");
			
			PhoneBookVO vo = new PhoneBookVO(id,name,hp,tel);
			list.add(vo);
		}
	} catch(SQLException e) {
		e.printStackTrace();
	} finally {
		try {
			rs.close();
			pstmt.close();
			conn.close();
		} catch(Exception e) {
			
		}
	}
	return list;	
		
}

	@Override
	public PhoneBookVO get(Long id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		
		PhoneBookVO vo = null;
		
		try {
			conn =getConnection();
			String sql = "SELECT id, name, hp ,tel FROM PHONE_BOOK WHERE id?";
		pstmt =	conn.prepareStatement(sql);
		pstmt.setLong(1, id);
		
		rs = pstmt.executeQuery();
		
		if(rs.next()) {
			Long PHONE_BOOK_id = rs.getLong(1);
			String name = rs.getString(2);
			String hp =rs.getString(3);
			String tel =rs.getString(4);
			
			vo = new PhoneBookVO(PHONE_BOOK_id, name, hp, tel);
		}
	}catch (SQLException e) {
		e.printStackTrace();
	}finally {
		try {
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			
		}
	}
		
	return vo;
				
}

	@Override
	public boolean insert(PhoneBookVO vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int insertedCount = 0;
		
		try {
			conn = getConnection();
			
			String sql = "INSERT INTO PHONE_BOOK VALUES(SEQ_PHONE_BOOK_PK.NEXTVAL, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, vo.getname());
			pstmt.setString(2, vo.gethp());
			pstmt.setString(3, vo.gettel());
			
		
			insertedCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (Exception e) {
				
			}
		}
		
		return 1 == insertedCount;	//	삽입된 레코드가 1개면 성공
	}


	@Override
	public boolean delete(Long id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int deletedCount = 0;
		
		try {
			conn = getConnection();
			String sql = "DELETE FROM PHONE_BOOK WHERE id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1 , id);
			
			deletedCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (Exception e) {
				
			}
		}
		
		return 1 == deletedCount;
	}

	@Override
	public boolean update(PhoneBookVO vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int updatedCount = 0;
		
		try {
			conn = getConnection();
			//	실행 계획
			String sql = "UPDATE PHONE_BOOK SET name, hp, tel = ? WHERE author_id=? ";
			pstmt = conn.prepareStatement(sql);
			//	파라미터 바인딩
			pstmt.setString(1, vo.getname());
			pstmt.setString(2, vo.gethp());
			pstmt.setString(3, vo.gettel());
			
			pstmt.setLong(2, vo.getid());
			
			updatedCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (Exception e) {
				
			}
		}
		
		return 1 == updatedCount;
	}
}
	