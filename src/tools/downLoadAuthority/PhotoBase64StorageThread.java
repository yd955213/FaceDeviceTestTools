package tools.downLoadAuthority;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;

import javax.imageio.ImageIO;

import faceEngine.MyPhoto;
import myProgressBar.PrimeNumbersTask;
import sqlite3.FaceEmpPhoto;
import sqlite3.PersonID;
import sqlite3.PersonInfo;
import tools.LogsWriter;
import view.MainIntfaceView;

/**
 * 照片入库线程
 * @author yangdang
 *
 */
public class PhotoBase64StorageThread implements Runnable{
	private File file = null;
	private Image image = null;
	private int feature_type;
	private PersonID personID ;
	private static CopyOnWriteArrayList<PersonInfo> PersonInfoList = new CopyOnWriteArrayList<PersonInfo>();
	private static CopyOnWriteArrayList<FaceEmpPhoto> faceEmpPhoto = new CopyOnWriteArrayList<FaceEmpPhoto>();
//	private final int TOTAL = 30;
	private PersonInfo pi = null;
	private FaceEmpPhoto fep = null;
	private CountDownLatch countDownLatch;
	private ReentrantLock lock = new ReentrantLock();
	
	public PhotoBase64StorageThread(File file, int feature_type, PersonID personID, CountDownLatch countDownLatch) {
		this.file = file;
		this.feature_type = feature_type;
		this.personID = personID;
		this.countDownLatch = countDownLatch;
	}
	//synchronized
	private synchronized void photoStorage()  {
		
		if( file.isFile()) {
			try {
				image = ImageIO.read(file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				MainIntfaceView.downloadAuthorLabel_err.setText(Integer.toString(DownLoadAuthorityStatu.getAddErr()));
				LogsWriter.writeInfo(LogsWriter.PTHOTO_STORAGE_ERR, file.getName() + " " + e.getMessage());
//				e.printStackTrace();
			}
			int success = 0;
			String filePath = file.getPath();
			if ( null != image) {
				lock.lock();
				success = DownLoadAuthorityStatu.getAddSuccess();
				int id = personID.addPersonID();
				
				pi = new PersonInfo();
				pi.setPersonID(id);
				pi.setPersonNo("test" + id);
				pi.setPersonName(file.getName().split("\\.")[0]);
				pi.setPhotoPath(filePath);
				
				PersonInfoList.add(pi);
				
				String photoBase64 = MyPhoto.getPhoteBASE64_Mime(file);
				
				fep = new FaceEmpPhoto();
				fep.setPersonID(id);
				fep.setPhotoUrl(filePath);
				fep.setPhotoBase64(photoBase64);
				fep.setFeatureType(feature_type);
				
				faceEmpPhoto.add(fep);
				
				lock.unlock();
				MainIntfaceView.downloadAuthorLabel_success.setText(Integer.toString(success));
			}else {
				MainIntfaceView.downloadAuthorLabel_err.setText(Integer.toString(DownLoadAuthorityStatu.getAddErr()));
			}
		}else {
			MainIntfaceView.downloadAuthorLabel_err.setText(Integer.toString(DownLoadAuthorityStatu.getAddErr()));
		}
		
		PrimeNumbersTask.setNumber(PrimeNumbersTask.getNumber() + 1);
		fep = null;
		image = null;
		countDownLatch.countDown();
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		photoStorage();
	}
	public static CopyOnWriteArrayList<PersonInfo> getPersonInfoList() {
		return PersonInfoList;
	}
	public static CopyOnWriteArrayList<FaceEmpPhoto> getFaceEmpPhoto() {
		return faceEmpPhoto;
	}
	
	
}
