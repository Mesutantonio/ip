package conversal.task;

public enum TaskType {
    TODO("[T]"),
    DEADLINE("[D]"),
    EVENT("[E]");

    // Fields
    private final String symbol;

    // Constructor
    TaskType(String symbol) {
        this.symbol = symbol;
    }

    // Getter
    public String getSymbol() {
        return symbol;
    }
}