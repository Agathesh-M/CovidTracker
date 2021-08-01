package com.agathesh.coronavirustracker.model;

public class LocationStats {
	
	private String state;
	private String country;
	private int latestStats;
	private int diff;
	
	
	public int getDiff() {
		return diff;
	}


	public void setDiff(int diff) {
		this.diff = diff;
	}

	
	public String getState() {
		return state;
	}
	public String setState(String state) {
		return this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public int getLatestStats() {
		return latestStats;
	}
	public void setLatestStats(int latestStats) {
		this.latestStats = latestStats;
	}
	@Override
	public String toString() {
		return "LocationStats [state=" + state + ", country=" + country + ", latestStats=" + latestStats + "]";
	}
	
	
}
