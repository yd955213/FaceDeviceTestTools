package myGson.das;
/**
 * request、response的json都一样
 * @author yangdang
 *
 */
public class GetCharacterResponseGson {
	private String UniqueCode;
	private String Character;
	public String getUniqueCode() {
		return UniqueCode;
	}
	public void setUniqueCode(String uniqueCode) {
		UniqueCode = uniqueCode;
	}
	public String getCharacter() {
		return Character;
	}
	public void setCharacter(String character) {
		Character = character;
	}
	
}
