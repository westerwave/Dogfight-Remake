package dogfight_remake.entities;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import dogfight_remake.main.GlbVar;

public abstract class Entity {

	protected float xpos;
	protected float ypos;
	protected float speed;

	private float angle;
	private int hitpoints;

	public Entity(float xpos, float ypos) {
		this.xpos = xpos;
		this.ypos = ypos;
	}

	public Entity(float xpos, float ypos, float angle) {
		this.setAngle(angle);
		this.xpos = xpos;
		this.ypos = ypos;
	}

	public abstract void update(float delta);

	public abstract void render(GameContainer container, Graphics g, float delta);

	public void setPosition(float xpos, float ypos) {
		this.xpos = xpos;
		this.ypos = ypos;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public float getXpos() {
		return xpos;
	}

	public float getYpos() {
		return ypos;
	}

	public void setXpos(float xpos) {
		this.xpos = xpos;
	}

	public void setYpos(float ypos) {
		this.ypos = ypos;
	}

	public float getSpeed() {
		return speed;
	}

	public void incSpeed(float speed) {
		if (this.speed + speed > GlbVar.PLANES_MAX_SPEED) {
			this.speed = GlbVar.PLANES_MAX_SPEED;
		} else {
			this.speed += speed;
		}
	}

	public void decSpeed(float speed, boolean base) {
		if (base && this.speed < 2) {
			this.speed = 2;
		} else if (!base && this.speed < 0.5) {
			this.speed = (float) 0.5;
		} else {
			this.speed -= speed;
		}
	}

	public int getHitpoints() {
		return hitpoints;
	}

	public void setHitpoints(int hitpoints) {
		this.hitpoints = hitpoints;
	}

	public float getAngle() {
		return angle;
	}

	public void setAngle(float angle) {
		this.angle = angle;
	}
}
