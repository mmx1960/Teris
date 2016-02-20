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
			//创建xml读取器
			SAXReader reader = new SAXReader();
			//读取xml文件
			Document doc = reader.read("data/cfg.xml");
			//得到根元素
			Element game=doc.getRootElement();
			//加载面板配置
			frameConfig=new FrameConfig(game.element("frame"));
			//加载面板配置
			systemConfig=new SystemConfig(game.element("system"));
			//加载面板配置
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
