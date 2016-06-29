/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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

    public test01() {

        vtkVolume16Reader v16 = new vtkVolume16Reader();
        v16.SetDataDimensions(512, 512);

//        v16.SetFilePrefix("C:\\Users\\IMG_1\\Desktop\\DATA_IMG\\512cube1f\\512cube1f_");
//        v16.SetFilePrefix("C:\\Users\\IMG_1\\Desktop\\DATA_IMG\\512cube01\\512cube01_");
//        v16.SetFilePrefix("C:\\Users\\IMG_1\\Desktop\\DATA_IMG\\512cube02\\512cube02_");
//        v16.SetFilePrefix("C:\\Users\\IMG_1\\Desktop\\DATA_IMG\\512cylinder1f\\512cylinder1f_");
//        v16.SetFilePrefix("C:\\Users\\IMG_1\\Desktop\\DATA_IMG\\512cylinder01\\512cylinder01_");
//        v16.SetFilePrefix("C:\\Users\\IMG_1\\Desktop\\DATA_IMG\\512cylinder02\\512cylinder02_");

//        v16.SetFilePrefix("C:\\Users\\IMG_1\\Desktop\\DATA_IMG\\cube1f\\cube1f_");
//        v16.SetFilePrefix("C:\\Users\\IMG_1\\Desktop\\DATA_IMG\\cube01\\cube01_");
//        v16.SetFilePrefix("C:\\Users\\IMG_1\\Desktop\\DATA_IMG\\cube02\\cube02_");
//        
//        v16.SetFilePrefix("C:\\Users\\IMG_1\\Desktop\\DATA_IMG\\cylinder1f\\cylinder1f_");
//        v16.SetFilePrefix("C:\\Users\\IMG_1\\Desktop\\DATA_IMG\\cylinder01\\cylinder01_");
//        v16.SetFilePrefix("C:\\Users\\IMG_1\\Desktop\\DATA_IMG\\cylinder02\\cylinder02_");
//        v16.SetFilePrefix("C:\\Users\\IMG_1\\Desktop\\DATA_IMG\\cylinder02\\cylinder02_");   
        v16.SetFilePrefix("C:\\Users\\IMG_1\\Desktop\\DATA_IMG\\Dentascan\\Dentascan01_");

        v16.SetFilePattern("%s%.3d.raw");
        v16.SetImageRange(0, 511);

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
        ////**vtkMarchingCubes**////
        vtkContourFilter  marchingCubes = new vtkContourFilter ();
        marchingCubes.SetInput(v16.GetOutput());
        marchingCubes.ComputeNormalsOn();
        marchingCubes.SetValue(0, 499);

        vtkPolyDataMapper cubesMapper = new vtkPolyDataMapper();
        cubesMapper.SetInput(marchingCubes.GetOutput());
        vtkActor cubesActor = new vtkActor();
        cubesActor.SetMapper(cubesMapper);
        cubesActor.GetProperty().SetColor(255, 0, 102);

        vtkMassProperties mass = new vtkMassProperties();
        mass.SetInput(marchingCubes.GetOutput());
        System.out.printf("%.5f", mass.GetVolume());
        ///////////////////////////

        vtkCamera aCamera = new vtkCamera();
        aCamera.SetViewUp(0, 0, -1);
        aCamera.SetPosition(0, 1, 0);
        aCamera.SetFocalPoint(0, 0, 0);
        aCamera.ComputeViewPlaneNormal();

        vtkRenderWindowPanel renderWindowPanel = new vtkRenderWindowPanel();
        renderWindowPanel.setPreferredSize(new Dimension(600, 600));
        renderWindowPanel.setInteractorStyle(new vtkInteractorStyleTrackballCamera());
        add(renderWindowPanel);
//        renderWindowPanel.GetRenderer().AddActor(contourActor);
        renderWindowPanel.GetRenderer().AddActor(cubesActor);
        renderWindowPanel.GetRenderer().SetActiveCamera(aCamera);
        renderWindowPanel.GetRenderer().ResetCamera();

//        System.out.print(marchingCubes.GetOutput());
    }

}
