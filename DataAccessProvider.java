package bank;

public class DataAccessProvider {
public static IDataAccess getDataAccess() {
	return new FileDataAccess();
}
}
