/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package volume_ct;

import vtk.vtkPolyDataMapper;

/**
 *
 * @author IMG_1
 */
interface Source {
    vtkPolyDataMapper getSourceMapper();
    double getVolume();
    double getMVolume();
}
