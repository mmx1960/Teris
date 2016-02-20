package ui;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import config.FrameConfig;
import config.GameConfig;
import config.LayerConfig;
import control.GameControl;
import control.PlayerControl;
import dto.GameDto;

public class JPanelGame extends JPanel
{
	
	private GameDto dto=null;
	private List<Layer> layers =null;
	private JButton start;
	private GameControl gameControl = null;
	

	public void setGameControl(GameControl gameControl)
	{
		this.gameControl = gameControl;
	}
	public JPanelGame(GameDto dto)
	{
		this.dto=dto;
		this.setLayout(null);
		//初始化窗口层
		initLayer();
		
		start = new JButton(Img.START);
		//TODO 节省时间，硬编码
		start.setBounds(828, 68, 105, 50);
		this.add(start);
		this.start.addActionListener(new ActionListener()
		{
			
			public void actionPerformed(ActionEvent e)
			{
				gameControl.start();
			}
		});
		

		

	}
	/**
	 * 安装监听器
	 * @param playerControl
	 */
	public void setGameControl(PlayerControl playerControl)
	{
		this.addKeyListener(playerControl);
	}

	/**
	 * 初始化窗口层
	 */
	private void initLayer()
	{
		try
		{
			FrameConfig fCfg=GameConfig.getFrameConfig();
			//获取layer元素
			List<LayerConfig> layersCfg = fCfg.getLayersConfig();
			layers= new ArrayList<Layer>(layersCfg.size());
			//利用反射循环创建Layer对象 并添加到layers集合里
			for(LayerConfig layerCfg:layersCfg)
			{
				//获得类对象
				Class<?> c =Class.forName(layerCfg.getClassName());
				//获得类的构造方法
				Constructor<?> ctr =c.getConstructor(int.class,int.class,int.class,int.class);
				//创建Layer对象
				Layer layer=(Layer)ctr.newInstance(layerCfg.getX(),layerCfg.getY(),layerCfg.getW(),layerCfg.getH());
				//把Lay对象添加到layers集合中
				layer.setDto(this.dto);
				layers.add(layer);	
			} 
		}catch (Exception e)
			{
				e.printStackTrace();
			}
	}
	/**
	 * 绘制窗口层
	 */
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		//绘制游戏界面
		for(int i=0;i<layers.size();layers.get(i++).paint(g));
		//获得当前窗口的焦点
		this.requestFocus();

	}
	public JButton getStart()
	{
		return start;
	}
	
}
