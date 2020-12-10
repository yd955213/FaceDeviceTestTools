package tools;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class WriteFiles {
	private  FileOutputStream fos = null;
	private  BufferedOutputStream bos = null;
//	private static String fileName = null;
//	private byte[] bytes = null;
	private File file = null;
	private byte[] bytes;
	/**读写锁*/
	private ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();// 
	
	/**
	 * 判断文件是否存在
	 * @return
	 */
	private File getFileIsExists() {
//		this.fileName = fileName;
		String fileName = "./logs/" + SystemTimes.getSysTimeYYYYMMDD() + ".txt";
		file = new File(fileName.intern());
		file = createFile(file);
		return file;
	}
	
	/**
	 * 判断文件是否存在
	 * @return
	 */
	public File createFile(File file) {
		
		if  (!file.getParentFile().exists())  {
			file.getParentFile().mkdirs();
		}
		if(!file.exists()) {
			try {
					file.createNewFile(); 
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return file;
	}
	/**
	 * 判断文件是否存在
	 * @return
	 */
	public File createFoder(File file) {
		
		if  (!file.getParentFile().exists())  {
			file.mkdirs();
		}
		
		return file;
	}
	
	/**
	 * 
	 * @param msg
	 */
	public synchronized void writeFiles (String msg) {
		try {
//			if ( fos == null ) {
			//追加方式写文件，设置为false 为不追加
			fos = new FileOutputStream(getFileIsExists(), true);
//			}
			bos = new BufferedOutputStream(fos);
			bytes = msg.getBytes("utf-8");
			bos.write(bytes, 0, bytes.length);
			bos.flush();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}finally {
			try {
				bos.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
			try {
				fos.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
	}
	public void writeFiles (String filePath, String msg) {
		try {
			
			reentrantReadWriteLock.writeLock().lock();
			file = createFile(new File(filePath.intern()));
//			if ( fos.i) {
				//追加方式写文件，设置为false 为不追加
				fos = new FileOutputStream(file, true);
//			}
			bos = new BufferedOutputStream(fos);
			bytes = msg.getBytes("utf-8");
			bos.write(bytes, 0, bytes.length);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}finally {
			try {
				bos.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				bos.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
			try {
				fos.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
			reentrantReadWriteLock.writeLock().unlock();
		}
	}
	/**
	 * 错误写文件方法
	 * @param msg
	 */
	public  void writeFiles_Err (String msg) {
		try {
			//追加方式写文件，设置为false 为不追加
			fos = new FileOutputStream(getFileIsExists(), true);
			
			bos = new BufferedOutputStream(fos);
			bytes = msg.getBytes("utf-8");
			bos.write(bytes, 0, bytes.length);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}finally {
			try {
				bos.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				bos.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
			try {
				fos.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
	}
}
