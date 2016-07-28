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

public class obj123_show extends JPanel {

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
    public static vtkImageReader2  v16 = new vtkImageReader2 ();

    public obj123_show() {
        vtkCamera aCamera = new vtkCamera();
        aCamera.SetViewUp(0, 0, -1);
        aCamera.SetPosition(0, 1, 0);
        aCamera.SetFocalPoint(0, 0, 0);
        aCamera.ComputeViewPlaneNormal();

        vtkRenderWindowPanel renderWindowPanel = new vtkRenderWindowPanel();
        renderWindowPanel.setPreferredSize(new Dimension(600, 600));
        renderWindowPanel.setInteractorStyle(new vtkInteractorStyleTrackballCamera());
        add(renderWindowPanel);
        
        for(int i = 0 ; i<=511 ; i++){
        v16.SetFilePrefix("C:\\Users\\IMG_1\\Desktop\\DATA_IMG\\512cube1f\\512cube1f_");
        v16.SetFilePattern("%s%.3d.raw");
        vtkImageActor b = new vtkImageActor();
        b.SetInput(v16.GetOutput());
        b.SetPosition(0, 0, 10*i);
        renderWindowPanel.GetRenderer().AddActor(b);
        }
        
        renderWindowPanel.GetRenderer().SetActiveCamera(aCamera);
        renderWindowPanel.GetRenderer().ResetCamera();

    }

}
