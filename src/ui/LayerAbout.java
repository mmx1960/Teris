package ui;

import java.awt.Graphics;

public class LayerAbout extends Layer
{

	public LayerAbout(int x,int y,int w,int h)
	{
		super(x,y,w,h);
	}
	public void paint(Graphics g)
	{
		this.creatWindow(g);
		g.drawImage(Img.ABOUT, this.x,this.y+BORDER ,this.w,this.h-(BORDER<<1), null);
	}
}
