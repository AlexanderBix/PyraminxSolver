package Layout;

public class Side {
    // Creating Sides
    public String[][] createSide(String color) {
        String[] row1;
        String[] row2;
        String[] row3;
        String[] row4;
        String[][] rowIndex;
        switch (color) {
            case "red":
                row1 = new String[]{"r"};
                row2 = new String[]{"r","r","r"};
                row3 = new String[]{"r","r","r","r","r"};
                row4 = new String[]{"r","r","r","r","r","r","r"};
                rowIndex = new String[][]{row1, row2, row3, row4};
                return rowIndex;
            case "green":
                row1 = new String[]{"g"};
                row2 = new String[]{"g","g","g"};
                row3 = new String[]{"g","g","g","g","g"};
                row4 = new String[]{"g","g","g","g","g","g","g"};
                rowIndex = new String[][]{row1, row2, row3, row4};
                return rowIndex;
            case "blue":
                row1 = new String[]{"b"};
                row2 = new String[]{"b","b","b"};
                row3 = new String[]{"b","b","b","b","b"};
                row4 = new String[]{"b","b","b","b","b","b","b"};
                rowIndex = new String[][]{row1, row2, row3, row4};
                return rowIndex;
            case "yellow":
                row1 = new String[]{"y"};
                row2 = new String[]{"y","y","y"};
                row3 = new String[]{"y","y","y","y","y"};
                row4 = new String[]{"y","y","y","y","y","y","y"};
                rowIndex = new String[][]{row1, row2, row3, row4};
                return rowIndex;
            default:
                break;
        }
        return null;
    }
}
