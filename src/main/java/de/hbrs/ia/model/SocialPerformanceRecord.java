package de.hbrs.ia.model;

import org.bson.Document;

public class SocialPerformanceRecord {
    private String goalId;

    private String goalDescr;

    private Integer targetVal;

    private Integer actVal;

    private Integer year;

    public SocialPerformanceRecord(String goalId,String goalDescr, Integer targetVal, Integer actVal, Integer year) {
        this.goalId = goalId;
        this.goalDescr = goalDescr;
        this.targetVal = targetVal;
        this.actVal = actVal;
        this.year = year;
    }

    public String getGoalId() {
        return goalId;
    }

    public String getGoalDescr() {
        return goalDescr;
    }

    public Integer getActVal() {
        return actVal;
    }

    public Integer getTargetVal() {
        return targetVal;
    }

    public Integer getYear() {
        return year;
    }

    public void setGoalId(String goalId) {
        this.goalId = goalId;
    }

    public void setGoalDescr(String goalDescr) {
        this.goalDescr = goalDescr;
    }

    public void setTargetVal(Integer targetVal) {
        this.targetVal = targetVal;
    }

    public void setActVal(Integer actVal) {
        this.actVal = actVal;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Document toDocument() {
        org.bson.Document document = new Document();
        document.append("goalId" , this.goalId );
        document.append("goalDescr" , this.goalDescr );
        document.append("targetValue" , this.targetVal);
        document.append("actValue", this.actVal);
        document.append("year", this.year);
        return document;
    }
}

