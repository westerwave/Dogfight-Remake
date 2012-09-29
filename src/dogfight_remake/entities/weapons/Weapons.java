package dogfight_remake.entities.weapons;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import dogfight_remake.entities.Entity;
import dogfight_remake.main.GamePlayState;

public class Weapons extends Entity {
	public final static int MAX_LIFETIME_GUN = 1000;
	public final static int MAX_LIFETIME_GUIDED_AIR = 35000;
	public final static int MAX_LIFETIME_UNGUIDED = 2000;
	public final static int MAX_LIFETIME_BOMB = 10000;
	private float angle;
	private boolean broken;
	private int height, width;
	private WeaponTypes type;
	private float speed_mod = 0;
	private float speed_mod_bomb = 0.3f;
	private int damage = 0;
	private boolean hasTarget = true;
	private int id;
	private Image image;
	private boolean split = false;
	private boolean isSplit = false;
	private float firstHeight;
	private int time;

	public Weapons(float xpos, float ypos, float angle, int damage,
			WeaponTypes type, int time, Image image, int id) {
		super(xpos, ypos);
		this.speed = type.getSpeed();
		this.angle = angle;
		this.image = image;
		this.broken = false;
		this.damage = damage;
		this.type = type;
		this.id = id;
		this.firstHeight = ypos;
		this.height = image.getHeight();
		this.width = image.getWidth();
		this.time = time;
	}

	/**
	 * Paints weapons
	 */
	public void render(GameContainer container, Graphics g, int delta) {
		time += delta;
		if (broken) {
			return;
		}
		if (type == WeaponTypes.GUN && time <= MAX_LIFETIME_GUN) {
			image.setRotation(angle);
			image.draw(xpos, ypos);
		} else if (type == WeaponTypes.MINIGUN && time <= MAX_LIFETIME_GUN) {
			image.setRotation(angle);
			image.draw(xpos, ypos);
		} else if (type == WeaponTypes.UNGUIDED
				&& time <= MAX_LIFETIME_UNGUIDED) {
			image.setRotation(angle);
			image.draw(xpos, ypos);
		} else if (type == WeaponTypes.GUIDED_GROUND) {
			image.setRotation(angle);
			image.draw(xpos, ypos);
		} else if (type == WeaponTypes.GUIDED_AIR
				&& time <= MAX_LIFETIME_GUIDED_AIR) {
			
			image.setRotation(angle);
			image.draw(xpos, ypos);
		} else if (type == WeaponTypes.RADAR_AIR) {
			image.setRotation(angle);
			image.draw(xpos, ypos);
		} else if (type == WeaponTypes.RADAR_GROUND) {
			image.setRotation(angle);
			image.draw(xpos, ypos);
		} else if (type == WeaponTypes.BOMB) {
			image.setRotation(angle);
			image.draw(xpos, ypos);
		} else if (type == WeaponTypes.BOMB_SPLIT) {
			image.setRotation(angle);
			image.draw(xpos, ypos);
		} else if (type == WeaponTypes.BOMB_SPLIT_SMALL) {
			image.setRotation(angle);
			image.draw(xpos, ypos);
		} else {
			broken = true;
		}

	}

	/**
	 * Moves weapon
	 * 
	 * @param delta
	 */
	public void move(int delta) {
		if (type == WeaponTypes.GUN || type == WeaponTypes.MINIGUN) {

			float hspeed = speed * (float) Math.cos(Math.toRadians(angle))
					* (float) delta / 17;
			float vspeed = speed * (float) Math.sin(Math.toRadians(angle))
					* (float) delta / 17;
			xpos += hspeed;
			ypos += vspeed;
		} else if (type == WeaponTypes.UNGUIDED) {

			if (speed_mod <= 1) {
				speed_mod = speed_mod + 0.05f;
			}
			float hspeed = (speed * speed_mod)
					* (float) Math.cos(Math.toRadians(angle)) * (float) delta
					/ 17;
			float vspeed = (speed * speed_mod)
					* (float) Math.sin(Math.toRadians(angle)) * (float) delta
					/ 17;
			xpos += hspeed;
			ypos += vspeed;
		} else if (type == WeaponTypes.GUIDED_AIR) {
			if (speed_mod <= 1) {
				speed_mod = speed_mod + 0.05f;
			}
			float hspeed = (speed * speed_mod)
					* (float) Math.cos(Math.toRadians(angle)) * (float) delta
					/ 17;
			float vspeed = (speed * speed_mod)
					* (float) Math.sin(Math.toRadians(angle)) * (float) delta
					/ 17;

			xpos += hspeed;
			ypos += vspeed;
		} else if (type == WeaponTypes.BOMB) {
			angleCheck();
			if (angle <= 90 && angle >= 0) {
				angle += (90 - angle) / 75;
			} else if (angle >= 270 && angle < 360) {
				angle += (angle - 270) / 75;
			} else if (angle < 270 && angle > 180) {
				angle += (angle - 270) / 75;
			} else if (angle > 90 && angle <= 180) {
				angle += (90 - angle) / 75;
			}
			if (speed_mod_bomb >= 0.2) {
				speed_mod_bomb = speed_mod_bomb - 0.01f;
			}
			float hspeed = (speed * speed_mod_bomb)
					* (float) Math.cos(Math.toRadians(angle)) * (float) delta
					/ 17;
			xpos += hspeed;
			ypos += GamePlayState.GRAVITY * 4 * speed_mod_bomb;
		} else if (type == WeaponTypes.BOMB_SPLIT) {
			angleCheck();
			if (ypos - firstHeight > 300 && !isSplit) {
				split = true;
			} else {
				split = false;
			}
			if (angle <= 90 && angle >= 0) {
				angle += (90 - angle) / 75;
			} else if (angle >= 270 && angle <= 360) {
				angle += (angle - 270) / 75;
			} else if (angle < 270 && angle > 180) {
				angle += (angle - 270) / 75;
			} else if (angle > 90 && angle <= 180) {
				angle += (90 - angle) / 75;
			}
			if (speed_mod_bomb >= 0.2) {
				speed_mod_bomb = speed_mod_bomb - 0.01f;
			}
			float hspeed = (speed * speed_mod_bomb)
					* (float) Math.cos(Math.toRadians(angle)) * (float) delta
					/ 17;
			xpos += hspeed;
			ypos += GamePlayState.GRAVITY * 4 * speed_mod_bomb;
		} else if (type == WeaponTypes.BOMB_SPLIT_SMALL) {
			if (angle <= 90 && angle >= 0) {
				angle += (90 - angle) / 75;
			} else if (angle >= 270 && angle <= 360) {
				angle += (angle - 270) / 75;
			} else if (angle < 270 && angle > 180) {
				angle += (angle - 270) / 75;
			} else if (angle > 90 && angle <= 180) {
				angle += (90 - angle) / 75;
			}
			if (speed_mod_bomb >= 0.2) {
				speed_mod_bomb = speed_mod_bomb - 0.01f;
			}
			float hspeed = (speed * speed_mod_bomb)
					* (float) Math.cos(Math.toRadians(angle)) * (float) delta
					/ 17;
			xpos += hspeed;
			ypos += GamePlayState.GRAVITY * 4 * speed_mod_bomb;
		}
	}

