package jiyun.com.openresausechina.view;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.thoughtworks.xstream.XStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import jiyun.com.openresausechina.R;
import jiyun.com.openresausechina.inteface.NetWork;
import jiyun.com.openresausechina.model.AutoLogin;
import jiyun.com.openresausechina.model.LoginBean;
import jiyun.com.openresausechina.model.db.DbManger;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity {

    ImageView imageView;
    Button button;
    EditText editTextuserName,editTextPwd;
    private String username;
    private String pwd;
    private List<LoginBean.UserBean> datas;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private CheckBox checkBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    private void init() {
        datas = new ArrayList<>();
        checkBox = (CheckBox) findViewById(R.id.checkbox);
        imageView = (ImageView) findViewById(R.id.backBtn);
        button = (Button) findViewById(R.id.login_btn);
        editTextuserName = (EditText) findViewById(R.id.name_edits);
        editTextPwd = (EditText) findViewById(R.id.pwd_edit);
        sharedPreferences = getSharedPreferences("data",MODE_PRIVATE);
        editor = sharedPreferences.edit();
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = editTextuserName.getText().toString();
                pwd = editTextPwd.getText().toString();
                sendRequest(username,pwd);
                if(checkBox.isChecked()){
                    editor.putBoolean("isAutoLogin",false);
                    editor.commit();
                }else {
                    editor.putBoolean("isAutoLogin",true);
                    editor.commit();
                }
            }
        });
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){

                case 1:
                    Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    String s =  msg.obj.toString();
                    intent.putExtra("userInfo",s);
                    startActivity(intent);
                    break;
                case 0:
                    Toast.makeText(LoginActivity.this,"登录失败",Toast.LENGTH_LONG).show();
                    break;
            }
        }
    };


    private void sendRequest(final String username, final String pwd){
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://www.oschina.net/").build();
        NetWork netWork = retrofit.create(NetWork.class);
        Call<ResponseBody> call = netWork.getLoginData(username, pwd, "1");
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                ResponseBody body = response.body();
                InputStream inputStream = body.byteStream();
                XStream xStream = new XStream();
                xStream.alias("oschina",LoginBean.class);
                LoginBean bean = (LoginBean) xStream.fromXML(inputStream);
                LoginBean.ResultBean result = bean.getResult();
                String errorCode = result.getErrorCode();
                datas.add(bean.getUser());
                //obtainMessage  获取一个消息   sendToTarget发送一个消息
                if(errorCode.equals("1")){
                    handler.obtainMessage(1,bean.getUser().getUid()).sendToTarget();
                }else {
                    handler.obtainMessage(0,"登录失败").sendToTarget();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }


}
