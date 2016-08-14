package com.cuser.utils;

import java.io.File;
import java.io.IOException;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.xpath.XPath;

import com.cuser.model.JdbcInfo;

//从配置文件中读取JDBC数据库配置信息
public class JdbcConfigure {
         private static JdbcConfigure instance;
         private JdbcInfo jdbcInfo = new JdbcInfo();
         private static String CONFIG_FILE_NAME = "configure/global-configure.xml";
         private Element rootElt = null;
         
         
         public JdbcInfo getJdbcInfo() {
			return jdbcInfo;
		}
         
		private JdbcConfigure(){
			 SAXBuilder saxBuilder = new SAXBuilder();
			 Document document = null;
	         File configFile = new File(CONFIG_FILE_NAME);
	         
			try {
				document = saxBuilder.build(configFile);
				rootElt = document.getRootElement();
				init();
			} catch (JDOMException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
         }
         
         
		private void init(){
			
			try {
				Element driverElt = (Element) XPath.selectSingleNode(rootElt,"//jdbc-config/driver");
				jdbcInfo.setDriver(driverElt.getText());
				
				Element userElt = (Element) XPath.selectSingleNode(rootElt,"//jdbc-config/user");
				jdbcInfo.setUser(userElt.getText());
				
				Element passwordElt = (Element) XPath.selectSingleNode(rootElt,"//jdbc-config/password");
	            jdbcInfo.setPassword(passwordElt.getText());
				
				Element urlElt = (Element) XPath.selectSingleNode(rootElt,"//jdbc-config/url");
				jdbcInfo.setUrl(urlElt.getText());
				
				
			} catch (JDOMException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
		}
         
		public static synchronized JdbcConfigure getInstance(){
			  if (instance == null){
				  instance = new JdbcConfigure();
				  }
			  return instance;
		}
		
		
		public static void main(String[] args) {
			System.out.println(JdbcConfigure.getInstance().getJdbcInfo().getUser());
		}
}
