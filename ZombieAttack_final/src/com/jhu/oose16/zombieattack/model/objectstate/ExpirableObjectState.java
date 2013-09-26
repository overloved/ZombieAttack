package com.jhu.oose16.zombieattack.model.objectstate;

/** The state for the object in the game */
public class ExpirableObjectState {

	private LifeState lifeState;
	private InvincibleState invincibleState;
	private DirectionState directionState;
	private RunningState runningState;

	public ExpirableObjectState() {
		lifeState = LifeState.APPEARANCE;
		invincibleState = InvincibleState.NORMAL;
		directionState = DirectionState.DOWN;
		runningState = RunningState.RUNNING;
	}

	public ExpirableObjectState(LifeState lifeState, InvincibleState invincibleState,
			DirectionState directionState, RunningState runningState) {
		this.lifeState = lifeState;
		this.invincibleState = invincibleState;
		this.directionState = directionState;
		this.runningState = runningState;
	}

	public LifeState getLifeState() {
		return lifeState;
	}

	public void setLifeState(LifeState lifeState) {
		this.lifeState = lifeState;
	}

	public InvincibleState getInvincibleState() {
		return invincibleState;
	}

	public void setInvincibleState(InvincibleState invincibleState) {
		this.invincibleState = invincibleState;
	}

	public DirectionState getDirectionState() {
		return directionState;
	}

	public void setDirectionState(DirectionState directionState) {
		this.directionState = directionState;
	}

	public RunningState getRunningState() {
		return runningState;
	}

	public void setRunningState(RunningState runningState) {
		this.runningState = runningState;
	}

	@Override
	public boolean equals(Object o) {
		ExpirableObjectState objectState = (ExpirableObjectState) o;

		if (lifeState == objectState.getLifeState()
				&& invincibleState == objectState.getInvincibleState()
				&& directionState == objectState.getDirectionState()
				&& runningState == objectState.getRunningState()) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		int result = 17;
		result = 31 * result + lifeState.hashCode();
		result = 31 * result + invincibleState.hashCode();
		result = 31 * result + directionState.hashCode();
		result = 31 * result + runningState.hashCode();
		return result;
	}

	/**
	 * Copy a new one and save it
	 * 
	 * @param objectState
	 *            The given object state
	 */
	public ExpirableObjectState getCopy(ExpirableObjectState objectState) {
		return new ExpirableObjectState(lifeState, invincibleState, directionState,
				runningState);
	}

}
