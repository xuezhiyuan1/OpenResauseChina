package jiyun.com.openresausechina.adapter;


import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.androidkun.adapter.BaseAdapter;
import com.androidkun.adapter.ViewHolder;

import java.util.List;

import jiyun.com.openresausechina.R;
import jiyun.com.openresausechina.model.Model;
import jiyun.com.openresausechina.view.Main2Activity;


/**
 * Created by think on 2017/4/5.
 */

public class PulltoRecycleView extends BaseAdapter<Model> {
    private Context context;
    public PulltoRecycleView(Context context,List<Model> datas) {
        super(context, R.layout.activity_item, datas);
        this.context = context;
    }

    @Override
    public void convert(ViewHolder holder, final Model model) {
        holder.setOnclickListener(R.id.layout, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,Main2Activity.class);
                intent.putExtra("id",model.getId());
                context.startActivity(intent);
            }
        });
        holder.setText(R.id.text,model.getTitle());
    }
}
