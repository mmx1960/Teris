package ui;

import java.awt.Graphics;
import java.util.List;



import dto.Player;

public class LayerDisk extends LayerData
{

	public LayerDisk(int x,int y,int w,int h)
	{
		super(x,y,w,h);
	}
	public void paint(Graphics g)
	{
		//获取数据源
		List<Player> players=this.dto.getDiskRecord();
		this.creatWindow(g);
		this.showFillImage(players, Img.DISK, g);
	}


}
