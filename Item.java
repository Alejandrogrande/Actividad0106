
/**
 * Objetos que te puedes encontrar dentro de las habitaciones de la mazmorra
 * 
 * @AlejandroGrande  
 * @1.0 -- 07/05/2014
 */
public class Item
{
    // instance variables - replace the example below with your own
    private String description; //descripcion del item
    private double weight; //peso en kilogramos

    /**
     * Constructor for objects of class Item
     * @param description the item's description
     * @param weight the item weight
     */
    public Item(String description,double weight)
    {
        this.description = description;
        this.weight = weight;
    }
    
    /**
     * get long description of item
     */
    public String getLongDescription(){
        return description + " (" + weight + " kg.)";
    }
}