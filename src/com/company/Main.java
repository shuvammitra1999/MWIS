package com.company;

import java.util.Arrays;

public class Main{
    public static void main(String[] args){
        MWIS x= new MWIS();
        double[][] messagesOneCurr = new double[5][5];
        double[][] messagesZeroCurr = new double[5][5];
        double[] trackscores =  {0,4,4,0,10};
        int[] independentset = new int[5];
        int[] independentsetprev;
        independentsetprev= new int[]{0, 0, 0, 0, 0};
        x.initialise(messagesZeroCurr,messagesOneCurr,trackscores,independentset);

        int [][] adj={
                {0,1,1,1,0},
                {1,0,0,0,1},
                {1,0,0,0,1},
                {1,0,0,0,1},
                {0,1,1,1,0}
        };

        Messages messagesCurr=new Messages(messagesOneCurr,messagesZeroCurr);
        int k=0;
        for(;;){

            messagesCurr=x.getAllMessages(messagesCurr,adj,trackscores); //getting all messages for current iteration

            //belief propagation
            independentset=x.allBeliefs(messagesCurr,independentset,trackscores,adj);

            //check convergence
            if(x.checkconvergence(independentsetprev,independentset)==1) break;


            independentsetprev=independentset;
        }


        //print independent set
        System.out.println(Arrays.toString(independentset));
        System.out.println(k);
    }

}

