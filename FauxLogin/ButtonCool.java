import java.awt.Color;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Shape;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import java.awt.Font;
import java.awt.geom.RoundRectangle2D;

public class ButtonCool extends JButton {
    private static final int ARC_WIDTH = 10;
    private static final int ARC_HEIGHT = 10;

    public ButtonCool(String s) 
    {
        super(s);
        setMargin(new Insets(5, 10, 5, 10));
        setContentAreaFilled(false);

		setFont(new Font("Arial", Font.PLAIN, 12));
        //setForeground(new Color(90,90,90));

        setBackground(new Color(220, 220, 220));
        setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        setFocusPainted(false);

        this.requestFocus();
    }

    @Override
    protected void paintComponent(Graphics g) 
    {
        if (getModel().isArmed()) 
        {
            g.setColor(new Color(220, 220, 220));
        } else if (getModel().isRollover()) 
        {
            g.setColor(new Color(230, 230, 230));
        } else 
        {
            g.setColor(new Color(220, 220, 220));
        }
        g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, ARC_WIDTH, ARC_HEIGHT);
        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        g.setColor(Color.GRAY);
        g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, ARC_WIDTH, ARC_HEIGHT);
    }

    @Override
    public boolean contains(int x, int y) {
        Shape shape = new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, ARC_WIDTH, ARC_HEIGHT);
        return shape.contains(x, y);
    }

    @Override
    public Insets getInsets() {
        return new Insets(8, 15, 8, 15);
    }
}
