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
	 * ����Դ����
	 */
	private GameDto dto = null;
	/**
	 * ���������
	 */
	private Random random = new Random();
	// ������������
	private static int MAX_TYPE = 6;

	/**
	 * ���������
	 * 
	 * @return
	 */
	public Random getRandom()
	{
		return random;
	}

	public GameService(GameDto dto)
	{
		// �õ�����Դ
		this.dto = dto;

	}

	/**
	 * ���Ҽ�
	 */
	public void keyRight()
	{
		this.dto.getGameAct().move(1, 0, dto.getGameMap());
	}

	/**
	 * �����
	 */
	public void keyLeft()
	{
		this.dto.getGameAct().move(-1, 0, dto.getGameMap());
	}

	/**
	 * ���¼�
	 */
	public void keyDown()
	{
		if (this.dto.getGameAct().move(0, 1, dto.getGameMap()))
		{
			return;
		}
		// �õ���ͼ����
		boolean[][] map = this.dto.getGameMap();
		// �õ����������
		Point[] acts = this.dto.getGameAct().getActPoints();
		// ���ݷ����λ������ͼ
		for (int i = 0; i < acts.length; i++)
		{
			map[acts[i].x][acts[i].y] = true;
		}
		// TODO �ж��Ƿ�����

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
					// TODO ���µ�ͼ
					map[x][y] = false;
				}
				// TODO ����
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

		// TODO �Ʒ�
		// TODO �ж��Ƿ�Ʒ�
		// TODO �Ʒ�
		// TODO ˢ��һ���·���
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
	 * ���ϼ�
	 */
	public void keyUp()
	{
		// ��ת
		this.dto.getGameAct().round(dto.getGameMap());

	}

	/*----------------------------------------------------------------------------------------------------------*/
	/*-------------------------���Է���--------------------------------------------------------------------------*/
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

		// �����·���
		GameAct gameAct = new GameAct();
		// �Ѹ�������Դ��ķ���
		this.dto.setGameAct(gameAct);
		this.dto.setIsStart(true);
		
	}

}
