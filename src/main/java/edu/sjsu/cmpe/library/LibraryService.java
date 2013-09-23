package edu.sjsu.cmpe.library;

import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;

import edu.sjsu.cmpe.library.api.resources.AuthorResource;
import edu.sjsu.cmpe.library.api.resources.BookResource;
import edu.sjsu.cmpe.library.api.resources.ReviewResource;
import edu.sjsu.cmpe.library.api.resources.RootResource;
import edu.sjsu.cmpe.library.config.LibraryServiceConfiguration;

public class LibraryService extends Service<LibraryServiceConfiguration> {

    public static void main(String[] args) throws Exception {
	new LibraryService().run(args);
    }


    public void initialize(Bootstrap<LibraryServiceConfiguration> bootstrap) {
	bootstrap.setName("library-service");
    }

  
    public void run(LibraryServiceConfiguration configuration,
	    Environment environment) throws Exception {
	/** Root API */
	environment.addResource(RootResource.class);
	/** Books APIs */
	environment.addResource(BookResource.class);
	/** Reviews APIs */
	environment.addResource(ReviewResource.class);
	/** Author APIs */
	environment.addResource(AuthorResource.class);
    }
}
