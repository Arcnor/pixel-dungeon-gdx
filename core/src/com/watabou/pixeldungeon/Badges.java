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

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import com.badlogic.gdx.utils.I18NBundle;
import com.watabou.noosa.Game;
import com.watabou.pixeldungeon.actors.mobs.Acidic;
import com.watabou.pixeldungeon.actors.mobs.Albino;
import com.watabou.pixeldungeon.actors.mobs.Bandit;
import com.watabou.pixeldungeon.actors.mobs.Mob;
import com.watabou.pixeldungeon.actors.mobs.Senior;
import com.watabou.pixeldungeon.actors.mobs.Shielded;
import com.watabou.pixeldungeon.items.Item;
import com.watabou.pixeldungeon.items.bags.ScrollHolder;
import com.watabou.pixeldungeon.items.bags.SeedPouch;
import com.watabou.pixeldungeon.items.bags.WandHolster;
import com.watabou.pixeldungeon.items.potions.Potion;
import com.watabou.pixeldungeon.items.rings.Ring;
import com.watabou.pixeldungeon.items.rings.RingOfHaggler;
import com.watabou.pixeldungeon.items.rings.RingOfThorns;
import com.watabou.pixeldungeon.items.scrolls.Scroll;
import com.watabou.pixeldungeon.items.wands.Wand;
import com.watabou.pixeldungeon.scenes.PixelScene;
import com.watabou.pixeldungeon.utils.GLog;
import com.watabou.utils.Bundle;

public class Badges {
	
