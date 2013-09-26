package com.jhu.oose16.zombieattack.model;

public class Unlock {
	private int scientistsPerBoulder;
	private int numBoulders;
	private int extraScientists;
	private int totalScientists;
	
	public Unlock(int scientists) {
		scientistsPerBoulder = scientists;
		numBoulders = 0;
		extraScientists = 0;
		totalScientists = 0;
	}
	
	public int addScientists(int scientist) {
		totalScientists = totalScientists + scientist;
		extraScientists = totalScientists%scientistsPerBoulder;
		numBoulders = totalScientists/scientistsPerBoulder;
		return totalScientists;
	}
	
	public int getExtraScientists() {
		return extraScientists;
	}
	
	public int getNumBoulders() {
		return numBoulders;
	}
	
	public boolean useBoulder() {
		if(numBoulders > 0) {
			numBoulders--;
			return true;
		} else {
			return false;
		}
	}

	public int refreshBoulders() {
		numBoulders = totalScientists/scientistsPerBoulder;
		return numBoulders;
	}
}
