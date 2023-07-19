package cc.robotdreams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.PrintStream;
import java.util.stream.Collectors;


public class Garage
{
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Static
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    static final private String CONST_GREEN = "green";
    static final private String CONST_CAR= "car";
    static final private String CONST_BICYCLE = "bicycle";

    static private int counter = 0;

    static public Garage create(byte[] name) {
        return new Garage(Arrays.toString(name));

    }static public Garage create(String name) {
        return new Garage(name);
    }

    static public int getCount() {
        return counter;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Object
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    final public int number;
    final public String name;
    final private List<Vehicle> items = new ArrayList<> ();

    /////////////////////////// OVERLOADING AT LEVEL CONSTRUCTORS ///////////////////////////////////////////////////
    public  Garage()                        // VOID constructor invokes constructor
    {
        this("Default");
    }

    public Garage(char[] name){             // constructor - class initialization char array
        this(new String(name));
    }

    public Garage(String name) {            // constructor - class initialization
        System.out.println("Create garage object \"" + name + "\"");
        this.name = name;
        this.number = ++ Garage.counter;
    }

    /////////////////////////// OVERLOADING AT LEVEL METHODS ///////////////////////////////////////////////////
    protected Garage add(char[] itemName) {   // method
                                              // signature here is add(char[])
        // this.items.add(new String(itemName)); // - transforms from char array to String and places to itemName
        //return this;
        return add(new String(itemName));        // - this time invokes method add and places to itemName

    }protected Garage add(String itemName) {   // method
        this.items.add(new Vehicle(itemName));              // signature here is add(String)
        return this;                           // this is Object of this class
    }

    //final public List<String> getItems() {    // returns "this.items" => with [Plane]
    //    return this.items;
    //}

    public List<String> getItems() {
        //return new ArrayList<>(this.items);     // returns not "this.items", but its copy => without [Plane]
        return this.items.stream().map( i -> i.name).collect(Collectors.toList());
    }


    //public List<String> getOnlyGreen(){
    //    return this.items.stream().filter(item -> item.toLowerCase().contains("green")).collect(Collectors.toList());
    //}
    // but:
    public List<String> filterItems(String textContains) {
        return this.getItems().stream().filter(
                item -> item.toLowerCase().contains(textContains)
        ).collect(Collectors.toList());
    }
    // so public List<String> getOnlyGreen() can be replaced with:
    public List<String> getOnlyGreen() {
        return this.filterItems(CONST_GREEN);
    }
    public List<String> getOnlyCars() {
        return this.filterItems(CONST_CAR);
    }
    public List<String> getOnlyBicycles() {
        return this.filterItems(CONST_BICYCLE);
    }

    /////////////////////////// OVERRIDING AT LEVEL METHODS ///////////////////////////////////////////////////
    @Override
    public String toString() {
        return this.getClass().getName() + " number " + this.number + " < " + this.name + " > " + this.getItems();
    }

}
