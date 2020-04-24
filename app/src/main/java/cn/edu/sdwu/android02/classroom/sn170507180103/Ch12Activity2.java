package cn.edu.sdwu.android02.classroom.sn170507180103;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;

public class Ch12Activity2 extends AppCompatActivity {


    private ServiceConnection serviceConnection;
    private boolean bound;
    private MediaService mediaService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_ch12_2);


        bound=false;
        serviceConnection=new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                //建立连接
                bound=true;//已经绑定
                MediaService.MyBinder myBinder=(MediaService.MyBinder)iBinder;
                mediaService=myBinder.getMediaService();//得到服务实例

            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {
                //未连接
                bound=false;//解绑
            }
        };

    }

    public void ch12_2_start(View view){
        //点击事件的处理
        startServiceClick(view);
    }
    public void ch12_2_pause(View view){
        //点击事件的处理
        startServiceClick(view);
    }
    public void ch12_2_stop(View view){
        //点击事件的处理
        startServiceClick(view);
    }
    public void ch12_2_stopservice(View view){
        //点击事件的处理
        startServiceClick(view);
    }
    private void startServiceClick(View view){
        //使用本方法统一处理用户的点击（启动方式的按键）
        int id=view.getId();
        Intent intent=new Intent(this,MediaService.class);
        switch (id){
            case R.id.ch12_2_start:
                intent.putExtra("PlayerState","START");
                break;
            case R.id.ch12_2_pause:
                intent.putExtra("PlayerState","PAUSE");
                break;
            case R.id.ch12_2_stop:
                intent.putExtra("PlayerState","STOP");
                break;
            case R.id.ch12_2_stopservice:
                intent.putExtra("PlayerState","STOPSERVICE");
                break;
        }
        startService(intent);

    }

    public void bindClick(View view){
        int id=view.getId();
        switch (id){
            case R.id.ch12_2_bind:
                Intent intent=new Intent(this,MediaService.class);
                bindService(intent,serviceConnection,BIND_AUTO_CREATE);//bind,服务链接常量
                break;
            case R.id.ch12_2_unbind:
                unbindService(serviceConnection);
                bound=false;
                break;
            case R.id.ch12_2_bindstart:
                if(bound){//判断是否绑定
                    mediaService.start();
                }
                break;
            case R.id.ch12_2_bindpause:
                if(bound){//判断是否绑定
                    mediaService.pause();
                }
                break;
            case R.id.ch12_2_bindstop:
                if(bound){//判断是否绑定
                    mediaService.stop();
                }
                break;
        }
    }


}
