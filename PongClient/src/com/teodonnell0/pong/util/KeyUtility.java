package com.teodonnell0.pong.util;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

public class KeyUtility {

	public enum Key { UP_ARROW, DOWN_ARROW, LEFT_ARROW, RIGHT_ARROW }
	
	private final static Map<Key, Boolean> keyState = new HashMap<>();
	private final static Map<Key, Boolean> previousKeyState = new HashMap<>();
	
	static {
		for(Key key : Key.values()) {
			keyState.put(key, false);
			previousKeyState.put(key, false);
		}
	}
	
	
	public static void keySet(int keyEvent, boolean b) {
		switch (keyEvent) {
		case KeyEvent.VK_UP:
			keyState.put(Key.UP_ARROW, b);
			break;
		case KeyEvent.VK_DOWN:
			keyState.put(Key.DOWN_ARROW, b);
			break;
		case KeyEvent.VK_LEFT:
			keyState.put(Key.LEFT_ARROW, b);
			break;
		case KeyEvent.VK_RIGHT:
			keyState.put(Key.RIGHT_ARROW, b);
			break;
		default:
			break;
		}
	}
	
	public static void update() {
		for(Key key : Key.values()) {
			previousKeyState.put(key, keyState.get(key));
		}

	}
	
	public static boolean isPressed(Key key) {
		return keyState.get(key) && !previousKeyState.get(key);
	}
	
	public static boolean isAnyKeyPressed() {
		for(Key key : Key.values()) {
			if(keyState.get(key) && !previousKeyState.get(key)) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean isDown(Key key) {
		return keyState.get(key);
	}
	
	public static boolean isAnyKeyDown() {
		for(Key key : Key.values()) {
			if(keyState.get(key)) {
				return true;
			}
		}
		return false;
	}
}
