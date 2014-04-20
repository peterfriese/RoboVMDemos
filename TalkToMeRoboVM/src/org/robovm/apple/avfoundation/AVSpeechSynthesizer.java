package org.robovm.apple.avfoundation;

import org.robovm.apple.foundation.NSObject;
import org.robovm.objc.annotation.Method;
import org.robovm.objc.annotation.NativeClass;
import org.robovm.rt.bro.annotation.Library;

@Library("AVFoundation")
@NativeClass
public class AVSpeechSynthesizer extends NSObject {
	
	@Method(selector = "speakUtterance:")
	public native void speakUtterance(AVSpeechUtterance utterance);
}