/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package volume_01;

import java.awt.Dimension;
import javax.swing.*;
import vtk.*;

/**
 *
 * @author IMG_1
 */
public class cube extends JPanel {
   public static vtkRenderWindowPanel renderWindowPanel;
   private static vtkCubeSource cubeSource; 
    
    static {
        System.loadLibrary("vtkCommonJava");
        System.loadLibrary("vtkFilteringJava");
        System.loadLibrary("vtkIOJava");
        System.loadLibrary("vtkImagingJava");
        System.loadLibrary("vtkGraphicsJava");
        System.loadLibrary("vtkRenderingJava");
    }
   
    
    public cube() {

        /*
         vtkPoints point = new vtkPoints();
         point.InsertNextPoint(1, 0, 0);
         point.InsertNextPoint(0, 0, 0);
         point.InsertNextPoint(0, 1, 0);
        
         vtkTriangle triangle  = new vtkTriangle();
         triangle .GetPointIds().SetId(0, 0);
         triangle .GetPointIds().SetId(1, 1);
         triangle .GetPointIds().SetId(2, 2);
        
         vtkCellArray triangles = new vtkCellArray();
         triangles.InsertNextCell(triangle);
        
         vtkPolyData trianglePolyData = new vtkPolyData();
         trianglePolyData.SetPoints(point);
         trianglePolyData.SetPolys(triangles);
         */
        cubeSource = new vtkCubeSource();
        cubeSource.SetXLength(10);
        cubeSource.SetYLength(10);
        cubeSource.SetZLength(10);
        
        vtkPolyDataMapper mapper = new vtkPolyDataMapper();
        mapper.SetInputConnection(cubeSource.GetOutputPort());

        vtkActor actor = new vtkActor();
        actor.SetMapper(mapper);
        

        /*
         vtkMassProperties Mass = new vtkMassProperties();
         Mass.SetInput(trianglePolyData);
         System.out.print(Mass);
        
         */
        
       
        
        
        
        
        
        renderWindowPanel = new vtkRenderWindowPanel();
        renderWindowPanel.setPreferredSize(new Dimension(800, 500));
        renderWindowPanel.setInteractorStyle(new vtkInteractorStyleTrackballCamera());
        add(renderWindowPanel);
        renderWindowPanel.GetRenderer().AddActor(actor);

        /*
         vtkPanel panel = new vtkPanel();
         panel.GetRenderer().AddActor(actor);
         JFrame frame = new JFrame("JAVA CONE");
         frame.getContentPane().add(panel,null);
         frame.show();
         */
        /*vtkRenderer renderer = new vtkRenderer();
         vtkRenderWindow renWin = new vtkRenderWindow();
         renWin.AddRenderer(renderer);
         vtkRenderWindowInteractor renderWindowInteractor = new vtkRenderWindowInteractor();
         renderWindowInteractor.SetRenderWindow(renWin);
         renderer.AddActor(actor);
        
        
        
        
        
         renWin.Render();
         renderWindowInteractor.Start();
        
         */
        
        
        
        
        
    }

    public static double tosetSize(float x,float y,float z) {
        System.out.println("Cube Click");
        cubeSource.SetXLength(x);
        cubeSource.SetYLength(y);
        cubeSource.SetZLength(z);
        renderWindowPanel.repaint();
        vtkTriangleFilter a = new vtkTriangleFilter();
        a.SetInputConnection(cubeSource.GetOutputPort());
        a.Update();
        vtkMassProperties b = new vtkMassProperties();
        b.SetInput(a.GetOutput());
        System.out.print(b.GetVolume());
        return b.GetVolume();
    }
    
    
    public static Double tosetSize() {
        vtkTriangleFilter a = new vtkTriangleFilter();
        a.SetInputConnection(cubeSource.GetOutputPort());
        a.Update();
        vtkMassProperties b = new vtkMassProperties();
        b.SetInput(a.GetOutput());
        return b.GetVolume();
    }
}
