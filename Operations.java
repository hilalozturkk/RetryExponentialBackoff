public class Operations {

	public static int MaxWaitingTime = 10000;
	public static int MaxNumberRetry = 10;

	public static String result = "NOT_READY";

	public static void Operation() {

		try {
			int reTry = 0;
			boolean retry;

			do {
				long watingTime = Math.min(WaitTime(reTry), MaxWaitingTime);
				reTry++;
				System.out.print(reTry + ". Wating time : " + watingTime + "\n");
				Thread.sleep(watingTime);

				retry = AsyncOperation(result);

			} while (retry && reTry < MaxNumberRetry);

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static boolean AsyncOperation(String result) {

		if (result == "SUCCESS")
			return false;
		else if (result == "NOT_READY") {
			return true;
		} else if (result == "THROTTLED") {
			return true;
		} else if (result == "SERVER_ERROR") {
			return true;
		} else {
			return false;
		}
	}

	public static long WaitTime(int reTry) {
		long watingTime = ((long) Math.pow(2, reTry) * 100);
		return watingTime;
	}

}