package BlackJack.model.rules;

public class RulesFactory {
	
	public IWinStrategy GetWinRule() {
		return new BasicWinStrategy();
	}

	public IHitStrategy GetHitRule() {
		return new Soft17HitStrategy();
	}

	public INewGameStrategy GetNewGameRule() {
		return new AmericanNewGameStrategy();
	}
}