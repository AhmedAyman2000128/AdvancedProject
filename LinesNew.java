
package AdvancedProject;

import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import java.nio.file.Paths;
import java.util.Vector;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polyline;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class LinesNew {
    private NodeList lineTag;
    private Line []l;
    private Path []p;
    Block B;
    public LinesNew(String s) throws ParserConfigurationException, SAXException, IOException{
        B=new Block(s);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	Document doc = factory.newDocumentBuilder().parse(new InputSource(new StringReader(s)));
        lineTag = doc.getElementsByTagName("Line");
        //
        System.out.println(((Element)lineTag.item(0)).getElementsByTagName("Branch").getLength());//item(0).getTextContent());
        String p=((Element)((Element)lineTag.item(0)).getElementsByTagName("P").item(1)).getTextContent();
        int k=((Element)lineTag.item(1)).getElementsByTagName("P").getLength();
        int x=((Element)lineTag.item(1)).getElementsByTagName("P").getLength();
        String r=((Element)((Element)lineTag.item(0)).getElementsByTagName("P").item(1)).getAttribute("Name");
        System.out.println(r);
        //
    }
    
    public int getNoOfLines(){
        return lineTag.getLength();
    }
    public void printLines(){
        for(int i=0;i<getNoOfLines();i++)
        {
            System.out.println(lineTag.item(i).getTextContent());
        }
    }
    public void setLines(){
        Vector<Line> v = new Vector<Line>();
        Vector<Path> p1 =new Vector<Path>();
        Path branch=new Path();
        Point2D start=new Point2D(0,0);
        Point2D end;
        for(int i=0;i<getNoOfLines();i++){
                int srcpointx=0;
                int srcpointy=0;
                
                for(int j=0;j<((Element)lineTag.item(i)).getElementsByTagName("P").getLength();j++){
                        
                        if(((Element)((Element)lineTag.item(i)).getElementsByTagName("P").item(j)).getAttribute("Name").equals("Src")){
                            branch=new Path();
                            String s=((Element)((Element)lineTag.item(i)).getElementsByTagName("P").item(j)).getTextContent();
                                for(int w=0;w<B.getNoOfBlocks();w++)
                                {
                                    if(B.getSID(w)==Integer.parseInt(""+s.charAt(0))){
                                        if(B.getYCoordinate(w)<=(B.getBlockHeight(w)+B.getMinY()))
                                        {
                                        srcpointx = (int) (B.getXCoordinate(w) + B.getBlockWidth(w)*1.2);
                                        srcpointy = (int)(B.getYCoordinate(w)+B.getBlockHeight(w)*1.2/2);
                                        }
                                        else{
                                            srcpointx = (int) (B.getXCoordinate(w));
                                            srcpointy = (int)(B.getYCoordinate(w)+B.getBlockHeight(w)*1.2/2);
                                        }
                                    }
                                }
                        }
                        else if(((Element)((Element)lineTag.item(i)).getElementsByTagName("P").item(j)).getAttribute("Name").equals("Dst")){
                            String s=((Element)((Element)lineTag.item(i)).getElementsByTagName("P").item(j)).getTextContent();
                                for(int w=0;w<B.getNoOfBlocks();w++)
                                {
                                    if(w==4)continue;
                                    if(B.getSID(w)==Integer.parseInt(""+s.charAt(0))){
                                        
                                        branch.getElements().add(new MoveTo(srcpointx,srcpointy));
                                        if(srcpointx<=B.getXCoordinate(w)){
                                        
                                          branch.getElements().add(new LineTo(B.getXCoordinate(w)-20,srcpointy));  
                                          branch.getElements().add(new LineTo(B.getXCoordinate(w)-20,B.getYCoordinate(w)+B.getBlockWidth(w)*1.2/2)); 
                                          branch.getElements().add(new LineTo(B.getXCoordinate(w),B.getYCoordinate(w)+B.getBlockWidth(w)*1.2/2)); 
                                          p1.add(branch);
                                        }
                                        else
                                        {
                                          branch.getElements().add(new LineTo(B.getXCoordinate(w)+B.getBlockWidth(w)+20,srcpointy));  
                                          branch.getElements().add(new LineTo(B.getXCoordinate(w)+B.getBlockWidth(w)+20,B.getYCoordinate(w)+B.getBlockWidth(w)*1.2/2)); 
                                          branch.getElements().add(new LineTo(B.getXCoordinate(w)+B.getBlockWidth(w)*1.2,B.getYCoordinate(w)+B.getBlockWidth(w)*1.2/2));
                                          p1.add(branch);
                                        }
                                    }
                                }
                                 
                        }
                }
            
           
        
    }
        l=v.toArray(new Line[v.size()]);
        branch.getElements().add(new MoveTo(B.getXCoordinate(4),(B.getYCoordinate(4)+B.getBlockHeight(4)*1.2/2)));
        branch.getElements().add(new LineTo(B.getXCoordinate(4)-20,B.getYCoordinate(4)+B.getBlockHeight(4)*1.2/2));
        branch.getElements().add(new LineTo(B.getXCoordinate(4)-20,B.getYCoordinate(0)+B.getBlockHeight(0)*1.2/2+5));
        branch.getElements().add(new LineTo(B.getXCoordinate(0),B.getYCoordinate(0)+B.getBlockHeight(0)*1.2/2+5));
        p1.add(branch);
        p=p1.toArray(new Path[p1.size()]);
    }
    public Line[] getLines(){
        return l;
    }
    public Path[] getPaths(){
        return p;
    }
    public static void main(String []args) throws IOException, ParserConfigurationException, SAXException{
        String filename = "F:/Electrical junior2/Advanced programming/Project/Example.mdl";
        // Read the .mdl file as a string
        String content = new String(Files.readAllBytes(Paths.get(filename)), Charset.defaultCharset());
           String s="";
           int start=content.indexOf("<System>");
           int end =content.indexOf("</System>",start);
        s = content.substring(start, end+"</System>".length());
        LinesNew l =new LinesNew(s);
    }
}
