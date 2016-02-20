package dto;

import java.io.Serializable;


public class Player implements Comparable<Player>, Serializable
{
	public String name;
	public int points; 
	public Player(String name,int points)
	{
		this.name=name;
		this.points=points;
	}
	@Override
	public int compareTo(Player pla)
	{
		return pla.points-this.points;
	}
}
