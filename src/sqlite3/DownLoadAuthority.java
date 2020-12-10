package sqlite3;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import myGson.das.DownloadAuthorityDataGson;

public class DownLoadAuthority extends DataBaseExecute{
	
	public DownLoadAuthority() {
		super();
	}
	
	
	public  synchronized int getFeatrueCount(Boolean isSelect, String macAddr) {
		ResultSet rs = null;
		int count = 0;
		String sql = "SELECT " + 
				"	count( face_dev_author_set.id )  " + 
				"FROM " + 
				"	face_dev_author_set " + 
				"	INNER JOIN com_dev ON com_dev.dev_id = face_dev_author_set.dev_id " + 
				"	LEFT JOIN face_feature ON face_feature.person_id = face_dev_author_set.person_id " + 
				"	LEFT JOIN face_photo ON face_photo.person_id = face_dev_author_set.person_id  " + 
				"	LEFT JOIN face_dev_parameter on face_dev_parameter.dev_id = com_dev.dev_id " + 
				"WHERE " + 
				"	face_photo.photo_url IS NOT NULL  " + 
				"	AND face_feature.feature_type = face_dev_parameter.feature_type  " + 
				"	AND com_dev.dev_mac_address = '"+macAddr+"' ";
		if(isSelect) {
			sql += " and face_dev_author_set.down_loaded = 0 ;";
		}else {
			sql += ";";
		} 
//		System.out.println(sql);
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			count = rs.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
		}finally {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return count;
	}
	
//	public  synchronized int getPhotoUrlCount() {
//		ResultSet rs = null;
//		int count = 0;
//		String sql = "select count(face_photo.person_id) from face_photo, pearson_info, face_feature "
//				+ "where face_photo.person_id = pearson_info.Person_id "
//				+ "and face_feature.person_id = pearson_info.Person_id "
//				+ "and face_photo.photo_url is not null "
//				+ "and face_feature.feature is null "
//				+ "and pearson_info.unique_code is not null ;";
////		System.out.println(sql);
//		try {
//			ps = conn.prepareStatement(sql);
//			
//			rs = ps.executeQuery();
//			count = rs.getInt(1);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}finally {
//			try {
//				rs.close();
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		return count;
//	}
	
