package com.woa.dataClasses;

import java.io.Serializable;

public class ExerciseList implements Serializable {

    private String exerciseId;
    private String name;

    public ExerciseList() {
    }

    public ExerciseList(String exerciseId, String name) {
        this.exerciseId = exerciseId;
        this.name = name;
    }

    public String getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(String exerciseId) {
        this.exerciseId = exerciseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
