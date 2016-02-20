package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import config.FrameConfig;
import config.GameConfig;
import dto.GameDto;

public abstract class Layer
{
	//TODO ÿ�����ֿ��
	protected static final int NUM_WIDTH =Img.NUM.getWidth(null)/10;
	//TODO ÿ�����ָ߶�
	protected static final int NUM_HEIGH =Img.NUM.getHeight(null);
	//TODO ÿ������ֵ���
	protected static final int EXP_WIDTH =Img.EXP.getWidth(null);
	//TODO ÿ������ֵ�߶�
	protected static final int EXP_HEIGH =Img.EXP.getHeight(null);
	//����
	protected static final Font DEF_FONT=new Font("����",Font.BOLD,20);
	//���������ľ���
	protected static final int PADDING;
	//���ڱ߿�Ĵ�С
	protected static final int BORDER;
    //ǰ5��
	public static final int maxRow;

	//��ʼ������
	static
	{
		FrameConfig fCfg=GameConfig.getFrameConfig();
		PADDING=fCfg.getPadding();
		BORDER=fCfg.getBorder();
		maxRow=fCfg.getMaxRow();
	}
	//��ô���ͼƬ
	protected static Image WINDOW_IMG = new ImageIcon("graphics/window/Window.png").getImage();
	//�õ�����ͼƬ�Ŀ��
	protected static int WINDOW_W=WINDOW_IMG.getWidth(null);
	//�õ�����ͼƬ�ĸ߶�
	protected static int WINDOW_H =WINDOW_IMG.getHeight(null);
	/**
	 * ����ֵ�ۿ��
	 */
	private final int expW;
	/**
	 * ���Ͻ�����X
	 * */
	protected int x;
	/**
	 * ���Ͻ�����Y
	 */
	protected int y;
	/**
	 * ���ڿ��
	 */
	protected int w;
	/**
	 * ���ڸ߶�
	 */
	protected int h;
	
	protected GameDto dto=null;
	/**
	 * װ������Դ
	 * @param dto
	 */
	public void setDto(GameDto dto)
	{
		this.dto = dto;
	}
	/**
	 * ��ʼ����������
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 */
	protected Layer(int x,int y,int w,int h)
	{
		this.x=x;
		this.y=y;
		this.w=w;
		this.h=h;
		this.expW=this.w-(PADDING<<1);
	}
	/**
	 * ���ƴ���
	 * @param g
	 */
	protected void creatWindow(Graphics g)
	{
		//��������
		g.drawImage(WINDOW_IMG, x, y, x+BORDER, y+BORDER, 0, 0, BORDER, BORDER, null);
		//��������
		g.drawImage(WINDOW_IMG, x+BORDER, y, x+w-BORDER, y+BORDER, BORDER, 0, WINDOW_W-BORDER, BORDER, null);
		//��������
		g.drawImage(WINDOW_IMG, x+w-BORDER, y,x+w, y+BORDER, WINDOW_W-BORDER, 0, WINDOW_W, BORDER, null);
		//��������
		g.drawImage(WINDOW_IMG, x, y+BORDER, x+BORDER,y+h-BORDER,0 , BORDER, BORDER, WINDOW_H-BORDER, null);
		
		//�����м�
		g.drawImage(WINDOW_IMG, x+BORDER, y+BORDER, x+w-BORDER, y+h-BORDER, BORDER, BORDER, WINDOW_W-BORDER, WINDOW_H-BORDER, null);
		
		//��������
		g.drawImage(WINDOW_IMG, x+w-BORDER, y+BORDER, x+w,y+h-BORDER,WINDOW_W-BORDER, BORDER,WINDOW_W,WINDOW_H-BORDER,null);
		
		//��������
		g.drawImage(WINDOW_IMG, x, y+h-BORDER, x+BORDER, y+h, 0, WINDOW_H-BORDER, BORDER, WINDOW_H, null);
		
		//��������
		g.drawImage(WINDOW_IMG, x+BORDER, y+h-BORDER, x+w-BORDER, y+h, BORDER, WINDOW_H-BORDER, WINDOW_W-BORDER, WINDOW_H, null);
		
		//��������
		g.drawImage(WINDOW_IMG, x+w-BORDER, y+h-BORDER, x+w, y+h, WINDOW_W-BORDER, WINDOW_H-BORDER, WINDOW_W, WINDOW_H, null);
		
	
	}
	public abstract void paint(Graphics g);
	/**
	 * ���л�ͼ
	 * @param img
	 * @param g
	 */
	public void drawCenterImg(Image img, Graphics g)
	{
		//���е�X����
		int w=(this.w-img.getWidth(null))>>1;
		//���е�Y����
		int h=(this.h-img.getHeight(null))>>1;
		//���л�ͼ
		g.drawImage(img, this.x+w, this.y+h, null);
	}
	
