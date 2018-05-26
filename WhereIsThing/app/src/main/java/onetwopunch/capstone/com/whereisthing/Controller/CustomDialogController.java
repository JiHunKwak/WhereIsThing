package onetwopunch.capstone.com.whereisthing.Controller;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import onetwopunch.capstone.com.whereisthing.R;
import onetwopunch.capstone.com.whereisthing.View.ModuleListActivity;

public class CustomDialogController extends Dialog{
    private static final int LAYOUT = R.layout.item_dialog;

    private Context context;

    private LinearLayout ll_dialog_add;

    private Intent moduleList;


    public CustomDialogController(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);

        moduleList = new Intent(context, ModuleListActivity.class);

        ll_dialog_add = (LinearLayout) findViewById(R.id.ll_dialog_add);
        ll_dialog_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                setIntentFlag(moduleList);
                context.startActivity(moduleList);
                dismiss();

            }
        });

    }

    public void setIntentFlag(Intent intent){
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
    }

}
