package com.jhu.oose16.zombieattack.view.factory;

import com.jhu.oose16.zombieattack.model.ExpirableObject;
import com.jhu.oose16.zombieattack.view.component.object.ViewExpirableObject;

public interface ViewExpirableObjectFactory {
	public ViewExpirableObject getViewExpirableObject(ExpirableObject expirableObject);
}
