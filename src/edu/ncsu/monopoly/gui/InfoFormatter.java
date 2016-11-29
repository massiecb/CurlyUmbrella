package edu.ncsu.monopoly.gui;

import edu.ncsu.monopoly.*;
import java.util.HashMap;

public class InfoFormatter {
    static HashMap cellInfoFormatters = null;
    
    static {
        if (cellInfoFormatters == null) {
            cellInfoFormatters = new HashMap();
            addFormatters();
        }
    }
    
    private static void addFormatters() {
        cellInfoFormatters.put(
            PropertyCell.class, new PropertyCellInfoFormatter());
        cellInfoFormatters.put(
            GoCell.class, new GoCellInfoFormatter());
        cellInfoFormatters.put(
            JailCell.class, new JailCellInfoFormatter());
        cellInfoFormatters.put(
            GoToJailCell.class, new GotoJailCellInfoFormatter());
        cellInfoFormatters.put(
            FreeParkingCell.class, new FreeParkingCellInfoFormatter());
        cellInfoFormatters.put(
            RailRoadCell.class, new RRCellInfoFormatter());
        cellInfoFormatters.put(
            UtilityCell.class, new UtilCellInfoFormatter());
        cellInfoFormatters.put(
            CardCell.class, new CCCellInfoFormatter());
    }

    public static String cellInfo(Cell cell) {
        CellInfoFormatter formatter =
            (CellInfoFormatter) cellInfoFormatters.get(cell.getClass());
        return formatter.format(cell);
    }

}
