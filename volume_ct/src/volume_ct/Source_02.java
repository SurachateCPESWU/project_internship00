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

public class Source_02 {

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

    public Source_02(int gray, int ngauss, int nshrink, int ndec) {

//        #### 0.4 ####
//        0-51      1070
//        0-44      1134
//        0-44      1169
//        #### 0.25 ####
//        0-72      1014
//        0-75      1099
//        0-75      1136
        marchingCubes = new vtkMarchingCubes();
        mass = new vtkMassProperties();
        vtkVolume16Reader v16 = new vtkVolume16Reader();

        v16.SetDataDimensions(400, 400);
        v16.SetFilePrefix("C:\\Users\\IMG_1\\Desktop\\QRM_04mmCTno_linepairs\\obj02\\obj02_");
        v16.SetFilePattern("%s%.3d.raw");
        v16.SetImageRange(0, 44);

        
        vtkImageGaussianSmooth gauss = new vtkImageGaussianSmooth();
        gauss.SetInput(v16.GetOutput());
        gauss.SetDimensionality(3);

        if (ngauss == 1) {
            gauss.SetRadiusFactors(1.0, 1.0, 1.0);
        } else if (ngauss == 2) {
            gauss.SetRadiusFactors(0.5, 0.5, 0.5);
        } else {
            System.out.println("Error in Gauss setting");
        }
        gauss.Update();

        
        vtkImageShrink3D shrink = new vtkImageShrink3D();
        shrink.SetInput(gauss.GetOutput());
        shrink.AveragingOn();
        if (nshrink == 1) {
            shrink.SetShrinkFactors(1, 1, 1);//1
        } else if (nshrink == 2) {
            shrink.SetShrinkFactors(2, 2, 2);//2
        } else {
            System.out.println("Error in Shrink setting");
        }
        shrink.Update();

        marchingCubes.SetInput(v16.GetOutput());
        marchingCubes.SetValue(0, gray);
        marchingCubes.Update();
        marchingCubes.ComputeScalarsOff();

        
        vtkDecimatePro dec = new vtkDecimatePro();
        dec.SetInput(marchingCubes.GetOutput());

        if (ndec == 1) {
            dec.SetTargetReduction(10 / 100.0); //1
        } else if (ndec == 2) {
            dec.SetTargetReduction(50 / 100.0); //2
        } else {
            System.out.println("Error in Decimate setting");
        }

        dec.Update();

        sourceMapper = new vtkPolyDataMapper();
        sourceMapper.SetInput(marchingCubes.GetOutput());

    }

    public static vtkPolyDataMapper getSourceMapper() {
        return sourceMapper;
    }

    public static double getVolume() {
        mass.SetInput(marchingCubes.GetOutput());
        return mass.GetVolume();
    }

}
