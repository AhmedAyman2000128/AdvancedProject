package AdvancedProject;

import java.util.Vector;

public class Block{
   private Vector<String> p=new Vector<String>();
   public Block(String s){
       try{
       int i=s.indexOf("<Block");
       for(;i<s.length();){
        p.add(s.substring(i,s.indexOf("</Block>",i)+"</Block>".length()));
        i=s.indexOf("<Block",i+1);
       }
       }
       catch(Exception e){
       }
   }
   public int getNoOfBlocks(){
       return p.size();
   }
   public String getBlockByName(String blockName){
       for(int i=0;i<p.size();i++){
           if(p.get(i).indexOf(blockName)!=-1)
               return p.get(i);
       }
       return "Such block doesnt exist";
   }
   public String getBlockName(int i){
        String s;
           int start=p.get(i).indexOf("Name=")+"Name=".length();
           s=p.get(i).substring(start+1,p.get(i).indexOf("\"",start+1));
       return s;
   }
   public String[] getPosition(String blockName){
       String block=getBlockByName(blockName);
       String[] s= block.substring(block.indexOf("Position")+"Position>".length(),block.indexOf("<",block.indexOf("Position"))).split(",",0);
       s[0]=s[0].substring(2);
       s[s.length-1]=s[s.length-1].substring(0,s[s.length-1].length()-1);
       return s;
       //must apply trim for the elements of the String
   }
   /*public int getNoOfInputs(String blockName){
       String block=getBlockByName(blockName);
   } */
}
