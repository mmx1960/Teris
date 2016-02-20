package ui;

import java.awt.Graphics;


public class LayerPoint extends Layer
{
	
	private static final int loseidx = 8;
	//TODO 设置每升1级需要消行的次数
	private static final int LEVEL_UP=20;
	
	private final int numY;
	
	private final int rmLineY;
	
	private final int expY;

	public LayerPoint(int x,int y,int w,int h)
	{
		super(x,y,w,h);
		this.numY=PADDING;
		this.rmLineY=numY+PADDING+Img.POINT.getHeight(null);
		this.expY=rmLineY+PADDING+Img.RMLINE.getHeight(null);
		
	}
	public void paint(Graphics g)
	{
		this.creatWindow(g);
		g.drawImage(Img.POINT, this.x+PADDING, 
				this.y+PADDING, null);
		g.drawImage(Img.RMLINE, this.x+PADDING, 
				this.y+Img.POINT.getHeight(null)+PADDING+PADDING, null);
		drawNumberLeftPad(g,this.dto.getNowPoint(),numY);
		drawNumberLeftPad(g,this.dto.getNowRemoveLine(),rmLineY);
		int rmLine=this.dto.getNowRemoveLine();
		drawFillRect("下一级",null,expY,(double)(rmLine%LEVEL_UP),(double)LEVEL_UP,g);
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