	/**
	 * ������ͼ
	 * @param g ����
	 * @param bit ����
	 * @param y ���봰�ڵײ���Y����
	 */
	public void drawNumberLeftPad(Graphics g,int num,int y)
	{
		//ת������Ϊ�ַ���
		String strBit = Integer.toString(num);
		for (int i = 0; i < strBit.length(); i++)
		{
			//�õ��ַ���i��������
			int bit=strBit.charAt(i)-'0';
			//�����������
			g.drawImage(Img.NUM,
					this.x+this.w-(strBit.length()-i+1)*NUM_WIDTH,
					this.y+y,
					this.x+this.w-(strBit.length()-i)*NUM_WIDTH,
					this.y+y+NUM_HEIGH,
					bit*NUM_WIDTH,
					0,
					bit*NUM_WIDTH+NUM_WIDTH,
					NUM_HEIGH, 
					null);

		}
	}
	/**
	 * ��ֵ��
	 * @param title ����
	 * @param number ����
	 * @param y ��ʼY����
	 * @param value ��ǰ����
	 * @param maxValue �ܷ�
	 * @param g ����
	 */
	public void drawFillRect(String title,String number,int y,double value,double maxValue,Graphics g)
	{
		//����ֵͼƬ��X����
		int rect_x=this.x+PADDING;
		//����ֵͼƬ��Y����
		int rect_y=this.y+y;
		//����ֵ��
		g.setColor(Color.BLACK);
		g.fillRect(rect_x, rect_y, expW, EXP_HEIGH+4);
		g.setColor(Color.WHITE);
		g.fillRect(rect_x+1, rect_y+1, expW-2, EXP_HEIGH+2);
		g.setColor(Color.BLACK);
		g.fillRect(rect_x+2, rect_y+2, expW-4, EXP_HEIGH);
		//�õ���ֵ
		double p=value/maxValue;
		//�������ֵ���	

		int w =(int)(p*(expW-4));

		//�����ɫ

		int idx=(int)(p*EXP_WIDTH);
		if(p>1){
			 w=expW-4;
			 idx=EXP_WIDTH-1;
		}
		//��������ֵ
			g.drawImage(Img.EXP,
				rect_x+2, 
				rect_y+2, 
				rect_x+2+w, 
				rect_y+2+EXP_HEIGH, 
				idx,
				0, 
				idx+1,
				EXP_HEIGH,
				null);
		//��������
		g.setColor(Color.WHITE);
		g.setFont(DEF_FONT);
		g.drawString(title, rect_x+2, rect_y+22);
		if(number!=null)
		{
			drawStringLeftPad(g,number, rect_x+expW-4, rect_y+22);
		}
	}
	/**
	 * ����仭�ַ���
	 * @param g
	 * @param number ����
	 * @param x
	 * @param y
	 */
	protected void drawStringLeftPad(Graphics g,String number,int x,int y)
	{
				for (int i = 0; i < number.length(); i++)
				{
					//�õ��ַ���i��������
					String bit=Integer.toString(number.charAt(i)-'0');
					//�����������
					g.drawString(bit, x-(number.length()-i)*10, y);
				}
	}
	
}
