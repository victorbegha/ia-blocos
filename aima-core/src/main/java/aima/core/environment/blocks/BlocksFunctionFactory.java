/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aima.core.environment.blocks;

import aima.core.agent.Action;
import aima.core.environment.blocks.BlocksState;
import aima.core.search.framework.ActionsFunction;
import aima.core.search.framework.ResultFunction;
import java.util.LinkedHashSet;
import java.util.Set;


public class BlocksFunctionFactory {
	private static ActionsFunction _actionsFunction = null;
    private static ResultFunction _resultFunction = null;
    public static ActionsFunction getActionsFunction() {
        if (null == _actionsFunction) {
            _actionsFunction = new BlocksActionsFunction();
        }
        return _actionsFunction;
    }
    public static ResultFunction getResultFunction() {
        if (null == _resultFunction) {
            _resultFunction = new BlocksResultFunction();
        }
        return _resultFunction;
    }
    private static class BlocksActionsFunction implements ActionsFunction {
        public Set<Action> actions(Object state) {
            BlocksState board = (BlocksState) state;
            Set<Action> actions = new LinkedHashSet<Action>();
                        
            
            if (getCurrentColumn('a',board)==0 && isAvailable('a',board)/* && columnHasSpace(1,board)*/) {
                actions.add(board.a0to1);
            }
            if (getCurrentColumn('a',board)==0 && isAvailable('a',board)) {
                actions.add(board.a0to2);
            }
            if (getCurrentColumn('a',board)==1 && isAvailable('a',board)) {
                actions.add(board.a1to0);
            }
            if (getCurrentColumn('a',board)==1 && isAvailable('a',board)) {
                actions.add(board.a1to2);
            }
            if (getCurrentColumn('a',board)==2 && isAvailable('a',board)) {
                actions.add(board.a2to0);
            }
            if (getCurrentColumn('a',board)==2 && isAvailable('a',board)) {
                actions.add(board.a2to1);
            }
            
            // B 
            if (getCurrentColumn('b',board)==0 && isAvailable('b',board)) {
                actions.add(board.b0to1);
            }
            if (getCurrentColumn('b',board)==0 && isAvailable('b',board)) {
                actions.add(board.b0to2);
            }
            if (getCurrentColumn('b',board)==1 && isAvailable('b',board)) {
                actions.add(board.b1to0);
            }
            if (getCurrentColumn('b',board)==1 && isAvailable('b',board)) {
                actions.add(board.b1to2);
            }
            if (getCurrentColumn('b',board)==2 && isAvailable('b',board)) {
                actions.add(board.b2to0);
            }
            if (getCurrentColumn('b',board)==2 && isAvailable('b',board)) {
                actions.add(board.b2to1);
            }
            
            // C
            if (getCurrentColumn('c',board)==0 && isAvailable('c',board)) {
                actions.add(board.c0to1);
            }
            if (getCurrentColumn('c',board)==0 && isAvailable('c',board)) {
                actions.add(board.c0to2);
            }
            if (getCurrentColumn('c',board)==1 && isAvailable('c',board)) {
                actions.add(board.c1to0);
            }
            if (getCurrentColumn('c',board)==1 && isAvailable('c',board)) {
                actions.add(board.c1to2);
            }
            if (getCurrentColumn('c',board)==2 && isAvailable('c',board)) {
                actions.add(board.c2to0);
            }
            if (getCurrentColumn('c',board)==2 && isAvailable('c',board)) {
                actions.add(board.c2to1);
            }
            
            //System.out.println("---------------------");
           //board.printMatriz();
            //System.out.println(a.toString());
            //System.out.println(newBoard.getStateAsString());
            
            return actions;
        }

        
        private int getCurrentColumn(char bloco, BlocksState board) {
            for (int i=0; i<3; i++) {
                for (int j=0; j<3; j++) {
                    if (board.matriz[i][j] == bloco)
                        return j;
                }
            }    
            //impossível:
            return -1;
        }
        
        
       

