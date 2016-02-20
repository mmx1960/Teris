package main;

import service.GameService;
import ui.JFrameGame;
import ui.JPanelGame;
import control.GameControl;
import control.PlayerControl;
import dto.GameDto;

public class Main
{
	public static void main(String[] args)
	{
		//创建游戏数据源
		GameDto dto =new GameDto();
		//创建游戏面板
		JPanelGame jPanelGame =new JPanelGame(dto);
		//创建游戏逻辑模块（连接数据源）
		GameService gameService =new GameService(dto);
		//创建游戏控制模块(连接面板，逻辑模块)
		GameControl gameControl =new GameControl(jPanelGame,gameService);
		//连接游戏控制器
		jPanelGame.setGameControl(gameControl);
		//创建玩家控制器(连接游戏控制块)
		PlayerControl playerControl =new PlayerControl(gameControl);
		//连接玩家控制器
		jPanelGame.setGameControl(playerControl);
		//安装游戏面板
		new JFrameGame(jPanelGame);
	}
}
