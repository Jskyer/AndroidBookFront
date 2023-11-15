package com.example.newhelloworld.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newhelloworld.R;
import com.example.newhelloworld.adapter.MyAlbumListAdapter;
import com.example.newhelloworld.databinding.ContributionLayoutBinding;
import com.example.newhelloworld.model.Album;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// 创作视图
public class MyAlbumListActivity extends ViewBindingActivity<ContributionLayoutBinding> {
    private final String TAG = "MyAlbumListActivity";
    private List<Album> albumList;

    private Button createAlbumBtn;

    public static void startAction(Context context){
        Intent intent = new Intent(context, MyAlbumListActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.contribution_layout);

        setRecycler();

        createAlbumBtn = findViewById(R.id.create_album);
        createAlbumBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View inflate = getLayoutInflater().inflate(R.layout.dialog_myalbumlist_activity, null);

                AlertDialog dialog = new AlertDialog.Builder(MyAlbumListActivity.this)
                        .setView(inflate)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {//添加"Yes"按钮
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Log.d(TAG, "open");

                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {//添加取消
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Log.d(TAG, "close");
                            }
                        }).create();

                dialog.show();


            }
        });

    }

    public void setRecycler(){
        albumList = new ArrayList<>();
        for (int i = 0; i < 12; i++){
            Album item = new Album("aaaaaaaaaaa",LocalDateTime.now(), i);
            albumList.add(item);
        }

        RecyclerView rcycView = findViewById(R.id.list_album);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        rcycView.setLayoutManager(manager);

        MyAlbumListAdapter adapter = new MyAlbumListAdapter(albumList, this);
        rcycView.setAdapter(adapter);

    }


}
