package httpFrame;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.Iterator;

import httpFrame.http.Http;

/**
 * 服务抽象
 *
 */
public abstract class Server {

	/**
	 * 配置文件地址
	 */
//	public static final String server_xml = System.getProperty("user.dir") + "/config/server.xml";
	/**
	 * 端口
	 */
	public static int port = Http.getPort();

	public static String serverIP = Http.getServerIP();
	public Server(String serverIP, int port) {
		Server.port = port;
		Server.serverIP = serverIP;
	}
//	public Server( int port) {
//		Server.port = port;
//	}
	/**
	 * 启动
	 */
	public abstract void bootstrap();

	/**
	 * 关闭
	 */
	public abstract void shutdown();

	public static void cleanShutdownHooks() {
		try {
			Field hooks = Class.forName("java.lang.ApplicationShutdownHooks").getDeclaredField("hooks");
			hooks.setAccessible(true);
			IdentityHashMap<?, ?> ShutdownHooks = IdentityHashMap.class.cast(hooks.get(null));
			Iterator<?> it = new ArrayList<Object>(ShutdownHooks.values()).iterator();
			for (Object o = it.next(); it.hasNext(); o = it.next()) {
				try {
					Runtime.getRuntime().removeShutdownHook(Thread.class.cast(o));
				} catch (Throwable e) {
					e.printStackTrace();
				}
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

}
