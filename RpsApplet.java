//package RockPaperScissor;
import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import java.awt.Graphics;
import java.text.*;
public class Rpsapplet extends Applet implements ActionListener
{       
 /* The following lines will show or print out the number of wins
             on a graph */

     Graphics Drawing;
     Choice nPlayers, nRounds;
     Button pushKey;
     Image graph;
     
     DecimalFormat decfom = new DecimalFormat("#.##");   

     private double proportion;
     private int numOfRounds, numOfPlayers;
     private int Rockwins,Paperwins, Scissrwins;
     private int draws;


     public void init()
     {
		setBackground ( Color.LIGHT_PURPLE); 
           pushKey = new Button ("Start"); 
		nPlayers = new Choice();
           nRounds = new Choice();
                
           for( int i = 50; i <= 2000; i += 50)
		{
			nRounds.addItem("Simulate " + i +" Rounds");
		}
		             
                for( int i = 2; i <= 10; i++)
		{
			nPlayers.addItem( i + " players");
		}
                
                

                add(pushKey);
                add(nRounds);
		     add(nPlayers);

                                run.addActionListener(this);
		
		
		numOfPlayers = 0; 
           numOfRounds = 0;
           Rockwins = 0;
		Paperwins = 0; 
		Scissrwins = 0; 
           draws = 0;
		proportion = (double)100/numOfRounds;	
                               
               
	}

public void DesignGraph(Round rounds)
{
	Int rockWins,paperWins,scissrWins,drawnum;	
	
     rockWins = (int) (rounds.getRock()-rounds.getRock()*(1- proportion));
	paperWins = (int)(rounds.getPaper()-rounds.getPaper()*(1- proportion));
scissrWins = (int)(rounds.getScissors()-rounds.getScissors()*(1- proportion));
	drawnum = (int)(rounds.getDraws()-rounds.getDraws()*(1- proportion));
		

	     // Draws number of Scissor Wins on the graph

		Design.setColor(Color.YELLOW);
		Design.fillRect(400, 500-scissrWins, 80, scissrWins);

           // Draws number of Paper Wins on the graph
		Design.setColor(Color.GREEN);
		Design.fillRect(300, 500-paperWins, 80, paperWins);

           // Draws number of Rock Wins on the graph
		Design.setColor(Color.BLUE);
		Design.fillRect(200, 500-rockWins, 80, rockWins);

		// Draws number of Draws on the graph
		Design.setColor(Color.RED);
		Design.fillRect(100,500-drawnum,80, drawnum);
		
       	 
		//	designing graph 
		  
	}
	                          
        
	public void update(Graphics Drawing)
	{ 

    Drawing.drawImage(graph, 0, 0, this);}

	
	public void actionPerformed(ActionEvent Actn)
	{
		if(Actn.getSource() == run)
		{
			new Thread(new Runnable()
		     { 
                  public void run()
		     { 
                   start(); 
                }
		    }).start();
		}
	}
	
	public void start()
	{	
           numOfPlayers = (nPlayers.getSelectedIndex()+2);	
		numOfRounds = (nRounds.getSelectedIndex()+1)*50;
                                
		if(numofRounds < 200){
                 
                    proportion = 2; 
            }
		else
                 {  
			proportion = (double)250/numOfRounds;
  		 }

                    Graph();
		
	     Round rounds = new Round(numOfRounds, numOfPlayers);

		player[] List = new player[numOfPlayers];
		for(int j = 0; j < numOfPlayers; j++)
		{   
			List[j] = new player(rounds, i+1);
			List[j].start();
		}		
		
                while(!rounds.check())
		{
			DesignGraph(rounds);
			repaint();
		}
		
           double rockWinTotal, paperWinTotal, scissorWinTotal, drawWinTotal;

           scissorWinTotal = rounds.getScissors();
		paperWinTotal = rounds.getPaper();
           rockWinTotal = rounds.getRock();
		drawWinTotal = rounds.getDraws();


           // for passing the result on to the graph, to get rid off the decimal point *100
	
           double dr, rk, pp, sc;

           sc = (100*( scissorWinTotal /numOfRounds));
           pp = (100*( paperWinTotal /numOfRounds));
           rk = (100*( rockWinTotal  /numOfRounds));
           dr = (100*( drawWinTotal /numOfRounds));
          
                         
           // for Displaying the number of wins on the graph  
            
		Design.setColor(Color.WHITE);

		Design.drawString(decfom.format(sc)+ "%",  390, 400- scissrWins);
           Design.drawString(decfom.format(pp)+ "%",  290, 400-paperWins);
           Design.drawString(decfom.format(rk)+ "%",  180, 400-rockWins);
		Design.drawString(decfom.format(dr)+ "%",   75, 400-drawnum);
		
           
		
		repaint();
		
	}
    
      
  public void Graph()
	  {
		graph = createImage(600,665);
		Drawing = graph.getGraphics();
		
		                
     Design.drawString("************************Rock Paper Scissor GAME" + "*****************************",150, 100);
                
     Design.drawString(" Player1, Player2,  Player3 ",                    
     150, 100);
					  
		
		Design.drawLine(50, 500, 550, 500);
           Design.drawString("Scissors", 400, 500);
           Design.drawString("Paper", 300, 500);
		Design.drawString("Rock", 200, 500);
           Design.drawString("Draws", 80, 500);
		
	  }
}
