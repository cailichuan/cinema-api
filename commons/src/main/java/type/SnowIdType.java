package type;

public enum SnowIdType {
    Activity(1L,1L),
    Admin(1L,2L),
    Arrangement(1L,3L),
    CART(1L,4L),
    DAILYWORK(1L,5L),
    FILM(1L,6L),
    FILMEVALUATE(1L,7L),
    LEAVINGMESSAGE(1L,8L),
    ORDER(1L,9L),
    ORDEREXCEPTION(1L,10L),
    POSTER(1L,11L),
    RESGISTRATION(1L,12L),
    ROLE(1L,13L),
    UPLOAD(1L,14L),
    USER(1L,15L),
    WORKER(1L,16L),
    WORKEREVALUATE(1L,17L);




    private Long machineId;
    private Long workId;

    private SnowIdType(Long machineId, Long workId){
        this.machineId=machineId;
        this.workId=workId;

    }

    public Long getMachineId() {
        return machineId;
    }

    public Long getWorkId(){
        return workId;
    }

    public static SnowIdType getSnowIdTyteByNum(int w){
        for (SnowIdType value : SnowIdType.values()) {
            if (value.getWorkId()==w){
                return value;
            }
        }

        return null;
    }
}
