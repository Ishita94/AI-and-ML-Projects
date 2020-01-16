/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package offline5;

import java.util.Random;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class Clause implements Cloneable{
    Vector vec;
    int size;
    int hashvalue;
    int fitness;
    public Clause(int s)
    {
        size=s;
        fitness=0;
        vec=new Vector(size);
    }
   
    
    public  int assessfitness2(Vector v,int m)
    {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
         int i,j,k,count=0,temp,t1,t2;
        boolean boo;
        for(i=0;i<v.size();i++)
        {
            Clause cl=(Clause)v.elementAt(i);
            //System.out.println("i: "+i);
            
            temp=0;
            for(j=0;j<cl.size;j++)
            {
                boo=(boolean)vec.elementAt(j);
                t1=(int)cl.vec.elementAt(j);
                //if(boo==true && t1<0) 
                    if((boo==false && t1<0) || (boo==true && t1>0)) 
                    {
                        if(temp<m)
                        {
                            temp++;
                        }
                        else
                        {
                            temp++;
                            break;
                        }
                        
                    }
                  
                       
            }
            if(temp==m) count++;
            System.out.println("temp: "+temp);
        }
        fitness=count;
        System.out.println("count: "+count);
        return fitness;
    
    
    }
   public int assessfitness1(Vector v)
    {
        int i,j,k,count=0,temp,t1,t2;
        boolean boo;
        for(i=0;i<v.size();i++)
        {
            Clause cl=(Clause)v.elementAt(i);
            //System.out.println("i: "+i);
            for(k=0;k<cl.size;k++)
            {
                 t2=(int)cl.vec.elementAt(k);
                //System.out.print(t2+" ");
            }
            //System.out.println();
            temp=0;
            for(j=0;j<cl.size;j++)
            {
                boo=(boolean)vec.elementAt(j);
                t1=(int)cl.vec.elementAt(j);
                //if(boo==true && t1<0) 
                    if((boo==false && t1<0) || (boo==true && t1>0))
                    {
                        count++;
                        break;
                    }
                  
                       
            }
            //System.out.println("co: "+count);
        }
        fitness=count;
        return fitness;
    } 

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
    Clause selectwithrep(Vector pop) throws CloneNotSupportedException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
          System.out.println("sel");
        Clause best=new Clause(size);
         Clause next=new Clause(size);
         int t=5,i,r2,j;
         int popsize=pop.size();
         
         Random rand = new Random();
        Clause temp;
         int r1 = rand.nextInt(popsize);
         //System.out.println("r1:"+r1);
        best=(Clause) ((Clause)(pop.elementAt(r1))).clone();
        /*for(j=0;j<size;j++)
        {
            boolean b =(boolean)best.vec.elementAt(j);
            System.out.print(b+" ");
        }
        System.out.println();*/
        int fitnext,fitbest;
        fitnext=fitbest=0;
        for(i=2;i<t;i++)
        {
            r2 = rand.nextInt(popsize);
             //System.out.println("r2:"+r2);
            next=(Clause) ((Clause)(pop.elementAt(r2))).clone();
             /*for(j=0;j<size;j++)
                        {
                            boolean b =(boolean)next.vec.elementAt(j);
                            System.out.print(b+" ");
                        }
                        System.out.println();*/
            fitbest=best.fitness;
             fitnext=next.fitness;
              //System.out.println("fitb:"+fitbest+" ,fitne:"+fitnext);
             if(fitnext>fitbest) 
             {
                 best=(Clause)next.clone();
                   /*for(j=0;j<size;j++)
                        {
                            boolean b =(boolean)best.vec.elementAt(j);
                            System.out.print(b+" ");
                        }
                        System.out.println();*/
             }
             
             
            
            
        }
                         
        return best;
    
    
    }

    public void crossover(Clause pa,Clause pb) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Clause temp;
        Random rand = new Random();
         int ch=rand.nextInt(2);
         if(ch==0)  onepcrossover(pa,pb);
         else twopcrossover(pa,pb);
    
    
    
    }

    public void onepcrossover(Clause pa, Clause pb) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
          System.out.println("onepointcrossover:");
        boolean temp1,temp2;
        Random rand = new Random();
        int l=pa.size;
         int c=rand.nextInt(l); //0 to l-1
         int i;
           System.out.println("c:"+c);
         for(i=c;i<l;i++) //c incl kre,atleast 1ta holeo swap hbe
         {
             temp1=(boolean)pa.vec.elementAt(i);
             temp2=(boolean)pb.vec.elementAt(i);
             //pa.vec.elementAt(i).;
             pa.vec.set(i,temp2);
              pb.vec.set(i,temp1);
             
             
         }
    
    
    }

    public void twopcrossover(Clause pa, Clause pb) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
          System.out.println("twopointcrossover:");
        boolean temp1,temp2;
        Random rand = new Random();
        int l=pa.size;
         int c=rand.nextInt(l); //1 to l
          int d=rand.nextInt(l); //1 to l
           
          if(c>d) 
          {
              int temp=c;
              c=d;
              d=temp;
          }
         int i;
         System.out.println("c:"+c+",d:"+d);
         for(i=c;i<d;i++) //c incl kre
         {
             temp1=(boolean)pa.vec.elementAt(i);
             temp2=(boolean)pb.vec.elementAt(i);
             //pa.vec.elementAt(i).;
             pa.vec.set(i,temp2);
              pb.vec.set(i,temp1);
             
             
         }
    
    
    }

    public void mutate(Clause pa) throws CloneNotSupportedException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        double p=1.00/size;
        Random rand = new Random();
       //double min=0.00;
       //double max=1.00;
        Clause ca=new Clause(pa.size);
        //ca=(Clause) pa.clone();
       double val;
       int i,j;
       boolean b;
      System.out.print("mutation of: ");
      for(j=0;j<pa.size;j++)
      {
            boolean bh =(boolean)pa.vec.elementAt(j);
            System.out.print(bh+" ");
      }
      System.out.println();
       for(i=0;i<pa.size;i++)
       {
           
           //val=min + (max-min) * rand.nextDouble();
           val=rand.nextDouble();
            System.out.println("p:"+p+",val:"+val);
           if(p>=val)
           {
               System.out.println("p>=val,mutate now");
              b= (boolean) pa.vec.elementAt(i);
              if(b==true) 
              {
                  b=false;
                  
              }
              else  b=true;
              pa.vec.set(i, b);
           }
       }
        //return ca;
        
        
    
    }

   
    
    


    
    
}
