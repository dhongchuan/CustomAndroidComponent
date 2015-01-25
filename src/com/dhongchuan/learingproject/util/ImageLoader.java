package com.dhongchuan.learingproject.util;

import java.util.Comparator;
import java.util.HashMap;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Future;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import android.webkit.DownloadListener;
import android.widget.ImageView;

public class ImageLoader implements DownloadImageRunnable.IDownloadFinishCallBack{
	/**
	 * it saves ImageView needed to show  and URL
	 */
	private HashMap<ImageView, String> mHmapNeedShowImageViews;
	private HashMap<String, Future<?>> mHmapFuture;
//	private LIFOThreadPoolProcessor mDownloadProcess;
	private ThreadPoolExecutor threadExecutor;
	private BlockingQueue<Runnable> opsToRun = new PriorityBlockingQueue<Runnable>(10,new Comparator<Runnable>() {

		@Override
		public int compare(Runnable lhs, Runnable rhs) {
			// TODO Auto-generated method stub
			if(lhs instanceof LIFOTask && rhs instanceof LIFOTask){
				LIFOTask l = (LIFOTask) lhs;
				LIFOTask r = (LIFOTask) rhs;
				return l.compareTo(r);
			}
			return 0;
		}
	});

	
	
	private Object mSync;
	
	private ImageLoader(){
		mHmapNeedShowImageViews = new HashMap<ImageView, String>(10);
		mHmapFuture = new HashMap<String, Future<?>>(15);
		threadExecutor = new ThreadPoolExecutor(5, 5, 0, TimeUnit.SECONDS, opsToRun);
//		mDownloadProcess = new LIFOThreadPoolProcessor(5);
		
	}
	
	public ImageLoader getInstance(){
		return new ImageLoader();
	}
	
	public void showImageView(ImageView view, String imageUrl){
		//判断ImageView是否已经在下载任务中
		String lastImageUrl = mHmapNeedShowImageViews.get(view);
		if(lastImageUrl != null && !lastImageUrl.equals(imageUrl)){
			Future<?> lastFuture = mHmapFuture.get(lastImageUrl);
			lastFuture.cancel(true);
			mHmapFuture.remove(lastFuture);
		}
		mHmapNeedShowImageViews.put(view, imageUrl);
	}

	public void finishHandle(byte[] byteArrImage, String imageUrl) {
		// TODO Auto-generated method stub
		synchronized (mSync) {
			
		}
	}
	
	

}
