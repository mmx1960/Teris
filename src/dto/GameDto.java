package dto;

import java.util.Collections;
import java.util.List;

import config.GameConfig;
import config.SystemConfig;
import entity.GameAct;

public class GameDto 
{
	private List<Player> dbRecord; 
	private List<Player> diskRecord;
	private boolean[][] gameMap;
	private GameAct gameAct;
	private int next;
	private int level;
	private int nowPoint;
	private int nowRemoveLine;
	private final int map_x =GameConfig.getSystemConfig().getMAX_X();
	private final int map_y = GameConfig.getSystemConfig().getMAX_Y();
	private boolean isStart;
	private boolean isLose;
	public GameDto()
	{
		doInit();
	}
	public void doInit()
	{
		gameMap=new boolean[map_x+1][map_y+1];
		//TODO 初始化所有游戏对象
		//TODO 初始化Next
		next=1;
		level=0;
		nowPoint=0;
		nowRemoveLine=0;
		isStart = false;
		isLose = false;
	}
	public int getMap_x()
	{
		return map_x;
	}
	public int getMap_y()
	{
		return map_y;
	}
	public List<Player> getDbRecord()
	{
		return dbRecord;
	}
	public void setDbRecord(List<Player> dbRecord)
	{
		while (dbRecord.size()<5)
	{
			dbRecord.add(new Player("No Data", 0));
	}
		Collections.sort(dbRecord);
		this.dbRecord = dbRecord;
	}
	public List<Player> getDiskRecord()
	{
		while (diskRecord.size()<5)
	{
			diskRecord.add(new Player("No Data", 0));
	}

		Collections.sort(diskRecord);
		return diskRecord;
	}
	public void setDiskRecord(List<Player> diskRecord)
	{
		this.diskRecord = diskRecord;
	}
	public boolean[][] getGameMap()
	{
		return gameMap;
	}
	public void setGameMap(boolean[][] gameMap)
	{
		this.gameMap = gameMap;
	}
	public GameAct getGameAct()
	{
		return gameAct;
	}
	public void setGameAct(GameAct gameAct)
	{
		this.gameAct = gameAct;
	}
	public int getNext()
	{
		return next;
	}
	public void setNext(int next)
	{
		this.next = next;
	}
	public int getLevel()
	{
		return level;
	}
	public void setLevel(int level)
	{
		this.level = level;
	}
	public int getNowPoint()
	{
		return nowPoint;
	}
	public void setNowPoint(int nowPoint)
	{
		this.nowPoint = nowPoint;
	}
	public int getNowRemoveLine()
	{
		return nowRemoveLine;
	}
	public void setNowRemoveLine(int nowRemoveLine)
	{
		this.nowRemoveLine = nowRemoveLine;
	}
	public boolean getIsStart()
	{
		return isStart;
	}
	public void setIsStart(boolean isStart)
	{
		this.isStart = isStart;
	}
	public boolean isLose()
	{
		return isLose;
	}
	public void setLose(boolean isLose)
	{
		this.isLose = isLose;
	}
	
	
}
