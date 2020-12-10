package myGson.das;

public class GetAuthorityDataRequestGson {
	private String UniqueCode;
	/**
	 * （需支持汉字/汉语拼音全拼/汉字首字母等方式，如:张三/zhangsan/zs）
	 */
	private String PersonName;
	/**每页查询多少条记录*/
	private int QueryCount;
	/**查询第几页数据（1表示第一页）*/
	private int Page;
	public String getUniqueCode() {
		return UniqueCode;
	}
	public void setUniqueCode(String uniqueCode) {
		UniqueCode = uniqueCode;
	}
	public String getPersonName() {
		return PersonName;
	}
	public void setPersonName(String personName) {
		PersonName = personName;
	}
	public int getQueryCount() {
		return QueryCount;
	}
	public void setQueryCount(int queryCount) {
		QueryCount = queryCount;
	}
	public int getPage() {
		return Page;
	}
	public void setPage(int page) {
		Page = page;
	}
	
}
