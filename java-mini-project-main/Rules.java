import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Rules extends JFrame implements ActionListener {

    String name;
    JButton start, back;

    Rules(String name) {
        this.name = name;

        // ---- FRAME SETTINGS ----
        setTitle("Quiz Rules");
        setSize(800, 650);
        setLocation(350, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // ---- GRADIENT BACKGROUND ----
        JPanel background = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(0, 0,
                        new Color(72, 61, 139), // Dark purple
                        0, getHeight(),
                        new Color(123, 179, 255)); // Soft blue
                g2.setPaint(gp);
                g2.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        background.setLayout(new GridBagLayout());
        add(background, BorderLayout.CENTER);

        // ---- MAIN WHITE CARD ----
        JPanel card = new JPanel(new BorderLayout(20, 20));
        card.setPreferredSize(new Dimension(650, 500));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));

        // ---- HEADING ----
        JLabel heading = new JLabel("Welcome, " + name + " 👋", SwingConstants.CENTER);
        heading.setFont(new Font("Montserrat", Font.BOLD, 30));
        heading.setForeground(new Color(72, 61, 139));
        card.add(heading, BorderLayout.NORTH);

        // ---- RULES AREA ----
        JTextPane rules = new JTextPane();
        rules.setContentType("text/html");
        rules.setText(
                "<html><body style='font-family: \"Segoe UI\", sans-serif; font-size:15px; color:#333; line-height:1.7;'>" +
                        "<ol>" +
                        "<li>The quiz consists of multiple-choice questions — only one option is correct for each question.</li><br>" +
                        "<li>Each question carries <b>1 mark</b>. There is no negative marking for wrong answers.</li><br>" +
                        "<li>You will get <b>10 seconds</b> to answer each question. When time runs out, the quiz automatically moves to the next question.</li><br>" +
                        "<li>Once you move to the next question, you <b>cannot go back</b> or change your previous answer.</li><br>" +
                        "<li>You can use your <b>50-50 lifeline</b> only once — it removes two incorrect options.</li><br>" +
                        "<li>Read each question carefully before selecting your answer.</li><br>" +
                        "<li>At the end of the quiz, your <b>total score</b> will be displayed on the screen.</li><br>" +
                        "<li>Try to answer as many questions correctly as you can. Good luck and have fun! 🎯</li>" +
                        "</ol></body></html>"
        );
        rules.setEditable(false);
        rules.setBorder(null);
        rules.setBackground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(rules);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(230, 230, 250)));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBackground(Color.WHITE);
        card.add(scrollPane, BorderLayout.CENTER);

        // ---- BUTTON PANEL ----
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 10));
        buttonPanel.setBackground(Color.WHITE);

        back = createButton("← Back", new Color(255, 99, 71));
        start = createButton("Start ▶", new Color(72, 61, 139));

        buttonPanel.add(back);
        buttonPanel.add(start);
        card.add(buttonPanel, BorderLayout.SOUTH);

        // ---- ADD CARD TO BACKGROUND ----
        background.add(card);

        // ---- BUTTON ACTIONS ----
        back.addActionListener(this);
        start.addActionListener(this);

        setVisible(true);
    }

    // Custom styled button with hover effect
    private JButton createButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Montserrat", Font.BOLD, 16));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setOpaque(true);
        button.setBorderPainted(false);

        // hover animation
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(color.darker());
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(color);
            }
        });
        return button;
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == start) {
            setVisible(false);
            new Quiz(name);
        } else {
            setVisible(false);
            new Login();
        }
    }

    public static void main(String[] args) {
        new Rules("User");
    }
}
