package com.geeselightning.zepr;

import com.badlogic.gdx.Game;

public class Zepr extends Game {

	private LoadingScreen loadingScreen;
	private MenuScreen menuScreen;
	private EndScreen endScreen;
	private Level level;
	private SelectLevelScreen selectLevelScreen;

	// The progress is the integer representing the last level completed. i.e. 3 for Town
	public int progress = 3;

	public final static int MENU = 0;
	public final static int SELECT = 2;
	public final static int TOWN = 3;
	public final static int HALIFAX = 4;
	public final static int COURTYARD = 5;
	public final static int COMPLETE = 9;

	//TEAM CRAIG: ADDED
	public final static int CSBUILDING = 6;
	public final static int GREGSPLACE = 7;
	public final static int LIBRARY = 8;
	public final static int MINIGAME = 10;



	public void changeScreen(int screen) {
		switch(screen) {
			case MENU:
				if (menuScreen == null) menuScreen = new MenuScreen(this);
				this.setScreen(menuScreen);
				break;
			case SELECT:
				selectLevelScreen = new SelectLevelScreen(this);
				this.setScreen(selectLevelScreen);
				break;
			case TOWN:
				level = new TownLevel(this);
				this.setScreen(level);
				break;
			case HALIFAX:
				level = new HalifaxLevel(this);
				this.setScreen(level);
				break;
			case COURTYARD:
				level = new CourtyardLevel(this);
				this.setScreen(level);
				break;
			case CSBUILDING: //TEAM CRAIG: ADDED
				level = new CSBuildingLevel(this);
				this.setScreen(level);
				break;
			case GREGSPLACE: //TEAM CRAIG: ADDED
				level = new GregsPlaceLevel(this);
				this.setScreen(level);
				break;
			case LIBRARY: //TEAM CRAIG: ADDED
				level = new LibraryLevel(this);
				this.setScreen(level);
				break;
			case COMPLETE:
				if (endScreen ==  null) endScreen = new EndScreen(this);
				this.setScreen(endScreen);
				break;
			case MINIGAME: //TEAM CRAIG: ADDED
				level = new MiniGameLevel(this);
				this.setScreen(level);
		}
	}

	@Override
	public void create() {
		loadingScreen = new LoadingScreen(this);
		setScreen(loadingScreen);
	}
}