	public static enum Badge {
		MONSTERS_SLAIN_1( "badge_enemies_slain", 0, 10 ),
		MONSTERS_SLAIN_2( "badge_enemies_slain", 1, 50 ),
		MONSTERS_SLAIN_3( "badge_enemies_slain", 2, 150 ),
		MONSTERS_SLAIN_4( "badge_enemies_slain", 3, 250 ),
		GOLD_COLLECTED_1( "badge_gold_collected", 4, 100 ),
		GOLD_COLLECTED_2( "badge_gold_collected", 5, 500 ),
		GOLD_COLLECTED_3( "badge_gold_collected", 6, 2500 ),
		GOLD_COLLECTED_4( "badge_gold_collected", 7, 7500 ),
		LEVEL_REACHED_1( "badge_level_reached", 8, 6 ),
		LEVEL_REACHED_2( "badge_level_reached", 9, 12 ),
		LEVEL_REACHED_3( "badge_level_reached", 10, 18 ),
		LEVEL_REACHED_4( "badge_level_reached", 11, 24 ),
		ALL_POTIONS_IDENTIFIED( "badge_all_potions", 16 ),
		ALL_SCROLLS_IDENTIFIED( "badge_all_scrolls", 17 ),
		ALL_RINGS_IDENTIFIED( "badge_all_rings", 18 ),
		ALL_WANDS_IDENTIFIED( "badge_all_wands", 19 ),
		ALL_ITEMS_IDENTIFIED( "badge_all_items", 35, true ),
		BAG_BOUGHT_SEED_POUCH,
		BAG_BOUGHT_SCROLL_HOLDER,
		BAG_BOUGHT_WAND_HOLSTER,
		ALL_BAGS_BOUGHT( "badge_all_bags", 23 ),
		DEATH_FROM_FIRE( "badge_death_fire", 24 ),
		DEATH_FROM_POISON( "badge_death_poison", 25 ),
		DEATH_FROM_GAS( "badge_death_toxic_gas", 26 ),
		DEATH_FROM_HUNGER( "badge_death_hunger", 27 ),
		DEATH_FROM_GLYPH( "badge_death_glyph", 57 ),
		DEATH_FROM_FALLING( "badge_death_falling", 59 ),
		YASD( "badge_death_yasd", 34, true ),
		BOSS_SLAIN_1_WARRIOR,
		BOSS_SLAIN_1_MAGE,
		BOSS_SLAIN_1_ROGUE,
		BOSS_SLAIN_1_HUNTRESS,
		BOSS_SLAIN_1( "badge_boss_1", 12, 5 ),
		BOSS_SLAIN_2( "badge_boss_2", 13, 10 ),
		BOSS_SLAIN_3( "badge_boss_3", 14, 15 ),
		BOSS_SLAIN_4( "badge_boss_4", 15, 20 ),
		BOSS_SLAIN_1_ALL_CLASSES( "badge_boss_1_all", 32, true ),
		BOSS_SLAIN_3_GLADIATOR,
		BOSS_SLAIN_3_BERSERKER,
		BOSS_SLAIN_3_WARLOCK,
		BOSS_SLAIN_3_BATTLEMAGE,
		BOSS_SLAIN_3_FREERUNNER,
		BOSS_SLAIN_3_ASSASSIN,
		BOSS_SLAIN_3_SNIPER,
		BOSS_SLAIN_3_WARDEN,
		BOSS_SLAIN_3_ALL_SUBCLASSES("badge_boss_3_all", 33, true ),
		RING_OF_HAGGLER( "badge_ring_haggler", 20 ),
		RING_OF_THORNS( "badge_ring_thorns", 21 ),
		STRENGTH_ATTAINED_1( "badge_strength_attained", 40, 13 ),
		STRENGTH_ATTAINED_2( "badge_strength_attained", 41, 15 ),
		STRENGTH_ATTAINED_3( "badge_strength_attained", 42, 17 ),
		STRENGTH_ATTAINED_4( "badge_strength_attained", 43, 19 ),
		FOOD_EATEN_1( "badge_food_eaten", 44, 10 ),
		FOOD_EATEN_2( "badge_food_eaten", 45, 20 ),
		FOOD_EATEN_3( "badge_food_eaten", 46, 30 ),
		FOOD_EATEN_4( "badge_food_eaten", 47, 40 ),
		MASTERY_WARRIOR,
		MASTERY_MAGE,
		MASTERY_ROGUE,
		MASTERY_HUNTRESS,
		ITEM_LEVEL_1( "badge_item_level", 48, 3 ),
		ITEM_LEVEL_2( "badge_item_level", 49, 6 ),
		ITEM_LEVEL_3( "badge_item_level", 50, 9 ),
		ITEM_LEVEL_4( "badge_item_level", 51, 12 ),
		RARE_ALBINO,
		RARE_BANDIT,
		RARE_SHIELDED,
		RARE_SENIOR,
		RARE_ACIDIC,
		RARE( "badge_all_rare", 37, true ),
		VICTORY_WARRIOR,
		VICTORY_MAGE,
		VICTORY_ROGUE,
		VICTORY_HUNTRESS,
		VICTORY( "badge_victory", 22 ),
		VICTORY_ALL_CLASSES( "badge_victory_all", 36, true ),
		MASTERY_COMBO( "badge_combo_mastery", 56, 7 ),
		POTIONS_COOKED_1( "badge_potions_cooked", 52, 3 ),
		POTIONS_COOKED_2( "badge_potions_cooked", 53, 6 ),
		POTIONS_COOKED_3( "badge_potions_cooked", 54, 9 ),
		POTIONS_COOKED_4( "badge_potions_cooked", 55, 12 ),
		NO_MONSTERS_SLAIN( "badge_no_monsters_slain", 28 ),
		GRIM_WEAPON( "badge_grim_weapon", 29 ),
		PIRANHAS( "badge_piranhas", 30, 6 ),
		NIGHT_HUNTER( "badge_night_hunter", 58, 15 ),
		GAMES_PLAYED_1( "badge_games_played", 60, 10, true ),
		GAMES_PLAYED_2( "badge_games_played", 61, 100, true ),
		GAMES_PLAYED_3( "badge_games_played", 62, 500, true ),
		GAMES_PLAYED_4( "badge_games_played", 63, 2000, true ),
		HAPPY_END( "badge_happy_end", 38 ),
		SUPPORTER( "badge_supporter", 31, true );

		public final boolean meta;
		
		private final String description;
		public final int image;
		/** Used to calculate against some attribute to award this badge, and for I18N purposes **/
		public final int amount;
		
		private Badge( String description, int image ) {
			this(description, image, -1);
		}

		private Badge( String description, int image, int amount ) {
			this(description, image, amount, false);
		}
		
		private Badge( String description, int image, boolean meta ) {
			this(description, image, -1, meta);
		}

		private Badge( String description, int image, int amount, boolean meta ) {
			this.description = description;
			this.image = image;
			this.amount = amount;
			this.meta = meta;
		}
		
