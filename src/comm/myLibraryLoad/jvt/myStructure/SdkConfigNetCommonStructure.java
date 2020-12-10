package comm.myLibraryLoad.jvt.myStructure;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;

public class SdkConfigNetCommonStructure extends Structure {
	public byte[] HostName = new byte[64];//!主机名
	public byte[] HostIP = new byte[4];//!主机IP	
	public byte[] Submask = new byte[4];//!子网掩码	
	public byte[] Gateway = new byte[4];//!网关IP	
	public int HttpPort;//!HTTP服务端口	
	public int TCPPort;	//!TCP侦听端口	
	public int SSLPort;//!SSL侦听端口	
	public int UDPPort;//!UDP侦听端口	
	public int MaxConn;//!最大连接数	
	public int MonMode;//!监视协议 {"TCP","UDP","MCAST",…}	
	public int MaxBps;//!限定码流值	
	public int TransferPlan;//!传输策略	
	public boolean bUseHSDownLoad;//!是否启用高速录像下载测率	
	public byte[] sMac = new byte[32];//!MAC地址
	public byte[] sSn = new byte[32]; 	///序列号
	public int ChannelNum;			///通道数                //by zjh 20170807
	public int	DeviceType;			///设备类型，如枚举DevType  //by zjh 20170807
	public byte[] szUserName = new byte[64];		//设备用户名
	public byte[] szPassword = new byte[64];		//设备密码
	public byte[] szKernelVersion = new byte[64];		//内核版本
	public byte[] szWebVersion = new byte[64];	//页面版本
	public byte[] szIPV6 = new byte[64];//单片机版本
	public byte[] szAlgorithmVersion = new byte[64];	//算法版本
	public byte[] szModelVersion = new byte[64];		//模型版本	
	public byte[] szCustomer = new byte[64];
//		public Ipbyte.ByReference  SubIP = new Ipbyte.ByReference();;//!副IP
	public byte[]  SubIP = new byte[4];//!副IP
	
	
	public static class ByReference extends SdkConfigNetCommonStructure implements Structure.ByReference {}
    public static class ByValue extends SdkConfigNetCommonStructure implements Structure.ByValue {}
    
    protected List<String> getFieldOrder() {
    	return Arrays.asList(new String[] { 
    			"HostName", 
    			"HostIP", 
    			"Submask", 
    			"Gateway", 
    			"HttpPort", 
    			"TCPPort", 
    			"SSLPort", 
    			"UDPPort", 
    			"MaxConn", 
    			"MonMode", 
    			"MaxBps", 
    			"TransferPlan", 
    			"bUseHSDownLoad",
    			"sMac", 
    			"sSn", 
    			"ChannelNum", 
    			"DeviceType", 
    			"szUserName", 
    			"szPassword", 
    			"szKernelVersion", 
    			"szWebVersion", 
    			"szIPV6", 
    			"szAlgorithmVersion", 
    			"szModelVersion", 
    			"szCustomer", 
    			"SubIP"});
    }

}
