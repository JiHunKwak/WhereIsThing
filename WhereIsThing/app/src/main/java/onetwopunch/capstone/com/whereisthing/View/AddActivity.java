package onetwopunch.capstone.com.whereisthing.View;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.kakao.sdk.newtoneapi.SpeechRecognizeListener;
import com.kakao.sdk.newtoneapi.SpeechRecognizerClient;

import java.util.ArrayList;

import onetwopunch.capstone.com.whereisthing.R;

public class AddActivity extends AppCompatActivity {

    private TextView tv_add_guide;
    private TextView tv_add_per;

    private ImageView iv_add_mic;
    private ImageView iv_add_micing;
    private ImageView iv_add_check;

    private ImageView iv_add_25;
    private ImageView iv_add_50;
    private ImageView iv_add_75;
    private ImageView iv_add_half;
    private ImageView iv_add_full;

    private ProgressBar pb_add;

    private ImageView iv_add_exit;

    private Animation anim_slide_bottom_up_25;
    private Animation anim_slide_bottom_up_50;
    private Animation anim_slide_bottom_up_75;
    private Animation anim_slide_bottom_up_full;

    private int count;
    private String[] dataArr;

    private Intent moduleList;

    private TextView tv_test1;
    private TextView tv_test2;
    private TextView tv_test3;
    private TextView tv_test4; // test용

    private SpeechRecognizerClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        getSupportActionBar().hide();

        tv_test1 = (TextView) findViewById(R.id.add_tv_test1);
        tv_test2 = (TextView) findViewById(R.id.add_tv_test2);
        tv_test3 = (TextView) findViewById(R.id.add_tv_test3);
        tv_test4 = (TextView) findViewById(R.id.add_tv_test4); // test용

        SpeechRecognizerClient.Builder builder = new SpeechRecognizerClient.Builder().setServiceType(SpeechRecognizerClient.SERVICE_TYPE_WEB);
        client = builder.build();

        moduleList = new Intent(getApplicationContext(), ModuleListActivity.class);

        tv_add_guide = (TextView) findViewById(R.id.tv_add_guide);

        iv_add_mic = (ImageView) findViewById(R.id.iv_add_mic);
        iv_add_micing = (ImageView) findViewById(R.id.iv_add_micing);
        iv_add_check = (ImageView) findViewById(R.id.iv_add_check);

        iv_add_25 = (ImageView) findViewById(R.id.iv_add_25);
        iv_add_50 = (ImageView) findViewById(R.id.iv_add_50);
        iv_add_75 = (ImageView) findViewById(R.id.iv_add_75);
        iv_add_half = (ImageView) findViewById(R.id.iv_add_half);
        iv_add_full = (ImageView) findViewById(R.id.iv_add_full);

        pb_add = (ProgressBar) findViewById(R.id.pb_add);

        anim_slide_bottom_up_25 = AnimationUtils.loadAnimation(this, R.anim.anim_slide_bottom_up_25);
        anim_slide_bottom_up_50 = AnimationUtils.loadAnimation(this, R.anim.anim_slide_bottom_up_50);
        anim_slide_bottom_up_75 = AnimationUtils.loadAnimation(this, R.anim.anim_slide_bottom_up_75);
        anim_slide_bottom_up_full = AnimationUtils.loadAnimation(this, R.anim.anim_slide_bottom_up_full);

        tv_add_per = (TextView) findViewById(R.id.tv_add_per);

        count = 0;
        dataArr = new String[4];
//        index 0: name, keyword1
//        index 1: keyword2;
//        index 2: keyword3;
//        index 3: keyword4;

