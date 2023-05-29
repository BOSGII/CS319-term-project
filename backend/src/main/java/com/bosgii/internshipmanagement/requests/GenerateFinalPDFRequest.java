package com.bosgii.internshipmanagement.requests;


import java.util.ArrayList;

public class GenerateFinalPDFRequest {
    int pointOfEmployer;
    boolean isWorkComp;
    boolean isSupervisorComp;
    int evaluationOfCompanyByInstructor;//recommended : 1   satisfied : 2   not recommended : 3
    ArrayList<String> pages;
    ArrayList<String> scores;

    public int getPointOfEmployer() {
        return pointOfEmployer;
    }

    public void setPointOfEmployer(int pointOfEmployer) {
        this.pointOfEmployer = pointOfEmployer;
    }

    public boolean getIsWorkComp() {
        return isWorkComp;
    }

    public void setIsWorkComp(boolean isWorkComp) {
        this.isWorkComp = isWorkComp;
    }

    public boolean getIsSupervisorComp() {
        return isSupervisorComp;
    }

    public void setIsSupervisorComp(boolean isSupervisorComp) {
        this.isSupervisorComp = isSupervisorComp;
    }

    public int getEvaluationOfCompanyByInstructor() {
        return evaluationOfCompanyByInstructor;
    }

    public void setEvaluationOfCompanyByInstructor(int evaluationOfCompanyByInstructor) {
        this.evaluationOfCompanyByInstructor = evaluationOfCompanyByInstructor;
    }

    public ArrayList<String> getPages() {
        return pages;
    }

    public void setPages(ArrayList<String> pages) {
        this.pages = pages;
    }

    public ArrayList<String> getScores() {
        return scores;
    }

    public void setScores(ArrayList<String> scores) {
        this.scores = scores;
    }








}
