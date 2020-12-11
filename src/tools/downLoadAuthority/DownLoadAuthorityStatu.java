package tools.downLoadAuthority;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import javax.swing.JButton;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import faceEngine.MyPhoto;
import myGson.das.DownloadAuthorityDataGson;
import myGson.das.GetCharacterGson;
import myGson.das.ResponseExGson;
import myGson.das.SendGson;
import myProgressBar.PrimeNumbersTask;
import mySocketClient.myHttpClient.api.DownloadAuthorityData;
import mySocketClient.myHttpClient.api.GetCharacter;
import sqlite3.DataBaseExecute;
import sqlite3.DownLoadAuthority;
import sqlite3.DownLoadAuthorityType;
import sqlite3.PersonID;
import tools.LogsWriter;
import tools.MyFileUtil;
import tools.SystemTimes;
import view.MainIntfaceView;

public class DownLoadAuthorityStatu {

	private static boolean downLoadAuthorityFaile = false;
	private static boolean wantBreak = false;
	private static int lotSize = 0;
	private final int TOTAL = 50;
	private static int err = 0;
	private static int success = 0;
	/**
	 * 全局变量，用于判断是否完成下载，例如一次下载10条，每返回一条downPersonInfo移除该条
	 */
	private static HashMap<String, String> downPersonInfo = new HashMap<String, String>();
	private int sendListLength = 30; //每次下载数量
	private int downLoadAgain = 3; //连接失败 反复下载次数
//	private int overLength = 0;
	private DownloadAuthorityData dad;
	private static String photoUrl = null;
	private static HashMap<String, String> photoUrlList = new HashMap<String, String>();
	
