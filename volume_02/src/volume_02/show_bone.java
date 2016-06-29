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

public class show_bone extends JPanel {

    static {
        System.loadLibrary("vtkCommonJava");
        System.loadLibrary("vtkFilteringJava");
        System.loadLibrary("vtkIOJava");
        System.loadLibrary("vtkImagingJava");
        System.loadLibrary("vtkGraphicsJava");
        System.loadLibrary("vtkRenderingJava");
    }

    public show_bone() {
        vtkVolume16Reader v16 = new vtkVolume16Reader();
        v16.SetDataDimensions(512, 512);
        v16.SetFilePrefix("C:\\Users\\IMG_1\\Desktop\\DATA_IMG\\Dentascan\\Dentascan01_");
        v16.SwapBytesOn();
        v16.SetFilePattern("%s%.3d.raw");
        v16.SetImageRange(0, 165);
        
      
        ////**vtkMarchingContourFilter**///
        vtkMarchingCubes contourFilter = new vtkMarchingCubes();
        contourFilter.SetInput(v16.GetOutput());
        contourFilter.SetValue(0, 1000);
        contourFilter.Update();

        vtkPolyDataMapper contourMapper = new vtkPolyDataMapper();
        contourMapper.SetInput(contourFilter.GetOutput());
        vtkActor contourActor = new vtkActor();
        contourActor.SetMapper(contourMapper);

        vtkMassProperties mass = new vtkMassProperties();
        mass.SetInput(contourFilter.GetOutput());
        System.out.print(mass.GetVolume());

        vtkImageActor ia = new vtkImageActor ();
        
        
        vtkImageReader vv = new vtkImageReader();
//        vv.SetFilePrefix("C:\\Users\\IMG_1\\Desktop\\DATA_IMG\\Dentascan\\Dentascan01_");
        vv.SetFilePattern("C:\\Users\\IMG_1\\Desktop\\DATA_IMG\\Dentascan\\Dentascan01_000.raw");
        
        ia.SetInput(vv.GetOutput());

        
        
        
        //////////////////////////////////
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
