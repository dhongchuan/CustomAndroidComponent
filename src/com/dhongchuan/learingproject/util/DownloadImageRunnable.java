package com.dhongchuan.learingproject.util;

import java.util.Queue;
import java.util.concurrent.PriorityBlockingQueue;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

/**
 * 下载图片的可执行对象
 * @author dhongchuan
 *
 */

public class DownloadImageRunnable implements Runnable{
	//指向下一个对象的指针
	private DownloadImageRunnable next;
	//指向第一个对象
	private static DownloadImageRunnable sPool;
	//当前对象池大小
	private static int sPoolCurrentSize = 0;
	//同步锁
	private static final Object sPoolSync = new Object();
	//对象池的大小
	private static final int MAX_POOL_SIZE = 10;
	
	private String mImageURL;
	private IDownloadFinishCallBack mCallBack;
	

	private DownloadImageRunnable(String imageURL, IDownloadFinishCallBack callBack) {
		this.mImageURL = imageURL;
		this.mCallBack = callBack;
	}
	
	public static DownloadImageRunnable obtain(String imageURL, IDownloadFinishCallBack callBack){
		synchronized (sPoolSync) {
			if(sPool != null){
				//从池中取对象
				DownloadImageRunnable tmp = sPool;
				sPool = tmp.next;
				tmp.next = null;
				sPoolCurrentSize--;
				//重新赋值
				
				tmp.mImageURL = imageURL;
				tmp.mCallBack = callBack;
				return tmp;
			}
		}
		return new DownloadImageRunnable(imageURL, callBack);
	}

	public void init(){
		mImageURL = "";
		mCallBack = null;
	}
	

	public void setImageURL(String imageURL) {
		mImageURL = imageURL;
	}

	public void setCallBack(IDownloadFinishCallBack callBack) {
		mCallBack = callBack;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		byte[] byteArrImage = NetUtil.getFileBytesByUrl(mImageURL);
		mCallBack.finishHandle(byteArrImage, mImageURL);
//		final Bitmap bitmapImage = BitmapFactory.decodeByteArray(byteArrImage, 0, byteArrImage.length);
	}
	
	public interface IDownloadFinishCallBack{
		void finishHandle(byte[] byteArrImage, String imageUrl);
	}

}
