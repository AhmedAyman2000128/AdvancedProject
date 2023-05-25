package AdvancedProject;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.application.Application;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import static javafx.scene.effect.BlurType.GAUSSIAN;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

public class Project extends Application {
    @Override
     public void start(Stage primaryStage)throws IOException, ParserConfigurationException, SAXException{
         String filename = "F:/Electrical junior2/Advanced programming/Project/Example.mdl";

        // Read the .mdl file as a string
        String content = new String(Files.readAllBytes(Paths.get(filename)), Charset.defaultCharset());
           String s="";
           int start=content.indexOf("<System>");
           int end =content.indexOf("</System>",start);
        s = content.substring(start, end+"</System>".length());
        Block B=new Block(s);
        Rectangle []r=new Rectangle[B.getNoOfBlocks()];
        Text []t =new Text[B.getNoOfBlocks()];
        for(int i=0;i<B.getNoOfBlocks();i++){
        r[i]=new Rectangle();
        t[i]=new Text();
        r[i].setX(B.getXCoordinate(i));
        r[i].setY(B.getYCoordinate(i));
        r[i].setWidth(B.getBlockWidth(i)*1.2);
        r[i].setHeight(B.getBlockHeight(i)*1.2);
        r[i].setStroke(Color.GREY);
        r[i].setFill(Color.TRANSPARENT);
        r[i].setEffect(new DropShadow(GAUSSIAN,Color.SKYBLUE,1,2,0,0));
        t[i].setText(B.getBlockName(i));
        t[i].setY(B.getYCoordinate(i)+B.getBlockHeight(i)+20);
        t[i].setX(B.getXCoordinate(i));
        t[i].setFont(new Font(10));
        }
        Lines l =new Lines(s);
        l.setLines();
        Path []p=l.getPaths();
        Pane pane=new Pane();
        pane.getChildren().addAll(t);
        pane.getChildren().addAll(r);
        pane.getChildren().add(p[0]);
        pane.getChildren().add(p[1]);
        pane.getChildren().add(p[3]);
        pane.getChildren().add(p[5]);
        Scene scene =new Scene(pane,B.getTotalBlocksWidth(),B.getTotalBlockHeight());
         primaryStage.setScene(scene);
         primaryStage.setTitle("Simulation");
         primaryStage.show();
     }
    public static void main(String[] args) throws IOException {
        
        Application.launch(args);
        
    }
}
