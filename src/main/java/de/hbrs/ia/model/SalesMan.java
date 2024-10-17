package de.hbrs.ia.model;

import org.bson.Document;

import java.util.ArrayList;

public class SalesMan {
    private String firstname;
    private String lastname;
    private Integer sid;

    private int[] goalIds;

    public SalesMan(String firstname, String lastname, Integer sid) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.sid = sid;
        this.goalIds = new int[0];
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Integer getId() {
        return sid;
    }

    public void setId(Integer sid) {
        this.sid = sid;
    }

    public void addGoalId(int goalId){
        int[] newArr = new int[goalIds.length + 1];
        for (int i = 0; i < goalIds.length; i++){
            newArr[i] = goalIds[i];
        }
        newArr[newArr.length - 1] = goalId;
        goalIds = newArr;
    }

    public void removeGoalId(int goalId){
        int[] tmpArr = new int[goalIds.length - 1];
        boolean found = false;
        int indexToRemove = 0;
        for (int i = 0; i < goalIds.length;i++){
            if (goalId == goalIds[i]){
                found = true;
                indexToRemove = i;
                break;
            }
        }

        if(!found){
            return;
        }

        for (int i = 0, j = 0; i < goalIds.length; i++) {
            if (i != indexToRemove) {
                tmpArr[j++] = goalIds[i];
            }
        }

        this.goalIds = tmpArr;
    }

    public Document toDocument() {
        org.bson.Document document = new Document();
        document.append("firstname" , this.firstname );
        document.append("lastname" , this.lastname );
        document.append("sid" , this.sid);
        document.append("goalIds", this.goalIds);
        return document;
    }
}
