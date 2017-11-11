package wuerfel;

        import javafx.geometry.Point3D;
        import javafx.scene.Node;
        import javafx.scene.paint.Color;
        import javafx.scene.paint.PhongMaterial;
        import javafx.scene.shape.Box;

/**
 * Würfel Element, bei welchem die Drehachsen editiert werden kann.
 *
 * @author segessemann
 */
public class Cube {

    private final Box cube;
    private double axisX = 1;
    private double axisY = 0;
    private double axisZ = 0;

    /**
     * Konstruktor zum erstellen des Würfels.
     */
    public Cube() {
        final PhongMaterial greyMaterial = new PhongMaterial();
        greyMaterial.setDiffuseColor(Color.DARKGREY);
        greyMaterial.setSpecularColor(Color.GREY);

        cube = new Box(50, 50, 50);
        cube.setMaterial(greyMaterial);

    }

    /**
     * Gibt das Element (Würfel) zurück.
     *
     * @return Würfel.
     */
    public Node getNode() {
        return cube;
    }

    public void setAxisX(double x) {
        this.axisX = x;

    }

    public void setAxisY(double y) {
        this.axisY = y;
    }

    public void setAxisZ(double z) {
        this.axisZ = z;
    }

    /**
     * Erstellt die Drehachse als Punkt im Koordinatensystem.
     *
     * @return Drehachse von Würfel.
     */
    public Point3D getRotateAxis() {
        return new Point3D(axisX, axisY, axisZ);
    }

}
