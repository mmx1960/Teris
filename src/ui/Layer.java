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
	//TODO 每个数字宽度
	protected static final int NUM_WIDTH =Img.NUM.getWidth(null)/10;
	//TODO 每个数字高度
	protected static final int NUM_HEIGH =Img.NUM.getHeight(null);
	//TODO 每个经验值宽度
	protected static final int EXP_WIDTH =Img.EXP.getWidth(null);
	//TODO 每个经验值高度
	protected static final int EXP_HEIGH =Img.EXP.getHeight(null);
	//字体
	protected static final Font DEF_FONT=new Font("黑体",Font.BOLD,20);
	//文字离面板的距离
	protected static final int PADDING;
	//窗口边框的大小
	protected static final int BORDER;
    //前5行
	public static final int maxRow;

	//初始化属性
	static
	{
		FrameConfig fCfg=GameConfig.getFrameConfig();
		PADDING=fCfg.getPadding();
		BORDER=fCfg.getBorder();
		maxRow=fCfg.getMaxRow();
	}
	//获得窗口图片
	protected static Image WINDOW_IMG = new ImageIcon("graphics/window/Window.png").getImage();
	//得到背景图片的宽度
	protected static int WINDOW_W=WINDOW_IMG.getWidth(null);
	//得到背景图片的高度
	protected static int WINDOW_H =WINDOW_IMG.getHeight(null);
	/**
	 * 经验值槽宽度
	 */
	private final int expW;
	/**
	 * 左上角坐标X
	 * */
	protected int x;
	/**
	 * 左上角坐标Y
	 */
	protected int y;
	/**
	 * 窗口宽度
	 */
	protected int w;
	/**
	 * 窗口高度
	 */
	protected int h;
	
	protected GameDto dto=null;
	/**
	 * 装载数据源
	 * @param dto
	 */
	public void setDto(GameDto dto)
	{
		this.dto = dto;
	}
	/**
	 * 初始化各种数据
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
	 * 绘制窗口
	 * @param g
	 */
	protected void creatWindow(Graphics g)
	{
		//绘制左上
		g.drawImage(WINDOW_IMG, x, y, x+BORDER, y+BORDER, 0, 0, BORDER, BORDER, null);
		//绘制中上
		g.drawImage(WINDOW_IMG, x+BORDER, y, x+w-BORDER, y+BORDER, BORDER, 0, WINDOW_W-BORDER, BORDER, null);
		//绘制右上
		g.drawImage(WINDOW_IMG, x+w-BORDER, y,x+w, y+BORDER, WINDOW_W-BORDER, 0, WINDOW_W, BORDER, null);
		//绘制左中
		g.drawImage(WINDOW_IMG, x, y+BORDER, x+BORDER,y+h-BORDER,0 , BORDER, BORDER, WINDOW_H-BORDER, null);
		
		//绘制中间
		g.drawImage(WINDOW_IMG, x+BORDER, y+BORDER, x+w-BORDER, y+h-BORDER, BORDER, BORDER, WINDOW_W-BORDER, WINDOW_H-BORDER, null);
		
		//绘制右中
		g.drawImage(WINDOW_IMG, x+w-BORDER, y+BORDER, x+w,y+h-BORDER,WINDOW_W-BORDER, BORDER,WINDOW_W,WINDOW_H-BORDER,null);
		
		//绘制左下
		g.drawImage(WINDOW_IMG, x, y+h-BORDER, x+BORDER, y+h, 0, WINDOW_H-BORDER, BORDER, WINDOW_H, null);
		
		//绘制中下
		g.drawImage(WINDOW_IMG, x+BORDER, y+h-BORDER, x+w-BORDER, y+h, BORDER, WINDOW_H-BORDER, WINDOW_W-BORDER, WINDOW_H, null);
		
		//绘制右下
		g.drawImage(WINDOW_IMG, x+w-BORDER, y+h-BORDER, x+w, y+h, WINDOW_W-BORDER, WINDOW_H-BORDER, WINDOW_W, WINDOW_H, null);
		
	
	}
	public abstract void paint(Graphics g);
	/**
	 * 居中绘图
	 * @param img
	 * @param g
	 */
	public void drawCenterImg(Image img, Graphics g)
	{
		//居中的X坐标
		int w=(this.w-img.getWidth(null))>>1;
		//居中的Y坐标
		int h=(this.h-img.getHeight(null))>>1;
		//居中画图
		g.drawImage(img, this.x+w, this.y+h, null);
	}
	
	/**
	 * 左填充绘图
	 * @param g 画笔
	 * @param bit 数字
	 * @param y 距离窗口底部的Y坐标
	 */
	public void drawNumberLeftPad(Graphics g,int num,int y)
	{
		//转换数字为字符串
		String strBit = Integer.toString(num);
		for (int i = 0; i < strBit.length(); i++)
		{
			//得到字符串i处的数字
			int bit=strBit.charAt(i)-'0';
			//左填充绘出数字
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
	 * 画值槽
	 * @param title 标题
	 * @param number 分数
	 * @param y 起始Y坐标
	 * @param value 当前分数
	 * @param maxValue 总分
	 * @param g 画笔
	 */
	public void drawFillRect(String title,String number,int y,double value,double maxValue,Graphics g)
	{
		//经验值图片的X坐标
		int rect_x=this.x+PADDING;
		//经验值图片的Y坐标
		int rect_y=this.y+y;
		//画出值槽
		g.setColor(Color.BLACK);
		g.fillRect(rect_x, rect_y, expW, EXP_HEIGH+4);
		g.setColor(Color.WHITE);
		g.fillRect(rect_x+1, rect_y+1, expW-2, EXP_HEIGH+2);
		g.setColor(Color.BLACK);
		g.fillRect(rect_x+2, rect_y+2, expW-4, EXP_HEIGH);
		//得到比值
		double p=value/maxValue;
		//算出经验值宽度	

		int w =(int)(p*(expW-4));

		//算出颜色

		int idx=(int)(p*EXP_WIDTH);
		if(p>1){
			 w=expW-4;
			 idx=EXP_WIDTH-1;
		}
		//画出经验值
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
		//画出标题
		g.setColor(Color.WHITE);
		g.setFont(DEF_FONT);
		g.drawString(title, rect_x+2, rect_y+22);
		if(number!=null)
		{
			drawStringLeftPad(g,number, rect_x+expW-4, rect_y+22);
		}
	}
	/**
	 * 左填充画字符串
	 * @param g
	 * @param number 数字
	 * @param x
	 * @param y
	 */
	protected void drawStringLeftPad(Graphics g,String number,int x,int y)
	{
				for (int i = 0; i < number.length(); i++)
				{
					//得到字符串i处的数字
					String bit=Integer.toString(number.charAt(i)-'0');
					//左填充绘出数字
					g.drawString(bit, x-(number.length()-i)*10, y);
				}
	}
	
}
