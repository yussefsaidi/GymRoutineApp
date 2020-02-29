package com.yussefsaidi.gymroutine.persistence.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "exercises")
public class Exercise {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "sets")
    private String sets;
    @ColumnInfo(name = "repetitions")
    private String repetitions;

    @Ignore
    public Exercise() {
    }

    public Exercise(String name, String sets, String repetitions) {
        this.name = name;
        this.sets = sets;
        this.repetitions = repetitions;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSets() {
        return sets;
    }

    public void setSets(String sets) {
        this.sets = sets;
    }

    public String getRepetitions() {
        return repetitions;
    }

    public void setRepetitions(String repetitions) {
        this.repetitions = repetitions;
    }

    @Override
    public String toString() {
        return "Exercise{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sets='" + sets + '\'' +
                ", repetitions='" + repetitions + '\'' +
                '}';
    }
}
