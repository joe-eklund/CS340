package server.games;

import java.util.ArrayList;

import shared.locations.HexLocation;
import shared.model.Hex;
import shared.model.Port;

final class ModelDefaults {
	@SuppressWarnings("serial")
	public static final ArrayList<String> VALID_COLORS = new ArrayList<String>(9) {
		{
			add("red");
			add("green");
			add("blue");
			add("yellow");
			add("puce");
			add("brown");
			add("white");
			add("purple");
			add("orange");
		}
	};

	@SuppressWarnings("serial")
	public static final ArrayList<Hex> DEFAULT_HEXES = new ArrayList<Hex>() {
		{
			Hex desertHex = new Hex(new HexLocation(0, -2));
			desertHex.setChit(-1);
			add(desertHex);
			add(new Hex(1, -2, "brick", 4));
			add(new Hex(2, -2, "wood", 11));
			add(new Hex(-1, -1, "brick", 8));
			add(new Hex(0, -1, "wood", 3));
			add(new Hex(1, -1, "ore", 6));
			add(new Hex(2, -1, "sheep", 12));
			add(new Hex(-2, 0, "ore", 5));
			add(new Hex(-1, 0, "sheep", 10));
			add(new Hex(0, 0, "wheat", 11));
			add(new Hex(1, 0, "brick", 5));
			add(new Hex(2, 0, "wheat", 9));
			add(new Hex(-2, 1, "wheat", 2));
			add(new Hex(-1, 1, "sheep", 9));
			add(new Hex(0, 1, "wood", 4));
			add(new Hex(1, 1, "sheep", 10));
			add(new Hex(-2, 2, "wood", 6));
			add(new Hex(-1, 2, "ore", 3));
			add(new Hex(0, 2, "wheat", 8));
		}
	};

	@SuppressWarnings("serial")
	public static final ArrayList<Integer> CHIT_VALUES = new ArrayList<Integer>() {
		{
			add(4);
			add(11);
			add(8);
			add(3);
			add(6);
			add(12);
			add(5);
			add(10);
			add(11);
			add(5);
			add(9);
			add(2);
			add(9);
			add(4);
			add(10);
			add(6);
			add(3);
			add(8);
		}
	};

	@SuppressWarnings("serial")
	public static final ArrayList<String> RESOURCE_NAMES = new ArrayList<String>() {
		{
			add("desert");
			add("brick");
			add("wood");
			add("brick");
			add("wood");
			add("ore");
			add("sheep");
			add("ore");
			add("sheep");
			add("wheat");
			add("brick");
			add("wheat");
			add("wheat");
			add("sheep");
			add("wood");
			add("sheep");
			add("wood");
			add("ore");
			add("wheat");
		}
	};

	@SuppressWarnings("serial")
	public static final ArrayList<Port> DEFAULT_PORTS = new ArrayList<Port>() {
		{
			add(new Port("ore", 1, -3, "S", 2));
			add(new Port(null, 3, -3, "SW", 3));
			add(new Port("wood", -3, 2, "NE", 2));
			add(new Port(null, 2, 1, "NW", 3));
			add(new Port(null, 0, 3, "N", 3));
			add(new Port(null, -3, 0, "SE", 3));
			add(new Port("sheep", 3, -1, "NW", 2));
			add(new Port("wheat", -1, -2, "S", 2));
			add(new Port("brick", -2, 3, "NE", 2));
		}
	};
}
