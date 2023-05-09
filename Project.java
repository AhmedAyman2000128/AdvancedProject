package AdvancedProject;

import javafx.application.Application;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import javafx.stage.Stage;

public class Project extends Application{
    @Override
     public void start(Stage primaryStage){
         
         
         //primaryStage.setScene(scene);
         primaryStage.setTitle("Simulation");
         primaryStage.show();
     }
    public static void main(String[] args) throws IOException {
        String filename = "F:/Electrical junior2/Advanced programming/Project/Example.mdl";

        // Read the .mdl file as a string
        String content = new String(Files.readAllBytes(Paths.get(filename)), Charset.defaultCharset());
           String s="";
           int start=content.indexOf("<System>");
           int end =content.indexOf("</System>",start);
        s = content.substring(start, end+"</System>".length());
        // Print the content of the .mdl file
        ///System.out.println(s);
        //showing the window
        Block B=new Block(s);
        Line L=new Line(s);
        System.out.println(B.getNoOfBlocks());
        
        //Application.launch(args);
        
    }
}