	private void angleCheck() {
		if (angle >= 360) {
			angle = 0;
		}
	}

	public float getAngle() {
		if (angle >= 360) {
			angle = 0;
		}
		return angle;
	}

	public void setAngle(float amount) {
		angle = amount;
	}

	public void increaseAngle(float amount) {
		angle = angle + amount;

		if (angle == 360 || angle == -360) {
			angle = 0;
		}
	}

	/**
	 * Returns if a weapon can split itself
	 * 
	 * @return
	 */
	public boolean canSplit() {
		return split;
	}

	/**
	 * Sets a weapon to split
	 * 
	 * @return
	 */
	public void setSplit() {
		isSplit = true;
	}

	/**
	 * Sets this weapons hit status to true
	 */
	public void setHit() {
		broken = true;
	}

	/**
	 * Returns wether this weapon has hit something
	 * 
	 * @return
	 */
	public boolean isHit() {
		return broken;
	}

	/**
	 * Returns Weapon type
	 * 
	 * @return
	 */
	public WeaponTypes getType() {
		return type;
	}

	/**
	 * Returns width of weapon
	 * 
	 * @return
	 */
	public float getWidth() {
		return width;
	}

	/**
	 * Returns height of weapon
	 * 
	 * @return
	 */
	public float getHeight() {
		return height;
	}

	/**
	 * Returns damage of weapon
	 * 
	 * @return
	 */
	public int getDamage() {
		return damage;
	}

	/**
	 * Returns if weapon is homing and has a target
	 * 
	 * @return
	 */
	public boolean hasTarget() {
		return hasTarget;
	}

	/**
	 * Sets a target for weapon
	 * 
	 * @param target
	 */
	public void setTarget(boolean target) {
		this.hasTarget = target;
	}

	/**
	 * Returns player id to whom this weapon belongs
	 * 
	 * @return
	 */
	public int getID() {
		return id;
	}

	/**
	 * Sets player id to whom this weapon belongs
	 * 
	 * @param id
	 */
	public void setID(int id) {
		this.id = id;
	}

	/**
	 * Returns weapon image
	 * 
	 * @return
	 */
	public Image getImage() {
		return image;
	}

	/**
	 * Sets weapon image
	 * 
	 * @param id
	 */
	public void setImage(Image image) {
		this.image = image;
	}

	/**
	 * Returns wether a weapon is 'alive'. Alive means not destroyed or max
	 * range achieved
	 * 
	 * @return
	 */
	public boolean isAlive() {
		if (type == WeaponTypes.GUN || type == WeaponTypes.MINIGUN) {
			if (time > MAX_LIFETIME_GUN) {
				return false;
			}
		} else if (type == WeaponTypes.GUIDED_AIR) {
			if (time > MAX_LIFETIME_GUIDED_AIR) {
				return false;
			}
		} else if (type == WeaponTypes.UNGUIDED) {
			if (time > MAX_LIFETIME_UNGUIDED) {
				return false;
			}
		} else if (type == WeaponTypes.GUIDED_GROUND) {

		} else if (type == WeaponTypes.BOMB || type == WeaponTypes.BOMB_SPLIT) {
			if (time > MAX_LIFETIME_BOMB) {
				return false;
			}
		}
		return true;
	}
}