		private Badge() {
			this( "", -1, -1 );
		}

		public String getDescription() {
			if (description.length() == 0) {
				return description;
			}
			final I18NBundle i18nBundle = Game.instance.getI18nBundle();
			return amount >= 0 ? i18nBundle.format(description, amount) : i18nBundle.get(description);
		}
	}
	
	private static final Badge[] MONSTERS_SLAIN = {Badge.MONSTERS_SLAIN_1, Badge.MONSTERS_SLAIN_2, Badge.MONSTERS_SLAIN_3, Badge.MONSTERS_SLAIN_4};
	private static final Badge[] GOLD_COLLECTED = {Badge.GOLD_COLLECTED_1, Badge.GOLD_COLLECTED_2, Badge.GOLD_COLLECTED_3, Badge.GOLD_COLLECTED_4};
	private static final Badge[] LEVEL_REACHED = {Badge.LEVEL_REACHED_1, Badge.LEVEL_REACHED_2, Badge.LEVEL_REACHED_3, Badge.LEVEL_REACHED_4};
	private static final Badge[] STRENGTH_ATTAINED = {Badge.STRENGTH_ATTAINED_1, Badge.STRENGTH_ATTAINED_2, Badge.STRENGTH_ATTAINED_3, Badge.STRENGTH_ATTAINED_4};
	private static final Badge[] FOOD_EATEN = {Badge.FOOD_EATEN_1, Badge.FOOD_EATEN_2, Badge.FOOD_EATEN_3, Badge.FOOD_EATEN_4};
	private static final Badge[] POTIONS_COOKED = {Badge.POTIONS_COOKED_1, Badge.POTIONS_COOKED_2, Badge.POTIONS_COOKED_3, Badge.POTIONS_COOKED_4};
	private static final Badge[] PIRANHAS_KILLED = {Badge.PIRANHAS};
	private static final Badge[] ITEM_LEVEL = {Badge.ITEM_LEVEL_1, Badge.ITEM_LEVEL_2, Badge.ITEM_LEVEL_3, Badge.ITEM_LEVEL_4};
	private static final Badge[] BOSS_SLAIN = {Badge.BOSS_SLAIN_1, Badge.BOSS_SLAIN_2, Badge.BOSS_SLAIN_3, Badge.BOSS_SLAIN_4};
	private static final Badge[] COMBOS = {Badge.MASTERY_COMBO};
	private static final Badge[] NIGHT_HUNT = {Badge.NIGHT_HUNTER};
	private static final Badge[] GAMES_PLAYED = {Badge.GAMES_PLAYED_1, Badge.GAMES_PLAYED_2, Badge.GAMES_PLAYED_3, Badge.GAMES_PLAYED_4};

	private static HashSet<Badge> global;
	private static HashSet<Badge> local = new HashSet<Badges.Badge>();
	
	private static boolean saveNeeded = false;
	
	public static void reset() {
		local.clear();
		loadGlobal();
	}
	
	private static final String BADGES_FILE	= "badges.dat";
	private static final String BADGES		= "badges";
	
	private static HashSet<Badge> restore( Bundle bundle ) {
		HashSet<Badge> badges = new HashSet<Badge>();
		
		String[] names = bundle.getStringArray( BADGES );
		if (names == null) {
			return badges;
		}
		for (int i=0; i < names.length; i++) {
			try {
				badges.add( Badge.valueOf( names[i] ) );
			} catch (Exception e) {
			}
		}
	
		return badges;
	}
	
	private static void store( Bundle bundle, HashSet<Badge> badges ) {
		int count = 0;
		String names[] = new String[global.size()];
		
		for (Badge badge:badges) {
			names[count++] = badge.toString();
		}
		bundle.put( BADGES, names );
	}
	
	public static void loadLocal( Bundle bundle ) {
		local = restore( bundle );
	}
	
	public static void saveLocal( Bundle bundle ) {
		store( bundle, local );
	}
	
	public static void loadGlobal() {
		if (global == null) {
			try {
				InputStream input = Game.instance.openFileInput( BADGES_FILE );
				Bundle bundle = Bundle.read( input );
				input.close();
				
				global = restore( bundle );
				
			} catch (IOException e) {
				global = new HashSet<Badge>();
			}
		}
	}
	
