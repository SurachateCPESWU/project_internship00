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

    public static double tosetSize(float r, float h) {
        coneSource.SetHeight(h);
        coneSource.SetRadius(r);
        renderWindowPanel.repaint();
        vtkTriangleFilter a = new vtkTriangleFilter();
        a.SetInputConnection(coneSource.GetOutputPort());
        a.Update();
        vtkMassProperties b = new vtkMassProperties();
        b.SetInput(a.GetOutput());
        System.out.print(a);
        return b.GetVolume();
    }

    public static Double tosetSize() {
        vtkTriangleFilter a = new vtkTriangleFilter();
        a.SetInputConnection(coneSource.GetOutputPort());
        a.Update();
        vtkMassProperties b = new vtkMassProperties();
        b.SetInput(a.GetOutput());
        return b.GetVolume();
    }

}
