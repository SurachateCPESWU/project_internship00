package volume_01;

import java.awt.Dimension;
import javax.swing.*;
import vtk.*;

public class Sphere extends JPanel {

    public static vtkRenderWindowPanel renderWindowPanel;
    private static vtkSphereSource sphereSource;

    static {
        System.loadLibrary("vtkCommonJava");
        System.loadLibrary("vtkFilteringJava");
        System.loadLibrary("vtkIOJava");
        System.loadLibrary("vtkImagingJava");
        System.loadLibrary("vtkGraphicsJava");
        System.loadLibrary("vtkRenderingJava");
    }

    public Sphere() {
        sphereSource = new vtkSphereSource();
        sphereSource.SetThetaResolution(100);
        sphereSource.SetPhiResolution(100);
        sphereSource.SetRadius(10);

        vtkPolyDataMapper mapper = new vtkPolyDataMapper();
        mapper.SetInputConnection(sphereSource.GetOutputPort());

        vtkActor actor = new vtkActor();
        actor.SetMapper(mapper);

        renderWindowPanel = new vtkRenderWindowPanel();
        renderWindowPanel.setPreferredSize(new Dimension(800, 500));
        renderWindowPanel.setInteractorStyle(new vtkInteractorStyleTrackballCamera());
        add(renderWindowPanel);
        renderWindowPanel.GetRenderer().AddActor(actor);

    }

    public static double tosetSize(float r) {
        sphereSource.SetRadius(r);
        renderWindowPanel.repaint();
        vtkTriangleFilter a = new vtkTriangleFilter();
        a.SetInputConnection(sphereSource.GetOutputPort());
        a.Update();
        vtkMassProperties b = new vtkMassProperties();
        b.SetInput(a.GetOutput());
        return b.GetVolume();
    }

    public static Double tosetSize() {
        vtkTriangleFilter a = new vtkTriangleFilter();
        a.SetInputConnection(sphereSource.GetOutputPort());
        a.Update();
        vtkMassProperties b = new vtkMassProperties();
        b.SetInput(a.GetOutput());
        return b.GetVolume();
    }

}
