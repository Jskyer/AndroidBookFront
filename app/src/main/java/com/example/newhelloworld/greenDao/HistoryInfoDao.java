package com.example.newhelloworld.greenDao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.example.newhelloworld.pojo.HistoryInfo;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "HISTORY_INFO".
*/
public class HistoryInfoDao extends AbstractDao<HistoryInfo, Long> {

    public static final String TABLENAME = "HISTORY_INFO";

    /**
     * Properties of entity HistoryInfo.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property History_id = new Property(0, Long.class, "history_id", true, "_id");
        public final static Property Podcast_id = new Property(1, Integer.class, "podcast_id", false, "PODCAST_ID");
        public final static Property Views = new Property(2, String.class, "views", false, "VIEWS");
        public final static Property Podcast_poster = new Property(3, String.class, "podcast_poster", false, "PODCAST_POSTER");
        public final static Property Title = new Property(4, String.class, "title", false, "TITLE");
        public final static Property Update_time = new Property(5, java.util.Date.class, "update_time", false, "UPDATE_TIME");
        public final static Property Create_time = new Property(6, java.util.Date.class, "create_time", false, "CREATE_TIME");
        public final static Property Podcast_path = new Property(7, String.class, "podcast_path", false, "PODCAST_PATH");
        public final static Property Uploader_id = new Property(8, Integer.class, "uploader_id", false, "UPLOADER_ID");
        public final static Property Uploader_name = new Property(9, String.class, "uploader_name", false, "UPLOADER_NAME");
        public final static Property Duration = new Property(10, String.class, "duration", false, "DURATION");
        public final static Property Album_id = new Property(11, Integer.class, "album_id", false, "ALBUM_ID");
        public final static Property Like_num = new Property(12, Integer.class, "like_num", false, "LIKE_NUM");
        public final static Property Comment_num = new Property(13, Integer.class, "comment_num", false, "COMMENT_NUM");
        public final static Property UserId = new Property(14, Integer.class, "userId", false, "USER_ID");
        public final static Property Album_name = new Property(15, String.class, "album_name", false, "ALBUM_NAME");
        public final static Property Podcast_name = new Property(16, String.class, "podcast_name", false, "PODCAST_NAME");
        public final static Property Datetime = new Property(17, java.util.Date.class, "datetime", false, "DATETIME");
    }


    public HistoryInfoDao(DaoConfig config) {
        super(config);
    }
    
    public HistoryInfoDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"HISTORY_INFO\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: history_id
                "\"PODCAST_ID\" INTEGER," + // 1: podcast_id
                "\"VIEWS\" TEXT," + // 2: views
                "\"PODCAST_POSTER\" TEXT," + // 3: podcast_poster
                "\"TITLE\" TEXT," + // 4: title
                "\"UPDATE_TIME\" INTEGER," + // 5: update_time
                "\"CREATE_TIME\" INTEGER," + // 6: create_time
                "\"PODCAST_PATH\" TEXT," + // 7: podcast_path
                "\"UPLOADER_ID\" INTEGER," + // 8: uploader_id
                "\"UPLOADER_NAME\" TEXT," + // 9: uploader_name
                "\"DURATION\" TEXT," + // 10: duration
                "\"ALBUM_ID\" INTEGER," + // 11: album_id
                "\"LIKE_NUM\" INTEGER," + // 12: like_num
                "\"COMMENT_NUM\" INTEGER," + // 13: comment_num
                "\"USER_ID\" INTEGER," + // 14: userId
                "\"ALBUM_NAME\" TEXT," + // 15: album_name
                "\"PODCAST_NAME\" TEXT," + // 16: podcast_name
                "\"DATETIME\" INTEGER);"); // 17: datetime
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"HISTORY_INFO\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, HistoryInfo entity) {
        stmt.clearBindings();
 
        Long history_id = entity.getHistory_id();
        if (history_id != null) {
            stmt.bindLong(1, history_id);
        }
 
        Integer podcast_id = entity.getPodcast_id();
        if (podcast_id != null) {
            stmt.bindLong(2, podcast_id);
        }
 
        String views = entity.getViews();
        if (views != null) {
            stmt.bindString(3, views);
        }
 
        String podcast_poster = entity.getPodcast_poster();
        if (podcast_poster != null) {
            stmt.bindString(4, podcast_poster);
        }
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(5, title);
        }
 
        java.util.Date update_time = entity.getUpdate_time();
        if (update_time != null) {
            stmt.bindLong(6, update_time.getTime());
        }
 
        java.util.Date create_time = entity.getCreate_time();
        if (create_time != null) {
            stmt.bindLong(7, create_time.getTime());
        }
 
        String podcast_path = entity.getPodcast_path();
        if (podcast_path != null) {
            stmt.bindString(8, podcast_path);
        }
 
        Integer uploader_id = entity.getUploader_id();
        if (uploader_id != null) {
            stmt.bindLong(9, uploader_id);
        }
 
        String uploader_name = entity.getUploader_name();
        if (uploader_name != null) {
            stmt.bindString(10, uploader_name);
        }
 
        String duration = entity.getDuration();
        if (duration != null) {
            stmt.bindString(11, duration);
        }
 
        Integer album_id = entity.getAlbum_id();
        if (album_id != null) {
            stmt.bindLong(12, album_id);
        }
 
        Integer like_num = entity.getLike_num();
        if (like_num != null) {
            stmt.bindLong(13, like_num);
        }
 
        Integer comment_num = entity.getComment_num();
        if (comment_num != null) {
            stmt.bindLong(14, comment_num);
        }
 
        Integer userId = entity.getUserId();
        if (userId != null) {
            stmt.bindLong(15, userId);
        }
 
        String album_name = entity.getAlbum_name();
        if (album_name != null) {
            stmt.bindString(16, album_name);
        }
 
        String podcast_name = entity.getPodcast_name();
        if (podcast_name != null) {
            stmt.bindString(17, podcast_name);
        }
 
        java.util.Date datetime = entity.getDatetime();
        if (datetime != null) {
            stmt.bindLong(18, datetime.getTime());
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, HistoryInfo entity) {
        stmt.clearBindings();
 
        Long history_id = entity.getHistory_id();
        if (history_id != null) {
            stmt.bindLong(1, history_id);
        }
 
        Integer podcast_id = entity.getPodcast_id();
        if (podcast_id != null) {
            stmt.bindLong(2, podcast_id);
        }
 
        String views = entity.getViews();
        if (views != null) {
            stmt.bindString(3, views);
        }
 
        String podcast_poster = entity.getPodcast_poster();
        if (podcast_poster != null) {
            stmt.bindString(4, podcast_poster);
        }
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(5, title);
        }
 
        java.util.Date update_time = entity.getUpdate_time();
        if (update_time != null) {
            stmt.bindLong(6, update_time.getTime());
        }
 
        java.util.Date create_time = entity.getCreate_time();
        if (create_time != null) {
            stmt.bindLong(7, create_time.getTime());
        }
 
        String podcast_path = entity.getPodcast_path();
        if (podcast_path != null) {
            stmt.bindString(8, podcast_path);
        }
 
        Integer uploader_id = entity.getUploader_id();
        if (uploader_id != null) {
            stmt.bindLong(9, uploader_id);
        }
 
        String uploader_name = entity.getUploader_name();
        if (uploader_name != null) {
            stmt.bindString(10, uploader_name);
        }
 
        String duration = entity.getDuration();
        if (duration != null) {
            stmt.bindString(11, duration);
        }
 
        Integer album_id = entity.getAlbum_id();
        if (album_id != null) {
            stmt.bindLong(12, album_id);
        }
 
        Integer like_num = entity.getLike_num();
        if (like_num != null) {
            stmt.bindLong(13, like_num);
        }
 
        Integer comment_num = entity.getComment_num();
        if (comment_num != null) {
            stmt.bindLong(14, comment_num);
        }
 
        Integer userId = entity.getUserId();
        if (userId != null) {
            stmt.bindLong(15, userId);
        }
 
        String album_name = entity.getAlbum_name();
        if (album_name != null) {
            stmt.bindString(16, album_name);
        }
 
        String podcast_name = entity.getPodcast_name();
        if (podcast_name != null) {
            stmt.bindString(17, podcast_name);
        }
 
        java.util.Date datetime = entity.getDatetime();
        if (datetime != null) {
            stmt.bindLong(18, datetime.getTime());
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public HistoryInfo readEntity(Cursor cursor, int offset) {
        HistoryInfo entity = new HistoryInfo( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // history_id
            cursor.isNull(offset + 1) ? null : cursor.getInt(offset + 1), // podcast_id
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // views
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // podcast_poster
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // title
            cursor.isNull(offset + 5) ? null : new java.util.Date(cursor.getLong(offset + 5)), // update_time
            cursor.isNull(offset + 6) ? null : new java.util.Date(cursor.getLong(offset + 6)), // create_time
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // podcast_path
            cursor.isNull(offset + 8) ? null : cursor.getInt(offset + 8), // uploader_id
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // uploader_name
            cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10), // duration
            cursor.isNull(offset + 11) ? null : cursor.getInt(offset + 11), // album_id
            cursor.isNull(offset + 12) ? null : cursor.getInt(offset + 12), // like_num
            cursor.isNull(offset + 13) ? null : cursor.getInt(offset + 13), // comment_num
            cursor.isNull(offset + 14) ? null : cursor.getInt(offset + 14), // userId
            cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15), // album_name
            cursor.isNull(offset + 16) ? null : cursor.getString(offset + 16), // podcast_name
            cursor.isNull(offset + 17) ? null : new java.util.Date(cursor.getLong(offset + 17)) // datetime
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, HistoryInfo entity, int offset) {
        entity.setHistory_id(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setPodcast_id(cursor.isNull(offset + 1) ? null : cursor.getInt(offset + 1));
        entity.setViews(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setPodcast_poster(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setTitle(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setUpdate_time(cursor.isNull(offset + 5) ? null : new java.util.Date(cursor.getLong(offset + 5)));
        entity.setCreate_time(cursor.isNull(offset + 6) ? null : new java.util.Date(cursor.getLong(offset + 6)));
        entity.setPodcast_path(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setUploader_id(cursor.isNull(offset + 8) ? null : cursor.getInt(offset + 8));
        entity.setUploader_name(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setDuration(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
        entity.setAlbum_id(cursor.isNull(offset + 11) ? null : cursor.getInt(offset + 11));
        entity.setLike_num(cursor.isNull(offset + 12) ? null : cursor.getInt(offset + 12));
        entity.setComment_num(cursor.isNull(offset + 13) ? null : cursor.getInt(offset + 13));
        entity.setUserId(cursor.isNull(offset + 14) ? null : cursor.getInt(offset + 14));
        entity.setAlbum_name(cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15));
        entity.setPodcast_name(cursor.isNull(offset + 16) ? null : cursor.getString(offset + 16));
        entity.setDatetime(cursor.isNull(offset + 17) ? null : new java.util.Date(cursor.getLong(offset + 17)));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(HistoryInfo entity, long rowId) {
        entity.setHistory_id(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(HistoryInfo entity) {
        if(entity != null) {
            return entity.getHistory_id();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(HistoryInfo entity) {
        return entity.getHistory_id() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}