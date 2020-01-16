/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package n.puzzle;

/**
 *
 * @author User
 */
public class node implements Comparable<node> {
    public int n;
    public int array[][];
    public int g,i0,j0;
    float cost,h;
    public node parent;
    //public node child;
    public node(int n1,int g1,int array1[][],int goal[][],node p)
    {
        n=n1;
        array=new int[n][n];
        g=g1;    //g(x)
        h=0;
        parent=p;
        
            int i,j;
         for(i=0;i<n;i++)
                    {
                       for(j=0;j<n;j++)
                        {
                            array[i][j]=array1[i][j];
                        } 
                        //System.out.println("");
                    }
    
    
        
       //calccost
    }
    public void calccost(int goal[][],int ch)
    {
        int i,j;
        if(ch==0) //misplaced tiles
        {
            for(i=0;i<n;i++)
             {
                for(j=0;j<n;j++)
                 {
                      if(array[i][j]!=0 && array[i][j]!=goal[i][j])
                      {
                          h++;
                         //System.out.println("h:"+h);
                      }
                 } 

             }
        }
        else   //euclidian distance
        {
            int item;
            //float dtotal=0;
            for(i=0;i<n;i++)
            {
                for(j=0;j<n;j++)
                {
                   if(array[i][j]!=0 && array[i][j]!=goal[i][j])
                    {
                          int flag=0,p1=0,q1=0;
                          item=array[i][j];
                      
                            for(int p=0;p<n;p++)
                             {
                                     for(int q=0;q<n;q++)
                                     {
                                         if(goal[p][q]==item)
                                         {
                                             p1=p;
                                             q1=q;
                                             flag=1;
                                             break;
                                         }
                                             
                                     }
                             }
                            //int disx=i-p1;
                           // disx=Math.abs(disx);
                            float disx =(float) Math.pow(i-p1, 2.0);
                             float disy =(float) Math.pow(j-q1, 2.0);
                           
                            float d=(float)Math.sqrt(disx+disy);
                            h=h+d;
                            
                            
                            
                    }
                    
                }
            }
            
        }
       cost=g+h;
        
    }
  
    
 

    public boolean equals(node t)
    {
        return this.cost==t.cost;
    }
    public int compareTo(node t) {
        //
        if(this.equals(t)) return 0;
        else if(cost>t.cost) return 1;
        else return -1;
    }

    
}
