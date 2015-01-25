package com.dhongchuan.learingproject.util;

import java.util.Queue;
import java.util.concurrent.PriorityBlockingQueue;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

/**
 * ����ͼƬ�Ŀ�ִ�ж���
 * @author dhongchuan
 *
 */

public class DownloadImageRunnable implements Runnable{
	//ָ����һ�������ָ��
	private DownloadImageRunnable next;
	//ָ���һ������
	private static DownloadImageRunnable sPool;
	//��ǰ����ش�С
	private static int sPoolCurrentSize = 0;
	//ͬ����
	private static final Object sPoolSync = new Object();
	//����صĴ�С
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
				//�ӳ���ȡ����
				DownloadImageRunnable tmp = sPool;
				sPool = tmp.next;
				tmp.next = null;
				sPoolCurrentSize--;
				//���¸�ֵ
				
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
