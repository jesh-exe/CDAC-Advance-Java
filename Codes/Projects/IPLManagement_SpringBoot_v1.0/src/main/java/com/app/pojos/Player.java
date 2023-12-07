package com.app.pojos;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "player")
public class Player extends BaseEntity {

	@Column(length = 100, nullable = false)
	private String name;
	private LocalDate dob;
	private double battingAvg;
	private int wicketsTaken;
	@ManyToOne
	@JoinColumn(name = "team_id")
	private Team myTeam;
	
	public Player() {
		// TODO Auto-generated constructor stub
	}

	public Player(String name, LocalDate dob, double battingAvg, int wicketsTaken) {
		super();
		this.name = name;
		this.dob = dob;
		this.battingAvg = battingAvg;
		this.wicketsTaken = wicketsTaken;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public double getBattingAvg() {
		return battingAvg;
	}

	public void setBattingAvg(double battingAvg) {
		this.battingAvg = battingAvg;
	}

	public int getWicketsTaken() {
		return wicketsTaken;
	}

	public void setWicketsTaken(int wicketsTaken) {
		this.wicketsTaken = wicketsTaken;
	}

	public Team getMyTeam() {
		return myTeam;
	}

	public void setMyTeam(Team myTeam) {
		this.myTeam = myTeam;
	}

	@Override
	public String toString() {
		return "Player [name=" + name + ", dob=" + dob + ", battingAvg=" + battingAvg + ", wicketsTaken=" + wicketsTaken
				 + "]";
	}
	
	
	
}
