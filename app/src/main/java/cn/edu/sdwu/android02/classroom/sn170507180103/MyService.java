package cn.edu.sdwu.android02.classroom.sn170507180103;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.util.Log;

public class MyService extends Service {
    private MediaPlayer mediaPlayer;
    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化媒体播放器
        mediaPlayer=MediaPlayer.create(this,R.raw.wav);//当前上下文、指向的资源
        mediaPlayer.setLooping(true);//循环播放
        Log.i(MyService.class.toString(),"onCreate");

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //覆盖父类的方法↑
        //主要业务方法写在本方法中
        mediaPlayer.start();//开始播放
        Log.i(MyService.class.toString(),"onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {

        mediaPlayer.stop();//停止播放
        mediaPlayer.release();//释放资源
        Log.i(MyService.class.toString(),"onDestroy");
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
    //播放音频



}
