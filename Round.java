
public class Round 
  {
    int numberOfRounds;
    int curRound = 0;
    int firstItem;
    int secondItem;
    int rockWin = 0;
    int paperWin = 0;
    int scissorWin = 0;
    int drawCount = 0;
    int playerCount = 2;
    int curPlayer = 0;
    int winnerIndex = -1;

    public Round(int numberOfRounds)
    {
        this.numberOfRounds = numberOfRounds;
    }

    public int getNumberOfRounds()
    {
        return numberOfRounds;
    }

    public synchronized void playRound(int id, int itemIndex)
    {
        curPlayer++;
        if(id == 1)
        {
            firstItem = itemIndex;
        }
        else
        {
            secondItem = itemIndex;
        }

        if(curPlayer < playerCount)
        {
            try
            {
                wait();
            }
            catch(InterruptedException e) {}
        }
        else
        {
            notifyAll();
            checkWin();
            displayResults(id);
            winnerIndex = -1;
            curPlayer = 0;
            curRound++;
        }
    }

    public void printStats()
    {
        System.out.println("Summary Statistics:");
        System.out.println("Number of draws: " + drawCount);
        System.out.println("Number of times rock won: " + rockWin);
        System.out.println("Number of times paper won: " + paperWin);
        System.out.println("Number of times scissors won: " + scissorWin);
    }

    private void checkWin()
    {
        if(firstItem == secondItem)
        {
            drawCount++;
            winnerIndex = -1;
        }
        else if(firstItem + secondItem == 1)
        {
            paperWin++;
            if(firstItem == 1)
                winnerIndex = 1;
            else
                winnerIndex = 2;
        }
        else if(firstItem + secondItem == 2)
        {
            rockWin++;
            if(firstItem == 0)
                winnerIndex = 1;
            else
                winnerIndex = 2;
        }
        else if(firstItem + secondItem == 3)
        {
            scissorWin++;
            if(firstItem == 2)
                winnerIndex = 1;
            else
                winnerIndex = 2;
        }
    }

    private void displayResults(int id)
    {
        if(winnerIndex == -1)
        {
            System.out.println("Round: " + (this.curRound + 1) + " The round was a draw");
        }
        else
        {
            if(winnerIndex == 1)
            {
                System.out.println("Round: " + (this.curRound + 1) + " Player" +  (winnerIndex + 1) + " Loses");
                System.out.println("Round: " + (this.curRound + 1) + " Player" + winnerIndex + " Wins");

            }
            else
            {
                System.out.println("Round: " + (this.curRound + 1) + " Player" + winnerIndex + " Wins");
                System.out.println("Round: " + (this.curRound + 1) + " Player" + (winnerIndex - 1) + " Loses");
            }
        }
    }
}
