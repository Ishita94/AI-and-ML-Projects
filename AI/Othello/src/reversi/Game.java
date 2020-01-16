/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reversi;

/**
 *
 * @author User
 */
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 * Extend this abstract class for your game implementation
 * @author Azad
 *
 */
public abstract class Game 
{
	Agent agent[]; // Array containing all the agents, here we only consider two player games.
	String name = "A Generic Game"; //A name for the Game object, it will be changed by the actual game class extending it
	
	Random random = new Random();
        Reversi main;
        int turn;
	Agent winner = null; // The winning agent will be saved here after the game compeltes, if null the game is drawn.
	ImageIcon onepic,twopic;
	
	public Game(Agent a, Agent b,Reversi J) 
	{
		// TODO Auto-generated constructor stub
		agent = new Agent[2];
		agent[0] = a;
		agent[1] = b;
                main=J;
		
	}
	
	/**
	 * The actual game loop, each player takes turn.
	 * The first player is selected randomly
	 */
	public void play()
	{
		updateMessage("Starting " + name + " between "+ agent[0].name+ " and "+ agent[1].name+".");
		 turn = random.nextInt(2);
                 int mor=0,shesh=0;
		if(agent[turn].name.compareTo("Human")==0)
                {
                    //System.out.println("ha");
                     main.label3.setText("Human");
                     agent[turn].color="black";
                    
                     onepic=new ImageIcon(this.getClass().getResource("black.jpg"));
                     twopic=new ImageIcon(this.getClass().getResource("white.jpg"));
                     main.one.setIcon(onepic);
                     main.label4.setText("Machine");
                     main.two.setIcon(twopic);
                     
                }
                else
                {
                    
                     agent[turn].color="black";
                     onepic=new ImageIcon(this.getClass().getResource("black.jpg"));
                     twopic=new ImageIcon(this.getClass().getResource("white.jpg"));
                     main.one.setIcon(onepic);
                     int t=(turn+1)%2;
                     if(agent[t].name.compareTo("Human")==0)
                     {
                           main.label3.setText("Machine");
                        main.label4.setText("Human");
                     }
                     else 
                     {
                         mor=1;
                         main.label3.setText("Machine1");
                         main.label4.setText("Machine2");
                     }
                     main.two.setIcon(twopic);
                }
                 if(turn==0)agent[1].color="white"; 
                     else agent[0].color="white";
		//System.out.println(agent[turn].name+ " makes the first move.");
		initialize(false);
		updatefirst(agent[0].color);
                scoreupdate();
                showGameState();
		while(1==1)
		{
                     System.out.println("turn="+turn);
                     if(mor==0)
			updateMessage(agent[turn].name+ "'s turn. ");
                     else updateMessage(agent[turn].name+(turn+1) +"'s turn. ");
                        int m=checkgamestate(turn,null);
                        int turn2=(turn+1)%2;
                        int n=checkgamestate(turn2,null);
                        if(m==1)
                        {
                            agent[turn].makeMove(this); 
                            showGameState();

                            try {
                            Thread.sleep(200);
                            } catch (InterruptedException f) {
                            // TODO Auto-generated catch block
                            f.printStackTrace();
                            }
                        }
                        else if(m==0 && n==0)
                        {
                            System.out.println("Game over!");
                           shesh=1;
                            break;
                        }
                        scoreupdate();
			turn = (turn+1)%2;
                        
		}
		
		/*if (winner != null)
			updateMessage(winner.name+ " wins!!!");
		else	
			updateMessage("Game drawn!!");
                
		*/
                 scoreupdate();
                 int c0=agent[0].score;
                 int c1=agent[1].score;
                 if(c0>c1) 
                 {
                     if(mor==0)
                     {
                         updateMessage("Human won!");
                         main.label7.setText("Human won!");
                     }
                     else
                     {
                         updateMessage("Machine1 won!");
                         main.label7.setText("Machine1 won!");
                     }
                         
                 }
                 else if(c0<c1) 
                 {
                     if(mor==1)
                     {
                         updateMessage("Machine2 won!");
                         main.label7.setText("Machine2 won!");
                     }
                     else  
                     {
                         updateMessage("Machine won!");
                         main.label7.setText("Machine won!");
                     }
                 }
                 else 
                 {
                     updateMessage("It's a draw!");
                     main.label7.setText("It's a draw!");
                 }
                 
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String str = "";
		return str;
	}
	
	/**
	 * 
	 * @return Returns true if the game has finished. Also must update the winner member variable.
	 */
	abstract boolean isFinished();
	
	/**
	 * Initializes the game as needed. If the game starts with different initial configurations, it can be read from file.
	 * @param fromFile if true loads the initial state from file. 
	 */
	abstract void initialize(boolean fromFile);
        abstract void scoreupdate();
        
        abstract void updatefirst(String color);
        abstract int checkgamestate(int turn,int [][]board);
	
	/**
	 * Prints the game state in console, or show it in the GUI
	 */
	abstract void showGameState();
	
	/**
	 * Shows game messages in console, or in the GUI
	 */
	abstract void updateMessage(String msg);
       
}

