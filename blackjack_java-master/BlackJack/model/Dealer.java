package BlackJack.model;

import java.util.ArrayList;

import BlackJack.model.rules.*;

public class Dealer extends Player {

	private Deck m_deck;
	private INewGameStrategy m_newGameRule;
	private IHitStrategy m_hitRule;
	private ArrayList<Observer> m_subscribers;

	public Dealer(RulesFactory a_rulesFactory) {

		m_newGameRule = a_rulesFactory.GetNewGameRule();
		m_hitRule = a_rulesFactory.GetHitRule();
		m_subscribers = new ArrayList<Observer>();
	}

	public boolean NewGame(Player a_player) {
		if (m_deck == null || IsGameOver()) {
			m_deck = new Deck();
			ClearHand();
			a_player.ClearHand();
			return m_newGameRule.NewGame(m_deck, this, a_player);
		}
		return false;
	}


	public boolean Stand() { // Method for Stand added
		if (m_deck != null) {
			ShowHand();
			while (m_hitRule.DoHit(this)) {
				GiveCard(this, true);
			}
			return true;
		}
		return false;
	}


	public boolean Hit(Player a_player) {
		if (m_deck != null && a_player.CalcScore() < g_maxScore && !IsGameOver()) {
			a_player.DealCard(m_deck.GetCard(), true);

			return true;
		}
		return false;
	}

	public boolean IsDealerWinner(Player a_player) {
		if (a_player.CalcScore() > g_maxScore) {
			return true;
		} else if (CalcScore() > g_maxScore) {
			return false;
		}
		return CalcScore() >= a_player.CalcScore();
	}

	public void dealCard(Player a_player, boolean a_shown) {
		a_player.DealCard(m_deck.GetCard(), a_shown);
	}

	public boolean IsGameOver() {
		if (m_deck != null && m_hitRule.DoHit(this) != true) {
			return true;
		}
		return false;
	}

	public void AddSubscriber(Observer subscriber) {
		this.m_subscribers.add(subscriber);
	}

	public void GiveCard(Player a_player, boolean a_shown) {// added
		a_player.DealCard(m_deck.GetCard(), a_shown);
		//notifier that card is dealt
		for (Observer subscriber : m_subscribers) {
			subscriber.DealNewCard();
		}
	}

	public void GiveCard(Player a_player) {
		GiveCard(a_player, true);
	}
}
