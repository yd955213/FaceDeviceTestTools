package sqlite3;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;

import view.update.UpdateTable;

public class QueryTable extends DataBaseExecute{
	
	public static void updateTableRowVector(JTable table, String selectType, String data) {
		QueryTable qt = new QueryTable();
		
		new UpdateTable().updateTableFromPersonInfoList(table, qt.getTableRowVector(data, selectType));
		
	}
	
	private List<PersonInfo>  getTableRowVector(String data , String selectType) {
		data = getDbSqlString(data, selectType);
//		System.out.println(data);
		List<PersonInfo> list = new ArrayList<PersonInfo>();
		PersonInfo pi = null;
		try {
			ps = conn.prepareStatement(data);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				pi = new PersonInfo();
				pi.setPersonID(rs.getInt("person_id"));
				pi.setUniqueCode(rs.getString("unique_code"));
				pi.setPersonType(rs.getInt("person_type"));
				pi.setPersonNo(rs.getString("person_no"));
				pi.setPersonName(rs.getString("person_name"));
				pi.setGender(rs.getInt("gender"));
				pi.setDptName(rs.getString("dpt_name"));
				pi.setIDType(Integer.toString(rs.getInt("id_type")));
				pi.setIDNo(rs.getString("Id_no"));
				pi.setCardNo(rs.getString("card_no"));
				pi.setIsLegal(Integer.toString(rs.getInt("is_legal")));
				pi.setFaceStartUseTime(rs.getString("face_start_use_time"));
				pi.setFaceStopUseTime(rs.getString("face_stop_use_time"));
				pi.setCardStartUseTime(rs.getString("card_start_use_time"));
				pi.setCardEndUseTime(rs.getString("card_end_use_time"));
				
				list.add(pi);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
		}
		return list;
	}

	private String getDbSqlString(String data, String selectType) {
		// TODO Auto-generated method stub
		String sql = null;
		switch (selectType) {
		case "姓名":
			sql = "select * from pearson_info where person_name like '%" + data + "%' order by Person_id;";
			break;

		case "卡号":
			sql = "select * from pearson_info where card_no = '" + data + "' order by Person_id;";
			break;
			
		case "ID":
			sql = "select * from pearson_info where person_id = " + data + " order by Person_id;";
			break;
		default:
			break;
		}
		return sql;
	}
}	
