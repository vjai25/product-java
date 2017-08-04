import javax.swing.*;
import java.awt.*;

public class MultiButtonTable {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Colored Trails");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JPanel firstPanel = new JPanel(new GridLayout(4, 4));
        firstPanel.setPreferredSize(new Dimension(4*100, 4*100));
        for (int i=1; i<=4; i++) {
            for (int j=1; j<=4; j++) {
                firstPanel.add(new JButton("delete"));
            }
        }

        JPanel firstGluePanel = new JPanel(new BorderLayout());
        firstGluePanel.add(firstPanel, BorderLayout.WEST);
        firstGluePanel.add(Box.createHorizontalGlue(), BorderLayout.CENTER);
        firstGluePanel.add(Box.createVerticalGlue(), BorderLayout.SOUTH);

        JPanel secondPanel = new JPanel(new GridLayout(13, 5));
        secondPanel.setPreferredSize(new Dimension(5*40, 13*40));
        for (int i=1; i<=5; i++) {
            for (int j=1; j<=13; j++) {
                secondPanel.add(new JButton("add"));
            }
        }

        JPanel secondGluePanel = new JPanel(new BorderLayout());
        secondGluePanel.add(secondPanel, BorderLayout.WEST);
        secondGluePanel.add(Box.createHorizontalGlue(), BorderLayout.CENTER);
        secondGluePanel.add(Box.createVerticalGlue(), BorderLayout.SOUTH);

        mainPanel.add(firstGluePanel);
        mainPanel.add(secondGluePanel);
        frame.getContentPane().add(mainPanel);

        //frame.setSize(400,600);
        frame.pack();
        frame.setVisible(true);
    }
}