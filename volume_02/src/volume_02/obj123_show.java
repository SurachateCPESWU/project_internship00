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

public class obj123_show extends JPanel {

    static {
        System.loadLibrary("vtkCommonJava");
        System.loadLibrary("vtkFilteringJava");
        System.loadLibrary("vtkIOJava");
        System.loadLibrary("vtkImagingJava");
        System.loadLibrary("vtkGraphicsJava");
        System.loadLibrary("vtkRenderingJava");
    }

    public static vtkMarchingCubes marchingCubes = new vtkMarchingCubes();
    public static vtkMassProperties mass = new vtkMassProperties();
    public static vtkVolume16Reader v16 = new vtkVolume16Reader();

    public obj123_show() {
        v16.SetDataDimensions(600, 600);
        v16.SetFilePrefix("C:\\Users\\IMG_1\\Desktop\\QRM_025mmCTno_linepairs\\obj03\\obj03_");
        v16.SetFilePattern("%s%.3d.raw");
        v16.SetImageRange(0, 72);

        marchingCubes.SetInput(v16.GetOutput());
        marchingCubes.SetValue(0, 1169);
        marchingCubes.Update();
        marchingCubes.ComputeScalarsOff();
//        marchingCubes.ComputeNormalsOff();

        mass.SetInput(marchingCubes.GetOutput());
        System.out.printf("%.3f\n", mass.GetVolume());

        vtkPolyDataMapper Mapper = new vtkPolyDataMapper();
        Mapper.SetInput(marchingCubes.GetOutput());
        vtkActor Actor = new vtkActor();
        Actor.SetMapper(Mapper);
        Actor.GetProperty().SetColor(0, 1, 0);

        vtkCamera aCamera = new vtkCamera();
        aCamera.SetViewUp(0, 0, -1);
        aCamera.SetPosition(0, 1, 0);
        aCamera.SetFocalPoint(0, 0, 0);
        aCamera.ComputeViewPlaneNormal();

        vtkRenderWindowPanel renderWindowPanel = new vtkRenderWindowPanel();
        renderWindowPanel.setPreferredSize(new Dimension(600, 600));
        renderWindowPanel.setInteractorStyle(new vtkInteractorStyleTrackballCamera());
        add(renderWindowPanel);
        renderWindowPanel.GetRenderer().AddActor(Actor);
        renderWindowPanel.GetRenderer().SetActiveCamera(aCamera);
        renderWindowPanel.GetRenderer().ResetCamera();


    }

}
