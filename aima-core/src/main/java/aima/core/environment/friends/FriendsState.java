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
import aima.core.agent.Action;
import aima.core.agent.impl.DynamicAction;
import java.util.Vector;
import java.util.List;
import aima.core.environment.map.SimplifiedRoadMapOfPartOfRomania;

public class FriendsState {
    
    //versao mais simples
    public Action ORADEAtoZERIND= new DynamicAction("Go from Oradea to Zerind");
    public Action ZERINDtoORADEA = new DynamicAction("Go from Oradea to Zerind"); 
    
    
    
    //usando mapa AIMA
    SimplifiedRoadMapOfPartOfRomania r = new SimplifiedRoadMapOfPartOfRomania();
    Vector actions = new java.util.Vector();
    
    String cityA, cityB;
    boolean turn; //true - move A; false - move B

    public FriendsState() {
        initMap();
    }
        
    public void setCityA(String loc) {
        cityA = loc;
    }
    
    public void setCityB(String loc) {
        cityB = loc;
    }
    
    public String getCityA() {
        return cityA;
    }
    
    public String getCityB() {
        return cityB;
    }
    
    public boolean moveA() {
        return this.turn;
    }

    private void initMap() {

        List<String> cities = r.getLocations();
        java.util.Iterator<String> it = cities.iterator();
        while (it.hasNext()) {
            String city = (String) it.next();
            List<String> neighbours = r.getLocationsLinkedTo(city);
            java.util.Iterator<String> nit = neighbours.iterator();
            while (nit.hasNext()) {
                String ncity = (String) nit.next();
                String road = city + " to " + ncity;
                Action action = new DynamicAction(road);
                this.actions.add(action);
            }
        }
        

    }
    
}
