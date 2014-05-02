import java.util.HashMap;
/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 * @author  Michael KÃ¶lling and David J. Barnes
 * @version 2011.07.31
 */
public class Room 
{
    private String description;
    //Se sustituye los 6 atributos por un solo HashMap para 
    private HashMap<String, Room> exits;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<>(); //Se inicializa el HashMap
        //Notacion diamante para no especificar el tipo de objeto que se utiliza
    }
    
    /**
     * Define an exit from this room.
     * @param direction The direction of the exit.
     * @param neighbor The room in the given direction.
     */
    public void setExit(String direction, Room nextRoom)
    {
            exits.put(direction, nextRoom);
    }
    
    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * getExit que tome como parámetro una cadena que represente una dirección
     * y devuelva el objeto de la clase Room asociado a esa salida o null si no hay salida.
     */
    public Room getExit(String direction){
        Room nextRoom = null;
        if(direction.equals("north")) {
            nextRoom = exits.get("north");
        }
        if(direction.equals("east")) {
            nextRoom = exits.get("east");
        }
        if(direction.equals("south")) {
            nextRoom = exits.get("south");
        }
        if(direction.equals("west")) {
            nextRoom = exits.get("west");
        }
        if(direction.equals("southEast")) {
            nextRoom = exits.get("southEast");
        }
        if(direction.equals("northWest")) {
            nextRoom = exits.get("northWest");
        }
        return nextRoom;
    }

    /**
     * Return a description of the room's exits.
     * For example: "Exits: north east west"
     *
     * @ return A description of the available exits.
     */
    public String getExitString(){
        String exitDescription = "Exits: ";
        if(exits.get("north") != null) {
            exitDescription += "north ";
        }
        if(exits.get("east") != null) {
            exitDescription += "east ";
        }
        if(exits.get("south") != null) {
            exitDescription += "south ";
        }
        if(exits.get("west") != null) {
            exitDescription += "west ";
        }
        if(exits.get("southEast") != null) {
            exitDescription += "southEast ";
        }
        if(exits.get("northWest") !=null) {
            exitDescription += "northWest ";
        }
        return exitDescription;
    }

    
}
