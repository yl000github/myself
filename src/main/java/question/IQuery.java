package question;

import java.util.List;

import exception.ErrorException;

public interface IQuery {
	public List<Answer> query(String question) throws ErrorException;
}
