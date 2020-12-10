package view.update;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import javax.swing.JLabel;

import faceEngine.MyPhoto;
import view.MainIntfaceView;

public class RecordUI{
	private Queue<HashMap<String, String>> queue = null;
	private int queueSize = 5;
	private List<JLabel> photoInDB = null;
	private List<JLabel> capturePhoto = null;
	private List<JLabel> similarityScore = null;
	private List<HashMap<String, String>> queueList = new ArrayList<HashMap<String,String>>();
	/*
	 * 单例模式之静态内部类
	 */
	private RecordUI() {
		
	}
	
	private static class RecordHolder {
		public static RecordUI instance = new RecordUI();
	}
	
	public static RecordUI getInstance() {
		return RecordHolder.instance;
	}
	
	public void setQueueOffer(HashMap<String, String> map) {
		
		init();
		//进一个出一个
		queue.offer(map);
		queue.poll();
		updateCaptureRecordUI();
	}
	
	private void init() {

		if (null == capturePhoto) {
			getCaptruePhotoSet();
		}
		if (null == queue) {
			setDefaultQueue();
		}
	}
	private void updateCaptureRecordUI () {
		queueList.clear();
		for(HashMap<String, String> map :queue) {
			queueList.add(map);
		}
//		System.out.println("queueList = " + queueList.size());
		for (int i = 0 ; i < queueSize ; i++) {
//			System.out.println("照片"+i+" = " + queueList.get(queueSize - i - 1).get("capturePhoto"));
			if (null != queueList.get(queueSize - i - 1).get("capturePhoto")) {
				photoInDB.get(i).setIcon(MyPhoto.getImageIconfromBASE64(queueList.get(queueSize - i - 1).get("photInDb"), 120, 105));
				similarityScore.get(i).setText(String.format("%.2f", Double.parseDouble(queueList.get(queueSize - i - 1).get("similarityScore"))));
				capturePhoto.get(i).setIcon(MyPhoto.getImageIconfromBASE64(queueList.get(queueSize - i - 1).get("capturePhoto"), 120, 105));
			}
		}
	}
	
	private void getCaptruePhotoSet() {
		
		photoInDB = new ArrayList<JLabel>();
		photoInDB.add(MainIntfaceView.recordPersonInBaseDataButton_1);
		photoInDB.add(MainIntfaceView.recordPersonInBaseDataButton_2);
		photoInDB.add(MainIntfaceView.recordPersonInBaseDataButton_3);
		photoInDB.add(MainIntfaceView.recordPersonInBaseDataButton_4);
		photoInDB.add(MainIntfaceView.recordPersonInBaseDataButton_5);
		
		capturePhoto = new ArrayList<JLabel>();
		capturePhoto.add(MainIntfaceView.capturePhotoButton_1);
		capturePhoto.add(MainIntfaceView.capturePhotoButton_2);
		capturePhoto.add(MainIntfaceView.capturePhotoButton_3);
		capturePhoto.add(MainIntfaceView.capturePhotoButton_4);
		capturePhoto.add(MainIntfaceView.capturePhotoButton_5);
		
		similarityScore = new ArrayList<JLabel>();
		similarityScore.add(MainIntfaceView.similarityScoreaLabel_1);
		similarityScore.add(MainIntfaceView.similarityScoreaLabel_2);
		similarityScore.add(MainIntfaceView.similarityScoreaLabel_3);
		similarityScore.add(MainIntfaceView.similarityScoreaLabel_4);
		similarityScore.add(MainIntfaceView.similarityScoreaLabel_5);
	}
	
	private void setDefaultQueue() {
		queue = new  LinkedList<HashMap<String, String>>();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("photInDb", null);
		map.put("similarityScore", null);
		map.put("capturePhoto", null);
		for (int i = 0 ; i < queueSize; i++ ) {
			queue.offer(map);
		}
	}
	public Queue<HashMap<String, String>> getQueue() {
		
		return queue;
	}
}
