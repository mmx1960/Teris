package dao;

import java.util.List;

import dto.Player;

public interface Data
{
	public List<Player> loadData();
	public void saveData(Player pla);
}
