package edu.sjsu.cmpe.library.domain;

public enum BookStatus {
	available("available"), checked_out("checked-out"), in_queue("in-queue"), lost(
			"lost");

	private String status;

	/**
	 * @param status
	 */
	private BookStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public static boolean contains(String newStatus) {

		for (BookStatus myStatus : BookStatus.values()) {
			if (myStatus.name().equalsIgnoreCase(newStatus)) {
				return true;
			}
		}

		return false;
	}

	public  void setStatus(String newStatus) {

		if (contains(newStatus)) {
			this.status = newStatus;
		} else {
			this.status = null;
		}
	}

	public String toString() {
		return status;
	}

	public static void main(String[] args) {
		BookStatus myStatus = BookStatus.lost;

		System.out.println("Status is " + myStatus);
		myStatus.setStatus("available1");
		System.out.println("Status is " + myStatus);

	}
}
