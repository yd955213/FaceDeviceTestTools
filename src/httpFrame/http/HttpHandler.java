package httpFrame.http;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import httpFrame.http.dasApi.DownLoadApk;
import view.MainIntfaceView;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

public class HttpHandler implements com.sun.net.httpserver.HttpHandler {
//	private String requestUrl = null;
	/**
	 * 
	 */
	private String x = "/";
	private final String headStr = "http:/";
	private final String methodStr = "\t" + " [ method ] ";
	@Override
	public void handle(HttpExchange http) throws IOException {
		try {
			String uri = get2cmdid(http.getRequestURI().getPath());
			String cmd_id = uri;
			if (cmd_id == null || cmd_id.equals(""))
				return;
			
			//创建对象: 注册了该接口的实例
//			System.out.println(SystemTimes.getSysTime() + "  收到请求 =" + http.getRequestURI() + "  cmd_id = " + cmd_id);
			HttpCmd cmd = HttpCmd.createInstance(cmd_id);	
			
			if (cmd == null) {
				http.sendResponseHeaders(400, 0);
			} else {
				//请求头信息
				String remoteAddress = http.getRemoteAddress().toString();
				String method = http.getRequestMethod();
				String requestURI = http.getRequestURI().toString();
				cmd.http = http;
				cmd.method = method;
				//执行接口业务逻辑代码
				cmd.requestUrl = headStr + remoteAddress + methodStr + requestURI;
				cmd.start();
			}
		} catch (Throwable e) {
			e.printStackTrace();
			MainIntfaceView.writeLogsTextArea(null, "Handler error"+e.getMessage() + " URL=" +  http.getRequestURI().toString());
//			System.out.println("Handler error:"+e.getMessage() + " URL=" +  http.getRequestURI().toString());
			responseError(http, e);
		} finally {
			try {
				http.close();
			} catch (Throwable e2) {
				e2.printStackTrace();
			}
		}
	}
	
	private String get2cmdid(String url) {
		//http
		if (url.endsWith(".jpg")) {
			String[] urls = url.split(x);
			url = "";
			for (int i = 1 ; i < urls.length -1; i++) {
				url += x + urls[i];
			}
			DownLoadApk.setDownLocalFile(".jpg");
			DownLoadApk.setFileName(urls[urls.length -1]);
		}else if (url.endsWith(".apk")) {
			String[] urls = url.split(x);
			url = "";
			for (int i = 1 ; i < urls.length -1; i++) {
				url += x + urls[i];
			}
			DownLoadApk.setDownLocalFile(".apk");
			DownLoadApk.setFileName(urls[urls.length -1]);
		}
		return url;
	}
	protected void responseError(HttpExchange http, Throwable t) {
		try {
			Headers responseHeaders = http.getResponseHeaders();
			responseHeaders.set("Content-type", "text/plain; charset=utf-8");
			responseHeaders.set("Access-Control-Allow-Origin", "*");

			OutputStream os = http.getResponseBody();

			http.sendResponseHeaders(200, 100000);

			t.printStackTrace(new PrintStream(os));
			os.flush();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
}
