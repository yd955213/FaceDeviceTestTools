package faceEngine;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import com.arcsoft.face.ActiveFileInfo;
import com.arcsoft.face.EngineConfiguration;
import com.arcsoft.face.Face3DAngle;
import com.arcsoft.face.FaceEngine;
import com.arcsoft.face.FaceInfo;
import com.arcsoft.face.FunctionConfiguration;
import com.arcsoft.face.enums.DetectMode;
import com.arcsoft.face.enums.DetectOrient;
import com.arcsoft.face.enums.ErrorInfo;
import com.arcsoft.face.toolkit.ImageInfo;
import tools.WriteFiles;

import static com.arcsoft.face.toolkit.ImageFactory.getRGBData;

public class MyFaceEngine {
	//从官网获取
	private String appId = "FgYZiWZ3tBr8DD6hgn1jwTkQvottZPmqPHS4fQwYZWbo";
	private String sdkKey = "5QCoRZxUBXRYPiJhgcgdfA2Vv7xL6ZYzuePZkY9Y5uE6";
	
	private static FaceEngine faceEngineDetect = null,faceEngine3dAngle = null;
	private int errorCode ;
	private static ImageInfo imageInfo = null;
	static List<FaceInfo> faceInfoList = new ArrayList<FaceInfo>();
	/**
	 * 3D信息检测
	 */
	static List<Face3DAngle> face3DAngleList = new ArrayList<Face3DAngle>();
    private static FunctionConfiguration functionConfiguration = null , configuration = null;
	private CutPicture cutPicture = null;
	private WriteFiles writeFiles = new WriteFiles();
	
	public MyFaceEngine() {
		String libPath = System.getProperty("user.dir") + "\\libs\\win64_dlls" + File.separator;
		if (null == faceEngineDetect) {
			faceEngineDetect = new FaceEngine(libPath);
			errorCode  = faceEngineDetect.activeOnline(appId, sdkKey);
			myDetectInit();
		}
		if (null == faceEngine3dAngle) {
			faceEngine3dAngle = new FaceEngine(libPath);
			faceEngine3dAngle.activeOnline(appId, sdkKey);
			my3dAngleInit();
		}
	}
	
	public boolean getPhotoExecute(File file) {
		boolean successBoolean = false;
		file = getRollPhoto(file);
		if (null != file) {
			getcutPicture(file);
			successBoolean = true;
			file.delete();
		}
		
		return successBoolean;
	}
	
	private String my3dAngleInit() {
		// TODO Auto-generated method stub

		String CodeInfo = null;
		if (errorCode != ErrorInfo.MOK.getValue() && errorCode != ErrorInfo.MERR_ASF_ALREADY_ACTIVATED.getValue()) {
			CodeInfo = "引擎激活失败";
        }else {
        	CodeInfo = "引擎激活成功";
        }
		
		ActiveFileInfo activeFileInfo=new ActiveFileInfo();
        errorCode = faceEngine3dAngle.getActiveFileInfo(activeFileInfo);
        if (errorCode != ErrorInfo.MOK.getValue() && errorCode != ErrorInfo.MERR_ASF_ALREADY_ACTIVATED.getValue()) {
        	CodeInfo = "获取激活文件信息失败";
        }else {
        	CodeInfo = "获取激活文件信息成功";
        }
		//引擎配置
        EngineConfiguration engineConfiguration = new EngineConfiguration();
        engineConfiguration.setDetectMode(DetectMode.ASF_DETECT_MODE_IMAGE);
        engineConfiguration.setDetectFaceOrientPriority(DetectOrient.ASF_OP_ALL_OUT);
        engineConfiguration.setDetectFaceMaxNum(10);
        engineConfiguration.setDetectFaceScaleVal(30);
        
        

//        int start = Integer.parseInt(activeFileInfo.getStartTime());
        int end = Integer.parseInt(activeFileInfo.getEndTime());
        activeFileInfo.setEndTime(Integer.toString(end*10));
//        end = Integer.parseInt(activeFileInfo.getEndTime());
//        System.out.println("剩余有效时间：" + ( end - start )/60/60/24);
    	//功能配置
    	functionConfiguration = new FunctionConfiguration();
        functionConfiguration.setSupportFace3dAngle(true);
        functionConfiguration.setSupportFaceDetect(true);
//        functionConfiguration.setSupportFaceRecognition(true);
//            functionConfiguration.setSupportAge(true);
//            functionConfiguration.setSupportGender(true);
//            functionConfiguration.setSupportLiveness(true);
//            functionConfiguration.setSupportIRLiveness(true);
        engineConfiguration.setFunctionConfiguration(functionConfiguration);
        //初始化引擎
        errorCode = faceEngine3dAngle.init(engineConfiguration);

        if (errorCode != ErrorInfo.MOK.getValue()) {
            System.out.println("初始化引擎失败");
        }
        
            //人脸属性检测
        configuration = new FunctionConfiguration();
//          configuration.setSupportAge(true);
        configuration.setSupportFace3dAngle(true);
//          configuration.setSupportGender(true);
//          configuration.setSupportLiveness(true);
        //初始化引擎
        
        if (errorCode != ErrorInfo.MOK.getValue()) {
        	CodeInfo = "初始化引擎失败";
        }
        return CodeInfo;
	}

