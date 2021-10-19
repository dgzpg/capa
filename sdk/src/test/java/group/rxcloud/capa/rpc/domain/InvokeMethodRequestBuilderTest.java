package group.rxcloud.capa.rpc.domain;

import group.rxcloud.cloudruntimes.domain.core.invocation.HttpExtension;
import group.rxcloud.cloudruntimes.domain.core.invocation.InvokeMethodRequest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;


public class InvokeMethodRequestBuilderTest {

    private InvokeMethodRequestBuilder invokeMethodRequestBuilder;

    @Before
    public void setUp() {
        invokeMethodRequestBuilder = new InvokeMethodRequestBuilder("appId", "method");
    }

    @Test
    public void testWithContentType_Success() {
        InvokeMethodRequestBuilder requestBuilder = invokeMethodRequestBuilder.withContentType("application/json");
        Assert.assertNotNull(requestBuilder);
    }

    @Test
    public void testWithBody_Success() {
        InvokeMethodRequestBuilder requestBuilder = invokeMethodRequestBuilder.withBody("body");
        Assert.assertNotNull(requestBuilder);
    }

    @Test
    public void testWithHttpExtension_Success() {
        InvokeMethodRequestBuilder requestBuilder = invokeMethodRequestBuilder.withHttpExtension(HttpExtension.POST);
        Assert.assertNotNull(requestBuilder);
    }

    @Test
    public void testWithMetadata_Success() {
        Map<String, String> metadata = new HashMap<>();
        metadata.put("key", "value");

        InvokeMethodRequestBuilder requestBuilder = invokeMethodRequestBuilder.withMetadata(metadata);
        Assert.assertNotNull(requestBuilder);
    }

    @Test
    public void testBuild_Success() {
        Map<String, String> metadata = new HashMap<>();
        metadata.put("key", "value");

        InvokeMethodRequest invokeMethodRequest = invokeMethodRequestBuilder
                .withContentType("application/json")
                .withBody("body")
                .withHttpExtension(HttpExtension.POST)
                .withMetadata(metadata)
                .build();

        Assert.assertEquals("appId", invokeMethodRequest.getAppId());
        Assert.assertEquals("method", invokeMethodRequest.getMethod());
        Assert.assertEquals("application/json", invokeMethodRequest.getContentType());
        Assert.assertEquals("body", invokeMethodRequest.getBody());
        Assert.assertEquals(HttpExtension.POST, invokeMethodRequest.getHttpExtension());

        Map<String, String> requestMetadata = invokeMethodRequest.getMetadata();
        Assert.assertEquals("value", requestMetadata.get("key"));
    }
}