package Model;

import java.util.*;

public class NavigateButtonModel {
	
	private boolean enable;
	int numStates;
	private SizedStack<Originator.Memento> savedStates;
	
	/**
	 * Only constructor of class with following parameter requirement
	 * @param enable enable state of buttonss
	 */
	public NavigateButtonModel(boolean enable, int numStates) {
		this.enable = enable;
		this.numStates = numStates;
		savedStates = new SizedStack<Originator.Memento>(numStates);
	}
	

	/**
	 * @return the enable
	 */
	public boolean isEnable() {
		return enable;
	}

	/**
	 * @param enable the enable to set
	 */
	public void setEnable(boolean enable) {
		this.enable = enable;
	}


	/**
	 * @return the savedStates
	 */
	public SizedStack<Originator.Memento> getSavedStates() {
		return savedStates;
	}
	
//	public void setNumStates(int numStates) {
//		savedStates.setMaxSize(numStates);
//	}
		
}
