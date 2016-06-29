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

public class test01 extends JPanel {

    
    
    static {
        System.loadLibrary("vtkCommonJava");
        System.loadLibrary("vtkFilteringJava");
        System.loadLibrary("vtkIOJava");
        System.loadLibrary("vtkImagingJava");
        System.loadLibrary("vtkGraphicsJava");
        System.loadLibrary("vtkRenderingJava");
    }
    
    public static vtkMarchingCubes marchingCubes = new vtkMarchingCubes();
    public static vtkMarchingContourFilter mcontourFilter = new vtkMarchingContourFilter();
    public static vtkContourFilter contourFilter = new vtkContourFilter();
    public static vtkMassProperties mass = new vtkMassProperties();
    public static vtkVolume16Reader v16 = new vtkVolume16Reader();
    public test01() {
        
        for (int i = 0; i <= 5; i++) {
            System.out.println("Data :" + i + "\n");
            for (int j = 0; j <= 2; j++) {
                report(i, j);
            }
        }

    }

    public static void report(int n, int op) {
        
        int con_value = 0;
        
        v16.SetDataDimensions(512, 512);
        if (n == 0) {
            v16.SetFilePrefix("C:\\Users\\IMG_1\\Desktop\\DATA_IMG\\512cube1f\\512cube1f_");
            con_value = 499;
        }
        if (n == 1) {
            v16.SetFilePrefix("C:\\Users\\IMG_1\\Desktop\\DATA_IMG\\512cube01\\512cube01_");
            con_value = 499;
        }
        if (n == 2) {
            v16.SetFilePrefix("C:\\Users\\IMG_1\\Desktop\\DATA_IMG\\512cube02\\512cube02_");
            con_value = 499;
        }
        if (n == 3) {
            v16.SetFilePrefix("C:\\Users\\IMG_1\\Desktop\\DATA_IMG\\512cylinder1f\\512cylinder1f_");
            con_value = 499;
        }
        if (n == 4) {
            v16.SetFilePrefix("C:\\Users\\IMG_1\\Desktop\\DATA_IMG\\512cylinder01\\512cylinder01_");
            con_value = 478;
        }
        if (n == 5) {
            v16.SetFilePrefix("C:\\Users\\IMG_1\\Desktop\\DATA_IMG\\512cylinder02\\512cylinder02_");
            con_value = 477;
        }
        v16.SetFilePattern("%s%.3d.raw");
        v16.SetImageRange(0, 511);

        if (op == 0) {
            ////**vtkMarchingCubes**////
            marchingCubes.SetInput(v16.GetOutput());
            marchingCubes.ComputeScalarsOn();
            marchingCubes.SetValue(0, con_value);
            marchingCubes.Update();


            mass.SetInput(marchingCubes.GetOutput());
            System.out.printf("marchingCubes =  " + "%.3f\n", mass.GetVolume());

            marchingCubes.RemoveAllInputs();
            mass.RemoveAllInputs();

            ///////////////////////////
        }

        if (op == 1) {
            ////**vtkMarchingContourFilter**///
            mcontourFilter.SetInput(v16.GetOutput());
            mcontourFilter.ComputeScalarsOn();
            mcontourFilter.SetValue(0, con_value);
            mcontourFilter.Update();

            mass.SetInput(mcontourFilter.GetOutput());
            System.out.printf("mcontourFilter = " + "%.3f\n", mass.GetVolumeProjected());

            mcontourFilter.RemoveAllInputs();
            mass.RemoveAllInputs();

            ///////////////////////////
        }

        if (op == 2) {
            ////**vtkMarchingContourFilter**///
            contourFilter.SetInput(v16.GetOutput());
            contourFilter.ComputeScalarsOn();
            contourFilter.SetValue(0, con_value);
            contourFilter.Update();

           
            mass.SetInput(contourFilter.GetOutput());
            System.out.printf("contourFilter = " + "%.3f\n", mass.GetVolumeProjected());

            contourFilter.RemoveAllInputs();
            mass.RemoveAllInputs();

            ///////////////////////////
        }

    }

}
