// Name:  Don Byrkett
// Instructor:  Dr. Byrkett
// CSE 174, Section D and E
// Date:  March 26, 2013
// Filename:  Truck.java
// Description:  Describe the movements of a truck
import java.awt.Point;
public class Truck {
   /** 
    * Instance variables for each truck
    * coord = coordinate location of truck
    * capacity = capacity of truck
    * distance = distance travelled by truck
    * currentLoad = tonnage currently on truck
    * tonsDelivered = total tonnage delivered
    */
   private Point coord;        // Current location of truck
   private int capacity;       // Capacity of truck in tons
   private int distance;       // Total miles traveled
   private int currentLoad;    // Tons currently on truck
   private int tonsDelivered;  // Total tons delivered
   /**
    * Constructs a new empty truck with the given capacity
    * at coordinates (0,0) with total miles travelled of zero
    * @param cap capacity of truck
    */
   public Truck(int cap) {
      capacity = cap;
      coord = new Point();
      distance = 0;
      currentLoad = 0;
      tonsDelivered = 0;
   }
   /** 
    * Move truck to a new location and update distance travelled 
    * @param dest destination of truck as a Point
    */
   public void move(Point dest) {
      distance = distance + 
         (int)Math.sqrt(Math.pow(coord.getX()-dest.getX(), 2.) +
         Math.pow(coord.getY()-dest.getY(), 2.));
      coord.setLocation(dest);                        
   }
   /**
    * Send truck home to location (0, 0) and update distance
    * travelled.
    */
   public void home() {
      move(new Point());
   }
   /** 
    * Load the number of tons specified in the parameter onto
    * the truck.  If the tons specified will cause the capacity
    * to be exceeded, only load up to the truck capacity.
    * @return the actual tons loaded
    */
   public int load(int tons) {
      int amountLoaded = tons;
      if (tons + currentLoad >= capacity) {
         amountLoaded = capacity-currentLoad;
         currentLoad = capacity;
      }
      else {
         currentLoad = currentLoad+tons;
      }
      return amountLoaded;
   }
   /**
    * Dump the current load on the truck and update total tons
    * delivered.
    * @return the amount dumped
    */
   public int dump() {
      int delivered = currentLoad;
      currentLoad = 0;
      tonsDelivered = tonsDelivered + delivered;
      return delivered;
   }
   /**
    * Obtain the total tons delivered by the truck
    * @return total tons delivered
    */
   public int getTotalDeliveries() {
      return tonsDelivered; 
   }
   /**
    * Obtain the total miles travelled by the truck
    * @return total miles travelled
    */
   public int getMilesTravelled() {
      return distance;
   }
   /**
    * Obtain the current state of the truck as a String
    * @return location and load on truck
    */
   public String toString() {
      String s = "(" + (int) coord.getX() + ", " + (int) coord.getY() +
         "), " + currentLoad + " tons";
      return s;
   }
}
