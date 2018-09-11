
public class RPS
 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int numberOfRounds = 10000;
        Round round = new Round(numberOfRounds);

        Player first = new Player(1,round);
        Player second = new Player(2,round);

        first.start();
        second.start();

        try
        {
            first.join();
            second.join();
        } catch(InterruptedException e) {}
        System.out.println("\n");
        round.printStats();
    }

}
