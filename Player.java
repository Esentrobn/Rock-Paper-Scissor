
import java.lang.Thread;
import java.lang.String;
import java.lang.Math;
public class Player extends Thread {
    int id;
    Round round;
    int itemIndex;
    String[] values = {"Rock", "Paper", "Scissors"};

    public Player(int id, Round round) {
        this.id = id;
        this.round = round;
    }


    @Override
    public void run() {
        for (int i = 1; i <= round.getNumberOfRounds(); i++) {
            itemIndex = (int) (Math.random() * 3);
            System.out.println("Round: " + i + " Player" + id + ": selects " + values[itemIndex]);
            round.playRound(id, itemIndex);
        }
    }
}
