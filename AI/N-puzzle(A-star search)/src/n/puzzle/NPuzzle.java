/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package n.puzzle;

import static java.lang.Math.sqrt;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 *
 * @author User
 */
  
public class NPuzzle {

   
    public static int n;
    
    public static void print(node first)
    {
        if(first.parent!=null)
        {
            //first.parent.child=first;
            print(first.parent);
            int i,j;
            for(i=0;i<n;i++)
                    {
                       for(j=0;j<n;j++)
                        {
                             System.out.print(first.array[i][j]+" ");
                        } 
                        System.out.println("");
                    }
            //System.out.println("cost:"+first.cost);
                  System.out.println("");
                  
        }
        
    }
    
    public static int solv(int input[][],int goal[][],int n)
    {
        int i,j,k=0;
        
        int n2=n*n;
        int input2[]=new int[n2];
        for(i=0;i<n;i++)
        {
            for(j=0;j<n;j++)
            {
                input2[k]=input[i][j];
                //System.out.print(input2[k]+" ");
                k++;
            }
        }
        
        
            int count=0;
            int rowpos=0;
            for(i=0;i<n2;i++)
            {
                for(j=i+1;j<n2;j++)
                { 
                    if(input2[j]!=0 && input2[i]!=0 && input2[i]>input2[j])
                    {
                       count++;
                    }

                }
            }
            
            if((n%2)==1) //odd,so even no inversion=solvable,8 puzzle
            {
                if((count%2)==1) return 0; //not solvable
                else return 1; //solvable
            }
            else    //15puzzle
            {   
                
                   for(i=0;i<n;i++)
                    {
                       for(j=0;j<n;j++)
                        {
                             if(input[i][j]==0)
                             {
                                 rowpos=n-i;
                                 break;

                             }
                        } 

                    }
                   if((rowpos%2)==0 && (count%2)==1) //even rowpos and odd invcount
                   {
                       return 1; //solvable
                       
                   }
                   else if((rowpos%2)==1 && (count%2)==0) //odd rowpos and even invcount
                   {
                       return 1; //solvable
                       
                   }
                   else return 0;
            

            }
  
    }
    
     
    
