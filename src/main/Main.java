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
		//������Ϸ����Դ
		GameDto dto =new GameDto();
		//������Ϸ���
		JPanelGame jPanelGame =new JPanelGame(dto);
		//������Ϸ�߼�ģ�飨��������Դ��
		GameService gameService =new GameService(dto);
		//������Ϸ����ģ��(������壬�߼�ģ��)
		GameControl gameControl =new GameControl(jPanelGame,gameService);
		//������Ϸ������
		jPanelGame.setGameControl(gameControl);
		//������ҿ�����(������Ϸ���ƿ�)
		PlayerControl playerControl =new PlayerControl(gameControl);
		//������ҿ�����
		jPanelGame.setGameControl(playerControl);
		//��װ��Ϸ���
		new JFrameGame(jPanelGame);
	}
}
