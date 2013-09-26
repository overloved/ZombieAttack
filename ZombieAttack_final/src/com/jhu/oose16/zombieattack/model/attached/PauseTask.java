package com.jhu.oose16.zombieattack.model.attached;

import java.util.TimerTask;

public abstract class PauseTask extends TimerTask {
	protected boolean isPaused;

	public void pause() {
		isPaused = true;
	}

	public void resume() {
		isPaused = false;
	}
	
	@Override
	public void run() {
		if(!isPaused) {
			runIfNotPaused();
		}
	}
	
	/** run if not paused*/
	public abstract void runIfNotPaused();
}
