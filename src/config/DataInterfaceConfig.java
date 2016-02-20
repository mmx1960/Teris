package config;

import java.util.HashMap;
import java.util.List;

import org.dom4j.Element;

public class DataInterfaceConfig
{
	private String className;
	public String getClassName()
	{
		return className;
	}
	public HashMap<String, String> getParam()
	{
		return param;
	}
	private HashMap<String,String> param;
	public DataInterfaceConfig(Element dataCfg)
	{
		
		param=new HashMap<String,String>();
		List<Element> params=dataCfg.elements("param");
		className=dataCfg.attributeValue("className");
		for (Element e:params)
		{
			param.put(e.attributeValue("key"), e.attributeValue("value"));
		}
	}
	
	
}
