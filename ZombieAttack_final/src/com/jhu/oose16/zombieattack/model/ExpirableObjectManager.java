package com.jhu.oose16.zombieattack.model;

import java.util.ArrayList;
import java.util.List;

import com.jhu.oose16.zombieattack.listener.ExpirableObjectManagerListener;
import com.jhu.oose16.zombieattack.model.attached.CleanupContext;
import com.jhu.oose16.zombieattack.model.attached.CollisionContext;
import com.jhu.oose16.zombieattack.model.attached.DetectionContext;
import com.jhu.oose16.zombieattack.model.attached.Position;
import com.jhu.oose16.zombieattack.model.effect.Effect;
import com.jhu.oose16.zombieattack.model.entity.Entity;
import com.jhu.oose16.zombieattack.model.entity.boulder.Boulder;
import com.jhu.oose16.zombieattack.model.entity.materials.Barrier;

/**
 * It is used to store all the entities for our game.
 */
public class ExpirableObjectManager {
	/** The list stores all the entities in the game */
	private List<Entity> entities;
	/** The list stores all the effects in the game */
	private List<Effect> effects;
	/**
	 * Boulders left, including the unused boulder and under using boulder (the
	 * last one in the list)
	 */
	private List<Entity> boulderLeft;

	/** All the barriers */
	private List<Barrier> barriers;

	/** The number of dead zombie in the game */
	private int numDeadZombie;

	/** The number of zombie left in the game */
	private int numLeftZombie;
	
	/** The number of scientist */
	private int numLeftScientist;

	/** Entity manager listeners */
	private List<ExpirableObjectManagerListener> expirableObjectManagerListeners;

	/** Add entity manager listeners */
	public void addExpirableObjectManagerListeners(
			ExpirableObjectManagerListener expirableObjectManagerListener) {
		expirableObjectManagerListeners.add(expirableObjectManagerListener);
	}

	/**
	 * Set up the entities at first (barriers, goos, scientists and the boulders
	 * can be used in this game) <br\>
	 * 
	 * @param entitiesAtFirst
	 *            Barriers, goos and scientists
	 * @param boulderleft
	 *            Boulders can be used
	 */
	public ExpirableObjectManager(List<Entity> entitiesAtFirst,
			List<Entity> boulderleft) {
		
		entities = new ArrayList<Entity>();
		effects = new ArrayList<Effect>();
		expirableObjectManagerListeners = new ArrayList<ExpirableObjectManagerListener>();
		entities.addAll(entitiesAtFirst);

		this.boulderLeft = boulderleft;

		numDeadZombie = 0;

		numLeftZombie = 0;
		
		numLeftScientist = getEntityList(ExpirableObjectType.Scientist).size();

		barriers = new ArrayList<Barrier>();

		for (Entity entity : entitiesAtFirst) {
			if (entity instanceof Barrier) {
				barriers.add((Barrier) entity);
			}
		}
	}

	/** Update all the effects */
	private void updateEffect() {
		for (Effect effect : effects) {
			effect.update();
		}
	}

	/** Add new effect */
	public void addEffect(Effect effect) {
		if (effect != null) {
			effects.add(effect);
		}
		
		for (ExpirableObjectManagerListener entityManagerListener : expirableObjectManagerListeners) {
			entityManagerListener.addViewExpirableObject(effect);
		}
	}

	/** Remove the finished effects from the list */
	private void cleanDeadEffect() {
		for (int i = 0; i < effects.size(); i++) {
			if (effects.get(i).isShouldBeRemoved()) {
				effects.remove(i);
			}
		}
	}

	/** Add an entity */
	public void addEntity(Entity entity) {
		entities.add(entity);

		for (ExpirableObjectManagerListener entityManagerListener : expirableObjectManagerListeners) {
			entityManagerListener.addViewExpirableObject(entity);
		}
	}

	/** Add a list of entities */
	public void addEntityAll(List<Entity> entityList) {
		entities.addAll(entityList);
	}

	/**
	 * Get the entity list according to the entity type.
	 * 
	 * @param entityType
	 *            The type of the entity
	 * @return the list
	 */
	public List<Entity> getEntityList(ExpirableObjectType entityType) {
		if (entityType == ExpirableObjectType.All) {
			return entities;
		} else {
			List<Entity> res = new ArrayList<Entity>();
			for (int i = 0; i < entities.size(); i++) {
				if (entities.get(i).isExpirableObjectType(entityType)) {
					res.add(entities.get(i));
				}
			}
			return res;
		}
	}

	/**
	 * Get the number of entities according to the entity type.
	 * 
	 * @param entityType
	 *            The type of the entity
	 * @return the list
	 */
	public int getNumEntity(ExpirableObjectType entityType) {
		return getEntityList(entityType).size();
	}

