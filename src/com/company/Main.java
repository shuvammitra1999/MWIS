package com.company;

import java.util.Arrays;

public class Main{
    public static void main(String[] args){
        MWIS x= new MWIS();
        int n=5;
        double[][] messagesOneCurr = new double[n][n];
        double[][] messagesZeroCurr = new double[n][n];
        double[] trackscores = {3.4, 9.1, 7.5, 4.8, 10.1};
        int[] independentset = new int[n];
        int[] independentsetprev = new int[n];
        x.initialise(n,messagesZeroCurr,messagesOneCurr,trackscores,independentset,independentsetprev);

        int [][] adj= {
                {0, 1, 1, 0, 0},
                {1, 0, 1, 1, 0},
                {1, 1, 0, 1, 1},
                {0, 1, 1, 0, 1},
                {0, 0, 1, 1, 0}
        };
//

        Messages messagesCurr=new Messages(messagesOneCurr,messagesZeroCurr);
//        int k=0;
        for(;;) {

            messagesCurr = x.getAllMessages(n,messagesCurr, adj, trackscores); //getting all messages for current iteration

            //belief propagation
            independentset = x.allBeliefs(n, messagesCurr, independentset, trackscores, adj);

            //check convergence
            //if(x.checkconvergence(independentsetprev,independentset)==1) break;
            System.out.println(Arrays.toString(independentset));
            if (java.util.Arrays.equals(independentsetprev, independentset)) break;
            independentsetprev = independentset.clone();

        }


        //print independent set
        System.out.println(Arrays.toString(independentset));
        //System.out.println(k);
    }

}

