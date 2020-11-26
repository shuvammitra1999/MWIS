package com.company;

import java.util.Arrays;

public class Main{
    public static void main(String[] args){
        MWIS x= new MWIS();
        double[][] messagesOneCurr = new double[5][5];
        double[][] messagesZeroCurr = new double[5][5];
        double[] trackscores = {3.4, 9.1, 7.5, 4.8, 10.1};
        int[] independentset = new int[5];
        int[] independentsetprev;
        independentsetprev= new int[]{0, 0, 0, 0, 0};
        x.initialise(messagesZeroCurr,messagesOneCurr,trackscores,independentset);

        int [][] adj= {
                {0, 1, 1, 0, 0},
                {1, 0, 1, 1, 0},
                {1, 1, 0, 1, 1},
                {0, 1, 1, 0, 1},
                {0, 0, 1, 1, 0}
        };

        Messages messagesCurr=new Messages(messagesOneCurr,messagesZeroCurr);
        //int k=0;
        for(;;) {

            messagesCurr = x.getAllMessages(messagesCurr, adj, trackscores); //getting all messages for current iteration

            //belief propagation
            independentset = x.allBeliefs(messagesCurr, independentset, trackscores, adj);

            //check convergence
            //if(x.checkconvergence(independentsetprev,independentset)==1) break;
            //System.out.println(Arrays.toString(independentset));
            if (java.util.Arrays.equals(independentsetprev, independentset)) break;
            independentsetprev = independentset.clone();

        }


        //print independent set
        System.out.println(Arrays.toString(independentset));
        //System.out.println(k);
    }

}

