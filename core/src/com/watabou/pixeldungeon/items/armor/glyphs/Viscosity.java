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
package com.watabou.pixeldungeon.items.armor.glyphs;

import com.watabou.pixeldungeon.Badges;
import com.watabou.pixeldungeon.Dungeon;
import com.watabou.pixeldungeon.ResultDescriptions;
import com.watabou.pixeldungeon.actors.Char;
import com.watabou.pixeldungeon.actors.buffs.Buff;
import com.watabou.pixeldungeon.items.armor.Armor;
import com.watabou.pixeldungeon.items.armor.Armor.Glyph;
import com.watabou.pixeldungeon.sprites.CharSprite;
import com.watabou.pixeldungeon.sprites.ItemSprite;
import com.watabou.pixeldungeon.sprites.ItemSprite.Glowing;
import com.watabou.pixeldungeon.ui.BuffIndicator;
import com.watabou.pixeldungeon.utils.GLog;
import com.watabou.utils.Bundle;
import com.watabou.utils.Random;

import static com.watabou.noosa.NoosaI18N.tr;

public class Viscosity extends Glyph {

	private static final String TXT_VISCOSITY	= "glyph_viscosity";
	private static final String TXT_DEFERRED = "glyph_viscosity_deferred";
	private static final String TXT_DEFERRED_DAMAGE = "glyph_viscosity_deferred_damage";

	private static ItemSprite.Glowing PURPLE = new ItemSprite.Glowing( 0x8844CC );
	
	@Override
	public int proc(Armor armor, Char attacker, Char defender, int damage ) {

		if (damage == 0) {
			return 0;
		}
		
		int level = Math.max( 0, armor.level );
		
		if (Random.Int( level + 7 ) >= 6) {
			
			DeferedDamage debuff = defender.buff( DeferedDamage.class );
			if (debuff == null) {
				debuff = new DeferedDamage();
				debuff.attachTo( defender );
			}
			debuff.prolong( damage );
			
			defender.sprite.showStatus( CharSprite.WARNING, tr(TXT_DEFERRED, damage) );
			
			return 0;
			
		} else {
			return damage;
		}
	}
	
	@Override
	public String name(String weaponName) {
		return tr(TXT_VISCOSITY, weaponName);
	}

	@Override
	public Glowing glowing() {
		return PURPLE;
	}
	
	public static class DeferedDamage extends Buff {
		
		protected int damage = 0;
		
		private static final String BUNDLE_KEY_DAMAGE = "damage";
		
		@Override
		public void storeInBundle( Bundle bundle ) {
			super.storeInBundle( bundle );
			bundle.put(BUNDLE_KEY_DAMAGE, damage);
			
		}
		
		@Override
		public void restoreFromBundle( Bundle bundle ) {
			super.restoreFromBundle( bundle );
			damage = bundle.getInt(BUNDLE_KEY_DAMAGE);
		}
		
		@Override
		public boolean attachTo( Char target ) {
			if (super.attachTo( target )) {
				postpone( TICK );
				return true;
			} else {
				return false;
			}
		}
		
		public void prolong( int damage ) {
			this.damage += damage;
		};
		
		@Override
		public int icon() {
			return BuffIndicator.DEFERRED;
		}
		
		@Override
		public String toString() {
			return tr(TXT_DEFERRED_DAMAGE, damage);
		}
		
		@Override
		public boolean act() {
			if (target.isAlive()) {
				
				target.damage( 1, this );
				if (target == Dungeon.hero && !target.isAlive()) {
					// Refactoring needed!
					Glyph glyph = new Viscosity();
					Dungeon.fail( tr(ResultDescriptions.GLYPH, glyph.name(), Dungeon.depth) );
					GLog.n( tr(Char.TXT_KILL, glyph.name()) );
					
					Badges.validateDeathFromGlyph();
				}
				spend( TICK );
				
				if (--damage <= 0) {
					detach();
				}
				
			} else {
				
				detach();
				
			}
			
			return true;
		}
	}
}
