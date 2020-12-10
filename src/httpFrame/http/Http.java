package httpFrame.http;

import httpFrame.Server;

public class Http {
	private static int port = 19000;
	private static String serverIP ;
	public static void setPort(int port) {
		Http.port = port;
	}
	public static int getPort() {
		return port;
	}
	private static HttpServer httpserver  = null;
	public static HttpServer getHttpserver() {
		return httpserver;
	}
	public Http() {
		try {	
			String user_dir = System.getProperty("user.dir"); 
//			System.out.println("user_dir = "+user_dir);
			httpFrame.JarLoader.load(user_dir + "/libs");
			httpFrame.JarLoader.addusr_paths(user_dir + "/libs");
			httpFrame.ClassLoader.loadClassesFromPath();

//			final HttpServer httpserver = new HttpServer(port);//http服务 监听19000端口
			httpserver = new HttpServer(serverIP, port);//http服务 监听19000端口
			httpserver.bootstrap();//启动

			//程序退出的清理工作
			Server.cleanShutdownHooks();
			Runtime.getRuntime().addShutdownHook(new Thread() {
				public void run() {
					try {
						httpserver.shutdown();
					} catch (Throwable e) {
						e.printStackTrace();
					}
				}
			});
		} catch (Throwable e) {
			e.printStackTrace();
//			System.exit(0);
		}
	}

	public Http(int port) {
		try {	
			String user_dir = System.getProperty("user.dir"); 
//			System.out.println("user_dir = "+user_dir);
			httpFrame.JarLoader.load(user_dir + "/libs");
			httpFrame.JarLoader.addusr_paths(user_dir + "/libs");
			httpFrame.ClassLoader.loadClassesFromPath();

//			final HttpServer httpserver = new HttpServer(port);//http服务 监听19000端口
			httpserver = new HttpServer(serverIP, port);//http服务 监听19000端口
			httpserver.bootstrap();//启动

			//程序退出的清理工作
			Server.cleanShutdownHooks();
			Runtime.getRuntime().addShutdownHook(new Thread() {
				public void run() {
					try {
						httpserver.shutdown();
					} catch (Throwable e) {
						e.printStackTrace();
					}
				}
			});
		} catch (Throwable e) {
			e.printStackTrace();
//			System.exit(0);
		}
	}
	public static String getServerIP() {
		return serverIP;
	}
	public static void setServerIP(String serverIP) {
		Http.serverIP = serverIP;
	}
	
}
