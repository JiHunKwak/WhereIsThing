package onetwopunch.capstone.com.whereisthing.Controller.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import onetwopunch.capstone.com.whereisthing.Model.ModuleListModel;
import onetwopunch.capstone.com.whereisthing.R;

public class ModuleListAdapter extends RecyclerView.Adapter<ModuleListAdapter.ViewHolder>{

    private List<ModuleListModel> tempArr;

    public ModuleListAdapter(List<ModuleListModel> tempArr) {
        this.tempArr = tempArr;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_module_list, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.tv_item_title.setText(tempArr.get(position).getName());
        holder.tv_item_module.setText(tempArr.get(position).getModuleNumber());

    }

    @Override
    public int getItemCount() {
        return tempArr.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_item_title;
        private TextView tv_item_module;

        public ViewHolder(View itemView) {
            super(itemView);

            tv_item_title = (TextView) itemView.findViewById(R.id.tv_item_title);
            tv_item_module = (TextView) itemView.findViewById(R.id.tv_item_module);

        }
    }
}
