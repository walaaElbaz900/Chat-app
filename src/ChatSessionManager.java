import java.util.ArrayList;
import java.util.List;

public class ChatSessionManager {
    private static ChatSessionManager instance;
    private List<String> chatHistory;

    private ChatSessionManager() {
        chatHistory = new ArrayList<>();
    }

    public static ChatSessionManager getInstance() {
        if (instance == null) {
            instance = new ChatSessionManager();
        }
        return instance;
    }

    public void addMessage(String user, String message) {
        chatHistory.add(user + ": " + message);
    }

    public List<String> getChatHistory() {
        return chatHistory;
    }
}
