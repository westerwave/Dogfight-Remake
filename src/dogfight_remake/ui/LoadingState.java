package dogfight_remake.ui;

import java.io.IOException;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMapPlus;
import org.newdawn.slick.loading.DeferredResource;
import org.newdawn.slick.loading.LoadingList;

import dogfight_remake.main.Dogfight_Remake;
import dogfight_remake.main.Var;

public class LoadingState extends BasicGameState {

    private DeferredResource nextResource;
    private boolean started;
    int stateID = -1;

    @Override
    public void init(GameContainer arg0, StateBasedGame arg1)
	    throws SlickException {
	LoadingList.setDeferredLoading(true);
	// Images Main Menu
	Var.background = new Image("dogfight_remake/images/menu/f4u.jpg");
	Var.menuOptions = new Image("dogfight_remake/images/menu/mainMenu.png");
	Var.gameOptionsMenu = new Image(
		"dogfight_remake/images/menu/GameOptionsMenu.png");
	Var.buttons = new Image("dogfight_remake/images/menu/buttons.png");
	Var.startGameOption = new Image(
		"dogfight_remake/images/menu/menu_start.png");
	Var.plane_p1 = new Image("dogfight_remake/images/menu/menu_player1.png");
	Var.plane_p2 = new Image("dogfight_remake/images/menu/menu_player2.png");
	// Images Plane Menu
	Var.arrows = new Image("dogfight_remake/images/menu/buttons2.png");

	// Images Game
	Var.img_player_ui = new Image(
		"dogfight_remake/images/game/player_ui.png");
	Var.img_player_ui_text = new Image(
		"dogfight_remake/images/game/player_ui_text.png");
	Var.img_timer = new Image("dogfight_remake/images/game/timer.png");
	Var.img_plane2 = new Image("dogfight_remake/images/planes/plane1.png",
		true);
	Var.img_bg = new Image("dogfight_remake/images/img_bg.jpg");
	Var.img_missile1 = new Image(
		"dogfight_remake/images/weapons/missile1.png");
	Var.aim9l = new Image(
		"dogfight_remake/images/weapons/aim9l.png");
	Var.img_bullet1 = new Image(
		"dogfight_remake/images/weapons/bullet1.png");
	Var.img_bomb1 = new Image("dogfight_remake/images/weapons/bomb1.png");
	Var.img_bomb1_split = new Image(
		"dogfight_remake/images/weapons/bomb1_split.png");
	Var.img_turret1 = new Image("dogfight_remake/images/turret.png");
	// Images planes gen 5
	Var.f35 = new Image("dogfight_remake/images/planes/gen5/f35.png");
	Var.plane_collis_generic = new Image("dogfight_remake/images/planes/gen5/f35_col.png");
	Var.mig35 = new Image("dogfight_remake/images/planes/gen5/mig-35.png");
	// Images weapons primary
	Var.mm30s = new Image("dogfight_remake/images/weapons/30mms.png");
	// Tiled Map
	Var.tmap = new TiledMapPlus("dogfight_remake/map/map_test/map2.tmx",
		"dogfight_remake/map/map_test");

	// Images Pause Menu
	Var.pauseMenuText = new Image(
		"dogfight_remake/images/menu/pauseMenuText.png");
	Var.pauseMenu = new Image("dogfight_remake/images/menu/pauseMenu.png");
	// Sounds and Music

	// GlbVar.music1 = new
	// Music("dogfight_remake/sound/sounds/fight07.ogg");
	// GlbVar.music2 = new
	// Music("dogfight_remake/sound/sounds/fight08.ogg");

	Var.prim_gun_heavy = new Sound(
		"dogfight_remake/sound/sounds/prim_gun_heavy.wav");
	Var.prim_gun_middle = new Sound(
		"dogfight_remake/sound/sounds/prim_gun_middle.wav");
	Var.prim_gun_light = new Sound(
		"dogfight_remake/sound/sounds/prim_gun_light.wav");
	Var.sec_missile1 = new Sound(
		"dogfight_remake/sound/sounds/sec_missile1.wav");
	Var.sec_missile2 = new Sound(
		"dogfight_remake/sound/sounds/sec_missile2.wav");
	Var.sec_bomb_drop = new Sound(
		"dogfight_remake/sound/sounds/sec_bomb_drop.wav");
	Var.explode = new Sound("dogfight_remake/sound/sounds/explode.wav");
	Var.hit = new Sound("dogfight_remake/sound/sounds/hit.wav");
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sgg, Graphics g)
	    throws SlickException {
	if (nextResource != null) {
	    g.drawString("Loading: " + nextResource.getDescription(),
		    (float) Var.dim_chosen.getWidth() / 3,
		    (float) Var.dim_chosen.getHeight() / 5);
	}
	int total = LoadingList.get().getTotalResources();
	int loaded = LoadingList.get().getTotalResources()
		- LoadingList.get().getRemainingResources();
	g.fillRect((float) Var.dim_chosen.getWidth() * 0.25f,
		(float) Var.dim_chosen.getHeight() / 4, loaded * 40, 20);
	g.drawRect((float) Var.dim_chosen.getWidth() * 0.25f,
		(float) Var.dim_chosen.getHeight() / 4, total * 40, 20);
	if (started) {
	    g.drawString("LOADING COMPLETE", 100, 500);
	}
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta)
	    throws SlickException {
	if (nextResource != null) {
	    try {
		nextResource.load();
	    } catch (IOException e) {
		throw new SlickException("Failed to load: "
			+ nextResource.getDescription(), e);
	    }
	    nextResource = null;
	}

	if (LoadingList.get().getRemainingResources() > 0) {
	    nextResource = LoadingList.get().getNext();
	} else {
	    sbg.enterState(Dogfight_Remake.MAINMENUSTATE);
	}
    }

    @Override
    public int getID() {
	return stateID;
    }

    public LoadingState(int stateID) {
	this.stateID = stateID;
    }

}
