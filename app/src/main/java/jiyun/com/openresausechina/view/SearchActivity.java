package jiyun.com.openresausechina.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.androidkun.PullToRefreshRecyclerView;
import com.androidkun.callback.PullToRefreshListener;
import com.thoughtworks.xstream.XStream;


import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import jiyun.com.openresausechina.R;
import jiyun.com.openresausechina.adapter.PullAdapter;
import jiyun.com.openresausechina.inteface.NetWork;
import jiyun.com.openresausechina.model.SoftNetBean;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by think on 2017/4/6.
 */

public class SearchActivity extends Activity {

    private PullToRefreshRecyclerView pulltoRecycleView;
    private EditText edittext;
    private PullAdapter adapter;
    private List<SoftNetBean.ResultBean> datas;
    private int pageIndex;
    private TextView textView;
    private String content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        init();
    }

    private void init() {
        textView = (TextView) findViewById(R.id.cancel);
        edittext = (EditText) findViewById(R.id.editText);

        //给EditText设置监听
        edittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String s1 = s.toString();
                //isEmpty  可以换成  length() > 0;
                if (s1.isEmpty()) {
                    textView.setText("取消");
                } else {
                    textView.setText("搜索");
                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        datas = new ArrayList<>();
        pulltoRecycleView = (PullToRefreshRecyclerView) findViewById(R.id.recycleView);
        adapter = new PullAdapter(this, datas);
        //设置样式
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        pulltoRecycleView.setLayoutManager(linearLayoutManager);
        pulltoRecycleView.setPullToRefreshListener(new PullToRefreshListener() {

            @Override
            public void onRefresh() {
                pulltoRecycleView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pulltoRecycleView.setRefreshComplete();
                        datas.clear();
                        pageIndex++;
                        sendRequest("software", content, pageIndex + "", "10");
                    }
                }, 3000);
            }

            @Override
            public void onLoadMore() {
                pulltoRecycleView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pulltoRecycleView.setLoadMoreComplete();
                        pageIndex++;
                        sendRequest("software", content, pageIndex + "", "10");
                    }
                }, 3000);

            }

        });

        //绑定适配器
        pulltoRecycleView.setAdapter(adapter);


        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                content = edittext.getText().toString();
                if (content == "") {
                    finish();
                } else {
                    datas.clear();
                    sendRequest("software", content, pageIndex + "", "10");
                    adapter.notifyDataSetChanged();
                }
            }

        });

    }


    private void sendRequest(String catalog, String content, String pageIndex, String pageSize) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://www.oschina.net/").build();
        NetWork netWork = retrofit.create(NetWork.class);
        Call<ResponseBody> call = netWork.getDatass(catalog, content, pageIndex, pageSize);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                ResponseBody body = response.body();
                InputStream inputStream = body.byteStream();
                XStream xStream = new XStream();
                xStream.alias("oschina", SoftNetBean.class);
                xStream.alias("result", SoftNetBean.ResultBean.class);
                SoftNetBean softNetBean = (SoftNetBean) xStream.fromXML(inputStream);
                List<SoftNetBean.ResultBean> list = softNetBean.getResults();
                datas.addAll(list);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(SearchActivity.this, "加载数据失败", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
