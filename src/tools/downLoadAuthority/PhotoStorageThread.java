package tools.downLoadAuthority;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;

import javax.imageio.ImageIO;
import myProgressBar.PrimeNumbersTask;
import sqlite3.FaceEmpPhoto;
import sqlite3.PersonID;
import sqlite3.PersonInfo;
import tools.LogsWriter;

/**
 * 照片入库线程
 * @author yangdang
 *
 */
public class PhotoStorageThread implements Runnable{
	private File file = null;
	private Image image = null;
	private PersonID personID ;
	private static CopyOnWriteArrayList<PersonInfo> PersonInfoList = new CopyOnWriteArrayList<PersonInfo>();
	private static CopyOnWriteArrayList<FaceEmpPhoto> faceEmpPhotoList = new CopyOnWriteArrayList<FaceEmpPhoto>();
	private PersonInfo pi = null;
	private FaceEmpPhoto fep = null;
	private CountDownLatch countDownLatch;
//	private static boolean insertIntoDb = false;
	private static ReentrantLock lock = new ReentrantLock();
    
	public PhotoStorageThread(File file, PersonID personID, CountDownLatch countDownLatch) {
		this.file = file;
		this.personID = personID;
		this.countDownLatch = countDownLatch;
	}
	//synchronized
	private synchronized void photoStorage()  {
		int id = 0;
		if( file.isFile()) {
			image = null;
			try {
				image = ImageIO.read(file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				LogsWriter.writeInfo(LogsWriter.PTHOTO_STORAGE_ERR, file.getName() + " " + e.getMessage());
			}
			
			//文件是照片，且数据库表face_photo没有改照片的路径，则新增人员
			String filePath = file.getPath();
//			if ( null != image && DataBaseExecute.getInstance().listResult("face_photo", "photo_url", filePath, "photo_url").isEmpty()) {
			
				if ( null != image) {
					lock.lock();
					id = personID.addPersonID();
					
					pi = new PersonInfo();
					pi.setPersonID(id);
					pi.setPersonNo("test" + id);
					pi.setPersonName(file.getName().split("\\.")[0]);
					pi.setPhotoPath(filePath);
					
					PersonInfoList.add(pi);
					
					fep = new FaceEmpPhoto();
					fep.setPersonID(id);
					fep.setPhotoUrl(filePath);
					faceEmpPhotoList.add(fep);
					lock.unlock();
			}
		}
		PrimeNumbersTask.setNumber(PrimeNumbersTask.getNumber() + 1);
		image = null;
		countDownLatch.countDown();
//		System.out.println("启动线程 = " + Thread.currentThread().getName() + " = " + id);
//		System.out.println("启动线程结束 = " + countDownLatch.getCount());
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		photoStorage();
	}
	public synchronized static CopyOnWriteArrayList<PersonInfo> getPersonInfoList() {
		return PersonInfoList;
	}
	public synchronized static CopyOnWriteArrayList<FaceEmpPhoto> getFaceEmpPhotoList() {
		return faceEmpPhotoList;
	}
	
}
