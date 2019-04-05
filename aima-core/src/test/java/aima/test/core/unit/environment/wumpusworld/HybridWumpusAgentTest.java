package aima.test.core.unit.environment.wumpusworld;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import aima.core.agent.Action;
import aima.core.environment.wumpusworld.AgentPercept;
import aima.core.environment.wumpusworld.AgentPosition;
import aima.core.environment.wumpusworld.HybridWumpusAgent;
import aima.core.environment.wumpusworld.Room;
import aima.core.environment.wumpusworld.action.Climb;
import aima.core.environment.wumpusworld.action.Forward;
import aima.core.environment.wumpusworld.action.Grab;
import aima.core.environment.wumpusworld.action.Shoot;
import aima.core.environment.wumpusworld.action.TurnLeft;

/**
 * 
 * @author Ciaran O'Reilly
 *
 */
public class HybridWumpusAgentTest {

    @SuppressWarnings("serial")
    @Test
    public void testGeral() {
		HybridWumpusAgent hwa = new HybridWumpusAgent();
                AgentPercept percepts[][] = new AgentPercept[4][4];
                                                 // mau cheiro, brisa, brilho, obstaculo, grito  
                percepts[0][0] = new AgentPercept(false, false, false, false, false); // 1,1
                percepts[1][0] = new AgentPercept(false, true, false, false, false); // 2,1
                System.out.println(hwa.hwaKBtoString());
                System.out.println("=============");
                System.out.println("=============");
                boolean makeAPlan = true;
                int x = 0; // = (x=1,y=1)
                int y = 0;
                while (makeAPlan) {
                   AgentPercept currentPercept = percepts[x][y];
                   Action a = hwa.execute(currentPercept);
                   String aOut = a.toString();
                   System.out.println((x+1) + ";"+ (y+1));
                   System.out.println("Acao :" + aOut);
                   int ixToPosition = aOut.indexOf("toPosition");
                   if (ixToPosition>=0) {
                       int ixLBracket = aOut.indexOf("[", ixToPosition);
                       int ixComma = aOut.indexOf(",", ixLBracket);                       
                       int ixRBracket = aOut.indexOf("]", ixComma);
                       int diff = ixComma-ixLBracket-1;
                       String s1 = aOut.substring(ixLBracket+1);
                       s1 = s1.substring(0, diff);
                       diff = ixRBracket-ixComma-1;
                       String s2 = aOut.substring(ixComma+1);
                       s2 = s2.substring(0, diff);
                       x = Integer.parseInt(s1) -1;  //ajustar indice
                       y = Integer.parseInt(s2) -1;
                   }
                       
//                   System.out.println(hwa.hwaKBtoString());
                   System.out.println("=============");
                }

    }
    
    
    
    
	@SuppressWarnings("serial")
	@Test
	public void testPlanRoute() {
		HybridWumpusAgent hwa = new HybridWumpusAgent(4);
		// Should be a NoOp plan as we are already at the goal.
		Assert.assertEquals(Collections.<Action>emptyList(), 
			hwa.planRoute(new AgentPosition(1, 1, AgentPosition.Orientation.FACING_EAST), 
				new LinkedHashSet<Room>() {{
					add(new Room(1,1));
				}},
				allRooms(4)
		));
		Assert.assertEquals(Arrays.asList(
				new TurnLeft(AgentPosition.Orientation.FACING_EAST),
				new TurnLeft(AgentPosition.Orientation.FACING_NORTH),
				new Forward(new AgentPosition(2, 1, AgentPosition.Orientation.FACING_WEST))
			), 
			hwa.planRoute(new AgentPosition(2, 1, AgentPosition.Orientation.FACING_EAST), 
				new LinkedHashSet<Room>() {{
					add(new Room(1,1));
				}},
				allRooms(4)
		));
		Assert.assertEquals(Arrays.asList(
				new TurnLeft(AgentPosition.Orientation.FACING_EAST),
				new Forward(new AgentPosition(3, 1, AgentPosition.Orientation.FACING_NORTH)),
				new Forward(new AgentPosition(3, 2, AgentPosition.Orientation.FACING_NORTH)),
				new TurnLeft(AgentPosition.Orientation.FACING_NORTH),
				new Forward(new AgentPosition(3, 3, AgentPosition.Orientation.FACING_WEST)),
				new Forward(new AgentPosition(2, 3, AgentPosition.Orientation.FACING_WEST)),
				new TurnLeft(AgentPosition.Orientation.FACING_WEST),
				new Forward(new AgentPosition(1, 3, AgentPosition.Orientation.FACING_SOUTH)),
				new Forward(new AgentPosition(1, 2, AgentPosition.Orientation.FACING_SOUTH))
			), 
			hwa.planRoute(new AgentPosition(3, 1, AgentPosition.Orientation.FACING_EAST), 
				new LinkedHashSet<Room>() {{
					add(new Room(1,1));
				}},
				new LinkedHashSet<Room>() {{
					addAll(allRooms(4));
					remove(new Room(2, 1));
					remove(new Room(2, 2));
				}}
	    ));
	}
	
	@SuppressWarnings("serial")
	@Test
	public void testPlanShot() {
		HybridWumpusAgent hwa = new HybridWumpusAgent(4);
		// Should be just shoot as are facing the Wumpus
		Assert.assertEquals(Arrays.asList(new Shoot()), 
			hwa.planShot(new AgentPosition(1, 1, AgentPosition.Orientation.FACING_EAST), 
				new LinkedHashSet<Room>() {{
					add(new Room(3,1));
				}},
				allRooms(4)
		));	
		Assert.assertEquals(Arrays.asList(
				new TurnLeft(AgentPosition.Orientation.FACING_EAST),
				new Shoot()
			), 
			hwa.planShot(new AgentPosition(1, 1, AgentPosition.Orientation.FACING_EAST), 
				new LinkedHashSet<Room>() {{
					add(new Room(1,2));
				}},
				allRooms(4)
		));	
		Assert.assertEquals(Arrays.asList(
				new Forward(new AgentPosition(1, 1, AgentPosition.Orientation.FACING_EAST)),
				new TurnLeft(AgentPosition.Orientation.FACING_EAST),
				new Shoot()
			), 
			hwa.planShot(new AgentPosition(1, 1, AgentPosition.Orientation.FACING_EAST), 
				new LinkedHashSet<Room>() {{
					add(new Room(2,2));
				}},
				allRooms(4)
		));	
	}
	
	@Test
	public void testGrabAndClimb() {
		HybridWumpusAgent hwa = new HybridWumpusAgent(2);
		// The gold is in the first square
		Action a = hwa.execute(new AgentPercept(true, true, true, false, false));
		Assert.assertTrue(a instanceof Grab);
		a = hwa.execute(new AgentPercept(true, true, true, false, false));
		Assert.assertTrue(a instanceof Climb);
	}
	
	private static Set<Room> allRooms(int caveXandYDimensions) {
		Set<Room> allRooms = new LinkedHashSet<Room>();		
		for (int x = 1; x <= caveXandYDimensions; x++) {
			for (int y = 1; y <= caveXandYDimensions; y++) {
				allRooms.add(new Room(x, y));
			}
		}
		
		return allRooms;
	}
}
