package net.sourceforge.fenixedu.domain.research.result.publication;

import net.sourceforge.fenixedu.domain.Country;
import net.sourceforge.fenixedu.domain.Person;
import net.sourceforge.fenixedu.domain.exceptions.DomainException;
import net.sourceforge.fenixedu.domain.organizationalStructure.Unit;
import net.sourceforge.fenixedu.domain.research.result.ResultParticipation.ResultParticipationRole;
import net.sourceforge.fenixedu.util.Month;

/**
 * A book with an explicit publisher.
 * Required fields: author or editor, title, publisher, year.
 * Optional fields: volume, series, address, edition, month, note.
 * 
 * Extra from previous publications: isbn, numberPages, country, language, scope
 */
public class Book extends Book_Base {
    
    public Book() {
        super();
    }
    
    //constructor with required fields
    public Book(Person participator, ResultParticipationRole participatorRole, String title, Unit publisher, Integer year) {
        super();
        if((participator == null) || (title == null) || (title.length() == 0) || (publisher == null) || (year == null))
            throw new DomainException("error.publication.missingRequiredFields");
        if(participatorRole == null)
            throw new DomainException("error.publication.neededResultParticipationRole");
        
        setParticipation(participator, participatorRole);
        setTitle(title);
        setPublisher(publisher);
        setYear(year);
    }
    
    //edit with required fields
    public void edit(String title, Unit publisher, Integer year) {
        if((title == null) || (title.length() == 0) || (publisher == null) || (year == null))
            throw new DomainException("error.publication.missingRequiredFields");
        
        setTitle(title);
        setPublisher(publisher);
        setYear(year);
    }
    
    public void setNonRequiredAttributes(String volume, String series, String address, String edition,
            Integer isbn, Integer numberPages, String language, Country country, ScopeType scope,
            String note, Month month, String url) {

        this.setVolume(volume);
        this.setSeries(series);
        this.setAddress(address);
        this.setEdition(edition);
        this.setIsbn(isbn);
        this.setNumberPages(numberPages);
        this.setLanguage(language);
        this.setCountry(country);
        this.setScope(scope);
        this.setNote(note);
        this.setMonth(month);
        this.setUrl(url);
    }
}
