package BlackJack.model.rules;

import BlackJack.model.Dealer;
import BlackJack.model.Player;

//Dealer wins in this one if scores are equal
public class DealerEqualsWinStrategy implements IWinStrategy{

	@Override
	public boolean IsDealerWinner(Player a_player, Dealer a_dealer) {
		//First we check if player has gone over the score of 21
		if (a_player.CalcScore() > a_player.getMaxScore()) {
			//If it has, dealer wins
			return true;
		}
		//Has dealer gone over 21?
		else if (a_dealer.CalcScore() > a_player.getMaxScore()) {
			return false;
		}
		
		return a_dealer.CalcScore() >= a_player.CalcScore();
	}

}
