package BlackJack.controller;

import BlackJack.view.IView;
import BlackJack.model.Game;

public class PlayGame {
	
	Game game;
	IView view;
	
	public PlayGame(Game a_game, IView a_view) {
		this.game = a_game;
		this.view = a_view;
	}

	public boolean Play() {
		view.DisplayWelcomeMessage();

		view.DisplayDealerHand(game.GetDealerHand(), game.GetDealerScore());
		view.DisplayPlayerHand(game.GetPlayerHand(), game.GetPlayerScore());

		if (game.IsGameOver()) {
			view.DisplayGameOver(game.IsDealerWinner());
		}

		int input = view.GetInput();

		if (input == 'p') {
			game.NewGame();
		} else if (input == 'h') {
			game.Hit();
		} else if (input == 's') {
			game.Stand();
		}

		return input != 'q';
	}
}