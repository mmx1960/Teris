package entity;

import java.awt.Point;
import java.util.List;

import config.GameConfig;
public class GameAct
{
	//地图最大X坐标
	private static int MAX_X=GameConfig.getSystemConfig().getMAX_X();
	//地图最大Y坐标
	private static int MAX_Y=GameConfig.getSystemConfig().getMAX_Y();
	//地图最小X坐标
	private static int MIN_X=GameConfig.getSystemConfig().getMIN_X();
	//地图最小Y坐标
	private static int MIN_Y =GameConfig.getSystemConfig().getMIN_Y();
	//方块数组
	private static List<Point[]> TYPE_CONFIG = GameConfig.getSystemConfig().getTypeConfig();
	/**
	 * 方块坐标
	 */
	private Point[] actPoints=null;
	/**
	 * 方块类型
	 */
	private int typeCode ;
	public GameAct()
	{
		//初始化方块坐标
		init(0);
	}
	
	/**
	 * 
	 * 初始化方块
	 */
	public void init(int typeCode)
	{
		//得到方块代号
		this.typeCode=typeCode;
		//得到方块坐标
		Point[] points=TYPE_CONFIG.get(this.typeCode);
		//初始化方块对象
		actPoints=new Point[points.length];
		for (int i = 0; i < points.length; i++)
		{
			actPoints[i]=new Point(points[i].x,points[i].y);
		}
	}
	/**
	 * 得到方块类型
	 * @return 方块类型
	 */
	public int getTypeCode()
	{
		return typeCode;
	}

	/**
	 * 得到方块坐标
	 * @return 方块坐标
	 */
	
	public Point[] getActPoints()
	{
		return actPoints;
	}
	
	/**
	 * 方块移动
	 * @param movX
	 * @param movY
	 */
	public boolean move(int movX,int movY,boolean[][] gameMap)
	{
		//判断是否超出地图
		for(int i=0;i<actPoints.length;i++)
		{
			int newX=actPoints[i].x+movX;
			int newY=actPoints[i].y+movY;
			if(isOverZone(newX, newY,gameMap))
				return false;
		}
		//循环打印方块移动
		for(int i=0;i<actPoints.length;i++)
		{
			actPoints[i].x+=movX;
			actPoints[i].y+=movY;
		}
		return true;
		
	}


	/**
	 *	 旋转
	 * @param gameMap
	 */

	public void round(boolean[][] gameMap)
	{
		Point[] actPoint =actPoints;
		//判断是否超出地图
		for(int i=1;i<actPoint.length;i++)
		{
			int newX=actPoint[0].x+actPoint[0].y-actPoint[i].y;
			int newY=actPoint[0].y-actPoint[0].x+actPoint[i].x;
			if(isOverZone(newX, newY,gameMap))
				return;
		}
		//旋转
		for(int i=1;i<actPoint.length;i++)
		{
			int newX=actPoint[0].x+actPoint[0].y-actPoint[i].y;
			int newY=actPoint[0].y-actPoint[0].x+actPoint[i].x;
			actPoint[i].x=newX;
			actPoint[i].y=newY;
		}
	}
	
	/**
	 * 判断是否超出地图
	 * @param x 移动后X坐标
	 * @param y 移动后Y坐标
	 * @return
	 */
	private boolean isOverZone(int x,int y,boolean[][] gameMap)
	{
		if(x<MIN_X||x>MAX_X||y<MIN_Y||y>MAX_Y||gameMap[x][y])
			return true;
		return false;
	}
	
	
	
}
