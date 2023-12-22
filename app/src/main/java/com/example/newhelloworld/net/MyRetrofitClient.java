package com.example.newhelloworld.net;

import com.example.newhelloworld.queryVO.GetCommentsResp;
import com.example.newhelloworld.queryVO.LoginResp;
import com.example.newhelloworld.queryVO.SendVerificationResp;
import com.example.newhelloworld.queryVO.Status;
import com.example.newhelloworld.queryVO.StatusResp;
import com.example.newhelloworld.queryVO.album.GetAlbumInfoResp;
import com.example.newhelloworld.queryVO.album.GetPopularAlbumResp;
import com.example.newhelloworld.queryVO.podcast.GetPodcastOffiRecResp;
import com.example.newhelloworld.queryVO.podcast.GetPodcastRankPreviewResp;
import com.example.newhelloworld.queryVO.podcast.GetPodcastResp;
import com.example.newhelloworld.queryVO.podcast.UploadPodcastResp;
import com.example.newhelloworld.queryVO.signIn.ResetPassResp;
import com.example.newhelloworld.queryVO.userInfo.GetCreateResp;
import com.example.newhelloworld.queryVO.userInfo.GetHistoryResp;
import com.example.newhelloworld.queryVO.userInfo.GetSearchResp;
import com.example.newhelloworld.queryVO.userInfo.GetSubscribeResp;
import com.example.newhelloworld.queryVO.userInfo.IntegerResp;

