package datasync.tapumandal.me.storage.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import datasync.tapumandal.me.storage.entity.ProfileModel;
import datasync.tapumandal.me.storage.entity.ProfileModelLocal;

@Dao
public interface ProfileDao {

    @Query("SELECT * FROM profiles")
    List<ProfileModelLocal> getAll();

    @Insert
    void insertAll(ProfileModelLocal profileModelLocal);


    @SuppressWarnings("AndroidUnresolvedRoomSqlReference")
    @Query("UPDATE profiles SET sync_status='true' WHERE id = :id")
    void update(Integer id);
}
