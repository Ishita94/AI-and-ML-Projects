/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author ishita
 */
public class ML1 {

    private static void testing(data test) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * @param args the command line arguments
     */
     //static node result;
   ML1()
   {
      // result=new node();
      // result=null;
   }
   public static double entropy(int countpos,int countneg,int size)
      {
          double en=0;
          //System.out.println(countpos +"cp vitore "+size+" s");
          if(countpos==0 || countneg==0) en=0;
          else
          {
            double p=countpos*(1.00)/size;
             double n=countneg*1.00/size;
            //en=p*(Math.log(p)/Math.log(2))-n*(Math.log(n)/Math.log(2));
             en=-(p)*(Math.log(p)/Math.log(2))-(n)*(Math.log(n)/Math.log(2));
           // System.out.println("p: "+p+" ,n: "+n+" ,en: "+en);
            
          }
          
                      
          return en;
      }
    public static int infogain( node nodenew,int size,int countpos,int countneg)
      {
          int i,j,pos,neg,k,countj,index;
          int f=9; //feature with highest info gain
          double infofinal=-999;
          double infog;
          for(i=0;i<8;i++) //protita feature
          {
              index=2;
              if(nodenew.featurelist[i]==0)
              {
                  //System.out.println("i="+i );
                  index=2;
                  infog=nodenew.hs; //protita feature er jonno age H(s) boshaye nilam
                                    //infog hocche ekta feature er infogain
                  // System.out.println(infog +" ingogst");
                  for(j=1;j<11;j++)
                  {
                      index=2;
                        pos=0;
                        neg=0;
                        countj=0;
                        for(k=0;k<nodenew.S.size();k++)
                        {
                              if((nodenew.S.get(k).arr[i])==j)
                              {
                                 // System.out.println("i:" +i+",j= "+j+" ,index:"+index);
                                  if(nodenew.S.get(k).Class==0) neg++;
                                  else pos++;
                                  countj++;
                                  
                              }
                              index++;
                        }
                         //double p=countpos*(-1.00)/size;
                          //double n=countneg*1.00/size;
                         // nodenew.hs=(p*(Math.log(p)/Math.log(2)))-(n*(Math.log(n)/Math.log(2)));
                        double enf=entropy(pos,neg, size);
                        double div=countj*1.00/size;
                        enf=div*enf;
                        infog=infog-enf;
                            


                      
                  }
                  
                  if(infog>infofinal)
                  {
                      infofinal=infog;
                      f=i;
                  }
                  
              }
          }
          
          return f;
      }
    public static node testings(node test,node root) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
      int fval=0,fno,i,j;
      for(i=0;i<test.S.size();i++)
      {
          for(j=0;j<8;j++)
            {
                System.out.print(test.S.get(i).arr[j]+" ");
            }
            System.out.println();
            node roottemp=root;
             while(roottemp.label==-1)
             {
                     fno=roottemp.featindex;
                   //  System.out.println(fno+" fno");
                    // System.out.println(fno+" jjjjjj");
                     fval=(test.S.get(i).arr[fno])-1;
                   //  System.out.println(fval+" fval");
                     //System.out.println(root.child[fval].label+" jjjjjj");
                    roottemp=roottemp.child[fval];
                    //break;

             }
             if(roottemp.label==0) {
                // System.out.println("class is 0");
                 test.S.get(i).testclass=0;
             }
             else if(roottemp.label==1){
                // System.out.println("class is 1");
                 test.S.get(i).testclass=1;
             }
             
      }
      return test;
      
    }
     
    
     public static node DT( node nodenow )
      {
         
          int a=nodenow.checkallpos();
          if(a==1) //all pos
          {
              nodenow.label=1;
              //System.out.println(a +"pos");
              return nodenow;
              
          }
          
          a=nodenow.checkallneg();
          if(a==1) //all neg
          {
              nodenow.label=0;
             // System.out.println(a +"neg");
              return nodenow;
          }
          int countpos=0,i;
         int countneg=0;
       //  System.out.println("mix" );
         for(i=0;i<nodenow.S.size();i++)
         {
                if(nodenow.S.get(i).Class==1) countpos++;
                else countneg++;

         }
         // System.out.println("featnum="+nodenow.featnum );
           if(nodenow.featnum==0)   //majoritylabel
           {
               if(countpos>=countneg) 
                {
                    nodenow.label=1; 
                }
                else nodenow.label=0;
               return nodenow;
            }
           int size=nodenow.S.size();
           //double p=countpos*(-1.00)/size;
            //double n=countneg*1.00/size;
         //  System.out.println(countpos +"coutp");
          // System.out.println(countneg +"coutneg");
           nodenow.hs=entropy(countpos,countneg,size);
          
           nodenow.countneg=countneg;
           nodenow.countpos=countpos;
           int featno=infogain(nodenow,size,countpos,countneg);

              // System.out.println(featno +"featno");
              nodenow.featindex=featno; //ei node theke jei feature choose holo=childrenra jei feature e choose hobe
              
              //nodenow.featnum--;
              //nodenow.featurelist[featno]=1;
          int f,g,m,fnow,b,c,dd=0;
          for(f=0;f<10;f++)
          {
               //if(nodenow.featindex==1 && f==2 ) System.out.println("findddddddddddd");
           //   System.out.println("f:"+f);
              nodenow.child[f]=new node();
              nodenow.parent=new node();
              nodenow.child[f].parent=nodenow;
              
              
              for(g=0;g<8;g++)
              {
                  nodenow.child[f].featurelist[g]=nodenow.featurelist[g];
                 // b=nodenow.child[f].featurelist[g];
                  //c=nodenow.featurelist[g];
                  //System.out.println();
                  
              }
             nodenow.child[f].featnum=nodenow.featnum-1; //parent er theke 1 kom feature tar kache available hbe
             nodenow.child[f].featurelist[featno]=1; //child er kache oi feature unavailable hoye gelo
                   // System.out.println("nodenow f "+f);
                    
              for(g=0;g<nodenow.S.size();g++)
              {
                 fnow= nodenow.S.get(g).arr[nodenow.featindex]; //g no line er featindex featurer value koto
                 //System.out.println("fnow="+fnow+"g="+g);

                 if(fnow==(f+1))
                 {
                    // System.out.println("fnowfefe=ind="+g);
                   data n=new data();
                    for(int k=0;k<8;k++)
                    {
                       n.arr[k]=nodenow.S.get(g).arr[k];
                    }
                    n.Class=nodenow.S.get(g).Class;
                    n.m=g;
                     nodenow.child[f].S.add(n);
                     
                      //System.out.println("gnoww="+g);
                     
                     dd++;
                     
                 }
                    
                 // nodenow.child[f].S.get(g).arr[g]=nodenow.S.get(i).arr[g];
              }
              //System.out.println("totaldd="+dd);
              
            //  System.out.println("childfsize="+nodenow.child[f].S.size());
                

               int chsize=nodenow.child[f].S.size();
               // System.out.println("childfsize="+nodenow.child[f].S.size());   
              //nodenow.child[f].S.get(f).Class=nodenow.S.get(f).Class;
               if(chsize==0)
               {
                   if(countpos>=countneg){ nodenow.child[f].label=1;}
                   else nodenow.child[f].label=0;
                    //System.out.println("pos="+countpos+",neg="+countneg+" ,childlabel="+nodenow.child[f].label);
                   
               }
               else
               {
                  //System.out.println("recur: ");   
                   node no=DT(nodenow.child[f]);
               }
              
              
              
          }
          
          return nodenow;
      //    return result;
      }
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
       // BufferedReader reader =new BufferedReader(new FileReader("D:\\BUET\\L4T2\\ML lab\\Assignment1\\Assignment1_data_set.csv"));
        BufferedReader reader =new BufferedReader(new FileReader("src/Assignment1_data_set.csv"));