import java.util.Map;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.MultipartBody;
import retrofit2.Retrofit;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public class MyRetrofitClient {
    //SignInController
    public Observable<SendVerificationResp> verify(String email, Observer<SendVerificationResp> observer){
        Retrofit retrofit = RetrofitFactory.getInstance();
        IRequest iRequest = retrofit.create(IRequest.class);

        Observable<SendVerificationResp> res = iRequest.verify(email);
        res.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
        return res;
    }

    public Observable<SendVerificationResp> register(Map<String,String> map, Observer<SendVerificationResp> observer){
        Retrofit retrofit = RetrofitFactory.getInstance();
        IRequest iRequest = retrofit.create(IRequest.class);

        Observable<SendVerificationResp> res = iRequest.register(map);
        res.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
        return res;
    }

    public Observable<LoginResp> login(Map<String,String> map, Observer<LoginResp> observer){
        Retrofit retrofit = RetrofitFactory.getInstance();
        IRequest iRequest = retrofit.create(IRequest.class);

        Observable<LoginResp> res = iRequest.login(map);
        res.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
        return res;
    }

    public Observable<ResetPassResp> resetPwd(String pwd, Observer<ResetPassResp> observer){
        Retrofit retrofit = RetrofitFactory.getInstance();
        IRequest iRequest = retrofit.create(IRequest.class);

        Observable<ResetPassResp> res = iRequest.resetPwd(pwd);
        res.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
        return res;
    }

    //CommentController
    public Observable<StatusResp> addComment(Integer podcastId, String comment_text, Observer<StatusResp> observer){
        Retrofit retrofit = RetrofitFactory.getInstance();
        IRequest iRequest = retrofit.create(IRequest.class);

        Observable<StatusResp> res = iRequest.addComment(podcastId, comment_text);
        res.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
        return res;
    }

    public Observable<GetCommentsResp> getComments(Integer podcastId, Observer<GetCommentsResp> observer){
        Retrofit retrofit = RetrofitFactory.getInstance();
        IRequest iRequest = retrofit.create(IRequest.class);

        Observable<GetCommentsResp> res = iRequest.getComments(podcastId);
        res.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
        return res;
    }


    public Observable<StatusResp> delComment(Integer comment_id, Observer<StatusResp> observer){
        Retrofit retrofit = RetrofitFactory.getInstance();
        IRequest iRequest = retrofit.create(IRequest.class);

        Observable<StatusResp> res = iRequest.delComment(comment_id);
        res.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
        return res;
    }

    //LikeController
    public Observable<StatusResp> likeComment(Integer comment_id, Observer<StatusResp> observer){
        Retrofit retrofit = RetrofitFactory.getInstance();
        IRequest iRequest = retrofit.create(IRequest.class);

        Observable<StatusResp> res = iRequest.likeComment(comment_id);
        res.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
        return res;
    }

    //UserController
    public Observable<GetHistoryResp> getMorePodcastPreviews(Integer page_no, Integer page_size,
                                                             Observer<GetHistoryResp> observer){
        Retrofit retrofit = RetrofitFactory.getInstance();
        IRequest iRequest = retrofit.create(IRequest.class);

        Observable<GetHistoryResp> res = iRequest.getMorePodcastPreviews(page_no, page_size);
        res.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
        return res;
    }

    public Observable<GetCreateResp> getCreatedPreviews(Integer page_no, Integer page_size,
                                                             Observer<GetCreateResp> observer){
        Retrofit retrofit = RetrofitFactory.getInstance();
        IRequest iRequest = retrofit.create(IRequest.class);

        Observable<GetCreateResp> res = iRequest.getCreatedPreviews(page_no, page_size);
        res.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
        return res;
    }

    public Observable<GetSubscribeResp> getSubscribePreviews(Integer page_no, Integer page_size,
                                                        Observer<GetSubscribeResp> observer){
        Retrofit retrofit = RetrofitFactory.getInstance();
        IRequest iRequest = retrofit.create(IRequest.class);

        Observable<GetSubscribeResp> res = iRequest.getSubscribePreviews(page_no, page_size);
        res.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
        return res;
    }

    public Observable<IntegerResp> getSubscribeNumPreviews(Observer<IntegerResp> observer){
        Retrofit retrofit = RetrofitFactory.getInstance();
        IRequest iRequest = retrofit.create(IRequest.class);

        Observable<IntegerResp> res = iRequest.getSubscribeNumPreviews();
        res.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
        return res;
    }

    public Observable<ResetPassResp> resetDescription(String description, Observer<ResetPassResp> observer){
        Retrofit retrofit = RetrofitFactory.getInstance();
        IRequest iRequest = retrofit.create(IRequest.class);

        Observable<ResetPassResp> res = iRequest.resetDescription(description);
        res.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
        return res;
    }

    public Observable<ResetPassResp> resetAvatar(String avatar, Observer<ResetPassResp> observer){
        Retrofit retrofit = RetrofitFactory.getInstance();
        IRequest iRequest = retrofit.create(IRequest.class);

        Observable<ResetPassResp> res = iRequest.resetAvatar(avatar);
        res.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
        return res;
    }

    public Observable<ResetPassResp> addHistory(Integer podcast_id, Observer<ResetPassResp> observer){
        Retrofit retrofit = RetrofitFactory.getInstance();
        IRequest iRequest = retrofit.create(IRequest.class);

        Observable<ResetPassResp> res = iRequest.addHistory(podcast_id);
        res.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
        return res;
    }

    public Observable<GetSearchResp> searchPodcast(Integer pageNum, Integer pageSize, String key,
                                                   Observer<GetSearchResp> observer){
        Retrofit retrofit = RetrofitFactory.getInstance();
        IRequest iRequest = retrofit.create(IRequest.class);

        Observable<GetSearchResp> res = iRequest.searchPodcast(pageNum, pageSize, key);
        res.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
        return res;
    }



    //AlbumController
    public Observable<GetAlbumInfoResp> getAlbumPreviews(Integer album_id, Integer page_no, Integer page_size,
                                                         Observer<GetAlbumInfoResp> observer){
        Retrofit retrofit = RetrofitFactory.getInstance();
        IRequest iRequest = retrofit.create(IRequest.class);

        Observable<GetAlbumInfoResp> res = iRequest.getAlbumPreviews(album_id, page_no, page_size);
        res.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
        return res;
    }


    public Observable<ResetPassResp> deleteAlbum(Integer album_id, Observer<ResetPassResp> observer){
        Retrofit retrofit = RetrofitFactory.getInstance();
        IRequest iRequest = retrofit.create(IRequest.class);

        Observable<ResetPassResp> res = iRequest.deleteAlbum(album_id);
        res.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
        return res;
    }

    public Observable<ResetPassResp> addAlbum(Map<String,String> map, Observer<ResetPassResp> observer){
        Retrofit retrofit = RetrofitFactory.getInstance();
        IRequest iRequest = retrofit.create(IRequest.class);

        Observable<ResetPassResp> res = iRequest.addAlbum(map);
        res.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
        return res;
    }

    public Observable<IntegerResp> getSubscribe(Integer album_id, Observer<IntegerResp> observer){
        Retrofit retrofit = RetrofitFactory.getInstance();
        IRequest iRequest = retrofit.create(IRequest.class);

        Observable<IntegerResp> res = iRequest.getSubscribe(album_id);
        res.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
        return res;
    }

    public Observable<ResetPassResp> AlbumSubscribe(Integer album_id, Observer<ResetPassResp> observer){
        Retrofit retrofit = RetrofitFactory.getInstance();
        IRequest iRequest = retrofit.create(IRequest.class);

        Observable<ResetPassResp> res = iRequest.AlbumSubscribe(album_id);
        res.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
        return res;
    }

    public Observable<ResetPassResp> cancelSubscribe(Integer album_id, Observer<ResetPassResp> observer){
        Retrofit retrofit = RetrofitFactory.getInstance();
        IRequest iRequest = retrofit.create(IRequest.class);

        Observable<ResetPassResp> res = iRequest.cancelSubscribe(album_id);
        res.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
        return res;
    }

    public Observable<GetPopularAlbumResp> getPopularAlbum(Integer pageNum, Integer pageSize,
                                                           Observer<GetPopularAlbumResp> observer){
        Retrofit retrofit = RetrofitFactory.getInstance();
        IRequest iRequest = retrofit.create(IRequest.class);

        Observable<GetPopularAlbumResp> res = iRequest.getPopularAlbum(pageNum, pageSize);
        res.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
        return res;
    }

    public Observable<GetPopularAlbumResp> getAlbumByType(Integer pageNum, Integer pageSize, String type,
                                                          Observer<GetPopularAlbumResp> observer){
        Retrofit retrofit = RetrofitFactory.getInstance();
        IRequest iRequest = retrofit.create(IRequest.class);

        Observable<GetPopularAlbumResp> res = iRequest.getAlbumByType(pageNum, pageSize, type);
        res.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
        return res;
    }


    public Observable<GetCreateResp> getMySubscribeAlbum(Integer pageNum, Integer pageSize,
                                                          Observer<GetCreateResp> observer){
        Retrofit retrofit = RetrofitFactory.getInstance();
        IRequest iRequest = retrofit.create(IRequest.class);

        Observable<GetCreateResp> res = iRequest.getMySubscribeAlbum(pageNum, pageSize);
        res.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
        return res;
    }




    //PodcastController
    public Observable<GetPodcastResp> getPodcast(Integer podcastId, Observer<GetPodcastResp> observer){
        Retrofit retrofit = RetrofitFactory.getInstance();
        IRequest iRequest = retrofit.create(IRequest.class);

        Observable<GetPodcastResp> res = iRequest.getPodcast(podcastId);
        res.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
        return res;
    }

    public Observable<GetPodcastOffiRecResp> getPodcastOffiRec(Observer<GetPodcastOffiRecResp> observer){
        Retrofit retrofit = RetrofitFactory.getInstance();
        IRequest iRequest = retrofit.create(IRequest.class);

        Observable<GetPodcastOffiRecResp> res = iRequest.getPodcastOffiRec();
        res.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
        return res;
    }


    public Observable<GetPodcastOffiRecResp> getPodcastRankPreviewView(Observer<GetPodcastOffiRecResp> observer){
        Retrofit retrofit = RetrofitFactory.getInstance();
        IRequest iRequest = retrofit.create(IRequest.class);

        Observable<GetPodcastOffiRecResp> res = iRequest.getPodcastRankPreviewView();
        res.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
        return res;
    }

    public Observable<GetPodcastOffiRecResp> getPodcastRankPreviewLike(Observer<GetPodcastOffiRecResp> observer){
        Retrofit retrofit = RetrofitFactory.getInstance();
        IRequest iRequest = retrofit.create(IRequest.class);

        Observable<GetPodcastOffiRecResp> res = iRequest.getPodcastRankPreviewLike();
        res.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
        return res;
    }


    public Observable<UploadPodcastResp> uploadPodcast(Integer albumId, Integer duration,
                                                       String title, String podcastPoster, String podcastPath,
                                                       Observer<UploadPodcastResp> observer){
        Retrofit retrofit = RetrofitFactory.getInstance();
        IRequest iRequest = retrofit.create(IRequest.class);

        Observable<UploadPodcastResp> res = iRequest.uploadPodcast(albumId, duration, title, podcastPoster, podcastPath);
        res.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
        return res;
    }



    public Observable<StatusResp> delPodcast(Integer podcastId, Observer<StatusResp> observer){
        Retrofit retrofit = RetrofitFactory.getInstance();
        IRequest iRequest = retrofit.create(IRequest.class);

        Observable<StatusResp> res = iRequest.delPodcast(podcastId);
        res.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
        return res;
    }

    public Observable<ResetPassResp> uploadAvatar(MultipartBody.Part mediaFile, Observer<ResetPassResp> observer){
        Retrofit retrofit = RetrofitFactory.getInstance();
        IRequest iRequest = retrofit.create(IRequest.class);

        Observable<ResetPassResp> res = iRequest.uploadAvatar(mediaFile);
        res.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
        return res;
    }


}
