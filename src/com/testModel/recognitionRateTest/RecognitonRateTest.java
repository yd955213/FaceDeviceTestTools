package com.testModel.recognitionRateTest;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import faceEngine.MyPhoto;
import httpFrame.http.dasApi.UploadRecords;
import sqlite3.DataBaseExecute;
import tools.LogsWriter;
import tools.WriteFiles;
import tools.downLoadAuthority.DownLoadAuthorityStatu;
import view.MainIntfaceView;

public class RecognitonRateTest {
	
	private JCheckBox doNotCreatePersonCheckBox = null;
	private JCheckBox doNotDownLoadCheckBox = null;
	private JComboBox<String> macAddrComboBox = null;
	private static final String PERSONFACE_A = "./test/facePhotoDataBase_A/";
	public static final String CONTRASTPHOTO_B = "./test/contrastPhotoDataBase_B/";
	
	private final int PHOTO_SIZE = 500;
	private ImageIcon icon = null;
	private BufferedImage srcImage = null;
	private File[] fileArray = null;
 	private Double total = 0D;
	private Double tempProgress = 0D;
	private String recognitionRate = null ;
	private String errorRate = null;
	private Double rejectionTime = 0D;
	private Double errorTime = 0D;
	private int temp = 0;
	private NumberFormat numberFormat = NumberFormat.getPercentInstance();  
	private static Timer timer = null;
	private String fileName = null;
	private String fileNameNoJpg = null;
	
	
	public RecognitonRateTest() {
		
	}
	public RecognitonRateTest(JCheckBox doNotCreatePersonCheckBox, JCheckBox doNotDownLoadCheckBox , JComboBox<String> macAddrComboBox) {
		this.doNotCreatePersonCheckBox = doNotCreatePersonCheckBox;
		this.doNotDownLoadCheckBox = doNotDownLoadCheckBox;
		this.macAddrComboBox = macAddrComboBox;
	}
	/**初始化，若目录下没有文件夹/test/facePhotoDataBase_A、contrastPhtotDataBase_B，则新建*/
	public static void getInit() {
		WriteFiles writeFiles = new WriteFiles();
		writeFiles.createFoder(new File(PERSONFACE_A));
		writeFiles.createFoder(new File(CONTRASTPHOTO_B));
	}
	public void doRecognitonRateTest() {
		tempProgress = 0D ;
		recognitionRate = "" ;
		errorRate = "" ;
		rejectionTime=0D;
		errorTime = 0D;
		updateView();
		getPpreparation();
		/*
		 * 开始失败对比测试
		 */
		
		fileArray = new File(CONTRASTPHOTO_B).listFiles();
		total = (double)fileArray.length;
		MainIntfaceView.getRrtLabel_total().setText(Double.toString(total));
//		System.out.println(total);
		if (total > 1) {
			//定时切换照片
			getTimes();
		}
		
	}
	/**初始化工作：准备对比数据*/
	private void getPpreparation() {
		//新建人员
		if (!doNotCreatePersonCheckBox.isSelected()) {
			//清除库中数据，防止干扰测试
			String sql = "delete from face_photo where photo_url like '%A001_%';";
			DataBaseExecute.getInstance().deleteDataBase(sql);
			
			new DownLoadAuthorityStatu().insertIntoPersonInfo(new File(PERSONFACE_A));
			
		}
		//权限下载
		if (!doNotDownLoadCheckBox.isSelected()) {
			String macAddr = macAddrComboBox.getSelectedItem().toString();
			//判断可下载权限有多少，无则提示
			DataBaseExecute.getInstance().updateDB("face_dev_author_set", Arrays.asList("dev_id"), Arrays.asList(MainIntfaceView.getDevInfo().get(macAddr).getDevID()), Arrays.asList("down_loaded"), Arrays.asList("0"));
			
			new DownLoadAuthorityStatu().downLoadAuthority(macAddr, MainIntfaceView.getDevInfo().get(macAddr).getDevID(), true);
		}
	}
	private void updateView() {
		// TODO Auto-generated method stub
		MainIntfaceView.getRrtLabel_progress().setText(Double.toString(tempProgress));
		MainIntfaceView.getRrtLabel_recognitionRate().setText(recognitionRate);
//		MainIntfaceView.getRrtLabel_rejectionRate().setText(rejectionRate);
		MainIntfaceView.getRrtLabel_errorRate().setText(errorRate);
	}
	
