package mySocketClient.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import javax.swing.JOptionPane;

import tools.MyString;


public class CommandSocket implements Runnable{
	private static int sendPort = 18111,receivePort = 18502;
	private static DatagramSocket ds = null;
	private static DatagramPacket sendDatagramPacket = null ,receiveDatagramPacket = null;
	private static byte[] bytes = null;
	private static String receiveData = null;
	public static String getReceiveData() {
		return receiveData;
	}

	public CommandSocket()  {
		try {
			ds = new DatagramSocket(receivePort);
			
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "端口("+receivePort+")被占用，无法发送udp请求", "D20设备通讯", JOptionPane.ERROR_MESSAGE);
//			e.printStackTrace();
		}
	}
	/**
	 * 多久无返回打断接受
	 * @param time
	 * @return
	 */
	public static boolean isNotReceiveData(int time) {
		int times = 100;
		boolean isbreak = false;
		while(time>0) {
			try {
				Thread.sleep(times);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
			}
//			System.out.println("3s="+receiveData);
			if(receiveData == null ||receiveData.length() < 4 ) {
				time -= times;
			}else {
				isbreak = true;
				break;
			}
		}
		return isbreak;
	}
	/**
	 * 发送数据
	 * @param ip	IP地址
	 * @param data	字符串
	 */
	public void SendDatagram (String ip,String data) {
		try {
			
			bytes = new MyString().hexStringToBytes(data);
			sendDatagramPacket = new DatagramPacket(bytes, bytes.length, InetAddress.getByName(ip), sendPort);
			
			ds.send(sendDatagramPacket);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "发送协失败："+e.getMessage(), "D20设备通讯", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		
	}
	//接受数据
	private void ReceiveDatagram (){
//		
		bytes = new byte[2048];
		receiveDatagramPacket = new DatagramPacket(bytes, bytes.length);
		try {
			receiveData = null;
			ds.receive(receiveDatagramPacket);
//			setReceiveData(true);
			receiveData = new MyString().getBytesToHexString(receiveDatagramPacket.getData(), 0, receiveDatagramPacket.getLength());
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
			ReceiveDatagram();
			
		}
	}
}
