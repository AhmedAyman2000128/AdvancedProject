
package AdvancedProject;

import java.util.Vector;

public class Line1 {
    private Vector<String> l=new Vector<String>();
    private Block B;//gdeed
   public Line1(String s){
       try{
           B=new Block(s);
       int i=s.indexOf("<Line");
       for(;i<s.length();){
        l.add(s.substring(i,s.indexOf("</Line>",i)+"</Line>".length()));
        i=s.indexOf("<Line",i+1);
       }
       //gdeed
       /*
       String r;//3#in:1
       int cntLinesIn=0;
       int cntLinesOut=0;
       int j;
       int k;
       for(i=0;i<l.size();i++){
           j=l.get(i).indexOf("Src");
           for(;j<l.get(i).length();)
           {
               r=l.get(i).substring(j+"Src".length()+3,j+"Src".length()+10);
               j=l.get(i).indexOf("Src",j+1);
               System.out.println(r);
           }
       }*/
        
       }
       catch(Exception e){
       }
   }
   public Vector getLine(){
       return l;
   }
}
