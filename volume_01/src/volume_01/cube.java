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
public class Cube extends JPanel {

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

    public Cube() {

        cubeSource = new vtkCubeSource();
        cubeSource.SetXLength(10);
        cubeSource.SetYLength(10);
        cubeSource.SetZLength(10);

        
        vtkPolyDataMapper mapper = new vtkPolyDataMapper();
        mapper.SetInputConnection(cubeSource.GetOutputPort());

        vtkActor actor = new vtkActor();
        actor.SetMapper(mapper);

        renderWindowPanel = new vtkRenderWindowPanel();
        renderWindowPanel.setPreferredSize(new Dimension(800, 500));
        renderWindowPanel.setInteractorStyle(new vtkInteractorStyleTrackballCamera());
        add(renderWindowPanel);
        renderWindowPanel.GetRenderer().AddActor(actor);

    }

    public static double tosetSize(float x, float y, float z) {
        cubeSource.SetXLength(x);
        cubeSource.SetYLength(y);
        cubeSource.SetZLength(z);
        renderWindowPanel.repaint();
        vtkTriangleFilter a = new vtkTriangleFilter();
        a.SetInputConnection(cubeSource.GetOutputPort());
        a.Update();
        vtkMassProperties b = new vtkMassProperties();
        b.SetInput(a.GetOutput());
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
