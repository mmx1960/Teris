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
		players.add(new Player("小明",100));
		players.add(new Player("小明",1000));
//		players.add(new Player("小明",10000));
//		players.add(new Player("小明",15000));
		players.add(new Player("小明",30000));
		return players;
	}
	@Override
	public void saveData(Player pla)
	{
		// TODO 
		System.out.println("");
		
	}
}
