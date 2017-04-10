package jiyun.com.openresausechina.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.androidkun.adapter.BaseAdapter;
import com.androidkun.adapter.ViewHolder;

import java.util.List;

import jiyun.com.openresausechina.R;
import jiyun.com.openresausechina.model.SoftNetBean;
import jiyun.com.openresausechina.view.SearchWebView;


/**
 * Created by think on 2017/4/6.
 */

public class PullAdapter extends BaseAdapter<SoftNetBean.ResultBean> {
    private Context context;
    public PullAdapter(Context context,  List<SoftNetBean.ResultBean> datas) {
        super(context, R.layout.activity_item2, datas);
        this.context = context;
    }

    @Override
    public void convert(ViewHolder holder, final SoftNetBean.ResultBean resultBean) {
        holder.setText(R.id.titles,resultBean.getTitle());
        holder.setText(R.id.content,resultBean.getDescription());
        holder.setOnclickListener(R.id.srarchItem, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SearchWebView.class);
                intent.putExtra("url",resultBean.getUrl());
                context.startActivity(intent);
            }
        });
    }
}
