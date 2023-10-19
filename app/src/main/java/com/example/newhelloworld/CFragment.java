package com.example.newhelloworld;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.newhelloworld.activity.HistoryActivity;
import com.example.newhelloworld.activity.PersonalDetailActivity;
import com.example.newhelloworld.activity.SettingActivity;

public class CFragment extends Fragment implements View.OnClickListener{
    private ImageView btn_space;
    private ImageView btn_history;
    private ImageView btn_setting;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.personal_layout, container, false);
        btn_space = view.findViewById(R.id.goto_space);
        btn_space.setOnClickListener(this);

        btn_history = view.findViewById(R.id.goto_history);
        btn_history.setOnClickListener(this);

        btn_setting = view.findViewById(R.id.goto_setting);
        btn_setting.setOnClickListener(this);

        return view;
//        return inflater.inflate(R.layout.fragment_c, container, false);
    }

//    public void initListeners(){
//        btn_space = getActivity().findViewById(R.id.goto_space);
//        btn_space.setOnClickListener(this);
//
//        btn_history = getActivity().findViewById(R.id.goto_history);
//        btn_history.setOnClickListener(this);
//
//        btn_setting = getActivity().findViewById(R.id.goto_setting);
//        btn_setting.setOnClickListener(this);
//    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if(id == R.id.goto_history){
            HistoryActivity.startAction(getActivity());
        }else if(id == R.id.goto_setting){
            SettingActivity.startAction(getActivity());
        }else if(id == R.id.goto_space){
            PersonalDetailActivity.startAction(getActivity());
        }
    }
}
