package tools;

public class MyIdName {
	public static String getMyIdName (int idType) {
		//--证件类型 1:身份证 2:港澳通行证 3:驾驶证 4:护照 99:其他
		String idName = "其他";
		switch (idType) {
		case 1:
			idName = "身份证";
			break;
			
		case 2:
			idName = "港澳通行证";
			break;
			
		case 3:
			idName = "驾驶证";
			break;
			
		case 99:
			idName = "其他";
			break;
			
		default:
			break;
		}
		return idName;
	}
}
