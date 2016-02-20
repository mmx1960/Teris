package ui;

import java.awt.Graphics;
import java.awt.Image;
import java.util.List;

import dto.Player;

public abstract  class LayerData extends Layer
{
		//值槽的间隔
		public final int SEPA;
		//数据值槽开始的Y坐标
		public static int START_Y;

	public LayerData(int x, int y, int w, int h)
	{
		super(x, y, w, h);
		//算出值槽间隔
		SEPA=(this.h-(PADDING<<1)-Img.DB.getHeight(null)-(EXP_HEIGH+4)*maxRow)/maxRow;
		//算出开始Y坐标
		START_Y=PADDING+Img.DB.getHeight(null)+SEPA;
	}
	public void showFillImage(List<Player> players,Image img,Graphics g)
	{
		//画出数据库图片
		g.drawImage(img,this.x+PADDING ,this.y+PADDING, null);
		//循环画出值槽
		for (int i = 0; i < maxRow; i++)
		{
			Player pla=players.get(i);
			String number=pla.points==0?null:Integer.toString(pla.points);
			drawFillRect(pla.name, number, START_Y+i*(EXP_HEIGH+SEPA), this.dto.getNowPoint(), pla.points, g);
		}
	}

	@Override
	public abstract void paint(Graphics g);
	
	

}
