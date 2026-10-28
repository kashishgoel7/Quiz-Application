import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Score extends JFrame implements ActionListener {

    JButton playAgain;

    Score(String name, int score) {

        // ---- FRAME SETTINGS ----
        setTitle("Quiz Score");
        setSize(750, 550);
        setLocation(400, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // ---- GRADIENT BACKGROUND ----
        JPanel background = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(
                        0, 0, new Color(72, 61, 139),      // Deep purple
                        0, getHeight(), new Color(123, 179, 255) // Light blue
                );
                g2.setPaint(gp);
                g2.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        background.setLayout(new GridBagLayout());
        add(background, BorderLayout.CENTER);

        // ---- WHITE CARD PANEL ----
        JPanel card = new JPanel(null);
        card.setPreferredSize(new Dimension(650, 400));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 255), 1),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        background.add(card);

        // ---- HEADING ----
        JLabel heading = new JLabel("Thank you, " + name + " 👏");
        heading.setFont(new Font("Montserrat", Font.BOLD, 30));
        heading.setForeground(new Color(72, 61, 139));
        heading.setBounds(180, 30, 400, 40);
        card.add(heading);

        // ---- SUBTEXT ----
        JLabel sub = new JLabel("<html>for playing <b><i>Simple Minds Quiz</i></b></html>", SwingConstants.CENTER);
        sub.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        sub.setForeground(new Color(90, 90, 120));
        sub.setBounds(180, 75, 350, 30);
        card.add(sub);

        // ---- SCORE IMAGE ----
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("score.png"));
        Image i2 = i1.getImage().getScaledInstance(220, 180, Image.SCALE_SMOOTH);
        JLabel image = new JLabel(new ImageIcon(i2));
        image.setBounds(50, 180, 220, 180);
        card.add(image);

        // ---- SCORE LABEL ----
        JLabel lblscore = new JLabel("Your Final Score: " + score);
        lblscore.setFont(new Font("Montserrat", Font.BOLD, 28));
        lblscore.setForeground(new Color(72, 61, 139));
        lblscore.setBounds(320, 190, 350, 40);
        card.add(lblscore);

        // ---- MESSAGE BELOW SCORE ----
        JLabel msg = new JLabel();
        if (score >= 8)
            msg.setText(" Excellent performance! You nailed it!");
        else if (score >= 5)
            msg.setText(" Good job! You can still go higher!");
        else
            msg.setText(" Keep learning! Try again for a better score!");

        msg.setFont(new Font("Segoe UI", Font.ITALIC, 16));
        msg.setForeground(new Color(100, 100, 130));
        msg.setBounds(320, 240, 380, 25);
        card.add(msg);

        // ---- PLAY AGAIN BUTTON ----
        playAgain = new JButton("Play Again ▶");
        playAgain.setFont(new Font("Montserrat", Font.BOLD, 16));
        playAgain.setBounds(380, 300, 160, 40);
        playAgain.setBackground(new Color(72, 61, 139));
        playAgain.setForeground(Color.WHITE);
        playAgain.setFocusPainted(false);
        playAgain.setCursor(new Cursor(Cursor.HAND_CURSOR));
        playAgain.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));

        // Hover animation
        playAgain.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                playAgain.setBackground(new Color(60, 50, 130));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                playAgain.setBackground(new Color(72, 61, 139));
            }
        });

        playAgain.addActionListener(this);
        card.add(playAgain);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        setVisible(false);
        new Login();
    }

    public static void main(String[] args) {
        new Score("User",7);
    }
}
