package httpFrame.http;

import tools.MyString;
import view.MainIntfaceView;

/**
 * 这是一个http接口类的参考示例；
 * 
 * @author yangdang
 *
 */
public class Example extends HttpCmd {
	static {
		//注册接口
		HttpCmd.register("URl方法名", Example.class);
	}
	
	@Override
	/*
	 * 接口业务逻辑处理方法
	 */
	public void execute() {
		//接收到的请求数据
		String requestStr = getRequestJsonStr();
		//面板日志框写日志
		MainIntfaceView.writeLogsTextArea("[RequestURL] " + requestUrl, requestStr);
		if (MyString.isJsonStr(requestStr)) {
			doSomething();
		}
		
		//响应请求；requestStr未需要响应的字符串 
		response(requestStr);
		
		MainIntfaceView.writeLogsTextArea("", "[返回数据：]" + requestStr);
	}

	private void doSomething() {
		// TODO Auto-generated method stub
		
	}
}
