
import aima.core.agent.Action;
import aima.core.environment.blocks.BlocksFunctionFactory;
import aima.core.environment.blocks.BlocksGoalState;
import aima.core.environment.blocks.BlocksState;

import aima.core.search.framework.Problem;
import aima.core.search.framework.Search;
import aima.core.search.framework.SearchAgent;
import aima.core.search.uninformed.DepthLimitedSearch;
import java.util.List;


public class Principal {
    
        public static void main(String[] args) {
            Principal testeBlocos = new Principal();
            try {
                testeBlocos.BlocksDepthLimitedSearch();
            } catch (Exception e) {
                System.out.println(e);
            }
            
        }
    
    
        public void BlocksDepthLimitedSearch() throws Exception {
		Problem problem = new Problem(new BlocksState(),
				BlocksFunctionFactory.getActionsFunction(),
				BlocksFunctionFactory.getResultFunction(),
				new BlocksGoalState());
		Search search = new DepthLimitedSearch(15);
		SearchAgent agent = new SearchAgent(problem, search); // esse printa os resultados
		List<Action> actions = agent.getActions();
	}
    
  
	
        
}
