package com.jhu.oose16.zombieattack.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.jhu.oose16.zombieattack.model.entity.boulder.Boulder;
import com.jhu.oose16.zombieattack.model.entity.boulder.FireBoulder;
import com.jhu.oose16.zombieattack.model.entity.boulder.HoverBoulder;
import com.jhu.oose16.zombieattack.model.entity.boulder.IceBoulder;
import com.jhu.oose16.zombieattack.model.entity.boulder.NormalBoulder;

public class Workshop {
	
	// To store scientists you from the last level.
	private int extraScientists;
	private List<Boulder> selectedBoulders;
	private List<Boulder> availableBoulders;
	private LinkedHashMap<Boulder, Unlock> unlockedBoulders;

	public Workshop() {
		Unlock u = new Unlock(1);
		unlockedBoulders = new LinkedHashMap<Boulder, Unlock>();
		unlockedBoulders.put(new NormalBoulder(), u);
	}
	
	// Set what is unlocked based on level
	public Boulder setWorkshop(int scientists, int level) {
		setScientists(scientists);
		// Initialize available boulders
		Boulder newBoulder = null;
		if(level == 3) {
			newBoulder = new HoverBoulder();
			Unlock u = new Unlock(3);
			unlockedBoulders.put(newBoulder, u);
		} else if(level == 5) {
//			newBoulder = new TeleportBoulder();
			Unlock u = new Unlock(3);
			unlockedBoulders.put(newBoulder, u);
		} else if(level == 8) {
			newBoulder = new IceBoulder();
			Unlock u = new Unlock(3);
			unlockedBoulders.put(newBoulder, u);
		} else if(level == 11) {
			newBoulder = new FireBoulder();
			Unlock u = new Unlock(3);
			unlockedBoulders.put(newBoulder, u);
		} else if(level == 15) {
			newBoulder = new HoverBoulder();
			Unlock u = new Unlock(3);
			unlockedBoulders.put(newBoulder, u);
		}

		return newBoulder;
	}

	// Set boulders to be available for selection
	public void setSelection() {
		for(Boulder b : unlockedBoulders.keySet()) {
			addToAvailableList(unlockedBoulders.get(b), b);
		}
		// Reinitialize selected boulders to null
		selectedBoulders = new ArrayList<Boulder>();
	}
	
	// Set a boulder to be able to get selected
	private void addToAvailableList(Unlock unlock, Boulder boulder) {
		int i = 0;
		i = unlock.refreshBoulders();
		while(i > 0) {
			availableBoulders.add(boulder);
		}
	}

	// Set scientists for current level
	private void setScientists(int scientists) {
		extraScientists = scientists;
	}
	
	// Use on scientist on an available boulder
	public Boolean addScientist(Boulder boulder) {
		if((unlockedBoulders.containsKey(boulder)) && (extraScientists > 0)) {
			Unlock u = unlockedBoulders.get(boulder);
			u.addScientists(1);
			unlockedBoulders.put(boulder, u);
			extraScientists--;
			return true;
		} else {
			return false;
		}
	}

	// Select 1 of the 3 boulders to use
	public Boolean selectBoulder(Boulder boulder) {
		if((selectedBoulders.size() < 3) && (availableBoulders.contains(boulder))) {
			availableBoulders.remove(boulder);
			selectedBoulders.add(boulder);
			return true;
		} else {
			return false;
		}
	}

	// Allows user to undo an add resulting from a miss-click
	public Boolean undoAdd(Boulder boulder) {
		if(selectedBoulders.contains(boulder)) {
			selectedBoulders.remove(boulder);
			availableBoulders.add(boulder);
			return true;
		} else {
			return false;
		}
	}

	// Returns selected boulders to be used during next level
	public List<Boulder> getSelectedBoulders() {
		return selectedBoulders;
	}
	
	// Returns selected boulders to be used during next level
	public List<Boulder> getAvailableBoulders() {
		return availableBoulders;
	}
}
