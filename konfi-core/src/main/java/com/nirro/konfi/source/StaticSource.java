package com.nirro.konfi.source;

import com.nirro.konfi.exception.SourceAccessException;

import java.util.Properties;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Source that can't be changed at runtime.
 * the return value of the delegated source get() method is cached and reused
 */
public class StaticSource implements Source {

    private final Source delegate;
    private final AtomicReference<Properties> value;

    /**
     * Construct a new Static Source
     * @param delegate source
     */
    public StaticSource(Source delegate) {
        this.delegate = delegate;
        value = new AtomicReference<>();
    }

    @Override
    public Properties get() throws SourceAccessException {
            Properties val = value.get();
            if (val == null) {
                val = value.updateAndGet(cur -> cur == null ? delegate.get() : cur);
            }
            return val;
    }
}
