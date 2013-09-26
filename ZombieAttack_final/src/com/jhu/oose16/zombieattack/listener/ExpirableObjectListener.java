package com.jhu.oose16.zombieattack.listener;

import com.jhu.oose16.zombieattack.model.objectstate.ExpirableObjectState;

public interface ExpirableObjectListener {

	public void update(int centerX, int centerY, ExpirableObjectState objectState);

	public void remove();

}