	/**
	 * 分页查询，获取人员信息
	 * @param selectCount 一次查询selectCount 行
	 * @param start 取start后面的数据
	 * @return list[0] =person_id  list[1] = photo_url list[2] = unique_code
	 */
	public  synchronized List<List<String>> listDownloadFeature(String macAddr) {
		String sql = "SELECT " + 
				"	face_photo.person_id, " + 
				"	face_photo.photo_url, " + 
				"	pearson_info.unique_code  " + 
				"FROM " + 
				"	face_photo " + 
				"	LEFT JOIN pearson_info on pearson_info.person_id = face_photo.person_id  " + 
				"	LEFT JOIN face_feature ON face_feature.person_id  = face_photo.person_id  " + 
				"	LEFT JOIN face_dev_parameter ON face_dev_parameter.feature_type = face_feature.feature_type " + 
				"	LEFT JOIN com_dev ON com_dev.dev_id = face_dev_parameter.dev_id	 " + 
				"WHERE " + 
				"	face_photo.photo_url IS NOT NULL  " + 
				"	AND face_feature.feature IS NULL"+
				"	AND com_dev.dev_mac_address = '"+macAddr+"' "+ 
				"	ORDER BY face_photo.person_id LIMIT 100;";
		List<List<String>> photoInfo = null;
		List<String> list = null;
		ResultSet rs = null ;
//		System.out.println(sql);
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			photoInfo = new ArrayList<List<String>>();
			while(rs.next()) {
				list = new ArrayList<String>();
				list.add(rs.getString("person_id"));
				list.add(rs.getString("photo_url"));
				list.add(rs.getString("unique_code"));
				photoInfo.add(list);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
		}finally {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return photoInfo;
	}
	
	/**
	 * 分页查询，获取人员信息
	 * @param selectCount 一次查询selectCount 行
	 * @param type type 0:含photo_url、 无feature  1 ：含photo_url、feature 、2:含PhotoBase64、 3：含PhotoBase64，feature 、5： is_legal = N 删除权限
	 * @param devID
	 * @return List<DownloadAuthorityDataGson> 
	 */
	public  synchronized List<DownloadAuthorityDataGson> listDownloadAuthorityDataGson(int selectCount, DownLoadAuthorityType type, String devID) {
		//offset代表从第几条记录“之后“开始查询，limit表明查询多少条结果
		String sql = getDownSQl(type,  devID) + " LIMIT " + selectCount + ";";
//		System.out.println("sql = " + sql);
		List<DownloadAuthorityDataGson> list = new ArrayList<DownloadAuthorityDataGson>();
		DownloadAuthorityDataGson pi = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				pi = new DownloadAuthorityDataGson();
				pi.setUniqueCode(rs.getString("unique_code"));
				pi.setPersonType(rs.getInt("person_type"));
				pi.setPersonNo(rs.getString("person_no"));
				pi.setPersonName(rs.getString("person_name"));
				pi.setGender(rs.getInt("gender"));
				pi.setDptName(rs.getString("dpt_name"));
				pi.setIDType(Integer.toString(rs.getInt("id_type")));
				pi.setIDNo(rs.getString("Id_no"));
				pi.setCardNo(rs.getString("card_no"));
				
				if(type == DownLoadAuthorityType.PHOTOURL_ISLEGAL_N) {
					pi.setIsLegal("N");
				}else {
					pi.setIsLegal(rs.getString("is_legal"));
				}
				
				pi.setFaceStartUseTime(rs.getString("face_start_use_time"));
				pi.setFaceStopUseTime(rs.getString("face_stop_use_time"));
				pi.setCardStartUseTime(rs.getString("card_start_use_time"));
				pi.setCardEndUseTime(rs.getString("card_end_use_time"));
				pi.setPhoto(rs.getString("photo_url"));
				pi.setCharacter(rs.getString("feature"));
				list.add(pi);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
		}finally {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}
	
	/**
	 * 分页查询sql语句前半部分，无分页语句
	 * @param selectCount 
	 * @param start
	 * @param type 0:含photo_url、 无feature(空)  1 ：舍弃、2:含PhotoBase64、 3：含PhotoBase64，feature, 4:含photo_url、feature,down_loaded = 0;
	 * @return
	 */
	private  String getDownSQl(DownLoadAuthorityType type , String devID) {
		String sqlString = "SELECT " + 
				"	pearson_info.unique_code, " + 
				"	pearson_info.person_type, " + 
				"	pearson_info.person_no, " + 
				"	pearson_info.person_name, " + 
				"	pearson_info.gender, " + 
				"	pearson_info.dpt_name, " + 
				"	pearson_info.id_type, " + 
				"	pearson_info.Id_no, " + 
				"	pearson_info.card_no, " + 
				"	pearson_info.is_legal, " + 
				"	pearson_info.face_start_use_time, " + 
				"	pearson_info.face_stop_use_time, " + 
				"	pearson_info.card_start_use_time, " + 
				"	pearson_info.card_end_use_time, " + 
				"	face_photo.photo_url, " + 
				"	face_feature.feature " + 
				"FROM " + 
				"	pearson_info " + 
				"	LEFT JOIN face_photo on pearson_info.person_id = face_photo.person_id " + 
				"	LEFT JOIN face_dev_author_set on pearson_info.person_id = face_dev_author_set.person_id " + 
				"	LEFT JOIN face_feature on pearson_info.person_id = face_feature.person_id " + 
				"	LEFT JOIN face_dev_parameter on face_dev_author_set.dev_id = face_dev_parameter.dev_id ";
		switch(type) {
		case PHOTOURL:
			sqlString += "	where face_dev_parameter.dev_id = '"+devID+"' ";
			break;

		case PHOTOURL_AND_FEATURE:
			sqlString += "	where face_dev_parameter.dev_id = '"+devID+"' "
					+ "and face_feature.feature is not null ";
			break;
			
		default:
			sqlString += "	where face_dev_parameter.dev_id = '"+devID+"' ";
			break;
		}
		sqlString += " and face_dev_author_set.down_loaded = 0 "
				+ "AND face_dev_parameter.feature_type = face_feature.feature_type "
				+ " ORDER BY pearson_info.Person_id ";
		return sqlString;
	}
	
//	private  String getResultSetUrl(DownLoadAuthorityType type) {
//		String st = null;
//		switch (type) {
//		case PHOTOURL_FEATURE:
//			st = "photo_url";
//			break;
//			
//		case PHOTOURL_NO_FEATURE:
//			st = "photo_url";
//			break;
//			
//		case PHOTOBASE64_FEATURE:
//			st = "PhotoBase64";
//			break;
//			
//		case PHOTOBASE64_ISNULL:
//			st = "PhotoBase64";
//			break;
//			
//		case PHOTOURL_ISLEGAL_N:
//			st = "photo_url";
//			break;
//		default:
//			break;
//		}
//		return st;
//	}
	
}
