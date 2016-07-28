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

public class cylinderSource extends JPanel {

    
    static {
        System.loadLibrary("vtkCommonJava");
        System.loadLibrary("vtkFilteringJava");
        System.loadLibrary("vtkIOJava");
        System.loadLibrary("vtkImagingJava");
        System.loadLibrary("vtkGraphicsJava");
        System.loadLibrary("vtkRenderingJava");
    }
   
    
    public cylinderSource() {
        
        vtkVolume16Reader v16 = new vtkVolume16Reader();
        v16.SetDataDimensions(150,150);
        v16.SetFilePrefix("C:\\Users\\IMG_1\\Desktop\\dataSourceImg\\kk\\kk");
        v16.SetFilePattern("%s%.3d.raw");
        v16.SetImageRange(0, 249);

        vtkMarchingCubes skinEx = new vtkMarchingCubes();
        skinEx.SetInput(v16.GetOutput());
        skinEx.SetValue(0, 1000);
        
        
        
        vtkPolyDataMapper skinMapper = new vtkPolyDataMapper();
        skinMapper.SetInput(skinEx.GetOutput());
        skinMapper.ScalarVisibilityOff();
        vtkActor skinActor = new vtkActor();
        skinActor.SetMapper(skinMapper);
        
        vtkOutlineFilter outline = new vtkOutlineFilter();
        outline.SetInput(v16.GetOutput());
        vtkPolyDataMapper mapper2 = new vtkPolyDataMapper();
        mapper2.SetInput(outline.GetOutput());
        
        vtkActor outlineac = new vtkActor();
        outlineac.SetMapper(mapper2);
        
        
        vtkMassProperties mp= new vtkMassProperties();
        mp.SetInput(skinEx.GetOutput());
        
        System.out.print(mp.GetVolume());
               
        vtkCamera aCamera = new vtkCamera();
        aCamera.SetViewUp(0, 0, -1);
        aCamera.SetPosition(0, 1, 0);
        aCamera.SetFocalPoint(0, 0, 0);
        aCamera.ComputeViewPlaneNormal();
        
       
        
        vtkRenderWindowPanel renderWindowPanel = new vtkRenderWindowPanel();
        renderWindowPanel.setPreferredSize(new Dimension(600, 600));
        renderWindowPanel.setInteractorStyle(new vtkInteractorStyleTrackballCamera());
        add(renderWindowPanel);
        renderWindowPanel.GetRenderer().AddActor(skinActor);
        renderWindowPanel.GetRenderer().AddActor(outlineac);
        renderWindowPanel.GetRenderer().SetActiveCamera(aCamera);
        renderWindowPanel.GetRenderer().ResetCamera();

            
        
    }
    

}


