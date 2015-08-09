package knowledgeevents;

import java.io.Serializable;

public class Circle implements Serializable {
	
	
	int radius;
	
	public Circle(int radius)
	{
		this.radius=radius;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}
	
	
}
