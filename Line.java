
package AdvancedProject;

import java.util.Vector;

public class Line {
    private Vector<String> l=new Vector<String>();
   public Line(String s){
       try{
       int i=s.indexOf("<Line");
       for(;i<s.length();){
        l.add(s.substring(i,s.indexOf("</Line>",i)+"</Line>".length()));
        i=s.indexOf("<Line",i+1);
       }
       }
       catch(Exception e){
       }
   }
   public Vector getLine(){
       return l;
   }
}
