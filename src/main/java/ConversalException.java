public class ConversalException extends RuntimeException {
    public ConversalException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "Hmm, something seems wrong... Ah! " + getMessage();
    }
}
