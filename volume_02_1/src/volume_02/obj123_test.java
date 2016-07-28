/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package volume_02;

/**
 *
 * @author IMG_1
 */
import java.awt.Dimension;
import javax.swing.*;
import vtk.*;

public class obj123_test extends JPanel {

    static {
        System.loadLibrary("vtkCommonJava");
        System.loadLibrary("vtkFilteringJava");
        System.loadLibrary("vtkIOJava");
        System.loadLibrary("vtkImagingJava");
        System.loadLibrary("vtkGraphicsJava");
        System.loadLibrary("vtkRenderingJava");
    }

    public static vtkMarchingCubes marchingCubes = new vtkMarchingCubes();
    public static vtkMassProperties mass = new vtkMassProperties();
    public static vtkVolume16Reader v16 = new vtkVolume16Reader();

    public obj123_test() {
//        v16.SetDataDimensions(400, 400);
//        v16.SetFilePrefix("C:\\Users\\IMG_1\\Desktop\\QRM_04mmCTno_linepairs\\obj03\\obj03_");
        v16.SetDataDimensions(600, 600);
        v16.SetFilePrefix("C:\\Users\\IMG_1\\Desktop\\QRM_025mmCTno_linepairs\\obj03\\obj03_");

        v16.SetFilePattern("%s%.3d.raw");
        v16.SetImageRange(0, 75);

        for (int i = 739; i <= 1131; i++) {
            report(i);
        }
    }

    public static void report(int con_value) {

        marchingCubes.SetInput(v16.GetOutput());
        marchingCubes.SetValue(0, con_value);
        marchingCubes.Update();

        mass.SetInput(marchingCubes.GetOutput());
        System.out.printf("%.3f\n", mass.GetVolume());

        marchingCubes.RemoveAllInputs();
        mass.RemoveAllInputs();

    }

}
