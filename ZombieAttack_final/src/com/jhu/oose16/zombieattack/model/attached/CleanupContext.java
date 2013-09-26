package com.jhu.oose16.zombieattack.model.attached;

import com.jhu.oose16.zombieattack.model.entity.Entity;

/** Do clean up context after an entity disappear */
public interface CleanupContext {
	public void trackDeadAndLeftZombieCount();
	
	public void trackDeadZombieCount();
	
	public void trackDeadScientistCount();

	public void addEntity(Entity entity);
}
