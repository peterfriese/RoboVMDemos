package org.robovm.demo.githubissues;

import org.robovm.apple.foundation.NSAutoreleasePool;
import org.robovm.apple.foundation.NSDictionary;
import org.robovm.apple.foundation.NSString;
import org.robovm.apple.uikit.UIApplication;
import org.robovm.apple.uikit.UIApplicationDelegateAdapter;
import org.robovm.apple.uikit.UIColor;
import org.robovm.apple.uikit.UINavigationController;
import org.robovm.apple.uikit.UIScreen;
import org.robovm.apple.uikit.UIWindow;

public class RoboVMGithubIssuesApplicationDelegate extends
		UIApplicationDelegateAdapter {

	private UIWindow window;

	@Override
	public boolean didFinishLaunching(UIApplication application,
			NSDictionary<NSString, ?> launchOptions) {
		window = new UIWindow(UIScreen.getMainScreen().getBounds());

		RepositoriesListViewController repositoriesListViewController = new RepositoriesListViewController();
		UINavigationController navigationController = new UINavigationController(
				repositoriesListViewController);
		navigationController.addStrongRef(repositoriesListViewController);
		window.setRootViewController(navigationController);

		window.setBackgroundColor(UIColor.colorWhite());
		window.makeKeyAndVisible();

		return true;
	}

	public static void main(String[] args) {
		NSAutoreleasePool pool = new NSAutoreleasePool();
		UIApplication.main(args, null,
				RoboVMGithubIssuesApplicationDelegate.class);
		pool.close();
	}

}
