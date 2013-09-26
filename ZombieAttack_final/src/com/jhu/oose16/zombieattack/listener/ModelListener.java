package com.jhu.oose16.zombieattack.listener;

import com.jhu.oose16.zombieattack.model.GameModelState;

/**
 * To listen all the functions that called from the model.
 */
public interface ModelListener {
	public void update(GameModelState gameModelState);
}
