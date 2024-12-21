interface Message {
    String getContent();
}

class TextMessage implements Message {
    private String text;

    public TextMessage(String text) {
        this.text = text;
    }

    public String getContent() {
        return "Text: " + text;
    }
}

class MessageFactory {
    public static Message createMessage(String type, String content) {
        if ("TEXT".equalsIgnoreCase(type)) {
            return new TextMessage(content);
        } 
        throw new IllegalArgumentException("Unsupported message type: " + type);
    }
}
