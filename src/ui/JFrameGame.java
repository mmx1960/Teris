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
		//���ñ���
		this.setTitle(fCfg.getTitle());
		//���ô��ڴ�С
		this.setSize(fCfg.getWidth(),fCfg.getHeight());
		//����Ĭ�Ϲرշ�ʽ
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//���ò����ƶ�
		this.setResizable(false);
		//���þ���
		Dimension screen =Toolkit.getDefaultToolkit().getScreenSize();
		int x =screen.width-this.getWidth()>>2;
		int y =screen.height-this.getHeight()>>2;
		this.setLocation(x,y);
		//�������
		this.setContentPane(jPanelGame);
		//���ÿɼ���
		this.setVisible(true);
	}
}
