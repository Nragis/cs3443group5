package bullet_hell.model;

public class Enemy extends GameObject{
	public Enemy(Shape shape, double size) {
		super(shape, size);
	}

	public Enemy(Shape shape, double size, double x, double y){
		super(shape, size, x, y);
	}

	public Enemy(Shape shape, double size, double x, double y, double dx, double dy){
		super(shape, size, x, y, dx, dy);
	}

	public Enemy(Shape shape, double size, double x, double y, double dx, double dy, double rotation){
		super(shape, size, x, y, dx, dy, rotation);
	}
}
