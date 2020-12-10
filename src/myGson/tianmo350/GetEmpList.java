package myGson.tianmo350;

public class GetEmpList {
	public String deviceCode;
	/**页数,控制page*num偏移,从0开始*/
	public String page;
	/**设定单页查询数量，超出 1000以 1000 最大返回（包括 35.9版及以下暂不支持）*/
	public String num;
	public String getDeviceCode() {
		return deviceCode;
	}
	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	
	
}
