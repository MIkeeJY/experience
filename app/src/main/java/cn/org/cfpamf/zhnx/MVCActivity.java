package cn.org.cfpamf.zhnx;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import cn.org.cfpamf.data.reflection.PerformMethodUtil;
import mvc.zzy.cusrecyclerview.CellModel;
import mvc.zzy.cusrecyclerview.CusRecyclerView;
import mvc.zzy.cusrecyclerview.DataListAdapter;
import mvc.zzy.cusrecyclerview.DataSources;

public class MVCActivity extends AppCompatActivity {

    CusRecyclerView cusRecyclerView;

    CellModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvc);
        initView();
    }

    private void initView() {

        DataSources dataResources = new DemoDataSources();

        cusRecyclerView = (CusRecyclerView) findViewById(R.id.cusRecyclerView);

        cusRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        cusRecyclerView.setDataResources(dataResources);

        dataResources.getDataListAdapter().setOnItemClickListener(new DataListAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                model = (DemoCellModel) view.getTag();
                PerformMethodUtil.getInstance().performMethodOnMainThread(MVCActivity.this, "clickCell", model);
                if (model.layout == R.layout.cellt) {
                    PerformMethodUtil.getInstance().performMethodOnMainThread(MVCActivity.this, "clickCellt", model);
                }
            }
        });
    }

    public void clickCell(DemoCellModel model) {
        Toast.makeText(getApplicationContext(), model.layout, Toast.LENGTH_LONG).show();
    }

    public void clickCellt(DemoCellModel model) {
        Toast.makeText(getApplicationContext(), model.layout, Toast.LENGTH_LONG).show();
        startActivity(new Intent(MVCActivity.this, MVVMActivity.class));
    }
}
