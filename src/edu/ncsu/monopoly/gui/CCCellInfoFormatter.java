package edu.ncsu.monopoly.gui;

import edu.ncsu.monopoly.Cell;

public class CCCellInfoFormatter implements CellInfoFormatter {
    @Override
    public String format(Cell cell) {
        return "<html><font color='black'><b>" + cell.getName() + "</b></font></html>";
    }
}
