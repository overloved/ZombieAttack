package com.jhu.oose16.zombieattack.model.attached;

import com.jhu.oose16.zombieattack.model.effect.Effect;

/** Do collision context after an entity disappear */
public interface CollisionContext {

	/**
	 * Add new effect to the effect manager
	 * 
	 * @param effect
	 *            The new effect need to be add to the effect manager
	 */
	public void addEffect(Effect effect);

}
