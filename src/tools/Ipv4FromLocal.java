package tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class Ipv4FromLocal {
//	  private static String line;

	/**
     * 获取本地所有IP地址
     * @return IP地址
     */
    public List<String> getIpv4FromLocal() {
    	List<String> ipList = null;
        InputStream is = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        String line = null;
        try {
            Process process = Runtime.getRuntime().exec("cmd.exe /c ipconfig | findstr IPv4");
            is = process.getInputStream();
            isr = new InputStreamReader(is);
            br = new BufferedReader(isr);
        	line = br.readLine();
        	ipList = new ArrayList<String>();
            while(null != line) {
            	ipList.add(line.substring(line.indexOf(':') + 1).trim());
            	line = br.readLine();
            }
            
        } catch (IOException e) {
        	e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
                if (isr != null) {
                    isr.close();
                }
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
            }
        }
        return ipList;
    }
    /**
     * 获取本机所有IP地址，返回想要的目的ip
     * 例如：ip = 192.168.1.20  则 element  = 192.168.1.
     * @param element
     * @return
     */
    public String getIpv4ListContainsElement(String element) {
    	List<String> ipList = getIpv4FromLocal();
    	String ip = null;
    	if (null != ipList && !ipList.isEmpty()) {
    		for(String temp : ipList) {
        		if (temp.contains(element)) {
        			ip = temp;
        		}
        	}
    	}
    	return ip;
    }
    
    /**
     * 查看本机某端口是否被占用
     * @param port  端口号
     * @return  如果被占用则返回true，否则返回false
     */
    public boolean isLoclePortUsing(int port){
        boolean flag = false;
        try{
            flag = isPortUsing("127.0.0.1", port);
        }catch (Exception e){
//            e.printStackTrace();
        }
        return flag;
    }
 
    /**
     * 根据IP和端口号，查询其是否被占用
     * @param host  IP
     * @param port  端口号
     * @return  如果被占用，返回true；否则返回false
     * @throws UnknownHostException    IP地址不通或错误，则会抛出此异常
     */
    public boolean isPortUsing(String host, int port) throws UnknownHostException {
        boolean flag = false;
        InetAddress theAddress = InetAddress.getByName(host);
        try{
            Socket socket = new Socket(theAddress, port);
            flag = true;
            socket.close();
        } catch (IOException e) {
            //如果所测试端口号没有被占用，那么会抛出异常，这里利用这个机制来判断
            //所以，这里在捕获异常后，什么也不用做
        }
        return flag;
    }
}