//String datarow="";
        String dataln = reader.readLine();
        int numlines=0,i=0,j;
       part []datapart=new part[5];
        //ArrayList<part> datapart=new ArrayList<part>();
       for(j=0;j<5;j++)
       {
            datapart[j] = new part();
            //datapart[j].row

         //ArrayList<data> row=new ArrayList<data>();

         while((dataln=reader.readLine())!=null)
         {
             String [] temp =dataln.trim().split(",");
             data n=new data();
             for(int k=0;k<8;k++)
             {
                n.arr[k]=Integer.parseInt(temp[k]);
             }
           
             n.Class=Integer.parseInt(temp[8]);
              datapart[j].row.add(n);
             //datapart[j].row.add(n);
             
             ++numlines;
             if(numlines%134==0) break;
         }

         }
        
       int in=0,op,op2;
        double tprf,tnrf,ppvf,npvf,fprf,fnrf,fdrf,f1scoref,accf;
        tprf=0;
        tnrf=0;
        ppvf=0;
        npvf=0;
        fprf=0;
        fnrf=0;
        fdrf=0;
        f1scoref=0;
        accf=0;
       for(in=0;in<5;in++)
       {
                    System.out.println("in="+in);
                    node root=new node();
                    data m,n;

                    for(op=0;op<5;op++)
                    {
                        if(op!=in)
                        {
                            System.out.println(datapart[op].row.size());
                            for(op2=0;op2<datapart[op].row.size();op2++)
                             {
                                 
                                 n=datapart[op].row.get(op2);
                                 root.S.add(n);
                                 
                             }

                        }
                    }



                    root.parent=null;
                   //int []feat={0,0,0,0,0,0,0,0};
                    //result=null;
                   double tpr,tnr,ppv,npv,fpr,fnr,fdr,f1score,acc;
                   node result= DT(root);
                   node testdata=new node();
                      for(op=0;op<datapart[in].row.size();op++)
                      {
                           m=datapart[in].row.get(op);
                            testdata.S.add(m);
                           //testings(m,result);

                      }

                        System.out.println("tesdata size="+testdata.S.size());
                        node testresult=  testings(testdata,result);
                        double tp=0,tn=0,fn=0,fp=0;
                        for(op=0;op<testresult.S.size();op++)
                        {
                             m=testresult.S.get(op);    //class=actual,testclass=predicted
                             if(m.Class==1 && m.testclass==1) tp++; 
                             else if(m.Class==0 && m.testclass==0) tn++;
                             else if(m.Class==1 && m.testclass==0) fn++;
                             else if(m.Class==0 && m.testclass==1) fp++;

                        }

                        System.out.println("tp="+tp+" tn= "+tn+" fn= "+fn+" fp= "+fp);

                        tpr=tp/(tp+fn);
                        tnr=tn/(tn+fp);
                        ppv=tp/(tp+fp);
                        npv=tn/(tn+fn);
                        fpr=fp/(fp+tn);
                        fnr=fn/(fn+tp);
                        fdr=fp/(tp+fp);
                        f1score=(2*tp)/((2*tp)+fp+fn);
                        acc=((tp+tn)/(tp+fp+tn+fn))*100;

                        tprf=tprf+tpr;
                        tnrf=tnrf+tnr;
                        ppvf=ppvf+ppv;
                        npvf=npvf+npv;
                        
                        fprf=fprf+fpr;
                        fnrf=fnrf+fnr;
                        fdrf=fdrf+fdr;
                        f1scoref=f1scoref+f1score;
                        accf=accf+acc;
                        
                        System.out.println("tpr="+tpr);
                         System.out.println("tnr="+tnr);
                          System.out.println("ppv="+ppv);
                           System.out.println("npv="+npv);
                            System.out.println("fpr="+fpr);
                             System.out.println("fnr="+fnr);
                              System.out.println("fdr="+fdr);
                               System.out.println("f1score="+f1score);
                                System.out.println("acc="+acc);



       }
                     tprf=tprf/5;
                        tnrf=tnrf/5;
                        ppvf=ppvf/5;
                        npvf=npvf/5;
                        
                        fprf=fprf/5;
                        fnrf=fnrf/5;
                        fdrf=fdrf/5;
                        f1scoref=f1scoref/5;
                        accf=accf/5;
                        
                         System.out.println("tpr avg="+tprf);
                         System.out.println("tnr avg="+tnrf);
                          System.out.println("ppv avg="+ppvf);
                           System.out.println("npv avg="+npvf);
                            System.out.println("fpr avg="+fprf);
                             System.out.println("fnr avg="+fnrf);
                              System.out.println("fdr avg="+fdrf);
                               System.out.println("f1score avg="+f1scoref);
                                System.out.println("acc avg="+accf);


                              
     }
    
}
