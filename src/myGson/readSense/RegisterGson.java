package myGson.readSense;

public class RegisterGson {
//	private String terminal_id;
	private String name;
	private String person_id;
	/**
	 * face，face_image两者只能带一个
	 * 后台有特征值时，使用face，face_image为空
	 * 后台有特征值时，使用face为空，face_image为照片下载地址：http://{host:port}/xxxx/xxx.jpg
	 */
	private String face;
	/**
	 *  face，face_image两者只能带一个
	 * 后台有特征值时，使用face，face_image为空
	 * 后台有特征值时，使用face为空，face_image为照片下载地址：http://{host:port}/xxxx/xxx.jpg
	 */
	private String face_image;
	private int type = 0;
	private String password;
	private String card_id;
	private String time;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFace() {
		return face;
	}
	public void setFace(String face) {
		this.face = face;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCard_id() {
		return card_id;
	}
	public void setCard_id(String card_id) {
		this.card_id = card_id;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getPerson_id() {
		return person_id;
	}
	public void setPerson_id(String person_id) {
		this.person_id = person_id;
	}
	public String getFace_image() {
		return face_image;
	}
	public void setFace_image(String face_image) {
		this.face_image = face_image;
	}
	
	
}
