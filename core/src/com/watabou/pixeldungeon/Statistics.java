/*
 * Pixel Dungeon
 * Copyright (C) 2012-2014  Oleg Dolya
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */
package com.watabou.pixeldungeon;

import com.watabou.utils.Bundle;

public class Statistics {
	
	public static int goldCollected;
	public static int deepestFloor;
	public static int enemiesSlain;
	public static int foodEaten;
	public static int potionsCooked;
	public static int piranhasKilled;
	public static int nightHunt;
	public static int ankhsUsed;
	
	public static float duration;
	
	public static boolean qualifiedForNoKilling = false;
	public static boolean completedWithNoKilling = false;
	
	public static boolean amuletObtained = false;
	
	public static void reset() {
		
		goldCollected	= 0;
		deepestFloor	= 0;
		enemiesSlain	= 0;
		foodEaten		= 0;
		potionsCooked	= 0;
		piranhasKilled	= 0;
		nightHunt		= 0;	
		ankhsUsed		= 0;
		
		duration	= 0;
		
		qualifiedForNoKilling = false;
		
		amuletObtained = false;
		
	}
	
	private static final String BUNDLE_KEY_GOLD = "score";
	private static final String BUNDLE_KEY_DEEPEST = "maxDepth";
	private static final String BUNDLE_KEY_SLAIN = "enemiesSlain";
	private static final String BUNDLE_KEY_FOOD = "foodEaten";
	private static final String BUNDLE_KEY_ALCHEMY = "potionsCooked";
	private static final String BUNDLE_KEY_PIRANHAS = "priranhas";
	private static final String BUNDLE_KEY_NIGHT = "nightHunt";
	private static final String BUNDLE_KEY_ANKHS = "ankhsUsed";
	private static final String BUNDLE_KEY_DURATION = "duration";
	private static final String BUNDLE_KEY_AMULET = "amuletObtained";
	
	public static void storeInBundle( Bundle bundle ) {
		bundle.put(BUNDLE_KEY_GOLD, goldCollected);
		bundle.put(BUNDLE_KEY_DEEPEST, deepestFloor);
		bundle.put(BUNDLE_KEY_SLAIN, enemiesSlain);
		bundle.put(BUNDLE_KEY_FOOD, foodEaten);
		bundle.put(BUNDLE_KEY_ALCHEMY, potionsCooked);
		bundle.put(BUNDLE_KEY_PIRANHAS, piranhasKilled);
		bundle.put(BUNDLE_KEY_NIGHT, nightHunt);
		bundle.put(BUNDLE_KEY_ANKHS, ankhsUsed);
		bundle.put(BUNDLE_KEY_DURATION, duration);
		bundle.put(BUNDLE_KEY_AMULET, amuletObtained);
	}
	
	public static void restoreFromBundle( Bundle bundle ) {
		goldCollected = bundle.getInt(BUNDLE_KEY_GOLD);
		deepestFloor = bundle.getInt(BUNDLE_KEY_DEEPEST);
		enemiesSlain = bundle.getInt(BUNDLE_KEY_SLAIN);
		foodEaten = bundle.getInt(BUNDLE_KEY_FOOD);
		potionsCooked = bundle.getInt(BUNDLE_KEY_ALCHEMY);
		piranhasKilled = bundle.getInt(BUNDLE_KEY_PIRANHAS);
		nightHunt = bundle.getInt(BUNDLE_KEY_NIGHT);
		ankhsUsed = bundle.getInt(BUNDLE_KEY_ANKHS);
		duration = bundle.getFloat(BUNDLE_KEY_DURATION);
		amuletObtained = bundle.getBoolean(BUNDLE_KEY_AMULET);
	}

}
