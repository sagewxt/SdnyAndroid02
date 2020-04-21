package cn.edu.sdwu.android02.classroom.sn170507180103;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Ch10Activity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_ch10_2);
    }

    public void send_broadcast(View view){
        //发送广播
        Intent intent=new Intent("com.inspur.broadcast");//指定广播频道
        intent.putExtra("key1","message");
        //intent.putExtra("key2","message");
        sendBroadcast(intent);//发送

    }
}
