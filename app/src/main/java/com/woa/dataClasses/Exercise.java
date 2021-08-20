package com.woa.dataClasses;

public class Exercise {

    String weightKgs, reps, exerciseKay;

    public Exercise(){}


    public Exercise(String weightKgs, String reps) {
        this.weightKgs = weightKgs;
        this.reps = reps;
    }

    public Exercise(String weightKgs, String reps, String exerciseKay) {
        this.weightKgs = weightKgs;
        this.reps = reps;
        this.exerciseKay = exerciseKay;
    }

    public String getWeightKgs() {
        return weightKgs;
    }

    public void setWeightKgs(String weightKgs) {
        this.weightKgs = weightKgs;
    }

    public String getReps() {
        return reps;
    }

    public void setReps(String reps) {
        this.reps = reps;
    }

    public String getExerciseKay() {
        return exerciseKay;
    }

    public void setExerciseKay(String exerciseKay) {
        this.exerciseKay = exerciseKay;
    }
}