	/** Remove the dead entity from the list. */
	private void cleanDeadEntity() {
		CleanupContext cleanupContext = new CleanupContextImpl();
		for (int i = 0; i < entities.size(); i++) {
			if (entities.get(i).isShouldBeRemoved()) {
				entities.get(i).cleanup(cleanupContext);
				entities.remove(i);
			}
		}
		for (int i = 0; i < boulderLeft.size(); i++) {
			if (boulderLeft.get(i).isShouldBeRemoved()) {
				boulderLeft.remove(i);
			}
		}

	}

	/**
	 * Update all the entities.<br\>
	 * Checks collision and detection and acts accordingly.<br\>
	 * At the end delete the dead entities from the list.
	 */
	private void updateEntity() {
		CollisionContextImpl collisionContextImpl = new CollisionContextImpl();
		DetectionContextImpl detectionContextImpl = new DetectionContextImpl();
		// update position for all the entities
		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).update();
		}

		for (int i = 0; i < entities.size(); i++) {
			for (int j = i + 1; j < entities.size(); j++) {
				// retrieve the entities
				final Entity entityI = entities.get(i);
				final Entity entityJ = entities.get(j);
				// perform checks before changing entity states
				final boolean iHitsJ = entityI.checkCollision(entityJ);
				final boolean jHitsI = iHitsJ;
				final boolean iSeesJ = entityI.checkDetection(entityJ);
				final boolean jSeesI = entityJ.checkDetection(entityI);
				// now apply results of the checks
				if (iHitsJ) {
					entityI.applyCollision(entityJ, collisionContextImpl);
				}
				if (jHitsI) {
					entityJ.applyCollision(entityI, collisionContextImpl);
				}
				if (iSeesJ) {
					entityI.applyDetection(entityJ, detectionContextImpl);
				}
				if (jSeesI) {
					entityJ.applyDetection(entityI, detectionContextImpl);
				}
			}
		}
	}

	public void updateEntityAndEffect() {
		updateEntity();
		updateEffect();

		cleanDeadEntity();
		cleanDeadEffect();

		for (ExpirableObjectManagerListener objectManagerListener : expirableObjectManagerListeners) {
			objectManagerListener.cleanDeadViewObject();
		}
	}

	/** use the next boulder in the list */
	public boolean useTheNextBoulder(Position position) {
		if (!isUsingBoulder() && getNumBoulderLeft() > 0) {
			Boulder nextBoulder = (Boulder) boulderLeft
					.get(boulderLeft.size() - 1);
			nextBoulder.setCenter(position);
			if (islegalPlaced(nextBoulder)) {
				addEntity(boulderLeft.get(boulderLeft.size() - 1));
				return true;
			}
		}
		return false;
	}

	/**
	 * Whether given boulder can be placed
	 * 
	 * @param boulder
	 *            The given boulder
	 * @return true: can be placed
	 */
	private boolean islegalPlaced(Boulder boulder) {
		for (Entity barrier : barriers) {
			if (barrier.checkCollision(boulder)) {
				return false;
			}
		}
		return true;
	}

	/** Get the number of boulder left */
	public int getNumBoulderLeft() {
		return boulderLeft.size();
	}

	/** Get the boulder in using */
	public Boulder getUsingBoulder() {
		if (isUsingBoulder()) {
			return (Boulder) boulderLeft.get(boulderLeft.size() - 1);
		} else {
			return null;
		}
	}

	/**
	 * Whether is using a boulder
	 * 
	 * @return True: using a boulder now
	 */
	private boolean isUsingBoulder() {
		return getNumEntity(ExpirableObjectType.Boulder) > 0;
	}

	/**
	 * Get the number of dead zombie
	 * 
	 * @return The number of dead zombie
	 */
	public int getNumDeadZombie() {
		return numDeadZombie;
	}

	public int getNumLeftZombie() {
		return numLeftZombie;
	}

	public void increaseNumLeftZombie() {
		this.numLeftZombie++;
	}

	public void decreaseNumLeftZombie() {
		this.numLeftZombie--;
	}

	private class CleanupContextImpl implements CleanupContext {
		@Override
		public void trackDeadAndLeftZombieCount() {
			ExpirableObjectManager.this.numDeadZombie++;
			ExpirableObjectManager.this.numLeftZombie--;
		}

		@Override
		public void addEntity(Entity entity) {
			ExpirableObjectManager.this.addEntity(entity);
		}

		@Override
		public void trackDeadZombieCount() {
			ExpirableObjectManager.this.numLeftZombie--;
		}

		@Override
		public void trackDeadScientistCount() {
			ExpirableObjectManager.this.numLeftScientist--;
		}
	}

	private class CollisionContextImpl implements CollisionContext {
		@Override
		public void addEffect(Effect effect) {
			ExpirableObjectManager.this.addEffect(effect);
		}
	}

	private class DetectionContextImpl implements DetectionContext {
		@Override
		public List<Entity> getBarriers() {
			return ExpirableObjectManager.this
					.getEntityList(ExpirableObjectType.Barrier);
		}
	}

	public List<Entity> getBouldersLeft() {
		return boulderLeft;
	}

	public int getNumLeftScientist() {
		return numLeftScientist;
	}

}
