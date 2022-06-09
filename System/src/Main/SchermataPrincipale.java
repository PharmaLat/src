package Main;

import javax.swing.*;

public class SchermataPrincipale extends JFrame {

    String titolo = "Pagina Principale";
    int width = 1280;
    int heigth = 720;

    public SchermataPrincipale(){
        this.setTitle(titolo);
        this.setSize(width, heigth);
        this.setVisible(true);
    }
}
