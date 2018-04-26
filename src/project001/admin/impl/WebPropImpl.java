package project001.admin.impl;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import sun.misc.BASE64Encoder;

public class WebPropImpl {
	//读取
	public static Map<String,String> get(String path) throws IOException {
		Map<String,String> propMap = new HashMap<String,String>();
		Properties prop = new Properties();
		InputStream in = new BufferedInputStream(new FileInputStream(path));
		prop.load(in);     ///加载属性列表
        Iterator<String> it=prop.stringPropertyNames().iterator();
        while(it.hasNext()){
            String key=it.next();
            propMap.put(key, new String(prop.getProperty(key).getBytes("ISO-8859-1"),"utf-8"));
        }
        in.close();
        return propMap;
	}
	//写入
	public static void save(Map<String,String> map,String path) throws IOException {
		Properties prop = new Properties();
		FileOutputStream oFile = new FileOutputStream(path, true);
		Iterator<Map.Entry<String, String>> entries = map.entrySet().iterator();
		
		while(entries.hasNext()) {
			Map.Entry<String, String> m = entries.next();
			prop.setProperty(m.getKey(), m.getValue());
		}
		
		prop.store(new OutputStreamWriter(oFile, "utf-8"), "The New properties file");
        oFile.close();
	}
	public static void main(String[] args) throws IOException {
		/*Map<String,String> propMap = new HashMap<String,String>();
		propMap.put("title", "Mr Ra11o");
		propMap.put("age", "26");
		propMap.put("from", "广东");
		WebPropImpl.save(propMap);*/
		//System.out.println(Thread.currentThread().getContextClassLoader().getResource("base.properties").getPath());
		//Map<String,String> m = WebPropImpl.get();
		//System.out.println(m.get("title"));
		/*String src = "https://ss1.baidu.com/6ONXsjip0QIZ8tyhnq/it/u=3536362456,2286913285&fm=173&s=46F17ADA4E061ED886058E410300B0F4&w=218&h=146&img.JPEG";
		BASE64Encoder encoder = new BASE64Encoder();
        String encode = encoder.encode(src.getBytes());
        System.out.println("encode: " + encode);*/

		System.out.println(System.getProperty("user.dir")+"\\base.properties");
	}
}
