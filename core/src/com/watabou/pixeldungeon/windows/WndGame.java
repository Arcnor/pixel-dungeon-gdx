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
package com.watabou.pixeldungeon.windows;

import java.io.IOException;

import com.watabou.noosa.Game;
import com.watabou.pixeldungeon.Dungeon;
import com.watabou.pixeldungeon.scenes.GameScene;
import com.watabou.pixeldungeon.scenes.InterlevelScene;
import com.watabou.pixeldungeon.scenes.RankingsScene;
import com.watabou.pixeldungeon.scenes.TitleScene;
import com.watabou.pixeldungeon.ui.Icons;
import com.watabou.pixeldungeon.ui.RedButton;
import com.watabou.pixeldungeon.ui.Window;

import static com.watabou.noosa.NoosaI18N.tr;

public class WndGame extends Window {
	
	private static final String TXT_SETTINGS	= "wgame_settings";
	private static final String TXT_RANKINGS	= "wgame_rankings";
	private static final String TXT_START		= "wgame_start";
	private static final String TXT_MENU		= "wgame_menu";
	private static final String TXT_EXIT		= "wgame_exit";
	private static final String TXT_RETURN		= "wgame_return";
	
	private static final int WIDTH		= 120;
	private static final int BTN_HEIGHT	= 20;
	private static final int GAP		= 2;
	
	private int pos;
	
	public WndGame() {
		
		super();
		
		addButton( new RedButton( tr(TXT_SETTINGS) ) {
			@Override
			protected void onClick() {
				hide();
				GameScene.show( new WndSettings( true ) );
			}
		} );
		
		// Restart
		if (!Dungeon.hero.isAlive()) {
			
			RedButton btnStart;
			addButton( btnStart = new RedButton( tr(TXT_START) ) {
				@Override
				protected void onClick() {
					Dungeon.hero = null;
					InterlevelScene.mode = InterlevelScene.Mode.DESCEND;
					InterlevelScene.noStory = true;
					Game.switchScene( InterlevelScene.class );
				}
			} );
			btnStart.icon( Icons.get( Dungeon.hero.heroClass ) );
			
			addButton( new RedButton( tr(TXT_RANKINGS) ) {
				@Override
				protected void onClick() {
					InterlevelScene.mode = InterlevelScene.Mode.DESCEND;
					Game.switchScene( RankingsScene.class );
				}
			} );
		}
				
		// Main menu
		addButton( new RedButton( tr(TXT_MENU) ) {
			@Override
			protected void onClick() {
				try {
					Dungeon.saveAll();
				} catch (IOException e) {
					//
				}
				Game.switchScene( TitleScene.class );
			}
		} );
		
		// Exit
		addButton( new RedButton( tr(TXT_EXIT) ) {
			@Override
			protected void onClick() {
				Game.instance.finish();
			}
		} );
		
		// Cancel
		addButton( new RedButton( tr(TXT_RETURN) ) {
			@Override
			protected void onClick() {
				hide();
			}
		} );
		
		resize( WIDTH, pos );
	}
	
	private void addButton( RedButton btn ) {
		add( btn );
		btn.setRect( 0, pos > 0 ? pos += GAP : 0, WIDTH, BTN_HEIGHT );
		pos += BTN_HEIGHT;
	}
}
