/*
 * MatrixTableViewModelEditableRhoSK.java
 *
 * Copyright 2006-2017 James F. Bowring and www.Earth-Time.org
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.earthtime.matrices.matrixViews;

import org.earthtime.matrices.matrixModels.AbstractMatrixModel;
import org.earthtime.ratioDataViews.DataEntryDetectorInterface;

/**
 *
 * @author James F. Bowring
 */
public class MatrixTableViewModelEditableRhoSK extends AbstractMatrixTableViewModel {

    private final DataEntryDetectorInterface dataEntryDetector;

    /**
     *
     *
     * @param matrixModel
     * @param dataEntryDetector the value of dataEntryDetector
     */
    public MatrixTableViewModelEditableRhoSK(AbstractMatrixModel matrixModel, DataEntryDetectorInterface dataEntryDetector) {
        super(matrixModel);
        this.dataEntryDetector = dataEntryDetector;       
    }

    /**
     *
     * @param row
     * @param col
     * @return
     */
    @Override
    public boolean isCellEditable(int row, int col) {
        //Note that the data/cell address is constant,
        //no matter where the cell appears on screen.
        // Special Case for StaceyKramer parameters to allow edit of top left cell of rho
        return ((col == 2)) && (row == 0);
    }

    
    /**
     *
     * @param value
     * @param row
     * @param column
     */
    @Override
    public void setValueAt(Object value, int row, int column) {
        // matrix itself does not have row labels so column value is offset by 1

        try {
            double myDouble = Double.valueOf((String) value);
            // this setvalue performs a check for correlations between -1 and 1
            // we know SK has three ratios and all the entries will be the same except diagonal
            
            matrixModel.setValueAt(row, column - 1, myDouble);
            matrixModel.setValueAt(row, column, myDouble);
            matrixModel.setValueAt(row + 1, column, myDouble);
            dataEntryDetector.dataEntryDetected();

        } catch (NumberFormatException numberFormatException) {
        }
    }
}
