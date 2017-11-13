package wuerfel;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller  extends Application implements Initializable{

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





    @FXML
    Slider xSlider;
    @FXML
    Slider ySlider;
    @FXML
    Slider zSlider;
    @FXML
    Group pCube;
    @FXML
    TextField xText;
    @FXML
    TextField yText;
    @FXML
    TextField zText;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        xSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            xText.setText(String.valueOf(newValue.intValue()));
            System.out.println("Slider Value Changed (newValue: " + newValue.intValue());});

        ySlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            yText.setText(String.valueOf(newValue.intValue()));
            System.out.println("Slider Value Changed (newValue: " + newValue.intValue());});

        zSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            zText.setText(String.valueOf(newValue.intValue()));
            System.out.println("Slider Value Changed (newValue: " + newValue.intValue());});

                buildScene();
        buildAxes();
        buildMolecule();
        buildCamera();
        addCube();

        pCube.getChildren().addAll(root);


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
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        Parent window = FXMLLoader.load(getClass().getResource(Views.window.getFileName()));
        primaryStage.setTitle("Wuerfel");
        primaryStage.setScene(new Scene(window, 1024,650));
        primaryStage.show();
        buildScene();
        buildCamera();
        buildAxes();
        buildMolecule();
        addCube();
    }
    /**
     * The main() method is ignored in correctly deployed JavaFX
     * application. main() serves only as fallback in case the
     * application can not be launched through deployment artifacts,
     * e.g., in IDEs with limited FX support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
