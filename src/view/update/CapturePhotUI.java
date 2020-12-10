package view.update;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import javax.swing.JLabel;

import faceEngine.MyPhoto;
import view.MainIntfaceView;

/**
 * 如果设备上报陌生人记录，则更新抓拍区域
 * @author yangdang
 *
 */
public class CapturePhotUI {
	private List<JLabel> capturePhoto = null;
	private List<JLabel> captureTime = null;
	private List<HashMap<String, String>> queueList = new ArrayList<HashMap<String,String>>();
	private Queue<HashMap<String, String>> queue = null;
	private int queueSize = 12;
	public CapturePhotUI() {
		setDefaultQueue();
	}
	/**
	 * 更新队列，并更新抓拍UI
	 * @param map
	 */
	public void setQueueOffer(HashMap<String, String> map) {
		initsomething();
		//进一个出一个
		queue.offer(map);
		queue.poll();
		//更新抓拍区域
		updateCaptureRecordUI();
	}
	
	private void initsomething() {
		if (null == queue) {
			setDefaultQueue();
		}
		if (null == capturePhoto) {

			getCaptruePhotoSet();
		}
		if (null == captureTime) {

			getCaptureTimeSet();
		}
	}
	private void updateCaptureRecordUI() {
		// TODO Auto-generated method stub
		queueList.clear();
		for (HashMap<String, String> map : queue) {
			queueList.add(map);
		}
		for (int i = 0 ; i < queueSize ; i++) {
			if (null != queueList.get(queueSize - i - 1).get("photInDb")) {
				capturePhoto.get(i).setIcon((MyPhoto.getImageIconfromBASE64(queueList.get(queueSize - i - 1).get("capturePhoto"), 120, 105)));
				captureTime.get(i).setText(queueList.get(queueSize - i - 1).get("captureTime"));
			}
			
		}
		
	}
	
	private void setDefaultQueue() {
		queue = new  LinkedList<HashMap<String, String>>();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("capturePhoto", null);
		map.put("captureTime", null);
		for (int i = 0 ; i < queueSize; i++ ) {
			queue.offer(map);
		}
	}

	private List<JLabel> getCaptruePhotoSet() {
		capturePhoto = new ArrayList<JLabel>();
		capturePhoto.add(MainIntfaceView.getCapturePhuot_1());
		capturePhoto.add(MainIntfaceView.getCapturePhuot_2());
		capturePhoto.add(MainIntfaceView.getCapturePhuot_3());
		capturePhoto.add(MainIntfaceView.getCapturePhuot_4());
		capturePhoto.add(MainIntfaceView.getCapturePhuot_5());
		capturePhoto.add(MainIntfaceView.getCapturePhuot_6());
		capturePhoto.add(MainIntfaceView.getCapturePhuot_7());
		capturePhoto.add(MainIntfaceView.getCapturePhuot_8());
		capturePhoto.add(MainIntfaceView.getCapturePhuot_9());
		capturePhoto.add(MainIntfaceView.getCapturePhuot_10());
		capturePhoto.add(MainIntfaceView.getCapturePhuot_11());
		capturePhoto.add(MainIntfaceView.getCapturePhuot_12());
		return capturePhoto;
	}
	
	private List<JLabel> getCaptureTimeSet() {
		captureTime = new ArrayList<JLabel>();
		captureTime.add(MainIntfaceView.getCaptureTime_1());
		captureTime.add(MainIntfaceView.getCaptureTime_2());
		captureTime.add(MainIntfaceView.getCaptureTime_3());
		captureTime.add(MainIntfaceView.getCaptureTime_4());
		captureTime.add(MainIntfaceView.getCaptureTime_5());
		captureTime.add(MainIntfaceView.getCaptureTime_6());
		captureTime.add(MainIntfaceView.getCaptureTime_7());
		captureTime.add(MainIntfaceView.getCaptureTime_8());
		captureTime.add(MainIntfaceView.getCaptureTime_9());
		captureTime.add(MainIntfaceView.getCaptureTime_10());
		captureTime.add(MainIntfaceView.getCaptureTime_11());
		captureTime.add(MainIntfaceView.getCaptureTime_12());
		return captureTime;
	}
}
