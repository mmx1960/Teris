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
	 * 游戏面板
	 */
	private JPanelGame jPanelGame=null;
	/**
	 * 游戏逻辑
	 */
	private GameService gameService=null;
	private Data dataA;
	private Data dataB;

	public GameControl(JPanelGame jPanelGame, GameService gameService)
	{
		//初始化面板
		this.jPanelGame=jPanelGame;
		//初始化逻辑
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
			//获得类对象
			Class<?> cls=Class.forName(dataCfg.getClassName());
			//获得类的构造方法
			Constructor<?> ctr =cls.getConstructor(HashMap.class);
			//创建Layer对象
			data=(Data)ctr.newInstance(dataCfg.getParam());
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}
	/**
	 * 按向上按钮
	 */
	public void keyUp()
	{
		//调用逻辑层的方法
		this.gameService.keyUp();
		//面板重新绘制
		this.jPanelGame.repaint();
	}
	/**
	 * 按向下按钮
	 */
	public void keyDown()
	{
		this.gameService.keyDown();
		this.jPanelGame.repaint();
	}
	/**
	 * 按向左按钮
	 */
	public void keyLeft()
	{
		this.gameService.keyLeft();
		this.jPanelGame.repaint();
	}
	/**
	 * 按向右按钮
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
