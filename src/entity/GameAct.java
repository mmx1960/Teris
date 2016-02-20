package entity;

import java.awt.Point;
import java.util.List;

import config.GameConfig;
public class GameAct
{
	//��ͼ���X����
	private static int MAX_X=GameConfig.getSystemConfig().getMAX_X();
	//��ͼ���Y����
	private static int MAX_Y=GameConfig.getSystemConfig().getMAX_Y();
	//��ͼ��СX����
	private static int MIN_X=GameConfig.getSystemConfig().getMIN_X();
	//��ͼ��СY����
	private static int MIN_Y =GameConfig.getSystemConfig().getMIN_Y();
	//��������
	private static List<Point[]> TYPE_CONFIG = GameConfig.getSystemConfig().getTypeConfig();
	/**
	 * ��������
	 */
	private Point[] actPoints=null;
	/**
	 * ��������
	 */
	private int typeCode ;
	public GameAct()
	{
		//��ʼ����������
		init(0);
	}
	
	/**
	 * 
	 * ��ʼ������
	 */
	public void init(int typeCode)
	{
		//�õ��������
		this.typeCode=typeCode;
		//�õ���������
		Point[] points=TYPE_CONFIG.get(this.typeCode);
		//��ʼ���������
		actPoints=new Point[points.length];
		for (int i = 0; i < points.length; i++)
		{
			actPoints[i]=new Point(points[i].x,points[i].y);
		}
	}
	/**
	 * �õ���������
	 * @return ��������
	 */
	public int getTypeCode()
	{
		return typeCode;
	}

	/**
	 * �õ���������
	 * @return ��������
	 */
	
	public Point[] getActPoints()
	{
		return actPoints;
	}
	
	/**
	 * �����ƶ�
	 * @param movX
	 * @param movY
	 */
	public boolean move(int movX,int movY,boolean[][] gameMap)
	{
		//�ж��Ƿ񳬳���ͼ
		for(int i=0;i<actPoints.length;i++)
		{
			int newX=actPoints[i].x+movX;
			int newY=actPoints[i].y+movY;
			if(isOverZone(newX, newY,gameMap))
				return false;
		}
		//ѭ����ӡ�����ƶ�
		for(int i=0;i<actPoints.length;i++)
		{
			actPoints[i].x+=movX;
			actPoints[i].y+=movY;
		}
		return true;
		
	}


	/**
	 *	 ��ת
	 * @param gameMap
	 */

	public void round(boolean[][] gameMap)
	{
		Point[] actPoint =actPoints;
		//�ж��Ƿ񳬳���ͼ
		for(int i=1;i<actPoint.length;i++)
		{
			int newX=actPoint[0].x+actPoint[0].y-actPoint[i].y;
			int newY=actPoint[0].y-actPoint[0].x+actPoint[i].x;
			if(isOverZone(newX, newY,gameMap))
				return;
		}
		//��ת
		for(int i=1;i<actPoint.length;i++)
		{
			int newX=actPoint[0].x+actPoint[0].y-actPoint[i].y;
			int newY=actPoint[0].y-actPoint[0].x+actPoint[i].x;
			actPoint[i].x=newX;
			actPoint[i].y=newY;
		}
	}
	
	/**
	 * �ж��Ƿ񳬳���ͼ
	 * @param x �ƶ���X����
	 * @param y �ƶ���Y����
	 * @return
	 */
	private boolean isOverZone(int x,int y,boolean[][] gameMap)
	{
		if(x<MIN_X||x>MAX_X||y<MIN_Y||y>MAX_Y||gameMap[x][y])
			return true;
		return false;
	}
	
	
	
}
