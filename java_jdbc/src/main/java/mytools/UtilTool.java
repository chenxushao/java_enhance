package mytools;

import java.io.UnsupportedEncodingException;

//实用工具类
public class UtilTool  
{    
	//iso-8859-1  -----> gbk       
    public static String toGBK(String str) throws UnsupportedEncodingException
    {   
    	if ( str ==null )
    	{
    		str="";
    		return str;
    	}
    	return new String(str.getBytes("iso-8859-1"),"GBK");
    }
    
    //用于对HTML中的保留字符和一些特殊字符进行转换过滤
    public static String toHtml(String str)
    {
        if(str==null)
            return "";
        StringBuffer sb = new StringBuffer();
        int len = str.length();
        for (int i = 0; i < len; i++)
        {
            char c = str.charAt(i);
            switch(c)
            {                  
            case ' ':
                sb.append("&nbsp;");
                break;
            case '\n':
                sb.append("<br>");
               break;
            case '\r':
               break;
            case '\'':
                sb.append("&#39;");
                break;
            case '<':
                sb.append("&lt;");
                break;
            case '>':
                sb.append("&gt;");
                break;
            case '&':
                sb.append("&amp;");
                break;
            case '"':
                sb.append("&#34;");
                break;
            case '\\':
                sb.append("&#92;");
                break;
            default:
                sb.append(c);
            }
        }
        return sb.toString();
    }   
    
    //用于对日期进行格式化
    
    //用于对数字进行格式化
    
	public static void main(String[] args) 
	{     
	
	}

}
