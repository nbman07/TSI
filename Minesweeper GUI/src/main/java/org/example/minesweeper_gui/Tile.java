package org.example.minesweeper_gui;

public class Tile {
    private String status;
    private Boolean hasMine;
    private Boolean beenFlagged;
    private Boolean visible = true;

    public Tile(Boolean hasMine) {
        this.visible = false;
        this.hasMine = hasMine;
        this.beenFlagged = false;
    }

    public String getStatus() { //status can be (U)nopened/(F)lagged/Number
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getHasMine() {
        return hasMine;
    }

    public void setHasMine(Boolean hasMine) {
        this.hasMine = hasMine;
    }

    public boolean getBeenFlagged() {
        return beenFlagged;
    }
    public void setBeenFlagged(Boolean beenFlagged) {
        this.beenFlagged = beenFlagged;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean _visible) {
        this.visible = _visible;
    }

}
