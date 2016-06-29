package uy.com.uma.logicgame.test;

import java.io.File;

import uy.com.uma.logicgame.ant.Lg2xmlTask;

/**
 * Testea el uso de la tarea Lg2xmlTask que graba en un archivo .xml el juego persistido en la base de datos
 *
 * @author Santiago Dalchiele
 */
public class TestLg2xmlTask {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Lg2xmlTask task = new Lg2xmlTask();
			task.setId(2);
			task.setFile(new File("c:/temp/02-turismo-espana-bd.xml"));
			task.setOverwrite(true);
			task.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
