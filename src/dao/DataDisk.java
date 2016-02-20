package dao;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import config.GameConfig;
import dto.Player;

public class DataDisk implements Data
{
	private final String path;
	public DataDisk(HashMap<String,String> param)
	{
		path=param.get("path");
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Player> loadData()
	{
		ObjectInputStream ois=null;
		List<Player> players=null;
		try
		{
		    ois=new ObjectInputStream(new FileInputStream(path));
			players=(List<Player>)ois.readObject();
			
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				ois.close();
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		return players;
	}

	@Override
	public void saveData(Player pla)
	{
		DataDisk dd=new DataDisk(GameConfig.getDataConfig().getDataB().getParam());
		List<Player> players=new ArrayList<Player>();
		players=dd.loadData();
		players.add(pla);
		ObjectOutputStream oos=null;
		try
		{
			oos=new ObjectOutputStream(new FileOutputStream(path));
			oos.writeObject(players);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				oos.close();
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}


}
