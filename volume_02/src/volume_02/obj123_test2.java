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

public class obj123_test2 extends JPanel {

    static {
        System.loadLibrary("vtkCommonJava");
        System.loadLibrary("vtkFilteringJava");
        System.loadLibrary("vtkIOJava");
        System.loadLibrary("vtkImagingJava");
        System.loadLibrary("vtkGraphicsJava");
        System.loadLibrary("vtkRenderingJava");
    }

    public static vtkVolume16Reader v16 = new vtkVolume16Reader();
    public static vtkImageGaussianSmooth gauss = new vtkImageGaussianSmooth();
    public static vtkImageShrink3D shrink = new vtkImageShrink3D();
    public static vtkMarchingCubes marchingCubes = new vtkMarchingCubes();
    public static vtkPolyDataNormals nm = new vtkPolyDataNormals();
    public static vtkDecimatePro dec = new vtkDecimatePro();
    public static vtkMassProperties mass = new vtkMassProperties();

    public obj123_test2() {
//        v16.SetDataDimensions(400, 400);
//        v16.SetFilePrefix("C:\\Users\\IMG_1\\Desktop\\QRM_04mmCTno_linepairs\\obj03\\obj03_");
        v16.SetDataDimensions(600, 600);
        v16.SetFilePrefix("C:\\Users\\IMG_1\\Desktop\\QRM_025mmCTno_linepairs\\obj01\\obj01_");

        v16.SetFilePattern("%s%.3d.raw");

//        #### 0.4 ####
//        0-51      1070
//        0-44      1134
//        0-44      1169
//        #### 0.25 ####
//        0-72      1014
//        0-75      1099
//        0-75      1136
        
        int o1 = 1070;
        int o2 = 1134;
        int o3 = 1169;
        int o4 = 1014;
        int o5 = 1099;
        int o6 = 1136;

        v16.SetImageRange(0, 72);

        for (int ngauss = 0; ngauss <= 1; ngauss++) {
            for (int nshrink = 0; nshrink <= 1; nshrink++) {
                for (int ndec = 1; ndec <= 1; ndec++) {
                    System.out.print("\n\n\nGauss = " + ngauss + "; " + "Shrink = " + nshrink + "; " + "Dec = " + ndec + " ;\n");
                    for (int con_value = o4 - 50; con_value < o4 + 50; con_value++) {
                        report(con_value, ngauss, nshrink, ndec);
                    }
                }
            }
        }
    }

    public static void report(int con_value, int ngauss, int nshrink, int ndec) {

        gauss.SetInput(v16.GetOutput());
        gauss.SetDimensionality(3);
        if (ngauss == 0) {
            gauss.SetRadiusFactors(1.0, 1.0, 1.0); //1
        } else if (ngauss == 1) {
            gauss.SetRadiusFactors(0.5, 0.5, 0.5); //2
        } else {
            System.out.println("Error in gauss");
        }
        gauss.Update();

        shrink.SetInput(gauss.GetOutput());
        shrink.AveragingOn();

        if (nshrink == 0) {
            shrink.SetShrinkFactors(1, 1, 1);//1
        } else if (nshrink == 1) {
            shrink.SetShrinkFactors(2, 2, 2);//2
        } else {
            System.out.println("Error in shrink");
        }
        shrink.Update();

        marchingCubes.SetInput(shrink.GetOutput());
        marchingCubes.SetValue(0, con_value);
        marchingCubes.Update();

        nm.SetInput(marchingCubes.GetOutput());
        nm.SetFeatureAngle(60);
        nm.Update();

        dec.SetInput(nm.GetOutput());

        if (ndec == 0) {
            dec.SetTargetReduction(10 / 100.0); //1
        } else if (ndec == 1) {
            dec.SetTargetReduction(50 / 100.0); //2
        } else {
            System.out.println("Error in dec");
        }
        dec.Update();

        mass.SetInput(dec.GetOutput());
        System.out.printf("%.3f\n", mass.GetVolume());

        gauss.RemoveAllInputs();
        shrink.RemoveAllInputs();
        marchingCubes.RemoveAllInputs();
        nm.RemoveAllInputs();
        dec.RemoveAllInputs();
        mass.RemoveAllInputs();

    }

}
