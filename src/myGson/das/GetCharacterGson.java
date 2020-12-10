package myGson.das;
/**
 * request、response的json都一样
 * @author yangdang
 *
 */
public class GetCharacterGson {
	private String UniqueCode;
	private String Photo;
	public String getUniqueCode() {
		return UniqueCode;
	}
	public void setUniqueCode(String uniqueCode) {
		UniqueCode = uniqueCode;
	}
	public String getPhoto() {
		return Photo;
	}
	public void setPhoto(String photo) {
		Photo = photo;
	}
	
}
