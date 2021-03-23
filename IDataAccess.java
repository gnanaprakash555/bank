package bank;

public interface IDataAccess {
	public void display(String s) throws Exception;
	public void update(String s) throws Exception;
	public void deposit(int amt,String s) throws Exception;
	public void withdraw(int amt,String s) throws Exception;
	public void histry(String id) throws Exception;
	public void transfer(int amt,String a) throws Exception;
}