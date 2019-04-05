/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aima.test.uepg.mri;

/**
 *
 * @author jcarlos
 */
import java.util.Vector;
import org.junit.Test;
import aima.core.logic.propositional.kb.data.Model;
import aima.core.logic.propositional.parsing.PLParser;
import aima.core.logic.propositional.parsing.ast.Sentence;
import aima.core.logic.propositional.parsing.ast.PropositionSymbol;

public class JointDistributionTest {

    Vector variables = new Vector();
    double jointTable[] = null;
    private Model m;
    private PLParser parser = new PLParser();

    @Test
    public void testJoint() {
        initVars(5);
        initRandomJoint(this.variables.size());
        double p = calcSentenceProbability("(x1 | x2) & x3");
        System.out.println("Probabilidade eh: " + p);
    }

    public void initVars(int n) {
        for (int i=0;i<n;i++)
            this.variables.add("X"+Integer.toString(i));
    }
    
    public void initRandomJoint(int n) {
        int nlines = 2^n;
        this.jointTable = new double[nlines];        
        double slines = 0; //sum
        for (int i=0;i<nlines;i++) {
           this.jointTable[i] = java.lang.Math.random();
           slines = slines + this.jointTable[i];
        }
        for (int i=0;i<nlines;i++) {
           this.jointTable[i] = this.jointTable[i]/slines;
        }
    }
    
    public double calcSentenceProbability(String strSentence) {
        Sentence s = (Sentence) parser.parse(strSentence);
        // para cada linha da joint
        double probability = 0;
        for (int i=0;i<this.jointTable.length;i++) {
            boolean[] instantiation = convertIntoBooleanArray(i);
            m = new Model();
            for (int j=0;j<this.variables.size();j++) {
                m = m.union(new PropositionSymbol((String) this.variables.get(j)), instantiation[j]);
            }
            if (m.isTrue(s)) 
                probability = probability + this.jointTable[i];
        }
        return probability;
    }
    
    public boolean[] convertIntoBooleanArray(int i) {
        String bString = Integer.toBinaryString(i);
        int lString = bString.length();
        boolean answer[] = new boolean[this.variables.size()];
        int startpoint = answer.length - bString.length();
        for (int j=0;j<answer.length;j++) { // processa de tras para frente
           if (j<startpoint) 
              answer[j] = false;
           else {
              if (bString.charAt(j-startpoint)=='1')
                  answer[j] = true;
              else answer[j] = false;
           }
        }
        return answer;
    }
    
}
