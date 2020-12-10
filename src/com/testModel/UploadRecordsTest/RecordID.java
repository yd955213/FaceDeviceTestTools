package com.testModel.UploadRecordsTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecordID {
	private List<String> macAddrList = null;
	private int recordID = 0;
	private Map<String, Integer> recordID_info_map = null;
	public  RecordID(List<String> macAddrList) {
		this.macAddrList = macAddrList;
		recordID_info_map = new HashMap<String, Integer>();
		for (String macAddr : macAddrList) {
			recordID_info_map.put(macAddr, 0);
		}
	}
	
	public int getRecordId(String macAddr) {
		int id = recordID;
		if (macAddrList.indexOf(macAddr) > 0) {
			id = recordID_info_map.get(macAddr) + 1;
			recordID_info_map.put(macAddr, id);
		}
		return id;
	}
}
