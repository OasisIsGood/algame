package zelda.observer;

import gameframework.base.IntegerObservable;

public class EnnemyObserver extends IntegerObservable {
	private static EnnemyObserver ennemy = null;
	
	private EnnemyObserver() {
		super();
	}
	
	public static EnnemyObserver getInstance() {
		if(ennemy == null)
			ennemy =  new EnnemyObserver();
		return ennemy;
	}
}
