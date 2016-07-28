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

public class Source_03 implements Source {

    static {
        System.loadLibrary("vtkCommonJava");
        System.loadLibrary("vtkFilteringJava");
        System.loadLibrary("vtkIOJava");
        System.loadLibrary("vtkImagingJava");
        System.loadLibrary("vtkGraphicsJava");
        System.loadLibrary("vtkRenderingJava");
    }

    private static vtkPolyDataMapper sourceMapper;
    private static vtkMassProperties mass;
    private static vtkMarchingCubes marchingCubes;
    private static final double MVOLUME = 10080;

    public Source_03(int gray) {
        marchingCubes = new vtkMarchingCubes();
        mass = new vtkMassProperties();
        vtkVolume16Reader v16 = new vtkVolume16Reader();

        v16.SetDataDimensions(400, 400);
        v16.SetFilePrefix("C:\\Users\\IMG_1\\Desktop\\QRM_04mmCTno_linepairs\\obj03\\obj03_");
        v16.SetFilePattern("%s%.3d.raw");
        v16.SetImageRange(0, 44);

        
        marchingCubes.SetInput(v16.GetOutput());
        marchingCubes.SetValue(0, gray);
        marchingCubes.Update();
        marchingCubes.ComputeScalarsOff();

        sourceMapper = new vtkPolyDataMapper();
        sourceMapper.SetInput(marchingCubes.GetOutput());

    }

    public vtkPolyDataMapper getSourceMapper() {
        return sourceMapper;
    }

    public double getVolume() {
        mass.SetInput(marchingCubes.GetOutput());
        return mass.GetVolume() * (0.4 * 0.4 * 0.4);
    }

    public double getMVolume() {
        return MVOLUME;
    }
}
