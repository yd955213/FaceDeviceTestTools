package httpFrame.http;

import com.google.gson.Gson;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import httpFrame.Command;
import httpFrame.Factory;
import myGson.NumberJsonSerializerString;
import view.MainIntfaceView;
import org.json.JSONObject;
import java.io.*;
import java.net.URI;
import java.net.URLDecoder;
import java.util.*;

public abstract class HttpCmd extends Command {
    public Gson gson = new NumberJsonSerializerString().create();
    public String result = null;
    public static final String SERVERAPI = "/ServerApi/";
    public static final String DEVAPI = "/DevApi/";
    public HttpExchange http;
    public String cmd_id;
    public String method;
    public String params_string;
    protected byte[] params_bytes;
    public String requestUrl = null;
    public void start() {
        super.start();
    }

    @Override
    public void execute() {
        response("not Override execute");
    }

    protected void response() {
    	responseJSonUtf_8(result);
    }

    protected boolean getRequest_OK(JSONObject jsonObject) {
		boolean insert = false;
		if ("0".equals(jsonObject.get("Code"))) {
			insert = true;
		}
		return insert;
	}
    
    
    protected void response_gbk() {
        response_gbk(result);
    }

    public void responseJSonUtf_8(String resultJson) {
        MainIntfaceView.writeLogsTextArea(null, "SendData："+resultJson);
        response(resultJson);
    }