    public static void heu(int n,int input2[][],int goal2[][],int ch)
    {
        int i,j;
        int input[][]=input2.clone();
        int goal[][]=goal2.clone();
        
          ArrayList<node> closedset = new ArrayList<node>();
        PriorityQueue<node> openset = new PriorityQueue<node>();
        node start=new node(n,0,input,goal,null);
        
        openset.add(start);
        node first=null;
       while(!openset.isEmpty())
       {
            first=openset.poll();
           closedset.add(first);
           
             int g=first.g;
             int i0=0,j0=0;
            int firstarray[][]=first.array.clone();
            int flag3=0;
                for(int p=0;p<n;p++)
                   {
                            for(int q=0;q<n;q++)
                            {
                                if(firstarray[p][q]!=goal[p][q])
                                {
                                    flag3=1;  //eta goalna,so shb kaj koro
                                    break;
                                }
                            }
                        }
                if(flag3==0) 
                {
                    
                    break;
                } //goal pawa gese
                    
           
                  
                   for(i=0;i<n;i++)
                    {
                       for(j=0;j<n;j++)
                        {
                             if(firstarray[i][j]==0)
                             {
                                 i0=i;
                                 j0=j;

                             }
                        } 

                    }
            
            if(j0>0) //leftmove
            {
                        node childnode=new node(n,g+1,firstarray,goal,first);

                         int temp=childnode.array[i0][j0-1]; //blank jekhane jabe
                        childnode.array[i0][j0-1]=childnode.array[i0][j0];
                        childnode.array[i0][j0]=temp;
                        childnode.calccost(goal,ch);
                        int flag2=0; //child nibo kina
                        for (int k = 0; k < closedset.size(); k++) {
			node temp2=closedset.get(k);
                        int flag=0;
                        for(int p=0;p<n;p++)
                        {
                            for(int q=0;q<n;q++)
                            {
                                if(childnode.array[p][q]!=temp2.array[p][q])
                                {
                                    flag=1;  //child ei nodetana,so next nodek check koro
                                    break;
                                }
                            }
                        }
                        if(flag==0) //milse,so childk newa jabena
                        {
                            //System.out.println("child newa jabena:");
                            flag2=1;
                            break;
                        }
                            
                        }
                        if(flag2==0) 
                        {
                            openset.add(childnode); //closedlst e ei child nai so add koro

                        }
            }
              
            if(j0<(n-1)) //rightmove
            {
                 
                        node childnode=new node(n,g+1,firstarray,goal,first);
                        int temp=childnode.array[i0][j0+1];
                        childnode.array[i0][j0+1]=childnode.array[i0][j0];
                        childnode.array[i0][j0]=temp;
                        childnode.calccost(goal,ch);

                        int flag2=0; //child nibo kina
                        for (int k = 0; k < closedset.size(); k++) {
			node temp2=closedset.get(k);
                        int flag=0;
                        for(int p=0;p<n;p++)
                        {
                            for(int q=0;q<n;q++)
                            {
                                if(childnode.array[p][q]!=temp2.array[p][q])
                                {
                                    flag=1;  //child ei nodetana,so next nodek check koro
                                    break;
                                }
                            }
                        }
                        if(flag==0) //milse,so childk newa jabena
                        {
                            flag2=1;
                             //System.out.println("child newa jabena:");
                            break;
                        }
                            
                    }
                    if(flag2==0) 
                    {openset.add(childnode); //closedlst e ei child nai so add koro

                    }
            }
               
            if(i0>0) //upmove
            {
                  
                        node childnode=new node(n,g+1,firstarray,goal,first);
                       int temp=childnode.array[i0-1][j0];
                       childnode.array[i0-1][j0]=childnode.array[i0][j0];
                       childnode.array[i0][j0]=temp;
                       childnode.calccost(goal,ch);
                      // openset.add(childnode);
                       int flag2=0; //child nibo kina
                       for (int k = 0; k < closedset.size(); k++) {
			node temp2=closedset.get(k);
                        int flag=0;
                        for(int p=0;p<n;p++)
                        {
                            for(int q=0;q<n;q++)
                            {
                                if(childnode.array[p][q]!=temp2.array[p][q])
                                {
                                    flag=1;  //child ei nodetana,so next nodek check koro
                                    break;
                                }
                            }
                        }
                        if(flag==0) //milse,so childk newa jabena
                        {
                            flag2=1;
                             //System.out.println("child newa jabena:");
                            break;
                        }
                            
                        }
                        if(flag2==0)
                        {openset.add(childnode); //closedlst e ei child nai so add koro

                        }
                 
            }
               
            if(i0<(n-1)) //downmove
            {
               //ch.add(new node(n,g+1,input,goal));
                //node childnode=ch.get(0);
                        node childnode=new node(n,g+1,firstarray,goal,first);
                        int temp=childnode.array[i0+1][j0];
                        childnode.array[i0+1][j0]=childnode.array[i0][j0];
                        childnode.array[i0][j0]=temp;
                        childnode.calccost(goal,ch);
                        //openset.add(childnode);
                        int flag2=0; //child nibo kina
                        for (int k = 0; k < closedset.size(); k++) {
			node temp2=closedset.get(k);
                        int flag=0;
                        for(int p=0;p<n;p++)
                        {
                            for(int q=0;q<n;q++)
                            {
                                if(childnode.array[p][q]!=temp2.array[p][q])
                                {
                                    flag=1;  //child ei nodetana,so next nodek check koro
                                    break;
                                }
                            }
                        }
                        if(flag==0) //milse,so childk newa jabena
                        {
                            flag2=1;
                             //System.out.println("child newa jabena:");
                            break;
                        }
                            
                        }
                        if(flag2==0) 
                        {openset.add(childnode); //closedlst e ei child nai so add koro

                        }
                 
            }
         
        }
        System.out.println("moves required: "+ (int)first.cost);
        System.out.println("nodes explored: "+closedset.size());
        System.out.println("Steps:");
        System.out.println("");
       print(first);
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println("enter n:");
        Scanner c=new Scanner(System.in);
        n=c.nextInt();
        n=n+1;
        n=(int) sqrt(n);
        int input[][]=new int[n][n];
        System.out.println("enter input state:");
        int i,j;
        for(i=0;i<n;i++)
        {
           for(j=0;j<n;j++)
            {
                 input[i][j]=c.nextInt();
            } 
        }
        System.out.println("enter goal state:");
        int goal[][]=new int[n][n];
        for(i=0;i<n;i++)
        {
           for(j=0;j<n;j++)
            {
                 goal[i][j]=c.nextInt();
            } 
        }

        
        
            int s=solv(input,goal,n);
        
        
        if(s==0) System.out.println("Not solvable");
        else 
        {
            System.out.println("solvable");
            heu(n,input,goal,0);
            heu(n,input,goal,1);
        }
        
       

    }

        
        
        
    
    
}
