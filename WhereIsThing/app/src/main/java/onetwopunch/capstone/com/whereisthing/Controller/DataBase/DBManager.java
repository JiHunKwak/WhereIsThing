package onetwopunch.capstone.com.whereisthing.Controller.DataBase;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import onetwopunch.capstone.com.whereisthing.Model.Constants;
import onetwopunch.capstone.com.whereisthing.Model.ModuleListModel;
import onetwopunch.capstone.com.whereisthing.View.BaseActivity;

public class DBManager extends SQLiteOpenHelper{

    public DBManager(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE TABLE " + Constants.TABLE_NAME + " (" +
                "moduleNumber VARCHAR(50) PRIMARY KEY," +
                "name VARCHAR(20) NOT NULL," +
                "keyword1 VARCHAR(20) NOT NULL," +
                "keyword2 VARCHAR(20)," +
                "keyword3 VARCHAR(20)," +
                "keyword4 VARCHAR(20)," +
                "flag INTEGER(4) NOT NULL); ");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void insert(String moduleNumber, String name, String keyword1, String keyword2, String keyword3, String keyword4, int flag){

        SQLiteDatabase db = getWritableDatabase(); // 쓰기 가능하게 열기

        db.execSQL("INSERT OR REPLACE INTO " + Constants.TABLE_NAME + " VALUES (" +
                "'" + moduleNumber + "', " +
                "'" + name + "', " +
                "'" + keyword1 + "', " +
                "'" + keyword2 + "', " +
                "'" + keyword3 + "', " +
                "'" + keyword4 + "', " +
                flag + ");");

        db.close();

    }

    public void clearDB(){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + Constants.TABLE_NAME + ";");
        db.close();
    }

    public void loadData(){

        SQLiteDatabase db = getReadableDatabase();

        BaseActivity.listArr.clear();

        Cursor cursor = db.rawQuery("SELECT * FROM " + Constants.TABLE_NAME + ";", null);
            while(cursor.moveToNext()){
                String moduleNumber = cursor.getString(0);
                String name = cursor.getString(1);
                String keyword1 = cursor.getString(2);
                String keyword2 = cursor.getString(3);
                String keyword3 = cursor.getString(4);
                String keyword4 = cursor.getString(5);
                int flag = cursor.getInt(6);

                BaseActivity.listArr.add(new ModuleListModel(moduleNumber, name, keyword1, keyword2, keyword3, keyword4, flag));

            }

    }

}
