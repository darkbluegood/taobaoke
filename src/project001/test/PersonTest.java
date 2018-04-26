package project001.test;

import java.io.InputStream;
import java.util.Iterator;
import java.util.Properties;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;


public class PersonTest {
	private long num;
	
	public long getNum() {
		return num;
	}

	public void setNum(long num) {
		this.num = num;
	}

	public static void main(String args[]){
		Properties prop = new Properties();
		try{
            //读取属性文件a.properties
            InputStream in = new BufferedInputStream(new FileInputStream("base.properties"));
            prop.load(in);     ///加载属性列表
            Iterator<String> it=prop.stringPropertyNames().iterator();
            while(it.hasNext()){
                String key=it.next();
                System.out.println(key+":"+new String(prop.getProperty(key).getBytes("ISO-8859-1"),"utf-8"));
            }
            in.close();
            
            ///保存属性到b.properties文件
            /*FileOutputStream oFile = new FileOutputStream("base.properties", false);//true表示追加打开
            prop.setProperty("phone", "10086");
            prop.store(oFile, "The New properties file");
            oFile.close();*/
        }
        catch(Exception e){
            System.out.println(e);
        }
	}
}
