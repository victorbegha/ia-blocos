/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aima.core.environment.jugs;

import aima.core.search.framework.GoalTest;

/**
 *
 * @author jcarlos
 */
public class JugsGoalTest implements GoalTest {
    
    int goalA = 4; 
    int goalB = 0;
    
    
    public boolean isGoalState(Object state) {
		JugsState currentState = (JugsState) state;
                if ((currentState.state[0]==goalA) && (currentState.state[1]==goalB))
                    return true;
		return false;
    }
    
    public void setGoal(int a, int b) {
        this.goalA=a;
        this.goalB=b;
    }
    
}
