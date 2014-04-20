package org.robovm.apple.avfoundation;

import org.robovm.apple.foundation.NSObject;
import org.robovm.objc.annotation.Method;
import org.robovm.objc.annotation.NativeClass;
import org.robovm.rt.bro.annotation.Library;
import org.robovm.rt.bro.annotation.Pointer;

@Library("AVFoundation")
@NativeClass
public class AVSpeechUtterance extends NSObject {
	
    @Method(selector = "initWithString:")
    private native @Pointer long initWithString$(String utteranceString);
    
    public AVSpeechUtterance(String utteranceString) {
    	super((SkipInit)null);
    	initWithString$(utteranceString);
    }
    
}