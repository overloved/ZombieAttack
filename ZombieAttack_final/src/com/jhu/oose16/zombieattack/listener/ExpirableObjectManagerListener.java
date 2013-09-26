package com.jhu.oose16.zombieattack.listener;

import com.jhu.oose16.zombieattack.model.ExpirableObject;

public interface ExpirableObjectManagerListener {

	public void addViewExpirableObject(ExpirableObject expirableObject);

	public void cleanDeadViewObject();

}
