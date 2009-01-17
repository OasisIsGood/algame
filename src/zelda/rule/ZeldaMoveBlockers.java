package zelda.rule;

import zelda.entity.characters.Guard;
import zelda.entity.decors.Tree;
import gameframework.game.IllegalMoveException;
import gameframework.game.MoveBlockerRuleApplierDefaultImpl;

/*
 * 	CLASSE INUTILE POUR NOUUUUUUS !!! 
 * ça servait pour que les ghosts ne sortent pas de la prison 
 * tant que le jeu ne commençait pas avec pacman...
 * 
 * Gardons la au cas où...ON POURRAIT ANNULER LES ATTAQUES DE GHOSTS 
 * TANT QUE LINK NE BOUGE PAS !!!...PFF INUTILE...
 */
public class ZeldaMoveBlockers extends MoveBlockerRuleApplierDefaultImpl {
	
	public void moveBlockerRule(Guard guard, Tree tree) throws IllegalMoveException {
		throw new IllegalMoveException();
	}
}
