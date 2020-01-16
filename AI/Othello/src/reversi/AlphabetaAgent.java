/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reversi;

import java.util.ArrayList;

/**
 *
 * @author User
 */

/**
 * Example MiniMax agent extending Agent class.
 * Here, for simplicity of understanding min and max functions are written separately. One single function can do the task. 
 * @author Azad
 *
 */
public class AlphabetaAgent extends Agent
{
	
	public AlphabetaAgent(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

        
        @Override
        public void setbuzz(int r,int c)
        {
            b.row=r;
            b.col=c;
            System.out.println("m set korsi");
        }
        @Override
        public int checkbuzz()
        {
            if(b.col==-1 && b.row==-1) 
            {
                   //System.out.println("habijabiiiii");
                    try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
              
                return 0;
            }
            else 
            {
                System.out.println("m check hoise");
                return 1;
            }
        }
	@Override
	public void makeMove(Game game) {
		// TODO Auto-generated method stub
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Reversitic tttGame = (Reversitic) game;
                int [][]board=tttGame.board.clone();
                int alpha=-1000000,beta=1000000;
                
		CellValueTuple best = max(tttGame,board,alpha,beta,depth);
                System.out.println("best.r="+best.row+" best.c="+best.col);
		if(best.col!=-1 && best.row!=-1)
		{
			tttGame.board[best.row][best.col] = role;
                        //tttGame.main.buttons
                                
                                 
                int turn=role;
                ArrayList<checkob> candidate=new ArrayList<checkob>();
                int a=tttGame.isValidCell(tttGame.board,turn,best.row, best.col,candidate);
               String color2=color;
                //color=game.agent[turn].color;
		if(a==1) //valid
                {
                
		
                     //System.out.println("max max main");
                    //System.out.println(candidate.size()+" can,a="+a);
                    //turn=game.turn;
                    int value=(best.row)*8+best.col;
                    //System.out.println("color"+color);
                    
                         tttGame.main.buttons[value].setcolor(color);
                    
                       // tttGame.main.buttons[value].setcolor("white");
                    
                    
                    int i,count,j,m,n;
                    checkob ob;
                    
                    for(i=0;i<candidate.size();i++)
                    {
                        ob=candidate.get(i);
                        m=best.row;
                        n=best.col;
                        //System.out.println("name="+ob.name+ob.state+ob.count);
                        if(ob.name.compareTo("r")==0)
                        {
                            
                            if(ob.state.compareTo("dec")==0)
                            {
                                System.out.println("row dec found");
                                for(j=0;j<ob.count;j++)
                                {
                                     --n;
                                    tttGame.board[m][n]=game.agent[turn].role;
                                    count=m*8+n;
                                        tttGame.main.buttons[count].setcolor(color);
                                }
                            }
                            else
                            {
                               System.out.println("row inc found");
                                for(j=0;j<ob.count;j++)
                                {
                                    ++n;
                                    tttGame.board[m][n]=game.agent[turn].role;
                                    count=m*8+n;
                                        tttGame.main.buttons[count].setcolor(color);
                                } 
                            }
                        }
                        else if(ob.name.compareTo("c")==0)
                        {
                            
                            if(ob.state.compareTo("dec")==0)
                            {
                               System.out.println("col dec found");
                                for(j=0;j<ob.count;j++)
                                {
                                     --m;
                                    tttGame.board[m][n]=game.agent[turn].role;
                                    count=m*8+n;
                                        tttGame.main.buttons[count].setcolor(color);
                                }
                            }
                            else
                            {
                              System.out.println("col inc found");
                                for(j=0;j<ob.count;j++)
                                {
                                     ++m;
                                    tttGame.board[m][n]=game.agent[turn].role;
                                    count=m*8+n;
                                        tttGame.main.buttons[count].setcolor(color);
                                } 
                            }
                        }
                        else if(ob.name.compareTo("ldiag")==0)
                        {
                            
                            if(ob.state.compareTo("dec")==0)
                            {
                                System.out.println("ldiag dec found");
                                for(j=0;j<ob.count;j++)
                                {
                                    --n;
                                    --m;
                                    tttGame.board[m][n]=game.agent[turn].role;
                                    count=m*8+n;
                                        tttGame.main.buttons[count].setcolor(color);
                                }
                            }
                            else
                            {
                               System.out.println("ldiag inc found");
                                for(j=0;j<ob.count;j++)
                                {
                                    ++n;
                                    ++m;
                                    tttGame.board[m][n]=game.agent[turn].role;
                                    count=m*8+n;
                                        tttGame.main.buttons[count].setcolor(color);
                                } 
                            }
                        }
                        else if (ob.name.compareTo("rdiag")==0)
                        {
                            
                            if(ob.state.compareTo("dec")==0)
                            {
                                System.out.println("rdiag dec found");
                                for(j=0;j<ob.count;j++)
                                {
                                     --m;
                                     ++n;
                                    tttGame.board[m][n]=game.agent[turn].role;
                                    count=m*8+n;
                                        tttGame.main.buttons[count].setcolor(color);
                                }
                            }
                            else
                            {
                                System.out.println("rdiag inc found");
                                for(j=0;j<ob.count;j++)
                                {
                                    ++m;
                                    --n;
                                    tttGame.board[m][n]=game.agent[turn].role;
                                    count=m*8+n;
                                        tttGame.main.buttons[count].setcolor(color);
                                } 
                            }
                        }
                    }
                    
                }
               
                
            }
                //System.out.println("m r set korsi");
               
	}
		
                
                
	
        public int calcwinner(int [][]board)
        {
            int winner=-1;
            int count0=0,count1=0,i,j;
            for(i=0;i<board.length;i++)
            {
                for(j=0;j<board.length;j++)
                {
                    if(board[i][j]!=-1)
                    {
                        if(board[i][j]==0) count0++;
                        else count1++;
                    }
                }
            }
            if(count0>count1) winner=0; //h
            else if(count0<count1) winner=1;
            
            
            return winner;
        }
        
         public void evaluate(int [][]board, CellValueTuple cvt)
        {
            int winner=-1;
            int count0=0,count1=0,i,j;
            for(i=0;i<board.length;i++)
            {
                for(j=0;j<board.length;j++)
                {
                    if(board[i][j]!=-1)
                    {
                        if(board[i][j]==0) count0++;
                        else count1++;
                    }
                }
            }
            int countcon0=0,countcon1=0;
            if(board[0][0]==0) countcon0++;
            else if(board[0][0]==1) countcon1++;
            if(board[0][7]==0) countcon0++;
            else if(board[0][7]==1) countcon1++;
            if(board[7][0]==0) countcon0++;
            else if(board[7][0]==1) countcon1++;
            if(board[7][7]==0) countcon0++;
            else if(board[7][7]==1) countcon1++;
            
            if(count0>count1) //lose
            {
                cvt.utility = 5000; 
                
            } //h
            else if(count0<count1) //win
            {
               cvt.utility = 100;
            }
            else cvt.utility=2500;
            if(countcon0>countcon1)
            {
                cvt.utility+=500;
            }
            else  if(countcon1>countcon0)
            {
                cvt.utility+=500;
            }
            
            
            
            
        }
	private CellValueTuple max(Reversitic game,int [][]board,int alpha,int beta,int d)
	{	
		CellValueTuple maxCVT = new CellValueTuple();
		  System.out.println("d="+d);
                  int [][]newarr=new int [8][8];
		int winner,validconfirmm;
                int  x,w;
                for(x=0;x<8;x++)
                {
                    for(w=0;w<8;w++)
                    {
                        newarr[x][w]=board[x][w];
                    }
                }
		int validconfirmh = game.checkgamestate(role,board); //human er kono valid state ache kina
                if(role==0) 
                {
                    validconfirmm=game.checkgamestate(1,board);
                } //machine
                else
                {
                    validconfirmm=game.checkgamestate(0,board);
                } //machine
                
                   System.out.println("validcnfrnmhmax="+validconfirmh+" validconfrmmmax="+validconfirmm);
                if(validconfirmh==0 && validconfirmm==0) //karo r valid move nai
                {
                    winner=calcwinner(board);
                    if(winner == role)
                    {
                            maxCVT.utility = 10000; //this agent wins
                            return maxCVT;
                    }
                    else if(winner!=-1) 
                    {
                            maxCVT.utility = 10; //opponent wins
                            return maxCVT;  
                    }
                    else if (winner==-1)
                    {
                            maxCVT.utility = 5000; //draw
                            return maxCVT;  
                    }
                }
                
                if(d==0)
                {
                    evaluate(board,maxCVT);
                    return maxCVT;
                    
                }
		
		
                maxCVT.utility = -1000000;  
		
		
		for (int p = 0; p < 8; p++) 
		{
			for (int q = 0; q<8;q++)
			{
                            int [][]tempboard=new int[8][8];
                             for(x=0;x<8;x++)
                            {
                                for(w=0;w<8;w++)
                                {
                                    tempboard[x][w]=newarr[x][w];
                                }
                            }
                            
                            int g,h;
                    
                             System.out.println("tempboard new=p"+p+"and q="+q);
		
        /*for (g = 0; g < 8; g++) 
        {
            System.out.print("| ");
            for (h = 0; h < 8; h++) 
            {
            	if(tempboard[g][h]==-1)
            		System.out.print(" " + " | ");
            	else if	(tempboard[g][h]==0)
            		System.out.print( "0 | ");
            	else
            		System.out.print( "1 | ");
            }
            System.out.println();
            System.out.println("-------------");
        }*/
				if(tempboard[p][q]==-1)
                                { 
//khali na eta
                                    ArrayList<checkob> candidate=new ArrayList<checkob>();
                                    int a= game.isValidCell(tempboard,role,p,q,candidate);
                                    if(a==1)
                                    {
                                           System.out.println("p and q valid="+p+q);
                                        tempboard[p][q] = role;
                                     //temporarily making a move
                                        
                                        int i,count,j,m,n;
                                        checkob ob;
                     System.out.println("cand size="+candidate.size());
                    for(i=0;i<candidate.size();i++)
                    {
                        ob=candidate.get(i);
                        m=p;
                        n=q;
                       // System.out.println("name="+ob.name+ob.state+ob.count);
                        if(ob.name.compareTo("r")==0)
                        {
                            
                            if(ob.state.compareTo("dec")==0)
                            {
                         //       System.out.println("row dec found");
                                for(j=0;j<ob.count;j++)
                                {
                                     --n;
                                    tempboard[m][n]=role;
                                    
                                }
                            }
                            else
                            {
                           //    System.out.println("row inc found");
                                for(j=0;j<ob.count;j++)
                                {
                                    ++n;
                                    tempboard[m][n]=role;
                                   
                                } 
                            }
                        }
                        else if(ob.name.compareTo("c")==0)
                        {
                            
                            if(ob.state.compareTo("dec")==0)
                            {
                             //  System.out.println("col dec found");
                                for(j=0;j<ob.count;j++)
                                {
                                     --m;
                                    tempboard[m][n]=role;
                                   
                                }
                            }
                            else
                            {
                              //System.out.println("col inc found");
                                for(j=0;j<ob.count;j++)
                                {
                                     ++m;
                                    tempboard[m][n]=role;
                                   
                                } 
                            }
                        }
                        else if(ob.name.compareTo("ldiag")==0)
                        {
                            
                            if(ob.state.compareTo("dec")==0)
                            {
                                //System.out.println("ldiag dec found");
                                for(j=0;j<ob.count;j++)
                                {
                                    --n;
                                    --m;
                                    tempboard[m][n]=role;
                                   
                                }
                            }
                            else
                            {
                               //System.out.println("ldiag inc found");
                                for(j=0;j<ob.count;j++)
                                {
                                    ++n;
                                    ++m;
                                    tempboard[m][n]=role;
                                    
                                } 
                            }
                        }
                        else if (ob.name.compareTo("rdiag")==0)
                        {
                            
                            if(ob.state.compareTo("dec")==0)
                            {
                                //System.out.println("rdiag dec found");
                                for(j=0;j<ob.count;j++)
                                {
                                     --m;
                                     ++n;
                                    tempboard[m][n]=role;
                                    
                                }
                            }
                            else
                            {
                                //System.out.println("rdiag inc found");
                                for(j=0;j<ob.count;j++)
                                {
                                    ++m;
                                    --n;
                                    tempboard[m][n]=role;
                                    
                                } 
                            }
                        }
                    }
                   // int g,h;
                    
                             System.out.println("tempboard for p="+p+"and q="+q);
		
        for (g = 0; g < 8; g++) 
        {
            System.out.print("| ");
            for (h = 0; h < 8; h++) 
            {
            	if(tempboard[g][h]==-1)
            		System.out.print(" " + " | ");
            	else if	(tempboard[g][h]==0)
            		System.out.print( "0 | ");
            	else
            		System.out.print( "1 | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
                        
                    
                                        int v = min(game,tempboard,alpha,beta,d-1).utility;
                                          //System.out.println("final v for tempb="+v);
                                        if(v>maxCVT.utility)
                                        {
                                            maxCVT.utility=v;
                                            maxCVT.row = p;
                                            maxCVT.col = q;
                                           // maxCVT.tempboard=tempboard.clone();
                                            System.out.println("maxu,cvtr and cvtc="+maxCVT.utility+","+maxCVT.row+maxCVT.col);
                                        }
                                        
                                    if(maxCVT.utility>=beta)
                                    {
                                              System.out.println("beta="+beta);
                                        return maxCVT;
                                    }
                                    if(maxCVT.utility>alpha)
                                    {
                                        //System.out.println("alpha1="+alpha);
                                        alpha=maxCVT.utility;
                                        System.out.println("alpha2="+alpha);
                                    }
                                        /*if(v>maxCVT.utility)
                                        {
                                                maxCVT.utility=v;
                                                maxCVT.row = i;
                                                maxCVT.col = j;
                                        }
                                        game.board[i][j] = -1; // reverting back to original state
                                                */
                                    }
                                }
				
			}
		}
		//return maxCVT;
			
                
                return maxCVT;
        }
private int minRole()
	{
		if(role==0)return 1;
		else return 0;
	}

        

	

	private CellValueTuple min(Reversitic game,int [][]board,int alpha,int beta,int d)
	{	
		CellValueTuple minCVT = new CellValueTuple();
		//minCVT.utility = 1000000;
                //CellValueTuple maxCVT = new CellValueTuple();
	  System.out.println("d="+d);
          int [][]newarr=new int [8][8];
		//int winner,validconfirmm;
                int  x,w;
                for(x=0;x<8;x++)
                {
                    for(w=0;w<8;w++)
                    {
                        newarr[x][w]=board[x][w];
                    }
                }
		int winner,validconfirmm;
		int validconfirmh = game.checkgamestate(role,board); //human er kono valid state ache kina
                if(role==0) 
                {
                    validconfirmm=game.checkgamestate(1,board);
                } //machine
                else
                {
                    validconfirmm=game.checkgamestate(0,board);
                } //machine
                
                
                   System.out.println("validcnfrnmhmin="+validconfirmh+" validconfrmmmin="+validconfirmm);
                if(validconfirmh==0 && validconfirmm==0) //karo r valid move nai
                {
                    winner=calcwinner(board);
                    if(winner == role)
                    {
                            minCVT.utility = 10000; //this agent wins
                            return minCVT;
                    }
                    else if(winner!=-1) 
                    {
                            minCVT.utility = 10; //opponent wins
                            return minCVT;  
                    }
                    else if (winner==-1)
                    {
                            minCVT.utility = 5000; //draw
                            return minCVT;  
                    }
                }
                
                if(d==0)
                {
                    evaluate(board,minCVT);
                    return minCVT;
                    
                }
		
		
                minCVT.utility = 1000000;  
		
		
		for (int p = 0; p < 8; p++) 
		{
                      
                   //System.out.println("hi");
			for (int q = 0; q<8;q++)
			{
                            //int [][]tempboard=board.clone();
                            int [][]tempboard=new int[8][8];
                             for(x=0;x<8;x++)
                            {
                                for(w=0;w<8;w++)
                                {
                                    tempboard[x][w]=newarr[x][w];
                                }
                            }
                             
                             int g,h;
                    
                             System.out.println("tempboard min new for p="+p+"and q="+q);
		
        /*for (g = 0; g < 8; g++) 
        {
            System.out.print("| ");
            for (h = 0; h < 8; h++) 
            {
            	if(tempboard[g][h]==-1)
            		System.out.print(" " + " | ");
            	else if	(tempboard[g][h]==0)
            		System.out.print( "0 | ");
            	else
            		System.out.print( "1 | ");
            }
            System.out.println();
            System.out.println("-------------");
        }*/
				if(tempboard[p][q]==-1)
                                { 
//khali na eta
                                    ArrayList<checkob> candidate=new ArrayList<checkob>();
                                    int a= game.isValidCell(tempboard,minRole(),p,q,candidate);
                                      
                   //System.out.println("amin="+a);
                                    if(a==1)
                                    {
                                        tempboard[p][q] = minRole();
                                     //temporarily making a move
                                        
                                        int i,count,j,m,n;
                                        checkob ob;
                    
                   //System.out.println("candminsize="+candidate.size());
                    for(i=0;i<candidate.size();i++)
                    {
                        ob=candidate.get(i);
                        m=p;
                        n=q;
                     //   System.out.println("name="+ob.name+ob.state+ob.count);
                        if(ob.name.compareTo("r")==0)
                        {
                            
                            if(ob.state.compareTo("dec")==0)
                            {
                       //         System.out.println("row dec found");
                                for(j=0;j<ob.count;j++)
                                {
                                     --n;
                                    tempboard[m][n]=minRole();
                                    
                                }
                            }
                            else
                            {
                         //      System.out.println("row inc found");
                                for(j=0;j<ob.count;j++)
                                {
                                    ++n;
                                    tempboard[m][n]=minRole();
                                   
                                } 
                            }
                        }
                        else if(ob.name.compareTo("c")==0)
                        {
                            
                            if(ob.state.compareTo("dec")==0)
                            {
                           //    System.out.println("col dec found");
                                for(j=0;j<ob.count;j++)
                                {
                                     --m;
                                    tempboard[m][n]=minRole();
                                   
                                }
                            }
                            else
                            {
                             // System.out.println("col inc found");
                                for(j=0;j<ob.count;j++)
                                {
                                     ++m;
                                    tempboard[m][n]=minRole();
                                   
                                } 
                            }
                        }
                        else if(ob.name.compareTo("ldiag")==0)
                        {
                            
                            if(ob.state.compareTo("dec")==0)
                            {
                               // System.out.println("ldiag dec found");
                                for(j=0;j<ob.count;j++)
                                {
                                    --n;
                                    --m;
                                    tempboard[m][n]=minRole();
                                   
                                }
                            }
                            else
                            {
                               //System.out.println("ldiag inc found");
                                for(j=0;j<ob.count;j++)
                                {
                                    ++n;
                                    ++m;
                                    tempboard[m][n]=minRole();
                                    
                                } 
                            }
                        }
                        else if (ob.name.compareTo("rdiag")==0)
                        {
                            
                            if(ob.state.compareTo("dec")==0)
                            {
                               // System.out.println("rdiag dec found");
                                for(j=0;j<ob.count;j++)
                                {
                                     --m;
                                     ++n;
                                    tempboard[m][n]=minRole();
                                    
                                }
                            }
                            else
                            {
                                //System.out.println("rdiag inc found");
                                for(j=0;j<ob.count;j++)
                                {
                                    ++m;
                                    --n;
                                    tempboard[m][n]=minRole();
                                    
                                } 
                            }
                        }
                    }
                    
                                 
                    
                             System.out.println("tempboard min for p="+p+"and q="+q);
		
        for (g = 0; g < 8; g++) 
        {
            System.out.print("| ");
            for (h = 0; h < 8; h++) 
            {
            	if(tempboard[g][h]==-1)
            		System.out.print(" " + " | ");
            	else if	(tempboard[g][h]==0)
            		System.out.print( "0 | ");
            	else
            		System.out.print( "1 | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
           
                                        int v = max(game,tempboard,alpha,beta,d-1).utility;
                                        System.out.println("vmin"+v);
                                        if(v<minCVT.utility)
                                        {
                                            minCVT.utility=v;
                                            minCVT.row = p;
                                            minCVT.col = q;
                                            //minCVT.tempboard=tempboard.clone();
                                            System.out.println("minu,mincvtr and mincvtc="+minCVT.utility+","+minCVT.row+minCVT.col);
                                        }
                                        
                                    if(minCVT.utility<=alpha)
                                    {
                                        System.out.println("alpha="+alpha);
                                        
                                        return minCVT;
                                    }
                                    if(minCVT.utility<beta)
                                    {
                                        //System.out.println("beta1="+beta);
                                        //alpha=minCVT.utility;
                                        
                                        beta=minCVT.utility;
                                        System.out.println("beta2="+beta);
                                    }
                                        /*if(v>maxCVT.utility)
                                        {
                                                maxCVT.utility=v;
                                                maxCVT.row = i;
                                                maxCVT.col = j;
                                        }
                                        game.board[i][j] = -1; // reverting back to original state
                                                */
                                    }
                                }
				
			}
		}
		return minCVT;
			
	}
		
		
			
	}
	
	
   

	class CellValueTuple
	{
		int row,col, utility;
                int [][]tempboard;
		public CellValueTuple() {
			// TODO Auto-generated constructor stub
			row =-1;
			col =-1;
		}
	}



