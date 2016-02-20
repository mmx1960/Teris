package config;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Element;

public class SystemConfig
{
		//地图最大X坐标
		private final int MAX_X;
		//地图最大Y坐标
		private final int MAX_Y;
		//地图最小X坐标
		private final int MIN_X;
		//地图最小Y坐标
		private final int MIN_Y;
		private final List<Point[]> typeConfig;
	public SystemConfig(Element system)
	{
		MAX_X = Integer.parseInt(system.attributeValue("MAX_X"));
		MAX_Y = Integer.parseInt(system.attributeValue("MAX_Y"));
		MIN_X = Integer.parseInt(system.attributeValue("MIN_X"));
		MIN_Y = Integer.parseInt(system.attributeValue("MIN_Y"));
		
		
		List<Element> rects =system.elements("rect");
		typeConfig =new ArrayList<Point[]>(rects.size());
		for(Element rect:rects)
		{
			List<Element> pointConfig =rect.elements("points");
			Point[] points = new Point[pointConfig.size()];
			for(int i = 0;i < points.length;i++)
			{
				int x = Integer.parseInt(pointConfig.get(i).attributeValue("x"));
				int y = Integer.parseInt(pointConfig.get(i).attributeValue("y"));
				points[i] = new Point(x,y);
			}
			typeConfig.add(points);
		}
		
		
	}
	public int getMAX_X()
	{
		return MAX_X;
	}
	public int getMAX_Y()
	{
		return MAX_Y;
	}
	public int getMIN_X()
	{
		return MIN_X;
	}
	public int getMIN_Y()
	{
		return MIN_Y;
	}
	public List<Point[]> getTypeConfig()
	{
		return typeConfig;
	}


}
