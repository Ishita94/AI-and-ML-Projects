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
import java.util.Scanner;

/**
 * An example class implementing Agent class for human players. 
 * The implementation is coupled with the actual game (here, Reversitic) the agent is playing.
 * @author Azad
 *
 */
public class HumanTTTAgent extends Agent
{

	static Scanner in = new Scanner(System.in);
       
        
	public HumanTTTAgent(String name) {
		super(name);
                //buzz=0;
		// TODO Auto-generated constructor stub
	}
        @Override
        public void setbuzz(int r,int c)
        {
            b.row=r;
            b.col=c;
            System.out.println("h set korsi"+b.row+b.col);
        }
        @Override
        public int checkbuzz()
        {
            if(b.col==-1 && b.row==-1) 
            {
               // System.out.println();
                try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
                //int r=b.row;
                //int c=b.col;
                return 0;
            }
            else 
            {
                System.out.println("h check hoise");
                return 1;
            }
        }


	@Override
	public void makeMove(Game game) {
		// TODO Auto-generated method stub
		
		int row,col;
		Reversitic tttGame = (Reversitic) game;
		
		
                                
		while(true)
                {
                    //System.out.println("ghurtesi");
                    int a=this.checkbuzz();
                    if(a==1) break;
                    
                }
                
		row=b.row;
                col=b.col;
		tttGame.board[row][col] = role;
                b.row=-1;
                b.col=-1;
                System.out.println("h r set korsi");
	}


	

}

