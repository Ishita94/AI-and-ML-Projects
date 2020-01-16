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
public class checkob {
    
    public int[][] board;
    public int row,col,count;
   
    public String name, state;
    public checkob(int [][]b,int r,int c)
    {
        board=b.clone();
        row=r;
        col=c;
        count=0;
        name=null;
        state=null;
        
    }
    public void setcheckob(String n, String s,int c)
    {
        name=n;
        state=s;
        count=c;
    }
}
