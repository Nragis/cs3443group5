package bullet_hell.model;

public enum Button{
	SPACE(1),
	LEFT(2),
	RIGHT(4),
	UP(8),
	DOWN(16);
	
	public this.value;

	public Button(int value){
		this.value = value;
	}
}
