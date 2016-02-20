package ui;

import java.awt.Graphics;


public class LayerLevel extends Layer
{

	
	
	public LayerLevel(int x,int y,int w,int h)
	{
		super(x,y,w,h);
	}
	public void paint(Graphics g)
	{
		this.creatWindow(g);
		g.drawImage(Img.LEVEL,this.x+((this.w-Img.LEVEL.getWidth(null))>>1),
				this.y+PADDING,
				Img.LEVEL.getWidth(null),
				Img.LEVEL.getHeight(null), null);
		drawNumberLeftPad(g,this.dto.getLevel(),Img.LEVEL.getHeight(null)+(PADDING<<1));
	
	
	}

}