	public static void saveGlobal() {
		if (saveNeeded) {
			
			Bundle bundle = new Bundle();
			store( bundle, global );
			
			try {
				OutputStream output = Game.instance.openFileOutput( BADGES_FILE );
				Bundle.write( bundle, output );
				output.close();
				saveNeeded = false;
			} catch (IOException e) {
				
			}
		}
	}
	
	private static void validateAndShowBadges(Badge[] badges, int attr) {
		Badge lastBadge = null;
		for (Badge badge : badges) {
			if (!local.contains(badge) && attr >= badge.amount) {
				lastBadge = badge;
				local.add( badge );
			}
		}
		displayBadge(lastBadge);
	}

	public static void validateMonstersSlain() {
		validateAndShowBadges(MONSTERS_SLAIN, Statistics.enemiesSlain);
	}
	
	public static void validateGoldCollected() {
		validateAndShowBadges(GOLD_COLLECTED, Statistics.goldCollected);
	}
	
	public static void validateLevelReached() {
		validateAndShowBadges(LEVEL_REACHED, Dungeon.hero.lvl);
	}
	
	public static void validateStrengthAttained() {
		validateAndShowBadges(STRENGTH_ATTAINED, Dungeon.hero.STR);
	}
	
	public static void validateFoodEaten() {
		validateAndShowBadges(FOOD_EATEN, Statistics.foodEaten);
	}
	
	public static void validatePotionsCooked() {
		validateAndShowBadges(POTIONS_COOKED, Statistics.potionsCooked);
	}
	
	public static void validatePiranhasKilled() {
		validateAndShowBadges(PIRANHAS_KILLED, Statistics.piranhasKilled);
	}
	
	public static void validateItemLevelAquired( Item item ) {
		
		// This method should be called:
		// 1) When an item is obtained (Item.collect)
		// 2) When an item is upgraded (ScrollOfUpgrade, ScrollOfWeaponUpgrade, ShortSword, WandOfMagicMissile)
		// 3) When an item is identified
		if (!item.levelKnown) {
			return;
		}

		validateAndShowBadges(ITEM_LEVEL, item.level);
	}

	private static void validateAllIdentified(Badge badge, boolean allKnown) {
		if (Dungeon.hero != null && Dungeon.hero.isAlive() &&
				!local.contains(badge) && allKnown) {

			local.add(badge);
			displayBadge(badge);

			validateAllItemsIdentified();
		}
	}
	
	public static void validateAllPotionsIdentified() {
		validateAllIdentified(Badge.ALL_POTIONS_IDENTIFIED, Potion.allKnown());
	}
	
	public static void validateAllScrollsIdentified() {
		validateAllIdentified(Badge.ALL_SCROLLS_IDENTIFIED, Scroll.allKnown());
	}
	
	public static void validateAllRingsIdentified() {
		validateAllIdentified(Badge.ALL_RINGS_IDENTIFIED, Ring.allKnown());
	}
	
	public static void validateAllWandsIdentified() {
		validateAllIdentified(Badge.ALL_WANDS_IDENTIFIED, Wand.allKnown());
	}
	
	public static void validateAllBagsBought( Item bag ) {
		
		Badge badge = null;
		if (bag instanceof SeedPouch) {
			badge = Badge.BAG_BOUGHT_SEED_POUCH;
		} else if (bag instanceof ScrollHolder) {
			badge = Badge.BAG_BOUGHT_SCROLL_HOLDER;
		} else if (bag instanceof WandHolster) {
			badge = Badge.BAG_BOUGHT_WAND_HOLSTER;
		}
		
		if (badge != null) {
			
			local.add( badge );
			
			if (!local.contains( Badge.ALL_BAGS_BOUGHT ) &&
				local.contains( Badge.BAG_BOUGHT_SCROLL_HOLDER ) &&
				local.contains( Badge.BAG_BOUGHT_SEED_POUCH ) &&
				local.contains( Badge.BAG_BOUGHT_WAND_HOLSTER )) {
						
					badge = Badge.ALL_BAGS_BOUGHT;
					local.add( badge );
					displayBadge( badge );
			}
		}
	}
	
