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
package com.watabou.pixeldungeon.actors.hero;

import com.watabou.pixeldungeon.Assets;
import com.watabou.pixeldungeon.Badges;
import com.watabou.pixeldungeon.Dungeon;
import com.watabou.pixeldungeon.items.TomeOfMastery;
import com.watabou.pixeldungeon.items.armor.ClothArmor;
import com.watabou.pixeldungeon.items.food.Food;
import com.watabou.pixeldungeon.items.potions.PotionOfStrength;
import com.watabou.pixeldungeon.items.rings.RingOfShadows;
import com.watabou.pixeldungeon.items.scrolls.ScrollOfIdentify;
import com.watabou.pixeldungeon.items.scrolls.ScrollOfMagicMapping;
import com.watabou.pixeldungeon.items.wands.WandOfMagicMissile;
import com.watabou.pixeldungeon.items.weapon.melee.Dagger;
import com.watabou.pixeldungeon.items.weapon.melee.Knuckles;
import com.watabou.pixeldungeon.items.weapon.melee.ShortSword;
import com.watabou.pixeldungeon.items.weapon.missiles.Dart;
import com.watabou.pixeldungeon.items.weapon.missiles.Boomerang;
import com.watabou.utils.Bundle;

import static com.watabou.noosa.NoosaI18N.tr;

public enum HeroClass {

	WARRIOR( "warrior", "war", 5 ), MAGE( "mage", "mag", 5), ROGUE( "rogue", "rog", 6), HUNTRESS( "huntress", "hun", 5);
	
	private final String title;
	private final String i18nPrefix;
	private final int numI18nStrings;

	private HeroClass(String title, String i18nPrefix, int numI18nStrings) {
		this.title = title;
		this.i18nPrefix = i18nPrefix;
		this.numI18nStrings = numI18nStrings;
	}
	
	public void initHero( Hero hero ) {
		
		hero.heroClass = this;
		
		switch (this) {
		case WARRIOR:
			initWarrior( hero );
			break;
			
		case MAGE:
			initMage( hero );
			break;
			
		case ROGUE:
			initRogue( hero );
			break;
			
		case HUNTRESS:
			initHuntress( hero );
			break;
		}
		
		hero.updateAwareness();
	}
	
	private static void initWarrior( Hero hero ) {
		hero.STR = hero.STR + 1;
		
		(hero.belongings.weapon = new ShortSword()).identify();
		(hero.belongings.armor = new ClothArmor()).identify();
		new Dart( 8 ).identify().collect();
		new Food().identify().collect();
		
		if (Badges.isUnlocked( Badges.Badge.MASTERY_WARRIOR )) {
			new TomeOfMastery().collect();
		}
		
		Dungeon.quickslot = Dart.class;
		
		new PotionOfStrength().setKnown();
	}
	
	private static void initMage( Hero hero ) {	
		(hero.belongings.weapon = new Knuckles()).identify();
		(hero.belongings.armor = new ClothArmor()).identify();
		new Food().identify().collect();
		
		WandOfMagicMissile wand = new WandOfMagicMissile();
		wand.identify().collect();
		
		if (Badges.isUnlocked( Badges.Badge.MASTERY_MAGE )) {
			new TomeOfMastery().collect();
		}
		
		Dungeon.quickslot = wand;
		
		new ScrollOfIdentify().setKnown();
	}
	
	private static void initRogue( Hero hero ) {
		(hero.belongings.weapon = new Dagger()).identify();
		(hero.belongings.armor = new ClothArmor()).identify();
		(hero.belongings.ring1 = new RingOfShadows()).upgrade().identify();
		new Dart( 8 ).identify().collect();
		new Food().identify().collect();
		
		hero.belongings.ring1.activate( hero );
		
		if (Badges.isUnlocked( Badges.Badge.MASTERY_ROGUE )) {
			new TomeOfMastery().collect();
		}
		
		Dungeon.quickslot = Dart.class;
		
		new ScrollOfMagicMapping().setKnown();
	}
	
	private static void initHuntress( Hero hero ) {
		
		hero.HP = (hero.HT -= 5);
		
		(hero.belongings.weapon = new Dagger()).identify();
		(hero.belongings.armor = new ClothArmor()).identify();
		Boomerang boomerang = new Boomerang();
		boomerang.identify().collect();
		new Food().identify().collect();
		
		if (Badges.isUnlocked( Badges.Badge.MASTERY_HUNTRESS )) {
			new TomeOfMastery().collect();
		}
		
		Dungeon.quickslot = boomerang;
	}
	
	public String title() {
		return title;
	}
	
	public String spritesheet() {
		
		switch (this) {
		case WARRIOR:
			return Assets.WARRIOR;
		case MAGE:
			return Assets.MAGE;
		case ROGUE:
			return Assets.ROGUE;
		case HUNTRESS:
			return Assets.HUNTRESS;
		}
		
		return null;
	}
	
	public String[] perks() {
		final String[] result = new String[numI18nStrings];

		for (int j = 0; j < numI18nStrings; j++) {
			result[j] = tr("perk_" + i18nPrefix + "_" + j);
		}

		return result;
	}

	private static final String BUNDLE_KEY_CLASS = "class";

	public void storeInBundle(Bundle bundle) {
		bundle.put(BUNDLE_KEY_CLASS, toString());
	}
	
	public static HeroClass restoreInBundle( Bundle bundle ) {
		String value = bundle.getString(BUNDLE_KEY_CLASS);
		return value.length() > 0 ? valueOf( value ) : ROGUE;
	}
}
