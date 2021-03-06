/*  This file is part of the source code for 3D-XplorMath-J, Version 1.0 (January 2008).
 *  Copyright (c) 2008 The 3D-XplorMath Consortium (http://3d-xplormath.org).
 *  This source code is released under a BSD License, which allows redistribution   
 *  in source and binary form, with or without modification, provided copyright
 *  and license information are included, and with no warranty or guarantee of
 *  any kind.  For details, see http://3d-xplormath.org/j/source/BSDLicense.txt
 */
 
 package vmm.pseudospherical;

import vmm.core.Complex;
import vmm.core3D.Vector3D;

public class ThreeSoliton extends nSolitons
{
	public ThreeSoliton()  
	{
		addParameter(s2);
		addParameter(s1);
		addParameter(s);
		umin.reset(-3);
		umax.reset(3);
		vmin.reset("-2*pi/3");
		vmax.reset("2*pi/2");
		setDefaultWindow(-0.732233,2.26917,-0.954466,1.37996);
		setDefaultViewpoint(new Vector3D(-2.38900,-7.28113,2.36436));
		setDefaultViewUp(new Vector3D(-0.785128,0.0573582,-0.616672));
		
		//get s and solitonNum
		lambda = new Complex(0,1);
		
		//set up storage for E at various values
		E = new ComplexMatrix2D[4][6];
		for(int i = 0; i <= 3; i++)
			for(int j = 0; j < 6; j++)
				E[i][j] = new ComplexMatrix2D();
		
		
		//set up storage for g tilde at various values
		g = new ComplexMatrix2D[4][5];
		for(int i = 0; i <= 3; i++)
			for(int j = 0; j < 5; j++)
				g[i][j] = new ComplexMatrix2D();
		
		
		//set up E0, projection matrix and identity matrix
		E0 = new ComplexMatrix2D();
		getE0(xVar, s.getValue(), lambda);
		proj = ComplexMatrix2D.getProj(initVec);
		proj1 = ComplexMatrix2D.getProj(initVec1);
		
		id = new ComplexMatrix2D();
		id.setMatrixEntry(Complex.ONE_C, 1, 1);
		id.setMatrixEntry(Complex.ZERO_C, 1, 2);
		id.setMatrixEntry(Complex.ZERO_C, 2, 1);
		id.setMatrixEntry(Complex.ONE_C, 2, 2);
		
		//initialize g1, g2, g3
		g1 = new ComplexMatrix2D();
		g2 = new ComplexMatrix2D();
	}
	
	synchronized public Vector3D surfacePoint(double x, double t)
	{
		ComplexMatrix2D ret = firstSoliton(x,t);
		afterFirstSol(x,t);
		ret = iterate1(x,t);
		afterIterate1(x,t);
		ret = iterate2();
		
		double aa =  ret.entries[1][1].im;
		double bb = ret.entries[1][2].re;
		double cc = ret.entries[1][2].im;
		
		if (Double.isNaN(aa) || Double.isNaN(aa) || Double.isNaN(aa))
			aa = bb = cc= 0;
		return new Vector3D(aa,bb,cc);
	}
	
}