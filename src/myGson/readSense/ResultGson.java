package myGson.readSense;

import java.util.List;

public class ResultGson {
	private String ts = "0";
	private List<RegisterGson> updated_data_device;
//	private List<RegisterGson> updated_data_service;
	private List<RegisterGson> deleted_data;
	public String getTs() {
		return ts;
	}
	public void setTs(String ts) {
		this.ts = ts;
	}
	public List<RegisterGson> getUpdated_data_device() {
		return updated_data_device;
	}
	public void setUpdated_data_device(List<RegisterGson> updated_data_device) {
		this.updated_data_device = updated_data_device;
	}
	public List<RegisterGson> getDeleted_data() {
		return deleted_data;
	}
	public void setDeleted_data(List<RegisterGson> deleted_data) {
		this.deleted_data = deleted_data;
	}
	
	
}
