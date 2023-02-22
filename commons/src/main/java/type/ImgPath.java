package type;

public enum ImgPath {
    DEFAULT("/default")
    ;



    private final String MAIN_PATH="/classes/static/images";
    private String secondaryPath;

    private ImgPath(String s){
        this.secondaryPath = s;
    }

    public String getPath(){
        return MAIN_PATH+secondaryPath;
    }
}
