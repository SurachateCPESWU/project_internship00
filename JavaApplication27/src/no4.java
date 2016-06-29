/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author IMG_1
 */

import vtk.*;

public class no4 {
    
    static {
System.loadLibrary("vtkCommonJava");
System.loadLibrary("vtkCommonJava");
System.loadLibrary("vtkFilteringJava");
System.loadLibrary("vtkIOJava");
System.loadLibrary("vtkImagingJava");
System.loadLibrary("vtkGraphicsJava");
System.loadLibrary("vtkRenderingJava");
}
    
    public no4(){
        vtkPointSource pointSource = new vtkPointSource();
        pointSource.SetNumberOfPoints(50);
        pointSource.Update();
        
        System.out.print("Have " + pointSource.GetOutput().GetNumberOfPoints() + "Point");
        
    
        
    }
}
