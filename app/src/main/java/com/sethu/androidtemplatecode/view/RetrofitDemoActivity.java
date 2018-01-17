package com.sethu.androidtemplatecode.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.sethu.androidtemplatecode.R;
import com.sethu.androidtemplatecode.application.MainApplication;
import com.sethu.androidtemplatecode.component.MainApplicationComponent;
import com.sethu.androidtemplatecode.model.User;
import com.sethu.androidtemplatecode.model.UserData;
import com.sethu.androidtemplatecode.retrofitapiinterface.UserApiInterface;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RetrofitDemoActivity extends AppCompatActivity {
    MainApplicationComponent component;
    @BindView(R.id.recycler_view)
    public RecyclerView recyclerView;
    @BindView(R.id.getMultipleUsers)
    Button multiUserButton;
    @BindView(R.id.singleUser)
    Button singleUserButton;
    @BindView(R.id.userId)
    EditText userId;
    private UserAdapter userAdapter;
    private List<User> userList = new ArrayList<User>();
    private String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit_demo);
        ButterKnife.bind(this);
        userAdapter = new UserAdapter(userList, getApplicationContext());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(userAdapter);
        getMainApplicationComponent();
        multiUserButton.setText("Get User List");
    }

    void getMainApplicationComponent() {
        component = MainApplication.getMainApplicationComponent();
    }

    Retrofit getRetrofitInstance() {
        return component.getRetrofitMultiUserConverterInstance();
    }

    Retrofit getRetrofitSingleUserConverterInstance() {
        return component.getRetrofitSingleUserConverterInstance();
    }

    @OnClick(R.id.getMultipleUsers)
    void getMultipleUserInfo() {
        getUsersInfo();
    }

    void getUsersInfo() {
        final UserApiInterface userApiInterface = getRetrofitInstance().create(UserApiInterface.class);
        Call<List<User>> call = userApiInterface.getUsersInfo();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                Log.d(TAG, "onResponse: call " + call + "  response " + response);
                userList.addAll(response.body());
                Set<User> userDataManipulation = new HashSet<>();
                userDataManipulation.addAll(userList);
                userList.removeAll(userDataManipulation);
                userList.addAll(userDataManipulation);
                sortData(userList);
                userAdapter.notifyDataSetChanged();
            }
            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t);
            }
        });
    }

    private void sortData(List<User> userList) {
        Collections.sort(userList, new Comparator<User>() {
            @Override
            public int compare(User lhs, User rhs) {
                return Integer.parseInt(lhs.getUserId()) > Integer.parseInt(rhs.getUserId()) ? -1 : (Integer.parseInt(lhs.getUserId()) < Integer.parseInt(rhs.getUserId()))  ? 1 : 0;
            }
        });
        Collections.reverse(userList);
    }

    @OnClick(R.id.singleUser)
    void getSingleUserInfo() {
        String id = userId.getText().toString();
        if (id != null && id.length() > 0) {
            try {
                getUserInfo(id);
            } catch (Exception e) {
                Log.e(TAG, "Exception getSingleUserInfo: " + e.toString());
            }
        }
        Log.d(TAG, " getSingleUserInfo: " + id);
    }

    void getUserInfo(String id) {
        final UserApiInterface userApiInterface = getRetrofitSingleUserConverterInstance().create(UserApiInterface.class);
        Call<UserData> call = userApiInterface.getUserInfo(id);
        call.enqueue(new Callback<UserData>() {
            @Override
            public void onResponse(Call<UserData> call, Response<UserData> response) {
                try {
                    UserData userData = response.body();
                    userList.add(userData.getData());
                    Set<User> s= new HashSet<>();
                    s.addAll(userList);
                    userList.clear();
                    userList.addAll(s);
                    sortData(userList);
                    userAdapter.notifyDataSetChanged();
                }catch (Exception e){
                    Log.e(TAG, "onResponse: "+ e );
                }
                Log.d(TAG, "onResponse: call " + call + "  response " + response);
            }
            @Override
            public void onFailure(Call<UserData> call, Throwable t) {
                Log.d(TAG, "getUserInfo onFailure: " + t);
            }
        });
    }
}
