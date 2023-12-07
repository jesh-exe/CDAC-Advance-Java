package com.app.pojos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "team")
public class Team extends BaseEntity{

	@Column(name = "team_name",length = 100,unique = true, nullable = false)
	private String name;
	@Column(name = "team_abbrv",length = 10,unique = true, nullable = false)
	private String abbreviation;
	@Column(name = "team_owner",length = 100, nullable = false)	
	private String owner;
	@Column(name = "max_age")
	private int maxAge;
	@Column(name = "min_batting_avg")
	private double minBattingAvg;
	@Column(name = "min_wickets_taken")
	private int minWicketsTaken;
	
	@OneToMany(mappedBy = "myTeam")
	List<Player> players = new ArrayList<>();
	
	public Team() {

	}

	public Team(String name, String abbreviation, String owner, int maxAge, double minBattingAvg, int minWicketsTaken) {
		super();
		this.name = name;
		this.abbreviation = abbreviation;
		this.owner = owner;
		this.maxAge = maxAge;
		this.minBattingAvg = minBattingAvg;
		this.minWicketsTaken = minWicketsTaken;
	}
	
	public void addPlayer(Player obj)
	{
		players.add(obj);
		obj.setMyTeam(this);
	}
	
	public void removePlayer(Player obj)
	{
		players.remove(obj);
		obj.setMyTeam(null);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public int getMaxAge() {
		return maxAge;
	}

	public void setMaxAge(int maxAge) {
		this.maxAge = maxAge;
	}

	public double getMinBattingAvg() {
		return minBattingAvg;
	}

	public void setMinBattingAvg(double minBattingAvg) {
		this.minBattingAvg = minBattingAvg;
	}

	public int getMinWicketsTaken() {
		return minWicketsTaken;
	}

	public void setMinWicketsTaken(int minWicketsTaken) {
		this.minWicketsTaken = minWicketsTaken;
	}
	
	
	
}
