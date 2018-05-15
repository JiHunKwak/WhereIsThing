package onetwopunch.capstone.com.whereisthing.View;

import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import onetwopunch.capstone.com.whereisthing.R;

public class MainActivity extends AppCompatActivity {

    private TextView tv_main_guide;
    private ImageView iv_main_nav;
    private LinearLayout ll_main_menu;

    private ImageView iv_main_mic;
    private ImageView iv_main_micing;

    private Animation anim_slide_left_in;
    private Animation anim_slide_left_out;

    private LinearLayout ll_menu_home;
    private LinearLayout ll_menu_notify;
    private LinearLayout ll_menu_option;
    private LinearLayout ll_menu_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        tv_main_guide = (TextView) findViewById(R.id.tv_main_guide);
        tv_main_guide.setTypeface(Typeface.createFromAsset(getAssets(), "mainfont.ttf"));

        ll_main_menu = (LinearLayout) findViewById(R.id.ll_main_menu);

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
                // 환경설정 액티비티 인텐트
            }
        });

        ll_menu_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 기기목록 액티비티 인텐트
            }
        });

        iv_main_mic = (ImageView) findViewById(R.id.iv_main_mic);
        iv_main_micing = (ImageView) findViewById(R.id.iv_main_micing);

        iv_main_mic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 음성인식 시작
                iv_main_mic.setVisibility(View.INVISIBLE);
                iv_main_micing.setVisibility(View.VISIBLE);
                tv_main_guide.setText(R.string.main_guide_searching);
            }
        });

        iv_main_micing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 음성인식 종료
                iv_main_micing.setVisibility(View.INVISIBLE);
                iv_main_mic.setVisibility(View.VISIBLE);
                String result = getString(R.string.main_guide_result1) + "곽지 " + getString(R.string.main_guide_result2);
                tv_main_guide.setText(result);
            }
        });

    }
}