	private String myDetectInit() {
		// TODO Auto-generated method stub
		String CodeInfo = null;
		if (errorCode != ErrorInfo.MOK.getValue() && errorCode != ErrorInfo.MERR_ASF_ALREADY_ACTIVATED.getValue()) {
			CodeInfo = "引擎激活失败";
        }else {
        	CodeInfo = "引擎激活成功";
        }
		
		ActiveFileInfo activeFileInfo=new ActiveFileInfo();
        errorCode = faceEngineDetect.getActiveFileInfo(activeFileInfo);
        if (errorCode != ErrorInfo.MOK.getValue() && errorCode != ErrorInfo.MERR_ASF_ALREADY_ACTIVATED.getValue()) {
        	CodeInfo = "获取激活文件信息失败";
        }else {
        	CodeInfo = "获取激活文件信息成功";
        }
		//引擎配置
        EngineConfiguration engineConfiguration = new EngineConfiguration();
        engineConfiguration.setDetectMode(DetectMode.ASF_DETECT_MODE_IMAGE);
        engineConfiguration.setDetectFaceOrientPriority(DetectOrient.ASF_OP_0_ONLY);
        engineConfiguration.setDetectFaceMaxNum(10);
        engineConfiguration.setDetectFaceScaleVal(30);
        
        

//        int start = Integer.parseInt(activeFileInfo.getStartTime());
        int end = Integer.parseInt(activeFileInfo.getEndTime());
        activeFileInfo.setEndTime(Integer.toString(end*10));
//        end = Integer.parseInt(activeFileInfo.getEndTime());
//        System.out.println("剩余有效时间：" + ( end - start )/60/60/24);
    	//功能配置
    	functionConfiguration = new FunctionConfiguration();
//        functionConfiguration.setSupportFace3dAngle(true);
        functionConfiguration.setSupportFaceDetect(true);
//        functionConfiguration.setSupportFaceRecognition(true);
//            functionConfiguration.setSupportAge(true);
//            functionConfiguration.setSupportGender(true);
//            functionConfiguration.setSupportLiveness(true);
//            functionConfiguration.setSupportIRLiveness(true);
        engineConfiguration.setFunctionConfiguration(functionConfiguration);
        //初始化引擎
        errorCode = faceEngineDetect.init(engineConfiguration);

        if (errorCode != ErrorInfo.MOK.getValue()) {
            System.out.println("初始化引擎失败");
        }
        if (errorCode != ErrorInfo.MOK.getValue()) {
        	CodeInfo = "初始化引擎失败";
        }
        return CodeInfo;
	}

