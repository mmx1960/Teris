package ui;

import java.awt.Graphics;
import java.awt.Image;
import java.util.List;

import dto.Player;

public abstract  class LayerData extends Layer
{
		//ֵ�۵ļ��
		public final int SEPA;
		//����ֵ�ۿ�ʼ��Y����
		public static int START_Y;

	public LayerData(int x, int y, int w, int h)
	{
		super(x, y, w, h);
		//���ֵ�ۼ��
		SEPA=(this.h-(PADDING<<1)-Img.DB.getHeight(null)-(EXP_HEIGH+4)*maxRow)/maxRow;
		//�����ʼY����
		START_Y=PADDING+Img.DB.getHeight(null)+SEPA;
	}
	public void showFillImage(List<Player> players,Image img,Graphics g)
	{
		//�������ݿ�ͼƬ
		g.drawImage(img,this.x+PADDING ,this.y+PADDING, null);
		//ѭ������ֵ��
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
