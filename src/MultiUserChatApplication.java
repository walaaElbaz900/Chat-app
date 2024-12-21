import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;

public class MultiUserChatApplication {
    private static JFrame frame;
    private static JTextArea chatArea;
    private static JTextField messageField;
    private static JComboBox<String> userDropdown;
    private static String currentUser;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MultiUserChatApplication::showLoginScreen);
    }

    private static void showLoginScreen() {
        frame = new JFrame("Chat Application - Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);

        JPanel panel = new JPanel(new GridLayout(3, 2));
        JTextField usernameField = new JTextField();
        JButton loginButton = new JButton("Login");

        loginButton.addActionListener(e -> {
            currentUser = usernameField.getText().trim();
            if (!currentUser.isEmpty()) {
                showChatWindow();
            } else {
                JOptionPane.showMessageDialog(frame, "Username cannot be empty!");
            }
        });

        panel.add(new JLabel("Enter Username:"));
        panel.add(usernameField);
        panel.add(loginButton);

        frame.add(panel);
        frame.setVisible(true);
    }

    private static void showChatWindow() {
        ChatSessionManager sessionManager = ChatSessionManager.getInstance();

        frame.getContentPane().removeAll();
        frame.setTitle("Chat Application - User: " + currentUser);

        JPanel mainPanel = new JPanel(new BorderLayout());
        chatArea = new JTextArea();
        chatArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(chatArea);

        JPanel inputPanel = new JPanel(new BorderLayout());
        messageField = new JTextField();
        JButton sendButton = new JButton("Send");

        // Dropdown for user selection
        userDropdown = new JComboBox<>(new String[]{"User1", "User2", "User3"});
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(new JLabel("Simulate As:"), BorderLayout.WEST);
        topPanel.add(userDropdown, BorderLayout.CENTER);

        // Send button action
        sendButton.addActionListener((ActionEvent e) -> {
            String messageContent = messageField.getText().trim();
            String sender = (String) userDropdown.getSelectedItem();
            if (!messageContent.isEmpty()) {
                sessionManager.addMessage(sender, messageContent);
                updateChatArea(sessionManager);
                messageField.setText("");
            }
        });

        inputPanel.add(messageField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);

        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(inputPanel, BorderLayout.SOUTH);
        mainPanel.add(topPanel, BorderLayout.NORTH);

        frame.add(mainPanel);
        frame.setSize(500, 400);
        frame.setVisible(true);
    }

    private static void updateChatArea(ChatSessionManager sessionManager) {
        chatArea.setText(String.join("\n", sessionManager.getChatHistory()));
    }
}
