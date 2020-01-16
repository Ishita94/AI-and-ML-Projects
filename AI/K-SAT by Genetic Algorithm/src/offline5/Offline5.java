/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package offline5;

import java.util.Random;
import java.util.Scanner;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class Offline5 {

   
    public static void main(String[] args) throws CloneNotSupportedException {
        // TODO code application logic here
        int c,l,v,i,j;
        //System.out.println("K-SAT PROBLEM:");
        System.out.println("enter no of clauses:");
        Scanner sc=new Scanner(System.in);
        c=sc.nextInt();
        System.out.println("enter no of variables:");
        v=sc.nextInt();
        System.out.println("enter no of literals per clause:");
        l=sc.nextInt();
        System.out.println("enter clauses:");
        Vector allclause=new Vector(c);
        int wrong=0;
        for(i=0;i<c;i++)
        {
            Clause cl=new Clause(l);
            for(j=0;j<l;j++)
            {
                int a=sc.nextInt();
                int b=v*(-1);
                if(a<b ||a>v)
                {
                    wrong=1;
                    break;
                }
                cl.vec.add(a);
            }
            if(wrong==1) break;
            allclause.add(cl);
        }
        if(wrong==1) 
        {
             System.out.println("You enterd wrong");
        }
        else
        {
     
       int t,temp,r;
        Random rand = new Random();
        int ptemp=(int)Math.pow(2,l);
         int popsize = rand.nextInt(ptemp-3) + 4;
         System.out.println("popsize: "+popsize);
         Vector pop=new Vector();
         i=0;
         int temp2;
         
         //hashing+population create
         while(i!=popsize)
         {
             //System.out.println("i: "+i);
             Clause cl=new Clause(l);
             t=0;
             for(j=0;j<l;j++)
             {
                 temp2=rand.nextInt(2);
                 //System.out.println("temp2: "+temp2);
                 boolean element;
                 if(temp2==0) element=false;
                 else element=true;
                  //System.out.println("el: "+element);
                 cl.vec.add(element);
                 r=(int)Math.pow(2,l-j-1);
                 temp=r*temp2;
                 t+=temp;
                 
             }
             t=t%ptemp;
             //System.out.println("tf: "+t);
             cl.hashvalue=t;
             Clause ck;
             int check=0;
             for(int k=0;k<i;k++)
             {
                 ck=(Clause)pop.elementAt(k);
                 //System.out.println("ck.h: "+ck.hashvalue);
                 if(t==ck.hashvalue)
                 {
                     check=1;
                     break;
                     
                 }
             }
             if(check==0)
             {
                 i++;
                 pop.add(cl);
                  //System.out.println("cladd "+cl.hashvalue);
             }
        }
         
          Vector pop2=new Vector(popsize);
                     
             for(i=0;i<popsize;i++)
            {
                Clause cl=(Clause)pop.elementAt(i);
                 //System.out.println("i: "+i);
                pop2.add(cl);

            }
         work w=new work();
         w.dowork(0, c, v, l, pop, allclause); //ksat
          w.dowork(1, c, v, l, pop2, allclause);//exact m k sat
         
         /*for(i=0;i<popsize;i++)
        {
            Clause cl=(Clause)pop.elementAt(i);
             System.out.println("i: "+i);
            for(j=0;j<l;j++)
            {
                boolean b =(boolean)cl.vec.elementAt(j);
                System.out.print(b+" ");
            }
            System.out.println();
          
        }*/
         
         
        /* Clause best=new Clause(l);
         best.vec=null;
         System.out.println("enter option:");
         int g;
         Clause best2=null;
          //g=sc.nextInt();
          for(g=0;g<2;g++){
         int fiti,fitbest=0,iter=0,m=0;
        
         if(g==0) System.out.println("Showing k-sat:");
                    else 
                    {
                         System.out.println("Showing exactly m k-sat:");
                        System.out.println("Enter m:");
                        m=sc.nextInt();

                    }
                while( iter<20)
                {

                    if(fitbest==c) 
                    break;
                    
                    System.out.println("fitbestpresent: "+fitbest+",iter:"+iter);
                       popsize=pop.size();
                        for(i=0;i<popsize;i++)
                            {
                                Clause cl=(Clause)pop.elementAt(i);
                                 System.out.println("popinitial: "+i);
                                for(j=0;j<l;j++)
                                {
                                    boolean b =(boolean)cl.vec.elementAt(j);
                                    System.out.print(b+" ");
                                }
                                System.out.println();

                            }
                       if(g==0)
                       {
                                for(i=0;i<popsize;i++)
                                {
                                    Clause cl=(Clause)pop.elementAt(i);
                                    //System.out.println("i1st: "+i);

                                    fiti=cl.assessfitness1(allclause);
                                    if(best.vec!=null) fitbest=best.assessfitness1(allclause);
                                    if(best.vec==null || fiti>fitbest)
                                    {
                                        try {
                                            best=(Clause)cl.clone();
                                             fitbest=best.assessfitness1(allclause);
                                             System.out.println("fitbestup:"+fitbest);
                                System.out.println("bestup:");
                                        for(j=0;j<l;j++)
                                        {
                                            boolean b =(boolean)best.vec.elementAt(j);
                                            System.out.print(b+" ");
                                        }
                                        System.out.println();
                                            //best=(Clause)((Clause)best).clone();
                                        } catch (CloneNotSupportedException ex) {
                                            Logger.getLogger(Offline5.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                        //Clause ct=(Clause)best.vec.elementAt(i);


                                    }


                                }
                                System.out.println("fitbestnew:"+fitbest);
                                System.out.println("best:");
                                        for(j=0;j<l;j++)
                                        {
                                            boolean b =(boolean)best.vec.elementAt(j);
                                            System.out.print(b+" ");
                                        }
                                        System.out.println();
                       }
                       else
                      {
                          
                               for(i=0;i<popsize;i++)
                               {
                                   Clause cl=(Clause)pop.elementAt(i);
                                   //System.out.println("i1st: "+i);

                                   fiti=cl.assessfitness2(allclause,m);
                                   // System.out.println("fiti:"+fiti);
                                   if(best.vec!=null) fitbest=best.assessfitness2(allclause,m);
                                   if(best.vec==null || fiti>fitbest)
                                   {
                                       try {
                                           best=(Clause)cl.clone();
                                            fitbest=best.assessfitness2(allclause,m);
                                           //best=(Clause)((Clause)best).clone();
                                       } catch (CloneNotSupportedException ex) {
                                           Logger.getLogger(Offline5.class.getName()).log(Level.SEVERE, null, ex);
                                       }
                                       //Clause ct=(Clause)best.vec.elementAt(i);
                                       /*System.out.println("fitbest"+fitbest);
                                       for(j=0;j<l;j++)
                                       {
                                           boolean b =(boolean)best.vec.elementAt(j);
                                           System.out.print(b+" ");
                                       }
                                       System.out.println();

                                   }


                               }
                                  System.out.println("fitbestnew:"+fitbest);
                               System.out.println("best:");
                                       for(j=0;j<l;j++)
                                       {
                                           boolean b =(boolean)best.vec.elementAt(j);
                                           System.out.print(b+" ");
                                       }
                                       System.out.println();
                       }
                       best2=new Clause(l);
                       best2.fitness=best.fitness;
                        best2.size=best.size;
                                               for(int f=0;f<l;f++)
                                               {
                                                   boolean boo=(boolean)best.vec.elementAt(f);
                                                   best2.vec.add(boo);

                                               }


                       
                        Vector Q=new Vector();
                       //Clause pa.selectwithrep(pop);
                        for(int h=0;h<(popsize/2);h++)
                        {
                               System.out.println("h: "+h);
                               Clause n = new Clause(l);
                               Clause pa=n.selectwithrep(pop);
                               System.out.print("pa: ");
                               for(j=0;j<l;j++)
                                       {
                                           boolean b =(boolean)pa.vec.elementAt(j);
                                           System.out.print(b+" ");
                                       }
                                       System.out.println();
                               Clause pb=n.selectwithrep(pop);
                                System.out.print("pb: ");
                               for(j=0;j<l;j++)
                                       {
                                           boolean b =(boolean)pb.vec.elementAt(j);
                                           System.out.print(b+" ");
                                       }
                                       System.out.println();

                                n.crossover(pa,pb);
                                 System.out.print("newpa: ");
                               for(j=0;j<l;j++)
                                       {
                                           boolean b =(boolean)pa.vec.elementAt(j);
                                           System.out.print(b+" ");
                                       }
                                       System.out.println();
                               //Clause pb=n.selectwithrep(pop);
                                System.out.print("newpb: ");
                               for(j=0;j<l;j++)
                                       {
                                           boolean b =(boolean)pb.vec.elementAt(j);
                                           System.out.print(b+" ");
                                       }
                                       System.out.println();

                                           Clause m1=new Clause(l);
                                            Clause m2=new Clause(l);
                                           Clause ca=new Clause(l);
                                           Clause cb=new Clause(l);

                                               ca.fitness=pa.fitness;
                                               ca.size=pa.size;
                                               for(int k2=0;k2<l;k2++)
                                               {
                                                   boolean boo=(boolean)pa.vec.elementAt(k2);
                                                   ca.vec.add(boo);

                                               }
                                           cb.fitness=pb.fitness;
                                               cb.size=pb.size;
                                               for(int k2=0;k2<l;k2++)
                                               {
                                                   boolean boo=(boolean)pb.vec.elementAt(k2);
                                                   cb.vec.add(boo);

                                               }
                                           /* System.out.print("capai: ");
                               for(j=0;j<ca.size;j++)
                                       {
                                           boolean bh =(boolean)ca.vec.elementAt(j);
                                           System.out.print(bh+" ");
                                       }
                                       System.out.println();
                                        System.out.print("capai: ");
                               for(j=0;j<cb.size;j++)
                                       {
                                           boolean bh =(boolean)cb.vec.elementAt(j);
                                           System.out.print(bh+" ");
                                       }
                                       System.out.println();


                                           //ca=(Clause) pa.clone();
                                            //cb=(Clause) pb.clone();
                                       m1.mutate(ca);
                                      m2.mutate(cb);

                                       System.out.print("newpamutate: ");
                               for(j=0;j<l;j++)
                                       {
                                           boolean b =(boolean)ca.vec.elementAt(j);
                                           System.out.print(b+" ");
                                       }
                                       System.out.println();
                               //Clause pb=n.selectwithrep(pop);
                                System.out.print("newpbmutate: ");
                               for(j=0;j<l;j++)
                                       {
                                           boolean b =(boolean)cb.vec.elementAt(j);
                                           System.out.print(b+" ");
                                       }
                                       System.out.println();

                                       Q.add(ca);
                                       Q.add(cb);

                        }
                        for(i=0;i<Q.size();i++)
               {
                   Clause cl=(Clause)Q.elementAt(i);
                    System.out.println("Qi: "+i);
                   for(j=0;j<l;j++)
                   {
                       boolean b =(boolean)cl.vec.elementAt(j);
                       System.out.print(b+" ");
                   }
                   System.out.println();

               }
                        pop=(Vector) Q.clone();
                        /*for(i=0;i<pop.size();i++)
               {
                   Clause cl=(Clause)pop.elementAt(i);
                    System.out.println("notunpop: "+i);
                   for(j=0;j<l;j++)
                   {
                       boolean b =(boolean)cl.vec.elementAt(j);
                       System.out.print(b+" ");
                   }
                   System.out.println();

               }
                        
                      iter++;
                }
                
                System.out.println("iterations needed:"+iter);
                    System.out.println("BestFinal:");
                   for(j=0;j<l;j++)
                   {
                       boolean b =(boolean)best2.vec.elementAt(j);
                       System.out.print(b+" ");
                   }
                    System.out.println("BestFit:"+fitbest);
                   System.out.println();
                
         
         }
         

                        
                
        } */ 
        }
            
         }
}
         
        
         
         
        
        
        
        
        
    //}
    
