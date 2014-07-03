package com.gpl.rpg.GotandaRPG.model.map;

import com.gpl.rpg.GotandaRPG.util.Coord;
import com.gpl.rpg.GotandaRPG.util.Size;

public final class MapLayer {
	public final int[][] tiles;

	public MapLayer(Size size) {
		tiles = new int[size.width][size.height];
	}
	public void setTile(int type, int x, int y) {
		tiles[x][y] = type;
	}
}
