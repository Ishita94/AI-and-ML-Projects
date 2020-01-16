/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package offline5;

import java.util.Scanner;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class work {
    
    public work()
    {
        
    }
    public void dowork(int option,int c,int v,int l,Vector pop,Vector allclause) throws CloneNotSupportedException
    {
        Clause best=new Clause(l);
         best.vec=null;
         System.out.println("enter option:");
         int g,popsize,i,j;
         Clause best2=null;
          //g=sc.nextInt();
         Scanner sc=new Scanner(System.in);
        
            
        
         int fiti,fitbest=0,iter=0,m=0;
        
         if(option==0) System.out.println("Showing k-sat:");
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
                       if(option==0)
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
                                       System.out.println();*/

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
                                       System.out.println();*/


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

               }*/
                        
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

                        
                
        }  
    

