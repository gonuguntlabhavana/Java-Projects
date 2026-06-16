import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PongGame extends JPanel implements ActionListener, KeyListener {

    int ballX = 300, ballY = 200;
    int ballXSpeed = 3, ballYSpeed = 3;

    int paddle1Y = 150;
    int paddle2Y = 150;

    int score1 = 0;
    int score2 = 0;

    Timer timer;

    public PongGame() {
        JFrame frame = new JFrame("Pong Game");
        frame.setSize(800, 500);
        frame.add(this);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addKeyListener(this);

        timer = new Timer(10, this);
        timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.fillRect(20, paddle1Y, 10, 80);
        g.fillRect(760, paddle2Y, 10, 80);

        g.fillOval(ballX, ballY, 20, 20);

        g.drawString("Player 1: " + score1, 100, 30);
        g.drawString("Player 2: " + score2, 600, 30);
    }

    public void actionPerformed(ActionEvent e) {

        ballX += ballXSpeed;
        ballY += ballYSpeed;

        if (ballY <= 0 || ballY >= 440)
            ballYSpeed *= -1;

        if (ballX <= 30 && ballY >= paddle1Y && ballY <= paddle1Y + 80)
            ballXSpeed *= -1;

        if (ballX >= 740 && ballY >= paddle2Y && ballY <= paddle2Y + 80)
            ballXSpeed *= -1;

        if (ballX < 0) {
            score2++;
            ballX = 300;
            ballY = 200;
        }

        if (ballX > 780) {
            score1++;
            ballX = 300;
            ballY = 200;
        }

        repaint();
    }

    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_W)
            paddle1Y -= 20;

        if (e.getKeyCode() == KeyEvent.VK_S)
            paddle1Y += 20;

        if (e.getKeyCode() == KeyEvent.VK_UP)
            paddle2Y -= 20;

        if (e.getKeyCode() == KeyEvent.VK_DOWN)
            paddle2Y += 20;
    }

    public void keyReleased(KeyEvent e) {}

    public void keyTyped(KeyEvent e) {}

    public static void main(String[] args) {
        new PongGame();
    }
}