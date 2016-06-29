/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import vtk.*;
/**
 *
 * @author IMG_1
 */
public class no1 {
    static {
System.loadLibrary("vtkCommonJava");
System.loadLibrary("vtkFilteringJava");
System.loadLibrary("vtkIOJava");
System.loadLibrary("vtkImagingJava");
System.loadLibrary("vtkGraphicsJava");
System.loadLibrary("vtkRenderingJava");
}
    
    public no1(){
        vtkSuperquadricSource superquadricSource  = new vtkSuperquadricSource();
        superquadricSource.SetPhiRoundness(4);
        superquadricSource.SetThetaRoundness(4);
        vtkPlane clipPlane = new vtkPlane();
        clipPlane.SetNormal(1, -1, -1);
        clipPlane.SetOrigin(0, 0, 0);
        
        vtkClipPolyData clipper = new vtkClipPolyData();
        clipper.SetInputConnection(superquadricSource.GetOutputPort());
        clipper.SetClipFunction(clipPlane);
        
        vtkPolyDataMapper superquadricMapper = new vtkPolyDataMapper();
        superquadricMapper.SetInputConnection(clipper.GetOutputPort());
        
        vtkActor superquadricActor = new vtkActor();
        superquadricActor.SetMapper(superquadricMapper);
        
        vtkProperty backFaces = new vtkProperty();
        backFaces.SetSpecular(0.0);
        backFaces.SetDiffuse(0.0);
        backFaces.SetAmbient(1.0);
        backFaces.SetAmbientColor(1.0000, 0.9882, 0.2784);
        
        superquadricActor.SetBackfaceProperty(backFaces);
        
        vtkRenderer renderer = new vtkRenderer();
        vtkRenderWindow renderWindow = new vtkRenderWindow();
        renderWindow.SetWindowName("SolidClip");
        renderWindow.AddRenderer(renderer);
        
        vtkRenderWindowInteractor renderWindowInteractor = new vtkRenderWindowInteractor();
        renderWindowInteractor.SetRenderWindow(renderWindow);
        
        renderer.AddActor(superquadricActor);
        renderWindow.Render();
        renderWindowInteractor.Start();
    }
    
}
