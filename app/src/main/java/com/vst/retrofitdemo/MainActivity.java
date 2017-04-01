package com.vst.retrofitdemo;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.vst.retrofitdemo.bean.Result;
import com.vst.retrofitdemo.bean.User;

import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * xml解析使用：compile 'com.squareup.retrofit2:converter-simplexml:2.2.0'
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = MainActivity.class.getSimpleName();
    private Button btn_get;
    private Button btn_get2;
    private Button btn_post1;
    private Button btn_post2;
    private Button btn_post3;
    private Button btn_post4;
    private Button btn_post5;
    private Button btn_post6;
    private Button btn_post7;
    private Button btn_post8;
    private Button btn_post9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        btn_get = (Button) findViewById(R.id.btn_get);
        btn_get2 = (Button) findViewById(R.id.btn_get2);
        btn_post1 = (Button) findViewById(R.id.btn_post1);
        btn_post2 = (Button) findViewById(R.id.btn_post2);
        btn_post3 = (Button) findViewById(R.id.btn_post3);
        btn_post4 = (Button) findViewById(R.id.btn_post4);
        btn_post5 = (Button) findViewById(R.id.btn_post5);
        btn_post6 = (Button) findViewById(R.id.btn_post6);

        btn_get.setOnClickListener(this);
        btn_get2.setOnClickListener(this);
        btn_post1.setOnClickListener(this);
        btn_post2.setOnClickListener(this);
        btn_post3.setOnClickListener(this);
        btn_post4.setOnClickListener(this);
        btn_post5.setOnClickListener(this);
        btn_post6.setOnClickListener(this);
        btn_post7 = (Button) findViewById(R.id.btn_post7);
        btn_post7.setOnClickListener(this);
        btn_post8 = (Button) findViewById(R.id.btn_post8);
        btn_post8.setOnClickListener(this);
        btn_post9 = (Button) findViewById(R.id.btn_post9);
        btn_post9.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_get:
                getInfo();
                break;
            case R.id.btn_get2:
                get2();
                break;
            case R.id.btn_post1:
                postInfo();
                break;
            case R.id.btn_post2:
                post2();
                break;
            case R.id.btn_post3:
                post3();
                break;
            case R.id.btn_post4:
                post4();
                break;
            case R.id.btn_post5:
                post5();
                break;
            case R.id.btn_post6:
                post6();
                break;
            case R.id.btn_post7:
                post7();
                break;
            case R.id.btn_post8:
                post8();
                break;
            case R.id.btn_post9:
                getInfo2();
                break;
        }
    }


    //get请求
    public void getInfo() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(VstService.HOST).build();
        VstService service = retrofit.create(VstService.class);
        Call<ResponseBody> call = service.getInfo();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Log.i(TAG, response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.i(TAG, t.getMessage());
            }
        });
    }

    //get请求带参数
    private void get2() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(VstService.HOST).build();
        VstService service = retrofit.create(VstService.class);
        Call<ResponseBody> call = service.get2("jacky", 29, "womenn");
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Log.i(TAG, response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.i(TAG, t.getMessage());
            }
        });
    }

    //post请求，返回使用HashMap接收
    public void postInfo() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(VstService.HOST)
                .addConverterFactory(new Converter.Factory() {
                    @Override
                    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
                        return new Converter<ResponseBody, String>() {
                            @Override
                            public String convert(ResponseBody value) throws IOException {
                                return value.string();
                            }
                        };
                    }
                })
                .build();
        VstService service = retrofit.create(VstService.class);
        Call<String> call = service.post1("tom", 28, "man");
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.i(TAG, response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    //post请求，返回为实体，实体中使用泛型
    public void post2() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(VstService.HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        VstService service = retrofit.create(VstService.class);
        Call<HashMap<String, String>> call = service.post2("tom", 28, "man");
        call.enqueue(new Callback<HashMap<String, String>>() {
            @Override
            public void onResponse(Call<HashMap<String, String>> call, Response<HashMap<String, String>> response) {
                Log.i(TAG, response.body().get("userName"));
            }

            @Override
            public void onFailure(Call<HashMap<String, String>> call, Throwable t) {
                Log.i(TAG, t.getMessage());
            }
        });
    }

    ////post请求，返回为实体，实体中使用泛型
    public void post3() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(VstService.HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        VstService service = retrofit.create(VstService.class);
        Call<Result<ArrayList<User>>> call = service.post3("success22");
        call.enqueue(new Callback<Result<ArrayList<User>>>() {
            @Override
            public void onResponse(Call<Result<ArrayList<User>>> call, Response<Result<ArrayList<User>>> response) {
                Result<ArrayList<User>> rs = response.body();

                Log.i(TAG, rs.msg);
                Log.i(TAG, rs.rs.get(0).getUserName());
            }

            @Override
            public void onFailure(Call<Result<ArrayList<User>>> call, Throwable t) {
                Log.i(TAG, t.getMessage());
            }
        });
    }

    //对请求的地址进行变量处理
    public void post4() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(VstService.HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        VstService service = retrofit.create(VstService.class);
        Call<Result<ArrayList<User>>> call = service.post4("account", "success22");
        call.enqueue(new Callback<Result<ArrayList<User>>>() {
            @Override
            public void onResponse(Call<Result<ArrayList<User>>> call, Response<Result<ArrayList<User>>> response) {
                Result<ArrayList<User>> rs = response.body();

                Log.i(TAG, rs.msg);
                Log.i(TAG, rs.rs.get(0).getUserName());
            }

            @Override
            public void onFailure(Call<Result<ArrayList<User>>> call, Throwable t) {
                Log.i(TAG, t.getMessage());
            }
        });
    }

    //初步封装
    public void post5() {
        RetrofitWrapper retrofitWrapper = RetrofitWrapper.getInstance();
        VstService vstService = retrofitWrapper.create(VstService.class);
        Call<Result<ArrayList<User>>> call = vstService.post3("success22");
        call.enqueue(new Callback<Result<ArrayList<User>>>() {

            @Override
            public void onResponse(Call<Result<ArrayList<User>>> call, Response<Result<ArrayList<User>>> response) {
                Result<ArrayList<User>> rs = response.body();
                Log.i(TAG, rs.msg);
                Log.i(TAG, rs.rs.get(0).getUserName());
            }

            @Override
            public void onFailure(Call<Result<ArrayList<User>>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    //多参数使用map设置
    public void post6() {
        VstService vstService = RetrofitWrapper.getInstance().create(VstService.class);
        Map<String, String> map = new Hashtable<>();
        map.put("userName", "tooom");
        map.put("age", "39");
        map.put("gender", "mennn");
        Call<HashMap<String, String>> call = vstService.post6(map);
        call.enqueue(new Callback<HashMap<String, String>>() {
            @Override
            public void onResponse(Call<HashMap<String, String>> call, Response<HashMap<String, String>> response) {
                Log.i(TAG, response.body().get("userName"));
            }

            @Override
            public void onFailure(Call<HashMap<String, String>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    //文件上传
    //二进制格式：application/octet-stream，可以传任意格式的文件
    public void post7() {
        VstService service = RetrofitWrapper.getInstance().create(VstService.class);
        File file = new File(Environment.getExternalStorageDirectory(), "image01.jpg");
        RequestBody requestFile = RequestBody.create(MediaType.parse("image/jpeg"), file);
        MultipartBody.Part fileBody = MultipartBody.Part.createFormData("upImage", file.getName(), requestFile);

        String msg = "This is a description";
        RequestBody msgBody = RequestBody.create(MediaType.parse("multipart/form-data"), msg);
        Call<ResponseBody> call = service.upload(msgBody, fileBody);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Log.i(TAG, response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    //提价json格式的请求参数
    public void post8() {
        try {
            VstService service = RetrofitWrapper.getInstance().create(VstService.class);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("userName", "TTTom");
            jsonObject.put("age", 16);
            RequestBody requstBody = RequestBody.create(MediaType.parse("application/json"), jsonObject.toString());
            Call<ResponseBody> call = service.post8(requstBody);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    try {
                        Log.i(TAG, response.body().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    t.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //get请求
    public void getInfo2() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(VstService.HOST)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        VstService service = retrofit.create(VstService.class);
        service.getInfo2().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResponseBody>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("zwy", e.getMessage());
                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            Log.i("zwy", responseBody.string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });

    }
}
