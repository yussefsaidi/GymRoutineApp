package com.yussefsaidi.gymroutine.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Exercise implements Parcelable {

    private int id;
    private String name;
    private String description;
    private String category;

    public Exercise() {
    }

    public Exercise(int id, String name, String description, String category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
    }


    protected Exercise(Parcel in) {
        id = in.readInt();
        name = in.readString();
        description = in.readString();
        category = in.readString();
    }

    public static final Creator<Exercise> CREATOR = new Creator<Exercise>() {
        @Override
        public Exercise createFromParcel(Parcel in) {
            return new Exercise(in);
        }

        @Override
        public Exercise[] newArray(int size) {
            return new Exercise[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeString(description);
        parcel.writeString(category);
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Exercise{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
