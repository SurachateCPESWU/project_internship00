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

public class test_img00 extends JPanel {

    static {
        System.loadLibrary("vtkCommonJava");
        System.loadLibrary("vtkFilteringJava");
        System.loadLibrary("vtkIOJava");
        System.loadLibrary("vtkImagingJava");
        System.loadLibrary("vtkGraphicsJava");
        System.loadLibrary("vtkRenderingJava");
    }

    public test_img00() {

        vtkDICOMImageReader  v16 = new vtkDICOMImageReader ();
        v16.SetDirectoryName("C:\\Users\\IMG_1\\Desktop\\QRM_04mmCTno_linepairs\\dicom");
        v16.Update();
//        v16.SetDirectoryName("C:\\Users\\IMG_1\\Desktop\\QRM_025mmCTno_linepairs\\dicom");
        


        vtkMarchingCubes marchingCubes = new vtkMarchingCubes();
        marchingCubes.SetInputConnection(v16.GetOutputPort());
        marchingCubes.SetValue(0, 1380);
        marchingCubes.Update();
        marchingCubes.ComputeScalarsOff();

        vtkPolyDataMapper contourMapper = new vtkPolyDataMapper();
        contourMapper.SetInput(marchingCubes.GetOutput());
        
        vtkActor marchingCubesActor = new vtkActor();
        marchingCubesActor.GetProperty().SetColor(1, 0.5, 0.5);
        marchingCubesActor.SetMapper(contourMapper);
//        
//        //////////////////////////////////
//        ////**vtkMarchingCubes**////
//        vtkContourFilter  marchingCubes = new vtkContourFilter ();
//        marchingCubes.SetInput(v16.GetOutput());
//        marchingCubes.ComputeNormalsOn();
//        marchingCubes.SetValue(0, 499);
//
//        vtkPolyDataMapper cubesMapper = new vtkPolyDataMapper();
//        cubesMapper.SetInput(marchingCubes.GetOutput());
//        vtkActor cubesActor = new vtkActor();
//        cubesActor.SetMapper(cubesMapper);
//        cubesActor.GetProperty().SetColor(255, 0, 102);
//
//        vtkMassProperties mass = new vtkMassProperties();
//        mass.SetInput(marchingCubes.GetOutput());
//        System.out.printf("%.5f", mass.GetVolume());
//        ///////////////////////////

        vtkCamera aCamera = new vtkCamera();
        aCamera.SetViewUp(0, 0, -1);
        aCamera.SetPosition(0, 1, 0);
        aCamera.SetFocalPoint(0, 0, 0);
        aCamera.ComputeViewPlaneNormal();

        vtkRenderWindowPanel renderWindowPanel = new vtkRenderWindowPanel();
        renderWindowPanel.setPreferredSize(new Dimension(600, 600));
        renderWindowPanel.setInteractorStyle(new vtkInteractorStyleTrackballCamera());
        add(renderWindowPanel);
        renderWindowPanel.GetRenderer().AddActor(marchingCubesActor);
        renderWindowPanel.GetRenderer().SetActiveCamera(aCamera);
        renderWindowPanel.GetRenderer().ResetCamera();

//        System.out.print(marchingCubes.GetOutput());
    }

}
