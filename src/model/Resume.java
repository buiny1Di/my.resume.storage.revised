package model;

public class Resume {
    //unique identifier
    private String uuid;
    //private String fullName;

    public Resume(String uuid) {
        this.uuid = uuid;
    }

    public String getUuid() {
        return uuid;
    }

    @Override
    public String toString() {
        return uuid;
    }
}