        iv_add_exit = (ImageView) findViewById(R.id.iv_add_exit);
        iv_add_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                setIntentFlag(moduleList);
                startActivity(moduleList);
                finish();

            }
        });

        iv_add_mic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iv_add_mic.setVisibility(View.INVISIBLE);
                iv_add_micing.setVisibility(View.VISIBLE);
                pb_add.setVisibility(View.VISIBLE);
                switch (count){
                    case 0:
                        iv_add_exit.setVisibility(View.INVISIBLE);
                        inputVoiceData(client, 1);
                        //음성인식.. (keyword1)
                        break;
                    case 1:
                        inputVoiceData(client, 2);
                        //음성인식.. (keyword2)
                        break;
                    case 2:
                        inputVoiceData(client, 3);
                        //음성인식.. (keyword3)
                        break;
                    case 3:
                        inputVoiceData(client, 4);
                        //음성인식.. (keyword4)
                        break;
                }
            }
        });

        iv_add_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setIntentFlag(moduleList);
                startActivity(moduleList);
                finish();
            }
        });

    }

    public void inputVoiceData(SpeechRecognizerClient client, final int inputCount){
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
                switch (inputCount){
                    case 1:
                        tv_test1.setText(texts.get(0)); // test용
                        dataArr[0] = texts.get(0);
                        tv_add_guide.setText(R.string.add_guide_more);
                        tv_add_per.setText(R.string.add_per_25);
                        iv_add_micing.setVisibility(View.INVISIBLE);
                        iv_add_mic.setVisibility(View.VISIBLE);
                        pb_add.setVisibility(View.INVISIBLE);
                        iv_add_25.setVisibility(View.VISIBLE);
                        iv_add_25.startAnimation(anim_slide_bottom_up_25);
                        count++;
                        break;
                    case 2:
                        tv_test2.setText(texts.get(0)); // test용
                        dataArr[1] = texts.get(0);
                        tv_add_guide.setText(R.string.add_guide_more);
                        tv_add_per.setText(R.string.add_per_50);
                        tv_add_per.setTextColor(Color.WHITE); // KWAKGEE's Think!
                        iv_add_micing.setVisibility(View.INVISIBLE);
                        iv_add_mic.setVisibility(View.VISIBLE);
                        pb_add.setVisibility(View.INVISIBLE);
                        iv_add_25.setVisibility(View.GONE);
                        iv_add_50.setVisibility(View.VISIBLE);
                        iv_add_50.startAnimation(anim_slide_bottom_up_50);
                        count++;
                        break;
                    case 3:
                        tv_test3.setText(texts.get(0)); // test용
                        dataArr[2] = texts.get(0);
                        tv_add_guide.setText(R.string.add_guide_more);
                        tv_add_per.setText(R.string.add_per_75);
                        iv_add_micing.setVisibility(View.INVISIBLE);
                        iv_add_mic.setVisibility(View.VISIBLE);
                        pb_add.setVisibility(View.INVISIBLE);
                        iv_add_50.setVisibility(View.GONE);
                        iv_add_75.setVisibility(View.VISIBLE);
                        iv_add_75.startAnimation(anim_slide_bottom_up_75);
                        count++;
                        break;
                    case 4:
                        tv_test4.setText(texts.get(0)); // test용
                        dataArr[3] = texts.get(0);
                        tv_add_guide.setText(R.string.add_guide_done);
                        tv_add_guide.setTextColor(Color.WHITE); // KWAKGEE's Think!
                        tv_add_per.setText(R.string.add_per_full);
                        iv_add_micing.setVisibility(View.INVISIBLE);
                        iv_add_check.setVisibility(View.VISIBLE);
                        pb_add.setVisibility(View.INVISIBLE);
                        iv_add_exit.setVisibility(View.VISIBLE);
//                        iv_add_75.setVisibility(View.GONE);
                        iv_add_half.setVisibility(View.VISIBLE);
                        iv_add_75.startAnimation(anim_slide_bottom_up_full);
                        count++;

                        String randomNumber = String.valueOf((int)(Math.random()*500000)+1);
                        BaseActivity.dbm.insert(randomNumber, dataArr[0], dataArr[0], dataArr[1], dataArr[2], dataArr[3],1);
                        BaseActivity.dbm.loadData();  // 데이터 업데이트

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                iv_add_full.setVisibility(View.VISIBLE);
                                iv_add_half.setVisibility(View.INVISIBLE);
                            }
                        },750);

                        break;
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

    @Override
    public void onBackPressed() {
        setIntentFlag(moduleList);
        startActivity(moduleList);
        finish();
    }

    public void setIntentFlag(Intent intent){
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
    }

}
