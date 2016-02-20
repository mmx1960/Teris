package config;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Element;

public class FrameConfig
{

	private final List<LayerConfig> layersConfig;
	private final String title;
	private final int width;
	private final int padding;
	private final int height;
	private final int border;
	private final int maxRow;
	private final int loseIdx;

	public int getMaxRow()
	{
		return maxRow;
	}

	public FrameConfig(Element frame)
	{	
		this.title=frame.attributeValue("title");
		this.width=Integer.parseInt(frame.attributeValue("width"));
		this.height=Integer.parseInt(frame.attributeValue("height"));
		this.padding=Integer.parseInt(frame.attributeValue("padding"));
		this.border=Integer.parseInt(frame.attributeValue("border"));
		this.maxRow=Integer.parseInt(frame.attributeValue("maxRow"));
		this.loseIdx=Integer.parseInt(frame.attributeValue("loseIdx"));
		/**
		 * ΩÁ√Ê≈‰÷√
		 * @author LoveYz
		 * @param frame
		 */
		layersConfig =new ArrayList<LayerConfig>();
		@SuppressWarnings("unchecked")
		List<Element> layers =frame.elements("layer");
		for(Element layer:layers)
		{
			LayerConfig lc=new LayerConfig(
			layer.attributeValue("className"),
			Integer.parseInt(layer.attributeValue("x")),
			Integer.parseInt(layer.attributeValue("y")),
			Integer.parseInt(layer.attributeValue("w")),
			Integer.parseInt(layer.attributeValue("h")));
			layersConfig.add(lc);
	}

	}

	public List<LayerConfig> getLayersConfig()
	{
		return layersConfig;
	}

	public String getTitle()
	{
		return title;
	}

	public int getWidth()
	{
		return width;
	}

	public int getPadding()
	{
		return padding;
	}

	public int getHeight()
	{
		return height;
	}

	public int getBorder()
	{
		return border;
	}

	public int getLoseIdx()
	{
		return loseIdx;
	}


}
