import java.awt.Color;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Shape;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;

import javax.swing.JTextField;

public class TextFieldCool extends JTextField implements FocusListener, KeyListener
{
	private static final int ARC_WIDTH = 10;
    private static final int ARC_HEIGHT = 10;

	private static final ArrayList<Integer> KEYEVENTS = new ArrayList<Integer>();

    static 
	{
        KEYEVENTS.add(KeyEvent.VK_DELETE);
        KEYEVENTS.add(KeyEvent.VK_SHIFT);
        KEYEVENTS.add(KeyEvent.VK_CONTROL);
        KEYEVENTS.add(KeyEvent.VK_ALT);
        KEYEVENTS.add(KeyEvent.VK_CAPS_LOCK);
        KEYEVENTS.add(KeyEvent.VK_ESCAPE);
        KEYEVENTS.add(KeyEvent.VK_LEFT);
        KEYEVENTS.add(KeyEvent.VK_RIGHT);
        KEYEVENTS.add(KeyEvent.VK_UP);
        KEYEVENTS.add(KeyEvent.VK_DOWN);
		KEYEVENTS.add(20);
		KEYEVENTS.add(16);
		KEYEVENTS.add(17);
		KEYEVENTS.add(18);
		KEYEVENTS.add(37);
		KEYEVENTS.add(38);
		KEYEVENTS.add(39);
		KEYEVENTS.add(40);
    }

	private boolean masque;
	private String textMdp;
	private String textBase;

	private PleinEcranFrame frame;

	public TextFieldCool(boolean masque, String textBase, PleinEcranFrame frame) 
	{
		this();
		this.masque = masque;
		this.textMdp = "";

		this.frame = frame;

		this.textBase = textBase;
		this.setText(textBase);
	}

	public TextFieldCool()
	{
		this.setForeground(Color.GRAY);

		this.textBase = "Entrez votre texte ici";
		this.setText(this.textBase);

		this.addFocusListener(this);
		this.addKeyListener(this);

		setMargin(new Insets(5, 10, 5, 10));

		this.masque = false;
	}

	@Override
    protected void paintComponent(Graphics g) {
        g.setColor(getBackground());
        g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, ARC_WIDTH, ARC_HEIGHT);
        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
		if(this.hasFocus())
			g.setColor(Color.BLUE);//changer le bleu
		else
        	g.setColor(Color.LIGHT_GRAY);

        g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, ARC_WIDTH, ARC_HEIGHT);
    }

    @Override
    public boolean contains(int x, int y) {
        Shape shape = new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, ARC_WIDTH, ARC_HEIGHT);
        return shape.contains(x, y);
    }
	
	@Override
	public void focusGained(FocusEvent e) 
	{
		if (this.getText().equals(this.textBase)) 
		{
			this.setText("");
			this.setForeground(Color.BLACK);
		}
	}

	@Override
	public void focusLost(FocusEvent e) 
	{
		if (this.getText().isEmpty()) 
		{
			this.setForeground(Color.GRAY);
			this.setText(this.textBase);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) 
	{
		
	}


	@Override
	public void keyPressed(KeyEvent e) 
	{
		
	}

	@Override
	public void keyReleased(KeyEvent e) 
	{
		int n = e.getKeyCode();
		char c = e.getKeyChar();

		if( n == KeyEvent.VK_ENTER)
		{
			if (!this.masque) 
				this.frame.next(1);
			else
				this.frame.next(2);
		}
		
		if (this.masque) 
		{
			if ( KEYEVENTS.contains(n) ) 
			{
				System.out.println("retour");
				return;
			}

			if(KeyEvent.VK_BACK_SPACE == c )
			{
				String s = "", s2 = "";
				for (int i = 0; i < textMdp.length() - 1; i++) 
				{
					s += textMdp.charAt(i);
					s2 += "•";
				}
				this.textMdp = s;
				this.setText(s2);
				return;
			}
			
			textMdp += c;

			String s = "";
			for (int i = 0; i < textMdp.length(); i++) 
			{
				s += "•";
			}
			this.setText(s);
		}
	}

	public String getMotPasse()
	{
		return this.textMdp;
	}

	public void reset()
	{
		this.textMdp = "";
		this.setText(textBase);
	}
	
}
