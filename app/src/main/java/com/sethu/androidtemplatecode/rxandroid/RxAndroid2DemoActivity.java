package com.sethu.androidtemplatecode.rxandroid;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import com.sethu.androidtemplatecode.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class RxAndroid2DemoActivity extends AppCompatActivity {
    @BindView(R.id.text)
    TextView textView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_android2_demo);
        ButterKnife.bind(this);
        RxAndroidExample.sampleExample(textView);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //in order to unsubscribe from Observable,
        // so that it does not get updated after activity is destroyed that leads to Exception
        RxAndroidExample.beforeDestroy();
    }
}