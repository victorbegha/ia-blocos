/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aima.core.environment.friends;

import java.util.Vector;
import aima.core.agent.Action;
import aima.core.agent.impl.DynamicAction;
import aima.core.search.framework.ActionsFunction;
import aima.core.search.framework.ResultFunction;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 *
 * @author jcarlos
 */
public class FriendsFunctionFactory {

	private static ActionsFunction _actionsFunction = null;
	private static ResultFunction _resultFunction = null;

	public static ActionsFunction getActionsFunction() {
		if (null == _actionsFunction) {
			_actionsFunction = new FriendsActionsFunction();
		}
		return _actionsFunction;
	}

	public static ResultFunction getResultFunction() {
		if (null == _resultFunction) {
			_resultFunction = null;
		}
		return _resultFunction;
	}

        
         
	private static class FriendsActionsFunction implements ActionsFunction {

            public Set<Action> actions(Object state) {
			FriendsState board = (FriendsState) state;

			Set<Action> actions = new LinkedHashSet<Action>();

                        if (board.moveA()) {
                            java.util.Enumeration e = board.actions.elements();
                            while (e.hasMoreElements()) {
                                DynamicAction dAction = (DynamicAction) e.nextElement();
                                String actionName = dAction.getName();
                                if (actionName.startsWith(board.getCityA())) {
                                    actions.add(dAction);
                                }
                            }
                            
                        }
                        

			

			return actions;
		}
	}

	private static class JugsResultFunction implements ResultFunction {
		public Object result(Object s, Action a) {
	

			

			// The Action is not understood or is a NoOp
			// the result will be the current state.
			return s;
		}
	}
}
    
