/* 
* Exercise: java 2 exercise 18.35
* Lauren Smith 
* 2/23/21 
* Description: displays an HTree with desired depth
*/
package pkg1835;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        //makes an HBox with some spacing to organize the pane 
        HBox hbox=new HBox(10); 
        //makes an empty text feild for number of branches to be entered
        TextField tfOrder=new TextField(); 
        //sets column number for text field for apperance 
        tfOrder.setPrefColumnCount(4);
        //alins data in text field to bottom right 
        tfOrder.setAlignment(Pos.BOTTOM_RIGHT); 
        //adds the text field alongside a label asking for input to the hbox
        hbox.getChildren().addAll(new Label("Enter an order: "),tfOrder);
        //alins hbox items to the center of the hbox
        hbox.setAlignment(Pos.CENTER);
        //makes pane out of HTreePane class 
        HTreePane pane=new HTreePane(); 
        //makes a BorderPane to hold HBox and HTreePane 
        BorderPane borderpane=new BorderPane(); 
        //sets HTreePane to the center 
        borderpane.setCenter(pane); 
        //sets Hbox to the bottom 
        borderpane.setBottom(hbox); 
        //Lambda for when text is changed in textarea 
        tfOrder.setOnAction(e->{
           //sets depth to input in text book
            pane.setDepth(Integer.parseInt(tfOrder.getText())); 
        });

        Scene scene = new Scene(borderpane, 300, 300);

        primaryStage.setTitle("HTree");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
    
     //inner class TreePane to create and display recursive tree
    class HTreePane extends Pane 
    { 
        //variable for how many layers of H's 
        int depth=1; 
        
        //method when depth is changed in the text area 
        public void setDepth(int d) 
        {
            //clears previous HTree
            getChildren().clear(); 
            //sets depth to new depth
            depth=d; 
            //runs recursive drawFullH method to draw the HTree with new depth
            drawFullH(d,150,150,120); 
        }
        
        //method that draws 1 singular H 
        public void drawH(double x, double y, double length) 
        {
          //calculates beginning and ending x's and y's based off start and desired line length
           //divides length by 2 and adds or subtracts from middle x/y 
           double x0=x-length/2;  
           double x1=x+length/2; 
           double y0= y-length/2; 
           double y1=y+length/2; 
           //draws the three lines to form an  H 
           getChildren().add(new Line(x0,y0,x0,y1)); 
           getChildren().add(new Line(x1,y0,x1,y1));  
           getChildren().add(new Line(x0,y,x1,y)); 
        } 
        
        //recursive method that draws the full HTree
        public void drawFullH(int depth, double x, double y, double size) 
        {
            //if depth is 0 Htree is drawn return
            if(depth==0) 
            {
                return; 
            } 
            else 
            {
                //draws a signular H in center of pane using drawH method
                drawH(x,y,size); 
                
                //calculates line points by dividing desired t size by two and adding or subtracting from mid point
                double x0=x-size/2; 
                double x1=x+size/2; 
                double y0=y-size/2; 
                double y1=y+size/2; 
                
                //draws all lines of the HTree recursively, dividing size by 2 each time
                drawFullH(depth-1,x0,y0,size/2); 
                drawFullH(depth-1,x0,y1,size/2);  
                drawFullH(depth-1,x1,y0,size/2);  
                drawFullH(depth-1,x1,y1,size/2); 
            }
           
        }
        
        
    }
}
