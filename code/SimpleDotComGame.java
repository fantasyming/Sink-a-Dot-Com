public class SimpleDotComGame{

	public static void main(String[] args){
		int numOfGuess = 0;
		boolean isAlive = true;
		int randomNum = (int)(Math.random()*5);
		int[] locations = {randomNum,randomNum+1,randomNum+2};

		GameHelper helper = new GameHelper();
		SimpleDotCom dot = new SimpleDotCom();
		dot.setLocationCells(locations);
		

		while(isAlive == true){
			String guess = helper.getUserInput("enter a number");
			String result = dot.checkYourself(guess);
			numOfGuess++;
			if(result.equals("kill")){
				isAlive = false;
				System.out.println("You took "+numOfGuess+" guesses");
			}
		}
	}
}