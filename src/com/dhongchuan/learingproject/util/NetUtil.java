package com.dhongchuan.learingproject.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class NetUtil {
	
	/**
	 * 根据URL从服务器上获取文件的byte数组
	 * @param strUrl
	 * @return
	 */
	public static byte[] getFileBytesByUrl(String strUrl){
		HttpsURLConnection httpConnection = null;
		try {
			URL url = new URL(strUrl);
			httpConnection = (HttpsURLConnection) url.openConnection();
			
			ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
			InputStream inStream = httpConnection.getInputStream();
			if(httpConnection.getResponseCode() != HttpsURLConnection.HTTP_OK){
				return null;
			}
			int bytesRead = 0;
			byte[] buffer = new byte[1024];
			while((bytesRead = inStream.read(buffer)) > 0){
				byteOut.write(buffer, 0, bytesRead);
			}
			byteOut.close();
			return byteOut.toByteArray();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(httpConnection != null)
				httpConnection.disconnect();
		}
		return null;
	}
}
