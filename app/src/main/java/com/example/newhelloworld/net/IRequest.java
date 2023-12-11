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
import com.example.newhelloworld.queryVO.podcast.GetPodcastUpdatePreviewResp;
import com.example.newhelloworld.queryVO.podcast.UploadPodcastResp;
import com.example.newhelloworld.queryVO.signIn.ResetPassResp;
import com.example.newhelloworld.queryVO.userInfo.GetCreateResp;
import com.example.newhelloworld.queryVO.userInfo.GetHistoryResp;
import com.example.newhelloworld.queryVO.userInfo.GetSubscribeResp;
import com.example.newhelloworld.queryVO.userInfo.IntegerResp;

import java.util.Map;

import io.reactivex.rxjava3.core.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface IRequest {
    //SignInController
    @GET("/api/login/send_verification")
    Observable<SendVerificationResp> verify(@Query("email") String map);

    @POST("/api/login/signin")
    Observable<SendVerificationResp> register(@Body Map<String, String> map);

    @POST("/api/login/login")
    Observable<LoginResp> login(@Body Map<String, String> map);

    @POST("/api/login/reset_pass")
    Observable<ResetPassResp> resetPwd(@Query("password") String pwd);

    //CommentController
    @POST("/api/Comment/add_comment")
    Observable<StatusResp> addComment(@Query("podcastId") Integer podcastId, @Query("comment_text") String comment_text);

    @GET("/api/Comment/get_comment")
    Observable<GetCommentsResp> getComments(@Query("podcastId") Integer podcastId);

    @POST("/api/Comment/del_comment")
    Observable<StatusResp> delComment(@Query("comment_id") Integer comment_id);


    //LikeController
    @POST("/api/like/like_comment")
    Observable<StatusResp> likeComment(@Query("comment_id") Integer comment_id);

    //UserController
    @GET("/api/user/history")
    Observable<GetHistoryResp> getMorePodcastPreviews(@Query("page_no") Integer page_no, @Query("page_size") Integer page_size);

    @GET("/api/user/created")
    Observable<GetCreateResp> getCreatedPreviews(@Query("page_no")Integer page_no, @Query("page_size") Integer page_size);


    @GET("/api/user/subscribed")
    Observable<GetSubscribeResp> getSubscribePreviews(@Query("page_no")Integer page_no, @Query("page_size") Integer page_size);

    @GET("/api/user/subscribe_num")
    Observable<IntegerResp> getSubscribeNumPreviews();

    @POST("/api/user/reset_des")
    Observable<ResetPassResp> resetDescription(@Query("description") String description);

    @POST("/api/user/reset_avatar")
    Observable<ResetPassResp> resetAvatar(@Query("avatar") String avatar);

    @POST("/api/user/add_history")
    Observable<ResetPassResp> addHistory(@Query("podcast_id") Integer podcast_id);


    //AlbumController
    @GET("api/album/get_album")
    Observable<GetAlbumInfoResp> getAlbumPreviews(@Query("album_id") Integer album_id, @Query("page_no") Integer page_no, @Query("page_size") Integer page_size);

    @DELETE("/api/album/delete")
    Observable<ResetPassResp> deleteAlbum(@Query("album_id") Integer album_id);

    @POST("/api/album/add")
    Observable<ResetPassResp> addAlbum(@Body Map<String,String> map);

    @GET("/api/album/is_sub")
    Observable<IntegerResp> getSubscribe(@Query("album_id") Integer album_id);

    @POST("/api/album/add_sub")
    Observable<ResetPassResp> AlbumSubscribe(@Query("album_id") Integer album_id);

    @POST("/api/album/cancel_sub")
    Observable<ResetPassResp> cancelSubscribe(@Query("album_id") Integer album_id);

    @GET("/api/album/popular_album")
    Observable<GetPopularAlbumResp> getPopularAlbum(@Query("page_no") Integer pageNum, @Query("page_size") Integer pageSize);

    @GET("/api/album/type")
    Observable<GetPopularAlbumResp> getAlbumByType(@Query("page_no") Integer pageNum,
                                                   @Query("page_size") Integer pageSize,
                                                   @Query("type") String type);


    //PodcastController
    @GET("/api/podcast/get_podcast_by_id")
    Observable<GetPodcastResp> getPodcast(@Query("podcastId") Integer podcastId);

    @GET("/api/podcast/get_podcast_official_rec")
    Observable<GetPodcastOffiRecResp> getPodcastOffiRec();

    @GET("/api/podcast/get_podcast_rank_preview_view")
    Observable<GetPodcastOffiRecResp> getPodcastRankPreviewView();

    @GET("/api/podcast/get_podcast_rank_preview_like")
    Observable<GetPodcastOffiRecResp> getPodcastRankPreviewLike();

//    @GET("/api/podcast/get_podcast_update_preview")
//    Observable<GetPodcastUpdatePreviewResp> getPodcastUpdatePreview(@Query(""))


    @POST("/api/podcast/upload_podcast")
    Observable<UploadPodcastResp> uploadPodcast(@Query("albumId") Integer albumId,
                                                @Query("duration") Integer duration,
                                                @Query("title") String title,
                                                @Query("podcastPoster") String podcastPoster,
                                                @Query("podcastPath") String podcastPath);

    @POST("/api/podcast/delete_podcast")
    Observable<StatusResp> delPodcast(@Query("podcastId") Integer podcastId);



//    上传头像
    @Multipart
    @POST("/api/user/uploadAvatar")
    Observable<ResetPassResp> uploadAvatar(@Part MultipartBody.Part mediaFile);

}
