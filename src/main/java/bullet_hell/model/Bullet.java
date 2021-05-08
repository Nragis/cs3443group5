package bullet_hell.model;


public class Bullet extends GameObject{
	private static final double BULLET_SIZE = 5.0;
	private boolean friendly;
	private boolean remove;
	
	public Bullet() {
		super(Shape.CIRCLE, BULLET_SIZE);
	}

	public boolean isFriendly(){
		return this.friendly;
	}

	public void setFriendly(boolean friendly){
		this.friendly = friendly;
	}

	public void remove(){
		this.remove = true;
	}

	public boolean isRemove(){
		return this.remove;
	}
}
