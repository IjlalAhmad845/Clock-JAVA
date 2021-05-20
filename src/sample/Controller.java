package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

//****************************************THIS PROGRAM IS NOT OPTIMISED FOR SLOW AND SLUGGISH PCS***********************************

public class Controller implements Initializable { //Program starts
    @FXML Line second,hour,minute,smallminute,smallsec,smallhour;//declaring variables for clock needles
    @FXML
    AnchorPane pane;//anchor pane

    @FXML
    Circle point,point1,point2,point3,point4,point5,point6,point7;//little dot for second needle

    double sec=0,min=0,hr=0;//variables for calculation time
    double pi=3.14;

    ArrayList<Line> list=new ArrayList<>();//LIST FOR STORING DASH LINES FOR DETERMINATION OF TIME
    public void start(){//method for printing dash lines
        for(int i=1;i<=360;i++) {//first loop for printing main dash lines
            if (i % 30 == 0)//printing lines for every 30 degree or for hours
            {
                Line line = new Line();//creating dynamic lines
                //setting layout for lines USING TRIGONOMETRIC CIRCLE EQUATION
                line.setLayoutX(400+210 * Math.sin(i * pi / 180));//first moving the center for circle the desired coordinate
                line.setLayoutY(300+210 * Math.cos(i * pi / 180));//then setting mid point of lines radially
                line.setEndX(20 * Math.sin(i * pi / 180));//then printing lines radially of 20 length
                line.setEndY(20 * Math.cos(i * pi / 180));

                    line.setStartX(0);//setting starting point with respect to mid point of line
                    line.setStartY(0);
                    line.setStrokeWidth(7);//boldness of lines

                line.setSmooth(true);//anti aliasing
                line.setStroke(Color.valueOf("#4e5675"));//color
                line.setStrokeLineCap(StrokeLineCap.ROUND);
                list.add(line);//adding lines to list

            }
        }
        for(int i=1;i<=360;i++){//second loop for small minute clock
            if (i % 30 == 0)//same thing as above
            {
            Line line = new Line();
            line.setLayoutX(277+40 * Math.sin(i * pi / 180));
            line.setLayoutY(274+40 * Math.cos(i * pi / 180));
            line.setEndX(5 * Math.sin(i * pi / 180));
            line.setEndY(5 * Math.cos(i * pi / 180));

            line.setStartX(0);
                line.setStartY(0);
                line.setStrokeWidth(1);



            line.setSmooth(true);
            line.setStroke(Color.valueOf("#4e5675"));
            line.setStrokeLineCap(StrokeLineCap.ROUND);
            list.add(line);
        }
        }
        for(int i=1;i<=360;i++){//third loop for small second clock
            if (i % 6 == 0)
            {
                Line line = new Line();
                line.setLayoutX(529+40 * Math.sin(i * pi / 180));
                line.setLayoutY(274+40 * Math.cos(i * pi / 180));
                line.setEndX(5 * Math.sin(i * pi / 180));
                line.setEndY(5 * Math.cos(i * pi / 180));

                line.setStartX(0);
                line.setStartY(0);
                line.setStrokeWidth(1);



                line.setSmooth(true);
                line.setStroke(Color.valueOf("#4e5675"));
                line.setStrokeLineCap(StrokeLineCap.ROUND);
                list.add(line);
            }
        }
        for(int i=1;i<=360;i++){//fourth loop for small 24 hour clock
            if (i % 15 == 0)//same thing as above
            {
                Line line = new Line();
                line.setLayoutX(400+40 * Math.sin(i * pi / 180));
                line.setLayoutY(164+40 * Math.cos(i * pi / 180));
                line.setEndX(5 * Math.sin(i * pi / 180));
                line.setEndY(5 * Math.cos(i * pi / 180));

                line.setStartX(0);
                line.setStartY(0);
                line.setStrokeWidth(1);



                line.setSmooth(true);
                line.setStroke(Color.valueOf("#4e5675"));
                line.setStrokeLineCap(StrokeLineCap.ROUND);
                list.add(line);
            }
        }


        pane.getChildren().addAll(list);//adding all the lines to the pane

    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {//this method is called at the very beginning




        Thread t = new Thread(new Runnable() {//new thread for animation
            @Override
            public void run() {



                for(double j=0;j>=0;j++) {//infinite loop for continuous animation
                    //storing seconds and converting to degrees and reversing anticlockwise to clockwise
                    sec = 360 - 6 * java.time.LocalTime.now().getSecond();
                    //storing minutes with additional degrees for smooth transition
                    min = 360 - 6 * java.time.LocalTime.now().getMinute() - 6 * java.time.LocalTime.now().getSecond() / 60;
                    //storing seconds with additional degrees for smooth transition
                    hr = 360 - 30 * (java.time.LocalTime.now().getHour()%12) - 6 * java.time.LocalTime.now().getMinute() / 12;

                    //HERE STARTS THE MAIN LOGIC
                    //printing needles with trigonometric circle equation after setting the center at desired coordinate
                    second.setEndX(-100 + 200 * Math.sin(sec * pi / 180 - pi));//200 radius
                    second.setEndY(200 * Math.cos(sec * pi / 180 - pi));//subtracting pi for initial location

                    minute.setEndX(-100 + 190 * Math.sin(min * pi / 180 - pi));//190 radius
                    minute.setEndY(190 * Math.cos(min * pi / 180 - pi));

                    hour.setEndX(-100 + 180 * Math.sin(hr * pi / 180 -pi));//180 radius
                    hour.setEndY(180 * Math.cos(hr * pi / 180 -pi));

                    //printing small needles for small clocks
                    smallminute.setEndX( 40 * Math.sin(min * pi / 180 - pi));//40 radius
                    smallminute.setEndY(40 * Math.cos(min * pi / 180 - pi));

                    //printing small 24 hr needle with 24 hr time format ,multiplying 15 for 24 equal parts
                    smallhour.setEndX(40 * Math.sin((24-java.time.LocalTime.now().getHour())*15 * pi / 180-pi));//40 radius
                    smallhour.setEndY(40 * Math.cos((24-java.time.LocalTime.now().getHour())*15 * pi / 180-pi));

                    //40 radius ,using nano seconds for smoothness
                    smallsec.setEndX( 40 * Math.sin((60-java.time.LocalTime.now().getSecond()-.000000001*java.time.LocalTime.now().getNano())*6 * pi / 180 - pi));
                    smallsec.setEndY( 40 * Math.cos((60-java.time.LocalTime.now().getSecond()-.000000001*java.time.LocalTime.now().getNano())*6 * pi / 180 - pi));


                     points();

                    
                }
            }
        });
        t.start();//starting new threads
    start();// calling printing lines method
    }

    public void points(){           //small points for continuous rotation of points at 260 distance radius

        //every point is differ by 45 degrees
        point.setLayoutX(400 + 260 * Math.sin((java.time.LocalTime.now().getSecond()+.000000001*java.time.LocalTime.now().getNano())*6 * pi / 180+0));
        point.setLayoutY(300 + 260 * Math.cos((java.time.LocalTime.now().getSecond()+.000000001*java.time.LocalTime.now().getNano())*6  * pi / 180+0));

        point1.setLayoutX(400 + 260 * Math.sin((java.time.LocalTime.now().getSecond()+.000000001*java.time.LocalTime.now().getNano())*6 * pi / 180+(pi/4)));
        point1.setLayoutY(300 + 260 * Math.cos((java.time.LocalTime.now().getSecond()+.000000001*java.time.LocalTime.now().getNano())*6  * pi / 180+(pi/4)));

        point2.setLayoutX(400 + 260 * Math.sin((java.time.LocalTime.now().getSecond()+.000000001*java.time.LocalTime.now().getNano())*6 * pi / 180+(pi/2)));
        point2.setLayoutY(300 + 260 * Math.cos((java.time.LocalTime.now().getSecond()+.000000001*java.time.LocalTime.now().getNano())*6  * pi / 180+(pi/2)));

        point3.setLayoutX(400 + 260 * Math.sin((java.time.LocalTime.now().getSecond()+.000000001*java.time.LocalTime.now().getNano())*6 * pi / 180+(3*pi/4)));
        point3.setLayoutY(300 + 260 * Math.cos((java.time.LocalTime.now().getSecond()+.000000001*java.time.LocalTime.now().getNano())*6  * pi / 180+(3*pi/4)));

        point4.setLayoutX(400 + 260 * Math.sin((java.time.LocalTime.now().getSecond()+.000000001*java.time.LocalTime.now().getNano())*6 * pi / 180+(pi)));
        point4.setLayoutY(300 + 260 * Math.cos((java.time.LocalTime.now().getSecond()+.000000001*java.time.LocalTime.now().getNano())*6  * pi / 180+(pi)));

        point5.setLayoutX(400 + 260 * Math.sin((java.time.LocalTime.now().getSecond()+.000000001*java.time.LocalTime.now().getNano())*6 * pi / 180+(5*pi/4)));
        point5.setLayoutY(300 + 260 * Math.cos((java.time.LocalTime.now().getSecond()+.000000001*java.time.LocalTime.now().getNano())*6  * pi / 180+(5*pi/4)));

        point6.setLayoutX(400 + 260 * Math.sin((java.time.LocalTime.now().getSecond()+.000000001*java.time.LocalTime.now().getNano())*6 * pi / 180+(6*pi/4)));
        point6.setLayoutY(300 + 260 * Math.cos((java.time.LocalTime.now().getSecond()+.000000001*java.time.LocalTime.now().getNano())*6  * pi / 180+(6*pi/4)));

        point7.setLayoutX(400 + 260 * Math.sin((java.time.LocalTime.now().getSecond()+.000000001*java.time.LocalTime.now().getNano())*6 * pi / 180+(7*pi/4)));
        point7.setLayoutY(300 + 260 * Math.cos((java.time.LocalTime.now().getSecond()+.000000001*java.time.LocalTime.now().getNano())*6  * pi / 180+(7*pi/4)));

    }


}


