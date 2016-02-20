package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dto.Player;

public class DataBase implements Data
{
	private  final String dbUrl;
	private  final String dbUser;
	private  final String dbPwd;
	private static final String LOAD_SQL="SELECT TOP 5 user_name,points FROM user_points WHERE game_type=1 ORDER BY points DESC";
	private static final String SAVE_SQL="INSERT INTO user_points(user_name,points,game_type)VALUES(?,?,?)";
	
	public DataBase(HashMap<String,String> param)
	{
		dbUrl=param.get("dbUrl");
		dbUser=param.get("dbUser");
		dbPwd=param.get("dbPwd");
		try
		{
			Class.forName(param.get("driver"));
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
		
	}
	
	@Override
	public List<Player> loadData()
	{
		List<Player> players=new ArrayList<Player>();
		Connection conn=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try
		{
			conn=DriverManager.getConnection(dbUrl, dbUser, dbPwd);
			stmt=conn.prepareStatement(LOAD_SQL);
			rs=stmt.executeQuery();
			while(rs.next())
			{
				players.add(new Player(rs.getString(1),rs.getInt(2)));
				
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			
			try
			{
				conn.close();
				stmt.close();
				rs.close();
			} catch (SQLException e)
			{
				e.printStackTrace();
			}
			
		}
		return players;
	}

	@Override
	public void saveData(Player pla)
	{
		Connection conn=null;
		PreparedStatement stmt=null;
		try
		{
			conn=DriverManager.getConnection(dbUrl, dbUser, dbPwd);
			stmt=conn.prepareStatement(SAVE_SQL);
			stmt.setObject(1, pla.name);
			stmt.setObject(2, pla.points);
			stmt.setObject(3, 1);
			stmt.execute();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			
			try
			{
				conn.close();
				stmt.close();
			} catch (SQLException e)
			{
				e.printStackTrace();
			}
			
		}
	}

}
