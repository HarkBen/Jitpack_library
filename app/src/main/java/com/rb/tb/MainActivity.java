package com.rb.tb;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.tb.rx_retrofit.http_excuter.HttpClientFactory;
import com.tb.rx_retrofit.http_excuter.RetrofitFactory;
import com.tb.rx_retrofit.http_presenter.JsonBody;
import com.tb.rx_retrofit.http_receiver.HttpResponseListener;
import com.tb.rx_retrofit.http_receiver.TbHttpUtils;
import com.tb.rx_retrofit.tools.cache.CacheModel;

import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RetrofitFactory.Builder.create()
                .setOkHttpClient(new HttpClientFactory.Builder()
                        .setDebug(BuildConfig.DEBUG)
                        .build())
                .init();
        findViewById(R.id.send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                send();
            }
        });

    }

    private void send() {
            TbHttpUtils.getHttpApi().get("http://www.baidu.com", new HttpResponseListener() {
                @Override
                public void onStart() {

                }

                @Override
                public void onResponse(Response<String> response) {

                }

                @Override
                public void onFailure(int errorCode, String message) {

                }

                @Override
                public void onFinish() {

                }

                @Override
                public Context getContext() {
                    return null;
                }

                @Override
                public CacheModel cacheModel() {
                    return null;
                }
            });

    }


}
