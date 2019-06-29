package datasync.tapumandal.me.storage.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import datasync.tapumandal.me.storage.dao.ProfileDao;
import datasync.tapumandal.me.storage.entity.ProfileModel;

@Database(entities = {ProfileModel.class}, version = 2)
public abstract class ProfileDatabase extends RoomDatabase {

    private static ProfileDatabase profileDatabase;
    public abstract ProfileDao profileDao();

    public static ProfileDatabase getProfileDatabase(Context context){
        if(profileDatabase == null){
            profileDatabase = Room.databaseBuilder(context.getApplicationContext(), ProfileDatabase.class, "profile_database")
                    .allowMainThreadQueries().build();
        }

        return profileDatabase;
    }

    public static void destroyProfileDatabase(){
        profileDatabase = null;
    }

}
