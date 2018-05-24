package onetwopunch.capstone.com.whereisthing.View;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.Toast;

import com.kakao.sdk.newtoneapi.SpeechRecognizerManager;

import java.util.ArrayList;
import java.util.List;

import onetwopunch.capstone.com.whereisthing.Controller.DataBase.DBManager;
import onetwopunch.capstone.com.whereisthing.Model.ModuleListModel;
import onetwopunch.capstone.com.whereisthing.R;

public class BaseActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_AUDIO_AND_WRITE_EXTERNAL_STORAGE = 1123;
    public static List<ModuleListModel> listArr;
    public static DBManager dbm;

    private Intent main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        if(checkNetworkState()){
            checkPermission(this);
        } else {
            Toast.makeText(this, "네트워크 설정을 확인한 뒤, 앱을 완전히 종료하고 다시 실행시켜 주십시오.", Toast.LENGTH_LONG).show();
        }

    }

    public void startWhereIsThing(){

        main = new Intent(getApplicationContext(), MainActivity.class);
        listArr = new ArrayList<>();

        SpeechRecognizerManager.getInstance().initializeLibrary(this); // kakao 음성인식 라이브러리 초기화

        dbm = new DBManager(getApplicationContext(), "MODULE.db", null, 1);
        dbm.clearDB();
        dbm.loadData();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                startActivity(main);
                finish();

            }
        }, 3000);
    }

    public void checkPermission(Context context){

        if(ActivityCompat.checkSelfPermission(context, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.RECORD_AUDIO) && ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)){
                Toast.makeText(this, "권한이 없습니다.", Toast.LENGTH_SHORT).show();
                ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE_AUDIO_AND_WRITE_EXTERNAL_STORAGE);
            } else {
                ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE_AUDIO_AND_WRITE_EXTERNAL_STORAGE);
            }
        } else {
            startWhereIsThing();
        }

    }

    public boolean checkNetworkState(){

        ConnectivityManager cm = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        if(ni != null && ni.isConnected()){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_AUDIO_AND_WRITE_EXTERNAL_STORAGE:
                if (grantResults.length > 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "권한 설정이 완료되었습니다. 잠시후 앱을 실행합니다.", Toast.LENGTH_SHORT).show();
                    startWhereIsThing();
                } else {
                    Toast.makeText(this, "권한 설정이 이루어지지 않았습니다. 앱을 실행 할 수 없습니다.", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }
}
// 5/17: 데이터 인서트, 환경설정 레이아웃
// 5/24: 앱 아이콘, 메인 음성작업, 영어버전작업, 블루투스작업, 환경설정작업, 공지사항작업
