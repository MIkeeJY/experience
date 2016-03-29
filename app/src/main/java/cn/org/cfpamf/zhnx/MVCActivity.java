package cn.org.cfpamf.zhnx;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import mvc.zzy.cusrecyclerview.CellModel;
import mvc.zzy.cusrecyclerview.CusRecyclerView;
import mvc.zzy.cusrecyclerview.DataListAdapter;
import mvc.zzy.cusrecyclerview.DataSources;

public class MVCActivity extends AppCompatActivity {

    CusRecyclerView cusRecyclerView;

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
                CellModel model = (DemoCellModel) view.getTag();
                Toast.makeText(getApplicationContext(), model.layout, Toast.LENGTH_LONG).show();
                if (model.layout == R.layout.cellt) {
                    startActivity(new Intent(MVCActivity.this, MVVMActivity.class));
                }
            }
        });
    }
}
