import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Login extends JFrame implements ActionListener {

    JButton startButton, exitButton;
    JTextField nameField;
    JPanel card;

    Login() {

        // Frame setup
        setTitle("Simple Minds | Login");
        setSize(900, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);

        // Background gradient (royal blue → light lavender)
        setContentPane(new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(
                        0, 0, new Color(72, 61, 139),     // Deep Royal Blue (top)
                        0, getHeight(), new Color(123, 179, 255) // Soft Lavender Blue (bottom)
                );
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        });
        getContentPane().setLayout(null);

        // Card (glass style)
        card = new JPanel();
        card.setBounds(250, 100, 400, 330);
        card.setBackground(new Color(255, 255, 255, 230));
        card.setBorder(BorderFactory.createLineBorder(new Color(180, 200, 255), 2, true));
        card.setLayout(null);
        getContentPane().add(card);

        // Heading
        JLabel heading = new JLabel(" Simple Minds", SwingConstants.CENTER);
        heading.setBounds(0, 30, 400, 40);
        heading.setFont(new Font("Serif", Font.BOLD, 36));
        heading.setForeground(new Color(72, 61, 139)); // matches gradient tone
        card.add(heading);

        // Tagline
        JLabel tagline = new JLabel("Where knowledge meets curiosity ✨", SwingConstants.CENTER);
        tagline.setBounds(0, 75, 400, 25);
        tagline.setFont(new Font("SansSerif", Font.ITALIC, 14));
        tagline.setForeground(new Color(90, 90, 110));
        card.add(tagline);

        // Label for name
        JLabel nameLabel = new JLabel("Enter Your Name");
        nameLabel.setBounds(50, 130, 300, 20);
        nameLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        nameLabel.setForeground(new Color(60, 60, 110));
        card.add(nameLabel);

        // Text field
        nameField = new JTextField();
        nameField.setBounds(50, 160, 300, 35);
        nameField.setFont(new Font("SansSerif", Font.PLAIN, 18));
        nameField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(160, 180, 240), 2, true),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        card.add(nameField);

        // Start Button
        startButton = createStyledButton("Start Quiz →", new Color(72, 61, 139)); // Deep royal blue
        startButton.setBounds(50, 220, 130, 40);
        card.add(startButton);

        // Exit Button
        exitButton = createStyledButton("Exit", new Color(220, 70, 90)); // soft red-pink
        exitButton.setBounds(220, 220, 130, 40);
        card.add(exitButton);

        // Footer
        JLabel footer = new JLabel("© Simple Minds Quiz Application", SwingConstants.CENTER);
        footer.setBounds(0, 460, 900, 25);
        footer.setFont(new Font("SansSerif", Font.PLAIN, 14));
        footer.setForeground(new Color(235, 235, 250));
        getContentPane().add(footer);

        setVisible(true);
    }

    // Button styling
    private JButton createStyledButton(String text, Color baseColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("SansSerif", Font.BOLD, 15));
        button.setBackground(baseColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Hover effect
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                button.setBackground(baseColor.brighter());
            }

            public void mouseExited(MouseEvent e) {
                button.setBackground(baseColor);
            }
        });

        button.addActionListener(this);
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == startButton) {
            String name = nameField.getText().trim();
            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter your name!", "Alert", JOptionPane.WARNING_MESSAGE);
                return;
            }
            setVisible(false);
            new Rules(name);
        } else if (ae.getSource() == exitButton) {
            int choice = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to exit?",
                    "Exit Confirmation",
                    JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}
