package com.bosgii.internshipmanagement.requests;


import java.util.ArrayList;

public class GenerateFinalPDFRequest {
    int pointOfEmployer;
    boolean isWorkComp;
    boolean isSupervisorComp;
    int evaluationOfCompanyByInstructor;//recommended : 1   satisfied : 2   not recommended : 3
    ArrayList<ArrayList<Integer>> pages;
    ArrayList<Integer> scores;


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

    public void setIsSupervisorComp(boolean ısSupervisorComp) {
        this.isSupervisorComp = ısSupervisorComp;
    }

    public int getEvaluationOfCompanyByInstructor() {
        return evaluationOfCompanyByInstructor;
    }

    public void setEvaluationOfCompanyByInstructor(int evaluationOfCompany) {
        this.evaluationOfCompanyByInstructor = evaluationOfCompany;
    }

    public ArrayList<ArrayList<Integer>> getPages() {
        return pages;
    }

    public void setPages(ArrayList<ArrayList<Integer>> pages) {
        this.pages = pages;
    }

    public ArrayList<Integer> getScores() {
        return scores;
    }

    public void setScores(ArrayList<Integer> scores) {
        this.scores = scores;
    }



}
