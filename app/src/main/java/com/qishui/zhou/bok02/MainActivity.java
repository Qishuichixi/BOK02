package com.qishui.zhou.bok02;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.qishui.zhou.dialoglibrary.CommomDialog;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import static com.qishui.zhou.bok02.R.id.xrv;

public class MainActivity extends AppCompatActivity {

    XRecyclerView rv;
    CommonAdapter mAdapter;
    List<String> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        rv = (XRecyclerView) findViewById(xrv);

        rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        rv.setRefreshProgressStyle(ProgressStyle.BallRotate);
        rv.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);


        for (int i = 0; i < 20; i++) {
            list.add(i + i + "ii-->" + i + i);
        }

        rv.setAdapter(mAdapter = new CommonAdapter<String>(this, R.layout.item, list) {
            @Override
            protected void convert(ViewHolder holder, final String s, final int position) {

                holder.setText(R.id.textView, s);

                Button btn = holder.getView(R.id.btn);
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(mContext, "点击位置：" + position + s, Toast.LENGTH_SHORT).show();

                        new CommomDialog(mContext, R.style.dialog, "您确定删除此信息？", new CommomDialog.OnCloseListener() {
                            @Override
                            public void onClick(Dialog dialog, boolean confirm) {
                                if (confirm) {
                                    Toast.makeText(mContext, "点击确定", Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                }
                            }
                        }).setTitle("温馨提示").show();


                    }
                });
            }
        });

        // rv.setPullRefreshEnabled(false);
        //rv.setLoadingMoreEnabled(false);

        rv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {

                new Handler().postDelayed(new Runnable() {
                    public void run() {

                        Toast.makeText(MainActivity.this, "刷新数据~~~", Toast.LENGTH_SHORT).show();

                        list.add(0, "刷新出来的数据");

                        mAdapter.notifyDataSetChanged();
                        rv.refreshComplete();
                    }

                }, 3000);
            }

            @Override
            public void onLoadMore() {

                new Handler().postDelayed(new Runnable() {
                    public void run() {

                        Toast.makeText(MainActivity.this, "加载数据~~~", Toast.LENGTH_SHORT).show();
                        list.add("加载出来的数据");
                        rv.loadMoreComplete();
                        //rv.setNoMore(true);
                        mAdapter.notifyDataSetChanged();
                    }
                }, 3000);


            }
        });

    }
}
