package aima.test.core.unit.search.uninformed;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import aima.core.agent.Action;
import aima.core.environment.nqueens.NQueensBoard;
import aima.core.environment.nqueens.NQueensFunctionFactory;
import aima.core.environment.nqueens.NQueensGoalTest;
import aima.core.environment.jugs.*;
import aima.core.search.framework.Problem;
import aima.core.search.framework.Search;
import aima.core.search.framework.SearchAgent;
import aima.core.search.uninformed.DepthLimitedSearch;
import aima.core.agent.impl.DynamicAction;

public class DepthLimitedSearchTest {


    
	@Test
	public void testSuccesfulDepthLimitedSearch() throws Exception {
                System.out.println("ESTOU AQUI");
		Problem problem = new Problem(new NQueensBoard(8),
				NQueensFunctionFactory.getIActionsFunction(),
				NQueensFunctionFactory.getResultFunction(),
				new NQueensGoalTest());
		Search search = new DepthLimitedSearch(8);
		SearchAgent agent = new SearchAgent(problem, search);
		List<Action> actions = agent.getActions();
		assertCorrectPlacement(actions);
                printActions(actions);
		Assert.assertEquals("113",
				agent.getInstrumentation().getProperty("nodesExpanded"));
	}

 

       
        
	@Test
	public void testJugsLDS10() throws Exception {
            java.lang.StringBuilder sb = new java.lang.StringBuilder("sb");
            sb.append("bs");
            
            JugsState start = new JugsState();
            start.setState(0,0);
            JugsGoalTest gt = new JugsGoalTest();
            gt.setGoal(1, 3);
            
	    Problem problem = new Problem(start, JugsFunctionFactory.getActionsFunction(),
                JugsFunctionFactory.getResultFunction(), gt);
            
		DepthLimitedSearch search = new DepthLimitedSearch(4);
		SearchAgent agent = new SearchAgent(problem, search);
                
		List<Action> actions = agent.getActions();
                printGeneralActions(actions);
                System.out.println(search.getMetrics().toString());
		//Assert.assertEquals(true, search.isCutOff(actions));
	}

	@Test
	public void testFailure() throws Exception {
		Problem problem = new Problem(new NQueensBoard(3),
				NQueensFunctionFactory.getIActionsFunction(),
				NQueensFunctionFactory.getResultFunction(),
				new NQueensGoalTest());
		DepthLimitedSearch search = new DepthLimitedSearch(6);
		SearchAgent agent = new SearchAgent(problem, search);
		List<Action> actions = agent.getActions();
		Assert.assertEquals(true, search.isFailure(actions));
	}

	//
	// PRIVATE METHODS
	//
	private void assertCorrectPlacement(List<Action> actions) {
		Assert.assertEquals(8, actions.size());
		Assert.assertEquals(
				"Action[name==placeQueenAt, location== ( 0 , 0 ) ]", actions
						.get(0).toString());
		Assert.assertEquals(
				"Action[name==placeQueenAt, location== ( 1 , 4 ) ]", actions
						.get(1).toString());
		Assert.assertEquals(
				"Action[name==placeQueenAt, location== ( 2 , 7 ) ]", actions
						.get(2).toString());
		Assert.assertEquals(
				"Action[name==placeQueenAt, location== ( 3 , 5 ) ]", actions
						.get(3).toString());
		Assert.assertEquals(
				"Action[name==placeQueenAt, location== ( 4 , 2 ) ]", actions
						.get(4).toString());
		Assert.assertEquals(
				"Action[name==placeQueenAt, location== ( 5 , 6 ) ]", actions
						.get(5).toString());
		Assert.assertEquals(
				"Action[name==placeQueenAt, location== ( 6 , 1 ) ]", actions
						.get(6).toString());
		Assert.assertEquals(
				"Action[name==placeQueenAt, location== ( 7 , 3 ) ]", actions
						.get(7).toString());
	}
        
        
        private void printActions(List<Action> actions) {
            java.util.Iterator<Action> ai = actions.iterator();
            while (ai.hasNext()) {
                Action a = ai.next();
                aima.core.environment.nqueens.QueenAction q;
                q = (aima.core.environment.nqueens.QueenAction) a;
                System.out.println(q.getName());
                System.out.println(q.getLocation().toString());

            }
            
        }
        
        private void printGeneralActions(List<Action> actions) {
            java.util.Iterator<Action> ai = actions.iterator();
            while (ai.hasNext()) {
                Action a = ai.next();
//                aima.core.environment.jugs.JugsState q;
//                q = (aima.core.environment.jugs.JugsState) a;
//                System.out.println(q.getState());
                System.out.println(((DynamicAction) a).getName());
            }
            
        }
        
        
}