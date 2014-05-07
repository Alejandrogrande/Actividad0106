import java.util.HashMap;
import java.util.Set;
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
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */
public class Room 
{
    private String description;
    //Se sustituye los 6 atributos por un solo HashMap para 
    private HashMap<String, Room> exits;
    private String itemDescription;
    private double itemWeight;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description, String itemDescription,double itemWeight) 
    {
        this.description = description;
        exits = new HashMap<>(); //Se inicializa el HashMap
        //Notacion diamante para no especificar el tipo de objeto que se utiliza
        this.itemDescription = itemDescription;
        this.itemWeight = itemWeight;
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
     * getExit que tome como par�metro una cadena que represente una direcci�n
     * y devuelva el objeto de la clase Room asociado a esa salida o null si no hay salida.
     */
    public Room getExit(String direction){
        return exits.get(direction);
    }

    /**
     * Return a description of the room's exits.
     * For example: "Exits: north east west"
     *
     * @ return A description of the available exits.
     */
    public String getExitString(){
        Set<String> namesOfDirections = exits.keySet();
        String exitsDescription = "Exit: ";
        for(String direction : namesOfDirections){
            exitsDescription += direction + " ";
        }

        return exitsDescription;
    }

    /**
     * Return a long description of this room, of the form:
     *     You are in the 'name of room'
     *     Exits: north west southwest
     * @return A description of the room, including exits.
     */
    public String getLongDescription(){
        String longDescription = "You are in the " + getDescription() +"\n"+ getExitString()+"\n" ;
        longDescription += "There is 1 item:\n";
        longDescription += " - " + itemDescription + " (" + itemWeight + " kg. )";
        return longDescription;
    }

}
