package com.EduTestPortal.model;

public class QuizStats 
{
	 private int totalAttempts;
	    private int highestScore;
	    private int lowestScore;
	    private double averageScore;

	    public int getTotalAttempts() {
	        return totalAttempts;
	    }

	    public void setTotalAttempts(int totalAttempts) {
	        this.totalAttempts = totalAttempts;
	    }

	    public int getHighestScore() {
	        return highestScore;
	    }

	    public void setHighestScore(int highestScore) {
	        this.highestScore = highestScore;
	    }

	    public int getLowestScore() {
	        return lowestScore;
	    }

	    public void setLowestScore(int lowestScore) {
	        this.lowestScore = lowestScore;
	    }

	    public double getAverageScore() {
	        return averageScore;
	    }

	    public void setAverageScore(double averageScore) {
	        this.averageScore = averageScore;
	    }
}