	public void insertIntoPersonInfo(File file) {
		err = 0;
		success = 0;
		File[] fileArr  = file.listFiles();
		int fileArrListlength = fileArr.length;
		
		ExecutorService pool = new ThreadPoolExecutor(3, 6, 1000, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(10),Executors.defaultThreadFactory(),new ThreadPoolExecutor.CallerRunsPolicy());
		CountDownLatch countDownLatch = null;
		PhotoStorageThread photoStorageThread = null;
		//获取数据库中的最大的 PersonId
		PersonID personID= PersonID.getInstance();
		PersonID.getInstance().getPersonIdInDb();
//		System.out.println("PersonID.getInstance().getPersonIdInDb(); = " + PersonID.getInstance().getPersonIdInDb());
		ReentrantLock lock  = new ReentrantLock();
		int temp = 0;
		/**
		 * 作为fileArrList的计数器
		 */
		int total = 0;
		while (fileArrListlength - total > 0) {
			
			if (wantBreak) {
				pool.shutdownNow();
				wantBreak = false;
				break;
			}
			/*
			 *  新建同步工具 CountDownLatch 
			 */
			temp = fileArrListlength - total;
			if (temp >= TOTAL) {
				temp = TOTAL;
			} 
			countDownLatch = new CountDownLatch(temp);
			/*
			 * 多线程读取照片文件
			 */
			for (int j = 0 ; j < temp; j ++) {
				photoStorageThread = new PhotoStorageThread(fileArr[total], personID, countDownLatch);
				pool.execute(photoStorageThread);
				total += 1;
				//手动清空内存
				if (total > 1) {
					fileArr[total-1] = null;
				}
			}
			try {
				//等待开启的TOTAL个线程结束
				if (null != countDownLatch) {
					countDownLatch.await();
					countDownLatch = null;
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			lock.lock();
			
			/*
			 * 写数据库，并清空数据
			 */
			DataBaseExecute.getInstance().insertIntoPersonInfo(PhotoStorageThread.getPersonInfoList());
			DataBaseExecute.getInstance().insertIntoFacePhoto(PhotoStorageThread.getFaceEmpPhotoList());
			PhotoStorageThread.getPersonInfoList().clear();
			PhotoStorageThread.getFaceEmpPhotoList().clear();
			photoStorageThread = null;
			lock.unlock();
		}
		file = null;
		pool.shutdownNow();
		
	}
	
	/**
	 * 批量提取特征值
	 * @param macAddr
	 */
	public void dowLoadFeature(String macAddr) {
		List<List<String>> personList = null;
		int page = 0;
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		GetCharacterGson getCharacterGson = new GetCharacterGson();
		SendGson<GetCharacterGson> sendGson = new SendGson<GetCharacterGson>();
		TypeToken<SendGson<GetCharacterGson>> typeToken = new TypeToken<SendGson<GetCharacterGson>>() {};
		err = 0;
		success = 0;
		while(true) {
			
			if (wantBreak) {
				wantBreak = false;
				break;
			}
			
			personList = new DownLoadAuthority().listDownloadFeature(macAddr);
			page = personList.size();
			getCharacterGson = new GetCharacterGson();
			sendGson = new SendGson<GetCharacterGson>();
			for (int i =0 ; i < page ; i++) {
				getCharacterGson.setUniqueCode(personList.get(i).get(2));
				getCharacterGson.setPhoto(MyPhoto.getPhoteBASE64_Mime(new File(personList.get(i).get(1))));
				
				sendGson.setData(getCharacterGson);
				sendGson.setDeviceUniqueCode(macAddr);
				sendGson.setTimeStamp(SystemTimes.getSysTime1());
				
				new GetCharacter(gson.toJson(sendGson, typeToken.getType()), macAddr);
				if (wantBreak) {
					break;
				}
			}
			getCharacterGson = null;
			sendGson = null;
			if(page == 0 ) {
//				//用于PrimeNumbersTask 判断是否停止
//				wantBreak = true;
				break;
			}
		}
	}
	/**
	 * 人脸权限下载
	 * @param macAddr
	 * @param devID
	 * @param hasFeature false:0 下载不戴特征值， true:4、 带特征值下载；
	 */
	public void downLoadAuthority (String macAddr, String devID, boolean hasFeature) {
		
		err = 0;
		success = 0;
		
		if (hasFeature) {
			getDasDownLoadAuthority(macAddr, devID, DownLoadAuthorityType.PHOTOURL_AND_FEATURE);
		}else {
			getDasDownLoadAuthority(macAddr, devID, DownLoadAuthorityType.PHOTOURL);
//			getJvtDownLoadAuthority(macAddr, devID, DownLoadAuthorityType.PHOTOURL);
		}
	}
	/**
	 * 人脸权限下载
	 * @param macAddr
	 * @param devID
	 * @param oneTime false:0 一次下载一张， true:连续下载
	 * @param type 0:含photo_url、 无feature  1 ：舍弃、2:含PhotoBase64、 3：含PhotoBase64，feature, 4:含photo_url、feature,down_loaded = 0; 、5： is_legal = N 删除权限
	 *  
	 */
	public void downLoadAuthority (String macAddr, String devID, boolean oneTime, DownLoadAuthorityType type) {
		
		err = 0;
		success = 0;
		
		if (oneTime) {
			getDasDownLoadAuthority(macAddr, devID, type);
		}else {
			getJvtDownLoadAuthority(macAddr, devID, type);
		}
	}
	
	/**
	 * 标准das权限下载
	 * @param macAddr
	 * @param devID
	 * @param type 0:含photo_url、 无feature  1 ：舍弃、2:含PhotoBase64、 3：含PhotoBase64，feature, 4:含photo_url、feature,down_loaded = 0;、5： is_legal = N 删除权限
	 */
	private void getDasDownLoadAuthority(String macAddr,String devID, DownLoadAuthorityType type) {
		
		List<DownloadAuthorityDataGson> personList = null;
		int page = 0;
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		SendGson<List<DownloadAuthorityDataGson>> sendGson = new SendGson<List<DownloadAuthorityDataGson>>();
		TypeToken<SendGson<List<DownloadAuthorityDataGson>>> typeToken = new TypeToken<SendGson<List<DownloadAuthorityDataGson>>>(){};
		photoUrl = null;
		String filePath = null;
		File file = null;
		while (true) {
			if (wantBreak) {
				wantBreak = false;
				break;
			}
			personList = new DownLoadAuthority().listDownloadAuthorityDataGson(sendListLength, type, devID);
			page = personList.size();
			System.out.println(page);
			if(page <= 0 ) {
				break;
			}
			photoUrlList = new HashMap<String, String>();
			for (int j = 0; j < page; j ++) {
				filePath = personList.get(j).getPhoto();
				photoUrlList.put(personList.get(j).getUniqueCode(), filePath);
//				这里有时会出现将转换后的base64当成了文件路径，问题还未找到，应该是多线程的锅：先将personList.get(j).getPhoto() 赋值给filePath 看看
				file = new File(filePath);
				personList.get(j).setPhoto(MyPhoto.getPhoteBASE64_Mime(file));
				downPersonInfo.put(personList.get(j).getUniqueCode(), "0");
				
			}
			sendGson = new SendGson<List<DownloadAuthorityDataGson>>();
			sendGson.setDeviceUniqueCode(macAddr);
			sendGson.setTimeStamp(SystemTimes.getSysTime1());
			sendGson.setData(personList);
			String tempStr= gson.toJson(sendGson, typeToken.getType());
			// 发送数据
			new DownloadAuthorityData(tempStr, macAddr).sendData();
			//手动回收内存
			file = null;
			tempStr = null;
			sendGson = null;
			personList.clear();
			
			myWite(macAddr, dad);
		}
	}
	/**
	 * 巨龙权限下载，后台不管理特征值，直接下载照片 且一次下载一张照片
	 * @param macAddr
	 * @param devID
	 * @param type = 0  0:含photo_url、 无feature  1 ：舍弃、2:含PhotoBase64、 3：含PhotoBase64，feature, 4:含photo_url、feature,down_loaded = 0;、5： is_legal = N 删除权限
	 */
	@SuppressWarnings("unchecked")
	private void getJvtDownLoadAuthority(String macAddr, String devID, DownLoadAuthorityType type) {
		List<DownloadAuthorityDataGson> personList = null;
		int page = 0;
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		ResponseExGson<String> responseGson = null;
		SendGson<List<DownloadAuthorityDataGson>> sendGson = new SendGson<List<DownloadAuthorityDataGson>>();
		TypeToken<SendGson<List<DownloadAuthorityDataGson>>> typeToken = new TypeToken<SendGson<List<DownloadAuthorityDataGson>>>(){};
//		TypeToken<ResponseExGson<String>> responseGsonTypeToken= new TypeToken<ResponseExGson<String>>() {};
		List<StringBuffer> stringBufferList = new ArrayList<StringBuffer>();
		//巨龙设备只能一次下载一张照片
		String devid = DataBaseExecute.getInstance().getDevID(macAddr);
		int temp = 0;
		photoUrl = null;
		MyPhoto myPhoto = new MyPhoto();
//		String requestStr = null;
//		String responseStr = null;
		while (true) {
			if (wantBreak) {
				wantBreak = false;
				break;
			}
			personList = new DownLoadAuthority().listDownloadAuthorityDataGson(sendListLength, type, devID);
			page = personList.size();
			
			if(page <= 0 ) {
				break;
			}
			//清空StringBuff
			for (StringBuffer sb : stringBufferList) {
				sb.setLength(0);
			}
			
//			System.out.println(type);
			for (int j = 0; j < page; j ++) {
				photoUrl = personList.get(j).getPhoto();
				photoUrlList = new HashMap<String, String>();
				personList.get(j).setPhoto(MyPhoto.getPhoteBASE64_Mime(new File(photoUrl)));
				downPersonInfo.put(personList.get(j).getUniqueCode(), "0");
				sendGson.setDeviceUniqueCode(macAddr);
				sendGson.setTimeStamp(SystemTimes.getSysTime1());
				sendGson.setData(Arrays.asList(personList.get(j)));
				
//				requestStr = gson.toJson(sendGson, typeToken.getType());
//				responseStr = new DownloadAuthorityData(requestStr, macAddr).sendData();
//				
//				// 发送数据
//				responseGson = gson.fromJson(responseStr, ResponseExGson.class);

				// 发送数据
				responseGson = gson.fromJson(
						new DownloadAuthorityData(
								gson.toJson(
										sendGson, 
										typeToken.getType()), 
								macAddr)
						.sendData(), 
						ResponseExGson.class);
				
				/*
				 * 当设备返回:设备忙  重新下载；
				 * 设备返回: OK、文件太大、图片质量差 将下载状态置为1
				 * 当发送未返回时，不写数据库
				 */
				switch (responseGson.getMsg()) {
				case "OK":
					DataBaseExecute.getInstance().updateDB(getUpdataAuthoritySql(true, responseGson.getMsg(), devid, personList.get(j).getUniqueCode()));
					//写日志
					break;
				case "设备忙": 
					//不写下载标志位，可反复下载
					DataBaseExecute.getInstance().updateDB(getUpdataAuthoritySql(false, responseGson.getMsg(), devid, personList.get(j).getUniqueCode()));
					//写日志
					LogsWriter.writeInfo(LogsWriter.DOWN_LOAD_AUTHORITY_ERR, getLogsMassage(personList.get(j).getPersonName(),photoUrl, responseGson.getMsg(), macAddr));
					//将下载失败照片文件夹
					myPhoto.writePhotoToFile(photoUrl);
					break;
					
				case "Err":  //连接失败
					//写日志
					LogsWriter.writeInfo(LogsWriter.DOWN_LOAD_AUTHORITY_ERR, getLogsMassage(personList.get(j).getPersonName(),photoUrl, responseGson.getErr(), macAddr));
					//将下载失败照片文件夹
					myPhoto.writePhotoToFile(photoUrl);
					break;
				default:
					DataBaseExecute.getInstance().updateDB(getUpdataAuthoritySql(true, responseGson.getMsg(), devid, personList.get(j).getUniqueCode()));
					//写日志
					LogsWriter.writeInfo(LogsWriter.DOWN_LOAD_AUTHORITY_ERR, getLogsMassage(personList.get(j).getPersonName(),photoUrl, responseGson.getMsg(), macAddr));
					//将下载失败照片文件夹
					myPhoto.writePhotoToFile(photoUrl);
					break;
				}
//				myWite(macAddr, dad);
			}
			PrimeNumbersTask.setNumber(++temp);
		}
	}
	
	/**
	 * 文件夹方式下载人脸权限  废弃
	 * @param macAddr
	 * @param button
	 */
	public void DownLoadAuthority_file (String macAddr, JButton button) {
		File file = new MyFileUtil().chooserDIRECTORIES();
		if ( null != file || null !=  macAddr) {
				
			button.setEnabled(false);
			
			//照片写库

//			new Thread(()->{
				insertIntoPhotoToDB(file, macAddr);
//			}).start();
			
			// 10后开始下载
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// 开始下载
			new Thread(()->{
				downLoadAuthority(macAddr, MainIntfaceView.getDevInfo().get(macAddr).getDevID(), false);
			}).start();
				
		}
		button.setEnabled(true);
	}
	/**
	 * 照片URL 、base64 写库
	 * @param file
	 * @param macAddr
	 */
	private void insertIntoPhotoToDB(File file,String macAddr) {
		/*
		 * 获取选择文件夹
		 */
		if(null != file) {
			File[] fileArr  = file.listFiles();
			int fileArrListlength = fileArr.length;
			System.out.println(fileArrListlength);
			init(fileArrListlength);
			err = 0;
			success = 0;
			int feature_type = Integer.parseInt(MainIntfaceView.getDevInfo().get(macAddr).getFeature_type());
			ExecutorService pool = new ThreadPoolExecutor(3, 6, 1000, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(10),Executors.defaultThreadFactory(),new ThreadPoolExecutor.CallerRunsPolicy());
			CountDownLatch countDownLatch = null;
			PhotoBase64StorageThread photoBase64StorageThread = null;
			//获取数据库中的最大的 PersonId
			PersonID personID= PersonID.getInstance();
			PersonID.getInstance().getPersonIdInDb();
			int total = 0;
			int temp = 0;
			//开始下载
//			try {
				while(fileArrListlength - total > 0) {
	//				pool = new ThreadPoolExecutor(3, 6, 1000, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(10),Executors.defaultThreadFactory(),new ThreadPoolExecutor.CallerRunsPolicy());
					
					if (wantBreak) {
						pool.shutdownNow();
						wantBreak = false;
						break;
					}
					
					temp = fileArrListlength - total;
					if (temp >= TOTAL) {
						temp = TOTAL;
					}
					countDownLatch = new CountDownLatch(temp);
					for (int i = 0 ; i < temp ; i++) {
						photoBase64StorageThread = new PhotoBase64StorageThread(fileArr[total], feature_type, personID, countDownLatch);
	//					System.out.println(fileArr[total].getName());
						pool.execute(photoBase64StorageThread);
						//手动清空内存
						fileArr[total] = null;
	//					if (total > 1) {
	//						fileArr[total-1] = null;
	//					}
						total +=1;
					}
					
					//所有的子线程都结束了
//					countDownLatch.await();  //屏蔽这段代码，jvm内存占用貌似就没怎么涨了
	//				pool.shutdownNow();
					DataBaseExecute.getInstance().insertIntoPersonInfo(PhotoBase64StorageThread.getPersonInfoList());
					DataBaseExecute.getInstance().insertIntoFacePhoto(PhotoBase64StorageThread.getFaceEmpPhoto());
					PhotoBase64StorageThread.getPersonInfoList().clear();
					PhotoBase64StorageThread.getFaceEmpPhoto().clear();
					
				}
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			file = null;
			//所有的子线程都结束了
			try {
				if (null != countDownLatch) {
					countDownLatch.await();
				}
				pool.shutdownNow();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	private void init(int fileArrListlength) {
		// TODO Auto-generated method stub
		//下载之前 初始化
		lotSize = 0;
		
		//下载之前 清空表
		DataBaseExecute.getInstance().cleanTable("pearson_info");
		DataBaseExecute.getInstance().cleanTable("face_photo");
		DataBaseExecute.getInstance().cleanTable("face_feature");
		DataBaseExecute.getInstance().cleanTable("face_dev_author_set");
	}
	private void myWite(String macAddr, DownloadAuthorityData dad) {
		// TODO Auto-generated method stub
		/*
		 * 等待设备返回下载成功信息，如果downPersonInfo为空，则完成本次10条下载,重新开始下一次下载
		 */
		int reSend = 1;
		int reSend1 = 1;
		int witeTime = 10*sendListLength;
		try {
			while (true) {
	//			System.out.println("downPersonInfo = " + downPersonInfo);
	//			System.out.println("downPersonInfo = " + downPersonInfo.size());
				if (wantBreak) {
					wantBreak = false;
					break;
				}
				if (downPersonInfo.isEmpty()) {
					break;
				}
				
				if(downLoadAuthorityFaile) {
					downLoadAuthorityFaile = false;
					MainIntfaceView.writeLogsTextArea(null, "[设备" + macAddr + "无返回,当前再次下载次数]：" + reSend);
					dad.reSendData();
	    			reSend += 1;
	    			if ( reSend > downLoadAgain) {
	    				break;
	    			}
				}
				//放在设备没有返回， 1分钟后打断等待循环
				reSend1 +=1;
	//			System.out.println("reSend1 = " + reSend1);
				if( reSend1 > witeTime) {
					MainIntfaceView.downloadAuthorLabel_err.setText(Integer.toString(err + downPersonInfo.size()));
					downPersonInfo.clear();
					break;
				}
			
				Thread.sleep(200);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			MainIntfaceView.writeLogsTextArea(null, "[未知错误]："+e.getMessage());
			e.printStackTrace();
		}
	}

	
	
	
	public static boolean isDownLoadAuthorityFaile() {
		return downLoadAuthorityFaile;
	}

	public static void setDownLoadAuthorityFaile(boolean downLoadAuthorityFaile) {
		DownLoadAuthorityStatu.downLoadAuthorityFaile = downLoadAuthorityFaile;
	}

	public static int getLotSize() {
		return lotSize;
	}

	public static void setLotSize(int lotSize) {
		DownLoadAuthorityStatu.lotSize = lotSize;
	}

	public static HashMap<String, String> getDownPersonInfo() {
		return downPersonInfo;
	}

	public static void setDownPersonInfo(HashMap<String, String> downPersonInfo) {
		DownLoadAuthorityStatu.downPersonInfo = downPersonInfo;
	}
	
	public static int getAddErr() {
		err += 1;
		return err;
	}



	public synchronized static int getAddSuccess() {
		success += 1;
		return success;
	}



	public static boolean isWantBreak() {
		return wantBreak;
	}

	

	public static HashMap<String, String> getPhotoUrlList() {
		return photoUrlList;
	}
	public static void setPhotoUrlList(HashMap<String, String> photoUrlList) {
		DownLoadAuthorityStatu.photoUrlList = photoUrlList;
	}
	

	public static void setWantBreak(boolean wantBreak) {
		DownLoadAuthorityStatu.wantBreak = wantBreak;
	}
	/**
	 * down_loaded = true,时，sql语句加入 down_loaded = 1
	 * @return sql
	 */
	private String getUpdataAuthoritySql(boolean down_loaded, String msg, String devid, String uniqueCode) {
		StringBuffer buffer= new StringBuffer();
		buffer.append("update face_dev_author_set set ");
		if (down_loaded) {
			buffer.append("down_loaded = 1, ");
		}
		buffer.append("last_down_time = '" + SystemTimes.getSysTime() + "', remark = '"+ msg +"', gmt_modified = '"+ SystemTimes.getSysTime() +"' where "
				+ "dev_id = " + devid + " and person_id = " + uniqueCode + ";");
		return buffer.toString();
	}
	/**"人员姓名:" + name + ", 照片路径：" + photoUrl + " 错误信息：" + errMgs*/
	public static String getLogsMassage(String name, String photoUrl, String errMgs, String devmac) {
		return "人员姓名:" + name + ", 照片路径：" + photoUrl + ",设备mac地址： "+devmac+"， 错误信息：" + errMgs;
	}

	
}
