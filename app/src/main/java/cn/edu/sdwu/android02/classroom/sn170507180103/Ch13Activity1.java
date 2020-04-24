package cn.edu.sdwu.android02.classroom.sn170507180103;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Ch13Activity1 extends AppCompatActivity {
    private EditText ip;
    private EditText port;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_ch13_1);

        ip=(EditText)findViewById(R.id.ch13_1_ip);
        port=(EditText)findViewById(R.id.ch13_1_port);

        //读取简单存储
        SharedPreferences sharedPreferences=getSharedPreferences("prefs",MODE_PRIVATE);
        ip.setText(sharedPreferences.getString("ip",""));
        port.setText(sharedPreferences.getString("port",""));
    }

    public void write(View v){
        EditText editText=(EditText)findViewById(R.id.ch13_1_et);
        String content=editText.getText().toString();

        try{
            //获取输出流
            FileOutputStream fileOutputStream=openFileOutput("android02.txt",MODE_PRIVATE);//文件名,访问模式

            fileOutputStream.write(content.getBytes());
            fileOutputStream.flush();//清空缓存
            fileOutputStream.close();

        }catch (Exception e){
            Log.i(Ch13Activity1.class.toString(),e.toString());
        }

    }

    public void read(View view){
        try {
            //获取输入流
            FileInputStream fileInputStream=openFileInput("android02.txt");

            int size=fileInputStream.available();
            byte[] bytes=new byte[size];
            fileInputStream.read(bytes);

            String content=new String(bytes);

            fileInputStream.close();//关闭

            Toast.makeText(this,content,Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Log.i(Ch13Activity1.class.toString(),e.toString());
        }
    }
    public void saveSharePref(View view){
        SharedPreferences sharedPreferences=getSharedPreferences("prefs",MODE_PRIVATE);//文件名，模式
        SharedPreferences.Editor editor=sharedPreferences.edit();//得到编辑器
        editor.putString("ip",ip.getText().toString());
        editor.putString("port",port.getText().toString());
        editor.commit();

    }
}
