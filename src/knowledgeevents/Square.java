package knowledgeevents;

import java.io.Serializable;
import java.util.List;



public class Square implements Serializable {

	int length;
	int width;
	
	public Square(int length,int width)
	{
		this.length=length;
		this.width=width;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}
	
	
}