	public static void validateAllItemsIdentified() {
		if (!global.contains( Badge.ALL_ITEMS_IDENTIFIED ) &&
			global.contains( Badge.ALL_POTIONS_IDENTIFIED ) &&
			global.contains( Badge.ALL_SCROLLS_IDENTIFIED ) &&
			global.contains( Badge.ALL_RINGS_IDENTIFIED ) &&
			global.contains( Badge.ALL_WANDS_IDENTIFIED )) {
			
			Badge badge = Badge.ALL_ITEMS_IDENTIFIED;
			displayBadge( badge );
		}
	}

	private static void validateDeath(final Badge badge, boolean doValidateYASD) {
		local.add(badge);
		displayBadge(badge);

		if (doValidateYASD) {
			validateYASD();
		}
	}

	public static void validateDeathFromFire() {
		validateDeath(Badge.DEATH_FROM_FIRE, true);
	}

	public static void validateDeathFromPoison() {
		validateDeath(Badge.DEATH_FROM_POISON, true);
	}
	
	public static void validateDeathFromGas() {
		validateDeath(Badge.DEATH_FROM_GAS, true);
	}
	
	public static void validateDeathFromHunger() {
		validateDeath(Badge.DEATH_FROM_HUNGER, true);
	}
	
	public static void validateDeathFromGlyph() {
		validateDeath(Badge.DEATH_FROM_GLYPH, false);
	}
	
	public static void validateDeathFromFalling() {
		validateDeath(Badge.DEATH_FROM_FALLING, false);
	}
	
	private static void validateYASD() {
		if (global.contains( Badge.DEATH_FROM_FIRE ) &&
			global.contains( Badge.DEATH_FROM_POISON ) &&
			global.contains( Badge.DEATH_FROM_GAS ) &&
			global.contains( Badge.DEATH_FROM_HUNGER)) {
			
			Badge badge = Badge.YASD;
			local.add( badge );
			displayBadge( badge );
		}
	}
	
	public static void validateBossSlain() {
		Badge badge = null;
		for (Badge bossBadge : BOSS_SLAIN) {
			if (bossBadge.amount == Dungeon.depth) {
				badge = bossBadge;
				break;
			}
		}
		
		if (badge != null) {
			local.add( badge );
			displayBadge( badge );
			
			if (badge == Badge.BOSS_SLAIN_1) {
				switch (Dungeon.hero.heroClass) {
				case WARRIOR:
					badge = Badge.BOSS_SLAIN_1_WARRIOR;
					break;
				case MAGE:
					badge = Badge.BOSS_SLAIN_1_MAGE;
					break;
				case ROGUE:
					badge = Badge.BOSS_SLAIN_1_ROGUE;
					break;
				case HUNTRESS:
					badge = Badge.BOSS_SLAIN_1_HUNTRESS;
					break;
				}
				local.add( badge );
				if (!global.contains( badge )) {
					global.add( badge );
					saveNeeded = true;
				}
				
				if (global.contains( Badge.BOSS_SLAIN_1_WARRIOR ) &&
					global.contains( Badge.BOSS_SLAIN_1_MAGE ) &&
					global.contains( Badge.BOSS_SLAIN_1_ROGUE ) &&
					global.contains( Badge.BOSS_SLAIN_1_HUNTRESS)) {
					
					badge = Badge.BOSS_SLAIN_1_ALL_CLASSES;
					if (!global.contains( badge )) {
						displayBadge( badge );
						global.add( badge );
						saveNeeded = true;
					}
				}
			} else
			if (badge == Badge.BOSS_SLAIN_3) {
				switch (Dungeon.hero.subClass) {
				case GLADIATOR:
					badge = Badge.BOSS_SLAIN_3_GLADIATOR;
					break;
				case BERSERKER:
					badge = Badge.BOSS_SLAIN_3_BERSERKER;
					break;
				case WARLOCK:
					badge = Badge.BOSS_SLAIN_3_WARLOCK;
					break;
				case BATTLEMAGE:
					badge = Badge.BOSS_SLAIN_3_BATTLEMAGE;
					break;
				case FREERUNNER:
					badge = Badge.BOSS_SLAIN_3_FREERUNNER;
					break;
				case ASSASSIN:
					badge = Badge.BOSS_SLAIN_3_ASSASSIN;
					break;
				case SNIPER:
					badge = Badge.BOSS_SLAIN_3_SNIPER;
					break;
				case WARDEN:
					badge = Badge.BOSS_SLAIN_3_WARDEN;
					break;
				default:
					return;
				}
				local.add( badge );
				if (!global.contains( badge )) {
					global.add( badge );
					saveNeeded = true;
				}
				
				if (global.contains( Badge.BOSS_SLAIN_3_GLADIATOR ) &&
					global.contains( Badge.BOSS_SLAIN_3_BERSERKER ) &&
					global.contains( Badge.BOSS_SLAIN_3_WARLOCK ) &&
					global.contains( Badge.BOSS_SLAIN_3_BATTLEMAGE ) &&
					global.contains( Badge.BOSS_SLAIN_3_FREERUNNER ) &&
					global.contains( Badge.BOSS_SLAIN_3_ASSASSIN ) &&
					global.contains( Badge.BOSS_SLAIN_3_SNIPER ) &&
					global.contains( Badge.BOSS_SLAIN_3_WARDEN )) {
					
					badge = Badge.BOSS_SLAIN_3_ALL_SUBCLASSES;
					if (!global.contains( badge )) {
						displayBadge( badge );
						global.add( badge );
						saveNeeded = true;
					}
				}
			}
		}
	}
	
