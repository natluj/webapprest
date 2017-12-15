package fr.dta.webapprest.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchService {

	@RequestMapping(value = "/images/search/{dateYear}/{dateMonth}/{dateDay}", method = RequestMethod.GET)
	@ResponseBody
	public String searchSortedByName(@PathVariable("dateYear") int year, @PathVariable("dateMonth") int month,
			@PathVariable("dateDay") int day, @RequestParam(name="sort", required=false) String sort) throws ParseException {
		String dateStr = year + "/" + month + "/" + day;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
		Date date = formatter.parse(dateStr);
		return "Liste des images du " + date.toString() + " triés par " + sort;
	}

}
