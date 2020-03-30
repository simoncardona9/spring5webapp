package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRespository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherReporitory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRespository authorRespository;
    private final BookRepository bookRepository;
    private final PublisherReporitory publisherReporitory;

    public BootStrapData(AuthorRespository authorRespository, BookRepository bookRepository, PublisherReporitory publisherReporitory) {
        this.authorRespository = authorRespository;
        this.bookRepository = bookRepository;
        this.publisherReporitory = publisherReporitory;
    }

    @Override
    public void run(String... args) throws Exception {

        Publisher publisher = new Publisher("pepe", "Somewhere", "NY", "NY", "NY");
        publisherReporitory.save(publisher);

        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "123123");
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);
        ddd.setPublisher(publisher);
        publisher.getBooks().add(ddd);

        authorRespository.save(eric);
        bookRepository.save(ddd);
        publisherReporitory.save(publisher);

        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development without EJB", "546546543132135464");
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);
        noEJB.setPublisher(publisher);
        publisher.getBooks().add(noEJB);

        authorRespository.save(rod);
        bookRepository.save(noEJB);

        System.out.println("Started in Bootstrap");
        System.out.println("Number of Books: " + bookRepository.count());
        System.out.println("Number of Publishers: " + publisherReporitory.count());
        System.out.println("Number of Publishers books: " + publisher.getBooks().size());

    }
}
