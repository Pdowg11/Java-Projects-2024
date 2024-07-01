package picross;

public enum Tile {
    EMPTY,
    CROSS,
    FILL,
    TRY_CROSS,
    TRY_FILL,
    SEL_EMPTY,
    SEL_CROSS,
    SEL_FILL,
    SEL_TRY_CROSS,
    SEL_TRY_FILL;

    public Tile toSelection() {
        return switch (this) {
            case FILL -> SEL_FILL;
            case EMPTY -> SEL_EMPTY;
            case TRY_FILL -> SEL_TRY_FILL;
            case TRY_CROSS -> SEL_TRY_CROSS;
            case CROSS -> SEL_CROSS;
            default -> this;
        };
    }

    public Tile toReal() {
        return switch (this) {
            case TRY_FILL -> FILL;
            case TRY_CROSS -> CROSS;
            default -> this;
        };
    }
    public Tile toTest() {
        return switch (this) {
            case FILL -> TRY_FILL;
            case CROSS -> TRY_CROSS;
            default -> this;
        };
    }

    public boolean isTest() {
        return this == TRY_FILL || this == TRY_CROSS;
    }
    public boolean isReal() {
        return this == FILL || this == CROSS;
    }

    public boolean canOverride(Tile replacement, boolean testMode, Tile base) {
        if ((testMode && this.isReal()) || this == replacement) {
            return false;
        } else {
            if (replacement == EMPTY) {
                return (!testMode && this.isReal()) || this.isTest();
            } else {
                return this == base || this == EMPTY;
            }
        }
    }
}
