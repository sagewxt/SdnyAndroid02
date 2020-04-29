package cn.edu.sdwu.android02.classroom.sn170507180103;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class Ch14Activity1 extends AppCompatActivity {
    private MyOpenHelper myOpenHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_ch14_1);

        myOpenHelper=new MyOpenHelper(this);

        //以可写方法打开数据库(如果数据库不存在，自动创建数据库）
        SQLiteDatabase sqLiteDatabase=myOpenHelper.getWritableDatabase();

        //使用完毕，将数据库关闭
        sqLiteDatabase.close();

    }

    public void insert(View view){
        //以可写方法打开数据库(如果数据库不存在，自动创建数据库）
        SQLiteDatabase sqLiteDatabase=myOpenHelper.getWritableDatabase();

        try {
            //将插入的数据放置在ContentValues中
            //事务的处理
            sqLiteDatabase.beginTransaction();//开启事务
            ContentValues contentValues=new ContentValues();
            contentValues.put("stuname","Jerry");
            contentValues.put("stutel","1333333333");
            sqLiteDatabase.insert("student",null,contentValues);
            //参数：向哪个表插入数据，对空的列怎么处理，ContentValues类型
            sqLiteDatabase.setTransactionSuccessful();//所有操作结束后，调用这个方法，才会将数据真正保存到数据库中
        }catch (Exception e){
            Log.e(Ch14Activity1.class.toString(),e.toString());
        }finally {
            sqLiteDatabase.endTransaction();//结束事务
            //使用完毕，将数据库关闭
            sqLiteDatabase.close();
        }
    }

    public void query(View view){
        //以只读方法打开数据库
        SQLiteDatabase sqLiteDatabase=myOpenHelper.getReadableDatabase();

        try {
            //返回游标类型
           Cursor cursor=sqLiteDatabase.rawQuery("select * from student where stuname=?",new String[]{"Tom"});
            while(cursor.moveToNext()){
                int id=cursor.getInt(cursor.getColumnIndex("id"));
                String stuname=cursor.getString(cursor.getColumnIndex("stuname"));
                String stutel=cursor.getString(cursor.getColumnIndex("stutel"));
                Log.i(Ch14Activity1.class.toString(),"id:"+id+",stuname:"+stuname+",stutel:"+stutel);

            }
            cursor.close();
        }catch (Exception e){
            Log.e(Ch14Activity1.class.toString(),e.toString());
        }finally {
            //使用完毕，将数据库关闭
            sqLiteDatabase.close();
        }
    }

    public void delete(View view){
        //以可写方法打开数据库(如果数据库不存在，自动创建数据库）
        SQLiteDatabase sqLiteDatabase=myOpenHelper.getWritableDatabase();

        try {
            //将插入的数据放置在ContentValues中
            //事务的处理
            sqLiteDatabase.beginTransaction();//开启事务

            sqLiteDatabase.delete("student","id=?",new String[]{"1"});//数据表名字，where条件,问号具体值

            sqLiteDatabase.setTransactionSuccessful();//所有操作结束后，调用这个方法，才会将数据真正保存到数据库中
        }catch (Exception e){
            Log.e(Ch14Activity1.class.toString(),e.toString());
        }finally {
            sqLiteDatabase.endTransaction();//结束事务
            //使用完毕，将数据库关闭
            sqLiteDatabase.close();
        }
    }


    public void modify(View view){
        //以可写方法打开数据库(如果数据库不存在，自动创建数据库）
        SQLiteDatabase sqLiteDatabase=myOpenHelper.getWritableDatabase();

        try {
            //将插入的数据放置在ContentValues中
            //事务的处理
            sqLiteDatabase.beginTransaction();//开启事务

            ContentValues contentValues=new ContentValues();
            contentValues.put("stutel","1399999999");

            sqLiteDatabase.update("student",contentValues,"id=?",new String[]{"2"});
            //参数：数据表的名字，更新的内容，查询条件，查询条件

            sqLiteDatabase.setTransactionSuccessful();//所有操作结束后，调用这个方法，才会将数据真正保存到数据库中
        }catch (Exception e){
            Log.e(Ch14Activity1.class.toString(),e.toString());
        }finally {
            sqLiteDatabase.endTransaction();//结束事务
            //使用完毕，将数据库关闭
            sqLiteDatabase.close();
        }
    }


}
