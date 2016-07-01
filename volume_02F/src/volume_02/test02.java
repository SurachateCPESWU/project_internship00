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

public class test02 extends JPanel {

    static {
        System.loadLibrary("vtkCommonJava");
        System.loadLibrary("vtkFilteringJava");
        System.loadLibrary("vtkIOJava");
        System.loadLibrary("vtkImagingJava");
        System.loadLibrary("vtkGraphicsJava");
        System.loadLibrary("vtkRenderingJava");
    }

    public static vtkVolume16Reader v16;
    public static vtkContourFilter marchingCubes = new vtkContourFilter();
    public static vtkMassProperties mass = new vtkMassProperties();
    public static vtkMarchingContourFilter contourFilter = new vtkMarchingContourFilter();

    public test02() {
        v16 = new vtkVolume16Reader();
        v16.SetDataDimensions(512, 512);
//        v16.SetFilePrefix("C:\\Users\\IMG_1\\Desktop\\DATA_IMG\\512cube1f\\512cube1f_");
//        v16.SetFilePrefix("C:\\Users\\IMG_1\\Desktop\\DATA_IMG\\512cube01\\512cube01_");
//        v16.SetFilePrefix("C:\\Users\\IMG_1\\Desktop\\DATA_IMG\\512cube02\\512cube02_");
//        v16.SetFilePrefix("C:\\Users\\IMG_1\\Desktop\\DATA_IMG\\512cube02_5000\\512cube02_5000_");
//        v16.SetFilePrefix("C:\\Users\\IMG_1\\Desktop\\DATA_IMG\\512cylinder1f\\512cylinder1f_");
//        v16.SetFilePrefix("C:\\Users\\IMG_1\\Desktop\\DATA_IMG\\512cylinder01\\512cylinder01_");
        v16.SetFilePrefix("C:\\Users\\IMG_1\\Desktop\\DATA_IMG\\512cylinder02\\512cylinder02_");
//        v16.SetFilePrefix("C:\\Users\\IMG_1\\Desktop\\DATA_IMG\\512cylinder02_5000\\512cylinder01_5000_");
        
//        v16.SetFilePrefix("C:\\Users\\IMG_1\\Desktop\\DATA_IMG\\cube1f\\cube1f_");
//        v16.SetFilePrefix("C:\\Users\\IMG_1\\Desktop\\DATA_IMG\\cube01\\cube01_");
//        v16.SetFilePrefix("C:\\Users\\IMG_1\\Desktop\\DATA_IMG\\cube02\\cube02_");
//        
//        v16.SetFilePrefix("C:\\Users\\IMG_1\\Desktop\\DATA_IMG\\cylinder1f\\cylinder1f_");
//        v16.SetFilePrefix("C:\\Users\\IMG_1\\Desktop\\DATA_IMG\\cylinder01\\cylinder01_");
//        v16.SetFilePrefix("C:\\Users\\IMG_1\\Desktop\\DATA_IMG\\cylinder02\\cylinder02_");
//        v16.SetFilePrefix("C:\\Users\\IMG_1\\Desktop\\DATA_IMG\\cylinder02\\cylinder02_");
        v16.SetFilePattern("%s%.3d.raw");
        v16.SetImageRange(0, 511);

        for (int i = 1; i <= 1000; i++) {
            test02_call(i);
        }
    }

    public static void test02_call(int value) {

//        ////**vtkMarchingContourFilter**///
//       
//        contourFilter.SetInput(v16.GetOutput());
//        contourFilter.SetNumberOfContours(value);
//        contourFilter.SetValue(0, value);
//        contourFilter.Update();
//
//
//        mass.SetInput(contourFilter.GetOutput());
//        System.out.printf("%.3f" + "\n", mass.GetVolume());
//        
//        mass.RemoveAllInputs();
//        contourFilter.RemoveAllInputs();
//        //////////////////////////////////
        
        
        ////**vtkMarchingCubes**////
        marchingCubes.SetInput(v16.GetOutput());
//        marchingCubes.GenerateValues(10, 500, 999);
        marchingCubes.SetValue(0, value );

        mass.SetInput(marchingCubes.GetOutput());
//        System.out.printf("Value  =" + value  + "  Volume = " + "%.3f" + "\n", mass.GetVolume());
        System.out.printf("%.3f" + "\n", mass.GetVolume());
        ///////////////////////////
        
        marchingCubes.RemoveAllInputs();
        mass.RemoveAllInputs();
    }

}
