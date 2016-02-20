package ui;

import java.awt.Graphics;
import config.FrameConfig;
import config.GameConfig;

public class LayerBackground extends Layer
{

	protected static final int WIDTH;
	protected static final int HEIGHT;
	static
	{
		FrameConfig fCfg=GameConfig.getFrameConfig();
		WIDTH=fCfg.getWidth();
		HEIGHT=fCfg.getHeight();
	}
	public LayerBackground(int x,int y,int w,int h)
	{
		super(x,y,w,h);
	}
	public void paint(Graphics g)
	{
		int lv=this.dto.getLevel()%Img.BG_LIST.size();
		g.drawImage(Img.BG_LIST.get(lv), 0, 0, WIDTH,HEIGHT,null);
	}
}
