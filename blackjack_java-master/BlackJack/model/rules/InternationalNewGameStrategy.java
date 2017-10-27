package BlackJack.model.rules;

import BlackJack.model.Deck;
import BlackJack.model.Dealer;
import BlackJack.model.Player;
import BlackJack.model.Card;

class InternationalNewGameStrategy implements INewGameStrategy {

	public boolean NewGame(Dealer a_dealer, Player a_player) {
		a_dealer.GiveCard(a_player);
		a_dealer.GiveCard(a_dealer);
		a_dealer.GiveCard(a_player);		

		return true;
	}
}
