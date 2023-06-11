public class MyClass {
	private int int1;
	private String obj1;

	/** Constructor */
	public MyClass(int i, String o) {
		int1 = i;
		obj1 = o;
	}

	/** Demonstration "equals" method */
	public boolean equals(Object o) {
		if (o == this)		// optimization
				return true;
		if (o == null)
				return false;
		if (o.getClass() != this.getClass())
				return false;

		MyClass other = (MyClass)o;		// OK, cast to this class

		// compare field-by-field
		if (int1 != other.int1)			// compare primitives directly
				return false;
		if (!obj1.equals(other.obj1))	// compare objects using their equals
				return false;
		return true;
	}

	/** Demonstration "hashCode" method */
	public int hashCode() {
		return 17 * int1 |
			obj1.hashCode();
	}
}
