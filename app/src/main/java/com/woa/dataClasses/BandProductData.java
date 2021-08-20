package com.woa.dataClasses;

import java.io.Serializable;

public class BandProductData implements Serializable {

    String date,name,productKey,workoutProgress,categoryName;
    long time;
    int exercisesCount;

    public BandProductData() {
    }

    public BandProductData(String date, String name, String productKey, String workoutProgress, long time, int exercisesCount) {
        this.date = date;
        this.name = name;
        this.productKey = productKey;
        this.workoutProgress = workoutProgress;
        this.time = time;
        this.exercisesCount = exercisesCount;
    }

    public BandProductData(String date, String name, String productKey, String workoutProgress, String categoryName, long time, int exercisesCount) {
        this.date = date;
        this.name = name;
        this.productKey = productKey;
        this.workoutProgress = workoutProgress;
        this.categoryName = categoryName;
        this.time = time;
        this.exercisesCount = exercisesCount;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProductKey() {
        return productKey;
    }

    public void setProductKey(String productKey) {
        this.productKey = productKey;
    }

    public String getWorkoutProgress() {
        return workoutProgress;
    }

    public void setWorkoutProgress(String workoutProgress) {
        this.workoutProgress = workoutProgress;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getExercisesCount() {
        return exercisesCount;
    }

    public void setExercisesCount(int exercisesCount) {
        this.exercisesCount = exercisesCount;
    }
}
