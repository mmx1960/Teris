package ui;

import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import config.GameConfig;

public class Img
{
	//声明背景数组
	protected static List<Image> BG_LIST;
	//加载开始图片
	protected static ImageIcon START = new ImageIcon("graphics/string/start.png");
	//加载暂停图片
	protected static ImageIcon SET = new ImageIcon("graphics/string/pause.png");
	//加载数字图片
	protected static Image NUM = new ImageIcon("graphics/string/num.png").getImage();
	//加载经验值图片
	protected static Image EXP = new ImageIcon("graphics/window/rect.png").getImage();
	//加载等级图片
	protected static Image LEVEL = new ImageIcon("graphics/string/level.png").getImage();
	//加载方块图片
	protected static Image ACT = new ImageIcon("graphics/game/rect.png").getImage();
	//加载分数图片
	protected static Image POINT = new ImageIcon("graphics/string/point.png").getImage();
	//加载消行图片
	protected static Image RMLINE = new ImageIcon("graphics/string/rmline.png").getImage();
	//加载作者图片
	protected static Image ABOUT = new ImageIcon("graphics/string/sign.png").getImage();
	//加载阴影图片
	protected static Image SHADOW = new ImageIcon("graphics/game/shadow.png").getImage();
	//初始化NEXT方块数组
	protected static Image[] NEXT;
	static{
		//加载背景图片
		BG_LIST=new ArrayList<Image>();
		File dir=new File("graphics/background");
		File[] files=dir.listFiles();
		for (File file:files)
		{
			if(file.isDirectory())
				;
			else
				BG_LIST.add(new ImageIcon(file.getPath()).getImage());
		}	
		
		NEXT=new Image[GameConfig.getSystemConfig().getTypeConfig().size()];
		for (int i = 0; i <NEXT.length ; i++)
		{
			NEXT[i]=new ImageIcon("graphics/game/"+i+".png").getImage();
		}
		
	}
	//加载文件图片
	protected static Image DISK = new ImageIcon("graphics/string/disk.png").getImage();
	//加载数据库图片
	protected static Image DB = new ImageIcon("graphics/string/db.png").getImage();
	
	
	
}
