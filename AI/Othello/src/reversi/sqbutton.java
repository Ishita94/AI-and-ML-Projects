/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author User
 */
package reversi;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.*;

public class sqbutton extends JButton implements ActionListener{
	ImageIcon white,black;
	int value;
        int index;
        int row,col;
        int available;
        Game game;
        int turn;
        String color;
	Reversitic r;
	public sqbutton(int i, Game g)
        {
            row=i/8;
            col=i%8;
            index=i;
            value=0;
            available=0;
           game=g;
           
           r = (Reversitic) game;
            Color c = new Color (88, 134, 2);
            setBackground(c);
            setOpaque(true);
            white=new ImageIcon(this.getClass().getResource("white.jpg"));
            black=new ImageIcon(this.getClass().getResource("black.jpg"));
            if(index==27||index==36) 
            {
                setIcon(white);
                available=1;
               
            }
            else if(index==28||index==35) 
            {
                setIcon(black);
                available=1;
            }
            this.addActionListener(this);
	}
	
        public void setcolor(String color2)
        {
             if(color2.compareTo("white")==0)
                    {
                        setIcon(white);
                    }
            else if(color2.compareTo("black")==0)
                    {
                        //System.out.println("ddd");
                        setIcon(black);
                    }
        }
	public void actionPerformed(ActionEvent e)
        {
            if(available==0)
            {
                turn=game.turn;
                ArrayList<checkob> candidate=new ArrayList<checkob>();
                int a=r.isValidCell(r.board,turn,row, col,candidate);
                /*try {
			Thread.sleep(1000);
		} catch (InterruptedException f) {
			// TODO Auto-generated catch block
			f.printStackTrace();
		}*/
                color=game.agent[turn].color;
		if(a==1) //valid
                {
                
                    System.out.println(candidate.size()+" can,a="+a);
                    //turn=game.turn;
                    if(game.agent[turn].color.compareTo("white")==0)
                    {
                        setIcon(white);
                    }
                    else if(game.agent[turn].color.compareTo("black")==0)
                    {
                        setIcon(black);
                    }
                    
                    int i,count,j,m,n;
                    checkob ob;
                    
                    for(i=0;i<candidate.size();i++)
                    {
                        ob=candidate.get(i);
                        m=row;
                        n=col;
                        System.out.println("name="+ob.name+ob.state+ob.count);
                        if(ob.name.compareTo("r")==0)
                        {
                            
                            if(ob.state.compareTo("dec")==0)
                            {
                                System.out.println("row dec found");
                                for(j=0;j<ob.count;j++)
                                {
                                     --n;
                                    r.board[m][n]=game.agent[turn].role;
                                    count=m*8+n;
                                        r.main.buttons[count].setcolor(color);
                                }
                            }
                            else
                            {
                               System.out.println("row inc found");
                                for(j=0;j<ob.count;j++)
                                {
                                    ++n;
                                    r.board[m][n]=game.agent[turn].role;
                                    count=m*8+n;
                                        r.main.buttons[count].setcolor(color);
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
                                    r.board[m][n]=game.agent[turn].role;
                                    count=m*8+n;
                                        r.main.buttons[count].setcolor(color);
                                }
                            }
                            else
                            {
                              System.out.println("col inc found");
                                for(j=0;j<ob.count;j++)
                                {
                                     ++m;
                                    r.board[m][n]=game.agent[turn].role;
                                    count=m*8+n;
                                        r.main.buttons[count].setcolor(color);
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
                                    r.board[m][n]=game.agent[turn].role;
                                    count=m*8+n;
                                        r.main.buttons[count].setcolor(color);
                                }
                            }
                            else
                            {
                               System.out.println("ldiag inc found");
                                for(j=0;j<ob.count;j++)
                                {
                                    ++n;
                                    ++m;
                                    r.board[m][n]=game.agent[turn].role;
                                    count=m*8+n;
                                        r.main.buttons[count].setcolor(color);
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
                                    r.board[m][n]=game.agent[turn].role;
                                    count=m*8+n;
                                        r.main.buttons[count].setcolor(color);
                                }
                            }
                            else
                            {
                                System.out.println("rdiag inc found");
                                for(j=0;j<ob.count;j++)
                                {
                                    ++m;
                                    --n;
                                    r.board[m][n]=game.agent[turn].role;
                                    count=m*8+n;
                                        r.main.buttons[count].setcolor(color);
                                } 
                            }
                        }
                    }
                    available=1;
                    game.agent[turn].setbuzz(row,col); 
                }
               
                
            }
               
	}
}
