package com.jhu.oose16.zombieattack.view.component.object;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.graphics.Canvas;

import com.jhu.oose16.zombieattack.listener.ExpirableObjectManagerListener;
import com.jhu.oose16.zombieattack.model.ExpirableObject;
import com.jhu.oose16.zombieattack.model.ExpirableObjectManager;
import com.jhu.oose16.zombieattack.model.ExpirableObjectType;
import com.jhu.oose16.zombieattack.model.entity.Entity;
import com.jhu.oose16.zombieattack.view.component.ViewComponentManager;
import com.jhu.oose16.zombieattack.view.factory.ViewBarrierLargeHorizontalFactory;
import com.jhu.oose16.zombieattack.view.factory.ViewBarrierLargeVerticalFactory;
import com.jhu.oose16.zombieattack.view.factory.ViewBarrierSmallHorizontalFactory;
import com.jhu.oose16.zombieattack.view.factory.ViewBarrierSmallVerticalFactory;
import com.jhu.oose16.zombieattack.view.factory.ViewBoulderEtherealFactory;
import com.jhu.oose16.zombieattack.view.factory.ViewBoulderFireFactory;
import com.jhu.oose16.zombieattack.view.factory.ViewBoulderHoverFactory;
import com.jhu.oose16.zombieattack.view.factory.ViewBoulderIceFactory;
import com.jhu.oose16.zombieattack.view.factory.ViewBoulderNormalFactory;
import com.jhu.oose16.zombieattack.view.factory.ViewEffectFireFactory;
import com.jhu.oose16.zombieattack.view.factory.ViewEffectIceSmallFactory;
import com.jhu.oose16.zombieattack.view.factory.ViewExpirableObjectFactory;
import com.jhu.oose16.zombieattack.view.factory.ViewGooFactory;
import com.jhu.oose16.zombieattack.view.factory.ViewScientistNormalFactory;
import com.jhu.oose16.zombieattack.view.factory.ViewScientistTeslaFactory;
import com.jhu.oose16.zombieattack.view.factory.ViewScreenBoundFactory;
import com.jhu.oose16.zombieattack.view.factory.ViewZombieCurseFactory;
import com.jhu.oose16.zombieattack.view.factory.ViewZombieFastFactory;
import com.jhu.oose16.zombieattack.view.factory.ViewZombieGiantFactory;
import com.jhu.oose16.zombieattack.view.factory.ViewZombieKillerFactory;
import com.jhu.oose16.zombieattack.view.factory.ViewZombieLeaderFactory;
import com.jhu.oose16.zombieattack.view.factory.ViewZombieNormalFactory;
import com.jhu.oose16.zombieattack.view.factory.ViewZombieSmartFactory;
import com.jhu.oose16.zombieattack.view.factory.ViewZombieStrongerFactory;

public class ViewExpirableObjectManager<K> extends ViewComponentManager<K> {

	private static final Map<ExpirableObjectType, ViewExpirableObjectFactory> viewFactoryMap = new HashMap<ExpirableObjectType, ViewExpirableObjectFactory>() {

		private static final long serialVersionUID = 6757071394855971761L;

		{
			put(ExpirableObjectType.BarrierLargeHorizontal,
					new ViewBarrierLargeHorizontalFactory());
			put(ExpirableObjectType.BarrierLargeVertical,
					new ViewBarrierLargeVerticalFactory());
			put(ExpirableObjectType.BarrierSmallHorizontal,
					new ViewBarrierSmallHorizontalFactory());
			put(ExpirableObjectType.BarrierSmallVertical,
					new ViewBarrierSmallVerticalFactory());
			put(ExpirableObjectType.Goo, new ViewGooFactory());
			put(ExpirableObjectType.NormalBoulder,
					new ViewBoulderNormalFactory());
			put(ExpirableObjectType.IceBoulder, new ViewBoulderIceFactory());
			put(ExpirableObjectType.FireBoulder, new ViewBoulderFireFactory());
			put(ExpirableObjectType.HoverBoulder, new ViewBoulderHoverFactory());
			put(ExpirableObjectType.EtherealBoulder,
					new ViewBoulderEtherealFactory());
			put(ExpirableObjectType.NormalScientist,
					new ViewScientistNormalFactory());
			put(ExpirableObjectType.Tesla, new ViewScientistTeslaFactory());
			put(ExpirableObjectType.NormalZombie, new ViewZombieNormalFactory());
			put(ExpirableObjectType.StrongerZombie,
					new ViewZombieStrongerFactory());
			put(ExpirableObjectType.FastZombie, new ViewZombieFastFactory());
			put(ExpirableObjectType.SmartZombie, new ViewZombieSmartFactory());
			put(ExpirableObjectType.LeaderZombie, new ViewZombieLeaderFactory());
			put(ExpirableObjectType.GiantZombie, new ViewZombieGiantFactory());
			put(ExpirableObjectType.CurseZombie, new ViewZombieCurseFactory());
			put(ExpirableObjectType.KillerZombie, new ViewZombieKillerFactory());
			put(ExpirableObjectType.iceBoulderToZombieEffect,
					new ViewEffectIceSmallFactory());
			put(ExpirableObjectType.fireBoulderToZombieEffect,
					new ViewEffectFireFactory());
			put(ExpirableObjectType.ScreenBound, new ViewScreenBoundFactory());
		}
	};

	private List<ViewExpirableObject> viewExpirableObjects;

	public ViewExpirableObjectManager(Context context) {
		super(context);
	}

	public void addViewObject(ExpirableObject expirableObject) {
		int index = 0;
		if (expirableObject.isExpirableObjectType(ExpirableObjectType.Goo)) {
			index = 0;
		} else if (!expirableObject
				.isExpirableObjectType(ExpirableObjectType.Boulder)) {
			index = Math.max(index, viewExpirableObjects.size());
		} else {
			index = viewExpirableObjects.size();
		}
		viewExpirableObjects.add(
				index,
				viewFactoryMap.get(
						expirableObject.getDetailExpirableObjectType())
						.getViewExpirableObject(expirableObject));
	}

	public void initializeViewEntityManager(
			ExpirableObjectManager expirableObjectManager) {
		viewExpirableObjects = new ArrayList<ViewExpirableObject>();

		List<Entity> entities = expirableObjectManager
				.getEntityList(ExpirableObjectType.All);
		for (Entity entity : entities) {
			addViewObject(entity);
		}

		expirableObjectManager
				.addExpirableObjectManagerListeners(new ExpirableObjectManagerListener() {

					@Override
					public void cleanDeadViewObject() {
						for (int i = 0; i < viewExpirableObjects.size(); i++) {
							if (viewExpirableObjects.get(i).isShouldBeRemoved()) {
								viewExpirableObjects.remove(i);
							}
						}
					}

					@Override
					public void addViewExpirableObject(
							ExpirableObject expirableObject) {
						ViewExpirableObjectManager.this
								.addViewObject(expirableObject);
					}

				});
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		for (int i = 0; i < viewExpirableObjects.size(); i++) {
			viewExpirableObjects.get(i).draw(canvas);
		}
	}

}
