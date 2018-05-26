package onetwopunch.capstone.com.whereisthing.View;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.kakao.sdk.newtoneapi.SpeechRecognizeListener;
import com.kakao.sdk.newtoneapi.SpeechRecognizerClient;

import java.util.ArrayList;

import onetwopunch.capstone.com.whereisthing.Controller.Adapter.ModuleListAdapter;
import onetwopunch.capstone.com.whereisthing.Controller.CustomDialogController;
import onetwopunch.capstone.com.whereisthing.Model.Constants;
import onetwopunch.capstone.com.whereisthing.R;

public class MainActivity extends AppCompatActivity {

    private TextView tv_main_guide;
    private ImageView iv_main_nav;
    private LinearLayout ll_main_menu;
    private LinearLayout ll_main_tip;

    private ImageView iv_main_mic;
    private ImageView iv_main_micing;
    private ImageView iv_main_check;
    private ImageView iv_main_sissor;
    private ImageView iv_main_done;
    private ImageView iv_main_fail;
    private ProgressBar pb_main;

    private Animation anim_slide_left_in;
    private Animation anim_slide_left_out;

    private TextView tv_menu_title;

    private LinearLayout ll_menu_home;
    private LinearLayout ll_menu_notify;
    private LinearLayout ll_menu_option;
    private LinearLayout ll_menu_list;

    private Intent notify;
    private Intent option;
    private Intent moduleList;

    private SpeechRecognizerClient client;

    private RecyclerView rv_main;
    private LinearLayoutManager llmanager;
    private ModuleListAdapter adapter;

    private TextView tv_main_test; // test


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        tv_main_test = (TextView) findViewById(R.id.tv_main_test); // test

        SpeechRecognizerClient.Builder builder = new SpeechRecognizerClient.Builder().setServiceType(SpeechRecognizerClient.SERVICE_TYPE_WEB);
        client = builder.build();

        notify = new Intent(getApplicationContext(), NotifyActivity.class);
        option = new Intent(getApplicationContext(), OptionActivity.class);
        moduleList = new Intent(getApplicationContext(), ModuleListActivity.class);

        tv_main_guide = (TextView) findViewById(R.id.tv_main_guide);
        tv_main_guide.setTypeface(Typeface.createFromAsset(getAssets(), "mainfont.ttf"));

        ll_main_menu = (LinearLayout) findViewById(R.id.ll_main_menu);
        ll_main_tip = (LinearLayout) findViewById(R.id.ll_main_tip);

        anim_slide_left_in = AnimationUtils.loadAnimation(this, R.anim.anim_slide_left_in);
        anim_slide_left_out = AnimationUtils.loadAnimation(this, R.anim.anim_slide_left_out);

        ll_menu_home = (LinearLayout) findViewById(R.id.ll_menu_home);
        ll_menu_notify = (LinearLayout) findViewById(R.id.ll_menu_notify);
        ll_menu_option = (LinearLayout) findViewById(R.id.ll_menu_option);
        ll_menu_list = (LinearLayout) findViewById(R.id.ll_menu_list);

