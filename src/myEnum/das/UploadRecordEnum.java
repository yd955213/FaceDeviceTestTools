package myEnum.das;

import java.util.HashMap;
import java.util.Map;

public enum UploadRecordEnum implements UploadRecordBehavior{ 

	DEFAULT_VALUE(null, null),
	RECOGNITION_SUCCESSFULLY("0000", "刷脸成功"),
	FACE_EXPIRATION_FAIL("0001", "人脸过期失效"),
	ALIVE_RECOGNITION_FAIL("0002", "刷脸活体验证失败"),
	DOOR_OPENING_TIMEOUT("0989", "开门超时"),
	DOOR_OPENED_SUPER_PASSWORD_ERROR("0990", "超级密码错误"),
	DOOR_OPENED_SUPER_PASSWORD_SECCUSS("0991", "超级密码开门成功"),
	DOOR_OPEN_SECCUSS("0992", "开门成功"),
	STRANGER("0993", "非法人"),
	NO_AUTHORITY("0994", "无权限"),
	AUTHORITY_TIMEOUT("0995", "过期限"),
	AUTHORITY_NOT_IN_TIME("0996", "无时限"),
	NORMALLY_CLOSED("0997", "常闭未通过"),
	INTERLOCK_UNTHROUGH_BY_CARD("0998", "互锁刷卡未通过"),
	UNDEFINED("0999", "未定义返回"),
	THROUGH_BY_CARD("2000", "刷卡成功"),
	CARD_AUTHORITY_TIMEOUT("2001", "卡片过期失效"),
	SACN_QR_CODE_SECCUSS("3000", "扫码成功"),
	QR_CODE_TIMEOUT("3001", "二维码过期失效"),
	CARD_AND_FACE_RECOGNITION_SECCUSS("4000", "卡+人脸识别成功"),
	CARD_AND_FACE_RECOGNITION_FAIL("4001", "卡+人脸过期失效"),
	CARD_AND_FACE_ALIVE_RECOGNITION_FAIL("4002", "卡+人脸活体验证失败"),
	GATE_MAGNETISM_TIMEOUT_EVENT("5000", "门磁超时事件"),
	GATE_MAGNETISM_RETURN_TO_NORNAL_EVENT("5001", "门磁超时恢复事件"),
	FORCED_ENTRY_EVENT("5002", "强行闯入事件"),
	DOOR_OPENED_SUPER_PASSWORD_SECCUSS_EVENT("5003", "超级密码开门事件"),
	DOOR_OPENED_SUPER_PASSWORD_ERROR_EVENT("5004", "超级密码错误事件"),
	REMOTE_DOOR_OPENING_EVENT("5005", "远程开门事件"),
	DOOR_OPENING_BY_PRESS_THE_KEY_EVENT("5006", "按键开门事件");
	
	private String accessResult; 
	private String accesscode; 
	
	private UploadRecordEnum(String accesscode , String accessResult) { 
		this.accesscode = accesscode; 
		this.accessResult = accessResult;
	}
	
	@Override
	public String getAccessResult() {
		// TODO Auto-generated method stub
		return this.accessResult;
	}
	@Override
	public String getAccessCode() {
		// TODO Auto-generated method stub
		return this.accesscode;
	}
	/**
	 * 获取UploadRecordEnum对应的map
	 * 当设备返回accesscode时根据其获取对应的accessResult；
	 * @author yangdang
	 *
	 */
	public static class EnumElement{
		public Map<String, String> map =null;
		private EnumElement() {
			map =new HashMap<String, String>();
			UploadRecordEnum[] element = UploadRecordEnum.values();
			for (UploadRecordEnum uploadRecordEnum : element) {
				map.put(uploadRecordEnum.getAccessCode(), uploadRecordEnum.getAccessResult());
			}
		}
		private static class EnumElementHandler{
			private static EnumElement instance = new EnumElement();
		}
		public static synchronized EnumElement getInstance() {
			return EnumElementHandler.instance;
		}
	}
}

