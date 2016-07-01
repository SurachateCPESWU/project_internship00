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
import static volume_02.test02.v16;
import vtk.*;

public class test05 {

    static {
        System.loadLibrary("vtkCommonJava");
        System.loadLibrary("vtkFilteringJava");
        System.loadLibrary("vtkIOJava");
        System.loadLibrary("vtkImagingJava");
        System.loadLibrary("vtkGraphicsJava");
        System.loadLibrary("vtkRenderingJava");
    }

    public test05() {
        long startTime = System.nanoTime();
        vtkVolume16Reader v16 = new vtkVolume16Reader();
        v16.SetDataDimensions(512, 512);
//        v16.SetFilePrefix("C:\\Users\\IMG_1\\Desktop\\DATA_IMG\\512cube1f\\512cube1f_");
//        v16.SetFilePrefix("C:\\Users\\IMG_1\\Desktop\\DATA_IMG\\512cube01\\512cube01_");
//        v16.SetFilePrefix("C:\\Users\\IMG_1\\Desktop\\DATA_IMG\\512cube02\\512cube02_");
//        v16.SetFilePrefix("C:\\Users\\IMG_1\\Desktop\\DATA_IMG\\512cube02_5000\\512cube02_5000_");
//        v16.SetFilePrefix("C:\\Users\\IMG_1\\Desktop\\DATA_IMG\\512cylinder1f\\512cylinder1f_");
        v16.SetFilePrefix("C:\\Users\\IMG_1\\Desktop\\DATA_IMG\\512cylinder01\\512cylinder01_");
//        v16.SetFilePrefix("C:\\Users\\IMG_1\\Desktop\\DATA_IMG\\512cylinder02\\512cylinder02_");
//        v16.SetFilePrefix("C:\\Users\\IMG_1\\Desktop\\DATA_IMG\\512cylinder02_5000\\512cylinder01_5000_");
        v16.SetFilePattern("%s%.3d.raw");
        v16.SetImageRange(0, 511);
        
        

//        ////**vtkMarchingCubes**////
//        vtkMarchingCubes marchingCubes = new vtkMarchingCubes();
//        marchingCubes.SetInput(v16.GetOutput());
//        marchingCubes.SetValue(0, 499);
//        marchingCubes.Update();
//
//        vtkMassProperties mass1 = new vtkMassProperties();
//        mass1.SetInput(marchingCubes.GetOutput());
//        System.out.printf("vtkMarchingCubes %.5f\n", mass1.GetVolume());
//
//        ///////////////////////////
//
//        ////**vtkMarchingContourFilter**///
//        vtkMarchingContourFilter contourFilter = new vtkMarchingContourFilter();
//        contourFilter.SetInput(v16.GetOutput());
//        contourFilter.SetValue(0, 499);
//        contourFilter.Update();
//        vtkMassProperties mass2 = new vtkMassProperties();
//        mass2.SetInput(contourFilter.GetOutput());
//        System.out.printf("vtkMarchingContourFilter %.5f\n", mass2.GetVolume());
//        //////////////////////////////////
        
        vtkContourFilter ncontourFilter = new vtkContourFilter();
        ncontourFilter.SetInput(v16.GetOutput());
        ncontourFilter.SetValue(0, 499);
        ncontourFilter.Update();
        
        vtkMassProperties mass3 = new vtkMassProperties();
        mass3.SetInput(ncontourFilter.GetOutput());
        System.out.printf("ContourFilter %.5f\n", mass3.GetVolume());

        //////////////////////////////////
//        
//        vtkDiscreteMarchingCubes discreteMarching = new vtkDiscreteMarchingCubes();
//        discreteMarching.SetInput(v16.GetOutput());
//        discreteMarching.SetValue(0, 1000);
//        discreteMarching.Update();
//
//        vtkMassProperties mass4 = new vtkMassProperties();
//        mass4.SetInput(discreteMarching.GetOutput());
//        System.out.printf("discreteMarching %.5f\n", mass4.GetVolume());
        long endTime = System.nanoTime();
        System.out.println("Time = "+(endTime - startTime)/1000000);
    }

//    public static void MarchingContour() {
//
//        ////**vtkMarchingContourFilter**///
//        vtkMarchingContourFilter contourFilter = new vtkMarchingContourFilter();
//        contourFilter.SetInput(v16.GetOutput());
//        contourFilter.GenerateValues(10, 500, 999);
//        contourFilter.Update();
//
//        vtkPolyDataMapper contourMapper = new vtkPolyDataMapper();
//        contourMapper.SetInput(contourFilter.GetOutput());
//        vtkActor contourActor = new vtkActor();
//        contourActor.SetMapper(contourMapper);
//
//        vtkMassProperties mass = new vtkMassProperties();
//        mass.SetInput(contourFilter.GetOutput());
//        System.out.print(mass.GetVolume() / 10);
//
//        //////////////////////////////////
//    }
}
