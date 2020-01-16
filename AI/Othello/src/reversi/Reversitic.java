/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reversi;

import javax.swing.JFrame;
import java.util.*;

/**
 *
 * @author User
 */
/**
 * Example class extending Game class
 * @author Azad
 *
 */
public class Reversitic extends Game 
{

	/**
	 * The actual game board
	 * -1 empty, 0 -> O, 1 -> X
	 */
	public int[][] board;
        Reversi main;
	
	/**
	 * First agent starts with O
	 * @param a
	 * @param b
	 */
	public Reversitic(Agent a, Agent b,Reversi J) {
		super(a, b,J);
                main=J;
		// TODO Auto-generated constructor stub
		
		a.setRole(0); // The first argument/agent is always assigned O (0)
		b.setRole(1); // The second argument/agent is always assigned X (1)
					  // NOtice that, here first dows not mean that afent a will make the first move of the game.
					  // Here, first means the first argument of the constructor
					  // Which of a and b will actually give the first move is chosen randomly. See Game class
		
		name = "Othello";
		
		board = new int[8][8];
		
	}

	/**
	 * Called by the play method of Game class. It must update the winner variable. 
	 * In this implementation, it is done inside checkForWin() function.
	 */
	@Override
	boolean isFinished() {
		// TODO Auto-generated method stub
		if(checkForWin() != -1)
			return true;
		else if(isBoardFull())
			return true;
		else return false;
	}

