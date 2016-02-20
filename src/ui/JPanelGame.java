package ui;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import config.FrameConfig;
import config.GameConfig;
import config.LayerConfig;
import control.GameControl;
import control.PlayerControl;
import dto.GameDto;

public class JPanelGame extends JPanel
{
	
	private GameDto dto=null;
	private List<Layer> layers =null;
	private JButton start;
	private GameControl gameControl = null;
	

	public void setGameControl(GameControl gameControl)
	{
		this.gameControl = gameControl;
	}
	public JPanelGame(GameDto dto)
	{
		this.dto=dto;
		this.setLayout(null);
		//��ʼ�����ڲ�
		initLayer();
		
		start = new JButton(Img.START);
		//TODO ��ʡʱ�䣬Ӳ����
		start.setBounds(828, 68, 105, 50);
		this.add(start);
		this.start.addActionListener(new ActionListener()
		{
			
			public void actionPerformed(ActionEvent e)
			{
				gameControl.start();
			}
		});
		

		

	}
	/**
	 * ��װ������
	 * @param playerControl
	 */
	public void setGameControl(PlayerControl playerControl)
	{
		this.addKeyListener(playerControl);
	}

	/**
	 * ��ʼ�����ڲ�
	 */
	private void initLayer()
	{
		try
		{
			FrameConfig fCfg=GameConfig.getFrameConfig();
			//��ȡlayerԪ��
			List<LayerConfig> layersCfg = fCfg.getLayersConfig();
			layers= new ArrayList<Layer>(layersCfg.size());
			//���÷���ѭ������Layer���� ����ӵ�layers������
			for(LayerConfig layerCfg:layersCfg)
			{
				//��������
				Class<?> c =Class.forName(layerCfg.getClassName());
				//�����Ĺ��췽��
				Constructor<?> ctr =c.getConstructor(int.class,int.class,int.class,int.class);
				//����Layer����
				Layer layer=(Layer)ctr.newInstance(layerCfg.getX(),layerCfg.getY(),layerCfg.getW(),layerCfg.getH());
				//��Lay������ӵ�layers������
				layer.setDto(this.dto);
				layers.add(layer);	
			} 
		}catch (Exception e)
			{
				e.printStackTrace();
			}
	}
	/**
	 * ���ƴ��ڲ�
	 */
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		//������Ϸ����
		for(int i=0;i<layers.size();layers.get(i++).paint(g));
		//��õ�ǰ���ڵĽ���
		this.requestFocus();

	}
	public JButton getStart()
	{
		return start;
	}
	
}
