package modele;

public class Position {
    private static int nombreDePas = 0;
    private int abscisse;
    private int ordonnee;

    public Position(int abscisse, int ordonnee){
        this.abscisse = abscisse;
        this.ordonnee = ordonnee;
    }

    /**
     * la méthode deplacementUneCase déplace la position this d'une case
     * pour la rapprocher de celle du paramètre parPosition
     * elle incrémente le champ static nombreDePas
     * @param parPosition : la position vers laquelle this se rapproche**/
    public void deplacementUneCase(Position parPosition){
        nombreDePas++;
        if (this.abscisse > parPosition.abscisse){
            this.abscisse -=1;
            return;
        }
        else if (this.abscisse < parPosition.abscisse){
            this.abscisse +=1;
            return;
        }
        if (this.ordonnee > parPosition.ordonnee){
            this.ordonnee -=1;
            return;
        }
        else if (this.ordonnee < parPosition.ordonnee){
            this.ordonnee +=1;
            return;
        }
    }

    /**
     * Méthode pour comparer deux positions et déterminer si elles sont égales.
     * @param objet : l'objet à comparer
     * @return true si les positions sont égales, false sinon**/
    public boolean equals(Object objet) {
        if (this == objet){
            return true;
        }
        if (objet == null || getClass() != objet.getClass()){
            return false;
        }
        Position position = (Position) objet;
        return abscisse == position.abscisse &&
                ordonnee == position.ordonnee;
    }

    // Accesseurs nécessaires

    public int getAbscisse() {
        return abscisse;
    }

    public int getOrdonnee() {
        return ordonnee;
    }

    public static int getNombreDePas() {
        return nombreDePas;
    }

    /**
     * Méthode pour afficher la position sous forme de chaîne de caractères.
     * @return la position sous forme de chaîne de caractères**/
    public String toString() {
        return "Position{" +
                "abscisse=" + abscisse +
                ", ordonnee=" + ordonnee +
                '}';
    }
}