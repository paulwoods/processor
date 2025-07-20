package org.mrpaulwoods.processor.exceptions;

import java.util.UUID;

public class JobNotFoundException extends ProcessorException {

    public JobNotFoundException(UUID id) {
        super("Job with id " + id + " not found");
    }

}
