package bullet_hell.model;

import java.lang.Math;

public enum Shape{
	CIRCLE(0),
	TRIANGLE(3),
	SQUARE(4),
	PENTAGON(5),
	HEXAGON(6);
	
	public double[] xOffset;
	public double minXOffset = 0;
	public double maxXOffset = 0;
	public double[] yOffset;
	public double minYOffset = 0;
	public double maxYOffset = 0;
	public int numSides;

	Shape(int numSides){
		this.numSides = numSides;
		
		this.xOffset = new double[numSides];
		this.yOffset = new double[numSides];

		for(int i = 0; i < numSides; ++i){
			double angle =  i * (2 * Math.PI / numSides);
			this.xOffset[i] = Math.cos(angle);
			this.yOffset[i] = Math.sin(angle);

			if(this.xOffset[i] > this.maxXOffset) 
				this.maxXOffset = this.xOffset[i];
			if(this.xOffset[i] < this.minXOffset) 
				this.minXOffset = this.xOffset[i];
			if(this.yOffset[i] > this.maxYOffset) 
				this.maxYOffset = this.yOffset[i];
			if(this.yOffset[i] < this.minYOffset) 
				this.minYOffset = this.yOffset[i];
		}
	}
}