        iv_main_nav = (ImageView) findViewById(R.id.iv_main_nav);
        iv_main_nav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                iv_main_nav.setVisibility(View.INVISIBLE);
                ll_main_menu.setVisibility(View.VISIBLE);
                ll_main_menu.startAnimation(anim_slide_left_in);

            }
        });

        ll_menu_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iv_main_nav.setVisibility(View.VISIBLE);
                ll_main_menu.startAnimation(anim_slide_left_out);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ll_main_menu.setVisibility(View.INVISIBLE);
                    }
                }, 750);
            }
        });

        ll_menu_notify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 공지사항 액티비티 인텐트
            }
        });

        ll_menu_option.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setIntentFlag(option);
                startActivity(option);
                // 환경설정 액티비티 인텐트
            }
        });

        ll_menu_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setIntentFlag(moduleList);
                startActivity(moduleList);
                // 기기목록 액티비티 인텐트
            }
        });

        tv_menu_title = (TextView) findViewById(R.id.tv_menu_title);
        tv_menu_title.setTypeface(Typeface.createFromAsset(getAssets(), "menufont.ttf"));

        iv_main_mic = (ImageView) findViewById(R.id.iv_main_mic);
        iv_main_micing = (ImageView) findViewById(R.id.iv_main_micing);
        iv_main_check = (ImageView) findViewById(R.id.iv_main_check);
        iv_main_sissor = (ImageView) findViewById(R.id.iv_main_sissor);
        iv_main_done = (ImageView) findViewById(R.id.iv_main_done);
        iv_main_fail = (ImageView) findViewById(R.id.iv_main_fail);

        pb_main = (ProgressBar) findViewById(R.id.pb_main);

        rv_main = (RecyclerView) findViewById(R.id.rv_main);
        llmanager = new LinearLayoutManager(getApplicationContext());
        llmanager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rv_main.setLayoutManager(llmanager);

        iv_main_mic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(BaseActivity.listArr.isEmpty()){
                    CustomDialogController dialog = new CustomDialogController(MainActivity.this);
                    dialog.show();
                    // 다이얼로그 박스
                }else {
                    // 음성인식 시작
                    iv_main_mic.setVisibility(View.INVISIBLE);
                    iv_main_micing.setVisibility(View.VISIBLE);
                    pb_main.setVisibility(View.VISIBLE);
                    tv_main_guide.setText(R.string.main_guide_searching);
                    InputVoiceData(client);
//                    BaseActivity.dbm.loadData();
                }
            }
        });

        iv_main_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 이건가요?
                // 맞으면 찾음
                tv_main_guide.setText(getString(R.string.main_guide_success));
                iv_main_done.setVisibility(View.VISIBLE);
                iv_main_check.setVisibility(View.INVISIBLE);
                iv_main_sissor.setVisibility(View.INVISIBLE);
            }
        });

        iv_main_sissor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 이건가요? 틀림
                // 찾는 물건 리스트
                // 있으면 선택, 없으면 처음으로
                tv_main_guide.setText(getString(R.string.main_guide_fail_recommend));
                adapter = new ModuleListAdapter(BaseActivity.listArr, Constants.LAYOUT_SELECTOR_MAIN_LIST);
                rv_main.setAdapter(adapter);
                rv_main.setVisibility(View.VISIBLE);
                iv_main_sissor.setVisibility(View.INVISIBLE);
                iv_main_check.setVisibility(View.INVISIBLE);
                iv_main_fail.setVisibility(View.VISIBLE);
            }
        });

        iv_main_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iv_main_done.setVisibility(View.INVISIBLE);
                iv_main_mic.setVisibility(View.VISIBLE);
                tv_main_guide.setText(getString(R.string.add_guide_default));
                ll_main_tip.setVisibility(View.INVISIBLE);
            }
        });

        iv_main_fail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_main_guide.setText(getString(R.string.main_guide_fail));
                ll_main_tip.setVisibility(View.VISIBLE);
                iv_main_fail.setVisibility(View.INVISIBLE);
                iv_main_done.setVisibility(View.VISIBLE);
                rv_main.setVisibility(View.INVISIBLE);
            }
        });


    }

    public void InputVoiceData(SpeechRecognizerClient client){
        client.setSpeechRecognizeListener(new SpeechRecognizeListener() {
            @Override
            public void onReady() {

            }

            @Override
            public void onBeginningOfSpeech() {

            }

            @Override
            public void onEndOfSpeech() {

            }

            @Override
            public void onError(int errorCode, String errorMsg) {

            }

            @Override
            public void onPartialResult(String partialResult) {

            }

            @Override
            public void onResults(Bundle results) {
                ArrayList<String> texts =  results.getStringArrayList(SpeechRecognizerClient.KEY_RECOGNITION_RESULTS);
                ArrayList<Integer> confs =   results.getIntegerArrayList(SpeechRecognizerClient.KEY_CONFIDENCE_VALUES);

                String searchTemp = texts.get(0);
                tv_main_test.setText(texts.get(0));
                boolean flagTemp = false;

                pb_main.setVisibility(View.INVISIBLE);

                for(int i=0; i<BaseActivity.listArr.size(); i++){
                    if(BaseActivity.listArr.get(i).getKeyword1().equals(searchTemp) || BaseActivity.listArr.get(i).getKeyword2().equals(searchTemp) || BaseActivity.listArr.get(i).getKeyword3().equals(searchTemp) || BaseActivity.listArr.get(i).getKeyword4().equals(searchTemp)){
                        flagTemp = true;
                        searchTemp = BaseActivity.listArr.get(i).getName();
                    }
                }

                if(flagTemp){
                    iv_main_micing.setVisibility(View.INVISIBLE);
                    iv_main_check.setVisibility(View.VISIBLE);
                    iv_main_sissor.setVisibility(View.VISIBLE);
                    String result = getString(R.string.main_guide_result1) + searchTemp + getString(R.string.main_guide_result2);
                    tv_main_guide.setText(result);
                } else {
                    // 찾는 물건 리스트
                    tv_main_guide.setText(getString(R.string.main_guide_fail_recommend));
                    adapter = new ModuleListAdapter(BaseActivity.listArr, Constants.LAYOUT_SELECTOR_MAIN_LIST);
                    rv_main.setAdapter(adapter);
                    rv_main.setVisibility(View.VISIBLE);
                    iv_main_micing.setVisibility(View.INVISIBLE);
                    iv_main_fail.setVisibility(View.VISIBLE);
                    // 있으면 선택, 없으면 처음으로
                }
            }

            @Override
            public void onAudioLevel(float audioLevel) {

            }

            @Override
            public void onFinished() {

            }
        });

        client.startRecording(true);

    }

    public void setIntentFlag(Intent intent){
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
    }

}
