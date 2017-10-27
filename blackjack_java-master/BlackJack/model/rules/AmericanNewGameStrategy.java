package BlackJack.model.rules;

import BlackJack.model.Deck;
import BlackJack.model.Dealer;
import BlackJack.model.Player;
import BlackJack.model.Card;

class AmericanNewGameStrategy implements INewGameStrategy {

	public boolean NewGame(Deck a_deck, Dealer a_dealer, Player a_player) {
		a_dealer.GiveCard(a_player);
		a_dealer.GiveCard(a_dealer);
		a_dealer.GiveCard(a_player);
		a_dealer.GiveCard(a_dealer, false); // the last dealer card is hidden
											// from start
		return true;
	}
}