package service;

import java.awt.Point;
import java.util.List;
import java.util.Random;

import dto.GameDto;
import dto.Player;
import entity.GameAct;

public class GameService
{
	/**
	 * 数据源对象
	 */
	private GameDto dto = null;
	/**
	 * 随机数成器
	 */
	private Random random = new Random();
	// 方块类型总数
	private static int MAX_TYPE = 6;

	/**
	 * 生成随机数
	 * 
	 * @return
	 */
	public Random getRandom()
	{
		return random;
	}

	public GameService(GameDto dto)
	{
		// 得到数据源
		this.dto = dto;

	}

	/**
	 * 向右键
	 */
	public void keyRight()
	{
		this.dto.getGameAct().move(1, 0, dto.getGameMap());
	}

	/**
	 * 向左键
	 */
	public void keyLeft()
	{
		this.dto.getGameAct().move(-1, 0, dto.getGameMap());
	}

	/**
	 * 向下键
	 */
	public void keyDown()
	{
		if (this.dto.getGameAct().move(0, 1, dto.getGameMap()))
		{
			return;
		}
		// 得到地图数据
		boolean[][] map = this.dto.getGameMap();
		// 得到方块的坐标
		Point[] acts = this.dto.getGameAct().getActPoints();
		// 根据方块的位置填充地图
		for (int i = 0; i < acts.length; i++)
		{
			map[acts[i].x][acts[i].y] = true;
		}
		// TODO 判断是否消行

		boolean need = true;
		int map_x = this.dto.getMap_x();
		int map_y = this.dto.getMap_y();

		for (int y = map_y; y > 0;)
		{

			need = true;
			for (int x = 0; x < map_x; x++)
			{
				if (map[x][y] != true)
				{
					need = false;
					break;
				}

			}
			if (need == true)
			{
				for (int x = 0; x < map.length; x++)
				{
					// TODO 更新地图
					map[x][y] = false;
				}
				// TODO 消行
				for (int ry = y; ry > 0; ry--)
				{
					for (int x = 0; x < map.length; x++)
					{
						if (ry != 0)
							map[x][ry] = map[x][ry - 1];
					}
				}
				int rmline = this.dto.getNowRemoveLine();
				int points = this.dto.getNowPoint();
				int level = this.dto.getLevel();
				this.dto.setNowRemoveLine(++rmline);
				this.dto.setNowPoint(points + 10);
				if (rmline % 20 == 0)
					this.dto.setLevel(++level);
			} else
			{
				y--;
			}
		}

		// TODO 计分
		// TODO 判断是否计分
		// TODO 计分
		// TODO 刷新一个新方块
		this.dto.getGameAct().init(this.dto.getNext());
		this.dto.setNext(this.random.nextInt(MAX_TYPE));
		boolean[][] gamemap = this.dto.getGameMap();
		Point[] actionPoint = this.dto.getGameAct().getActPoints();
		int len = actionPoint.length;
		for(int i = 0;i < len;i++)
		{
			if(gamemap[actionPoint[i].x][actionPoint[i].y] == true)
			{
				this.dto.setLose(true);
				break;
			}
		}

	}

	/**
	 * 向上键
	 */
	public void keyUp()
	{
		// 旋转
		this.dto.getGameAct().round(dto.getGameMap());

	}

	/*----------------------------------------------------------------------------------------------------------*/
	/*-------------------------测试方法--------------------------------------------------------------------------*/
	public void testMethod()
	{
		int rmline = this.dto.getNowRemoveLine();
		int level = this.dto.getLevel();
		int points = this.dto.getNowPoint();
		rmline++;
		points = rmline * 10;
		if (rmline % 20 == 0)
		{
			level++;
		}
		this.dto.setNowRemoveLine(rmline);
		this.dto.setNowPoint(points);
		this.dto.setLevel(level);
	}

	public void setDbRecordTest(List<Player> dbRecord)
	{
		this.dto.setDbRecord(dbRecord);
	}

	public void setDiskRecordTest(List<Player> diskRecord)
	{
		this.dto.setDiskRecord(diskRecord);
	}

	public void start()
	{

		// 创建新方块
		GameAct gameAct = new GameAct();
		// 把更新数据源里的方块
		this.dto.setGameAct(gameAct);
		this.dto.setIsStart(true);
		
	}

}