	public void updatePhoto(JLabel label, File[] fileArray, int subscript) {
		int width = 0;
		int height = 0;
		try {
			srcImage =  ImageIO.read(new FileInputStream(fileArray[subscript]));
			if (srcImage.getWidth() > PHOTO_SIZE || srcImage.getHeight() > PHOTO_SIZE) {
				double sx = (double) PHOTO_SIZE / srcImage.getWidth();
		        double sy = (double) PHOTO_SIZE / srcImage.getHeight();
		     // 等比缩放
		        sx = sx > sy? sx:sy;
		        width = (int) (sx * srcImage.getWidth());
		        height = (int) (sx * srcImage.getHeight());
			}else {
				width = srcImage.getWidth();
		        height = srcImage.getHeight();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		icon = MyPhoto.getImageIconfromBASE64(MyPhoto.getPhoteBASE64_Mime(fileArray[subscript]), width, height);
		label.setIcon(icon);
	}
	
	public static final int DELAY = 5000;
	public static final int DELAY_TODO_ATHOR = 1000;
	public final int SLEEP_TIMES = 100;
	private void getTimes() {
		timer = new Timer("test");
		int timeToSetNull = DELAY - DELAY_TODO_ATHOR;
		
		timer.schedule(new TimerTask() {
            Thread t= null;
            public void run() {
            	/*
            	 * 定时等待，
            	 */
            	waitTimeToSetPictureJlableNull(t, MainIntfaceView.getRrtLaben_photo(), DELAY_TODO_ATHOR, timeToSetNull);
            	
        		updatePhoto(MainIntfaceView.getRrtLaben_photo(), fileArray, tempProgress.intValue());
        		
            	if (tempProgress >= total - 1D) {
            		if (null != timer) {
        				timer.cancel();
        				try {
    						Thread.sleep(DELAY);
    					} catch (InterruptedException e) {
    						// TODO Auto-generated catch block
    						e.printStackTrace();
    					}
        				MainIntfaceView.getRrtLaben_photo().setText(MainIntfaceView.getExplain());
        				MainIntfaceView.getRrtLaben_photo().setIcon(null);
        			}
            	}
            	temp = 0;
            	fileName = fileArray[tempProgress.intValue()].getName();
            	fileNameNoJpg = fileName.split("\\.")[0];
            	while(true) {
            		/*A库为人脸库，下载都设备中， B库为识别库（有混淆照片）;其中500人与A库为同一个人命名为库B1，500人不在A库中命名为B2
            		 * B库中照片命名：B1以B001开头，B2已B002开头；
            			A库中在B库中的人员以B003开头，其他随意
            		 */
            		
            		/*
            		 * 当设备上传记录后，姓名不已B003开头，即该记录不是A库中的照片，为误识别
            		 * （当前情况不考虑记录未上报的情况，3s还未上传，你是有多差）
            		 */
            		if (UploadRecords.getName() != null && !(fileNameNoJpg.equals(UploadRecords.getName()))) {
            			errorTime += 1D;
            			LogsWriter.writeInfo_logs(LogsWriter.RECOGNITION_RATE_TEST, "文件名：" + fileName + "  与  设备中人员姓名" + UploadRecords.getName() + "  误识别");
                		break;
            		}
            		/*
            		 * 当文件名以"B001"开头时，且设备无记录上传，判断为拒识别 （当前情况不考虑记录未上报的情况，3s还未上传，你是有多差）
            		 */
            		
            		if (temp >= DELAY ) {
        				if (fileName.startsWith("AB001")) {
	        				rejectionTime += 1D;
	        				LogsWriter.writeInfo_logs(LogsWriter.RECOGNITION_RATE_TEST, "文件名：" + fileName + "  判断为拒识别，" + DELAY + "毫秒内无记录上传（不考虑记录未上报）！");
	        			}
            			temp = 0;
            			break;
            		}
            		//等待
            		try {
						Thread.sleep(SLEEP_TIMES);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
            		temp += SLEEP_TIMES;
//            		System.out.println("temp = " + temp);
            	}
            	
//		        	 计数识别率 开始100%
        		getRecognitionRate();
//		        //计算误识率 开始0%
        		getErrotRate();
//		        //计算拒识率 开始 0%
//	        		getRejectionRate();
        		
        		//更新面板
        		tempProgress += 1;
        		updateView();
        		UploadRecords.setName(null);
        		
            }
        }, 1000,DELAY);
	}
	/**
	 * jLabel 显示内容时长 times, 显示为空时长：timeToSetNull
	 * @param t
	 * @param jLabel
	 * @param times 4
	 * @param timeToSetNull 1
	 */
	public void waitTimeToSetPictureJlableNull(Thread t, JLabel jLabel, int times, int timeToSetNull) {
		t = new Thread(() ->{
    		int temp = 0;
    		while(temp < timeToSetNull) {
    			temp += DELAY_TODO_ATHOR;
    			try {
					Thread.currentThread();
					Thread.sleep(DELAY_TODO_ATHOR);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}
    		jLabel.setIcon(null);
    	});
    	t.start();
	}
	
	private void getRecognitionRate() {
		recognitionRate = numberFormat.format((total - rejectionTime - errorTime) / total);
	}
//	private void getRejectionRate() {
//		rejectionRate = numberFormat.format(rejectionTime / total);
//	}
	private void getErrotRate() {
		errorRate = numberFormat.format(errorTime / total);
	}
	public static Timer getTimer() {
		return timer;
	}
	
}
