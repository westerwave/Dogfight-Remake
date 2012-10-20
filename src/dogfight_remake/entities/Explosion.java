package dogfight_remake.entities;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Ellipse;

public class Explosion extends Entity {

	public final static int MAX_RADIUS = 15;
	private float xpos, ypos, size;
	private Ellipse explosion1, explosion2;
	private float radius = 0;
	private float radius_small = 0;
	private boolean broken = false;

	public Explosion(float xpos, float ypos, float size) {
		super(xpos, ypos);
		this.xpos = xpos;
		this.ypos = ypos;
		this.size = size;
	}

	public void render(GameContainer container, Graphics g, float delta) {
		if (broken) {
			return;
		}
		explosion1 = new Ellipse(xpos - radius / 2, ypos - radius / 2, radius,
				radius);
		g.setColor(Color.red);
		g.fill(explosion1);
		if (size > 1) {
			radius_small = radius / 2;
			explosion2 = new Ellipse(xpos - radius_small / 2, ypos
					- radius_small / 2, radius_small, radius_small);
			g.setColor(Color.yellow);
			g.fill(explosion2);
		}
		isMaxRadius();
	}

	public void update(float delta) {
		if (radius < (MAX_RADIUS * size))
			radius += 2;
	}

	public void isMaxRadius() {
		if (radius >= MAX_RADIUS * size) {
			broken = true;
		} else {
			broken = false;
		}
	}

	public int getMaxRadius() {
		return MAX_RADIUS;
	}

	public boolean isBroken() {
		return broken;
	}
}
