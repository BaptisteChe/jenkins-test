package paniertp2.model;

import paniertp2.view.vueDesc;
import java.util.ArrayList;
import java.util.Observable;

public class Modele extends Observable{
    private int compteur;   //compteur toujours positif
    private Panier pan;
    private vueDesc v;
    
    public Modele(Panier p){
        compteur = 0;
        pan = p;
        v = new vueDesc();
        v.setVisible(true);
    }
    
    public void update(int incr) throws PanierPleinException, PanierVideException {
        int cpt2 = compteur + incr;
        if(cpt2 < compteur){
            if(cpt2<0){
                v.Fruits.setText("Erreur !");
            }else{
                pan.retrait();
                compteur = cpt2;
                v.Fruits.setText(pan.toString());
            }
        }else if(pan.getContenanceMax() <= cpt2){
            compteur = cpt2;
            v.Fruits.setText("Panier Plein !");
        }else{
            compteur += incr;
            if(compteur < 0)       
            {    
                compteur = 0;
                pan.setFruits(new ArrayList<Fruit>());
            }else{
                pan.ajout(new Orange());
                v.Fruits.setText(pan.toString()); 
            }
        }
        
        setChanged();                //marks this Observable object as having been changed; the hasChanged method will now return true
        notifyObservers(getCompteur());   //if this object has changed, as indicated by the hasChanged method, then notify all of its observers and then call the clearChanged method to indicate that this object has no longer changed
    }

    /**
     * @return the compteur
     */
    public int getCompteur() {
        return compteur;
    }

    /**
     * @param compteur the compteur to set
     */
    public void setCompteur(int compteur) {
        this.compteur = compteur;
        if(this.compteur < 0)
            this.compteur = 0;
        
        setChanged();                //marks this Observable object as having been changed; the hasChanged method will now return true
        notifyObservers(getCompteur());   //if this object has changed, as indicated by the hasChanged method, then notify all of its observers and then call the clearChanged method to indicate that this object has no longer changed
    }
    
}

