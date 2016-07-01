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

public class test_pick extends JPanel {

    static {
        System.loadLibrary("vtkCommonJava");
        System.loadLibrary("vtkFilteringJava");
        System.loadLibrary("vtkIOJava");
        System.loadLibrary("vtkImagingJava");
        System.loadLibrary("vtkGraphicsJava");
        System.loadLibrary("vtkRenderingJava");
    }

    public test_pick() {
        
        vtkImageReader read = new vtkImageReader();
        read.SetFileName("C:\\Users\\IMG_1\\Desktop\\DATA_IMG\\Dentascan\\Dentascan01_000.raw");
        read.Update();
        vtkImageActor ia = new vtkImageActor ();
        ia.SetInput(read.GetOutput());
        
        vtkCamera aCamera = new vtkCamera();
        aCamera.ComputeViewPlaneNormal();

        vtkRenderWindowPanel renderWindowPanel = new vtkRenderWindowPanel();
        renderWindowPanel.setPreferredSize(new Dimension(600, 600));
        renderWindowPanel.setInteractorStyle(new vtkInteractorStyleTrackballCamera());
        renderWindowPanel.GetRenderer().AddActor(ia);
        renderWindowPanel.GetRenderer().SetActiveCamera(aCamera);
        renderWindowPanel.GetRenderer().ResetCamera();
        add(renderWindowPanel);

        
        
    }
}
