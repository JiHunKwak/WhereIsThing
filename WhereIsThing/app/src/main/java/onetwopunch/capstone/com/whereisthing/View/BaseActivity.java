package onetwopunch.capstone.com.whereisthing.View;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;

import onetwopunch.capstone.com.whereisthing.Controller.DataBase.DBManager;
import onetwopunch.capstone.com.whereisthing.Model.ModuleListModel;
import onetwopunch.capstone.com.whereisthing.R;

public class BaseActivity extends AppCompatActivity {

    public static List<ModuleListModel> listArr;
    public static DBManager dbm;

    private Intent main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        main = new Intent(getApplicationContext(), MainActivity.class);
        listArr = new ArrayList<>();

        dbm = new DBManager(getApplicationContext(), "MODULE.db", null, 1);
        dbm.loadData();

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                startActivity(main);
                finish();

            }
        }, 3000);

    }
} // 5/17: 데이터 인서트, 환경설정 레이아웃,
