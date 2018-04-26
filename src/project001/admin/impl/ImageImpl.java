package project001.admin.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.web.multipart.MultipartFile;

import project001.admin.entity.Image;



public class ImageImpl extends HibernateConfig {
	public static Image getByUuid(String uuid) {
		Session session = ImageImpl.getSession();
		Query query = session.createQuery("from Image where tagUuid=?");
		query.setString(0, uuid);
		return (Image)query.getSingleResult();
	}
	public static Map<String,String> upload(String path,String relativePath,MultipartFile mFile,String fileName) throws IOException {
		Map<String,String> map = new HashMap<String,String>();
		String[] pointer = mFile.getOriginalFilename().split("\\.");
		int len = pointer.length;
		String fileType = null;
		if(len >1){
			fileType = pointer[len-1].toLowerCase();
		}

		if(fileType != null && (fileType.equals("png") || fileType.equals("jpg") || fileType.equals("gif") || fileType.equals("jpeg"))){
			File filePath = new File(path+relativePath);
			//检查目录是否存在
			if(!filePath.exists()) {
				filePath.mkdirs();
			}
			
			//如何存在其他后缀文件重名,则删之.
			String[] fileTypes = {"png","jpg","gif","jpeg"};
			for(int i=0;i<fileTypes.length;i++){
				if(!fileType.equals(fileTypes[i])){
					File tempFile = new File(path+relativePath+fileName+"."+fileTypes[i]);
					if(tempFile.exists()) {
						tempFile.delete();
					}
				}
			}
			
			//生成文件名
			File file = new File(path+relativePath+fileName+"."+fileType);
			FileOutputStream fileOutputStream = new FileOutputStream(file);
			fileOutputStream.write(mFile.getBytes());
			fileOutputStream.close();

			map.put("status", "1");
			map.put("src", relativePath+fileName+"."+fileType);
		}else {
			map.put("info", "上传失败,仅支持jpg,jpeg,gif,png格式文件");
			map.put("status", "0");
		}
		return map;
	}
}
