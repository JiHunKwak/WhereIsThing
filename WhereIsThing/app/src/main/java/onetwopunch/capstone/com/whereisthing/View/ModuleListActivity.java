package onetwopunch.capstone.com.whereisthing.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import onetwopunch.capstone.com.whereisthing.Controller.Adapter.ModuleListAdapter;
import onetwopunch.capstone.com.whereisthing.Model.Constants;
import onetwopunch.capstone.com.whereisthing.R;

public class ModuleListActivity extends AppCompatActivity {

    private TextView tv_list_guide;
    private RecyclerView rv_list_list;
    private ModuleListAdapter mlAdapter;
    private LinearLayoutManager llManager;

    private ImageView iv_list_add;
    private ImageView iv_list_back;

    private Intent add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module_list);

        getSupportActionBar().hide();
        add = new Intent(getApplicationContext(), AddActivity.class);

        tv_list_guide = (TextView) findViewById(R.id.tv_list_guide);
        rv_list_list = (RecyclerView) findViewById(R.id.rv_list_list);

        llManager = new LinearLayoutManager(getApplicationContext());
        llManager.setOrientation(LinearLayoutManager.VERTICAL);

        iv_list_add = (ImageView) findViewById(R.id.iv_list_add);
        iv_list_add.bringToFront();
        iv_list_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(BaseActivity.listArr.size() >= Constants.ITEM_LIST_MAX_SIZE){
                    String messageTemp = getString(R.string.list_item_full);
                    Toast.makeText(ModuleListActivity.this, messageTemp, Toast.LENGTH_LONG).show();
                }else {
                    setIntentFlag(add);
                    startActivity(add);
                    finish();
                }
            }
        });

        iv_list_back = (ImageView) findViewById(R.id.iv_list_back);
        iv_list_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        if(BaseActivity.listArr.isEmpty()){
            tv_list_guide.setVisibility(View.VISIBLE);
            rv_list_list.setVisibility(View.INVISIBLE);
        } else {
            tv_list_guide.setVisibility(View.INVISIBLE);
            rv_list_list.setVisibility(View.VISIBLE);

            mlAdapter = new ModuleListAdapter(BaseActivity.listArr, Constants.LAYOUT_SELECTOR_MODULE_LIST);

            rv_list_list.setLayoutManager(llManager);
            rv_list_list.setAdapter(mlAdapter);

        }

    }

    public void setIntentFlag(Intent intent){
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
    }

}
