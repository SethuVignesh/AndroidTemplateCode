package com.sethu.androidtemplatecode.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import com.sethu.androidtemplatecode.R;
import com.sethu.androidtemplatecode.application.MainApplication;
import com.sethu.androidtemplatecode.component.MainApplicationComponent;
import com.sethu.androidtemplatecode.model.CreateUserResponse;
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

public class RetrofitDemoActivity extends AppCompatActivity implements CreateUserFragment.ICallback {
    MainApplicationComponent component;
    @BindView(R.id.recycler_view)
    public RecyclerView recyclerView;
    @BindView(R.id.getMultipleUsers)
    Button multiUserButton;
    @BindView(R.id.singleUser)
    Button singleUserButton;
    @BindView(R.id.userId)
    EditText userId;
    @BindView(R.id.createUser)
    Button createUserButton;
    @BindView(R.id.scrollView2)
    ScrollView userListScrollView;
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
                    Set<User> userDataManipulation = new HashSet<>();
                    userDataManipulation.addAll(userList);
                    userList.clear();
                    userList.addAll(userDataManipulation);
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

    @OnClick(R.id.createUser)
    void toggleFragmentForUserCreation(){
        if(createUserButton.getText().toString().equals("create user")) {
            createUserButton.setText("hide");
            multiUserButton.setClickable(false);
            singleUserButton.setClickable(false);
            userId.setClickable(false);
            userListScrollView.setVisibility(View.GONE);
        }else{
            userId.setClickable(true);
            multiUserButton.setClickable(true);
            singleUserButton.setClickable(true);
            createUserButton.setText("create user");
            removeFragment();
            userListScrollView.setVisibility(View.VISIBLE);
            return;
        }
        CreateUserFragment createUserFragment = CreateUserFragment.newInstance();
        FragmentManager fragmentManager	= getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment,createUserFragment);
        fragmentTransaction.commit();
    }

    public	void removeFragment()	{
        FragmentManager	fragmentManager	=	getSupportFragmentManager();
        CreateUserFragment createUserFragment =	(CreateUserFragment)fragmentManager.findFragmentById(R.id.fragment);
        if	(createUserFragment	!=	null)	{
            FragmentTransaction	fragmentTransaction	= fragmentManager.beginTransaction();
            fragmentTransaction.remove(createUserFragment).commit();
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }
    String userCreationResponse;
    @Override
    public void createUserCallback(String username, String job) {

        Log.d("Tag",username +" "+job);
        UserApiInterface userApiInterface = getRetrofitSingleUserConverterInstance().create(UserApiInterface.class);
        Call<CreateUserResponse> call = userApiInterface.createUser(username,job);
        call.enqueue(new Callback<CreateUserResponse>() {
            @Override
            public void onResponse(Call<CreateUserResponse> call, Response<CreateUserResponse> response) {
             CreateUserResponse createUserResponse =   response.body();
                userCreationResponse =    "user id  " + createUserResponse.getId() +" \n"
                        + "name  " + createUserResponse.getName()+" \n"
                        + "job  " + createUserResponse.getJob()+" \n"
                        + "creation time  " +createUserResponse.getCreatedAt() +" \n";
                Intent mIntent = new Intent("userCreationResponse");
                mIntent.putExtra("response",userCreationResponse);
                LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(mIntent);
            }
            @Override
            public void onFailure(Call<CreateUserResponse> call, Throwable t) {
                userCreationResponse = "onFailure: "+t ;
                Intent mIntent = new Intent("userCreationResponse");
                mIntent.putExtra("response",userCreationResponse);
                LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(mIntent);
            }
        });

    }
}
