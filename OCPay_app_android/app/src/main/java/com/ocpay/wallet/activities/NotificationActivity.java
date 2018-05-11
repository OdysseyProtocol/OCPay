package com.ocpay.wallet.activities;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ocpay.wallet.R;
import com.ocpay.wallet.adapter.NotificationAdapter;
import com.ocpay.wallet.bean.NotificationBean;
import com.ocpay.wallet.databinding.ActivityNotificationBinding;
import com.snow.commonlibrary.recycleview.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

public class NotificationActivity extends BaseActivity implements View.OnClickListener {


    private ActivityNotificationBinding binding;
    private List<NotificationBean> notificationList;
    private NotificationAdapter notificationAdapter;


    public static void startNotificationActivity(Activity activity) {

        Intent intent = new Intent(activity, NotificationActivity.class);
        activity.startActivity(intent);

    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(NotificationActivity.this, R.layout.activity_notification);
        initActionBar();
        initData();
        initView();
        initListener();

    }

    private void initListener() {
        notificationAdapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView.Adapter adapter, Object data, int position) {
                if (data instanceof NotificationBean) {
                    NotificationDetailActivity.startNotificationDetailActivity(NotificationActivity.this, (NotificationBean) data);
                    notificationList.get(position).setRead(true);
                    notificationAdapter.notifyDataSetChanged();
                }
            }
        });


    }

    private void initActionBar() {
        binding.includeActionBar.actionBarTitle.setText(R.string.activity_notification);
        binding.includeActionBar.toolbarMenuIcon.setVisibility(View.GONE);
        binding.includeActionBar.tbTvMenu.setText(R.string.activity_notification_read_all);
        binding.includeActionBar.tbTvMenu.setVisibility(View.VISIBLE);
        binding.includeActionBar.tbTvMenu.setOnClickListener(this);
        binding.includeActionBar.llToolbar.setBackgroundColor(getResources().getColor(R.color.white));
        binding.includeActionBar.actionBarTitle.setTextColor(getResources().getColor(R.color.color_text_main));
        binding.includeActionBar.ivBack.setImageResource(R.mipmap.ic_back);
        binding.includeActionBar.ivBack.setOnClickListener(this);


    }

    private void initData() {
        notificationList = new ArrayList<>();
        notificationList.add(new NotificationBean("ttx", "jkfjkl\n fsdfsdf\n fsdfsd\n", System.currentTimeMillis() + "", true));
        notificationList.add(new NotificationBean("bbfsd", "jkfjkl\n fsdfsdf\n fsdfsd\n", System.currentTimeMillis() + "", true));
        notificationList.add(new NotificationBean("ksfd", "jkfjkl\n fsdfsdf\n fsdfsd\n", System.currentTimeMillis() + "", false));

    }

    private void initView() {
        notificationAdapter = new NotificationAdapter(NotificationActivity.this);
        notificationAdapter.setData(notificationList);
        binding.rlNotification.setAdapter(notificationAdapter);
        binding.rlNotification.setLayoutManager(new LinearLayoutManager(NotificationActivity.this));
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.tb_tv_menu:
                readAll();
                break;
            case R.id.iv_back:
                finish();
                break;

        }

    }

    private void readAll() {
        if (notificationList != null && notificationList.size() > 0) {
            for (int i = 0; i < notificationList.size(); i++) {
                notificationList.get(i).setRead(true);
            }
        }
        if (notificationAdapter != null) notificationAdapter.notifyDataSetChanged();
    }
}
