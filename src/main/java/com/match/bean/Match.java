package com.match.bean;


public class Match {
	String team1;
	String team2;
	String location;
	String date;

	public Match(String team1, String team2, String location, String date) {
		super();
		this.team1 = team1;
		this.team2 = team2;
		this.location = location;
		this.date = date;
	}

	
	public String getTeam1() {
		return team1;
	}


	public void setTeam1(String team1) {
		this.team1 = team1;
	}


	public String getTeam2() {
		return team2;
	}


	public void setTeam2(String team2) {
		this.team2 = team2;
	}


	public String getLocation() {
		return location;
	}


	public void setLocation(String location) {
		this.location = location;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	public String toString() {
		return team1 + " v/s " + team2 + " at " + location + " on " + date;
	}
}
