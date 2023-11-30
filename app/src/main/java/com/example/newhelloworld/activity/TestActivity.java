package com.example.newhelloworld.activity;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.newhelloworld.R;
import com.example.newhelloworld.net.MyObserver;
import com.example.newhelloworld.net.MyRetrofitClient;
import com.example.newhelloworld.queryVO.GetCommentsResp;
import com.example.newhelloworld.queryVO.StatusResp;
import com.example.newhelloworld.queryVO.album.GetAlbumInfoResp;
import com.example.newhelloworld.queryVO.podcast.GetPodcastOffiRecResp;
import com.example.newhelloworld.queryVO.podcast.GetPodcastRankPreviewResp;
import com.example.newhelloworld.queryVO.podcast.GetPodcastResp;
import com.example.newhelloworld.queryVO.podcast.UploadPodcastResp;
import com.example.newhelloworld.queryVO.signIn.ResetPassResp;
import com.example.newhelloworld.queryVO.userInfo.GetCreateResp;
import com.example.newhelloworld.queryVO.userInfo.GetHistoryResp;
import com.example.newhelloworld.queryVO.userInfo.GetSubscribeResp;
import com.example.newhelloworld.queryVO.userInfo.IntegerResp;

import java.util.HashMap;
import java.util.Map;

public class TestActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        MyRetrofitClient client = new MyRetrofitClient();

//        client.uploadPodcast(13, 300, "xyz", "/pod/test1.jpg",
//                "/audio/test123.mp3", new MyObserver<UploadPodcastResp>() {
//            @Override
//            public void onSuccss(UploadPodcastResp uploadPodcastResp) {
//                Log.d("rxjava", "onSuccss");
//                Log.d("rxjava", uploadPodcastResp.getStatus() + "");
//            }
//        });

        client.delPodcast(21, new MyObserver<StatusResp>() {
            @Override
            public void onSuccss(StatusResp statusResp) {
                Log.d("rxjava", "onSuccss");
                Log.d("rxjava", statusResp.getStatus() + "");
            }
        });



//        client.getPodcast(1, new MyObserver<GetPodcastResp>() {
//            @Override
//            public void onSuccss(GetPodcastResp getPodcastResp) {
//                Log.d("rxjava", "onSuccss");
//                Log.d("rxjava", getPodcastResp.getStatus() + "");
//            }
//        });
//
//        client.getPodcastOffiRec(new MyObserver<GetPodcastOffiRecResp>() {
//            @Override
//            public void onSuccss(GetPodcastOffiRecResp getPodcastOffiRecResp) {
//                Log.d("rxjava", "onSuccss");
//                Log.d("rxjava", getPodcastOffiRecResp.getStatus() + "");
//            }
//        });
//
//        client.getPodcastRankPreviewView(new MyObserver<GetPodcastRankPreviewResp>() {
//            @Override
//            public void onSuccss(GetPodcastRankPreviewResp getPodcastRankPreviewResp) {
//                Log.d("rxjava", "onSuccss");
//                Log.d("rxjava", getPodcastRankPreviewResp.getStatus() + "");
//            }
//        });
//
//        client.getPodcastRankPreviewLike(new MyObserver<GetPodcastRankPreviewResp>() {
//            @Override
//            public void onSuccss(GetPodcastRankPreviewResp getPodcastRankPreviewResp) {
//                Log.d("rxjava", "onSuccss");
//                Log.d("rxjava", getPodcastRankPreviewResp.getStatus() + "");
//            }
//        });

//        client.cancelSubscribe(1, new MyObserver<ResetPassResp>() {
//            @Override
//            public void onSuccss(ResetPassResp resetPassResp) {
//                Log.d("rxjava", "onSuccss");
//                Log.d("rxjava", resetPassResp.getStatus() + "");
//            }
//        });


//        client.AlbumSubscribe(1, new MyObserver<ResetPassResp>() {
//            @Override
//            public void onSuccss(ResetPassResp resetPassResp) {
//                Log.d("rxjava", "onSuccss");
//                Log.d("rxjava", resetPassResp.getStatus() + "");
//            }
//        });

//        client.getSubscribe(13, new MyObserver<IntegerResp>() {
//            @Override
//            public void onSuccss(IntegerResp integerResp) {
//                Log.d("rxjava", "onSuccss");
//                Log.d("rxjava", integerResp.getStatus() + "");
//                Log.d("rxjava", integerResp.getNum() + "");
//            }
//        });


