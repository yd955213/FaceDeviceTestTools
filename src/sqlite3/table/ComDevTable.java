package sqlite3.table;

public class ComDevTable {
	public int devId;
	public int devUseId;
    public String devIsOnline; 	//是否在线 0：否， 1: 是
    public String devIsUsed;   //是否使用 0：否， 1: 是
    public String devIp;
    public String devGateIp ; //网关IP
    public String devMacIp;    //子网掩码
    public String devMacAddress;  //mac地址
    public int devPort;
    //dev_sever_ip 数据库中字段单词写错
    public String devServerIp;
    //dev_sever_port 数据库中字段单词写错
    public int devServerPort;
    public String devName;
    public String gmtCreate;  //创建时间
    public String gmtModified;  //修改时间
    
}
