
package volume_01;

import java.awt.Dimension;
import javax.swing.*;
import vtk.*;

public class Cone extends JPanel {

   public static vtkRenderWindowPanel renderWindowPanel;
   private static vtkConeSource coneSource; 
    
    static {
        System.loadLibrary("vtkCommonJava");
        System.loadLibrary("vtkFilteringJava");
        System.loadLibrary("vtkIOJava");
        System.loadLibrary("vtkImagingJava");
        System.loadLibrary("vtkGraphicsJava");
        System.loadLibrary("vtkRenderingJava");
    }
   
    
    public Cone() {
        coneSource = new vtkConeSource();
        coneSource.SetHeight(10);
        coneSource.SetRadius(4);
        coneSource.SetResolution(100);

        vtkPolyDataMapper mapper = new vtkPolyDataMapper();
        mapper.SetInputConnection(coneSource.GetOutputPort());

        vtkActor actor = new vtkActor();
        actor.SetMapper(mapper);

        renderWindowPanel = new vtkRenderWindowPanel();
        renderWindowPanel.setPreferredSize(new Dimension(800, 500));
        renderWindowPanel.setInteractorStyle(new vtkInteractorStyleTrackballCamera());
        add(renderWindowPanel);
        renderWindowPanel.GetRenderer().AddActor(actor);

        
    }
    
    public static double tosetSize(float h,float r){
        coneSource.SetHeight(h);
        coneSource.SetRadius(r);

        System.out.println("Cone Click");
        
        vtkTriangleFilter a = new vtkTriangleFilter();
        a.SetInputConnection(coneSource.GetOutputPort());
        a.Update();
        vtkPolyData out = new vtkPolyData();
        out = a.GetOutput();
        vtkMassProperties b = new vtkMassProperties();
        b.SetInput(out);
        System.out.print(a);
        return b.GetVolume();
        
    }
    

}

