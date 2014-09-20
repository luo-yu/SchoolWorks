public class Sub extends Base {
	public void f() {
		System.out.print("Sub f");
	
	}

	public void h() {
		System.out.print("Sub h");
	}

	public void j() {
		super.j();
		System.out.print("Sub j");
	}
}
