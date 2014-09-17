package client.model;

import java.util.ArrayList;

import shared.definitions.DevCardType;
/**
 * 
 * @author Epper Marshall
 *
 */
public class Player {
	private int cities;
	private int settlements;
	private int roads;
	private String color;
	private boolean discarded;
	private int monuments;
	private String name;
	private ArrayList<DevCardType> newDevCards;
	private ArrayList<DevCardType> oldDevCards;
}
