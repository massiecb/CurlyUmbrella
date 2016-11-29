package edu.ncsu.monopoly;

public class GoCell extends Cell {
    public GoCell() {
        super.setName("Go");
        setAvailable(false);
    }

    @Override
    public void playAction() {
    }

    @Override
    void setName(String name) {
    }
}