    public void response_gbk(Map<String, Object> result) {
//        result.put("cmd", cmd_id + "/response");
        String resultString = new JSONObject(result).toString();
        response_gbk(resultString);
    }
    /**
     * json格式返回
     * @param result
     */
    protected void response(String result) {
        try {
//            MainIntfaceView.writeLogsTextArea(null, "返回数据："+result);
            
            byte[] bytes = result.getBytes("utf-8");
            Headers responseHeaders = http.getResponseHeaders();
            responseHeaders.set("Content-type", "application/json; charset=utf-8");
            responseHeaders.set("Access-Control-Allow-Origin", "*");
            OutputStream os = http.getResponseBody();
            http.sendResponseHeaders(200, bytes.length);
            os.write(bytes);
            os.flush();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
    /**
     * 键值对 格式返回
     * @param result
     */
    protected void responseUrlencoded(String result) {
        try {
//            MainIntfaceView.writeLogsTextArea(null, "返回数据："+result);
            
            byte[] bytes = result.getBytes("utf-8");
            Headers responseHeaders = http.getResponseHeaders();
            responseHeaders.set("Content-type", "application/x-www-form-urlencoded; charset=utf-8");
            responseHeaders.set("Access-Control-Allow-Origin", "*");
            OutputStream os = http.getResponseBody();
            http.sendResponseHeaders(200, bytes.length);
            os.write(bytes);
            os.flush();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
    
    
    protected void response_gbk(String result) {
        try {
            byte[] bytes = result.getBytes("GBK");
            Headers responseHeaders = http.getResponseHeaders();
            responseHeaders.set("Content-type", "text/plain; charset=utf-8");
            responseHeaders.set("Access-Control-Allow-Origin", "*");
            OutputStream os = http.getResponseBody();
            http.sendResponseHeaders(200, bytes.length);
            os.write(bytes);
            os.flush();
        } catch (Throwable e) {
           e.printStackTrace();
        }
    }

    
    /**
     * 请求头数据
     *
     * @param head
     * @return
     */
    public String getHead(String head) {
        return http.getRequestHeaders().getFirst(head);
    }

    /**
     * 请求头数据
     *
     * @param head
     * @return
     */
    public List<String> getHeads(String head) {
        return http.getRequestHeaders().get(head);
    }

    public Object getParameter(String s) {
        URI uri = http.getRequestURI();
        try {
            Map<String, Object> map = parseQuery(uri.getQuery());
            if (map != null) {
                return map.get(s);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Map<String, Object> getParamMap() {
        URI uri = http.getRequestURI();
        try {
            Map<String, Object> map = parseQuery(uri.getQuery());
            if (map == null) {
                map = new HashMap<String, Object>();
            }
            return map;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return new HashMap<String, Object>();
    }

    @SuppressWarnings("unchecked")
	private Map<String, Object> parseQuery(String query) throws UnsupportedEncodingException {
        Map<String, Object> parameters = null;
        if (query != null) {
            parameters = new HashMap<String, Object>();
            String pairs[] = query.split("[&]");
            String[] param = null;
            String key = null;
            String value = null;
            List<String> values = null;
            for (String pair : pairs) {
                param = pair.split("[=]");
                if (param.length > 0) {
                    key = URLDecoder.decode(param[0], System.getProperty("file.encoding"));
                }
                if (param.length > 1) {
                    value = URLDecoder.decode(param[1], System.getProperty("file.encoding"));
                }
                if (parameters.containsKey(key)) {
                    Object obj = parameters.get(key);
                    if (obj instanceof List<?>) {
                        values = (List<String>) obj;
                        values.add(value);
                    } else if (obj instanceof String) {

                        values = new ArrayList<String>();
                        values.add((String) obj);
                        values.add(value);
                        parameters.put(key, values);
                    }
                } else {
                    parameters.put(key, value);
                }
            }
        }
        return parameters;
    }

    /**
     * 正文数据
     *
     * @return
     */
    public byte[] readBytes() {
        try {
            if (params_bytes != null)
                return params_bytes;
            byte[] bs = new byte[1024 * 1024 * 3];
            int count = 0;
            int len = 500;
            int off = 0;
            InputStream input = http.getRequestBody();
            while ((count = input.read(bs, off, len)) > -1) {
                off += count;
            }
            return Arrays.copyOfRange(bs, 0, off);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }

    static byte[] trunkBuff = new byte[10485760];
    static int len = 0;
    static int pos = 0;
    static int markLen = 0;
    static int markPos = 0;

    static byte[] recvBuff = new byte[8192];
    /**
     * 获取Ip
     * @return
     */
    public String getServerIpInfo() {
    	return http.getRemoteAddress().toString().replaceAll("/", "").split(":")[0];
    }
    /**
     * 获取端口
     * @return
     */
    public String getServerProtIpInfo() {
    	return http.getRemoteAddress().toString().replaceAll("/", "").split(":")[1];
    }
    
    /**
     * 正文数据
     *
     * @return
     */
    public byte[] readTrunk() {
        try {
            if (params_bytes != null)
                return params_bytes;
            len = 0;
            pos = 0;
            markLen = 0;
            markPos = 0;
            InputStream is = http.getRequestBody();
            int read_len = 0;
            while (true) {
                read_len = is.read(recvBuff);
                if (read_len == -1) {
                    //连接断开
                    params_bytes = "-1".getBytes();
                    break;
                }
                //recvBuff 追加到 trunkBuff
                System.arraycopy(recvBuff, 0, trunkBuff, pos + len, read_len);
                len += read_len;
            }
            //params_bytes = sb.toString().getBytes();
            return params_bytes;
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 正文数据
     *
     * @return
     */
    public byte[] readFileBytes() {
        try {
            if (params_bytes != null)
                return params_bytes;
            InputStream is = http.getRequestBody();
            ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
            byte[] buff = new byte[100];
            int rc = 0;
            while ((rc = is.read(buff, 0, 100)) > 0) {
                swapStream.write(buff, 0, rc);
            }
            params_bytes = swapStream.toByteArray();

            return params_bytes;
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 正文数据
     *
     * @return
     */
    public String readString() {
        try {
            if (params_string != null)
                return params_string;
            InputStream is = http.getRequestBody();
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "utf-8"));
            StringBuffer sb = new StringBuffer();
            String s;
            while ((s = br.readLine()) != null) {
                sb.append(s);
            }
            
//			MainIntfaceView.writeLogsTextArea(null, "[ReceiveData]"+sb.toString());
            params_string = sb.toString();
//            System.out.println("params_string = " + params_string);
            return params_string;
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 获取数据
     *
     * @return
     */
    protected Map<String, String> getParams() {
        String params_string = readString();
        Map<String, String> map = new HashMap<String, String>();
        if (params_string == null || params_string.equals(""))
            return map;
        String[] key_values_string = params_string.split("&");

        String key = null;
        String value = null;
        for (String key_values : key_values_string) {
            int i = key_values.indexOf("=");
            key = key_values.substring(0, i);
            value = key_values.substring(i + 1);
            map.put(key, value);
        }
        return map;
    }

    protected Map<String, String> getDecodeParams() throws UnsupportedEncodingException {
        String params_string = readString().trim();
        Map<String, String> map = new HashMap<String, String>();
        if (params_string == null || params_string.equals(""))
            return map;
        String[] key_values_string = params_string.split("&");
        String key = null;
        String value = null;
        int i = 0;
        for (String key_values : key_values_string) {
            i = key_values.indexOf("=");
            key = key_values.substring(0, i);
           value = URLDecoder.decode(key_values.substring(i + 1), "utf-8");
            map.put(key, value);
        }
        return map;
    }

    /**
     * 获取数据
     *
     * @return
     */
    public String getRequestJsonStr() {
        return readString();
    }

    protected static Map<String, HttpCmdFactory> factorys = new HashMap<String, HttpCmdFactory>();

    public static void register(String name, Class<? extends HttpCmd> clazz) {
        HttpCmdFactory httpCmdFactory = new HttpCmdFactory(clazz);
        factorys.put(name, httpCmdFactory);
        
//        Iterator<Entry<String, HttpCmdFactory>> iterator = factorys.entrySet().iterator();
//        while (iterator.hasNext()) {
//			Map.Entry<String, HttpCmdFactory> entry = iterator.next();
//			if ("/servers/update_local_database" .equals(entry.getKey()))
//				System.out.println(entry.getKey() + "  " + entry.getValue());
//		}
    }

    public static HttpCmd createInstance(String cmd_id) {
        HttpCmdFactory factory = factorys.get(cmd_id);
        if (factory == null)
            return null;
        HttpCmd cmd = factory.createInstance();
        cmd.name = cmd_id;
        cmd.cmd_id = cmd_id;
        return cmd;
    }

    public static Map<String, HttpCmdFactory> getFactorys() {
        return factorys;
    }

    public static HttpCmdFactory getCmdFactory(String name) {
        return factorys.get(name);
    }

}

class HttpCmdFactory extends Factory<HttpCmd> {
    public HttpCmdFactory(Class<? extends HttpCmd> clazz) {
        super(clazz);
    }
    
    
}
