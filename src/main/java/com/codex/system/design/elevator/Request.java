package com.codex.system.design.elevator;

/**
 * Represents a request to the elevator system.
 */
public class Request implements Comparable<Request> {
    private InternalRequest internalRequest;
    private ExternalRequest externalRequest;

    public Request(InternalRequest internalRequest, ExternalRequest externalRequest) {
        this.internalRequest = internalRequest;
        this.externalRequest = externalRequest;
    }

    public InternalRequest getInternalRequest() {
        return internalRequest;
    }

    public void setInternalRequest(InternalRequest internalRequest) {
        this.internalRequest = internalRequest;
    }

    public ExternalRequest getExternalRequest() {
        return externalRequest;
    }

    public void setExternalRequest(ExternalRequest externalRequest) {
        this.externalRequest = externalRequest;
    }

    @Override
    public int compareTo(Request req) {
        return Integer.compare(this.getInternalRequest().getDestinationFloor(), req.getInternalRequest().getDestinationFloor());
    }
}