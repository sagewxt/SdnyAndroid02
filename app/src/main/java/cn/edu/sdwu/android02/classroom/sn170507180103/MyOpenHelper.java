package cn.edu.sdwu.android02.classroom.sn170507180103;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by wxt on 2020/4/29.
 */

public class MyOpenHelper extends SQLiteOpenHelper {
    private String STUDENT_TB_SQL="create table student(id integer primary key autoincrement,stuname text,stutel text)";


    public MyOpenHelper(Context context){
        //调用父类的构造方法
        //参数：
        //①Context context(上下文)
        //②String name(创建的数据库名字）
        //③CursorFactory factory（游标工厂，默认null）
        //④int version（数据库版本）
        //super(context,"stud.db",null,1);
        super(context,"stud.db",null,2);//版本号改为2
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //当打开数据库时，发现数据库并不存在，此时会自动创建数据库，然后调用onCreate方法
        //在本方法中完成数据库对象（表，视图，索引等）的创建

        sqLiteDatabase.execSQL(STUDENT_TB_SQL);
        Log.i(MyOpenHelper.class.toString(),"onCreate");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //当构造方法中指定的版本号，与手机中已有数据库的版本号更新的时候，调用本方法
        sqLiteDatabase.execSQL("alter table student add column stuadd text");

    }
}
