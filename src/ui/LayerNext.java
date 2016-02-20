package ui;

import java.awt.Graphics;

import dto.GameDto;

public class LayerNext extends Layer
{

	public LayerNext(int x, int y, int w, int h)
	{
		super(x, y, w, h);
	}

	public void paint(Graphics g)
	{
		this.creatWindow(g);
		if( this.dto.getIsStart())
		{
			this.drawCenterImg(Img.NEXT[this.dto.getNext()], g);
		}

	}

}
