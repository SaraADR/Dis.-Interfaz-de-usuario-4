import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;


public class PanelImagen extends JPanel{
    
    private BufferedImage[] arrayImage = new BufferedImage[3];
    private BufferedImage f;

    private int pos = 0;
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponents(g);
        
        f = null;    
        try {
            arrayImage[0] = ImageIO.read(new File("gato.jpg"));
            arrayImage[1] = ImageIO.read(new File("imagene.png"));
            arrayImage[2] = ImageIO.read(new File("comida.jpg"));
        } catch (IOException ex) {
            Logger.getLogger(PanelImagen.class.getName()).log(Level.SEVERE, null, ex);
        }
        f = arrayImage[pos];
        g.drawImage(f,0,0,null);
        
    }
    
    public void setImage(int pos){
        this.pos = pos;
    }
    
    public int difuminar (Graphics g){
        System.out.println("DIFUMINO");
        float[] difuminar={
            0.111f,0.111f,0.111f,
            0.111f,0.111f,0.111f,
            0.111f,0.111f,0.111f,};
        Kernel sharpkernel = new Kernel(3,3,difuminar);
        ConvolveOp sop = new ConvolveOp(sharpkernel,ConvolveOp.EDGE_NO_OP, null);
        f= sop.filter(arrayImage[pos], null);
        g.drawImage(f,0,0,null);
        return pos;
        }

    public void resaltar(){
        float[]resaltar ={
            0.f , -1.f, 0.f,
            -1.f, 5.0f, -1.f,
            0.f, -1.f,0.f};
        Kernel sharpkernel = new Kernel(3,3,resaltar);
        ConvolveOp sop = new ConvolveOp(sharpkernel,ConvolveOp.EDGE_NO_OP, null);
        arrayImage[pos]= sop.filter(arrayImage[pos], null);
        
    }
}

  