	@SuppressWarnings("finally")
	private File getRollPhoto (File file) {
		String srcpath = file.getParent()+"\\temp\\" + file.getName();
		String failFilePath = file.getParent()+"\\CutFail\\" + file.getName();
		//旋转照片
		int angel = getRoll(file);

		BufferedImage bi = null;
		OutputStream os = null;
		if (angel >= 0) {
			try {
				bi = ImageIO.read(Files.newInputStream(Paths.get(file.getPath())));
		    	if (angel > 10) {
		 			bi = MyPhoto.rotateImage(bi, angel);
		 		}

				File tempFile = new File(srcpath);
				writeFiles.createFile(tempFile);
				os = Files.newOutputStream(Paths.get(srcpath));
		    	ImageIO.write(bi, "jpg", os);
				bi.flush();
				return new File(srcpath);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				return null;
			}finally {
				try {
					os.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}else {
			try {
				bi = ImageIO.read(Files.newInputStream(Paths.get(file.getPath())));
				File failFile = new File(failFilePath);
				writeFiles.createFile(failFile);
				os = Files.newOutputStream(Paths.get(failFilePath));
				ImageIO.write(bi, "jpg", os);
				bi.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					os.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;
			}
			
		}
	}
	
	private void getcutPicture(File file) {
		String outFileName = file.getParentFile().getParentFile().toString();
//		System.out.println("截图前后照片路径 = " + file.getPath());
		faceInfoList = getImageInfo(file);
		//返回人脸框，即表示检查到人脸
	    int x;
	    int y;
	    int width = 0;
	    int height = 0;
		if(faceInfoList.size() > 0) {
			int remp = 100;
			x = faceInfoList.get(0).getRect().left - remp;
			x = x > 0 ? x : 0;
			y = faceInfoList.get(0).getRect().top - remp;
			y = y > 0 ? y : 0;
			width = faceInfoList.get(0).getRect().right -x + remp;
			height = faceInfoList.get(0).getRect().bottom -y + remp;
			
			outFileName += ".\\CutSuccess\\" + file.getName();
			
			//裁剪照片
			try {
				cutPicture = new CutPicture(file.getPath().toString(), x, y, width, height);
				
//				System.out.println("截图后照片路径 = " + outFileName);
				cutPicture.cut(new File(outFileName));
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} else {
			
		}
//		file.delete();
	}
	
	/**
	 * 角度信息
	 * @return 照片角度  -1表示未检查到人脸导致无法旋转
	 */
	public int getRoll(File file) {
		int roll = 0;
		imageInfo = getRGBData(file);
		faceInfoList.clear();
//		faceInfoList = new ArrayList<FaceInfo>();
		//用全角度检查人脸角度
		faceEngine3dAngle.detectFaces(imageInfo.getImageData(), imageInfo.getWidth(), imageInfo.getHeight(), imageInfo.getImageFormat(), faceInfoList);
		faceEngine3dAngle.process(imageInfo.getImageData(), imageInfo.getWidth(), imageInfo.getHeight(), imageInfo.getImageFormat(), faceInfoList, configuration);
		face3DAngleList.clear();
//		face3DAngleList = new ArrayList<Face3DAngle>();
		faceEngine3dAngle.getFace3DAngle(face3DAngleList);
//		System.out.println("face3DAngleList = " + face3DAngleList);
		
		if(!face3DAngleList.isEmpty()) {
			roll = (int) face3DAngleList.get(0).getRoll();
			if (roll < 0) {
	    		// 将负数角度，纠正为正数角度 
				roll = roll + 360;
	    	}
		}else {
			roll = -1;
		}
		return roll;
	}
	public List<FaceInfo> getImageInfo(File file) {
		imageInfo = getRGBData(file);
		faceInfoList.clear();
//		faceInfoList = new ArrayList<FaceInfo>();
		faceEngineDetect.detectFaces(imageInfo.getImageData(), imageInfo.getWidth(), imageInfo.getHeight(), imageInfo.getImageFormat(), faceInfoList);
//		System.out.println("faceInfoList = " + faceInfoList);
		return faceInfoList;
	}

}
