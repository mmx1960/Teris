package control;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PlayerControl extends KeyAdapter
{
	GameControl gameControl=null;

	public PlayerControl(GameControl gameControl)
	{
		this.gameControl=gameControl;
	}
	@Override
	public void keyPressed(KeyEvent e)
	{
		switch (e.getKeyCode())
		{
		case KeyEvent.VK_W:
			this.gameControl.keyUp();
			break;
		case KeyEvent.VK_S:
			this.gameControl.keyDown();
			break;
		case KeyEvent.VK_A:
			this.gameControl.keyLeft();
			break;
		case KeyEvent.VK_D:
			this.gameControl.keyRight();
			break;
		case KeyEvent.VK_T:
			this.gameControl.test();
			break;
		default:
			break;
		}
		
		
		
	}
	
}