	@Override
	void initialize(boolean fromFile) {
		// TODO Auto-generated method stub
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				board[i][j] = -1;
			}
		}
	}

	/**
	 * Prints the current board (may be replaced/appended with by GUI)
	 */
	@Override
	void showGameState() {
		// TODO Auto-generated method stub
		 
        System.out.println("-------------");
		
        for (int i = 0; i < 8; i++) 
        {
            System.out.print("| ");
            for (int j = 0; j < 8; j++) 
            {
            	if(board[i][j]==-1)
            		System.out.print(" " + " | ");
            	else if	(board[i][j]==0)
            		System.out.print( "0 | ");
            	else
            		System.out.print( "1 | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }
	
    /** Loop through all cells of the board and if one is found to be empty (contains -1) then return false.
    	Otherwise the board is full.
    */
    public boolean isBoardFull() {

		
        for (int i = 0; i < 8; i++) 
        {
            for (int j = 0; j < 8; j++) 
            {
                if (board[i][j] == -1) 
                {
                   return false;
                }
            }
        }
		
        return true;
    }
	
	
    /** Returns role of the winner, if no winner/ still game is going on -1.
     * @return role of the winner, if no winner/ still game is going on -1.
     */
    public int checkForWin() 
    {
    	winner = null;
    	int winRole=-1;
    	//row
        for (int i = 0; i < 3; i++) 
        {
            if (checkRowCol(board[i][0], board[i][1], board[i][2]) == true) 
            {
            	winRole = board[i][0];
            }
        }
        
        //column
        for (int i = 0; i < 3; i++) 
        {
            if (checkRowCol(board[0][i], board[1][i], board[2][i]) == true) 
            {
            	winRole =  board[0][i];
            }
        }
        
        //diagonal
    	if(checkRowCol(board[0][0], board[1][1], board[2][2]))
    		winRole =  board[0][0];
    	
    	if (checkRowCol(board[0][2], board[1][1], board[2][0]))
    		winRole =  board[0][2];

    	if(winRole!=-1)
    	{
    		winner = agent[winRole];
    	}
		return winRole;
    }
	
	
	
    // Check to see if all three values are the same (and not empty) indicating a win.
    private boolean checkRowCol(int c1, int c2, int c3) 
    {
        return ((c1 != -1) && (c1 == c2) && (c2 == c3));
    }

     public int checkrow (int [][]board2,int turn,int row, int col, ArrayList<checkob> candidate)
    {
        int c=0;
        //dec
        int i,count=0;
        i=col-1;
        int other;
        if(turn==0) other=1;
        else other=0;
        //System.out.println("turn1"+turn);
         //System.out.println("rowcol"+row+col);
        while(i>=0)
        {
            //System.out.println("i="+i);
            if((board2[row][i]==-1))  break;
            else
            {
                if(count==0)
                {
                    if(board2[row][i]==turn) break;
                    else 
                    {
                        ++count;
                        //System.out.println("count="+count);
                    }
                
                }
                else
                {
                    if(board2[row][i]==turn)
                    {
                        c=1;
                        checkob newob=new checkob(board2,row,col);
                         newob.setcheckob("r", "dec",count);
                        candidate.add(newob);
                         //System.out.println("r dec");
                        break;
                    }
                    else
                    {
                        ++count;
                         //System.out.println("count="+count);
                    }
                }
            }
               
            --i;
         }
        
       //inc
        i=col+1;
        count=0;
        //System.out.println("turn1"+turn);
        while(i<board2.length)
        {
             //System.out.println("iturn="+i+turn);
              //System.out.println("br="+board2[row][i]);
             if((board2[row][i]==-1))  break;
            else
            {
                if(count==0)
                {
                      
                    if(board2[row][i]==turn) break;
                    else 
                    {
                         ++count;
                         //System.out.println("count="+count);
                    }
                
                }
                else
                {
                    if(board2[row][i]==turn)
                    {
                        c=1;
                        checkob newob=new checkob(board2,row,col);
                         newob.setcheckob("r", "inc",count);
                          //System.out.println("r inc");
                        candidate.add(newob);
                        break;
                    }
                    else 
                    {
                        ++count;
                         //System.out.println("count="+count);
                    }
                }
            }
               
            ++i;
        }
         //System.out.println("c="+c);
        return c;
    }
     
     public int checkcol (int [][]board2,int turn,int row, int col, ArrayList<checkob> candidate)
    {
        int c=0;
        //dec
        int i,count=0;
        i=row-1;
        int other;
        if(turn==0) other=1;
        else other=0;
       // System.out.println("turn1"+turn);
         //System.out.println("rowcol"+row+col);
        while(i>=0)
        {
           // System.out.println("i="+i);
            if((board2[i][col]==-1))  break;
            else
            {
                if(count==0)
                {
                    if(board2[i][col]==turn) break;
                    else 
                    {
                        ++count;
                        //System.out.println("count="+count);
                    }
                
                }
                else
                {
                    if(board2[i][col]==turn)
                    {
                        c=1;
                        checkob newob=new checkob(board2,row,col);
                         newob.setcheckob("c", "dec",count);
                        candidate.add(newob);
                         //System.out.println("c dec");
                        break;
                    }
                    else 
                    {
                        ++count;
                        //System.out.println("count="+count);
                    }
                }
            }
               
            --i;
         }
        
       //inc
        i=row+1;
        count=0;
        //System.out.println("turn1"+turn);
        while(i<board2.length)
        {
            //System.out.println("iturn="+i+turn);
              //System.out.println("br="+board2[i][col]);
             if((board2[i][col]==-1))  break;
            else
            {
                if(count==0)
                {
                    if(board2[i][col]==turn) break;
                    else 
                    {
                        ++count;
                         //System.out.println("count="+count);
                    }
                
                }
                else
                {
                    if(board2[i][col]==turn)
                    {
                        c=1;
                        checkob newob=new checkob(board2,row,col);
                         newob.setcheckob("c", "inc",count);
                        candidate.add(newob);
                         //System.out.println("c inc");
                        break;
                    }
                    else 
                    {
                        ++count;
                        // System.out.println("count="+count);
                    }
                }
            }
               
            ++i;
        }
        //System.out.println("c="+c);
        return c;
    }
     
     public int checkldiag (int [][]board2,int turn,int row, int col, ArrayList<checkob> candidate)
    {
        int c=0;
        //dec
        int i,j,count=0;
        i=row-1;
        j=col-1;
        int other;
        if(turn==0) other=1;
        else other=0;
                //System.out.println("turn1"+turn);
         //System.out.println("rowcol"+row+col);
       
        while(i>=0 && j>=0)
        {
            //System.out.println("i="+i);
            if((board2[i][j]==-1))  break;
            else
            {
                if(count==0)
                {
                    if(board2[i][j]==turn) break;
                    else 
                    {
                        ++count;
                           //System.out.println("count="+count);
                    }
                
                }
                else
                {
                    if(board2[i][j]==turn)
                    {
                        c=1;
                        checkob newob=new checkob(board2,row,col);
                         newob.setcheckob("ldiag", "dec",count);
                        candidate.add(newob);
                         //System.out.println("ldiag dec");
                        break;
                    }
                    else 
                    {
                        ++count;
                           //System.out.println("count="+count);
                    }
                }
            }
               
            --i;
            --j;
         }
        
       //inc
        i=row+1;
        j=col+1;
        count=0;
        //System.out.println("turn1"+turn);
        
           // System.out.println("iturn="+i+turn);
              //System.out.println("br="+board[i][j]);
                 //if(i<board2.length && j<board2.length)System.out.println("br="+board2[i][j]);
        while(i<board2.length && j<board2.length)
        {
            
            //System.out.println("i="+i);
             if((board2[i][j]==-1))  break;
            else
            {
                if(count==0)
                {
                    if(board2[i][j]==turn) break;
                    else 
                    {
                        ++count;
                        //System.out.println("count="+count);
                    }
                
                }
                else
                {
                    if(board2[i][j]==turn)
                    {
                        c=1;
                        checkob newob=new checkob(board2,row,col);
                         newob.setcheckob("ldiag", "inc",count);
                        candidate.add(newob);
                         //System.out.println("ldiag inc");
                        break;
                    }
                    else 
                    {
                        ++count;
                        //System.out.println("count="+count);
                    }
                }
            }
               
            ++i;
            ++j;
        }
       // System.out.println("c="+c);
        return c;
    }
     
     public int checkrdiag (int [][]board2,int turn,int row, int col, ArrayList<checkob> candidate)
    {
        int c=0;
        //rowdec,colinc
        int i,j,count=0;
        i=row-1;
        j=col+1;
        
        int other;
        if(turn==0) other=1;
        else other=0;
        //System.out.println("turn1"+turn);
        // System.out.println("rowcol"+row+col);
       
        
        while(i>=0 && j<board2.length)
        {
            //System.out.println("i="+i);
            if((board2[i][j]==-1))  break;
            else
            {
                if(count==0)
                {
                    if(board2[i][j]==turn) break;
                    else 
                    {
                        ++count;
                        // System.out.println("count="+count);
                    }
                
                }
                else
                {
                    if(board2[i][j]==turn)
                    {
                        c=1;
                        checkob newob=new checkob(board2,row,col);
                         newob.setcheckob("rdiag", "dec",count);
                        candidate.add(newob);
                         //System.out.println("rdiag dec");
                        break;
                    }
                    else 
                    {
                        ++count;
                         //System.out.println("count="+count);
                    }
                }
            }
               
            --i;
            ++j;
         }
        
       //inc
        i=row+1;
        j=col-1;
        count=0;
       // System.out.println("turn1"+turn);
        
         //   System.out.println("iturn="+i+turn);
              //if(i<board2.length && j>=0)System.out.println("br="+board2[i][j]);
        while(i<board2.length && j>=0)
        {
            
           // System.out.println("i="+i);
             if((board2[i][j]==-1))  break;
            else
            {
                if(count==0)
                {
                    if(board2[i][j]==turn) break;
                    else 
                    {
                        ++count;
             //           System.out.println("count="+count);
                    }
                
                }
                else
                {
                    if(board2[i][j]==turn)
                    {
                        c=1;
                        checkob newob=new checkob(board2,row,col);
                         newob.setcheckob("rdiag", "inc",count);
                        candidate.add(newob);
                         //System.out.println("rdiag inc");
                        break;
                    }
                    else 
                    {
                        ++count;
               //         System.out.println("count="+count);
                    }
                }
            }
               
            ++i;
            --j;
        }
        //System.out.println("c="+c);
        return c;
    }
    public int isValidCell(int [][] board2,int turn,int row, int col, ArrayList<checkob> candidate)
    {
		if(board2==null) board2=board.clone();
        int validrow,validcol,validldiag,validrdiag;
        validrow=0;
        validcol=0;
        validldiag=0;
        validrdiag=0;
         validrow=checkrow(board2,turn,row,col,candidate);
         validcol=checkcol(board2,turn,row,col,candidate);
         validldiag=checkldiag(board2,turn,row,col,candidate);
         validrdiag=checkrdiag(board2,turn,row,col,candidate);
         
         if(validrow==1 || validcol==1 || validldiag==1 || validrdiag==1) return 1;
         
         else return 0;

    
    }

	@Override
	void updateMessage(String msg) {
		// TODO Auto-generated method stub
		//System.out.println(msg);
            main.label2.setText(msg);
            
	}

    @Override
    void updatefirst(String color) {
        
        if(color.compareTo("white")==0) //human white
        {  
             board[3][3]=0;
             board[4][4]=0;
             board[4][3]=1;
             board[3][4]=1;
               
        }
        else
        {  
             board[3][3]=1;
             board[4][4]=1;
             board[4][3]=0;
             board[3][4]=0;
               
        }
    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    

    @Override
    int checkgamestate(int turn,int[][]board2) {
        int i,j;
        int a=0;
        if(board2==null) board2=board.clone();
        for(i=0;i<board2.length;i++)
        {
            for(j=0;j<board2.length;j++)
            {
                if(board2[i][j]==-1)
                {
                    ArrayList<checkob> candidate=new ArrayList<checkob>();
                    a=isValidCell(board2,turn,i, j,candidate);
                    if(a==1) break;
                }
            }
             if(a==1) break;
        }
        return a;
       
        
    }

    @Override
    void scoreupdate() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
         int i,j,count;
        agent[0].score=0;
        agent[1].score=0;
        for(i=0;i<board.length;i++)
        {
            for(j=0;j<board.length;j++)
            {
                if(board[i][j]==0)
                {
                    agent[0].score++;
                }
                else if(board[i][j]==1)
                {
                    agent[1].score++;
                }
            }
            
        }
        if(agent[0].name.compareTo("Machine")==0)
        {
            main.label5.setText("Machine 1's score="+agent[0].score+"  ");
             main.label6.setText("Machine 2's score="+agent[1].score);
                    
        }
        else
        {
            main.label5.setText("Human's score="+agent[0].score+"  ");
             main.label6.setText("Machine's score="+agent[1].score);
        }
        System.out.println("0 score="+agent[0].score+", 1 score="+agent[1].score);
    }
	
}

