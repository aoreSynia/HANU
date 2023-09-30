package Tut2;

public class TestCodeWeek2 {

    // hàm main bên dưới là để test code Ex6 week 2 
    
    public static void main(String[] args) throws InterruptedException {
        IronSuit ironSuit = new IronSuit("IronMan Suit", 2.5, 1.5, 3.0, 1500, 1, "IS123", 1000);

        System.out.println("1st flight");
        ironSuit.setOccupied(false);
        ironSuit.fly("New York", "Los Angeles", true); // Using progress bar

        System.out.println(" ");
        Thread.sleep(300);

        System.out.println("2nd flight");
        ironSuit.setOccupied(false);
        ironSuit.fly("Los Angeles", "San Francisco", false); // Static message
        
        System.out.println(" ");
        Thread.sleep(300);

        System.out.println("3nd flight");
        ironSuit.setOccupied(true); // Isoccupied 
        ironSuit.fly("Los Angeles", "San Francisco", false); // Static message
    }

    // hàm main bên dưới để test IntBag

    // public static void main(String[] args) {
    //     IntSet intSet = new IntSet();
    //     intSet.insert(5);
    //     intSet.insert(10);
    //     intSet.remove(5);
    //     System.out.println("IntSet size: " + intSet.size());

    //     IntBag intBag = new IntBag();
    //     intBag.insert(5);
    //     intBag.insertMultiple(10, 3); // Inserting 10 three times
    //     System.out.println("IntBag size: " + intBag.size());
    // }




// dấu } bên dưới k được xoá =))
}