	public static void validateMastery() {
		
		Badge badge = null;
		switch (Dungeon.hero.heroClass) {
		case WARRIOR:
			badge = Badge.MASTERY_WARRIOR;
			break;
		case MAGE:
			badge = Badge.MASTERY_MAGE;
			break;
		case ROGUE:
			badge = Badge.MASTERY_ROGUE;
			break;
		case HUNTRESS:
			badge = Badge.MASTERY_HUNTRESS;
			break;
		}
		
		if (!global.contains( badge )) {
			global.add( badge );
			saveNeeded = true;
		}
	}
	
	public static void validateMasteryCombo( int n ) {
		validateAndShowBadges(COMBOS, n);
	}
	
	public static void validateRingOfHaggler() {
		if (!local.contains( Badge.RING_OF_HAGGLER ) && new RingOfHaggler().isKnown()) {
			Badge badge = Badge.RING_OF_HAGGLER;
			local.add( badge );
			displayBadge( badge );
		}
	}
	
	public static void validateRingOfThorns() {
		if (!local.contains( Badge.RING_OF_THORNS ) && new RingOfThorns().isKnown()) {
			Badge badge = Badge.RING_OF_THORNS;
			local.add( badge );
			displayBadge( badge );
		}
	}
	
	public static void validateRare( Mob mob ) {
		
		Badge badge = null;
		if (mob instanceof Albino) {
			badge = Badge.RARE_ALBINO;
		} else if (mob instanceof Bandit) {
			badge = Badge.RARE_BANDIT;
		} else if (mob instanceof Shielded) {
			badge = Badge.RARE_SHIELDED;
		} else if (mob instanceof Senior) {
			badge = Badge.RARE_SENIOR;
		} else if (mob instanceof Acidic) {
			badge = Badge.RARE_ACIDIC;
		}
		if (!global.contains( badge )) {
			global.add( badge );
			saveNeeded = true;
		}
		
		if (global.contains( Badge.RARE_ALBINO ) &&
			global.contains( Badge.RARE_BANDIT ) &&
			global.contains( Badge.RARE_SHIELDED ) &&
			global.contains( Badge.RARE_SENIOR ) &&
			global.contains( Badge.RARE_ACIDIC )) {
			
			badge = Badge.RARE;
			displayBadge( badge );
		}
	}
	
	public static void validateVictory() {

		Badge badge = Badge.VICTORY;
		displayBadge( badge );

		switch (Dungeon.hero.heroClass) {
		case WARRIOR:
			badge = Badge.VICTORY_WARRIOR;
			break;
		case MAGE:
			badge = Badge.VICTORY_MAGE;
			break;
		case ROGUE:
			badge = Badge.VICTORY_ROGUE;
			break;
		case HUNTRESS:
			badge = Badge.VICTORY_HUNTRESS;
			break;
		}
		local.add( badge );
		if (!global.contains( badge )) {
			global.add( badge );
			saveNeeded = true;
		}
		
		if (global.contains( Badge.VICTORY_WARRIOR ) &&
			global.contains( Badge.VICTORY_MAGE ) &&
			global.contains( Badge.VICTORY_ROGUE ) &&
			global.contains( Badge.VICTORY_HUNTRESS )) {
			
			badge = Badge.VICTORY_ALL_CLASSES;
			displayBadge( badge );
		}
	}
	
