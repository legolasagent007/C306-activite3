/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package secondeactivitesudoku;

import java.util.Arrays;

/**
 *
 * @author Idjame
 */
public class GrilleImpl implements Grille {

    /**
     * attribut DIMENSION1.
     */
    private static final int DIMENSION1 = 9;
    /**
     * attribut DIMENSION2.
     */
    private static final int DIMENSION2 = 16;
    /**
     * attribut dimension.
     */
    private final int dimension;
    /**
     * attribut valeurspossibles.
     */
    private final char[] valeurspossibles;
    /**
     * attribut valeurs du sudoku.
     */
    private char[][] values;

    /**
     * constructeur.
     * @param ladimension comme taille
     * @throws IllegalArgumentException quand ma taille diffère de 9 ou 16
     */
    public GrilleImpl(final int ladimension) throws IllegalArgumentException {
        if (ladimension != DIMENSION1 && ladimension != DIMENSION2) {
            throw new IllegalArgumentException();
        }
        dimension = ladimension;
        valeurspossibles = new char[dimension + 1];
        int t = 0;
        for (int i = 0; i < dimension; i++) {
            valeurspossibles[i] = Grille.POSSIBLE[i];
            t = i;
        }
        valeurspossibles[t + 1] = Grille.EMPTY;
        values = new char[dimension][dimension];
        for (int i = 0; i < dimension; i++) {
            for (int y = 0; y < dimension; y++) {
                values[i][y] = Grille.EMPTY;
            }
        }
    }

    /**
     * getDimension.
     *
     * @return la dimension
     */
    public int getDimension() {
        return dimension;
    }

    /**
     * setValue.
     * @param x est la colonne
     * @param y est la ligne
     * @param value est la valeur de la case
     * @throws IllegalArgumentException dans certains cas
     */
    public void setValue(final int x, final int y, final char value)
            throws IllegalArgumentException {
        //Récupération des valeurs de comparaison
        char[] valeursCarre = getToutCarre(x, y, value);
        char[] valeursColonne = getTouteColonne(x, y, value);
        char[] valeursLigne = getTouteLigne(x, y, value);
        //Fin récuprération
        if (!possible(x, y, value)
                || (Arrays.toString(valeursLigne).contains("" + value + ""))
                || (Arrays.toString(valeursColonne).contains("" + value + ""))
                || (Arrays.toString(valeursCarre).contains("" + value + ""))) {
            throw new IllegalArgumentException();
        }
        values[x][y] = value;
    }
    /**
     * getValue.
     * @param x est la colonne
     * @param y est la ligne
     * @throws IllegalArgumentException dans certains cas
     * @return la valeur de case
     */
    public char getValue(final int x, final int y)
            throws IllegalArgumentException {
        if ((x < 0 || x >= dimension) || (y < 0 || y >= dimension)) {
            throw new IllegalArgumentException();
        }
        return values[x][y];
    }
    /**
     * getValue.
     * @return true si c'est complet
     */
    public boolean complete() {
        boolean estComplet = true;
        for (int i = 0; (i < dimension && estComplet); i++) {
            for (int y = 0; (y < dimension && estComplet); y++) {
                if (values[i][y] == Grille.EMPTY) {
                    estComplet = false;
                }
            }
        }
        return estComplet;
    }
    /**
     * possible.
     * @param x est la colonne
     * @param y est la ligne
     * @param value est la valeur de la case
     * @throws IllegalArgumentException dans certains cas
     * @return la valeur de case
     */
    public boolean possible(final int x, final int y,
            final char value) throws IllegalArgumentException {
        if ((x < 0 || x >= dimension)
                || (y < 0 || y >= dimension)
                ||
                (!Arrays.toString(valeurspossibles).
                        contains("" + value + ""))
                ) {
            throw new IllegalArgumentException();
        }
        return true;
    }
    /**
     * getToutCarre.
     * @param x est la colonne
     * @param y est la ligne
     * @param value est la valeur de la case
     * @throws IllegalArgumentException dans certains cas
     * @return toutes les valeurs appartenant au même carré
     */
    private char[] getToutCarre(final int x, final int y, final char value)
            throws IllegalArgumentException {
        if (!possible(x, y, value)) {
            throw new IllegalArgumentException();
        }
        //Récupération des valeurs du même morceau de carre
        int tailleCarre = (int) Math.sqrt(dimension);
        int indiceColonneCarre = x / tailleCarre;
        int indiceLigneCarre = y / tailleCarre;
        int minColonneCarre = indiceColonneCarre * tailleCarre;
        int maxColonneCarre = minColonneCarre + tailleCarre - 1;
        int minLigneCarre = indiceLigneCarre * tailleCarre;
        int maxLigneCarre = minLigneCarre + tailleCarre - 1;
        char[] valeursCarre = new char[dimension];
        int j = 0;
        for (int i = minColonneCarre; i <= maxColonneCarre; i++) {
            for (int u = minLigneCarre; u <= maxLigneCarre; u++) {
                valeursCarre[j] = values[i][u];
                j++;
            }
        }
        //Fin récuprération
        return valeursCarre;
    }
    /**
     * getTouteColonne.
     * @param x est la colonne
     * @param y est la ligne
     * @param value est la valeur de la case
     * @throws IllegalArgumentException dans certains cas
     * @return toutes les valeurs appartenant à la même colonne
     */
    private char[] getTouteColonne(final int x, final int y, final char value)
            throws IllegalArgumentException {
        if (!possible(x, y, value)) {
            throw new IllegalArgumentException();
        }
        //Récupération des valeurs de la même colonne
        char[] valeursColonne = new char[dimension];
        int jj = 0;
        for (int u = dimension; u < dimension; u++) {
            valeursColonne[jj] = values[x][u];
            jj++;
        }
            //Fin récuprération
        return valeursColonne;
    }
    /**
     * getTouteLigne.
     * @param x est la colonne
     * @param y est la ligne
     * @param value est la valeur de la case
     * @throws IllegalArgumentException dans certains cas
     * @return toutes les valeurs appartenant à la même ligne
     */
    private char[] getTouteLigne(final int x, final int y, final char value)
            throws IllegalArgumentException {
        if (!possible(x, y, value)) {
            throw new IllegalArgumentException();
        }
        //Récupération des valeurs de la même ligne
        char[] valeursLigne = new char[dimension];
        int jjj = 0;
        for (int u = dimension; u < dimension; u++) {
            valeursLigne[jjj] = values[u][y];
            jjj++;
        }
        //Fin récuprération
        return valeursLigne;
    }
}
