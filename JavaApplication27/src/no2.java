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
public class no2 {
    static {
System.loadLibrary("vtkCommonJava");
System.loadLibrary("vtkCommonJava");
System.loadLibrary("vtkFilteringJava");
System.loadLibrary("vtkIOJava");
System.loadLibrary("vtkImagingJava");
System.loadLibrary("vtkGraphicsJava");
System.loadLibrary("vtkRenderingJava");
}
    public   no2(){
        double lineP0[]={0,0,0};
        double lineP1[]={2,0,0};
        
        double p0[] = {1, 0, 0};
        double p1[] = {1.0, 2.0, 0};
  
        vtkLine a = new vtkLine();
        System.out.println(a.DistanceToLine(p0, lineP0, lineP1));
        System.out.println(a.DistanceToLine(p1, lineP0, lineP1));
        
        
    }
}
