/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import java.util.ArrayList;

/**
 *
 * @author meni
 */
public class PairSet {

    static class Pair {
        private int a;
        private int b;

        public Pair(int a, int b) {
            this.a = Math.min(a, b);
            this.b = Math.max(a, b);
        }
        
        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Pair) {
                Pair p = (Pair) obj;
                if (this.a == p.a && this.b == p.b) {
                    return true;
                }
            }
            return false;
        }
    }
    
    private final ArrayList<Pair> arrayList = new ArrayList<>();
    
    public boolean checkAndAdd(Pair newPair){
        boolean find = false;
        for (Pair p: arrayList){
            if (p.equals(newPair)){
                return false;
            }
        }
        arrayList.add(newPair);
        return true;
    }
}
