package dplocal.fibonaciinums;

public class _1_Fibonacci {
	
	private static _1_Fibonacci instance = new _1_Fibonacci();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int n = 10;
		System.out.println(System.currentTimeMillis());
		System.out.println(instance.calNthFib(n));
		System.out.println(System.currentTimeMillis());
		
		int[] dp = new int[n+1];
		//dp[0] = 0;
		//dp[1] = 1;
		System.out.println(System.currentTimeMillis());
		int ret = instance.calNthFib2(n,dp);
		System.out.println(ret);
		System.out.println(System.currentTimeMillis());
		
		System.out.println(instance.calNthFib3(n));
	}

	private int calNthFib(int n) {
		if(n<2) {
			return n;
		}
		return calNthFib(n-1) + calNthFib(n-2); 
	}
	
	/*
	 * top -down - recursive
	 */
	private int calNthFib2(int n, int[] dp) {
		if(n<2) {
			return n;
		}
		if(dp[n] > 0)
			return dp[n];
		dp[n] = calNthFib2(n-1, dp) + calNthFib2(n-2,dp);
		return dp[n];
	}
	
	/*
	 * bottom-up iterative
	 */
	private int calNthFib3(int n) {
		int[] dp = new int[n+1];
		dp[0] = 0;
		dp[1] = 1;
		for(int i=2;i<=n;i++) {
			dp[i] = dp[i-1] + dp[i-2];
		}
		return dp[n];
	}
}
