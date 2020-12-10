package sqlite3;

/**
 * 当枚举类种含有PHOTOURL时，sql查询返回的照片字段为照片的路径，使用时需调用MyPhoto.getPhoteBASE64_Mime(File file)，将照片路径转化为base64;
 * @author yangdang
 *
 */
public enum DownLoadAuthorityType {
	/**
	 * SQL语句判断photo_url、feature字段不为空
	 */
	PHOTOURL_AND_FEATURE,
	/**
	 * SQL语句判断photo_url不为空、feature为空，适用于只下载权限时不带特征值的场景
	 */
	PHOTOURL,
	/**
	 * 删除权限；测试用，不修改数据库的情况下将查询到的权限的身份合法性强制为非法，
	 */
	PHOTOURL_ISLEGAL_N
}
