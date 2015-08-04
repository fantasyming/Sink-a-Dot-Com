public class DotCom{
		public int[] locationCells;
		public int numOfHits;

		public void setLocationCells(int[] celllocations){
			locationCells = celllocations;
		} 

		public String checkYourself(String stringguess){
			int guess = Integer.parseInt(stringguess);

			String result = "miss";

			for(int cell : locationCells){
				if(cell == guess){
					result = "hit";
					numOfHits++;
					break;
				}
			}

			if(numOfHits == locationCells.length){
				result = "kill";
			}
			System.out.println(result);
			return result;
		}
}