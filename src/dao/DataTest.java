package dao;

import java.util.ArrayList;
import java.util.List;

import dto.Player;

public class DataTest implements Data
{
	@Override
	public List<Player> loadData()
	{
		List<Player> players =new ArrayList<Player>();
		players.add(new Player("С��",100));
		players.add(new Player("С��",1000));
//		players.add(new Player("С��",10000));
//		players.add(new Player("С��",15000));
		players.add(new Player("С��",30000));
		return players;
	}
	@Override
	public void saveData(Player pla)
	{
		// TODO 
		System.out.println("");
		
	}
}
