/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aima.core.environment.blocks;
import aima.core.agent.Action;
import aima.core.agent.impl.DynamicAction;



public class BlocksState {
    
    public Action a0to1 = new DynamicAction("'a' from 0 to 1");
    public Action a0to2 = new DynamicAction("'a' from 0 to 2");
    public Action a1to0 = new DynamicAction("'a' from 1 to 0");
    public Action a1to2 = new DynamicAction("'a' from 1 to 2");
    public Action a2to0 = new DynamicAction("'a' from 2 to 0");
    public Action a2to1 = new DynamicAction("'a' from 2 to 1");
    
    public Action b0to1 = new DynamicAction("'b' from 0 to 1");
    public Action b0to2 = new DynamicAction("'b' from 0 to 2");
    public Action b1to0 = new DynamicAction("'b' from 1 to 0");
    public Action b1to2 = new DynamicAction("'b' from 1 to 2");
    public Action b2to0 = new DynamicAction("'b' from 2 to 0");
    public Action b2to1 = new DynamicAction("'b' from 2 to 1");
    
    public Action c0to1 = new DynamicAction("'c' from 0 to 1");
    public Action c0to2 = new DynamicAction("'c' from 0 to 2");
    public Action c1to0 = new DynamicAction("'c' from 1 to 0");
    public Action c1to2 = new DynamicAction("'c' from 1 to 2");
    public Action c2to0 = new DynamicAction("'c' from 2 to 0");
    public Action c2to1 = new DynamicAction("'c' from 2 to 1");


    
    
    public char[][] matriz = new char[3][3];
    
    public BlocksState() {
        
        // Inicialização:
        matriz[0][0]= 'a'; 
        matriz[1][0]= 'b';
        matriz[2][0]= 'c';
        for(int i=0; i<3; i++) {
            for (int j=1; j<3; j++) {
                matriz[i][j] = '0';
            }
        }
    }
    
    public void printMatriz() {
        System.out.println("_______");
        System.out.println(matriz[0][0] + matriz[0][1] + matriz[0][2]);
        System.out.println(matriz[1][0] + matriz[1][1] + matriz[1][2]);
        System.out.println(matriz[2][0] + matriz[2][1] + matriz[2][2]);
        System.out.println("_______");
    }
    
}
 
 
