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
import static volume_02.Source_01.Actor_01;
import vtk.*;

public class obj123_show2 extends JPanel {

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

    public obj123_show2() {
        v16.SetDataDimensions(600, 600);
        v16.SetFilePrefix("C:\\Users\\IMG_1\\Desktop\\QRM_025mmCTno_linepairs\\obj02\\obj02_");
        v16.SetFilePattern("%s%.3d.raw");
        v16.SetImageRange(0, 72);

        vtkImageGaussianSmooth gauss = new vtkImageGaussianSmooth();
        gauss.SetInput(v16.GetOutput());
        gauss.SetDimensionality(3);

        gauss.SetRadiusFactors(1.0, 1.0, 1.0); //1
//        gauss.SetRadiusFactors(0.5, 0.5, 0.5); //2
        gauss.Update();

        vtkImageShrink3D shrink = new vtkImageShrink3D();
        shrink.SetInput(gauss.GetOutput());
        shrink.AveragingOn();
        shrink.SetShrinkFactors(1, 1, 1);//1
//        shrink.SetShrinkFactors(2, 2, 2);//2
        shrink.Update();

        marchingCubes.SetInput(shrink.GetOutput());
        marchingCubes.SetValue(0, 1040);
        marchingCubes.Update();
        marchingCubes.ComputeScalarsOff();
        marchingCubes.ComputeNormalsOff();

        vtkPolyDataNormals nm = new vtkPolyDataNormals();
        nm.SetInput(marchingCubes.GetOutput());
        nm.SetFeatureAngle(60);
        nm.Update();

        vtkDecimatePro dec = new vtkDecimatePro();
        dec.SetInput(nm.GetOutput());

//        dec.SetTargetReduction(10/100.0); //1
        dec.SetTargetReduction(50 / 100.0); //2
        dec.Update();

        mass.SetInput(dec.GetOutput());

        System.out.printf("%.3f voxel\n", mass.GetVolume());
        System.out.printf("%.3f mm3 in 0.4 \n", mass.GetVolume() * (0.4 * 0.4 * 0.4));
        System.out.printf("%.3f mm3 in 0.25\n", mass.GetVolume() * (0.25 * 0.25 * 0.25));

        vtkPolyDataMapper Mapper = new vtkPolyDataMapper();
        Mapper.SetInput(dec.GetOutput());

        vtkActor Actor_01 = new vtkActor();
        Actor_01.SetMapper(Mapper);
        Actor_01.GetProperty().SetColor(0, 1, 0);

    }

}
