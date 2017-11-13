package wuerfel;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.control.Slider;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;

import java.io.IOException;


public class Main extends Application {
//    final Group sliders = new Group();

    final Group root = new Group();
    final Group axisGroup = new Group();
    final Xform world = new Xform();
    final PerspectiveCamera camera = new PerspectiveCamera(true);
    final Xform cameraXform = new Xform();
    final Xform cameraXform2 = new Xform();
    final Xform cameraXform3 = new Xform();
    private static final double CAMERA_INITIAL_DISTANCE = -450;
    private static final double CAMERA_INITIAL_X_ANGLE = 30.0;
    private static final double CAMERA_INITIAL_Y_ANGLE = 320.0;
    private static final double CAMERA_NEAR_CLIP = 0.1;
    private static final double CAMERA_FAR_CLIP = 10000.0;
    private static final double AXIS_LENGTH = 250.0;
    private static final double HYDROGEN_ANGLE = 104.5;
    final Xform moleculeGroup = new Xform();
    private Cube cube = new Cube();

    private static final double CONTROL_MULTIPLIER = 0.1;    private static final double SHIFT_MULTIPLIER = 10.0;    private static final double MOUSE_SPEED = 0.1;    private static final double ROTATION_SPEED = 2.0;    private static final double TRACK_SPEED = 0.3;

    double mousePosX;
    double mousePosY;
    double mouseOldX;
    double mouseOldY;
    double mouseDeltaX;
    double mouseDeltaY;



    private void buidlSliders(){

        Slider xAchse = new Slider(0,360,180);
        Slider yAchse = new Slider(0,360,180);
        Slider zAchse = new Slider(0,360,180);


    }



    private void buildCamera() {
        cameraXform.getChildren().add(cameraXform2);
        cameraXform2.getChildren().add(cameraXform3);
        cameraXform3.getChildren().add(camera);
        cameraXform3.setRotateZ(180.0);

        camera.setNearClip(CAMERA_NEAR_CLIP);
        camera.setFarClip(CAMERA_FAR_CLIP);
        camera.setTranslateZ(CAMERA_INITIAL_DISTANCE);
        cameraXform.ry.setAngle(CAMERA_INITIAL_Y_ANGLE);
        cameraXform.rx.setAngle(CAMERA_INITIAL_X_ANGLE);
        //root.getChildren().add(cameraXform);
    }

    private void buildAxes() {
        final PhongMaterial redMaterial = new PhongMaterial();
        redMaterial.setDiffuseColor(Color.DARKRED);
        redMaterial.setSpecularColor(Color.RED);

        final PhongMaterial greenMaterial = new PhongMaterial();
        greenMaterial.setDiffuseColor(Color.DARKGREEN);
        greenMaterial.setSpecularColor(Color.GREEN);

        final PhongMaterial blueMaterial = new PhongMaterial();
        blueMaterial.setDiffuseColor(Color.DARKBLUE);
        blueMaterial.setSpecularColor(Color.BLUE);

        final Box xAxis = new Box(AXIS_LENGTH, 1, 1);
        final Box yAxis = new Box(1, AXIS_LENGTH, 1);
        final Box zAxis = new Box(1, 1, AXIS_LENGTH);

        xAxis.setMaterial(redMaterial);
        yAxis.setMaterial(greenMaterial);
        zAxis.setMaterial(blueMaterial);

        axisGroup.getChildren().addAll(xAxis, yAxis, zAxis);


       axisGroup.setVisible(true);
        world.getChildren().addAll(axisGroup);
    }



