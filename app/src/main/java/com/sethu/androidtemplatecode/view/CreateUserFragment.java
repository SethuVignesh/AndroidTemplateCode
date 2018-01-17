package com.sethu.androidtemplatecode.view;

import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import com.sethu.androidtemplatecode.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class CreateUserFragment extends Fragment {

    @BindView(R.id.submit)
    Button submit;
    @BindView(R.id.username)
    EditText userName;
    @BindView(R.id.job)
    EditText job;
    private Unbinder unbinder;
    ICallback mListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_user, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.submit)
    void createUser(){
        mListener.createUserCallback(userName.getText().toString(),job.getText().toString());
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
        unbinder.unbind();
    }

    interface ICallback{
        void createUserCallback(String username ,String job);
    }
}