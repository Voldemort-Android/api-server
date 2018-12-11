package voldemort.writter.server.persistence;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.EnumSet;

import javax.persistence.Entity;

import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.dialect.MySQL57Dialect;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.schema.TargetType;
import org.reflections.Reflections;

public class SchemaGenerator {

	public static void main(String args[]) throws IOException {
		
		StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
	        .applySetting("hibernate.dialect", MySQL57Dialect.class)
	        .build();
		
	    MetadataSources sources = new MetadataSources(registry);
	    new Reflections("voldemort.writter.server.persistence.entity")
	        .getTypesAnnotatedWith(Entity.class)
	        .forEach(sources::addAnnotatedClass);

	    Metadata metadata = sources.buildMetadata();
	    
	    Files.deleteIfExists(Paths.get("db-schema.sql"));
	    
	    new SchemaExport()
	    	.setDelimiter(";")
            .setOutputFile("db-schema.sql")
            .create(EnumSet.of(TargetType.SCRIPT), metadata);
	    
	}

}
