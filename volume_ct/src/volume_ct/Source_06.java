/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package volume_ct;

/**
 *
 * @author IMG_1
 */
import java.awt.Dimension;
import javax.swing.*;
import vtk.*;

public class Source_06 {

    static {
        System.loadLibrary("vtkCommonJava");
        System.loadLibrary("vtkFilteringJava");
        System.loadLibrary("vtkIOJava");
        System.loadLibrary("vtkImagingJava");
        System.loadLibrary("vtkGraphicsJava");
        System.loadLibrary("vtkRenderingJava");
    }

    public static vtkActor Actor_06;

    public Source_06() {
        vtkMarchingCubes marchingCubes = new vtkMarchingCubes();
        vtkMassProperties mass = new vtkMassProperties();
        vtkVolume16Reader v16 = new vtkVolume16Reader();

        v16.SetDataDimensions(512, 512);
        v16.SetFilePrefix("C:\\Users\\IMG_1\\Desktop\\DATA_IMG\\512cylinder01\\512cylinder01_");
        v16.SetFilePattern("%s%.3d.raw");
        v16.SetImageRange(0, 511);

      
        marchingCubes.SetInput(v16.GetOutput());
        marchingCubes.SetValue(0, 499);
        marchingCubes.Update();
        marchingCubes.ComputeScalarsOff();
        marchingCubes.ComputeNormalsOff();


        mass.SetInput(marchingCubes.GetOutput());

        vtkPolyDataMapper Mapper = new vtkPolyDataMapper();
        Mapper.SetInput(marchingCubes.GetOutput());
        Actor_06 = new vtkActor();
        Actor_06.SetMapper(Mapper);
        Actor_06.GetProperty().SetColor(0, 1, 0);

    }

}