    @Override
    public void start(Stage primaryStage) throws IOException {
//        Slider xAchse = new Slider(0,360,180);
//        Slider yAchse = new Slider(0,360,180);
//        Slider zAchse = new Slider(0,360,180);
//        AnchorPane mainAPane = new AnchorPane();
//        AnchorPane verticalObjects = new AnchorPane();
//        mainAPane.setPrefWidth(1020);
//        mainAPane.setPrefHeight(750);
//        mainAPane.setStyle("-fx-border-color: black");
//        verticalObjects.setStyle("-fx-border-color: green");
//        verticalObjects.setPrefWidth(1000);
//        verticalObjects.setPrefHeight(90);
//
//        VBox vBox = new VBox();
//        vBox.setStyle("-fx-border-color: red");
//        vBox.setPrefSize(verticalObjects.getPrefWidth(), verticalObjects.getPrefHeight());
//        mainAPane.getChildren().add(verticalObjects);
//        verticalObjects.getChildren().addAll(vBox);
//
//
//
//
//        vBox.getChildren().addAll(xAchse,yAchse,zAchse);
//
//        Scene scene = new Scene(mainAPane, 1024, 768, true);

//
//
////        Scene scene = new Scene(root, 1024, 768, true);
//        scene.setFill(Color.GREY);
////        handleKeyboard(scene, world);
////        handleMouse(scene, world);
//        primaryStage.setTitle("Würfel");
//        primaryStage.setScene(scene);
//        primaryStage.show();
//        scene.setCamera(camera);
//        buildScene();
//        buildCamera();
//        buildAxes();
//        buildMolecule();
//        addCube();


        Parent window = FXMLLoader.load(getClass().getResource(Views.window.getFileName()));
        primaryStage.setTitle("Wuerfel");
        primaryStage.setScene(new Scene(window, 1024,650));
        primaryStage.show();

    }
    /**
     * The main() method is ignored in correctly deployed JavaFX
     * application. main() serves only as fallback in case the
     * application can not be launched through deployment artifacts,
     * e.g., in IDEs with limited FX support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
//    public static void main(String[] args) {
//        launch(args);
//    }


    private void buildMolecule() {
        Xform moleculeXform = new Xform();
        Box mybox = new Box(30,30,30);
        mybox.setTranslateX(0.0);
        world.getChildren().addAll(mybox);
    }

    private void buildScene() {
        root.getChildren().add(world);
    }

    private void addCube() {

        world.getChildren().addAll(cube.getNode());
    }

    private void handleMouse(Scene scene, final Node root) {

        scene.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent me) {
                mousePosX = me.getSceneX();
                mousePosY = me.getSceneY();
                mouseOldX = me.getSceneX();
                mouseOldY = me.getSceneY();
            }
        });
        scene.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent me) {
                mouseOldX = mousePosX;
                mouseOldY = mousePosY;
                mousePosX = me.getSceneX();
                mousePosY = me.getSceneY();
                mouseDeltaX = (mousePosX - mouseOldX);
                mouseDeltaY = (mousePosY - mouseOldY);

                double modifier = 1.0;

                if (me.isControlDown()) {
                    modifier = CONTROL_MULTIPLIER;
                }
                if (me.isShiftDown()) {
                    modifier = SHIFT_MULTIPLIER;
                }
                if (me.isPrimaryButtonDown()) {
                    double modifierFactor=1;
                    cameraXform.ry.setAngle(cameraXform.ry.getAngle() -
                            mouseDeltaX*modifierFactor*modifier*ROTATION_SPEED);  //
                    cameraXform.rx.setAngle(cameraXform.rx.getAngle() +
                            mouseDeltaY*modifierFactor*modifier*ROTATION_SPEED);  // -
                }
                else if (me.isSecondaryButtonDown()) {
                    double z = camera.getTranslateZ();
                    double newZ = z + mouseDeltaX*MOUSE_SPEED*modifier;
                    camera.setTranslateZ(newZ);
                }
                else if (me.isMiddleButtonDown()) {
                    cameraXform2.t.setX(cameraXform2.t.getX() +
                            mouseDeltaX*MOUSE_SPEED*modifier*TRACK_SPEED);  // -
                    cameraXform2.t.setY(cameraXform2.t.getY() +
                            mouseDeltaY*MOUSE_SPEED*modifier*TRACK_SPEED);  // -
                }
            }
        }); // setOnMouseDragged
    } //handleMous



    private void handleKeyboard(Scene scene, final Node root) {

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case Z:
                        cameraXform2.t.setX(0.0);
                        cameraXform2.t.setY(0.0);
                        cameraXform.ry.setAngle(CAMERA_INITIAL_Y_ANGLE);
                        cameraXform.rx.setAngle(CAMERA_INITIAL_X_ANGLE);
                        break;
                    case X:
                        axisGroup.setVisible(!axisGroup.isVisible());
                        break;
                    case V:
                        moleculeGroup.setVisible(!moleculeGroup.isVisible());
                        break;
                } // switch
            } // handle()
        });  // setOnKeyPressed
    }  //  handleKeyboard()




}




