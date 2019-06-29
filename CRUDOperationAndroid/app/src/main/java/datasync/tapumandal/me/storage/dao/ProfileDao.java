package datasync.tapumandal.me.storage.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import datasync.tapumandal.me.activity.Profile.Profile;
import datasync.tapumandal.me.storage.entity.ProfileModel;

@Dao
public interface ProfileDao {

    @Query("SELECT * FROM profiles")
    List<ProfileModel> getAll();

    @Insert
    void insertAll(ProfileModel profile);

}