	public static void validateNoKilling() {
		if (!local.contains( Badge.NO_MONSTERS_SLAIN ) && Statistics.completedWithNoKilling) {
			Badge badge = Badge.NO_MONSTERS_SLAIN;
			local.add( badge );
			displayBadge( badge );
		}
	}
	
	public static void validateGrimWeapon() {
		if (!local.contains( Badge.GRIM_WEAPON )) {
			Badge badge = Badge.GRIM_WEAPON;
			local.add( badge );
			displayBadge( badge );
		}
	}
	
	public static void validateNightHunter() {
		validateAndShowBadges(NIGHT_HUNT, Statistics.nightHunt);
	}
	
	public static void validateSupporter() {

		global.add( Badge.SUPPORTER );
		saveNeeded = true;
		
		PixelScene.showBadge( Badge.SUPPORTER );
	}

	public static void validateGamesPlayed() {
		// FIXME: This is now adding the badge to `local`. Is that ok?
		validateAndShowBadges(GAMES_PLAYED, Rankings.INSTANCE.totalNumber);
	}
	
	public static void validateHappyEnd() {
		displayBadge( Badge.HAPPY_END );
	}
	
	private static void displayBadge( Badge badge ) {
		
		if (badge == null) {
			return;
		}
		
		if (global.contains( badge )) {
			
			if (!badge.meta) {
				GLog.h( "Badge endorsed: %s", badge.getDescription() );
			}
			
		} else {
			
			global.add( badge );
			saveNeeded = true;
			
			if (badge.meta) {
				GLog.h( "New super badge: %s", badge.getDescription() );
			} else {
				GLog.h( "New badge: %s", badge.getDescription() );
			}	
			PixelScene.showBadge( badge );
		}
	}
	
	public static boolean isUnlocked( Badge badge ) {
		return global.contains( badge );
	}
	
	public static void disown( Badge badge ) {
		loadGlobal();
		global.remove( badge );
		saveNeeded = true;
	}
	
	public static List<Badge> filtered( boolean global ) {
		
		HashSet<Badge> filtered = new HashSet<Badge>( global ? Badges.global : Badges.local );
		
		if (!global) {
			Iterator<Badge> iterator = filtered.iterator();
			while (iterator.hasNext()) {
				Badge badge = iterator.next();
				if (badge.meta) {
					iterator.remove();
				}
			}
		}
		
		leaveBest( filtered, MONSTERS_SLAIN );
		leaveBest( filtered, GOLD_COLLECTED );
		leaveBest( filtered, BOSS_SLAIN );
		leaveBest( filtered, LEVEL_REACHED );
		leaveBest( filtered, STRENGTH_ATTAINED );
		leaveBest( filtered, FOOD_EATEN );
		leaveBest( filtered, ITEM_LEVEL );
		leaveBest( filtered, POTIONS_COOKED );
		leaveBest( filtered, Badge.BOSS_SLAIN_1_ALL_CLASSES, Badge.BOSS_SLAIN_3_ALL_SUBCLASSES );
		leaveBest( filtered, Badge.DEATH_FROM_FIRE, Badge.YASD );
		leaveBest( filtered, Badge.DEATH_FROM_GAS, Badge.YASD );
		leaveBest( filtered, Badge.DEATH_FROM_HUNGER, Badge.YASD );
		leaveBest( filtered, Badge.DEATH_FROM_POISON, Badge.YASD );
		leaveBest( filtered, Badge.VICTORY, Badge.VICTORY_ALL_CLASSES );
		leaveBest( filtered, GAMES_PLAYED );
		
		ArrayList<Badge> list = new ArrayList<Badge>( filtered );
		Collections.sort( list );
		
		return list;
	}
	
	private static void leaveBest( HashSet<Badge> list, Badge...badges ) {
		for (int i=badges.length-1; i > 0; i--) {
			if (list.contains( badges[i])) {
				for (int j=0; j < i; j++) {
					list.remove( badges[j] );
				}
				break;
			}
		}
	}
}
