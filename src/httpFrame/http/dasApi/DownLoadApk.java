package httpFrame.http.dasApi;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import com.sun.net.httpserver.Headers;
import httpFrame.http.HttpCmd;
import httpFrame.http.readSense.UpdateLocalDatabase;
import myGson.das.ResponseExGson;
import myGson.das.ResponseGson;

public class DownLoadApk extends HttpCmd {//http接口需要继承 HttpCmd
	private static String fileName = null;
	/**
	 * filePath为文件路径，不带文件名
	 */
	private static String filePath = ".\\test\\readSense";
	/**
	 * 文件下载类型：.jpg ， 。apk
	 */
	private static String downLocalFileType = null;
	public static String URL_NAME = "/download";
	static {
		//注册接口
		HttpCmd.register(URL_NAME, DownLoadApk.class);
	}
	
//	public static void registerFileName(String fileName) {
//		DownLoadApk.fileName = fileName;
//	}
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		File apkFile = new File(getDownLoadFile(downLocalFileType) + fileName);
		System.out.println(apkFile.exists());
		if(apkFile.exists()) {
			downloadFile(apkFile);
		}else {
			ResponseGson<String> responseGson = new ResponseExGson<String>();
			responseGson.setCode("1");
			responseGson.setData("错误，未找到文件路径");
			response(gson.toJson(responseGson, ResponseGson.class));
		}
		
	}
	
	private String getDownLoadFile(String type) {
		switch (type.toUpperCase()) {
		case ".APK":
			type = filePath+"/";
			break;
			
		case ".JPG":
			type = UpdateLocalDatabase.getFilePath();
			break;
			
		case ".JPEG":
			type = UpdateLocalDatabase.getFilePath();
			break;
			
		default:
			break;
		}
		return type;
	}
	
	protected void downloadFile(File file) {
		FileInputStream fileInputStream = null;
		FileChannel fileChannel = null;
		OutputStream outputStream = null;
		try {
			fileInputStream = new FileInputStream(file);
			fileChannel = fileInputStream.getChannel();
			/* 设置文件ContentType类型，这样设置，会自动判断下载文件类型 */
			Headers responseHeaders = http.getResponseHeaders();
			
			responseHeaders.set("Content-type", "application/octet-stream; charset=utf-8");
			/* 设置文件头：最后一个参数是设置下载文件名 */
			responseHeaders.set("Content-Disposition", "attachment;filename="+file.getName());
			responseHeaders.set("Content-Length", Long.toString(fileChannel.size()));
			
			outputStream = http.getResponseBody();
			http.sendResponseHeaders(200, fileChannel.size());
			byte[] b = new byte[1024*1024];
			int len = 0;
			while((len = fileInputStream.read(b)) > 0){//83109285
				outputStream.write(b,0,len);
			}
			outputStream.flush();
		}catch (IOException ioe){
			ioe.printStackTrace();
		}finally {
			try {
				outputStream.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				fileChannel.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				fileInputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public static String getDownLocalFile() {
		return downLocalFileType;
	}
	public static void setDownLocalFile(String downLocalFileType) {
		DownLoadApk.downLocalFileType = downLocalFileType;
	}

	public static String getFileName() {
		return fileName;
	}

	public static void setFileName(String fileName) {
		DownLoadApk.fileName = fileName;
	}

	public static String getFilePath() {
		return filePath;
	}

	public static void setFilePath(String filePath) {
		DownLoadApk.filePath = filePath;
	}

	public static String getURL_NAME() {
		return URL_NAME;
	}

	public static void setURL_NAME(String uRL_NAME) {
		URL_NAME = uRL_NAME;
	}
	
}
