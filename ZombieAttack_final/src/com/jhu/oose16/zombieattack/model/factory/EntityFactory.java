package com.jhu.oose16.zombieattack.model.factory;

import com.jhu.oose16.zombieattack.model.entity.Entity;

/**
 * An interface for entity factory
 */
public interface EntityFactory<E extends Entity> {
	public E newEntity();
}
