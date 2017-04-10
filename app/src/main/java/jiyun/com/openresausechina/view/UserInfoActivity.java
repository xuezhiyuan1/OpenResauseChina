package jiyun.com.openresausechina.view;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.thoughtworks.xstream.XStream;

import java.io.InputStream;

import jiyun.com.openresausechina.R;
import jiyun.com.openresausechina.inteface.NetWork;
import jiyun.com.openresausechina.model.UserInfoBean;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by think on 2017/4/9.
 */

public class UserInfoActivity extends Activity{
    ImageView imageView;
    TextView textViewdata,textViewdizhi,textViewpingtai,textViewlingyu,textViewsign;
    private String info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        imageView = (ImageView) findViewById(R.id.backBtninfo);
        textViewdata = (TextView) findViewById(R.id.datas);
        textViewdizhi = (TextView) findViewById(R.id.dizhi);
        textViewpingtai = (TextView) findViewById(R.id.pingtai);
        textViewlingyu = (TextView) findViewById(R.id.lingyu);
        textViewsign = (TextView) findViewById(R.id.sign);
        info = getIntent().getStringExtra("userInfo");
    }

    private void sendRequset(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://www.oschina.net/").build();
        NetWork netWork = retrofit.create(NetWork.class);
        Call<ResponseBody> call = netWork.getUserInfoData(info);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                ResponseBody body = response.body();
                InputStream inputStream = body.byteStream();
                XStream xStream = new XStream();
                xStream.alias("oschina", UserInfoBean.class);
                UserInfoBean bean = (UserInfoBean) xStream.fromXML(inputStream);

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }


}
