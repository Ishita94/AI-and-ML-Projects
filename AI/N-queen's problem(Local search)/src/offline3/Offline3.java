/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package offline3;

/**
 *
 * @author User
 */
import java.util.Collections;
import java.util.Vector;
import java.util.concurrent.ThreadLocalRandom;



public class Offline3 {
    
    public  int it1;
     public  int it2;
    public static Vector GenerateRandomState(int n)
    {
            Vector start=new Vector();
            start.add(0);
            for(int i=0;i<n;i++)
            {
                int m=ThreadLocalRandom.current().nextInt(1,n+1);
                //System.out.println(m);
                start.add(m);
            }
            return start;
    }
   
    public static int conflict(Vector start)
    {
        
        int con=0,i,j,m,n,row,col;
        for(i=1;i<start.size();i++)
        {
            m=(int)start.get(i);
            for(j=i+1;j<start.size();j++)
            {
                n=(int)start.get(j);
                if(m==n) con++; //same column e
                else
                {
                    row=Math.abs(i-j);
                    col=Math.abs(m-n);
                    if(row==col) con++;
                }
            }
            
        }
        return con;
    }
    
    public static Vector findbestnei(Vector current)
    {
            Vector temp=(Vector)current.clone();
            Vector result=(Vector)current.clone();
            int i,j,m,con;
            int min=10000;
            for(i=1;i<current.size();i++)
            {
                m=(int)current.get(i);
                for(j=1;j<current.size();j++)
                {
                    
                    if(j!=m)
                    {
                        temp=(Vector)current.clone();
                        temp.set(i, j);
                        con=conflict(temp);
                        
                        if(con<=min)
                        {
                             Collections.copy(result, temp);
                             min=con;
                        }
                         System.out.println("temp="+temp+" con="+con+" min="+min+" result="+result);
                    }
                }
            }
            return result;
            
            
    }
    public static ob HillClimb(Vector start)
    {
        Vector current=(Vector)start.clone();
        
        //System.out.println(con);
        int max_step=15;
        int count=1,i,j,con2,con1=0;
        ob a=new ob();
        a.vect=(Vector)start.clone();
        for(i=0;i<max_step;i++,count++)
        {
            System.out.println("current="+current);
            Vector bestnei=findbestnei(current);
            con1=conflict(current);
            con2=conflict(bestnei);
            if(con2>=con1)
            {
                System.out.println("no bestnei,current= "+current+" ,con1= "+con1+",nei= "+ bestnei+" ,con2="+con2);
                System.out.println("count="+count);
                
               
                break;
            }
            Collections.copy(current, bestnei);
            
            
        }
        Collections.copy(a.vect, current);
                a.conflict=con1;
                a.count=a.count+count;
        return a;
    }
    
    
    public static ob SimulatedAnnealing(Vector start)
    {
        
        int i,j,T0=100,row,col,newcol,connext,concurrent,diff,prob2,prob3,min=1000,count=1;
        double meu=0.3,T,prob;
        ob a=new ob();
        a.vect=(Vector)start.clone();
        Vector current=(Vector)start.clone();
        Vector best=(Vector)start.clone();
        Vector next=(Vector)current.clone();
        for(i=0;i<100000;i++,count++)
        {
             
            T=T0-(meu*i);
            System.out.println("T="+T);
            if(T<=0) break;
             concurrent=(int)conflict(current);
            if(concurrent<=min)  //best
            {
                Collections.copy(best, current);
                min=concurrent;
            }
             next=(Vector)current.clone();
            row=ThreadLocalRandom.current().nextInt(1,current.size()); //random row
            col=(int)current.get(row);
            newcol=ThreadLocalRandom.current().nextInt(1,current.size()); //oi row er random column
            while(true)
            {
                System.out.println("newcol= "+newcol+" row="+row);
                if(newcol!=col) break; 
                else newcol=ThreadLocalRandom.current().nextInt(1,current.size());//same ashle ghuro
            }
            next.set(row, newcol); //next generate
            connext=(int)conflict(next);
           
            System.out.println("Current= "+current+" ,concu="+concurrent+",next=" + next+ ",connext="+connext+ "best="+ best);
            
            diff=concurrent-connext;
            if(diff>0)  Collections.copy(current, next); //next e conflict kom
            else
            {
                prob=diff/T;
                prob=Math.exp(prob);
                prob2=(int) (prob*100);
                prob3=ThreadLocalRandom.current().nextInt(0,100);
                System.out.println("prob2= "+prob2+" prob3= "+prob3);
                if(prob3<=prob2) Collections.copy(current, next);
            }
            connext=(int)conflict(next);
            concurrent=(int)conflict(current);
           System.out.println("Current= "+current+" ,concu="+concurrent+",next=" + next+ ",connext="+connext+ "best="+ best); 
        }
        
        Collections.copy(a.vect, current);
        a.conflict=conflict(current);
        a.count=count;
        return a;
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        int min1,min2;
        int it1=0,it2=0;
        int con1=0,con2=0;
        min1=min2=1000;
         for(int index=1;index<11;index++)
       {
           
           System.out.println("ind="+index);
            Vector start=GenerateRandomState(12);
            System.out.println(start);
            ob solution;
            int con;
            solution=HillClimb(start);
             con=solution.conflict;
             con1+=con;
             
              if(con<=min1) min1=con;
              it1+=solution.count;
             System.out.println("Hillclimb:solution="+solution.vect + "and con1="+ con1+"min1="+min1+" count="+it1);
             
            
            solution=SimulatedAnnealing(start);
            con=solution.conflict;
             con2+=con;
             if(con<=min2) min2=con;
             it2+=solution.count;
             System.out.println("Simulated annealing:solution="+solution.vect + "and con2="+ con2+"min2="+min2+" count="+it2);
             
               
             System.out.println();
             System.out.println();
           
            
            
       }
         double con1f=con1*1.00/10;
         double it1f=it1*1.00/10;
         double con2f=con2*1.00/10;
         double it2f=it2*1.00/10;
         
         System.out.println("con1="+con1f+"it1="+it1f+"con2="+con2f+"it2="+it2f+"min1="+min1+"min2="+min2);
         
        
        
    }
    
}
