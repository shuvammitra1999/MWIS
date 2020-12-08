package com.company;

public class MWIS {

    double messagePassing(int n, double[][] messagesPrev, int i, int j, int[][] adj) {
        double product = 1.0;

        for (int k = 0; k < n; k++) {

            if (adj[k][i] == 1 && k != j) { //get messages to i from all neighbours except j
                product *= messagesPrev[k][i]; //multiply with current product
            }

        }
        return product;
    }

    Messages getAllMessages(int n, Messages messagesPrev, int[][] adj, double[] trackscores) {

        double[][] messagesOneCurr = new double[n][n];
        double[][] messagesZeroCurr = new double[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                if (adj[i][j] == 1) { //check so that only adjacent vertices pass messages
                    //according to EQN 17
                    messagesOneCurr[i][j] = messagePassing(n,messagesPrev.messagesZero, i, j, adj);
                    messagesZeroCurr[i][j] = Math.max(messagesOneCurr[i][j], (Math.exp(trackscores[i]) * messagePassing(n,messagesPrev.messagesOne, i, j, adj)));
                }

            }
        }
        messagesPrev = new Messages(messagesOneCurr, messagesZeroCurr); // make messagesprev refer to new messages object with current values
        return messagesPrev;
    }

    void initialise(int n, double[][] messagezero, double[][] messageone, double[] trackscore, int[] independentset,int[] independentsetPrev) {
        int i, j;

        for (i = 0; i < n; i++) {

            independentset[i] = 0;
            independentsetPrev[i] = 0;
            for (j = 0; j < n; j++) {
                messagezero[i][j] = Math.exp(trackscore[i]);
                messageone[i][j] = 1;

            }
        }
    }

//    int checkconvergence(int n, int[] independentsetprev, int[] independentsetcur) {
//        int flag = 0;
//        for (int i = 0; i < n; i++) {
//            if (independentsetcur[i] != independentsetprev[i]) {
//                flag = 0;
//                break;
//            }
//        }
//        return flag;
//    }

    void updateBelief(int nodes, double[][] messagesZeroCurr, double[][] messagesOneCurr, int i, int[] indpSet, double[] trackScores, int[][] adj){
        double b0=1.0, b1;
        b1 = java.lang.Math.exp(trackScores[i]);
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




    int[] allBeliefs(int n, Messages messagesCurr, int[] indpSet,double[] trackscores, int[][] adj){
        for(int i=0;i<5;i++){
            updateBelief(n,messagesCurr.messagesZero,messagesCurr.messagesOne,i,indpSet,trackscores,adj);
        }
        return indpSet;
    }

}
