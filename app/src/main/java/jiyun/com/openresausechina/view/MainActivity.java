package jiyun.com.openresausechina.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.androidkun.PullToRefreshRecyclerView;
import com.androidkun.callback.PullToRefreshListener;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import jiyun.com.openresausechina.R;
import jiyun.com.openresausechina.adapter.PulltoRecycleView;
import jiyun.com.openresausechina.inteface.NetWork;
import jiyun.com.openresausechina.model.Model;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    private PullToRefreshRecyclerView pullToRefreshRecyclerView;
    private PulltoRecycleView adapter;
    private List<Model> datas;
    private int pageIndex = 1;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        init();
        sendRetrofit("1", String.valueOf(pageIndex), "10");
        getSupportActionBar().hide();
        textView = (TextView) findViewById(R.id.Sousuo);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });
    }

    private void init() {
        datas = new ArrayList<>();
        pullToRefreshRecyclerView = (PullToRefreshRecyclerView) findViewById(R.id.PullToRecycleView);
        adapter = new PulltoRecycleView(this, datas);
        //设置样式
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        pullToRefreshRecyclerView.setLayoutManager(linearLayoutManager);

        pullToRefreshRecyclerView.setPullToRefreshListener(new PullToRefreshListener() {
            @Override
            public void onRefresh() {
                pullToRefreshRecyclerView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pullToRefreshRecyclerView.setRefreshComplete();
                        datas.clear();
                        pageIndex++;
                        sendRetrofit("1", String.valueOf(pageIndex), "10");
                    }
                }, 1000);
            }

            @Override
            public void onLoadMore() {
                pullToRefreshRecyclerView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pullToRefreshRecyclerView.setLoadMoreComplete();
                        pageIndex++;
                        sendRetrofit("1", String.valueOf(pageIndex), "10");
                    }
                }, 1000);

            }

        });
        //绑定适配器
        pullToRefreshRecyclerView.setAdapter(adapter);

    }

    private void sendRetrofit(String catalog, String pageIndex, String pageSize) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://www.oschina.net/").build();
        NetWork netWork = retrofit.create(NetWork.class);
        Call<ResponseBody> call = netWork.getData(catalog, pageIndex, pageSize);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    //创建解析器工厂
                    SAXParserFactory spf = SAXParserFactory.newInstance();
                    SAXParser sp = null;
                    try {
                        sp = spf.newSAXParser();
                        XMLReader xmlr = sp.getXMLReader();
                        MyDefaultHander hander = new MyDefaultHander();
                        xmlr.setContentHandler(hander);
                        xmlr.parse(new InputSource(response.body().byteStream()));
                        ArrayList<Model> list = hander.getList();


                        if (list != null) {
                            datas.addAll(list);
                        }
                    } catch (ParserConfigurationException e) {
                        e.printStackTrace();
                        if (adapter != null) {
                            adapter.notifyDataSetChanged();
                        }
                    } catch (SAXException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }


            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(MainActivity.this, "加载失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
