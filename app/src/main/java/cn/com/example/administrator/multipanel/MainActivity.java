package cn.com.example.administrator.multipanel;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView list;
    private ListView list_land;
    private FragmentManager manager;
    private MyFragment mf1;
    private Bundle bundle;
    private String[] str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WindowManager windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        int width = windowManager.getDefaultDisplay().getWidth();
        int height = windowManager.getDefaultDisplay().getHeight();

        if (width > height) {
            //横屏
            initLand();
        } else {
            init();
        }
    }

    private void init() {
       final String[] str = {"三国演义", "西游记", "水浒传", "红楼梦"};
        list = (ListView) findViewById(R.id.listView);
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_list_item_1, str);
        list.setAdapter(aa);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent =
                        new Intent(MainActivity.this, TestActivity.class);
                intent.putExtra("str", str[position]);
                startActivity(intent);

            }
        });
    }

    private void initLand() {
        str = new String[]{"三国演义", "西游记", "水浒传", "红楼梦"};
        list_land = (ListView) findViewById(R.id.listView_land);
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_list_item_1, str);
        list_land.setAdapter(aa);
        list_land.setOnItemClickListener(this);

        manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        mf1 = new MyFragment();
        bundle = new Bundle();
        bundle.putString("str", "三国演义");
        mf1.setArguments(bundle);
        transaction.add(R.id.fragment, mf1);
        transaction.commit();

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        FragmentTransaction transaction = manager.beginTransaction();
        MyFragment myFragment = new MyFragment();
        bundle.putString("str", str[position]);
        myFragment.setArguments(bundle);
        transaction.replace(R.id.fragment, myFragment);
        transaction.commit();
    }
}
