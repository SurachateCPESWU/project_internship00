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

public class dataSource extends JPanel {

    
    static {
        System.loadLibrary("vtkCommonJava");
        System.loadLibrary("vtkFilteringJava");
        System.loadLibrary("vtkIOJava");
        System.loadLibrary("vtkImagingJava");
        System.loadLibrary("vtkGraphicsJava");
        System.loadLibrary("vtkRenderingJava");
    }
   
    
    public dataSource() {
        
        vtkVolume16Reader v16 = new vtkVolume16Reader();
        v16.SetDataDimensions(150,150);
        v16.SetFilePrefix("C:\\Users\\IMG_1\\Desktop\\dataSourceImg\\kk\\kk");
        v16.SetFilePattern("%s%.3d.raw");
        v16.SetImageRange(0, 249);

        
        
        
//        vtkImageReader2 v16 = new vtkImageReader2();
//        v16.SetDataExtent(0, 63, 0, 63, 0, 92);
//        v16.SetFileNameSliceOffset(1);
//        v16.SetDataScalarTypeToUnsignedShort();
//        v16.SetDataByteOrderToLittleEndian();
//        v16.SetFilePrefix("C:/Users/IMG_1/Desktop/VTKData-master/Data/headsq/quarter");
//        v16.SetDataSpacing(3.2,3.2,1.5);
                
                
        vtkMarchingCubes skinEx = new vtkMarchingCubes();
        skinEx.SetInput(v16.GetOutput());
        skinEx.SetValue(1000, 1000);
        
        
        
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
        
        System.out.println(mp.GetVolume());
           
        System.out.println(skinEx.GetOutput());
        
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
        
        
        /*vtkMarchingCubes bone = new vtkMarchingCubes();
        bone.SetInput(v16.GetOutput());
        bone.SetValue(0, 1150);
        
        vtkStripper boneSr = new vtkStripper();
        boneSr.SetInput(bone.GetOutput());
        
        vtkPolyDataMapper bonemap = new vtkPolyDataMapper();
        bonemap.SetInput(boneSr.GetOutput());
        bonemap.ScalarVisibilityOff();
        vtkActor bone_ac = new vtkActor();
        bone_ac.SetMapper(bonemap);
        
        */
        
        /* 
        double isoValue = 0.5;
        
        vtkPoints point = new vtkPoints();
        double p0[] = {1,1,1};
        double p1[] = {-1,1,1};
        double p2[] = {-1,-1,1};
        double p3[] = {1,-1,1};
        double p4[] = {0,0,0};
        
        point.InsertNextPoint(p0);
        point.InsertNextPoint(p1);
        point.InsertNextPoint(p2);
        point.InsertNextPoint(p3);
        point.InsertNextPoint(p4);
        
        vtkPyramid pyramid = new vtkPyramid();
        pyramid.GetPointIds().SetId(0, 0);
        pyramid.GetPointIds().SetId(1, 1);
        pyramid.GetPointIds().SetId(2, 2);
        pyramid.GetPointIds().SetId(3, 3);
        pyramid.GetPointIds().SetId(4, 4);
        
        vtkCellArray cell = new vtkCellArray();
        cell.InsertNextCell(pyramid);
        
        vtkUnstructuredGrid ug = new vtkUnstructuredGrid();
        ug.SetPoints(point);
        ug.InsertNextCell(pyramid.GetCellType(),pyramid.GetPointIds());
        
       
        vtkDataSetMapper mapper = new vtkDataSetMapper();
        mapper.SetInput(ug);
        
        vtkActor actor = new vtkActor();
        actor.SetMapper(mapper);
        
        vtkRenderWindowPanel renderWindowPanel = new vtkRenderWindowPanel();
        renderWindowPanel.setPreferredSize(new Dimension(300, 300));
        renderWindowPanel.setInteractorStyle(new vtkInteractorStyleTrackballCamera());
        add(renderWindowPanel);
        renderWindowPanel.GetRenderer().AddActor(actor);
        
        
        */
        
        /*
        vtkRenderer ren =new vtkRenderer();
        vtkRenderWindow renWin = new vtkRenderWindow();
        renWin.AddRenderer(ren);
        vtkRenderWindowInteractor renInt = new vtkRenderWindowInteractor();
        renInt.SetRenderWindow(renWin);
        vtkImageReader2 reader = new vtkImageReader2();
        reader.SetDataExtent(0,63,0,63,0,92);
        reader.SetFileNameSliceOffset(1);
        reader.SetDataScalarTypeToUnsignedShort();
        reader.SetDataByteOrderToLittleEndian();
        reader.SetFilePrefix("C:/Users/IMG_1/Desktop/VTKData-master/Data/headsq/quarter");
        reader.SetDataSpacing(3.2,3.2,1.5);
        */
        
            
        
    }
    

}


