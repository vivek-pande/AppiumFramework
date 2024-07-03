package extentReport;

import java.util.logging.LogManager;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import freemarker.log.Logger;

public class MyRetry implements IRetryAnalyzer {
	private static final int maxTry = 3;
	private int count = 0;

	@Override
	public boolean retry(final ITestResult iTestResult) {
		if (!iTestResult.isSuccess()) {
			if (this.count < maxTry) {
				this.count++;
				return true;
			}
		}
		return false;
	}

	public String getResultStatusName(final int status) {
		String resultName = null;
		if (status == 1) {
			resultName = "SUCCESS";
		}
		if (status == 2) {
			resultName = "FAILURE";
		}
		if (status == 3) {
			resultName = "SKIP";
		}
		return resultName;
	}
}
