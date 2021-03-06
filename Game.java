import java.util.Stack;
/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
    private Stack <Room>  visitedRooms;
    
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     * Mazmorra para que el heroe derrote al mounstruo
     */
    private void createRooms()
    {
        Room entrance,foyer,skeleton,itemroom,lizard,subboss,boss;

        // create the rooms
        entrance = new Room("outside the main entrance of the dungeon");
        foyer = new Room("in a foyer");
        skeleton = new Room("in the skeletons room");
        itemroom = new Room("in a special item's room");
        lizard = new Room("in the lizard's room");
        subboss = new Room("in the subboss's room");
        boss = new Room("in the boss's room, Prepare for the battle");

        // initialise room exits
        //Al haber cambiado el metodo setExit ahora se tienen que especificar cada salida
        //en cada habitacion
        entrance.setExit("north", foyer);

        foyer.setExit("south",entrance);
        foyer.setExit("west",skeleton);
        foyer.setExit("southEast",lizard);

        skeleton.setExit("east",foyer);
        skeleton.setExit("west",itemroom);

        itemroom.setExit("east", skeleton);

        lizard.setExit("northWest",foyer);
        lizard.setExit("north",subboss);

        subboss.setExit("south",lizard);
        subboss.setExit("west", boss);

        boss.setExit("east",subboss);

        entrance.addItem(new Item("candy",0.5));
        foyer.addItem(new Item( "leaf of paper",0.1));
        skeleton.addItem(new Item("skeleton bone",2.0));
        itemroom.addItem(new Item( "weapon demon's slayer",3.0));
        lizard.addItem(new Item("scales",0.5));
        subboss.addItem(new Item("subboss head",5.0));
        boss.addItem(new Item( "treasure",5.0));

        currentRoom = entrance;  // start game outside
        visitedRooms = new Stack<>();
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.

        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println("Type 'look' to look current Position");
        System.out.println();
        printLocationInfo();
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("go")) {
            goRoom(command);
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }
        else if(commandWord.equals("look")){
            printLocationInfo();
        }
        else if (commandWord.equals("back")){
            back();
        }
        else if(commandWord.equals("eat")){
            eat();
        }

        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.imprCommands();
    }

    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = null;
        nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            visitedRooms.push(currentRoom);
            currentRoom = nextRoom;
            printLocationInfo();
        }
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }

    /**
     * metodo para expresar en el lugar que se esta en un determinado momento
     */
    private void printLocationInfo(){
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * El personaje come
     */
    private void eat(){
        System.out.println("You have eaten now and you are not hungry any more");
    }
    
    /**
     * El personaje retrocede a la anterior habitacion
     */
    private void back(){
        if(!visitedRooms.empty()){
        currentRoom = visitedRooms.pop();
        printLocationInfo();
    }   
    else{
        System.out.println("You are in the begining of the dungeon");
    }
    }
}
