/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;

/**
 *
 * @author ishita
 */
public class node {
    int label,value;
    int featindex;
    node parent;
    node []child;
    ArrayList <data> S;
    int m;
    int []featurelist={0,0,0,0,0,0,0,0}; //unused
    int featnum;
    int countneg;
    int countpos;
    double alpha;
    double hs;
    
    node()
    {
        child=new node[10];
//        parent=new node();
        S=new ArrayList<data>();
        featnum=8;
        label=-1; //internal nodes er label=-1,leaf er 1/0
       hs=0;
        
    }
     public int checkallpos( )
     {
         int a=1;
         int i;
         for(i=0;i<S.size();i++)
         {
             if(S.get(i).Class==0)
             {
                 a=0; //mane neg o ase result e,all pos na
                 break;
             }
         }
         return a;
         
     }
      public int checkallneg( )
     {
         int a=1;
          int i;
         for(i=0;i<S.size();i++)
         {
             if(S.get(i).Class==1)
             {
                 a=0; //mane pos o ase result e,all neg na
                 break;
             }
         }
         
         return a;
         
     }
    
    public void setfeat(int index,int value)
    {
        featurelist[index]=value;
    }

  
}
