package com.sethu.androidtemplatecode.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import com.sethu.androidtemplatecode.R;
import com.sethu.androidtemplatecode.application.MainApplication;
import com.sethu.androidtemplatecode.component.MainApplicationComponent;
import com.sethu.androidtemplatecode.model.User;
import com.sethu.androidtemplatecode.retrofitapiinterface.UserApiInterface;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RetrofitDemoActivity extends AppCompatActivity {
    MainApplicationComponent component;
    @BindView(R.id.recycler_view)
    public RecyclerView recyclerView;
    private UserAdapter userAdapter;
    private List userList = new ArrayList<>();
    private String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit_demo);
        ButterKnife.bind(this);
        userAdapter = new UserAdapter(userList,getApplicationContext());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(userAdapter);
        getMainApplicationComponent();
        getUsersInfo();
    }

    void  getMainApplicationComponent(){
        component = MainApplication.getMainApplicationComponent();
    }

    Retrofit getRetrofitInstance(){
       return component.getRetrofitInstance();
    }

    void getUsersInfo(){

        UserApiInterface userApiInterface = getRetrofitInstance().create(UserApiInterface.class);
        Call<List<User>> call = userApiInterface.getUsersInfo();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                Log.d(TAG, "onResponse: call "+call+"  response "+response);
                ArrayList<User> arrayList = (ArrayList<User>) response.body();
                userList.addAll(arrayList);
                userAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t);
            }
        });
    }

}
