package dev.changuii.project.enums;

public enum FriendshipStatus {

    REQUESTING("친구요청중"),
    FRIEND("친구"),
    REFUSAL("친구신청거부");

    private String description;

    private FriendshipStatus(String description){
        this.description=description;
    }

}
