package test.bwie.com.myrecyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView recyclerView;
    private ArrayList<String> arr = new ArrayList<>();
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        findViewById(R.id.additem).setOnClickListener(this);
        findViewById(R.id.removoitem).setOnClickListener(this);
        //添加布局  不加入布局树
//        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayout.VERTICAL, false));
        initDate();

    }

    //初始化数据
    private void initDate() {
        for (int i = 0; i < 20; i++) {
            arr.add("item" + i);
        }

        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        //添加分割线
//        recyclerView.addItemDecoration(new DividerItemDecoration(MainActivity.this, DividerItemDecoration.VERTICAL));
        recyclerView.addItemDecoration(new DividerGridItemDecoration(this));

        myAdapter = new MyAdapter(arr, this);
        recyclerView.setAdapter(myAdapter);
        //添加动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        myAdapter.setOnItemClickLitener(new MyAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(MainActivity.this, "点击", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(MainActivity.this, "长按", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.additem:
                myAdapter.add(1);
                break;
            case R.id.removoitem:
                myAdapter.removo(1);
                break;
        }
    }


}
