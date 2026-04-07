class Robot {
    private int width;
    private int height;
    private int pos; 
    private int perimeter;
    private boolean moved;

    public Robot(int width, int height) {
        this.width = width;
        this.height = height;
        this.perimeter = 2 * (width + height) - 4;
        this.pos = 0;
        this.moved = false;
    }
    
    public void step(int num) {
        moved = true;
        pos = (pos + num) % perimeter;
    }
    
    public int[] getPos() {
        if (pos < width) {
            return new int[]{pos, 0};
        } else if (pos < width + height - 1) {
            return new int[]{width - 1, pos - (width - 1)};
        } else if (pos < 2 * width + height - 2) {
            return new int[]{width - 1 - (pos - (width + height - 2)), height - 1};
        } else {
            return new int[]{0, height - 1 - (pos - (2 * width + height - 3))};
        }
    }
    
    public String getDir() {
        if (moved && pos == 0) return "South";
        
        if (pos > 0 && pos <= width - 1) {
            return "East";
        } else if (pos > width - 1 && pos <= width + height - 2) {
            return "North";
        } else if (pos > width + height - 2 && pos <= 2 * width + height - 3) {
            return "West";
        } else {
            return pos == 0 ? "East" : "South";
        }
    }
}