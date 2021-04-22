package bank;

public interface IDataAccess {
	public void display(int a_id) throws Exception;

	public void createAccount(int id1) throws Exception;

	public void deposit(int a_id, int amt) throws Exception;

	public void withdraw(int a_id, int amt) throws Exception;

	public void deleteAccount(int id) throws Exception;

	public void transferAccount(int id1, int id2, int amt) throws Exception;

	public void editAccount(int a_id) throws Exception;

	public void transactionHistory(int a_id) throws Exception;
}
