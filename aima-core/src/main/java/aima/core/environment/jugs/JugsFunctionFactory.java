/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aima.core.environment.jugs;

import aima.core.agent.Action;
import aima.core.search.framework.ActionsFunction;
import aima.core.search.framework.ResultFunction;
import java.util.LinkedHashSet;
import java.util.Set;




public class JugsFunctionFactory {
	private static ActionsFunction _actionsFunction = null;
	private static ResultFunction _resultFunction = null;

	public static ActionsFunction getActionsFunction() {
		if (null == _actionsFunction) {
			_actionsFunction = new JugsActionsFunction();
		}
		return _actionsFunction;
	}

	public static ResultFunction getResultFunction() {
		if (null == _resultFunction) {
			_resultFunction = new JugsResultFunction();
		}
		return _resultFunction;
	}

	private static class JugsActionsFunction implements ActionsFunction {
		public Set<Action> actions(Object state) {
			JugsState board = (JugsState) state;

			Set<Action> actions = new LinkedHashSet<Action>();
                        
                        int x = board.state[0];
                        int y = board.state[1];

			if (x>0) { // pote de 5L esta cheio
                            actions.add(board.ESVASIAR_4L);
			}
                        if (y>0) { // pote de 3L esta cheio
                            actions.add(board.ESVASIAR_3L);
			}
                        
                        if (x<4)
                            actions.add(board.COMPLETAR_4L);
			
                        if (y<3)
                            actions.add(board.COMPLETAR_3L);

                        if ((x+y)>0 && (x+y)<=3 && (x>0) && (y<3))
                            actions.add(board.DESPEJAR_4L_3L);

                        if ((x+y)>0 && (x+y)<=4 && (y>0) && (x<4) )
                            actions.add(board.DESPEJAR_3L_4L);
                      
                        if ((x+y)>0 && (x+y)>=3 && (x>0) && (y<3))
                            actions.add(board.COMPLETAR_3L_C4L);
                         	
                        if ((x+y)>0 && (x+y)>=4 && (y>0) && (x<4))
                            actions.add(board.COMPLETAR_4L_C3L);


			return actions;
		}
	}

	private static class JugsResultFunction implements ResultFunction {
		public Object result(Object s, Action a) {
			JugsState board = (JugsState) s;
                        JugsState newBoard = new JugsState();
                        newBoard.setState(0, 0);

			if (board.ESVASIAR_3L.equals(a)) {
                            newBoard.state[0]=board.state[0];
			    newBoard.state[1]=0;
			} else if (board.ESVASIAR_4L.equals(a)) {
                            newBoard.state[1]=board.state[1];
			    newBoard.state[0]=0;
                        } else if (board.COMPLETAR_3L.equals(a)) {
                            newBoard.state[1]=3;
			    newBoard.state[0]=board.state[0];
                        } else if (board.COMPLETAR_4L.equals(a)) {
                            newBoard.state[1]=board.state[1];
			    newBoard.state[0]=4;
                        } else if (board.COMPLETAR_3L_C4L.equals(a)) {
                            newBoard.state[1]=3;
    			    newBoard.state[0]=board.state[0]-(3-board.state[1]);
                        } else if (board.COMPLETAR_4L_C3L.equals(a)) {
                            newBoard.state[0]=4;
    			    newBoard.state[1]=board.state[1]-(4-board.state[0]);
                        } else if (board.DESPEJAR_3L_4L.equals(a)) {
                            newBoard.state[1]=0;
    			    newBoard.state[0]=board.state[0]+board.state[1];
                        } else if (board.DESPEJAR_4L_3L.equals(a)) {
                            newBoard.state[1]=0;
    			    newBoard.state[0]=board.state[1]+board.state[0];
                        } else newBoard=null;


                        System.out.println("---------------------");
                        System.out.println(board.getStateAsString());
                        System.out.println(a.toString());
                        System.out.println(newBoard.getStateAsString());
                        if (newBoard!=null)
                            return newBoard;
                        else return s;
		}
	}
}