package jiyun.com.openresausechina.view;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Toast;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import jiyun.com.openresausechina.R;
import jiyun.com.openresausechina.inteface.NetWork;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by think on 2017/4/5.
 */

public class Main2Activity extends Activity {

    private WebView webView;
    private String url;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        webView = (WebView) findViewById(R.id.webView);
        id = getIntent().getStringExtra("id");
        sendRequest();
    }

    private void sendRequest(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://www.oschina.net/").build();
        NetWork netWork = retrofit.create(NetWork.class);
        Call<ResponseBody> call = netWork.getDatas(id);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    // 先构建sax解析器工厂实例
                    SAXParserFactory spf = SAXParserFactory.newInstance();
                    // 通过解析器工厂获取解析器对象
                    SAXParser sp = null;
                    try {
                        sp = spf.newSAXParser();
                        // 获取读取事件源实例
                        XMLReader xmlr = sp.getXMLReader();
                        // 实例化事件处理器对象
                        MyDefaultHanderTwo handler = new MyDefaultHanderTwo();
                        // 将事件处理器设置给事件源
                        xmlr.setContentHandler(handler);
                        // 录入数据 就是指向那个文件
                        xmlr.parse(new InputSource(response.body().byteStream()));
                        // xmlr.parse("src/cat.xml");
                        url = handler.url();
                        webView.loadUrl(url);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(Main2Activity.this, "fff", Toast.LENGTH_SHORT).show();
            }
        });
    }


   }

