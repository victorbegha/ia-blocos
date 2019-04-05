/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aima.core.environment.blocks;
import aima.core.environment.jugs.JugsState;
import aima.core.search.framework.GoalTest;



public class BlocksGoalState  implements GoalTest {
    // objetivo
    char[][] goal = new char[3][3];
    
    // todos na mesa
    public boolean isGoalState(Object state) {
        BlocksState currentState = (BlocksState) state;
                if ((currentState.matriz[2][2]=='a') && (currentState.matriz[2][1]=='b') &&
                        (currentState.matriz[2][0]=='c'))
                    return true;
        return false;
    }
    
    
}

    
    
    