package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.*;
import java.util.Arrays;

public class Main extends Application {
    public int s=0;
    public int pen=2;
    public int pic=0;
    String myS=null;
    String[] new_s={};
    String[] copy_int=null;
    String[] copy_int_s=null;
    String[] copy_1=null;
    int[] final_int=null;
    int size=10;
    double x_dis=40;
    double y_dis=25;
    @Override
    public void start(Stage primaryStage) throws Exception,IOException {
        Parent root = FXMLLoader.load(getClass().getResource("fm.fxml"));
        TextField mTextFiled_A = (TextField) root.lookup("#textField");
        TextArea mtextArea_A=(TextArea) root.lookup("#TextArea_A");
        Button mButton_a=(Button) root.lookup("#button_OPEN");
        Button mButton_b=(Button) root.lookup("#Button_B");
        Button button_draw=(Button)root.lookup("#Button_draw");
        Canvas my_Canvas=(Canvas)root.lookup("#My_Canvas");
        Canvas mini_canvas=(Canvas)root.lookup("#mini_c");
        Button btn_add=(Button)root.lookup("#add_btn");
        Button btn_dec=(Button)root.lookup("#decrease_btn");
        Slider slider=(Slider)root.lookup("#my_slider");
        ColorPicker my_picker=(ColorPicker)root.lookup("#my_picker");
        ImageView my_image=(ImageView)root.lookup("#my_i");
        Image image=new Image("PIC/0.png");
        Image image1=new Image("PIC/1.png");
        Image image2=new Image("PIC/2.png");
        my_image.setImage(image);

        GraphicsContext gc = my_Canvas.getGraphicsContext2D();
        gc.save();
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, my_Canvas.getWidth(), my_Canvas.getHeight());
        gc.setLineWidth(2);
        gc.setStroke(Color.BLACK);

        GraphicsContext gc_mini=mini_canvas.getGraphicsContext2D();
        gc_mini.save();
        gc_mini.setFill(Color.WHITE);
        gc_mini.fillRect(0, 0, mini_canvas.getWidth(), mini_canvas.getHeight());
        gc_mini.setLineWidth(2);
        gc_mini.setStroke(Color.BLACK);

        slider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                size=newValue.intValue();
                x_dis=40-(size-10)*2.0;
                y_dis=25-(size-10)*1.2;
            }
        });

        btn_add.setOnAction(event2 -> {
            pen+=2;
            gc.setLineWidth(pen);
            gc_mini.setLineWidth(pen);
        });
        btn_dec.setOnAction(event3 ->{
            pen-=2;
            gc.setLineWidth(pen);
            gc_mini.setLineWidth(pen);
        });
        my_picker.setOnAction((ActionEvent t) -> {
            Color new_c=my_picker.getValue();
            gc.setStroke(new_c);
            gc_mini.setStroke(new_c);

        });
//        btn_yellow.setOnAction(event4 -> {gc.setStroke(Color.YELLOW);});
        primaryStage.setTitle("WORD READ");

        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();//是窗口最大化，填充屏幕
        primaryStage.setX(primaryScreenBounds.getMinX());
        primaryStage.setY(primaryScreenBounds.getMinY());
        primaryStage.setWidth(primaryScreenBounds.getWidth());
        primaryStage.setHeight(primaryScreenBounds.getHeight());

        mButton_a.setOnAction(e->{
            myS=null;
            new_s=null;
            copy_int=null;
            copy_int_s=null;
            final_int=null;
            try {
                //String url=getClass().getResource("chineseCharacter.txt").toString();
                String URLString = "http://web1701142149034.hk.bdysite.com/wp-content/uploads/2017/05/aaa.txt";
                int a = Integer.parseInt(mTextFiled_A.getText());
                Readword readword=new Readword(URLString,a);
                //Readword readword=new Readword(url,a);
                mtextArea_A.setText(readword.getWords());

            }
            catch (Exception ex){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText("请输入数字");
                alert.show();
            }
        });
        mButton_b.setOnAction(event1 -> {
            //清屏
            gc.save();
            gc.setFill(Color.WHITE);
            gc.fillRect(0, 0, my_Canvas.getWidth(), my_Canvas.getHeight());

            gc_mini.save();
            gc_mini.setFill(Color.WHITE);
            gc_mini.fillRect(0, 0, mini_canvas.getWidth(), mini_canvas.getHeight());

            my_image.setImage(image);
        });

        button_draw.setOnAction(event -> {
            s=1;
            myS=mtextArea_A.getText();
            //处理读取到的文本域的text
            new_s=myS.split("-64,0");
            copy_1=new String[200];
            int a=0;
            int x=0;
            for(int i=0;i<new_s.length;i++){
                String[] int_1=new_s[i].split(",");
                for(int j=0;j<int_1.length;j++){
                    copy_1[a]=int_1[j];
                    a++;
                }
                copy_1[a]="100";
                a++;
            }
            for(int i=0;i<copy_1.length;i++){
                if (copy_1[i]==null){
                    x=i;
                    break;
                }
            }
            copy_1=Arrays.copyOf(copy_1,x-1);

            copy_int=new String[copy_1.length];
            int k=0;
            for(int i=0;i<copy_1.length;i++){
                if("".equals(copy_1[i])){
                    continue;
                }else{
                    copy_int[k]=copy_1[i];
                    k++;
                }
            }
            for(int j=0;j<copy_int.length;j++){
                if(copy_int[j]==null){
                    copy_int_s= Arrays.copyOf(copy_int,j);
                    break;
                }
            }
            final_int=new int[copy_int_s.length];
            for(int i=0;i<copy_int_s.length;i++){
                final_int[i]=Integer.parseInt(copy_int_s[i]);
            }
            mtextArea_A.appendText('\n'+Arrays.toString(final_int));//test
            mtextArea_A.appendText('\n'+String.valueOf(final_int.length));

            EventHandler<ActionEvent> eventHandler= e -> {
                if (final_int[s]==100){
                    s+=1;
                }
                if(s<final_int.length-4){
                    gc.save();
                    gc_mini.save();
                    if(final_int[s+2]==100){
                        s+=3;
                    }
                    gc.strokeLine((final_int[s]+x_dis)*size,(final_int[s+1]+y_dis)*size,(final_int[s+2]+x_dis)*size,(final_int[s+3]+y_dis)*size);
                    gc.restore();
                    gc_mini.strokeLine((final_int[s]+20)*5,(final_int[s+1]+20)*5,(final_int[s+2]+20)*5,(final_int[s+3]+20)*5);
                    gc_mini.restore();
                    pic++;
                    if(pic%2==0){
                        my_image.setImage(image1);
                    }else if(pic%2==1){
                        my_image.setImage(image2);
                    }
                    if (final_int[s]==final_int[s+2]&&final_int[s+1]==final_int[s+3]){
                        s+=4;
                    } else{
                        s+=2;
                    }
                }
            };
            KeyFrame keyFrame=new KeyFrame(Duration.millis(500),eventHandler);
            Timeline animation=new Timeline(keyFrame);
            animation.setCycleCount(Timeline.INDEFINITE);
            animation.play();
        });
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }

}