package com.woa.dataClasses;

public class ListOfExercise {

    String CategoryName,Exercise,exerciseKay;

    public ListOfExercise() {
    }

    public ListOfExercise(String categoryName, String exercise, String exerciseKay) {
        CategoryName = categoryName;
        Exercise = exercise;
        this.exerciseKay = exerciseKay;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
    }

    public String getExercise() {
        return Exercise;
    }

    public void setExercise(String exercise) {
        Exercise = exercise;
    }

    public String getExerciseKay() {
        return exerciseKay;
    }

    public void setExerciseKay(String exerciseKay) {
        this.exerciseKay = exerciseKay;
    }
}
