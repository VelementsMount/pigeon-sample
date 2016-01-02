package payne.framework.pigeon.sample.springmvc;

public class Pagination {
	private int page;
	private int size;

	public Pagination() {
		super();
	}

	public Pagination(int page, int size) {
		super();
		this.page = page;
		this.size = size;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

}
