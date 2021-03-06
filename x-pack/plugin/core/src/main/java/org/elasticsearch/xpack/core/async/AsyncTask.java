/*
 * Copyright Elasticsearch B.V. and/or licensed to Elasticsearch B.V. under one
 * or more contributor license agreements. Licensed under the Elastic License
 * 2.0; you may not use this file except in compliance with the Elastic License
 * 2.0.
 */

package org.elasticsearch.xpack.core.async;

import org.elasticsearch.tasks.TaskManager;

import java.util.Map;

/**
 * A task that supports asynchronous execution and provides information necessary for safe temporary storage of results
 */
public interface AsyncTask {
    /**
     * Returns all of the request contexts headers
     */
    Map<String, String> getOriginHeaders();

    /**
     * Returns the {@link AsyncExecutionId} of the task
     */
    AsyncExecutionId getExecutionId();

    /**
     * Returns true if the task is cancelled
     */
    boolean isCancelled();

    /**
     * Extends the expiration time of the (partial) response if needed
     */
    void extendExpirationTime(long newExpirationTimeMillis);

    /**
     * Performs necessary checks, cancels the task and calls the runnable upon completion
     */
    void cancelTask(TaskManager taskManager, Runnable runnable, String reason);
}