//        Map<String, String> map = new HashMap<>();
//        map.put("album_name", "qing hua ci");
//        map.put("description", "hot one hundred");
//        map.put("is_private", "0");
//        map.put("album_poster", "/album/test123.jpg");
//
//        client.addAlbum(map, new MyObserver<ResetPassResp>() {
//            @Override
//            public void onSuccss(ResetPassResp resetPassResp) {
//                Log.d("rxjava", "onSuccss");
//                Log.d("rxjava", resetPassResp.getStatus() + "");
//            }
//        });
//
//        client.deleteAlbum(11, new MyObserver<ResetPassResp>() {
//            @Override
//            public void onSuccss(ResetPassResp resetPassResp) {
//                Log.d("rxjava", "onSuccss");
//                Log.d("rxjava", resetPassResp.getStatus() + "");
//            }
//        });


//        client.getAlbumPreviews(3, 2, 3, new MyObserver<GetAlbumInfoResp>() {
//            @Override
//            public void onSuccss(GetAlbumInfoResp getAlbumInfoResp) {
//                Log.d("rxjava", "onSuccss");
//                Log.d("rxjava", getAlbumInfoResp.getStatus() + "");
//                Log.d("rxjava", getAlbumInfoResp.getAlbum() + "");
//                Log.d("rxjava", getAlbumInfoResp.getPodcast_num() + "");
//            }
//        });


//        client.resetDescription("hello world", new MyObserver<ResetPassResp>() {
//            @Override
//            public void onSuccss(ResetPassResp resetPassResp) {
//                Log.d("rxjava", "onSuccss");
//                Log.d("rxjava", resetPassResp.getStatus() + "");
//            }
//        });
//
//        client.resetAvatar("/img/1234.jpg", new MyObserver<ResetPassResp>() {
//            @Override
//            public void onSuccss(ResetPassResp resetPassResp) {
//                Log.d("rxjava", "onSuccss");
//                Log.d("rxjava", resetPassResp.getStatus() + "");
//            }
//        });



//        client.getSubscribeNumPreviews(new MyObserver<IntegerResp>() {
//            @Override
//            public void onSuccss(IntegerResp integerResp) {
//                Log.d("rxjava", "onSuccss");
//                Log.d("rxjava", integerResp.getStatus() + "");
//            }
//        });

//        client.getMorePodcastPreviews(1, 10, new MyObserver<GetHistoryResp>() {
//            @Override
//            public void onSuccss(GetHistoryResp getHistoryResp) {
//                Log.d("rxjava", "onSuccss");
//                Log.d("rxjava", getHistoryResp.getStatus() + "");
//            }
//        });
//
//        client.getCreatedPreviews(1, 10, new MyObserver<GetCreateResp>() {
//            @Override
//            public void onSuccss(GetCreateResp getCreateResp) {
//                Log.d("rxjava", "onSuccss");
//                Log.d("rxjava", getCreateResp.getStatus() + "");
//            }
//        });
//
//        client.getSubscribePreviews(1, 10, new MyObserver<GetSubscribeResp>() {
//            @Override
//            public void onSuccss(GetSubscribeResp getSubscribeResp) {
//                Log.d("rxjava", "onSuccss");
//                Log.d("rxjava", getSubscribeResp.getStatus() + "");
//            }
//        });


//        client.likeComment(12, new MyObserver<StatusResp>() {
//            @Override
//            public void onSuccss(StatusResp statusResp) {
//                Log.d("rxjava", "onSuccss");
//                Log.d("rxjava", statusResp.getStatus() + "");
//            }
//        });

//        client.getComments(0, new MyObserver<GetCommentsResp>() {
//            @Override
//            public void onSuccss(GetCommentsResp getCommentsResp) {
//                Log.d("rxjava", "onSuccss");
//                Log.d("rxjava", getCommentsResp.getStatus() + "");
//                Log.d("rxjava", getCommentsResp.getCommentNum()+"");
////                Log.d("rxjava", getCommentsResp.getComments());
//
//            }
//        });

//        client.addComment(1, "abc", new MyObserver<StatusResp>() {
//            @Override
//            public void onSuccss(StatusResp statusResp) {
//                Log.d("rxjava", statusResp.getStatus() + "");
//            }
//        });
//
//        client.delComment(9, new MyObserver<StatusResp>() {
//            @Override
//            public void onSuccss(StatusResp statusResp) {
//                Log.d("rxjava", statusResp.getStatus() + "");
//            }
//        });

    }

}
