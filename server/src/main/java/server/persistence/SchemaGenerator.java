package server.persistence;

import java.io.IOException;
import java.util.EnumSet;

import javax.persistence.Entity;

import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.dialect.MySQL8Dialect;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.schema.TargetType;
import org.reflections.Reflections;

public class SchemaGenerator {

	public static void main(String args[]) throws IOException {
		
		StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
	        .applySetting("hibernate.dialect", MySQL8Dialect.class)
	        .build();
		
	    MetadataSources sources = new MetadataSources(registry);
	    new Reflections("server.persistence.entity")
	        .getTypesAnnotatedWith(Entity.class)
	        .forEach(sources::addAnnotatedClass);

	    Metadata metadata = sources.buildMetadata();
	    
	    new SchemaExport()
	    	.setDelimiter(";")
            .setOutputFile("db-schema.sql")
            .create(EnumSet.of(TargetType.SCRIPT), metadata);
	    
	}

}
