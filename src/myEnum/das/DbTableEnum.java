package myEnum.das;
/**
 * 这个enum关联数据库中表名
 * 理论上：数据库中的表名、字段名在设计好后不能更改，
 * 该类的作用是将表面和字段名集中，方便修改或者在其他地方进行sql语句处理时名字写错造成不必要的麻烦
 * @author yangdang
 *
 */
public enum DbTableEnum {
	COM_DEV("com_dev"),
	FACE_DEV_PARAMETER("face_dev_parameter"),
	FACE_DEV_RECORD("face_dev_record"),
	PERSON_INFO("pearson_info"),
	FACE_PHOTO("face_photo"),
	FACE_FEATURE("face_feature"),
	FACE_DEV_AUTHOR_SET("face_dev_author_set"),
	SYSTEM_PATAMETER("system_parameter");
	
	private String tableName = null;
	private DbTableEnum(String tableName) {
		this.tableName = tableName;
	}
	public String getTableName() {
		return tableName;
	}
	
}
