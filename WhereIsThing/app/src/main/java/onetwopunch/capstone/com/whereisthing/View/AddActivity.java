package onetwopunch.capstone.com.whereisthing.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import onetwopunch.capstone.com.whereisthing.R;

public class AddActivity extends AppCompatActivity {

    private TextView tv_add_guide;
    private TextView tv_add_per;

    private ImageView iv_add_mic;
    private ImageView iv_add_micing;

    private ImageView iv_add_25;
    private ImageView iv_add_50;
    private ImageView iv_add_75;
    private ImageView iv_add_full;

    private ImageView iv_add_exit;

    private Animation anim_slide_bottom_up_25;
    private Animation anim_slide_bottom_up_50;
    private Animation anim_slide_bottom_up_75;
    private Animation anim_slide_bottom_up_full;

    private int count;

    private Intent moduleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        getSupportActionBar().hide();

        moduleList = new Intent(getApplicationContext(), ModuleListActivity.class);

        tv_add_guide = (TextView) findViewById(R.id.tv_add_guide);

        iv_add_mic = (ImageView) findViewById(R.id.iv_add_mic);
        iv_add_micing = (ImageView) findViewById(R.id.iv_add_micing);

        iv_add_25 = (ImageView) findViewById(R.id.iv_add_25);
        iv_add_50 = (ImageView) findViewById(R.id.iv_add_50);
        iv_add_75 = (ImageView) findViewById(R.id.iv_add_75);
        iv_add_full = (ImageView) findViewById(R.id.iv_add_full);

        anim_slide_bottom_up_25 = AnimationUtils.loadAnimation(this, R.anim.anim_slide_bottom_up_25);
        anim_slide_bottom_up_50 = AnimationUtils.loadAnimation(this, R.anim.anim_slide_bottom_up_50);
        anim_slide_bottom_up_75 = AnimationUtils.loadAnimation(this, R.anim.anim_slide_bottom_up_75);
        anim_slide_bottom_up_full = AnimationUtils.loadAnimation(this, R.anim.anim_slide_bottom_up_full);

        tv_add_per = (TextView) findViewById(R.id.tv_add_per);

        count = 0;

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
            }
        });

        iv_add_micing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (count){
                    case 0:
                        tv_add_guide.setText(R.string.add_guide_more);
                        tv_add_per.setText(R.string.add_per_25);
                        iv_add_micing.setVisibility(View.INVISIBLE);
                        iv_add_mic.setVisibility(View.VISIBLE);
                        iv_add_25.setVisibility(View.VISIBLE);
                        iv_add_25.startAnimation(anim_slide_bottom_up_25);
                        count++;
                        break;
                    case 1:
                        tv_add_guide.setText(R.string.add_guide_more);
                        tv_add_per.setText(R.string.add_per_50);
                        iv_add_micing.setVisibility(View.INVISIBLE);
                        iv_add_mic.setVisibility(View.VISIBLE);
                        iv_add_50.setVisibility(View.VISIBLE);
                        iv_add_50.startAnimation(anim_slide_bottom_up_50);
                        count++;
                        break;
                    case 2:
                        tv_add_guide.setText(R.string.add_guide_more);
                        tv_add_per.setText(R.string.add_per_75);
                        iv_add_micing.setVisibility(View.INVISIBLE);
                        iv_add_mic.setVisibility(View.VISIBLE);
                        iv_add_75.setVisibility(View.VISIBLE);
                        iv_add_75.startAnimation(anim_slide_bottom_up_75);
                        count++;
                        break;
                    case 3:
                        tv_add_guide.setText(R.string.add_guide_done);
                        tv_add_per.setText(R.string.add_per_full);
                        iv_add_micing.setVisibility(View.INVISIBLE);
                        iv_add_mic.setVisibility(View.VISIBLE);
                        iv_add_full.setVisibility(View.VISIBLE);
                        iv_add_full.startAnimation(anim_slide_bottom_up_full);
                        break;
                }
            }
        });

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
