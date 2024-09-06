package com.github.st0rm1O1;

import javax.swing.JOptionPane;

import com.github.st0rm1O1.frame.ApplicationFrame;
import com.github.st0rm1O1.utilities.DialogX;

public class MyBank {

	public static void main(String[] args) {
		try {
			new ApplicationFrame();
		} catch (Throwable e) {
			new DialogX().displayDialog(null, 404,
					e.getClass().getSimpleName() + " : " + e.getMessage(),
					JOptionPane.ERROR_MESSAGE);
			throw e;
		}
	}
}