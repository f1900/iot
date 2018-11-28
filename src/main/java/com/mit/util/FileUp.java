package com.mit.util;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;
import org.apache.commons.fileupload.util.FileItemHeadersImpl;
//import org.apache.commons.lang3.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

//import com.hitopsports.service.impl.UserServiceImpl;
@Component
public class FileUp {
	private String path = System.getenv("TOMCAT_HOME")+"/webapps/ps";
	private Logger logger = LoggerFactory.getLogger(FileUp.class);
	private File temp = new File("D:/file/temp");

	public List<String> up(HttpServletRequest request,String url) {

		// 判断是否是多数据段提交格式
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		List<String> list=new ArrayList<String>();
		if (isMultipart) {
			DiskFileItemFactory factory = new DiskFileItemFactory();
			// 设置file的下限
//			factory.setSizeThreshold(4096);
			// 设置中转目录
			factory.setRepository(temp);


			ServletFileUpload upload = new ServletFileUpload(factory);
			
//			upload.setHeaderEncoding("UTF-8");
			// 设置上传file的上限
//			upload.setSizeMax(1000000 * 20);
			try {
				List<FileItem> fileItems = upload.parseRequest(new ServletRequestContext(request));
				for (Iterator<FileItem> iter = fileItems.iterator(); iter.hasNext();) {
					FileItem item = iter.next();
					// 是普通的表单输入域
					if (item.isFormField()) {
						logger.info(item.getFieldName() + "--text--" +item.getString("UTF-8") );
					}else {
						String name = item.getName();//file名
						logger.info(item.getFieldName()+"--file--"+name);
						long size = item.getSize();
						if ((name == null || name.equals("")) && size == 0) {
							continue;
						}
						File dir=new File(path+url);
						if(!dir.exists()){
							dir.mkdirs();
						}
						File file=new File(dir, name);
						int i=0;
						while(file.exists()){
							file=new File(dir,name.substring(0, name.lastIndexOf("."))+"+"+i+name.substring(name.lastIndexOf(".")));
							i++;
						}
						item.write(file);
						list.add(url+"/"+name);
					}
				}
			} catch (FileUploadException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			logger.info("请求错误");
		}
		return list;
	}
	public Map<String, String> field(HttpServletRequest request,List<FileItem> fileItems){
		Map<String, String> map=new HashMap<String, String>();
		FileItemFactory factory = new DiskFileItemFactory();
		FileUpload upload = new ServletFileUpload(factory);
		upload.setHeaderEncoding("UTF-8");
		// 判断是否是多数据段提交格式
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (isMultipart) {
			try {
				List<FileItem> items = upload.parseRequest(new ServletRequestContext(request));
				for(FileItem item:items){
					if (item.isFormField()) {
						logger.info(item.getFieldName()+"==="+item.getString("utf-8"));
						map.put(item.getFieldName(), item.getString("utf-8"));
					}else {
						logger.info(item.getName());
						fileItems.add(item);
					}
				}
			} catch (FileUploadException | UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}else {
			logger.info("请求错误");
		}
		return map;
	}
	
	public List<String> up(List<FileItem> fileItems,String url){
		List<String> list =new ArrayList<String>();
		try {
			for (Iterator<FileItem> iter = fileItems.iterator(); iter.hasNext();) {
				FileItem item = iter.next();
				// 是普通的表单输入域
				if (item.isFormField()) {
					logger.info(item.getFieldName() + "--text--" +item.getString("UTF-8") );
				}else {
					String name = item.getName();//file名
					logger.info(item.getFieldName()+"--file--"+name);
					long size = item.getSize();
					if ((name == null || name.equals("")) && size == 0) {
						continue;
					}
					File dir=new File(path+url);

					if(!dir.exists()){
						dir.mkdirs();
					}
					File file=new File(dir, name);
					while(file.exists()){
						name=Calendar.getInstance().getTime().getTime()+name.substring(name.lastIndexOf("."));
						file=new File(dir,name);
					}
					item.write(file);
					list.add(url+"/"+name);
				}
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return list;
	}
	public String up(FileItem fileItem,String url,String name){
//		List<String> list =new ArrayList<String>();
		try {
//			for (Iterator<FileItem> iter = fileItems.iterator(); iter.hasNext();) {
//				FileItem item = iter.next();
				// 是普通的表单输入域
				if (fileItem.isFormField()) {
					logger.info(fileItem.getFieldName() + "--text--" +fileItem.getString("UTF-8") );
				}else {
//					String name = item.getName();//file名
					logger.info(fileItem.getFieldName()+"--file--"+name);
					if (fileItem.getSize() == 0) {
						return "";
					}
					File dir=new File(path+url);
					if(dir.exists()){
						dir.delete();
					}
					dir.mkdirs();
					File file=new File(dir, name);
//					if(file.exists()){
//						file.delete();
//					}
					fileItem.write(file);
					return url+"/"+name;
				}
//			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return null;
	}
	
	
	public boolean del(String url) {
		File file=new File(path+url);
		if (file.exists()) {
			return file.delete();
		}
		return false;
	}
}
