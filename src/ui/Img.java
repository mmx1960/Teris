package ui;

import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import config.GameConfig;

public class Img
{
	//������������
	protected static List<Image> BG_LIST;
	//���ؿ�ʼͼƬ
	protected static ImageIcon START = new ImageIcon("graphics/string/start.png");
	//������ͣͼƬ
	protected static ImageIcon SET = new ImageIcon("graphics/string/pause.png");
	//��������ͼƬ
	protected static Image NUM = new ImageIcon("graphics/string/num.png").getImage();
	//���ؾ���ֵͼƬ
	protected static Image EXP = new ImageIcon("graphics/window/rect.png").getImage();
	//���صȼ�ͼƬ
	protected static Image LEVEL = new ImageIcon("graphics/string/level.png").getImage();
	//���ط���ͼƬ
	protected static Image ACT = new ImageIcon("graphics/game/rect.png").getImage();
	//���ط���ͼƬ
	protected static Image POINT = new ImageIcon("graphics/string/point.png").getImage();
	//��������ͼƬ
	protected static Image RMLINE = new ImageIcon("graphics/string/rmline.png").getImage();
	//��������ͼƬ
	protected static Image ABOUT = new ImageIcon("graphics/string/sign.png").getImage();
	//������ӰͼƬ
	protected static Image SHADOW = new ImageIcon("graphics/game/shadow.png").getImage();
	//��ʼ��NEXT��������
	protected static Image[] NEXT;
	static{
		//���ر���ͼƬ
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
	//�����ļ�ͼƬ
	protected static Image DISK = new ImageIcon("graphics/string/disk.png").getImage();
	//�������ݿ�ͼƬ
	protected static Image DB = new ImageIcon("graphics/string/db.png").getImage();
	
	
	
}
