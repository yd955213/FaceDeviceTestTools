package tools;

import java.io.File;

public class LogsWriter {
	private static WriteFiles wf = null;
	private static String filePath = null;
	public static final String GETCHARACTERLOG = "GETCHARACTERLOG";
	public static final String GETHEARTBEAT = "GETHEARTBEAT";
	public static final String GETONLINEPAYDATA = "GETGETONLINEPAYDATA";
	public static final String GETOFFLINEPAYDATA = "GETOFFLINEPAYDATA";
	public static final String COMPARE_AUTHORITY = "COMPARE_AUTHORITY";
	public static final String COMPARE_AUTHORITY_ERR = "COMPARE_AUTHORITY_ERR";
	public static final String UPLOAD_RECORDS = "UPLOAD_RECORDS";
	public static final String DOWN_LOAD_AUTHORITY_ERR = "DOWN_LOAD_AUTHORITY_ERR";
	public static final String UPLOAD_RECORDS_ERR = "UPLOAD_RECORDS_ERR";
	public static final String DOWN_LOAD_AUTHORITY_DATA = "DOWN_LOAD_AUTHORITY_DATA";
	public static final String DOWN_LOAD_AUTHORITY_DATA_ERR = "DOWN_LOAD_AUTHORITY_DATA_ERR";
	public static final String All_LOGS = "All_LOGS";
	public static final String RECOGNITION_RATE_TEST = "RECOGNITION_RATE_TEST";
	public static final String PTHOTO_STORAGE_ERR = "PTHOTO_STORAGE_ERR";
	public static final String FACTORY_TEST = "FACTORY_TEST";
	
	public LogsWriter () {
		
	}
	
	/**
	 * 写处理日志用 或者失败日志
	 * 1、msg + "\r\n";
	 * 2、"[" + SystemTimes.getSysTime() + "]" msg + "\r\n";
	 * @param logType
	 * @param msg
	 */
	public static void write(String logType, String msg) {
		wf = new WriteFiles();
		filePath = getFilePath(logType);
		msg = msg + "\r\n";
		wf.writeFiles(filePath, msg);
	}
	
	
	/**
	 * 写处理日志用 或者失败日志
	 *  "[" + SystemTimes.getSysTime() + "]" + "  " + msg + "\r\n" + "\r\n";
	 * @param logType
	 * @param msg
	 */
	public static void writeInfo(String logType, String msg) {
		wf = new WriteFiles();
		filePath = getFilePath(logType);
		msg = "[" + SystemTimes.getSysTime() + "]" + "  " + msg + "\r\n" + "\r\n";
		wf.writeFiles(filePath, msg);
	}
	
	/**
	 * 写接口日志用 拼接 url+msg
	 * url + "[" + SystemTimes.getSysTime() + "]" + "  " + msg + "\r\n" + "\r\n";
	 * @param logType
	 * @param url
	 * @param msg
	 */
	public static String writeUrlInfo(String logType, String url, String msg) {
		wf = new WriteFiles();
		filePath = getFilePath(logType);
		if( null == url || url.length() < 1) {
			url = "";
		}else {
			url = "[" + SystemTimes.getSysTime() + "]" + "  " + url + "\r\n";
		}
		msg =  url + "[" + SystemTimes.getSysTime() + "]" + "  " + msg + "\r\n" + "\r\n";
		
		wf.writeFiles(filePath, msg);
		return msg;
	}
	
	/**
	 * 压测数据
	 *  msg + "***" + "\r\n";
	 * @param logType
	 * @param msg
	 */
	public static void writeInfo_1(String logType, String msg) {
		wf = new WriteFiles();
		filePath = getFilePath(logType);
		msg = msg + "***" + "\r\n";
		wf.writeFiles(filePath, msg);
	}
	
	/**
	 * 打印一般数据
	 * "[" + SystemTimes.getSysTime() + "]" + msg + "\r\n";
	 * @param logType
	 * @param msg
	 */
	public static void writeInfo_logs(String logType, String msg) {
		wf = new WriteFiles();
		filePath = getFilePath(logType);
		msg ="[" + SystemTimes.getSysTime() + "]" + msg + "\r\n";
		wf.writeFiles(filePath, msg);
	}
	
	public static void isExistsToDelect(String logType) {
		File file = new File(getFilePath(logType));
		if (file.exists()) {
			file.delete();
		}
	}
	
	private static String getFilePath(String logType) {
		String filePath = null;
		switch (logType) {
		case All_LOGS:
			filePath = "./logs/All_Logs" + SystemTimes.getSysTimeYYYYMMDD() + ".txt";
			break;
			
		case GETCHARACTERLOG:
			filePath = "./logs/TestGetCharacterLog" + SystemTimes.getSysTimeYYYYMMDD() + ".txt";
			break;

		case GETHEARTBEAT:
			filePath = "./logs/TestHeartBeatLog" + SystemTimes.getSysTimeYYYYMMDD() + ".txt";
			break;

		case GETONLINEPAYDATA:
			filePath = "./OnlinePayData.txt";
			break;

		case GETOFFLINEPAYDATA:
			filePath = "./OfflinePayData.txt";
			break;
			
		case COMPARE_AUTHORITY:
			filePath = "./logs/权限对比结果" + SystemTimes.getSysTimeYYYYMMDD() + ".txt";
			break;
			
		case COMPARE_AUTHORITY_ERR:
			filePath = "./权限对比结果错误日志" + SystemTimes.getSysTimeYYYYMMDD() + ".txt";
			break;
			
		case UPLOAD_RECORDS:
			filePath = "./logs/模拟记录上报测试日志" + SystemTimes.getSysTimeYYYYMMDD() + ".txt";
			break;
			
		case UPLOAD_RECORDS_ERR:
			filePath = "./logs/模拟记录上报错误日志" + SystemTimes.getSysTimeYYYYMMDD() + ".txt";
			break;
			
		case DOWN_LOAD_AUTHORITY_ERR:
			filePath = "./logs/权限下载错误日志" + SystemTimes.getSysTimeYYYYMMDD() + ".txt";
			break;
			
		case DOWN_LOAD_AUTHORITY_DATA:
			filePath = "./logs/模拟权限下载测试日志" + SystemTimes.getSysTimeYYYYMMDD() + ".txt";
			break;
			
		case DOWN_LOAD_AUTHORITY_DATA_ERR:
			filePath = "./logs/模拟权限下载错误日志" + SystemTimes.getSysTimeYYYYMMDD() + ".txt";
			break;
			
		case RECOGNITION_RATE_TEST:
			filePath = "./logs/recognitionRateLogs" + SystemTimes.getSysTimeYYYYMMDD() + ".txt";
			break;
			
		case PTHOTO_STORAGE_ERR:
			filePath = "./logs/photoStorageErrLogs" + SystemTimes.getSysTimeYYYYMMDD() + ".txt";
			break;
			
		case FACTORY_TEST:
			filePath = "./logs/343、354厂测日志" + SystemTimes.getSysTimeYYYYMMDD() + ".txt";
			break;
			
			
		default:
			break;
		}
		return filePath;
	}
}
