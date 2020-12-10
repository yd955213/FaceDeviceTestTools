package myGson.das;

public class ClearDeviceDataRequestGson {
	/**清空所有通行记录:Y 清空 N不清空*/
	public String ClearPassRecord;
	/**清空所有人员权限:Y 清空 N不清空*/
	public String ClearPerson;
	/**清空所有日志:Y 清空 N不清空*/
	public String ClearLog;
	
	public String getClearPassRecord() {
		return ClearPassRecord;
	}
	public void setClearPassRecord(String clearPassRecord) {
		ClearPassRecord = clearPassRecord;
	}
	public String getClearPerson() {
		return ClearPerson;
	}
	public void setClearPerson(String clearPerson) {
		ClearPerson = clearPerson;
	}
	public String getClearLog() {
		return ClearLog;
	}
	public void setClearLog(String clearLog) {
		ClearLog = clearLog;
	}
	
//	public String getSelect(JCheckBox box) {
//		String str = "N";
//		if (box.isSelected()) {
//			str = "Y";
//		}
//		return str;
//	}
}
