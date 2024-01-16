public class Colors extends GameItems{
    private String colorName;

    private int point;
    public Colors(String colorName,int point) {
        this.colorName=colorName;
        this.point=point;
    }

    public String getColorName() {
        return colorName;
    }


    public int getPoint() {
        return point;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }


    public void setPoint(int point) {
        this.point = point;
    }
}
