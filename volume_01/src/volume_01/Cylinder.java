package volume_01;

import java.awt.Dimension;
import javax.swing.*;
import vtk.*;

public class Cylinder extends JPanel {

    public static vtkRenderWindowPanel renderWindowPanel;
    private static vtkCylinderSource cylinderSource;

    static {
        System.loadLibrary("vtkCommonJava");
        System.loadLibrary("vtkFilteringJava");
        System.loadLibrary("vtkIOJava");
        System.loadLibrary("vtkImagingJava");
        System.loadLibrary("vtkGraphicsJava");
        System.loadLibrary("vtkRenderingJava");
    }

    public Cylinder() {
        cylinderSource = new vtkCylinderSource();
        cylinderSource.SetCenter(0, 0, 0);
        cylinderSource.SetRadius(5);
        cylinderSource.SetHeight(10);
        cylinderSource.SetResolution(100);

        vtkPolyDataMapper mapper = new vtkPolyDataMapper();
        mapper.SetInputConnection(cylinderSource.GetOutputPort());

        vtkActor actor = new vtkActor();
        actor.SetMapper(mapper);

        renderWindowPanel = new vtkRenderWindowPanel();
        renderWindowPanel.setPreferredSize(new Dimension(800, 500));
        renderWindowPanel.setInteractorStyle(new vtkInteractorStyleTrackballCamera());
        add(renderWindowPanel);
        renderWindowPanel.GetRenderer().AddActor(actor);

    }

    public static double tosetSize(float r, float h) {
        cylinderSource.SetRadius(r);
        cylinderSource.SetHeight(h);
        renderWindowPanel.repaint();
        vtkTriangleFilter a = new vtkTriangleFilter();
        a.SetInputConnection(cylinderSource.GetOutputPort());
        a.Update();
        vtkMassProperties b = new vtkMassProperties();
        b.SetInput(a.GetOutput());
        return b.GetVolume();

    }

    public static Double tosetSize() {
        vtkTriangleFilter a = new vtkTriangleFilter();
        a.SetInputConnection(cylinderSource.GetOutputPort());
        a.Update();
        vtkMassProperties b = new vtkMassProperties();
        b.SetInput(a.GetOutput());
        return b.GetVolume();
    }

}
