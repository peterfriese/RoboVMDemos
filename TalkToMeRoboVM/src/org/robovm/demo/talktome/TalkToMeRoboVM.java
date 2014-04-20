package org.robovm.demo.talktome;
import org.robovm.apple.avfoundation.AVSpeechSynthesizer;
import org.robovm.apple.avfoundation.AVSpeechUtterance;
import org.robovm.apple.coregraphics.CGRect;
import org.robovm.apple.foundation.NSAutoreleasePool;
import org.robovm.apple.foundation.NSDictionary;
import org.robovm.apple.foundation.NSString;
import org.robovm.apple.uikit.UIApplication;
import org.robovm.apple.uikit.UIApplicationDelegateAdapter;
import org.robovm.apple.uikit.UIButton;
import org.robovm.apple.uikit.UIButtonType;
import org.robovm.apple.uikit.UIColor;
import org.robovm.apple.uikit.UIControl;
import org.robovm.apple.uikit.UIControl.OnTouchUpInsideListener;
import org.robovm.apple.uikit.UIControlState;
import org.robovm.apple.uikit.UIEvent;
import org.robovm.apple.uikit.UIScreen;
import org.robovm.apple.uikit.UIWindow;

public class TalkToMeRoboVM extends UIApplicationDelegateAdapter {

	private UIWindow window;
	private int timesClicked = 0;

	@Override
	public boolean didFinishLaunching(UIApplication application,
			NSDictionary<NSString, ?> launchOptions) {
		
		final UIButton button = UIButton.create(UIButtonType.System);
		button.setFrame(new CGRect(10, 200, 300, 30));
		button.setTitle("Hello World!", UIControlState.Normal);
		button.addOnTouchUpInsideListener(new OnTouchUpInsideListener() {
			@Override
			public void onTouchUpInside(UIControl control, UIEvent event) {
				timesClicked++;
				String message = String.format("You clicked me %s times", timesClicked);
				button.setTitle(message, UIControlState.Normal);
				
				AVSpeechUtterance utterance = new AVSpeechUtterance(message);
				AVSpeechSynthesizer synthesizer = new AVSpeechSynthesizer();
				synthesizer.speakUtterance(utterance);
				
			}
		});
		
		window = new UIWindow(UIScreen.getMainScreen().getBounds());
		window.setBackgroundColor(UIColor.colorLightGray());
		window.addSubview(button);
		window.makeKeyAndVisible();
		
		return true;
	}
	
	public static void main(String[] args) {
		try (NSAutoreleasePool pool = new NSAutoreleasePool()) {
			UIApplication.main(args, null, TalkToMeRoboVM.class);
		}
	}

}
