package httpFrame.http;

import view.MainIntfaceView;
import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import httpFrame.Server;

//import javax.swing.JOptionPane;

public class HttpServer extends Server {
	public HttpServer(String serverIP, int port) {
		super(serverIP, port);
	}
//	public HttpServer(int port) {
//		super(port);
//	}
	protected com.sun.net.httpserver.HttpServer server;
	
	protected ExecutorService executor = Executors.newCachedThreadPool();//线程池
	private boolean portIsUsed = false;
	public void bootstrap() {
		try {
			if (null == serverIP) {
				serverIP = "127.0.0.1";
			}
			server = com.sun.net.httpserver.HttpServer.create(new InetSocketAddress(serverIP, port), 0);//http服务
			server.setExecutor(executor);//设置线程池
			server.createContext("/", new HttpHandler());//设置上下文,以及每个http请求的处理过程
			server.start();
			MainIntfaceView.writeLogsTextArea(null, "HttpServer bind: " + port);
		} catch (Throwable e) {
			e.printStackTrace();
			portIsUsed = true;
//			JOptionPane.showMessageDialog(null, "端口 "+port+" 被占用、请关闭占用该端口的软件或者在系统设置界面更换端口号!!!");
			shutdown();
		}
	}

	public void shutdown() {
		
		if (!portIsUsed) {
			server.stop(0);
			executor.shutdown();
			MainIntfaceView.writeLogsTextArea(null, "HttpServer unbind: " + port);
		}
		
	}

}
