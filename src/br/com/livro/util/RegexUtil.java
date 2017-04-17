package br.com.livro.util;

import java.util.regex.Pattern;

import javax.sql.rowset.serial.SerialException;

public class RegexUtil {

	private static final Pattern regexAll = Pattern.compile("/carros");
	private static final Pattern regexById = Pattern.compile("/carros/([0-9]*)");
	
	// Verifica se a URL é no padrão "/carros/id"
	public static Long matchId(String questUri) throws SerialException{
		// Verifica o ID
		java.util.regex.Matcher matcher = regexById.matcher(questUri);
		if(matcher.find() && matcher.groupCount() > 0){
			String s = matcher.group(1);
			if(s != null && s.trim().length() > 0){
				Long id = Long.parseLong(s);
				return id;
			}
		}
		return null;
	}
	
	// Verifica se a URL é no padrão "/carros/id"
	public boolean matchAll(String questUri) throws SerialException{
		// Verifica o ID
		java.util.regex.Matcher matcher = regexAll.matcher(questUri);
		if(matcher.find()){
			return true;
		}
		return false;
	}
}
