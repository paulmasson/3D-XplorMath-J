/*  This file is part of the source code for 3D-XplorMath-J, Version 1.0 (January 2008).
 *  Copyright (c) 2008 The 3D-XplorMath Consortium (http://3d-xplormath.org).
 *  This source code is released under a BSD License, which allows redistribution   
 *  in source and binary form, with or without modification, provided copyright
 *  and license information are included, and with no warranty or guarantee of
 *  any kind.  For details, see http://3d-xplormath.org/j/source/BSDLicense.txt
 */
 
 package vmm.core;

/**
 * A parameter that has a real value and a start and end value to be used for animation.
 */
public class RealParamAnimateable extends RealParam implements Animateable {
	
	private double animationStart, animationEnd;
	private double defaultAnimationStart, defaultAnimationEnd;
	
	private String animationStartString, animationEndString;
	private String defaultStartString, defaultEndString;
	
	/**
	 * Creates a RealParamAnimateable that initially has no name and has value and default value equal to 0,
	 * with animation limits also equal to 0.
	 */
	public RealParamAnimateable() {
		this(null,0.0);
	}
	
	/**
	 * Create a RealParamAnimateable with a specified name and initial value and with
	 * animation start and end values equal to the same value
	 * @param name The name of the Parameter, which can be null but ordinarily should not be.
	 * The name shoud identify the Parameter uniquely among parameters associated with a given
	 * Parameterizeable object.
	 * @param value The initial value of the parameter.  This also becomes its default value.
	 */
	public RealParamAnimateable(String name, double value) {
		this(name, value, value, value);
	}
	
	/**
	 * Create a RealParamAnimateable with a specified name, initial value, and start and end values.
	 * @param name The name of the Parameter, which can be null but ordinarily should not be.
	 * The name shoud identify the Parameter uniquely among parameters associated with a given
	 * Parameterizeable object.
	 * @param value The initial value of the parameter.  This also becomes its default value.
	 * @param start the animation start value for this parameter.
	 * @param end the animation end value for this parameter.
	 */
	public RealParamAnimateable(String name, double value, double start, double end) {
		super(name,value);
		animationStart = defaultAnimationStart = start;
		animationEnd = defaultAnimationEnd = end;
	}
	
	/**
	 * Create a RealParamAnimateable with a specified name and initial value and with
	 * animation start and end values equal to the same value.
	 * @param name The name of the Parameter, which can be null but ordinarily should not be.
	 * The name shoud identify the Parameter uniquely among parameters associated with a given
	 * Parameterizeable object. 
	 * @param valueAsString The initial value of the parameter, given as a string. An error
	 * of type NumberFormatException occurs if this string does not represent a legal value.
	 */
	public RealParamAnimateable(String name, String valueAsString) {
		this(name, valueAsString, valueAsString, valueAsString);
	}
	
	/**
	 * Create a RealParamAnimateable with a specified name, initial value, and start and end values.
	 * The values are specified as strings.
	 * @param name The name of the Parameter, which can be null but ordinarily should not be.
	 * The name shoud identify the Parameter uniquely among parameters associated with a given
	 * Parameterizeable object.
	 * @param valueAsString The initial value of the parameter, given as a string. An error
	 * of type NumberFormatException occurs if this string does not represent a legal value.
	 * @param startString the animation start value given as a string.  A NumberFormatError can
	 * occur, if this is not a legal value.
	 * @param endString the animation end value given as a string.  A NumberFormatError can
	 * occur, if this is not a legal value.
	 */
	public RealParamAnimateable(String name, String valueAsString, String startString, String endString) {
		super(name,valueAsString);
		setAnimationStartFromString(startString);
		setAnimationEndFromString(endString);
		setDefaultAnimationStartFromString(startString);
		setDefaultAnimationEndFromString(endString);
	}
	
	/**
	 * Sets both the default value and the value of this parameter to the specified number.
	 * In addition the values and default values for the animation start and end properities
	 * are also set to the same value.
	 * This is primarily meant to be called in the constructor of an Exhibit when it wants to
	 * change the value of a parameter that is inherited from a superclass.
	 * @see Parameter#reset(String)
	 */
	public void reset(double value) {
		reset(new Double(value));
		setAnimationLimitsAndDefaults(value,value);
	}
	
