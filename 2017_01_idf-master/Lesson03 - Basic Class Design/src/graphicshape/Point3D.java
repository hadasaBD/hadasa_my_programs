package graphicshape;

/**
 * Created by eladlavi on 07/02/2017.
 */
public class Point3D extends Point {
    private int z;
    public Point3D(int x, int y, int z){
        super(x, y);
        setZ(z);
    }

    public void setZ(int z) {
        this.z = z;
    }

    public int getZ() {
        return z;
    }



    @Override
    protected String commaSeparated() {
        return super.commaSeparated() + "," + z;
    }
}
