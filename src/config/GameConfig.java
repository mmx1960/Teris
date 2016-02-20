package config;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class GameConfig
{
	private static FrameConfig frameConfig=null;
	private static SystemConfig systemConfig=null;
	private static DataConfig dataConfig=null;
	static{
		try
		{
			//����xml��ȡ��
			SAXReader reader = new SAXReader();
			//��ȡxml�ļ�
			Document doc = reader.read("data/cfg.xml");
			//�õ���Ԫ��
			Element game=doc.getRootElement();
			//�����������
			frameConfig=new FrameConfig(game.element("frame"));
			//�����������
			systemConfig=new SystemConfig(game.element("system"));
			//�����������
			dataConfig=new DataConfig(game.element("data"));
			
		} catch (DocumentException e)
		{
			e.printStackTrace();
		}



		
	}
	
	private  GameConfig() 
	{

	}
	
	public static FrameConfig getFrameConfig()
	{
		return frameConfig;
	}

	public static SystemConfig getSystemConfig()
	{
		return systemConfig;
	}

	public static DataConfig getDataConfig()
	{
		return dataConfig;
	}

}
