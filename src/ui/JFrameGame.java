package ui;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import config.FrameConfig;
import config.GameConfig;

public class JFrameGame extends JFrame
{
	FrameConfig fCfg=GameConfig.getFrameConfig();
	public JFrameGame(JPanelGame jPanelGame)
	{
		//设置标题
		this.setTitle(fCfg.getTitle());
		//设置窗口大小
		this.setSize(fCfg.getWidth(),fCfg.getHeight());
		//设置默认关闭方式
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//设置不可移动
		this.setResizable(false);
		//设置居中
		Dimension screen =Toolkit.getDefaultToolkit().getScreenSize();
		int x =screen.width-this.getWidth()>>2;
		int y =screen.height-this.getHeight()>>2;
		this.setLocation(x,y);
		//创建面板
		this.setContentPane(jPanelGame);
		//设置可见性
		this.setVisible(true);
	}
}
