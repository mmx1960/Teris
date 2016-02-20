package control;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import config.DataInterfaceConfig;
import config.GameConfig;
import service.GameService;
import ui.JPanelGame;
import ui.Layer;
import dao.Data;
import dao.DataBase;
import dao.DataDisk;

public class GameControl
{
	/**
	 * ��Ϸ���
	 */
	private JPanelGame jPanelGame=null;
	/**
	 * ��Ϸ�߼�
	 */
	private GameService gameService=null;
	private Data dataA;
	private Data dataB;

	public GameControl(JPanelGame jPanelGame, GameService gameService)
	{
		//��ʼ�����
		this.jPanelGame=jPanelGame;
		//��ʼ���߼�
		this.gameService=gameService;
		dataA=creatDataObject(GameConfig.getDataConfig().getDataB());
		dataB=creatDataObject(GameConfig.getDataConfig().getDataB());
		this.gameService.setDbRecordTest(dataA.loadData());
		this.gameService.setDiskRecordTest(dataB.loadData());
	}
	private Data creatDataObject(DataInterfaceConfig dataCfg)
	{
		Data data=null;

		try
		{
			//��������
			Class<?> cls=Class.forName(dataCfg.getClassName());
			//�����Ĺ��췽��
			Constructor<?> ctr =cls.getConstructor(HashMap.class);
			//����Layer����
			data=(Data)ctr.newInstance(dataCfg.getParam());
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}
	/**
	 * �����ϰ�ť
	 */
	public void keyUp()
	{
		//�����߼���ķ���
		this.gameService.keyUp();
		//������»���
		this.jPanelGame.repaint();
	}
	/**
	 * �����°�ť
	 */
	public void keyDown()
	{
		this.gameService.keyDown();
		this.jPanelGame.repaint();
	}
	/**
	 * ������ť
	 */
	public void keyLeft()
	{
		this.gameService.keyLeft();
		this.jPanelGame.repaint();
	}
	/**
	 * �����Ұ�ť
	 */
	public void keyRight()
	{
		this.gameService.keyRight();
		this.jPanelGame.repaint();
	}
	public void test()
	{
		this.gameService.testMethod();
		this.jPanelGame.repaint();
	}
	public void start()
	{
		this.gameService.start();
		this.jPanelGame.getStart().setEnabled(false);
		this.jPanelGame.repaint();
		
	}
}
