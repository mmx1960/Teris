package ui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import config.GameConfig;
import entity.GameAct;

public class LayerGame extends Layer
{
	
	//TODO 
	private static int ACT_BORDER=Img.ACT.getHeight(null);
	private static int LOSE_IDX = GameConfig.getFrameConfig().getLoseIdx();


	public LayerGame(int x,int y,int w,int h)
	{
		super(x,y,w,h);
	}
	
	public void paint(Graphics g)
	{
		this.creatWindow(g);
		if(this.dto.getIsStart())
		{
			//获得方块数组集合
			Point[] points=this.dto.getGameAct().getActPoints();//获得方块类型编号
			int typeCode =this.dto.getGameAct().getTypeCode();
			//阴影开关
			boolean isShowShadow=true;
			// TODO 绘制阴影 
			drawShadow(Img.SHADOW,points,isShowShadow,g);
			for(Point point:points)
			{
				//TODO
				drawActByPoint(point.x,point.y,typeCode+1,g);
			}
			
		}
		
		boolean[][] gameMap=this.dto.getGameMap();
		int lv=this.dto.getLevel();
		//TODO 硬编码
		int index=lv==0?0:(lv-1)%7+1;
				
		for (int x = 0; x < gameMap.length; x++)
		{
			for (int y = 0; y < gameMap[x].length; y++)
			{
				if(gameMap[x][y]==true)
					drawActByPoint(x,y,this.dto.isLose()?LOSE_IDX:index,g);
			}
		}
				
	}

	private void drawShadow(Image img, Point[] points,boolean isShowShadow,Graphics g)
	{
		if(!isShowShadow)
			return;
		//TODO 硬编码
		int leftX=9;
		int rightX=0;
		for (Point p:points)
		{
			leftX=p.x<leftX?p.x:leftX;
			rightX=p.x>rightX?p.x:rightX;
		}
		g.drawImage(img, this.x+leftX*ACT_BORDER+BORDER,this.y+BORDER,(rightX-leftX)*ACT_BORDER+ACT_BORDER,this.h-BORDER,null);
	}

	private void drawActByPoint(int x,int y,int imgIdx,Graphics g)
	{
	g.drawImage(Img.ACT, this.x+x*ACT_BORDER+BORDER,
			this.y+y*ACT_BORDER+BORDER, 
			this.x+x*ACT_BORDER+ACT_BORDER+BORDER,
			this.y+y*ACT_BORDER+ACT_BORDER+BORDER, imgIdx*ACT_BORDER, 0, imgIdx*ACT_BORDER+ACT_BORDER, ACT_BORDER, null);
	}
	
}