        private boolean isAvailable(char bloco, BlocksState board) {
            
            // Se estiver no topo (posição 0 de alguma coluna) significa que o bloco está disponível:
            for (int j=0; j<3; j++) {
                if (board.matriz[0][j] == bloco) {
                    return true;
                }
            }
            
            // Caso contrário vê se está no meio e o de cima vazio:
            for (int j=0; j<3; j++) {
                if (board.matriz[1][j] == bloco && board.matriz[0][j] == '0') {
                    return true;
                }
            }
            
            // Caso contrário vê se está embaixo e o resto vazio:
            for (int j=0; j<3; j++) {
                if (board.matriz[2][j] == bloco && board.matriz[1][j] == '0' && board.matriz[0][j] == '0') {
                    return true;
                }
            }
            
            return false;
        }
        
        /*
        private boolean columnHasSpace(int column, BlocksState board) {
            if (board.matriz[0][column] == '0')
                return true;
            else
                return false;
        }*/
        
        
        
    }
    
    private static class BlocksResultFunction implements ResultFunction {
        public Object result(Object s, Action act) {
            BlocksState board = (BlocksState) s;
            BlocksState newBoard = new BlocksState();
            printMatrizAtual(board);
            if (board.a0to1.equals(act)) {
                newBoard = move('a',0,1, board);
            } else if (board.a0to2.equals(act)) {
                newBoard = move('a',0,2, board);
            } else if (board.a1to0.equals(act)) {
                newBoard = move('a',1,0, board);
            } else if (board.a1to2.equals(act)) {
                newBoard = move('a',1,2, board);
            } else if (board.a2to0.equals(act)) {
                newBoard = move('a',2,0, board);
            } else if (board.a2to1.equals(act)) {
                newBoard = move('a',2,1, board);
            }
            // B
            else if (board.b0to1.equals(act)) {
                newBoard = move('b',0,1, board);
            } else if (board.b0to2.equals(act)) {
                newBoard = move('b',0,2, board);
            } else if (board.b1to0.equals(act)) {
                newBoard = move('b',1,0, board);
            } else if (board.b1to2.equals(act)) {
                newBoard = move('b',1,2, board);
            } else if (board.b2to0.equals(act)) {
                newBoard = move('b',2,0, board);
            } else if (board.b2to1.equals(act)) {
                newBoard = move('b',2,1, board);
            }
            // C
            else if (board.c0to1.equals(act)) {
                newBoard = move('c',0,1, board);
            } else if (board.c0to2.equals(act)) {
                newBoard = move('c',0,2, board);
            } else if (board.c1to0.equals(act)) {
                newBoard = move('c',1,0, board);
            } else if (board.c1to2.equals(act)) {
                newBoard = move('c',1,2, board);
            } else if (board.c2to0.equals(act)) {
                newBoard = move('c',2,0, board);
            } else if (board.c2to1.equals(act)) {
                newBoard = move('c',2,1, board);
            }
            
            
            return newBoard;
        }
        
        private void printMatrizAtual(BlocksState board) {
            for (int i=0; i<3; i++) {
                for (int j=0; j<3; j++) {
                    System.out.print(board.matriz[i][j]);
                }
                System.out.println();
            }
            System.out.println("________________");
        }
        
        
        private BlocksState move(char bloco, int colIni, int colFin, BlocksState board) {
            // primeiro localiza o bloco na coluna colIni
            BlocksState aux = new BlocksState();
            aux.matriz = board.matriz;
            
            if (getCurrentColumn(bloco,aux) != colIni) {
                return aux;
            }
            
            
            int linIni = -1;
            for (int i=0; i<3; i++) {
                if (aux.matriz[i][colIni] == bloco) {
                    //System.out.println("Achei o linini "+i+" bloco "+bloco);
                    linIni = i;
                    break;
                }
            }
            
            // depois acha a posição livre mais baixa na coluna:
            int linFin = -1;
            for (int i=2; i>=0; i--) {
                if (aux.matriz[i][colFin] == '0') {
                    linFin = i;
                    break;
                }
            }
            
            // Aí faz a troca:
            aux.matriz[linIni][colIni] = '0';
            aux.matriz[linFin][colFin] = bloco;
            return aux;
        }
        
        
        
        
        
        
        
        
        
        
        
        
        private int getCurrentColumn(char bloco, BlocksState board) {
            for (int i=0; i<3; i++) {
                for (int j=0; j<3; j++) {
                    if (board.matriz[i][j] == bloco)
                        return j;
                }
            }    
            //impossível:
            return -1;
        }
        
        
        
        
        
    }
}