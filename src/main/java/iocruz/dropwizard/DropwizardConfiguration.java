package iocruz.dropwizard;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class DropwizardConfiguration extends Configuration {

    @Valid @NotNull @JsonProperty
    private final DataSourceFactory database = new DataSourceFactory();

    public DataSourceFactory getDatabase() { return database; }
}
