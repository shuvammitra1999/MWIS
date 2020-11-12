package com.company;

public class MWIS {

    float messagePassing(double[][] messagesPrev, int i, int j, int[][] adj) {
        float product = 1;

        for (int k = 0; k < 5; k++) {

            if (adj[k][i] == 1 && k != j) { //get messages to i from all neighbours except j
                product *= messagesPrev[k][i]; //multiply with current product
            }

        }
        return product;
    }

    Messages getAllMessages(Messages messagesPrev, int[][] adj, double[] trackscores) {

        double[][] messagesOneCurr = new double[5][5];
        double[][] messagesZeroCurr = new double[5][5];

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {

                if (adj[i][j] == 1) { //check so that only adjacent vertices pass messages
                    //according to EQN 17
                    messagesOneCurr[i][j] = messagePassing(messagesPrev.messagesZero, i, j, adj);
                    messagesZeroCurr[i][j] = (float) Math.max(messagesOneCurr[i][j], (Math.exp(trackscores[i]) * messagePassing(messagesPrev.messagesOne, i, j, adj)));
                }

            }
        }
        messagesPrev = new Messages(messagesOneCurr, messagesZeroCurr); // make messagesprev refer to new messages object with current values
        return messagesPrev;
    }

    void initialise(double[][] messagezero, double[][] messageone, double[] trackscore, int[] independentset) {
        int i, j;

        for (i = 0; i < 5; i++)
            for (j = 0; j < 5; j++) {
                messagezero[i][j] = (float) Math.exp(trackscore[i]);
                messageone[i][j] = 1;
                independentset[i] = 0;
            }

    }

    int checkconvergence(int[] independentsetprev, int[] independentsetcur) {
        int flag = 1;
        for (int i = 0; i < 5; i++) {
            if (independentsetcur[i] != independentsetprev[i]) {
                flag = 0;
                break;
            }
        }
        return flag;
    }

    void updateBelief(int nodes, double[][] messagesZeroCurr, double[][] messagesOneCurr, int i, int[] indpSet, double[] trackScores, int[][] adj){
        double b0=1, b1;
        b1= java.lang.Math.exp(trackScores[i]);
        for(int k=0; k<nodes; k++)

        {
            if(adj[i][k]==1){
                b0=b0*messagesZeroCurr[k][i];
                b1=b1*messagesOneCurr[k][i];
            }

        }
        if (b1>b0)
        {
            indpSet[i]=1;
        }
        else{
            indpSet[i]=0;
        }
    }




    int[] allBeliefs(Messages messagesCurr, int[] indpSet,double[] trackscores, int[][] adj){
        for(int i=0;i<5;i++){
            updateBelief(5,messagesCurr.messagesZero,messagesCurr.messagesOne,i,indpSet,trackscores,adj);
        }
        return indpSet;
    }

}
