package datasync.tapumandal.me.storage.entity;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "profiles")
public class ProfileModel {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name="name")
    private String name;

    @ColumnInfo(name="email")
    private String email;

    @ColumnInfo(name="gender")
    private String gender;

    @ColumnInfo(name="phone")
    private String phone;

//    @ColumnInfo(name="state")
//    private String state;

    @ColumnInfo(name="country")
    private String country;

    @ColumnInfo(name="sync_status")
    private String syncStatus;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

//    public String getState() {
//        return state;
//    }

//    public void setState(String state) {
//        this.state = state;
//    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getSyncStatus() {
        return syncStatus;
    }

    public void setSyncStatus(String syncStatus) {
        this.syncStatus = syncStatus;
    }
}
