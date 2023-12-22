package com.example.newhelloworld.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newhelloworld.R;
import com.example.newhelloworld.adapter.MyAlbumListAdapter;
import com.example.newhelloworld.databinding.ContributionLayoutBinding;
import com.example.newhelloworld.net.MyObserver;
import com.example.newhelloworld.net.MyRetrofitClient;
import com.example.newhelloworld.pojo.Album;
import com.example.newhelloworld.queryVO.Status;
import com.example.newhelloworld.queryVO.userInfo.GetCreateResp;

import java.util.ArrayList;
import java.util.List;

// 创作视图
public class MyAlbumListActivity extends ViewBindingActivity<ContributionLayoutBinding> {
    private final String TAG = "MyAlbumListActivity";
    private List<Album> albumList;

    private RecyclerView rcycView;

    private MyAlbumListAdapter adapter;


    private MyRetrofitClient client;

    private int pageNo = 1;

    private int pageSize = 6;

    private int lastItemId = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.contribution_layout);

        client = new MyRetrofitClient();
        albumList = new ArrayList<>();
        loadData();
        setRecycler();
//        setListener();

    }

    public void setRecycler(){

//        for (int i = 0; i < 12; i++){
//            Album item = new Album("aaaaaaaaaaa",LocalDateTime.now(), i);
//            albumList.add(item);
//        }

        rcycView = findViewById(R.id.list_album);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        rcycView.setLayoutManager(manager);

        adapter = new MyAlbumListAdapter(albumList, this, client);
        rcycView.setAdapter(adapter);

        rcycView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                RecyclerView.Adapter adapter = recyclerView.getAdapter();
                int itemCount = adapter.getItemCount();
                int last = layoutManager.findLastVisibleItemPosition();
                int childCount = recyclerView.getChildCount();

                if(newState == RecyclerView.SCROLL_STATE_IDLE
                        && last == itemCount - 1
                        && childCount > 0){
                    Log.d(TAG, "加载下一页");
                    pageNo++;
                    loadData();
                }

            }
        });
    }

//    public void setListener(){
//        binding.createAlbum.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                View inflate = getLayoutInflater().inflate(R.layout.dialog_myalbumlist_activity, null);
//
//                AlertDialog dialog = new AlertDialog.Builder(MyAlbumListActivity.this)
//                        .setView(inflate)
//                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {//添加"Yes"按钮
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
//                                Log.d(TAG, "open");
//
//                            }
//                        })
//                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {//添加取消
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
//                                Log.d(TAG, "close");
//                            }
//                        }).create();
//
//                dialog.show();
//
//
//            }
//        });
//    }

    public void loadData(){
        client.getMySubscribeAlbum(pageNo, pageSize, new MyObserver<GetCreateResp>() {
            @Override
            public void onSuccss(GetCreateResp getCreateResp) {
                Status status = getCreateResp.getStatus();

                if(status.getCode() == 200){
//                    episodeList.addAll(getHistoryResp.getHistorys());

                    List<Album> albums = getCreateResp.getAlbums();
                    if(albums != null && albums.size() > 0){
                        Integer id = albums.get(0).getAlbum_id();
                        if(id != lastItemId){
                            Log.d(TAG,"load new: " + status.getMsg());
                            adapter.updateList(albums);
                            lastItemId = id;
                        }else{
                            Toast.makeText(MyAlbumListActivity.this, "到底啦~", Toast.LENGTH_SHORT).show();
                        }
                    }

                }else{
                    Log.d(TAG,"status error: " + status.getMsg());
                }
            }
        });

//        client.getCreatedPreviews(pageNo, pageSize, new MyObserver<GetCreateResp>() {
//            @Override
//            public void onSuccss(GetCreateResp getCreateResp) {
//                Status status = getCreateResp.getStatus();
//
//                if(status.getCode() == 200){
////                    episodeList.addAll(getHistoryResp.getHistorys());
//
//                    List<Album> albums = getCreateResp.getAlbums();
//                    if(albums != null && albums.size() > 0){
//                        Integer id = albums.get(0).getAlbum_id();
//                        if(id != lastItemId){
//                            Log.d(TAG,"load new: " + status.getMsg());
//                            adapter.updateList(albums);
//                            lastItemId = id;
//                        }else{
//                            Toast.makeText(MyAlbumListActivity.this, "到底啦~", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//
//                }else{
//                    Log.d(TAG,"status error: " + status.getMsg());
//                }
//            }
//        });
    }



    public static void startAction(Context context){
        Intent intent = new Intent(context, MyAlbumListActivity.class);
        context.startActivity(intent);
    }

}
