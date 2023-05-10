package AdvancedProject;

import java.util.Vector;

public class Block{
   private Vector<String> p=new Vector<String>();
   private int noOfInputs=0;
   private int noOfOutputs=0;
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
   
   public String getBlockByIndex(int i){
       return p.get(i);
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
   
   public int[] getPositionByName(String blockName){
       String block=getBlockByName(blockName);
       String[] s= block.substring(block.indexOf("Position")+"Position>".length(),block.indexOf("<",block.indexOf("Position"))).split(",",0);
       s[0]=s[0].substring(2);
       s[s.length-1]=s[s.length-1].substring(0,s[s.length-1].length()-1);
       int []o=new int[s.length];
       for(int j=0;j<s.length;j++){
           o[j]=Integer.parseInt(s[j].trim());
       }
       return o;
       //must apply trim for the elements of the String
   }
   
   public int[] getPositionByIndex(int i){
       String block=p.get(i);
       String[] s= block.substring(block.indexOf("Position")+"Position>".length(),block.indexOf("<",block.indexOf("Position"))).split(",",0);
       s[0]=s[0].substring(2);
       s[s.length-1]=s[s.length-1].substring(0,s[s.length-1].length()-1);
       int []o=new int[s.length];
       for(int j=0;j<s.length;j++){
           o[j]=Integer.parseInt(s[j].trim());
       }
       return o;
   }
   public int getXCoordinate(int i){
       return getPositionByIndex(i)[0]-getMinX()+50;
   }
   public int getYCoordinate(int i){
       return getPositionByIndex(i)[1]-getMinY()+50;
   }
   public int getBlockWidth(int i){
       int width;
       width=getPositionByIndex(i)[2]-getPositionByIndex(i)[0];
       return width;
   }
   
   public int getBlockHeight(int i){
       int height;
       height=getPositionByIndex(i)[3]-getPositionByIndex(i)[1];
       return height;
   }
   
   public int getSID(int i){
       int start=p.get(i).indexOf("SID=\"")+"SID=\"".length();
       int SID=Integer.parseInt(p.get(i).substring(start,start+1));
       return SID;
   }
   //get min x for the blocks
   public int getMinX(){
       int min=Integer.MAX_VALUE;
       for(int i=0;i<p.size();i++){
           if(min>getPositionByIndex(i)[0])
           {
               min=getPositionByIndex(i)[0];
           }
       }
       return min;
   }
   //get max x for the blocks
   public int getMaxX(){
       int max=0;
       for(int i=0;i<p.size();i++){
           if(max<getPositionByIndex(i)[2])
           {
               max=getPositionByIndex(i)[2];
           }
       }
       return max;
   }
   public int getTotalBlocksWidth(){
       return getMaxX()-getMinX()+100;
   }
   
   public int getMaxY(){
       int max=0;
       for(int i=0;i<p.size();i++){
           if(max<getPositionByIndex(i)[3])
           {
               max=getPositionByIndex(i)[3];
           }
       }
       return max;
   }
   
   public int getMinY(){
       int min=Integer.MAX_VALUE;
       for(int i=0;i<p.size();i++){
           if(min>getPositionByIndex(i)[1])
           {
               min=getPositionByIndex(i)[1];
           }
       }
       return min;
   }
   
   public int getTotalBlockHeight(){
       return getMaxY()-getMinY()+100;
   }
   //gdeed
   public void setNoOfInputs(int inputs){
       noOfInputs=inputs;
   }
   //gdeed
   public void setNoOfOutputs(int outputs){
       noOfOutputs=outputs;
   }
}
