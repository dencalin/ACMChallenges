package stockprofit;

public class StockProfit {

	public static void main(String[] args){
		int[] stock_prices_yesterday = new int[]{5, 7, 4, 9, 6, 5, 4, 7, 3, 5};
		System.out.println(get_max_profit(stock_prices_yesterday));
	}
	
		static int get_max_profit(int[] prices) {
		int max = 0;
		for(int i = 0; i < prices.length-1; i++){
			for(int j = i; j < prices.length-1; j++){
				max = Math.max(max,  prices[j] - prices[i]);
			}
		}
		return max;
		
	}

}
