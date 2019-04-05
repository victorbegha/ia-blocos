/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aima.core.environment.friends;

/**
 *
 * @author jcarlos
 */
import aima.core.search.framework.GoalTest;


public class FriendsTest implements GoalTest {
    
    public boolean isGoalState(Object state) {
	      FriendsState currentState = (FriendsState) state;
		return (currentState.getCityA()==currentState.getCityB());
    }
    
}
