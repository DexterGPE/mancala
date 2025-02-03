package mancala.api;

import mancala.api.controllers.MancalaController;
import mancala.domain.IMancalaFactory;
import mancala.domain.MancalaFactory;
import mancala.persistence.IMancalaRepository;
import mancala.persistence.MongoDBMancalaRepository;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

public class App {
    private static final int PORT = 8080;

    public static void main(String[] args) throws Exception {
        Server server = createServer(PORT);
        
        server.start();

        // Update the listening message to reflect the new binding address
        System.out.println("Started server.");
        System.out.format("Listening on http://0.0.0.0:%d/%n", PORT); // Binding to 0.0.0.0
        System.out.println("Press CTRL+C to exit.");

        server.join();
    }

    private static Server createServer(int port) {
        var server = new Server(port);

        ServletContextHandler context = createStatefulContext(server);
        registerServlets(context);

        return server;
    }

    private static ServletContextHandler createStatefulContext(Server server) {
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);

        return context;
    }

    private static void registerServlets(ServletContextHandler context) {
        // Use the Jersey framework to translate the methods in the
        // MancalaController class to server endpoints (servlets).
        // For example, the start method will become an endpoint at
        // http://localhost:8080/mancala/api/start
        context.addServlet(new ServletHolder(new ServletContainer(createResources())), "/*");
    }

    private static ResourceConfig createResources() {
        // Get MongoDB URI from environment variable, with a fallback
        String mongoUri = System.getenv("MONGO_URI");
        if (mongoUri == null) {
            mongoUri = "mongodb://localhost:27017/mancala"; // fallback to default
        }

        // Create the dependencies we want to inject
        IMancalaFactory factory = new MancalaFactory();
        IMancalaRepository repository = new MongoDBMancalaRepository(mongoUri); // Pass the mongoUri here

        // Create the MancalaController and inject the dependencies
        MancalaController mancalaController = new MancalaController(factory, repository);
        
        // Register our MancalaController
        return new ResourceConfig().register(mancalaController);
    }
}
