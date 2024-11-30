package willow.train.kuayue.systems.tech_tree;

import lombok.Getter;

public enum NetworkState {

    STANDING_BY(0),
    BUSY(-1),
    USE_ABLE(1);

    @Getter
    private final int code;

    private NetworkState(int code) {
        this.code = code;
    }

    public static NetworkState fromCode(int code) {
        return switch (code) {
            case 0 -> STANDING_BY;
            case 1 -> USE_ABLE;
            default -> BUSY;
        };
    }
}
