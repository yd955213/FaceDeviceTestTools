package myProgressBar;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;

import tools.downLoadAuthority.DownLoadAuthorityStatu;

public class PrimeNumbersTask extends SwingWorker<Void, Integer>{
	private JProgressBar progressBar;
	private static int number = 0;
	private  int maxNum = 0;
	private JButton button;
//	private int tempNum = 0;
	/**
	 * 设置进度条
	 * @param button 点击的按钮，完成进度后按钮设置可使用
	 * @param progressBar 显示进度条
	 * @param maxNum 进度条最大值
	 * @param minNum 进度条最小值
	 */
	public PrimeNumbersTask(JButton button, JProgressBar progressBar, int maxNum, int minNum) {
		this.button = button;
		this.progressBar = progressBar;
		this.maxNum = maxNum;
		number = 0;
		progressBar.setMaximum(maxNum);
		progressBar.setMinimum(0);
	}
	@Override
	protected Void doInBackground() throws Exception {
		// TODO Auto-generated method stub
		while (number < maxNum) {
			publish(number);
			if (DownLoadAuthorityStatu.isWantBreak()) {
				button.setEnabled(true);
				break;
			}
		}
		return null;
	}

	@Override
	protected void process(List<Integer> chunks) {
		// TODO Auto-generated method stub
//		System.out.println("deaf = " + chunks.get(chunks.size() -1));
		progressBar.setValue(number);
	}
	@Override
	protected void done() {
		// TODO Auto-generated method stub
		button.setEnabled(true);
//		super.done();
	}
	
	public static  int getNumber() {
		return number;
	}
	
	public static void setNumber(int number) {
		PrimeNumbersTask.number = number;
	}
	
}
