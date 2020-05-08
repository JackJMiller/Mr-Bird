package com.jackjmiller.mrbird.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.jackjmiller.mrbird.MrBird;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = (int) MrBird.WIDTH * 4;
		config.height = (int) MrBird.HEIGHT * 4;
		config.title = "Mr Bird";
		new LwjglApplication(new MrBird(), config);
	}
}
