package com.example.newhelloworld;

import android.media.metrics.Event;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.newhelloworld.activity.MyAlbumListActivity;
import com.example.newhelloworld.activity.HistoryActivity;
import com.example.newhelloworld.activity.PersonalDetailActivity;
import com.example.newhelloworld.activity.SettingActivity;
import com.example.newhelloworld.adapter.CFragmentAdapter;
import com.example.newhelloworld.event.MsgChangeAvatar;
import com.example.newhelloworld.util.PreferenceUtil;
import com.example.newhelloworld.util.ResourceUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class CFragment extends Fragment{
    public static final String TAG = "CFragment";
    private View view;

    private ImageView avatarView;

    private TextView nameView;

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onChangeAvatar(MsgChangeAvatar msg){
        Log.d(TAG, "onChangeAvatar");

        if(msg.getPath() != null){
            Glide.with(this)
                    .load(ResourceUtil.getUserAvatarPath(msg.getPath()))
                    .centerCrop()
                    .into(avatarView);
        }

        EventBus.getDefault().removeStickyEvent(this);
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.personal_layout, container, false);
        ListView listView = view.findViewById(R.id.list_personal);

        // 创建图像数组和文本数组
        Integer[] imageArray = {R.drawable.personal_space, R.drawable.personal_contribution, R.drawable.personal_history,R.drawable.personal_setting}; // 替换为您的图片资源
        String[] textArray = {"个人主页", "创作中心", "浏览历史", "设置"}; // 栏目名

        // 创建自定义适配器
        CFragmentAdapter adapter = new CFragmentAdapter(getActivity(), textArray, imageArray);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((adapterView, view1, position, l) -> {
            // 根据点击的位置跳转到相应的页面
            // 这里具体页面还没写，所以还没完善这里
            switch (position) {
                case 0:
                    // 跳转到column 1
                    PersonalDetailActivity.startAction(getActivity());
//                    Toast.makeText(getActivity(), "0", Toast.LENGTH_LONG).show();
                    break;
                case 1:
                    // 跳转到column 2
                    MyAlbumListActivity.startAction(getActivity());
//                    Toast.makeText(getActivity(), "1", Toast.LENGTH_LONG).show();
                    break;
                case 2:
                    // 跳转到column 3
                    HistoryActivity.startAction(getActivity());
//                    Toast.makeText(getActivity(), "2", Toast.LENGTH_LONG).show();
                    break;
                case 3:
                    // 跳转到column 4
                    SettingActivity.startAction(getActivity());
//                    Toast.makeText(getActivity(), "3", Toast.LENGTH_LONG).show();
                    break;
            }
        });


        nameView = view.findViewById(R.id.username);
        avatarView = view.findViewById(R.id.avatar);

        bindPersonalInfo();


        EventBus.getDefault().register(this);

        return view;
    }

    public void bindPersonalInfo(){
        //绑定头像
        String avatarPath = PreferenceUtil.getString(MyApplication.getContext(), PreferenceUtil.KEY_USER_AVATAR, null);
        if (avatarPath != null){
            Glide.with(this)
                    .load(ResourceUtil.getUserAvatarPath(avatarPath))
                    .centerCrop()
                    .into(avatarView);
        }

        //绑定名字
        String name = PreferenceUtil.getString(MyApplication.getContext(), PreferenceUtil.KEY_USER_NAME);
        if(name != null){
            nameView.setText(name);
        }

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    //    @Override
//    public void onClick(View view) {
//        int id = view.getId();
//        if(id == R.id.goto_history){
//            HistoryActivity.startAction(getActivity());
//        }else if(id == R.id.goto_setting){
//            SettingActivity.startAction(getActivity());
//        }else if(id == R.id.goto_space){
//            PersonalDetailActivity.startAction(getActivity());
//        }
//    }


}
