package userinterface;

public class TextField extends javafx.scene.control.TextField {
    private final int x;
    private final int y;

    public TextField(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    //IT MIGHT BE UNNECESSARY TO OVERRIDE THESE METHODS
    @Override
    public void replaceText(int i, int j, String s) {
        if (s.matches("[0-9]")==false) {
            super.replaceText(i,j,s);
        }
    }

    @Override
    public void replaceSelection(String s) {
        if (s.matches("[0-9]")==false) {
            super.replaceSelection(s);
        }
    }
}
