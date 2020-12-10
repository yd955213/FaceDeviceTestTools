package sqlite3;

import java.util.concurrent.locks.ReentrantLock;

public class PersonID {
	private static int personID = 0;
	private ReentrantLock lock = null;
	/**
	 * 开启软件时获取数据库中最大人员ID，当有新人加入时，其值加1；
	 */
	private PersonID() {
		lock = new ReentrantLock();
	}
	
	public static PersonID getInstance() {
		return PersonIdHandler.instance;
	}
	public static class PersonIdHandler{
		private static PersonID instance = new PersonID();
	}
	
	public int getPersonIdInDb() {
		personID = DataBaseExecute.getInstance().getPersonID();
		return personID;
	}
	/**
	 * 开启软件时获取数据库中最大人员ID，当有新人加入时，其值加1；
	 * @return personID += 1;
	 */
	public int addPersonID() {
		lock.lock();
		personID += 1;
		lock.unlock();
		return personID;
	}
	public int getPersonID() {
		return personID;
	}

	
}
