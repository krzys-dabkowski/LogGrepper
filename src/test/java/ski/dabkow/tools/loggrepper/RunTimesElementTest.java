package ski.dabkow.tools.loggrepper;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import ski.dabkow.tools.loggrepper.RunTimesElement;

public class RunTimesElementTest {

	@Test
	public void equalsTest() {
		RunTimesElement rte1 = new RunTimesElement("1");
		RunTimesElement rte2 = new RunTimesElement("1");
		assertTrue(rte1.equals(rte2));
	}

	@Test
	public void equalsTest_negative() {
		RunTimesElement rte1 = new RunTimesElement("1");
		RunTimesElement rte2 = new RunTimesElement("2");
		assertFalse(rte1.equals(rte2));
	}

	@Test
	public void equalsTest_array_contains() {
		ArrayList<RunTimesElement> list = new ArrayList<RunTimesElement>();
		RunTimesElement rte1 = new RunTimesElement("1");
		list.add(rte1);
		RunTimesElement rte2 = new RunTimesElement("1");
		assertTrue(list.contains(rte2));
	}

	@Test
	public void equalsTest_array_indexOf() {
		ArrayList<RunTimesElement> list = new ArrayList<RunTimesElement>();
		RunTimesElement rte1 = new RunTimesElement("1");
		RunTimesElement rte3 = new RunTimesElement("3");
		RunTimesElement rte4 = new RunTimesElement("4");
		RunTimesElement rte5 = new RunTimesElement("5");
		list.add(rte1);
		list.add(rte3);
		list.add(rte4);
		list.add(rte5);

		RunTimesElement rte2 = new RunTimesElement("5");
		// System.out.println(list.indexOf(rte2));
		assertTrue(list.indexOf(rte2) == 3);
	}

	@Test
	public void equalsTest_1() {
		ArrayList<RunTimesElement> list = new ArrayList<RunTimesElement>();
		RunTimesElement rte1 = new RunTimesElement(
				"ea703fe0-b934-4692-bb5b-7366cf96388e_2");
		RunTimesElement rte3 = new RunTimesElement(
				"ea703fe0-b934-4692-bb5b-7366cf96388e_2");
		// RunTimesElement rte4 = new RunTimesElement("4");
		// RunTimesElement rte5 = new RunTimesElement("5");
		list.add(rte1);
		// list.add(rte3);
		// list.add(rte4);
		// list.add(rte5);

		// RunTimesElement rte2 = new RunTimesElement("5");
		// System.out.println(list.indexOf(rte2));
		assertTrue(rte1.equals(list.get(0)));
	}

}
