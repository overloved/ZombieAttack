package com.jhu.oose16.zombieattack.model.factory;

import com.jhu.oose16.zombieattack.model.entity.zombie.FastZombie;

public class ZombieFastFactory implements EntityFactory<FastZombie>{
	@Override
	public FastZombie newEntity() {
		return new FastZombie();
	}
}