package bullet_hell.model;


public class Bullet extends GameObject{
	private static final double BULLET_SIZE = 2.0;
	public Bullet() {
		super(Shape.CIRCLE, BULLET_SIZE);
	}
}