	/**
	 * Sets both the value and default value of the parameter, as well as the value and default
	 * value of the animation start and end properties.
	 */
	public void reset(double value, double animationStart, double animationEnd) {
		reset(new Double(value));
		setAnimationLimitsAndDefaults(animationStart, animationEnd);
	}
	
	/**
	 * Set both the value and the default value of the parameter to the value encoded in the given string,
	 * as if that string had been passed to the constructor.
	 * In addition the values and default values for the animation start and end properities
	 * are also set to the same value.
	 * This is primarily meant to be called in the constructor of an Exhibit when it wants to
	 * change the value of a parameter that is inherited from a superclass.
	 * @see #stringToValueObject(String)
	 */
	public void reset(String valueAsString) {
		reset(valueAsString,valueAsString,valueAsString);
	}
	
	/**
	 * Sets both the value and default value of the parameter, as well as the value and default
	 * value of the animation start and end properties.
	 */
	public void reset(String valueAsString, String animationStartAsString, String animationEndAsString) {
		super.reset(valueAsString);
		setAnimationStartFromString(animationStartAsString);
		setAnimationEndFromString(animationEndAsString);
		setDefaultAnimationStartFromString(animationStartAsString);
		setDefaultAnimationEndFromString(animationEndAsString);
	}
	
	public double getDefaultAnimationStart() {
		return defaultAnimationStart;
	}
	
	public double getDefaultAnimationEnd() {
		return defaultAnimationEnd;
	}
	
	public void setDefaultAnimationLimits(double defaultStart, double defaultEnd) {
		defaultAnimationStart = defaultStart;
		defaultAnimationEnd = defaultEnd;
		defaultStartString = defaultEndString = null;
	}
	
	public String getDefaultAnimationStartAsString() {
		return (defaultStartString != null)? defaultStartString : ("" + defaultAnimationStart);
	}
	
	public String getDefaultAnimationEndAsString() {
		return (defaultEndString != null)? defaultEndString : ("" + defaultAnimationEnd);
	}
	
	public void setDefaultAnimationStartFromString(String startString) {
		Double d = (Double)stringToValueObject(startString);
		defaultAnimationStart = d.doubleValue();
		defaultStartString = startString;
	}
	
	public void setDefaultAnimationEndFromString(String endString) {
		Double d = (Double)stringToValueObject(endString);
		defaultAnimationEnd = d.doubleValue();
		defaultEndString = endString;
	}
	
	public void setAnimationLimits(double start, double end) {
		animationStart = start;
		animationEnd = end;
	}
	
	public void setAnimationLimitsAndDefaults(double start, double end) {
		setAnimationLimits(start,end);
		setDefaultAnimationLimits(start,end);
	}
	
	public void setAnimationStart(double x) {
		animationStart = x;
		animationStartString = null;
	}
	
	public void setAnimationEnd(double x) {
		animationEnd = x;
		animationEndString = null;
	}
	
	public double getAnimationStart() {
		return animationStart;
	}
	
	public double getAnimationEnd() {
		return animationEnd;
	}
	
	public void setAnimationStartFromString(String startVal) {
		Double d = (Double)stringToValueObject(startVal);
		animationStart = d.doubleValue();
		animationStartString = startVal;
	}

	public void setAnimationEndFromString(String endVal) {
		Double d = (Double)stringToValueObject(endVal);
		animationEnd = d.doubleValue();
		animationEndString = endVal;
	}

	public String getAnimationStartAsString() {
		return (animationStartString != null)? animationStartString : ("" + animationStart);
	}

	public String getAnimationEndAsString() {
		return (animationEndString != null)? animationEndString : ("" + animationEnd);
	}


	public void setFractionComplete(double fractionComplete) {
		setValue(animationStart + fractionComplete*(animationEnd - animationStart));
	}

	public boolean reallyAnimated() {
		return animationStart != animationEnd;
	}


}
