package cn.com.example.administrator.multipanel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);


       TextView tv = (TextView) findViewById(R.id.textView);

       String msg =  getIntent().getStringExtra("str");

        tv.setText(msg);

    }
}
