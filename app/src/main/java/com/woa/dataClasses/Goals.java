package com.woa.dataClasses;

public class Goals {

    private String endDate, goalsType, nameExercise, reps, startDate, weight,goalId,categoryName,goalProgress;
    private long timestamp;



    public Goals() {
    }

    public Goals(String endDate, String goalsType, String nameExercise, String reps, String startDate, String weight, String goalId, long timestamp) {
        this.endDate = endDate;
        this.goalsType = goalsType;
        this.nameExercise = nameExercise;
        this.reps = reps;
        this.startDate = startDate;
        this.weight = weight;
        this.goalId = goalId;
        this.timestamp = timestamp;
    }

    public Goals(String endDate, String goalsType, String nameExercise, String reps, String startDate, String weight, String goalId, String categoryName, long timestamp) {
        this.endDate = endDate;
        this.goalsType = goalsType;
        this.nameExercise = nameExercise;
        this.reps = reps;
        this.startDate = startDate;
        this.weight = weight;
        this.goalId = goalId;
        this.categoryName = categoryName;
        this.timestamp = timestamp;
    }


    public Goals(String endDate, String goalsType, String nameExercise, String reps, String startDate, String weight, String goalId, String categoryName, String goalProgress, long timestamp) {
        this.endDate = endDate;
        this.goalsType = goalsType;
        this.nameExercise = nameExercise;
        this.reps = reps;
        this.startDate = startDate;
        this.weight = weight;
        this.goalId = goalId;
        this.categoryName = categoryName;
        this.goalProgress = goalProgress;
        this.timestamp = timestamp;
    }

    public String getGoalProgress() {
        return goalProgress;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getGoalsType() {
        return goalsType;
    }

    public String getNameExercise() {
        return nameExercise;
    }

    public String getReps() {
        return reps;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getWeight() {
        return weight;
    }

    public String getGoalId() {
        return goalId;
    }

    public long getTimestamp() {
        return timestamp;
    }
}