package com.sethu.androidtemplatecode.view;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.sethu.androidtemplatecode.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class CreateUserFragment extends Fragment {

    static  CreateUserFragment fragment = new CreateUserFragment();
    @BindView(R.id.submit)
    Button submit;
    @BindView(R.id.username)
    EditText userName;
    @BindView(R.id.responseText)
    TextView responseText ;
    @BindView(R.id.job)
    EditText job;
    private Unbinder unbinder;
    ICallback mListener;

    static CreateUserFragment newInstance(){
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_user, container, false);
        unbinder = ButterKnife.bind(this, view);
        LocalBroadcastManager.getInstance(getContext()).registerReceiver(broadcastReceiver ,new IntentFilter("userCreationResponse"));
        return view;
    }

    @OnClick(R.id.submit)
    void createUser(){
        if (!TextUtils.isEmpty(userName.getText().toString())
                && !TextUtils.isEmpty(job.getText().toString())) {
            mListener.createUserCallback(userName.getText().toString(), job.getText().toString());
            userName.setText("");job.setText("");
        }else{
            Log.e("TAG", "createUserButton: empty parameters ");
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ICallback) {
            mListener = (ICallback) context;
        } else {
            throw new ClassCastException(context.toString() + "please implement ICallback Interface");
        }
    }

    @Override public void onDestroyView() {
        super.onDestroyView();
        LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(broadcastReceiver);
        unbinder.unbind();
    }

    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Intent resultIntent = intent;
            String data =resultIntent.getStringExtra("response") ;
            responseText.setText(data);
        }
    };

    interface ICallback{
        void createUserCallback(String username ,String job);
    }
}