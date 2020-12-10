package myGson.das;
/**
 * 接口：GetAllPerson 的response使用
 * 接口DeletePerson 的requset使用 
 * 成功返回打他= null
 * 失败识别返回：{
    "Code":"1",
    "Msg":"删除失败，返回没有删除成功的UniqueCode",
    "TimeStamp":"2020-04-07 09:48:03",
    "Data":["92350231","92350231","92350231","92350231","92350231"]
}
 * @author yangdang
 *
 */
public class GetAllPersonResponseGson {
	private String[] data;
	/**人员唯一标识（UniqueCode）数组*/
	public String[] getData() {
		return data;
	}
	/**人员唯一标识（UniqueCode）数组*/
	public void setData(String[] data) {
		this.data = data;
	}
	
}
