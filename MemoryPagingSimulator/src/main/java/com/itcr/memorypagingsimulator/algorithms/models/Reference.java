/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itcr.memorypagingsimulator.algorithms.models;

/**
 *
 * @author juand
 */
public class Reference {
    	private int reference;
	private Frames frames;

	public Reference(int reference, int numberOfFrames) {
		this(reference, new Frames(numberOfFrames));
	}

	private Reference(int reference, Frames frames) {
		this.reference = reference;
		this.frames = frames;
	}

	public int getReference() {
		return reference;
	}

	public Frames getFrames() {
		return frames;
	}
}
